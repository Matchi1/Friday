package fr.umlv.main.crypt;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class CryptPassword {

    public static byte[] cryptedPassword (String password) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException, NoSuchAlgorithmException {
        String encryptionKey = "CeSTfUNLeJAVAHa5";
        byte[] encryptionKeyBytes = encryptionKey.getBytes(); // Advanced Encryptiopn standard
        SecretKey secretKey = new SecretKeySpec(encryptionKeyBytes, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return cipher.doFinal(password.getBytes());
    }

}
