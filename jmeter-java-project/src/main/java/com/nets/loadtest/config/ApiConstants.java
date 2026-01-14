package com.nets.loadtest.config;

/**
 * API URL constants for different environments and services.
 */
public class ApiConstants {

    // CIAM API Base URLs
    public static final String CIAM_SIT_BASE_URL = "https://cauth-sit.nets.com.sg";
    public static final String CIAM_UAT_BASE_URL = "https://cauth-uat.nets.com.sg";

    // UVMC API Base URLs
    public static final String UVMC_TEST_BASE_URL = "https://test-prepaid.nets.com.sg/uvmcapi/rest/";
    public static final String UVMC_SIT_BASE_URL = "https://sit-npp.nets.com.sg/uvmcapi/rest/";

    // CIAM API Endpoints
    public static final String CIAM_TEST_RSA = "/ciam-api/test/rsa";
    public static final String CIAM_REGISTRATION_INIT = "/ciam-api/v1/registration/init";
    public static final String CIAM_REGISTRATION_PROFILE = "/ciam-api/v1/registration/profile";
    public static final String CIAM_TEST_ENCRYPT_PASSWORD = "/ciam-api/test/registration/encrypt/password";

    // UVMC API Endpoints
    public static final String UVMC_AUTH_USER_AUTO_SIGNIN = "auth/userAutoSignIn";
    public static final String UVMC_AUTH_LOGIN_ACK = "auth/login/ack";
    public static final String UVMC_AUTH_LOGOUT = "auth/logout";
    public static final String UVMC_GET_IP_ADDR = "getIpAddr";
    public static final String UVMC_UPDATE_DEVICE = "updateDevice";
    public static final String UVMC_USER_ATTRIBUTE = "user/attribute";
    public static final String UVMC_USER_CURRENT = "user/current";
    public static final String UVMC_USER_WALLET_REGISTER = "user/wallet/register";
    public static final String UVMC_NPC_CARDS = "npc/cards";
    public static final String UVMC_USER_PREFERENCE = "user/preference";
    public static final String UVMC_PAYMENT_METHOD_LIST = "paymentmethod/list";
    public static final String UVMC_MAINTENANCE = "maintenance";
    public static final String UVMC_APP_VERSION_ANDROID = "appversion/android";
    public static final String UVMC_ANNOUNCEMENT_BANNER = "announcement/banner";
    public static final String UVMC_MARKETING_BANNER = "marketing/banner";
    public static final String UVMC_NOTIFICATION_LAST = "notification/last";
    public static final String UVMC_NOTIFICATION_SMARTPROMPT_LIST = "notification/smartprompt/list";
    public static final String UVMC_NOTIFICATION_TOKEN = "notification/token";
    public static final String UVMC_KEY_RSA = "key/rsa?key=true";
    public static final String PROCESS_CARD = "api/keys/processcard";
    public static final String UVMC_PAYMENT_METHOD_NFP_ATU = "paymentmethod/nfp/atu";
    // Example:
    // public static final String UVMC_REGISTER = "register";
    // public static final String UVMC_VERIFY = "verify";

    private ApiConstants() {
        // Utility class, prevent instantiation
    }
}
