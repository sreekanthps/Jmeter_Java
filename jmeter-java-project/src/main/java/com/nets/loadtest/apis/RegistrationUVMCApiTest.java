package com.nets.loadtest.apis;

import static us.abstracta.jmeter.javadsl.JmeterDsl.*;
import us.abstracta.jmeter.javadsl.core.postprocessors.DslRegexExtractor;
import org.apache.http.entity.ContentType;
import com.google.gson.Gson;

import com.nets.loadtest.config.ApiConstants;
import com.nets.loadtest.config.TestConfig;
import com.nets.loadtest.data.TestData;
import com.nets.loadtest.model.NotificationTokenRequest;
import com.nets.loadtest.model.CardTransactionRequest;

import com.nets.loadtest.model.UpdateDeviceRequest;
import com.nets.loadtest.service.CardEncryptionService;
import com.nets.loadtest.model.UserAutoSignInRequest;

import java.io.IOException;

/**
 * CIAM API UVMC Registration Test using JMeter Java DSL.
 * Tests the UVMC (User Verification and Management Center) registration flow.
 */
public class RegistrationUVMCApiTest {

        private static final Gson gson = new Gson();

        public static void run() throws IOException {
                // Load configuration for UVMC SIT environment
                TestConfig config = TestConfig.uvmcSitConfig();

                // Create request payload for userAutoSignIn
                // Using variables from CSV: ${email} and ${onboardingId}
                UserAutoSignInRequest autoSignInRequest = new UserAutoSignInRequest(
                                "S3407752E+6561@gmail.com",
                                "fRzIVQRNSON9/kM/WO5Hqg8U2P2bxh9Fgr226OC1590OcDgs+3/0slPQuDm7X9ceN68j5eMPpF3a0aRBiFvjafi8CItR3dEGI9zF59owqtrs9IW+fxwYOJxTbKjNJM6MN0/edJ/GbDqrjkwMTCa0rTpwUMDtBLTM3c5mhyKSMroMtMl8rVgfV8y3OILGJr1VtIqZzMHlwbklG56NVIZfj/LPKgF1/i/DpQR4yPGQihBJ286iP/i0TWNVLHCcls/fbiyAXG5VFYS0CtD5+PJYSQGoLPtf2Hu/VsII3CRBQLYrLOSLbBIZoEadhETBrf0UKSWU6mK1/iSrALKXN0LOnw==");

                // Create request payload for updateDevice
                // Note: ipAddress will use the ${ipAddress} variable extracted from getIpAddr
                UpdateDeviceRequest updateDeviceRequest = new UpdateDeviceRequest(
                                TestData.IMEI_NUMBER,
                                "${ipAddress}", // ipAddress (from previous request)
                                TestData.LATITUDE,
                                TestData.LONGITUDE,
                                TestData.MOBILE_DEVICE_ID,
                                TestData.MOBILE_MODEL,
                                TestData.MOBILE_OS_VERSION);
                // Create request payload for notification/token
                NotificationTokenRequest notificationTokenRequest = new NotificationTokenRequest(
                                TestData.DEVICE_TYPE,
                                TestData.FCM_TOKEN);

                // Create request payload for card transaction
                CardTransactionRequest cardTransactionRequest = new CardTransactionRequest(
                                "1111737999876626",
                                "liew",
                                "4111111111111111",
                                "0",
                                "111",
                                "122030",
                                3000);

                testPlan(
                                // CSV Data Set Config to read email and onboardingId from export_20260112.csv
                                csvDataSet("export_20260112.csv"),

                                threadGroup(1, 1) // 1 thread, 1 iteration - runs all APIs once
                                                .children(
                                                                // Thread Group: Single user, single iteration
                                                                // This ensures all APIs in the test plan execute
                                                                // completely

                                                                // POST request for auth/userAutoSignIn
                                                                httpSampler(ApiConstants.UVMC_AUTH_USER_AUTO_SIGNIN,
                                                                                config.getBaseUrl()
                                                                                                + ApiConstants.UVMC_AUTH_USER_AUTO_SIGNIN)
                                                                                .post(gson.toJson(autoSignInRequest),
                                                                                                ContentType.APPLICATION_JSON)
                                                                                .children(
                                                                                                httpHeaders()
                                                                                                                .header("Accept-Encoding",
                                                                                                                                TestData.HEADER_ACCEPT_ENCODING)
                                                                                                                .header("User-Agent",
                                                                                                                                TestData.HEADER_USER_AGENT)
                                                                                                                .header("Accept",
                                                                                                                                TestData.HEADER_ACCEPT),
                                                                                                // Extract accessToken
                                                                                                // from response
                                                                                                jsonExtractor("accessToken",
                                                                                                                "accessToken")
                                                                                                                .defaultValue("NOT_FOUND"),
                                                                                                // Optional: Extract
                                                                                                // other response
                                                                                                // fields
                                                                                                jsonExtractor("userId",
                                                                                                                "userId")
                                                                                                                .defaultValue("NOT_FOUND"),
                                                                                                // Extract
                                                                                                // expiryTimestamp from
                                                                                                // response
                                                                                                jsonExtractor("expiryTimestamp",
                                                                                                                "expiryTimestamp")
                                                                                                                .defaultValue("NOT_FOUND"),
                                                                                                // Debug: Print response
                                                                                                // if
                                                                                                // userAutoSignIn failed
                                                                                                jsr223PostProcessor(
                                                                                                                s -> {
                                                                                                                        if (!"200".equals(
                                                                                                                                        s.prev
                                                                                                                                                        .getResponseCode())) {
                                                                                                                                System.out.println(
                                                                                                                                                "!!! userAutoSignIn FAILED !!!");
                                                                                                                                System.out.println(
                                                                                                                                                "Response Code: "
                                                                                                                                                                + s.prev.getResponseCode());
                                                                                                                                System.out
                                                                                                                                                .println("Response Body: "
                                                                                                                                                                + s.prev.getResponseDataAsString());
                                                                                                                        }
                                                                                                                })),

                                                                // POST request for auth/login/ack using the extracted
                                                                // accessToken
                                                                httpSampler(ApiConstants.UVMC_AUTH_LOGIN_ACK,
                                                                                config.getBaseUrl()
                                                                                                + ApiConstants.UVMC_AUTH_LOGIN_ACK)
                                                                                .post("", ContentType.APPLICATION_JSON)
                                                                                .children(
                                                                                                httpHeaders()
                                                                                                                .header("Authorization",
                                                                                                                                "Bearer ${accessToken}")
                                                                                                                .header("Accept-Encoding",
                                                                                                                                TestData.HEADER_ACCEPT_ENCODING)
                                                                                                                .header("User-Agent",
                                                                                                                                TestData.HEADER_USER_AGENT)
                                                                                                                .header("Accept",
                                                                                                                                TestData.HEADER_ACCEPT)
                                                                                // TODO: Add JSON extractors for
                                                                                // response data if needed
                                                                                ),

                                                                // Execute Authenticated Requests

                                                                // GET request for getIpAddr using the
                                                                // extracted
                                                                // accessToken
                                                                httpSampler(ApiConstants.UVMC_GET_IP_ADDR, config
                                                                                .getBaseUrl()
                                                                                + ApiConstants.UVMC_GET_IP_ADDR)
                                                                                .children(
                                                                                                httpHeaders()
                                                                                                                .header("Authorization",
                                                                                                                                "Bearer ${accessToken}")
                                                                                                                .header("Accept-Encoding",
                                                                                                                                TestData.HEADER_ACCEPT_ENCODING)
                                                                                                                .header("User-Agent",
                                                                                                                                TestData.HEADER_USER_AGENT)
                                                                                                                .header("Accept",
                                                                                                                                TestData.HEADER_ACCEPT),
                                                                                                // Extract
                                                                                                // ipAddress
                                                                                                // from
                                                                                                // response
                                                                                                jsonExtractor("ipAddress",
                                                                                                                "ipAddress")
                                                                                                                .defaultValue("NOT_FOUND")),

                                                                // POST request for updateDevice using
                                                                // the extracted
                                                                // ipAddress
                                                                httpSampler(ApiConstants.UVMC_UPDATE_DEVICE, config
                                                                                .getBaseUrl()
                                                                                + ApiConstants.UVMC_UPDATE_DEVICE)
                                                                                .post(gson.toJson(
                                                                                                updateDeviceRequest),
                                                                                                ContentType.APPLICATION_JSON)
                                                                                .children(
                                                                                                httpHeaders()
                                                                                                                .header("Authorization",
                                                                                                                                "Bearer ${accessToken}")
                                                                                                                .header("Accept-Encoding",
                                                                                                                                TestData.HEADER_ACCEPT_ENCODING)
                                                                                                                .header("User-Agent",
                                                                                                                                TestData.HEADER_USER_AGENT)
                                                                                                                .header("Accept",
                                                                                                                                TestData.HEADER_ACCEPT),
                                                                                                // Extract
                                                                                                // coolingPeriod
                                                                                                // from
                                                                                                // response
                                                                                                jsonExtractor("coolingPeriod",
                                                                                                                "coolingPeriod")
                                                                                                                .defaultValue("NOT_FOUND")
                                                                                // Debug block removed
                                                                                ),

                                                                // GET request for user/attribute using
                                                                // the extracted
                                                                // accessToken
                                                                httpSampler(ApiConstants.UVMC_USER_ATTRIBUTE, config
                                                                                .getBaseUrl()
                                                                                + ApiConstants.UVMC_USER_ATTRIBUTE)
                                                                                .children(
                                                                                                httpHeaders()
                                                                                                                .header("Authorization",
                                                                                                                                "Bearer ${accessToken}")
                                                                                                                .header("Accept-Encoding",
                                                                                                                                TestData.HEADER_ACCEPT_ENCODING)
                                                                                                                .header("User-Agent",
                                                                                                                                TestData.HEADER_USER_AGENT)
                                                                                                                .header("Accept",
                                                                                                                                TestData.HEADER_ACCEPT)
                                                                                // TODO: Add JSON
                                                                                // extractors for
                                                                                // response data if
                                                                                // needed
                                                                                ),

                                                                // GET request for user/current using
                                                                // the extracted
                                                                // accessToken
                                                                httpSampler(ApiConstants.UVMC_USER_CURRENT, config
                                                                                .getBaseUrl()
                                                                                + ApiConstants.UVMC_USER_CURRENT)
                                                                                .children(
                                                                                                httpHeaders()
                                                                                                                .header("Authorization",
                                                                                                                                "Bearer ${accessToken}")
                                                                                                                .header("Accept-Encoding",
                                                                                                                                TestData.HEADER_ACCEPT_ENCODING)
                                                                                                                .header("User-Agent",
                                                                                                                                TestData.HEADER_USER_AGENT)
                                                                                                                .header("Accept",
                                                                                                                                TestData.HEADER_ACCEPT),

                                                                                                jsonExtractor("walletCreated",
                                                                                                                "walletCreated")
                                                                                                                .defaultValue("true")),

                                                                // Conditional execution: Only register
                                                                // wallet if walletCreated is false
                                                                ifController("${walletCreated}"
                                                                                + " == false")
                                                                                .children(
                                                                                                httpSampler(ApiConstants.UVMC_USER_WALLET_REGISTER,
                                                                                                                config
                                                                                                                                .getBaseUrl()
                                                                                                                                + ApiConstants.UVMC_USER_WALLET_REGISTER)
                                                                                                                .post("",
                                                                                                                                ContentType.APPLICATION_JSON)
                                                                                                                .children(
                                                                                                                                httpHeaders()
                                                                                                                                                .header("Authorization",
                                                                                                                                                                "Bearer ${accessToken}")
                                                                                                                                                .header("Accept-Encoding",
                                                                                                                                                                TestData.HEADER_ACCEPT_ENCODING)
                                                                                                                                                .header("User-Agent",
                                                                                                                                                                TestData.HEADER_USER_AGENT)
                                                                                                                                                .header("Accept",
                                                                                                                                                                TestData.HEADER_ACCEPT)

                                                                                                                )),

                                                                // GET request for npc/cards using the
                                                                // extracted
                                                                // accessToken
                                                                httpSampler(ApiConstants.UVMC_NPC_CARDS, config
                                                                                .getBaseUrl()
                                                                                + ApiConstants.UVMC_NPC_CARDS)
                                                                                .children(
                                                                                                httpHeaders()
                                                                                                                .header("Authorization",
                                                                                                                                "Bearer ${accessToken}")
                                                                                                                .header("Accept-Encoding",
                                                                                                                                TestData.HEADER_ACCEPT_ENCODING)
                                                                                                                .header("User-Agent",
                                                                                                                                TestData.HEADER_USER_AGENT)
                                                                                                                .header("Accept",
                                                                                                                                TestData.HEADER_ACCEPT)
                                                                                // TODO: Add JSON
                                                                                // extractors for
                                                                                // response data if
                                                                                // needed
                                                                                ),

                                                                // GET request for user/preference using
                                                                // the extracted
                                                                // accessToken
                                                                httpSampler(ApiConstants.UVMC_USER_PREFERENCE, config
                                                                                .getBaseUrl()
                                                                                + ApiConstants.UVMC_USER_PREFERENCE)
                                                                                .children(
                                                                                                httpHeaders()
                                                                                                                .header("Authorization",
                                                                                                                                "Bearer ${accessToken}")
                                                                                                                .header("Accept-Encoding",
                                                                                                                                TestData.HEADER_ACCEPT_ENCODING)
                                                                                                                .header("User-Agent",
                                                                                                                                TestData.HEADER_USER_AGENT)
                                                                                                                .header("Accept",
                                                                                                                                TestData.HEADER_ACCEPT)
                                                                                // TODO: Add JSON
                                                                                // extractors for
                                                                                // response data if
                                                                                // needed
                                                                                ),

                                                                // GET request for paymentmethod/list
                                                                // using the
                                                                // extracted accessToken
                                                                httpSampler(ApiConstants.UVMC_PAYMENT_METHOD_LIST,
                                                                                config.getBaseUrl()
                                                                                                + ApiConstants.UVMC_PAYMENT_METHOD_LIST)
                                                                                .children(
                                                                                                httpHeaders()
                                                                                                                .header("Authorization",
                                                                                                                                "Bearer ${accessToken}")
                                                                                                                .header("Accept-Encoding",
                                                                                                                                TestData.HEADER_ACCEPT_ENCODING)
                                                                                                                .header("User-Agent",
                                                                                                                                TestData.HEADER_USER_AGENT)
                                                                                                                .header("Accept",
                                                                                                                                TestData.HEADER_ACCEPT)
                                                                                // TODO: Add JSON
                                                                                // extractors for
                                                                                // response data if
                                                                                // needed
                                                                                ),

                                                                // GET request for maintenance using the
                                                                // extracted
                                                                // accessToken
                                                                httpSampler(ApiConstants.UVMC_MAINTENANCE, config
                                                                                .getBaseUrl()
                                                                                + ApiConstants.UVMC_MAINTENANCE)
                                                                                .children(
                                                                                                httpHeaders()
                                                                                                                .header("Authorization",
                                                                                                                                "Bearer ${accessToken}")
                                                                                                                .header("Accept-Encoding",
                                                                                                                                TestData.HEADER_ACCEPT_ENCODING)
                                                                                                                .header("User-Agent",
                                                                                                                                TestData.HEADER_USER_AGENT)
                                                                                                                .header("Accept",
                                                                                                                                TestData.HEADER_ACCEPT)
                                                                                // TODO: Add JSON
                                                                                // extractors for
                                                                                // response data if
                                                                                // needed
                                                                                ),

                                                                // GET request for appversion/android
                                                                // with query
                                                                // parameters
                                                                httpSampler(ApiConstants.UVMC_APP_VERSION_ANDROID,
                                                                                config.getBaseUrl()
                                                                                                + ApiConstants.UVMC_APP_VERSION_ANDROID
                                                                                                +
                                                                                                "?version="
                                                                                                + config.getAppVersion()
                                                                                                + "&codeVersion="
                                                                                                + config.getCodeVersion())
                                                                                .children(
                                                                                                httpHeaders()
                                                                                                                .header("Authorization",
                                                                                                                                "Bearer ${accessToken}")
                                                                                                                .header("Accept-Encoding",
                                                                                                                                TestData.HEADER_ACCEPT_ENCODING)
                                                                                                                .header("User-Agent",
                                                                                                                                TestData.HEADER_USER_AGENT)
                                                                                                                .header("Accept",
                                                                                                                                TestData.HEADER_ACCEPT)
                                                                                // TODO: Add JSON
                                                                                // extractors for
                                                                                // response data if
                                                                                // needed
                                                                                ),

                                                                // GET request for announcement/banner
                                                                // using the
                                                                // extracted accessToken
                                                                httpSampler(ApiConstants.UVMC_ANNOUNCEMENT_BANNER,
                                                                                config.getBaseUrl()
                                                                                                + ApiConstants.UVMC_ANNOUNCEMENT_BANNER)
                                                                                .children(
                                                                                                httpHeaders()
                                                                                                                .header("Authorization",
                                                                                                                                "Bearer ${accessToken}")
                                                                                                                .header("Accept-Encoding",
                                                                                                                                TestData.HEADER_ACCEPT_ENCODING)
                                                                                                                .header("User-Agent",
                                                                                                                                TestData.HEADER_USER_AGENT)
                                                                                                                .header("Accept",
                                                                                                                                TestData.HEADER_ACCEPT)
                                                                                // TODO: Add JSON
                                                                                // extractors for
                                                                                // response data if
                                                                                // needed
                                                                                ),

                                                                // GET request for marketing/banner
                                                                // using the extracted
                                                                // accessToken
                                                                httpSampler(ApiConstants.UVMC_MARKETING_BANNER, config
                                                                                .getBaseUrl()
                                                                                + ApiConstants.UVMC_MARKETING_BANNER)
                                                                                .children(
                                                                                                httpHeaders()
                                                                                                                .header("Authorization",
                                                                                                                                "Bearer ${accessToken}")
                                                                                                                .header("Accept-Encoding",
                                                                                                                                TestData.HEADER_ACCEPT_ENCODING)
                                                                                                                .header("User-Agent",
                                                                                                                                TestData.HEADER_USER_AGENT)
                                                                                                                .header("Accept",
                                                                                                                                TestData.HEADER_ACCEPT)
                                                                                // TODO: Add JSON
                                                                                // extractors for
                                                                                // response data if
                                                                                // needed
                                                                                ),

                                                                // POST request for notification/last
                                                                // using the
                                                                // extracted accessToken
                                                                httpSampler(ApiConstants.UVMC_NOTIFICATION_LAST, config
                                                                                .getBaseUrl()
                                                                                + ApiConstants.UVMC_NOTIFICATION_LAST)
                                                                                .post("", ContentType.APPLICATION_JSON) // Empty
                                                                                                                        // body
                                                                                                                        // POST
                                                                                                                        // request
                                                                                .children(
                                                                                                httpHeaders()
                                                                                                                .header("Authorization",
                                                                                                                                "Bearer ${accessToken}")
                                                                                                                .header("Accept-Encoding",
                                                                                                                                TestData.HEADER_ACCEPT_ENCODING)
                                                                                                                .header("User-Agent",
                                                                                                                                TestData.HEADER_USER_AGENT)
                                                                                                                .header("Accept",
                                                                                                                                TestData.HEADER_ACCEPT)
                                                                                // TODO: Add JSON
                                                                                // extractors for
                                                                                // response data if
                                                                                // needed
                                                                                ),

                                                                // GET request for
                                                                // notification/smartprompt/list with
                                                                // limit query parameter
                                                                httpSampler(ApiConstants.UVMC_NOTIFICATION_SMARTPROMPT_LIST,
                                                                                config.getBaseUrl()
                                                                                                + ApiConstants.UVMC_NOTIFICATION_SMARTPROMPT_LIST
                                                                                                + "?limit="
                                                                                                + config.getNotificationLimit())
                                                                                .children(
                                                                                                httpHeaders()
                                                                                                                .header("Authorization",
                                                                                                                                "Bearer ${accessToken}")
                                                                                                                .header("Accept-Encoding",
                                                                                                                                TestData.HEADER_ACCEPT_ENCODING)
                                                                                                                .header("User-Agent",
                                                                                                                                TestData.HEADER_USER_AGENT)
                                                                                                                .header("Accept",
                                                                                                                                TestData.HEADER_ACCEPT)
                                                                                // TODO: Add JSON
                                                                                // extractors for
                                                                                // response data if
                                                                                // needed
                                                                                ),

                                                                // POST request for notification/token
                                                                httpSampler(ApiConstants.UVMC_NOTIFICATION_TOKEN, config
                                                                                .getBaseUrl()
                                                                                + ApiConstants.UVMC_NOTIFICATION_TOKEN)
                                                                                .post(gson.toJson(
                                                                                                notificationTokenRequest),
                                                                                                ContentType.APPLICATION_JSON)
                                                                                .children(
                                                                                                httpHeaders()
                                                                                                                .header("Authorization",
                                                                                                                                "Bearer ${accessToken}")
                                                                                                                .header("Accept-Encoding",
                                                                                                                                TestData.HEADER_ACCEPT_ENCODING)
                                                                                                                .header("User-Agent",
                                                                                                                                TestData.HEADER_USER_AGENT)
                                                                                                                .header("Accept",
                                                                                                                                TestData.HEADER_ACCEPT)
                                                                                // TODO: Add JSON
                                                                                // extractors for
                                                                                // response data if
                                                                                // needed
                                                                                ),

                                                                // POST request for key/rsa?key=true
                                                                httpSampler(ApiConstants.UVMC_KEY_RSA, config
                                                                                .getBaseUrl()
                                                                                + ApiConstants.UVMC_KEY_RSA)
                                                                                .post("", ContentType.APPLICATION_JSON)
                                                                                .children(
                                                                                                httpHeaders()
                                                                                                                .header("Authorization",
                                                                                                                                "Bearer ${accessToken}")
                                                                                                                .header("Accept-Encoding",
                                                                                                                                TestData.HEADER_ACCEPT_ENCODING)
                                                                                                                .header("User-Agent",
                                                                                                                                TestData.HEADER_USER_AGENT)
                                                                                                                .header("Accept",
                                                                                                                                TestData.HEADER_ACCEPT),
                                                                                                regexExtractor("PUBLIC_KEY",
                                                                                                                "PUBLIC_KEY: (.+)")
                                                                                                                .fieldToCheck(DslRegexExtractor.TargetField.RESPONSE_HEADERS)
                                                                                                                .defaultValue("NOT_FOUND")),

                                                                // Encrypt card data using JSR223 Sampler
                                                                jsr223Sampler(s -> {
                                                                        System.out.println(
                                                                                        "=== Starting Card Encryption ===");

                                                                        String publicKey = s.vars.get("PUBLIC_KEY");
                                                                        System.out.println("Retrieved PUBLIC_KEY: " +
                                                                                        (publicKey != null ? publicKey
                                                                                                        .substring(0, Math
                                                                                                                        .min(50, publicKey
                                                                                                                                        .length()))
                                                                                                        + "..."
                                                                                                        : "NULL"));

                                                                        if (publicKey == null || "NOT_FOUND"
                                                                                        .equals(publicKey)) {
                                                                                System.out.println(
                                                                                                "ERROR: Public Key not found!");
                                                                                s.sampleResult.setSuccessful(false);
                                                                                s.sampleResult.setResponseMessage(
                                                                                                "Public Key not found");
                                                                                return;
                                                                        }

                                                                        // Convert cardTransactionRequest to JSON
                                                                        String cardJson = gson
                                                                                        .toJson(cardTransactionRequest);
                                                                        System.out.println("Card JSON: " + cardJson);

                                                                        // Convert JSON string to byte array
                                                                        byte[] bodyBytes = cardJson.getBytes(
                                                                                        java.nio.charset.StandardCharsets.UTF_8);
                                                                        System.out.println("Body bytes length: "
                                                                                        + bodyBytes.length);

                                                                        try {
                                                                                System.out.println(
                                                                                                "Calling CardEncryptionService.encryptCard()...");

                                                                                // Encrypt using CardEncryptionService
                                                                                CardEncryptionService.EncryptionResult result = CardEncryptionService
                                                                                                .encryptCard(publicKey,
                                                                                                                bodyBytes);

                                                                                System.out.println(
                                                                                                "Encryption SUCCESS!");
                                                                                System.out.println(
                                                                                                "Encrypted Body (first 100 chars): "
                                                                                                                +
                                                                                                                result.getEncryptedBody()
                                                                                                                                .substring(0, Math
                                                                                                                                                .min(100, result.getEncryptedBody()
                                                                                                                                                                .length()))
                                                                                                                + "...");
                                                                                System.out.println(
                                                                                                "Encrypted Key (first 100 chars): "
                                                                                                                +
                                                                                                                result.getEncryptedKey()
                                                                                                                                .substring(0, Math
                                                                                                                                                .min(100, result.getEncryptedKey()
                                                                                                                                                                .length()))
                                                                                                                + "...");
                                                                                System.out.println("IV: "
                                                                                                + result.getIv());
                                                                                System.out.println(
                                                                                                "Encryption Algorithm: "
                                                                                                                + result.getEncryption());

                                                                                // Store encrypted values in JMeter
                                                                                // variables
                                                                                s.vars.put("encrypt_body", result
                                                                                                .getEncryptedBody());
                                                                                s.vars.put("x_encryption_key", result
                                                                                                .getEncryptedKey());
                                                                                s.vars.put("x_encryption_iv",
                                                                                                result.getIv());

                                                                                System.out.println(
                                                                                                "Stored encrypted values in JMeter variables");
                                                                                System.out.println(
                                                                                                "=== Card Encryption Completed Successfully ===");

                                                                                s.sampleResult.setSuccessful(true);
                                                                        } catch (Exception e) {
                                                                                System.out.println(
                                                                                                "ERROR: Encryption FAILED!");
                                                                                System.out.println("Exception: " + e
                                                                                                .getClass().getName());
                                                                                System.out.println("Message: "
                                                                                                + e.getMessage());

                                                                                s.sampleResult.setSuccessful(false);
                                                                                s.sampleResult.setResponseMessage(
                                                                                                "Encryption failed: "
                                                                                                                + e.getMessage());
                                                                                e.printStackTrace();
                                                                        }
                                                                }),

                                                                // POST request for paymentmethod/nfp/atu
                                                                httpSampler(ApiConstants.UVMC_PAYMENT_METHOD_NFP_ATU,
                                                                                config.getBaseUrl()
                                                                                                + ApiConstants.UVMC_PAYMENT_METHOD_NFP_ATU)
                                                                                .post("${encrypt_body}",
                                                                                                ContentType.TEXT_PLAIN)
                                                                                .children(
                                                                                                httpHeaders()
                                                                                                                .header("Authorization",
                                                                                                                                "Bearer ${accessToken}")
                                                                                                                .header("X-ENCRYPTION",
                                                                                                                                ApiConstants.ENCRYPTION_ALGORITHM)
                                                                                                                .header("X-ENCRYPTION-KEY",
                                                                                                                                "${x_encryption_key}")
                                                                                                                .header("X-ENCRYPTION-IV",
                                                                                                                                "${x_encryption_iv}")
                                                                                                                .header("Content-Type",
                                                                                                                                "application/json")
                                                                                                                .header("Accept",
                                                                                                                                "application/json"),
                                                                                                // Debug: Log response
                                                                                                // for
                                                                                                // paymentmethod/nfp/atu
                                                                                                jsr223PostProcessor(
                                                                                                                s -> {
                                                                                                                        System.out.println(
                                                                                                                                        "=== paymentmethod/nfp/atu Response ===");
                                                                                                                        System.out.println(
                                                                                                                                        "Response Code: "
                                                                                                                                                        + s.prev.getResponseCode());
                                                                                                                        System.out.println(
                                                                                                                                        "Response Message: "
                                                                                                                                                        + s.prev.getResponseMessage());
                                                                                                                        System.out.println(
                                                                                                                                        "Response Body: "
                                                                                                                                                        + s.prev.getResponseDataAsString());
                                                                                                                        System.out.println(
                                                                                                                                        "Response Headers: "
                                                                                                                                                        + s.prev.getResponseHeaders());
                                                                                                                        System.out.println(
                                                                                                                                        "=======================================");
                                                                                                                })),
                                                                // POST request for auth/logout after all APIs
                                                                httpSampler(ApiConstants.UVMC_AUTH_LOGOUT, config
                                                                                .getBaseUrl()
                                                                                + ApiConstants.UVMC_AUTH_LOGOUT)
                                                                                .post("", ContentType.APPLICATION_JSON)
                                                                                .children(
                                                                                                httpHeaders()
                                                                                                                .header("Authorization",
                                                                                                                                "Bearer ${accessToken}")
                                                                                                                .header("Accept-Encoding",
                                                                                                                                TestData.HEADER_ACCEPT_ENCODING)
                                                                                                                .header("User-Agent",
                                                                                                                                TestData.HEADER_USER_AGENT)
                                                                                                                .header("Accept",
                                                                                                                                TestData.HEADER_ACCEPT)
                                                                                // TODO: Add JSON extractors for
                                                                                // response data if needed
                                                                                )), // End of Thread Group
                                jtlWriter("uvmc-results.jtl"),
                                htmlReporter("uvmc-html-report")).run();

                System.out.println("CIAM API UVMC Registration Test completed!");
        }
}
