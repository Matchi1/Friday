package fr.umlv.back.user;

import fr.umlv.back.crypt.CryptPassword;

import java.util.Objects;

/**
 * This class is responsible of containing the information transmitted during a
 * request mapping
 */
public record UserSaveDTO(String username, String password) {

    private static final CryptPassword encryptor = new CryptPassword();

    /**
	 * Compact constructor that verify the validity of the arguments
	 *
	 * @throws NullPointerException if one the specified argument is null
	 */
    public UserSaveDTO {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);
    }

    public User toUser() {
        return new User(username, encryptor.hash(password));
    }
}
