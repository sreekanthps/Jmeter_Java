package com.nets.loadtest.apis;

import static us.abstracta.jmeter.javadsl.JmeterDsl.*;
import org.apache.http.entity.ContentType;
import com.google.gson.Gson;

import com.nets.loadtest.config.TestConfig;
import com.nets.loadtest.data.TestData;
import com.nets.loadtest.model.PasswordEncryptRequest;
import com.nets.loadtest.model.ProfileRequest;
import com.nets.loadtest.model.RegistrationInitRequest;

import java.io.IOException;

/**
 * CIAM API Registration Test using JMeter Java DSL.
 * Tests the complete registration flow including RSA key retrieval,
 * registration initialization, Singpass profile submission, and password
 * encryption.
 */
public class RegistrationApiTest {

    private static final Gson gson = new Gson();

    public static void run() throws IOException {
        // Load configuration for SIT environment
        TestConfig config = TestConfig.sitConfig();

        // Create request payloads
        RegistrationInitRequest initRequest = new RegistrationInitRequest(
                TestData.SAMPLE_ENCRYPTED_KEY,
                TestData.CONTEXT_SINGPASS,
                TestData.APP_VERSION);

        ProfileRequest profileRequest = new ProfileRequest(
                TestData.SAMPLE_SINGPASS_CODE);

        PasswordEncryptRequest passwordRequest = new PasswordEncryptRequest(
                TestData.TEST_PASSWORD,
                TestData.SAMPLE_SESSION_ID);

        testPlan(
                threadGroup(1, 1,
                        // Email counter for generating unique emails (for future use)
                        counter("emailCounter"),

                        // Limit counter to 9999 using JSR223 PreProcessor
                        jsr223PreProcessor("// Limit emailCounter to range 1-9999\n" +
                                "int counter = Integer.parseInt(vars.get(\"emailCounter\"));\n" +
                                "int limitedCounter = ((counter - 1) % 9999) + 1;\n" +
                                "vars.put(\"emailCounter\", String.valueOf(limitedCounter));"),

                        // GET request for RSA key
                        httpSampler("https://" + config.getBaseUrl() + "/ciam-api/test/rsa")
                                .children(
                                        httpHeaders()
                                                .header("Postman-Token", TestData.POSTMAN_TOKEN_RSA)
                                                .header("Accept-Encoding", TestData.HEADER_ACCEPT_ENCODING)
                                                .header("User-Agent", TestData.HEADER_USER_AGENT)
                                                .header("Accept", TestData.HEADER_ACCEPT)),

                        // POST request for registration init
                        httpSampler("https://" + config.getBaseUrl() + "/ciam-api/v1/registration/init")
                                .post(gson.toJson(initRequest), ContentType.APPLICATION_JSON)
                                .children(
                                        httpHeaders()
                                                .header("Postman-Token", TestData.POSTMAN_TOKEN_INIT)
                                                .header("Accept-Encoding", TestData.HEADER_ACCEPT_ENCODING)
                                                .header("User-Agent", TestData.HEADER_USER_AGENT)
                                                .header("Accept", TestData.HEADER_ACCEPT),
                                        jsonExtractor("sessionId", "data.sessionId")
                                                .defaultValue("NOT_FOUND"),
                                        jsonExtractor("nonce", "next_action.nonce")
                                                .defaultValue("NOT_FOUND")),

                        // POST request for registration profile
                        httpSampler("https://" + config.getBaseUrl() + "/ciam-api/v1/registration/profile")
                                .post(gson.toJson(profileRequest), ContentType.APPLICATION_JSON)
                                .children(
                                        httpHeaders()
                                                .header("X-Session-Id", "${sessionId}")
                                                .header("X-Nonce", "${nonce}")
                                                .header("Postman-Token", TestData.POSTMAN_TOKEN_PROFILE)
                                                .header("Accept-Encoding", TestData.HEADER_ACCEPT_ENCODING)
                                                .header("User-Agent", TestData.HEADER_USER_AGENT)
                                                .header("Accept", TestData.HEADER_ACCEPT),
                                        jsonExtractor("nonce", "next_action.nonce")
                                                .defaultValue("NOT_FOUND")),

                        // POST request for password encryption
                        httpSampler("https://" + config.getBaseUrl() + "/ciam-api/test/registration/encrypt/password")
                                .post(gson.toJson(passwordRequest), ContentType.APPLICATION_JSON)
                                .children(
                                        httpHeaders()
                                                .header("Postman-Token", TestData.POSTMAN_TOKEN_ENCRYPT)
                                                .header("Accept-Encoding", TestData.HEADER_ACCEPT_ENCODING)
                                                .header("User-Agent", TestData.HEADER_USER_AGENT)
                                                .header("Accept", TestData.HEADER_ACCEPT),
                                        jsonExtractor("encryptPwd", "encryptPwd")
                                                .defaultValue("NOT_FOUND"))),

                // Result Collectors
                jtlWriter("results.jtl"),
                htmlReporter("html-report")).run();

        System.out.println("CIAM API Registration Test completed!");
    }
}
