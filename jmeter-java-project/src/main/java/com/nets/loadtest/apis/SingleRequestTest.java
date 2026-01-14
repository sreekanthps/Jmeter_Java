package com.nets.loadtest.apis;

import com.nets.loadtest.config.ApiConstants;
import com.nets.loadtest.config.TestConfig;
import com.nets.loadtest.data.TestData;

import static us.abstracta.jmeter.javadsl.JmeterDsl.*;

/**
 * Simple test for a single API request.
 * Use this to test individual endpoints quickly.
 */
public class SingleRequestTest {

    public static void main(String[] args) throws Exception {
        // Use SIT configuration
        TestConfig config = TestConfig.sitConfig();

        System.out.println("Starting Single Request Test...");
        System.out.println("Base URL: " + config.getBaseUrl());

        testPlan(
                threadGroup(1, 1,
                        // Example: Test the RSA endpoint
                        httpSampler(config.getBaseUrl() + ApiConstants.CIAM_TEST_RSA)
                                .children(
                                        httpHeaders()
                                                .header("Accept-Encoding", TestData.HEADER_ACCEPT_ENCODING)
                                                .header("User-Agent", TestData.HEADER_USER_AGENT)
                                                .header("Accept", TestData.HEADER_ACCEPT),
                                        // Extract the public key from response
                                        jsonExtractor("publicKey", "publicKey"))),
                // Result collectors
                jtlWriter("single-request-results.jtl"),
                htmlReporter("single-request-report")).run();

        System.out.println("Single Request Test completed!");
        System.out.println("Check single-request-report folder for results");
    }
}
