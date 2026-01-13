package com.example.loadtest.apis;

import static us.abstracta.jmeter.javadsl.JmeterDsl.*;
import org.apache.http.entity.ContentType;
import com.google.gson.Gson;

import com.example.loadtest.config.ApiConstants;
import com.example.loadtest.config.TestConfig;
import com.example.loadtest.data.TestData;
import com.example.loadtest.model.NotificationTokenRequest;
import com.example.loadtest.model.UpdateDeviceRequest;
import com.example.loadtest.model.UserAutoSignInRequest;

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
                // TODO: Replace with actual EMAIL and ONBOARDING_ID values or variables
                UserAutoSignInRequest autoSignInRequest = new UserAutoSignInRequest(
                                "S3407752E+2809@gmail.com", // Replace with ${EMAIL} variable when available
                                "lGqkB6nSBeK9xWpwINBLCIJc3coxyo7lC2uPJ+oZzyG60nveE+6bxUqFjjFqHwYeMZt9uQlM5sZBjSuqn32hMCHpCjpUse4O3HwZSQP7XGkjD/b9rEbbTnX8zazOtHaYmujTYGdtHht+EivFFvQtMTRoFZnsCIyvWsKCTA97xrhXVMNHsE3QmiFpjebEZjAXt5RW/AwHoT5wWKaPL+pSXLxyF/mrFy3VrAO0hG6+1FUdbk5ey8urHKreS4O2PEB5bRx444i0rHe+ZJuSRUZG9VJJxRiIda92gY/xUg9+espu1xRvigaHxpkzw/hrivmeoPnxo8VZnBh1nyP7T4dUMw==" // Replace
                );

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

                testPlan(
                                threadGroup(1, 1,
                                                // NOTE: Target throughput is configured in TestConfig (60
                                                // requests/minute = 1
                                                // TPS)
                                                // To control throughput, adjust the number of threads and iterations
                                                // Or use preciseThroughputTimer() if available in your JMeter DSL
                                                // version

                                                // POST request for auth/userAutoSignIn
                                                httpSampler(config.getBaseUrl()
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
                                                                                // Extract accessToken from response
                                                                                jsonExtractor("accessToken",
                                                                                                "accessToken")
                                                                                                .defaultValue("NOT_FOUND"),
                                                                                // Optional: Extract other response
                                                                                // fields
                                                                                jsonExtractor("userId", "userId")
                                                                                                .defaultValue("NOT_FOUND"),
                                                                                // Extract expiryTimestamp from response
                                                                                jsonExtractor("expiryTimestamp",
                                                                                                "expiryTimestamp")
                                                                                                .defaultValue("NOT_FOUND"),
                                                                                // Debug: Print response if
                                                                                // userAutoSignIn failed
                                                                                jsr223PostProcessor(s -> {
                                                                                        if (!"200".equals(s.prev
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

                                                // POST request for auth/login/ack using the extracted accessToken
                                                httpSampler(config.getBaseUrl() + ApiConstants.UVMC_AUTH_LOGIN_ACK)
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
                                                                // TODO: Add JSON extractors for response data if needed
                                                                ),

                                                // Start Loop for Authenticated Requests
                                                forLoopController(30,
                                                                // GET request for getIpAddr using the extracted
                                                                // accessToken
                                                                httpSampler(config.getBaseUrl()
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
                                                                                                // Extract ipAddress
                                                                                                // from response
                                                                                                jsonExtractor("ipAddress",
                                                                                                                "ipAddress")
                                                                                                                .defaultValue("NOT_FOUND")),

                                                                // POST request for updateDevice using the extracted
                                                                // ipAddress
                                                                httpSampler(config.getBaseUrl()
                                                                                + ApiConstants.UVMC_UPDATE_DEVICE)
                                                                                .post(gson.toJson(updateDeviceRequest),
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
                                                                                                // Extract coolingPeriod
                                                                                                // from response
                                                                                                jsonExtractor("coolingPeriod",
                                                                                                                "coolingPeriod")
                                                                                                                .defaultValue("NOT_FOUND")
                                                                                // Debug block removed
                                                                                ),

                                                                // GET request for user/attribute using the extracted
                                                                // accessToken
                                                                httpSampler(config.getBaseUrl()
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
                                                                                // TODO: Add JSON extractors for
                                                                                // response data if needed
                                                                                ),

                                                                // GET request for user/current using the extracted
                                                                // accessToken
                                                                httpSampler(config.getBaseUrl()
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
                                                                                                                                TestData.HEADER_ACCEPT)
                                                                                // TODO: Add JSON extractors for
                                                                                // response data if needed
                                                                                ),

                                                                // POST request for user/wallet/register using the
                                                                // extracted accessToken
                                                                httpSampler(config.getBaseUrl()
                                                                                + ApiConstants.UVMC_USER_WALLET_REGISTER)
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
                                                                                // TODO: Add JSON extractors for
                                                                                // response data if needed
                                                                                ),

                                                                // GET request for npc/cards using the extracted
                                                                // accessToken
                                                                httpSampler(config.getBaseUrl()
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
                                                                                // TODO: Add JSON extractors for
                                                                                // response data if needed
                                                                                ),

                                                                // GET request for user/preference using the extracted
                                                                // accessToken
                                                                httpSampler(config.getBaseUrl()
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
                                                                                // TODO: Add JSON extractors for
                                                                                // response data if needed
                                                                                ),

                                                                // GET request for paymentmethod/list using the
                                                                // extracted accessToken
                                                                httpSampler(config.getBaseUrl()
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
                                                                                // TODO: Add JSON extractors for
                                                                                // response data if needed
                                                                                ),

                                                                // GET request for maintenance using the extracted
                                                                // accessToken
                                                                httpSampler(config.getBaseUrl()
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
                                                                                // TODO: Add JSON extractors for
                                                                                // response data if needed
                                                                                ),

                                                                // GET request for appversion/android with query
                                                                // parameters
                                                                httpSampler(config.getBaseUrl()
                                                                                + ApiConstants.UVMC_APP_VERSION_ANDROID
                                                                                +
                                                                                "?version=" + config.getAppVersion()
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
                                                                                // TODO: Add JSON extractors for
                                                                                // response data if needed
                                                                                ),

                                                                // GET request for announcement/banner using the
                                                                // extracted accessToken
                                                                httpSampler(config.getBaseUrl()
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
                                                                                // TODO: Add JSON extractors for
                                                                                // response data if needed
                                                                                ),

                                                                // GET request for marketing/banner using the extracted
                                                                // accessToken
                                                                httpSampler(config.getBaseUrl()
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
                                                                                // TODO: Add JSON extractors for
                                                                                // response data if needed
                                                                                ),

                                                                // POST request for notification/last using the
                                                                // extracted accessToken
                                                                httpSampler(config.getBaseUrl()
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
                                                                                // TODO: Add JSON extractors for
                                                                                // response data if needed
                                                                                ),

                                                                // GET request for notification/smartprompt/list with
                                                                // limit query parameter
                                                                httpSampler(
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
                                                                                // TODO: Add JSON extractors for
                                                                                // response data if needed
                                                                                ),

                                                                // POST request for notification/token
                                                                httpSampler(config.getBaseUrl()
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
                                                                                // TODO: Add JSON extractors for
                                                                                // response data if needed
                                                                                )),
                                                // POST request for auth/logout after all APIs
                                                httpSampler(config.getBaseUrl() + ApiConstants.UVMC_AUTH_LOGOUT)
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
                                                                // TODO: Add JSON extractors for response data if needed
                                                                )), // End of Thread Group
                                jtlWriter("uvmc-results.jtl"),
                                htmlReporter("uvmc-html-report")).run();

                System.out.println("CIAM API UVMC Registration Test completed!");
        }
}
