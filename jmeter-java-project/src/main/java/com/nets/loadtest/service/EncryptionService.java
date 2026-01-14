package com.nets.loadtest.service;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.MGF1ParameterSpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.logging.Logger;

/**
 * Service class for handling AES key generation and RSA encryption operations.
 * Provides methods to generate AES keys, load RSA public keys, and encrypt AES
 * keys with RSA.
 */
public class EncryptionService {

    private static final Logger logger = Logger.getLogger(EncryptionService.class.getName());

    // Store AES key as ByteArray for later use
    private byte[] aesKeyBytes;

    /**
     * Generates a 256-bit AES symmetric key.
     *
     * @return byte array containing the encoded AES key
     * @throws Exception if key generation fails
     */
    public byte[] generateAesKey() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(256);
        return keyGen.generateKey().getEncoded();
    }

    /**
     * Loads an RSA public key from a Base64-encoded PEM string.
     *
     * @param pem Base64-encoded public key string
     * @return PublicKey object
     * @throws Exception if key loading fails
     */
    public PublicKey loadPublicKey(String pem) throws Exception {
        byte[] encoded = Base64.getDecoder().decode(pem);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(encoded);
        return KeyFactory.getInstance("RSA").generatePublic(keySpec);
    }

    /**
     * Encrypts an AES key using RSA public key encryption with OAEP padding.
     * Encrypts the raw AES key bytes directly (standard approach).
     *
     * @param aesKey    AES key as byte array
     * @param publicKey RSA public key
     * @return Base64-encoded encrypted AES key
     * @throws Exception if encryption fails
     */
    public String encryptAesKeyWithRsa(byte[] aesKey, PublicKey publicKey) throws Exception {
        // Convert AES key to Base64 string (matching Android implementation)
        String aesKeyStr = Base64.getEncoder().encodeToString(aesKey);

        // Initialize cipher with RSA/ECB/OAEPWithSHA-256AndMGF1Padding
        Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");

        // Configure OAEP parameters
        OAEPParameterSpec oaepParams = new OAEPParameterSpec(
                "SHA-256",
                "MGF1",
                MGF1ParameterSpec.SHA256,
                PSource.PSpecified.DEFAULT);

        // Initialize cipher in encrypt mode
        cipher.init(Cipher.ENCRYPT_MODE, publicKey, oaepParams, new SecureRandom());

        // Encrypt the Base64-encoded AES key string
        byte[] encryptedBytes = cipher.doFinal(aesKeyStr.getBytes(StandardCharsets.UTF_8));

        // Return Base64-encoded encrypted bytes
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    /**
     * Main method that orchestrates the encryption process:
     * 1. Loads RSA public key from PEM
     * 2. Generates AES symmetric key
     * 3. Stores AES key for later use
     * 4. Encrypts AES key with RSA public key
     *
     * @param publicKeyPem Base64-encoded RSA public key in PEM format
     * @return Base64-encoded encrypted AES key, or null if publicKeyPem is
     *         empty/null
     * @throws Exception if any step in the encryption process fails
     */
    public String encryptAndSendAesKey(String publicKeyPem) throws Exception {
        try {
            if (publicKeyPem == null || publicKeyPem.isEmpty()) {
                logger.warning("encryptAndSendAesKey: publicKeyPem is empty");
                return null;
            }

            // 1. Load RSA public key from PEM
            PublicKey publicKey = loadPublicKey(publicKeyPem);

            // 2. Generate AES symmetric key
            byte[] aesKey = generateAesKey();

            // Store AES key as ByteArray in a class-level variable for later use
            this.aesKeyBytes = aesKey;

            // 3. Encrypt AES key with RSA public key
            return encryptAesKeyWithRsa(aesKey, publicKey);
        } catch (Exception e) {
            logger.severe("encryptAndSendAesKey exception: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Gets the stored AES key bytes.
     *
     * @return byte array containing the AES key, or null if not yet generated
     */
    public byte[] getAesKeyBytes() {
        return aesKeyBytes;
    }

    /**
     * Sets the AES key bytes.
     *
     * @param aesKeyBytes byte array containing the AES key
     */
    public void setAesKeyBytes(byte[] aesKeyBytes) {
        this.aesKeyBytes = aesKeyBytes;
    }

    /**
     * Encrypts plaintext using AES-GCM with the provided key and nonce.
     * Returns Base64-encoded result containing nonce + ciphertext + auth tag.
     *
     * @param aesKeyBase64 Base64-encoded AES key
     * @param plaintext    Plain text password to encrypt
     * @param nonceBase64  Base64-encoded nonce (URL-safe)
     * @return Base64-encoded encrypted data (nonce + ciphertext + tag)
     * @throws Exception if encryption fails
     */
    public String encryptPasswordWithAes(String aesKeyBase64, String plaintext, String nonceBase64) throws Exception {
        // Decode the Base64-encoded AES key
        byte[] key = Base64.getDecoder().decode(aesKeyBase64);

        // Decode the URL-safe Base64-encoded nonce
        byte[] nonce = Base64.getUrlDecoder().decode(nonceBase64);

        // Encrypt the password
        return encryptToBase64(key, plaintext, nonce);
    }

    /**
     * Encrypts plaintext to Base64 using AES-GCM.
     * Combines nonce + ciphertext + auth tag and returns as Base64.
     *
     * @param key       AES key bytes
     * @param plaintext Plain text to encrypt
     * @param nonce     Nonce bytes
     * @return Base64-encoded (nonce + ciphertext + tag)
     * @throws Exception if encryption fails
     */
    private String encryptToBase64(byte[] key, String plaintext, byte[] nonce) throws Exception {
        byte[] pt = plaintext.getBytes(StandardCharsets.UTF_8);

        // Encrypt using AES-GCM
        byte[] ct = encrypt(key, nonce, pt);

        // Combine nonce + ciphertext+tag
        byte[] combined = new byte[nonce.length + ct.length];
        System.arraycopy(nonce, 0, combined, 0, nonce.length);
        System.arraycopy(ct, 0, combined, nonce.length, ct.length);

        return Base64.getEncoder().encodeToString(combined);
    }

    /**
     * Encrypts plaintext using AES/GCM/NoPadding with 128-bit auth tag.
     *
     * @param key       AES key bytes
     * @param nonce     Nonce/IV bytes
     * @param plaintext Plaintext bytes to encrypt
     * @return Ciphertext + auth tag
     * @throws Exception if encryption fails
     */
    private byte[] encrypt(byte[] key, byte[] nonce, byte[] plaintext) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");

        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");

        // 128-bit auth tag = recommended
        GCMParameterSpec gcmSpec = new GCMParameterSpec(128, nonce);

        cipher.init(Cipher.ENCRYPT_MODE, keySpec, gcmSpec);

        return cipher.doFinal(plaintext);
    }
}
