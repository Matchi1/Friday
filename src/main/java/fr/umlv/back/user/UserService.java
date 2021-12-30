package fr.umlv.back.user;

import fr.umlv.back.crypt.CryptPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * This class provides services for user table access such as adding a new
 * user, removing a user and updating a user.
 */
@Service
public class UserService {
    @Autowired
    private UserRepo userRepository;

	/**
	 * Add a user to the DB using the specified user details
	 *
	 * @param username the specified username
	 * @param password the specified password
	 *
	 * @throws NullPointerException if one of the specified details is null
	 * @return 200 (ok) http response with the added user
	 */
    public ResponseEntity<UserResponseDTO> addUser(String username, String password) {
		Objects.requireNonNull(username);
        var crypt = new CryptPassword();
        var user = new User(username, crypt.hash(password));
        var createdUser =  userRepository.save(user);
        return ResponseEntity
                .created(URI.create("/users/save/" + createdUser.getUsername()))
                .body(new UserResponseDTO(createdUser.getUsername()));
    }

	/**
	 * Remove a user from the DB with the specified username
	 *
	 * @param username the specified username
	 *
	 * @throws NullPointerException if the specified username is null
	 * @return 200 (ok) http response if the user was found
	 * 		   404 (not found) http response otherwise
	 */
    public ResponseEntity<UserResponseDTO> removeUser(String username) {
        Objects.requireNonNull(username);
        if (userRepository.findById(username).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        userRepository.deleteById(username);
        return ResponseEntity.ok().build();
    }

	/**
	 * Update the password of a user with the specified username
	 *
	 * @param username the specified username
	 * @param newPassword the new password
	 *
	 * @throws NullPointerException if the specified details is null
	 *
	 * @return 201 (created) http response if the password of the user has been changed,
     *         404 (not found) http response otherwise
	 */
    public ResponseEntity<UserResponseDTO> updatePassword(String username , String newPassword) {
        var crypt = new CryptPassword();
        var user = userRepository.findById(username);
        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        user.get().setPassword(crypt.hash(newPassword));
        var updatedUser = userRepository.save(user.get());
        return ResponseEntity
                .created(URI.create("/user/update/" + updatedUser.getUsername()))
                .body(new UserResponseDTO(updatedUser.getUsername()));
    }

    /**
     * Retrieve a user with the specified username
     *
     * @param username the specified username
     *
     * @return 200 (ok) http response containing the retrieved user,
     * 		   404 (not found) http response otherwise
     */
    public ResponseEntity<UserResponseDTO> existById(String username) {
        Objects.requireNonNull(username);
        if (userRepository.existsById(username)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Get user container according to the specified credentials
     *
     * @param credentials the specified credentials
     * @return the user container
     */
    private Optional<User> getUser(UserSaveDTO credentials) {
        Objects.requireNonNull(credentials);
        var encryptor = new CryptPassword();
        var hashedPassword = encryptor.hash(credentials.password());
        var example = Example.of(new User(credentials.username(), hashedPassword), ExampleMatcher.matchingAll());
        return userRepository.findOne(example);
    }

    /**
     * Verify if a user input a correct credentials for the application login
     *
     * @param credentials the input credentials
     *
     * @throws NullPointerException if the input credentials is null
     * @return 200 (ok) http response containing a user info if the credentials are correct,
     * 		   404 (not found) http response otherwise
     */
    public ResponseEntity<UserResponseDTO> correctCredentials(UserSaveDTO credentials) {
        var userContainer = getUser(credentials);
        if (userContainer.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var user = userContainer.get();
        return ResponseEntity.ok(new UserResponseDTO(user.getUsername()));
    }
}
