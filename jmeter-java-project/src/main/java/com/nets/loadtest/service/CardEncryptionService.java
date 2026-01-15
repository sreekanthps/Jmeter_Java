package com.nets.loadtest.service;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.MGF1ParameterSpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource;

import com.nets.loadtest.config.ApiConstants;

public class CardEncryptionService {

    private static final int AES_KEY_SIZE = 256; // bits
    private static final int GCM_TAG_LENGTH = 128; // bits
    private static final int IV_SIZE = 12; // Standard GCM IV is 12 bytes

    /**
     * Generates a secure random numeric IV of specified length (as per Swift logic)
     */
    public static String generateSecureRandomIV(int length) {
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    public static EncryptionResult encrypt(String publicKeyBase64, String payload) throws Exception {
        // 1. Generate AES Session Key
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(AES_KEY_SIZE);
        SecretKey aesKey = keyGen.generateKey();

        // 2. Encrypt AES Key with RSA (OAEP with SHA-256)
        byte[] decodedKey = Base64.getDecoder().decode(publicKeyBase64);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(decodedKey);
        PublicKey publicKey = KeyFactory.getInstance("RSA").generatePublic(spec);

        Cipher rsaCipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
        OAEPParameterSpec oaepSpec = new OAEPParameterSpec("SHA-256", "MGF1", MGF1ParameterSpec.SHA256,
                PSource.PSpecified.DEFAULT);
        rsaCipher.init(Cipher.ENCRYPT_MODE, publicKey, oaepSpec);

        // Note: Swift code converted key to Base64 before RSA encrypting
        String base64AesKey = Base64.getEncoder().encodeToString(aesKey.getEncoded());
        byte[] encryptedAesKey = rsaCipher.doFinal(base64AesKey.getBytes("UTF-8"));

        // 3. Encrypt Payload with AES/GCM
        String ivString = generateSecureRandomIV(IV_SIZE); // Mimicking the Swift numeric IV logic
        byte[] ivBytes = ivString.getBytes("UTF-8");

        Cipher aesCipher = Cipher.getInstance("AES/GCM/NoPadding");
        GCMParameterSpec gcmSpec = new GCMParameterSpec(GCM_TAG_LENGTH, ivBytes);
        aesCipher.init(Cipher.ENCRYPT_MODE, aesKey, gcmSpec);

        byte[] encryptedBody = aesCipher.doFinal(payload.getBytes("UTF-8"));

        return new EncryptionResult(
                Base64.getEncoder().encodeToString(encryptedAesKey),
                Base64.getEncoder().encodeToString(encryptedBody),
                ivString);
    }

    public static EncryptionResult encryptCard(String publicKeyBase64, byte[] bodyBytes) throws Exception {
        // 1. Generate AES Session Key
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(AES_KEY_SIZE);
        SecretKey aesKey = keyGen.generateKey();

        // 2. Encrypt AES Key with RSA (OAEP with SHA-256)
        byte[] decodedKey = Base64.getDecoder().decode(publicKeyBase64);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(decodedKey);
        PublicKey publicKey = KeyFactory.getInstance("RSA").generatePublic(spec);

        Cipher rsaCipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
        OAEPParameterSpec oaepSpec = new OAEPParameterSpec("SHA-256", "MGF1", MGF1ParameterSpec.SHA256,
                PSource.PSpecified.DEFAULT);
        rsaCipher.init(Cipher.ENCRYPT_MODE, publicKey, oaepSpec);

        // Note: Swift code converted key to Base64 before RSA encrypting
        String base64AesKey = Base64.getEncoder().encodeToString(aesKey.getEncoded());
        byte[] encryptedAesKey = rsaCipher.doFinal(base64AesKey.getBytes("UTF-8"));

        // 3. Encrypt Payload with AES/GCM
        String ivString = generateSecureRandomIV(16); // Mimicking the Swift numeric IV logic
        byte[] ivBytes = ivString.getBytes("UTF-8");

        Cipher aesCipher = Cipher.getInstance("AES/GCM/NoPadding");
        GCMParameterSpec gcmSpec = new GCMParameterSpec(GCM_TAG_LENGTH, ivBytes);
        aesCipher.init(Cipher.ENCRYPT_MODE, aesKey, gcmSpec);

        byte[] encryptedBody = aesCipher.doFinal(bodyBytes);

        return new EncryptionResult(
                Base64.getEncoder().encodeToString(encryptedAesKey),
                Base64.getEncoder().encodeToString(encryptedBody),
                ivString);
    }

    /**
     * Decrypts a payload that was encrypted using the encrypt() method
     * 
     * @param privateKeyBase64    Base64-encoded RSA private key (PKCS8 format)
     * @param encryptedKeyBase64  Base64-encoded encrypted AES key
     * @param encryptedBodyBase64 Base64-encoded encrypted payload
     * @param ivString            The IV string used during encryption
     * @return DecryptionResult containing the decrypted payload
     */
    public static DecryptionResult decrypt(String privateKeyBase64, String encryptedKeyBase64,
            String encryptedBodyBase64, String ivString) throws Exception {
        // 1. Decrypt AES Key with RSA (OAEP with SHA-256)
        byte[] decodedPrivateKey = Base64.getDecoder().decode(privateKeyBase64);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(decodedPrivateKey);
        PrivateKey privateKey = KeyFactory.getInstance("RSA").generatePrivate(spec);

        Cipher rsaCipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
        OAEPParameterSpec oaepSpec = new OAEPParameterSpec("SHA-256", "MGF1", MGF1ParameterSpec.SHA256,
                PSource.PSpecified.DEFAULT);
        rsaCipher.init(Cipher.DECRYPT_MODE, privateKey, oaepSpec);

        byte[] encryptedAesKey = Base64.getDecoder().decode(encryptedKeyBase64);
        byte[] decryptedAesKeyBytes = rsaCipher.doFinal(encryptedAesKey);

        // The AES key was Base64-encoded before RSA encryption, so decode it
        String base64AesKey = new String(decryptedAesKeyBytes, "UTF-8");
        byte[] aesKeyBytes = Base64.getDecoder().decode(base64AesKey);
        SecretKey aesKey = new javax.crypto.spec.SecretKeySpec(aesKeyBytes, "AES");

        // 2. Decrypt Payload with AES/GCM
        byte[] ivBytes = ivString.getBytes("UTF-8");
        byte[] encryptedBody = Base64.getDecoder().decode(encryptedBodyBase64);

        Cipher aesCipher = Cipher.getInstance("AES/GCM/NoPadding");
        GCMParameterSpec gcmSpec = new GCMParameterSpec(GCM_TAG_LENGTH, ivBytes);
        aesCipher.init(Cipher.DECRYPT_MODE, aesKey, gcmSpec);

        byte[] decryptedPayload = aesCipher.doFinal(encryptedBody);

        return new DecryptionResult(new String(decryptedPayload, "UTF-8"));
    }

    /**
     * Decrypts a payload that was encrypted using the encryptCard() method
     * 
     * @param privateKeyBase64    Base64-encoded RSA private key (PKCS8 format)
     * @param encryptedKeyBase64  Base64-encoded encrypted AES key
     * @param encryptedBodyBase64 Base64-encoded encrypted payload
     * @param ivString            The IV string used during encryption
     * @return DecryptionResult containing the decrypted payload as bytes
     */
    public static DecryptionResult decryptCard(String privateKeyBase64, String encryptedKeyBase64,
            String encryptedBodyBase64, String ivString) throws Exception {
        // 1. Decrypt AES Key with RSA (OAEP with SHA-256)
        byte[] decodedPrivateKey = Base64.getDecoder().decode(privateKeyBase64);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(decodedPrivateKey);
        PrivateKey privateKey = KeyFactory.getInstance("RSA").generatePrivate(spec);

        Cipher rsaCipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
        OAEPParameterSpec oaepSpec = new OAEPParameterSpec("SHA-256", "MGF1", MGF1ParameterSpec.SHA256,
                PSource.PSpecified.DEFAULT);
        rsaCipher.init(Cipher.DECRYPT_MODE, privateKey, oaepSpec);

        byte[] encryptedAesKey = Base64.getDecoder().decode(encryptedKeyBase64);
        byte[] decryptedAesKeyBytes = rsaCipher.doFinal(encryptedAesKey);

        // The AES key was Base64-encoded before RSA encryption, so decode it
        String base64AesKey = new String(decryptedAesKeyBytes, "UTF-8");
        byte[] aesKeyBytes = Base64.getDecoder().decode(base64AesKey);
        SecretKey aesKey = new javax.crypto.spec.SecretKeySpec(aesKeyBytes, "AES");

        // 2. Decrypt Payload with AES/GCM
        byte[] ivBytes = ivString.getBytes("UTF-8");
        byte[] encryptedBody = Base64.getDecoder().decode(encryptedBodyBase64);

        Cipher aesCipher = Cipher.getInstance("AES/GCM/NoPadding");
        GCMParameterSpec gcmSpec = new GCMParameterSpec(GCM_TAG_LENGTH, ivBytes);
        aesCipher.init(Cipher.DECRYPT_MODE, aesKey, gcmSpec);

        byte[] decryptedPayload = aesCipher.doFinal(encryptedBody);

        return new DecryptionResult(decryptedPayload);
    }

    // Helper class to hold result
    public static class EncryptionResult {
        @com.fasterxml.jackson.annotation.JsonProperty("x_encryption_key")
        public final String encryptedKey;

        @com.fasterxml.jackson.annotation.JsonProperty("encrypt_body")
        public final String encryptedBody;

        @com.fasterxml.jackson.annotation.JsonProperty("x_encryption_iv")
        public final String iv;

        @com.fasterxml.jackson.annotation.JsonProperty("x_encryption")
        private final String encryption = ApiConstants.ENCRYPTION_ALGORITHM;

        public EncryptionResult(String k, String b, String i) {
            this.encryptedKey = k;
            this.encryptedBody = b;
            this.iv = i;
        }

        public String getEncryptedKey() {
            return encryptedKey;
        }

        public String getEncryptedBody() {
            return encryptedBody;
        }

        public String getIv() {
            return iv;
        }

        public String getEncryption() {
            return encryption;
        }
    }

    // Helper class to hold decryption result
    public static class DecryptionResult {
        private final String decryptedPayload;
        private final byte[] decryptedBytes;

        // Constructor for String payload
        public DecryptionResult(String payload) {
            this.decryptedPayload = payload;
            this.decryptedBytes = null;
        }

        // Constructor for byte array payload
        public DecryptionResult(byte[] bytes) {
            this.decryptedBytes = bytes;
            this.decryptedPayload = null;
        }

        public String getDecryptedPayload() {
            return decryptedPayload;
        }

        public byte[] getDecryptedBytes() {
            return decryptedBytes;
        }

        public boolean isStringPayload() {
            return decryptedPayload != null;
        }

        public boolean isBytesPayload() {
            return decryptedBytes != null;
        }
    }
}