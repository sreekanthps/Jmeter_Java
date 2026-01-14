package com.nets.loadtest.service;

import com.nets.loadtest.utils.EncryptionIVGenerator;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * Service for AES encryption with RSA key encryption
 */
public class AESEncryptionService {

    private String encryption;
    private String x_encryptionKey;
    private String encryptionIV;

    /**
     * Encrypts data using AES-GCM and encrypts the AES key using RSA
     * 
     * @param bodyBytes The data to encrypt
     * @param key       The RSA public key (PEM format, optional)
     * @param mod       The RSA modulus (hex format, optional)
     * @param exp       The RSA exponent (hex format, optional)
     * @return Base64 encoded encrypted data
     */
    public String encryptAES(byte[] bodyBytes, String key, String mod, String exp) {
        System.out.println("[DEBUG] encryptAES called with key: "
                + (key != null ? "PROVIDED (length=" + key.length() + ")" : "NULL"));
        System.out.println("[DEBUG] mod: " + (mod != null ? "PROVIDED" : "NULL") + ", exp: "
                + (exp != null ? "PROVIDED" : "NULL"));

        String randomNonce = EncryptionIVGenerator.generateRandomIV(); // Must be random for each request

        try {
            // 1. Create Key at Client App - key and IV must be generated randomly
            KeyGenerator generator = KeyGenerator.getInstance("AES");
            generator.init(256); // The AES key size in number of bits
            SecretKey secKey = generator.generateKey();

            // 2. Encrypt data with generated AES Key and random nonce
            // For AES-GCM, use GCMParameterSpec with 128-bit authentication tag
            GCMParameterSpec gcmSpec = new GCMParameterSpec(128, randomNonce.getBytes());
            Cipher aesCipher = Cipher.getInstance("AES/GCM/NOPADDING");
            aesCipher.init(Cipher.ENCRYPT_MODE, secKey, gcmSpec);
            byte[] byteCipherText = aesCipher.doFinal(bodyBytes);

            // 3. Encode base64 encrypted data
            String base64EncodedEncryptedData = Base64.getEncoder().encodeToString(byteCipherText);

            // 4. Encode base64 AES Key
            String base64EncodedAESKey = Base64.getEncoder().encodeToString(secKey.getEncoded());

            // 5. Encrypt key with RSA PublicKey
            PublicKey pubKey = generatePublicKey(key, mod, exp);

            Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, pubKey);
            byte[] encryptedKey = cipher.doFinal(base64EncodedAESKey.getBytes());

            // 6. Encode base64 encrypted Key
            String base64EncodedEncryptedKey = Base64.getEncoder().encodeToString(encryptedKey);

            // Set encryption metadata
            this.encryption = "AES_GCM_NoPadding-RSA_OAEPWithSHA256";
            this.x_encryptionKey = base64EncodedEncryptedKey;
            this.encryptionIV = randomNonce;

            return base64EncodedEncryptedData;

        } catch (Exception ex) {
            System.err.println("[ERROR] Encryption failed: " + ex.getClass().getName() + " - " + ex.getMessage());
            ex.printStackTrace();

            clearEncryptionInfo();
            // Fallback: return base64 encoded original data
            return Base64.getEncoder().encodeToString(bodyBytes);
        }
    }

    /**
     * Generates RSA public key from either PEM format or modulus/exponent
     * 
     * @param key PEM format public key (optional)
     * @param mod Modulus in hex format (optional)
     * @param exp Exponent in hex format (optional)
     * @return PublicKey object
     * @throws Exception if key generation fails
     */
    private PublicKey generatePublicKey(String key, String mod, String exp) throws Exception {
        KeyFactory fact = KeyFactory.getInstance("RSA");

        if (key != null && !key.isEmpty()) {
            // Use PEM format key
            String publicKeyString = key.replace("-----BEGIN PUBLIC KEY-----", "")
                    .replace("-----END PUBLIC KEY-----", "")
                    .replaceAll("\\s", "");
            byte[] keyBytes = Base64.getDecoder().decode(publicKeyString);
            X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
            return fact.generatePublic(spec);
        } else {
            // Use modulus and exponent
            BigInteger modBig = new BigInteger(mod, 16);
            BigInteger expBig = new BigInteger(exp, 16);
            RSAPublicKeySpec spec = new RSAPublicKeySpec(modBig, expBig);
            return fact.generatePublic(spec);
        }
    }

    /**
     * Clears encryption metadata
     */
    private void clearEncryptionInfo() {
        this.encryption = null;
        this.x_encryptionKey = null;
        this.encryptionIV = null;
    }

    // Getters for encryption metadata
    public String getEncryption() {
        return encryption;
    }

    public String getX_encryptionKey() {
        return x_encryptionKey;
    }

    public String getEncryptionIV() {
        return encryptionIV;
    }
}
