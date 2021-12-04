package fr.umlv.main.crypt;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

@Component
@Scope("singleton")
public class CryptPassword {

    private final byte[] salt;
    private final MessageDigest md;

    public CryptPassword() {
        String encryptionKey = "CeSTfUNLeJAVAHa5";
        salt = encryptionKey.getBytes(StandardCharsets.UTF_8);
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError("Algorithm or padding error");
        }
    }

    public String hash(String msg) {
        Objects.requireNonNull(msg);
        md.update(salt);
        var hash = md.digest(msg.getBytes(StandardCharsets.UTF_8));
        return new String(hash);
    }
}
