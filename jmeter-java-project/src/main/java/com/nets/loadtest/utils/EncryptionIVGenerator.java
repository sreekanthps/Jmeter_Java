package com.nets.loadtest.utils;

import java.security.SecureRandom;

/**
 * Utility class for generating random Initialization Vectors (IV) for
 * encryption
 */
public class EncryptionIVGenerator {

    private static final String ALLOWED_CHARACTERS = "qwertyuiopasdfghjklzxcvbnm";
    private static final String ALLOWED_NUMBERS = "1234567890";

    /**
     * Generates a random 16-character IV using numbers
     * 
     * @return Random IV string
     */
    public static String generateRandomIV() {
        return getRandomString(16, ALLOWED_NUMBERS);
    }

    /**
     * Generates a random string of specified length using allowed characters
     * 
     * @param sizeOfRandomString Length of the random string
     * @param allowedCharacters  Characters to use for generation
     * @return Random string
     */
    private static String getRandomString(int sizeOfRandomString, String allowedCharacters) {
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(sizeOfRandomString);

        for (int i = 0; i < sizeOfRandomString; i++) {
            sb.append(allowedCharacters.charAt(random.nextInt(allowedCharacters.length())));
        }

        return sb.toString();
    }
}
