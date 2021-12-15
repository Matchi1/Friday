package fr.umlv.main.back.user;

import fr.umlv.main.back.crypt.CryptPassword;

import javax.crypto.IllegalBlockSizeException;
import java.security.InvalidKeyException;
import java.util.Objects;
import java.util.UUID;

/**
 * This class provides information about the id of the user
 */
public record UserCredentialDTO(UUID id, String password) {
    public UserCredentialDTO {
        Objects.requireNonNull(id);
        Objects.requireNonNull(password);
    }
}
