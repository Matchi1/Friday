package fr.umlv.main.back.user;

import fr.umlv.main.back.crypt.CryptPassword;

import javax.crypto.IllegalBlockSizeException;
import java.security.InvalidKeyException;
import java.util.Objects;

/**
 * This class is responsible of containing the information transmitted during a
 * request mapping
 */
public record UserSaveDTO(String username, String password) {

	/**
	 * Compact constructor that verify the validity of the arguments
	 *
	 * @throws NullPointerException if one the specified argument is null
	 */
    public UserSaveDTO {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);
    }
}
