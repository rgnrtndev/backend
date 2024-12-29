package com.rcc.dev.backend.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Base64;

@Component
public class AESCrypto {
    private static final String ALG = "AES/CBC/PKCS5PADDING";
    private static final String ALGSEC = "AES";
    private static final String ALGSECFACTORY = "PBKDF2WithHmacSHA256";

    private static final int IV_LENGTH = 16;
    private static final int KEY_LENGTH = 256;
    private static final int ITERATION_COUNT = 65536;

    //TODO: keep these secure and dont harcode in production after DB ready
    static final String SECRETKEY = "SECRET_KEY";
    static final String SALT = "SALT";

    @Autowired
    AESCrypto() {
    }

    public static String encrypt(String value, String key, String salt) throws Exception {
        if (value == null || value.isEmpty()) return "";
        try {
            SecureRandom secureRandom = new SecureRandom();
            byte[] iv = new byte[16];
            secureRandom.nextBytes(iv);
            IvParameterSpec ivspec = new IvParameterSpec(iv);
            SecretKeyFactory factory = SecretKeyFactory.getInstance(ALGSECFACTORY);
            KeySpec spec = new PBEKeySpec(key.toCharArray(), salt.getBytes(), ITERATION_COUNT, KEY_LENGTH);
            SecretKey tmp = factory.generateSecret(spec);

            SecretKeySpec skeySpec = new SecretKeySpec(tmp.getEncoded(), ALGSEC);

            Cipher cipher = Cipher.getInstance(ALG);
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivspec);

            byte[] cipherText = cipher.doFinal(value.getBytes());
            byte[] encryptedData = new byte[IV_LENGTH + cipherText.length];
            System.arraycopy(iv, 0, encryptedData, 0, iv.length);
            System.arraycopy(cipherText, 0, encryptedData, iv.length, cipherText.length);

            return Base64.getEncoder().encodeToString(encryptedData);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception(ex.getMessage());
        }
    }

    public static String encrypt(String value, String key) throws Exception {
        return encrypt(value, key, SALT);
    }

    public static String encrypt(String value) throws Exception {
        return encrypt(value, SECRETKEY, SALT);
    }

    public static String decrypt(String encrypted, String key, String salt) {
        if (encrypted == null || encrypted.isEmpty()) return "";
        try {
            byte[] encryptedData = Base64.getDecoder().decode(encrypted);
            byte[] iv = new byte[16];
            System.arraycopy(encryptedData, 0, iv, 0, iv.length);
            IvParameterSpec ivspec = new IvParameterSpec(iv);

            SecretKeyFactory factory = SecretKeyFactory.getInstance(ALGSECFACTORY);
            KeySpec spec = new PBEKeySpec(key.toCharArray(), salt.getBytes(), ITERATION_COUNT, KEY_LENGTH);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKeySpec = new SecretKeySpec(tmp.getEncoded(), ALGSEC);

            Cipher cipher = Cipher.getInstance(ALG);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivspec);

            byte[] cipherText = new byte[encryptedData.length - 16];
            System.arraycopy(encryptedData, 16, cipherText, 0, cipherText.length);

            byte[] original = cipher.doFinal(cipherText);

            return new String(original, "UTF-8");
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
            //throw new ProcessException(ApiConstant.DEFAULT_SOURCE_SYSTEM, ApiConstant.ENC_INVALID_CIPHER);
        }
    }

    public static String decrypt(String encrypted, String key){
        return decrypt(encrypted, key, SALT);
    }

    public static String decrypt(String encrypted){
        return decrypt(encrypted, SECRETKEY, SALT);
    }
}
