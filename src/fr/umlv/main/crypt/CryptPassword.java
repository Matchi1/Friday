package fr.umlv.main.crypt;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

@Component
@Scope("singleton")
public class CryptPassword {
    private final String encryptionKey = "CeSTfUNLeJAVAHa5";
    private final byte[] encryptionKeyBytes = encryptionKey.getBytes(); // Advanced Encryptiopn standard
    SecretKey secretKey = new SecretKeySpec(encryptionKeyBytes, "AES");
    private final Cipher cipher;
    private final Cipher decipher;

    public CryptPassword() throws InvalidKeyException {
        try {
            Cipher instance = Cipher.getInstance("AES/ECB/PKCS5Padding");
            this.cipher = instance;
            this.decipher = instance;
            this.cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            this.decipher.init(Cipher.DECRYPT_MODE, secretKey);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            throw new AssertionError("Algorithm or padding error");
        }
    }

    public byte[] cryptedPassword (String password) throws IllegalBlockSizeException {
        Objects.requireNonNull(password);
        try {
            return cipher.doFinal(password.getBytes());
        } catch (BadPaddingException e) {
            throw new AssertionError("Padding error");
        }
    }


    public String decryptedPassword (byte[] cryptedpassword) throws IllegalBlockSizeException {
        Objects.requireNonNull(cryptedpassword);
        try {
            var decryptedPassword = decipher.doFinal(cryptedpassword);
            return new String(decryptedPassword);
        } catch (BadPaddingException e) {
            throw new AssertionError("Padding error");
        }
    }
}
