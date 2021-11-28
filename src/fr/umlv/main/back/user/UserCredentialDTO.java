package fr.umlv.main.back.user;

import fr.umlv.main.back.crypt.CryptPassword;

import javax.crypto.IllegalBlockSizeException;
import java.security.InvalidKeyException;
import java.util.Objects;
import java.util.UUID;

public record UserCredentialDTO(UUID id, byte[] password) {
    public UserCredentialDTO {
        Objects.requireNonNull(id);
        Objects.requireNonNull(password);
    }

    public UserCredentialDTO(UUID id, String password) throws InvalidKeyException, IllegalBlockSizeException {
        this(id, CryptPassword.createCrypter().cryptedPassword(password));
    }
}
