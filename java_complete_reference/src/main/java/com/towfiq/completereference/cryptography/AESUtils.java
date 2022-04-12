package com.towfiq.completereference.cryptography;

import javax.crypto.*;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Base64;

public class AESUtils {

    private static final String AES_ALGO = "AES/GCM/NoPadding";
    public static final int AES_KEY_SIZE = 256;
    public static final int GCM_IV_LENGTH = 12;
    public static final int GCM_TAG_LENGTH = 12;
    public static final String IV_FOR_KEY_GEN = "KeyGenIV";
    public static final String RANDOM_NUMBER_SALT = "6794e58db8c533bd85d4e19cdbc1b196XX";
    public static final String RANDOM_NUMBER_PASSWORD = "b2ddbcdd89c82f0a4813e8a728eeb9c8YY";




    public static SecretKey getKeyFromPassword(String password, String salt)
            throws NoSuchAlgorithmException, InvalidKeySpecException {

        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), 65536, 256);
        SecretKey secretKeyFromPBKDF2 = factory.generateSecret(spec);
        return new SecretKeySpec(secretKeyFromPBKDF2
                .getEncoded(), "AES");
    }

    public static GCMParameterSpec generateIv() {
        byte[] iv = new byte[GCM_IV_LENGTH];
        new SecureRandom().nextBytes(iv);
        return new GCMParameterSpec(GCM_TAG_LENGTH * 8, iv);
    }

    public static GCMParameterSpec generateIvFromHardCodedString() {
        byte[] ivBytes = Arrays.copyOfRange(IV_FOR_KEY_GEN.getBytes(), 0, GCM_IV_LENGTH);

        return new GCMParameterSpec(GCM_TAG_LENGTH * 8, ivBytes);
    }




    public static SecretKey generateSecret(String password, String[] saltItems) throws NoSuchAlgorithmException, InvalidKeySpecException {
        String salt = Arrays.stream(saltItems).reduce("", (item1, item2) -> item1 + item2);
        SecretKey key = AESUtils.getKeyFromPassword(password,salt);
        return key;
    }


    public static String getEncryptedPrivateKey( String privateKey, String password, String[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, IOException {
//        System.out.println("Private key: " + privateKey);
        String plainText = privateKey;
        SecretKey key = generateSecret(password, salt);
        GCMParameterSpec ivParameterSpec = AESUtils.generateIv();
        Cipher cipher = Cipher.getInstance(AES_ALGO);
        cipher.init(Cipher.ENCRYPT_MODE, key, ivParameterSpec);

        byte[] cipherText2 = cipher.doFinal(plainText.getBytes());

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream( );
        outputStream.write( ivParameterSpec.getIV() );
        outputStream.write( cipherText2 );

        byte[] outstreamBytes = outputStream.toByteArray();
        String cipherText =  (Base64.getEncoder()
                .encodeToString(outstreamBytes));


        return cipherText;
    }

    public static String getEncryptedWalletSecretKey( String walletSecretPlainText, String password, String[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, IOException {
//        System.out.println("walletSecretPlainText: " + walletSecretPlainText);
        String plainText = walletSecretPlainText;
        SecretKey key = generateSecret(password, salt);
        GCMParameterSpec ivParameterSpec = AESUtils.generateIvFromHardCodedString();
        Cipher cipher = Cipher.getInstance(AES_ALGO);
        cipher.init(Cipher.ENCRYPT_MODE, key, ivParameterSpec);

        byte[] cipherText2 = cipher.doFinal(plainText.getBytes());

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream( );
        outputStream.write( ivParameterSpec.getIV() );
        outputStream.write( cipherText2 );

        byte[] outstreamBytes = outputStream.toByteArray();
        String cipherText =  (Base64.getEncoder()
                .encodeToString(outstreamBytes));


        return cipherText;
    }


    public static String getDecryptedPrivateKey( String privateKey, String password, String[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {

        SecretKey key = generateSecret(password, salt);
        byte[] decodedKey = Base64.getDecoder().decode(privateKey.getBytes());

        byte[] ivBytes = Arrays.copyOfRange(decodedKey, 0, GCM_IV_LENGTH);
        byte[] cipherBytes = Arrays.copyOfRange(decodedKey, GCM_IV_LENGTH, decodedKey.length );
       GCMParameterSpec ivParameterSpec = new GCMParameterSpec(GCM_TAG_LENGTH * 8, ivBytes);

        Cipher cipher = Cipher.getInstance(AES_ALGO);

        cipher.init(Cipher.DECRYPT_MODE, key, ivParameterSpec);
        String decryptedKey =new String(cipher.doFinal(cipherBytes));
//        System.out.println("decrypted key: " + decryptedKey);

        return decryptedKey;
    }


}
