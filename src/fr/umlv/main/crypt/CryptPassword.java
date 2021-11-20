package fr.umlv.main.crypt;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

public class CryptPassword {
    private static final String encryptionKey = "CeSTfUNLeJAVAHa5";
    private static final byte[] encryptionKeyBytes = encryptionKey.getBytes(); // Advanced Encryptiopn standard
    private static final SecretKey secretKey = new SecretKeySpec(encryptionKeyBytes, "AES");

    public static byte[] cryptedPassword (String password) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException, NoSuchAlgorithmException {
        Objects.requireNonNull(password);
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return cipher.doFinal(password.getBytes());
    }


    public static String decryptedPassword (byte[] cryptedpassword) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Objects.requireNonNull(cryptedpassword);
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        var decryptedPassword = cipher.doFinal(cryptedpassword);
        return new String(decryptedPassword);
    }

}
