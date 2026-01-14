package com.nets.loadtest.data;

/**
 * Test data constants for CIAM API registration tests.
 */
public class TestData {

    // Postman Tokens
    public static final String POSTMAN_TOKEN_RSA = "5af24e9d-a6ce-460e-9d20-6f567aff00a9";
    public static final String POSTMAN_TOKEN_INIT = "583271f7-fad9-48a2-929f-cfbf8189bb55";
    public static final String POSTMAN_TOKEN_PROFILE = "7b06e766-bb09-48f4-92b8-35a578a4810b";
    public static final String POSTMAN_TOKEN_ENCRYPT = "1ca23591-91fc-4631-8ad8-05ffbc4650e1";

    // Sample Encrypted AES Key
    public static final String SAMPLE_ENCRYPTED_KEY = "1XamHvwa7VA2dkSDVAoW0ZZ+8kTw0ycDi7JuCKfaCnXoVnEqPEYSumd+4AX4crtD8fwBx+dXpp+vJyGReU729Y0PqOQKkcTdlVCJ+V7Ht9sSxqRH+1/EIdgPzy8KmOKAvDmnuXAN+b1FL4j0IeAvXFZxb39FYFJHcct4lMHBxErn8EhK27yz+AU2h0rl2ybp1ECnU8e+AnEDdMbocf07hMxlvJ1X4J7l5XQZ3bP2Dcvxq1kS5k8ERjDPDCcXHbfgIl3Fb/43nLk9EfYs8yVnP3+dGbOvqrBStp6NwEsByCcbTrWCAeYTd78j2E5mKgqn/1bTGlnagpjDAruSiZJqcA==";

    // Sample Singpass Authorization Code
    public static final String SAMPLE_SINGPASS_CODE = "code=myinfo-com-lXzbh0MTEwLmC34GxqMHAXB3xSGWpMyqONKKtAyQ";

    // Test Password
    public static final String TEST_PASSWORD = "Nets@1234";

    // Sample Session ID (for password encryption test)
    public static final String SAMPLE_SESSION_ID = "49e212f5131642ed9341c27dc5221c1c";

    // Registration Context
    public static final String CONTEXT_SINGPASS = "SINGPASS";

    // App Version
    public static final String APP_VERSION = "1.0";

    // HTTP Headers
    public static final String HEADER_ACCEPT_ENCODING = "gzip, deflate, br";
    public static final String HEADER_USER_AGENT = "PostmanRuntime/7.24.0";
    public static final String MOBILE_OS = "Android";
    public static final String HEADER_ACCEPT = "*/*";

    // Device and Notification Constants
    public static final String DEVICE_TYPE = "ANDROID";
    public static final String FCM_TOKEN = "ej7FU4pgRQm3Y7JJ1XJxeD:APA91bF1wjJHElB1RWMKy5Xk8Cjdaz4Vt-iaPl6PW1UdPYyKk03JXo9-xdFGJVLyM0rahkH827PbuTS5atvSCnG76Pen03jT8rRI9dm_5bPjI4_ajaXubD4";
    public static final String IMEI_NUMBER = "NA";
    public static final String LATITUDE = "NA";
    public static final String LONGITUDE = "NA";
    public static final String MOBILE_DEVICE_ID = "7647f0a42003d92ef6c91d92917b27e8c6bab94beddf6428";
    public static final String MOBILE_MODEL = "SM-S928B";
    public static final String MOBILE_OS_VERSION = "16";

    private TestData() {
        // Utility class, prevent instantiation
    }
}
