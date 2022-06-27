package fr.umlv.back.crypt;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

/**
 * This class provides a password encryptor using SHA-512 instance
 */
@Component
@Scope("singleton")
public class CryptPassword {

    private final byte[] salt;
    private final MessageDigest md;
    private final String encryptionKey = "CeSTfUNLeJAVAHa5";
    private final String encryptionType = "SHA-512";

	/**
	 * Contructs a encrypter with default encryption key and that uses
	 * SHA-256 encryption instance
	 *
	 * @throws NoSuchAlgorithmException if the algorithme was not found
	 */
    public CryptPassword() {
        salt = encryptionKey.getBytes(StandardCharsets.UTF_8);
        try {
            md = MessageDigest.getInstance(encryptionType);
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError("Algorithm or padding error");
        }
    }
    
	/**
	 * Encrypt the specified message
	 *
	 * @param msg the specified message
	 *
	 * @throws NullPointerException if the specified message is null
	 * @return the encrypted message
	 */
    public String hash(String message) {
        Objects.requireNonNull(message);
        md.update(salt);
        var hash = md.digest(message.getBytes(StandardCharsets.UTF_8));
        return new String(hash);
    }
}
