package com.nets.loadtest.config;

/**
 * Configuration class for test environment settings.
 */
public class TestConfig {
    private final String baseUrl;
    private final int tpsOnboarding;
    private final String netsAppSecret;
    private final String appVersion;
    private final String codeVersion;
    private final int notificationLimit;
    private final double targetThroughput; // Requests per minute

    public TestConfig(String baseUrl, int tpsOnboarding, String netsAppSecret, String appVersion, String codeVersion,
            int notificationLimit, double targetThroughput) {
        this.baseUrl = baseUrl;
        this.tpsOnboarding = tpsOnboarding;
        this.netsAppSecret = netsAppSecret;
        this.appVersion = appVersion;
        this.codeVersion = codeVersion;
        this.notificationLimit = notificationLimit;
        this.targetThroughput = targetThroughput;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public int getTpsOnboarding() {
        return tpsOnboarding;
    }

    public String getNetsAppSecret() {
        return netsAppSecret;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public String getCodeVersion() {
        return codeVersion;
    }

    public int getNotificationLimit() {
        return notificationLimit;
    }

    public double getTargetThroughput() {
        return targetThroughput;
    }

    /**
     * Creates a configuration for SIT environment.
     */
    public static TestConfig sitConfig() {
        return new TestConfig(
                ApiConstants.CIAM_SIT_BASE_URL,
                2,
                "a2ugZ9xYfAZlRCzqGpeaxuJv5cCylAk8",
                "3.1.0",
                "01-04-2025",
                100,
                60.0); // 60 requests/minute = 1 TPS
    }

    /**
     * Creates a configuration for UAT environment.
     */
    public static TestConfig uatConfig() {
        return new TestConfig(
                ApiConstants.CIAM_UAT_BASE_URL,
                2,
                "ecd861eb-5539-4936-8806-c2e1c04a34ac",
                "3.1.0",
                "01-04-2025",
                100,
                60.0); // 60 requests/minute = 1 TPS
    }

    /**
     * Creates a configuration for UVMC Test environment.
     */
    public static TestConfig uvmcTestConfig() {
        return new TestConfig(
                ApiConstants.UVMC_TEST_BASE_URL,
                2,
                "",
                "3.1.0",
                "01-04-2025",
                100,
                60.0 // 60 requests/minute = 1 TPS
        );
    }

    /**
     * Creates a configuration for UVMC SIT environment.
     */
    public static TestConfig uvmcSitConfig() {
        return new TestConfig(
                ApiConstants.UVMC_SIT_BASE_URL,
                2,
                "",
                "3.1.0",
                "01-04-2025",
                100,
                60.0 // 60 requests/minute = 1 TPS
        );
    }
}
