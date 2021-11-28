package fr.umlv.main.back.user;

import fr.umlv.main.back.crypt.CryptPassword;

import javax.crypto.IllegalBlockSizeException;
import java.security.InvalidKeyException;
import java.util.Objects;

public record UserSaveDTO(String username, byte[] password) {
    public UserSaveDTO {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);
    }

    public UserSaveDTO(String username, String password) throws InvalidKeyException, IllegalBlockSizeException {
        this(username, CryptPassword.createCrypter().cryptedPassword(password));
    }
}
