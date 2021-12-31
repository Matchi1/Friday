package fr.umlv.back.user;

import fr.umlv.back.crypt.CryptPassword;
import fr.umlv.back.event.EventSaveDTO;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * This class provides services for user table access such as adding a new
 * user, removing a user and updating a user.
 */
@Service
public class UserService {
    private final UserRepo userRepository;

    public UserService(UserRepo userRepository) {
        this.userRepository = userRepository;
    }

    private boolean userExists(String username) {
        var exampleMatcher = ExampleMatcher.matchingAll()
                .withIgnorePaths("password", "events");
        var example = Example.of(new User(username, ""), exampleMatcher);
        var user = userRepository.findOne(example);
        return user.isPresent();
    }

	/**
	 * Add a user to the DB using the specified user details
	 *
	 * @param username the specified username
	 * @param password the specified password
	 *
	 * @throws NullPointerException if one of the specified details is null
	 * @return 200 (ok) http response with the added user
	 */
    @Async
    public CompletableFuture<ResponseEntity<UserResponseDTO>> addUser(String username, String password) {
		Objects.requireNonNull(username);
        if (userExists(username)) {
            return CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.CONFLICT).build());
        }
        var user = new User(username, new CryptPassword().hash(password));
        var createdUser =  userRepository.save(user);
        return CompletableFuture.completedFuture(ResponseEntity
                .created(URI.create("/users/save/" + createdUser.getUsername()))
                .body(new UserResponseDTO(createdUser.getUsername())));
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
    @Async
    public CompletableFuture<ResponseEntity<UserResponseDTO>> removeUser(String username) {
        Objects.requireNonNull(username);
        if (userRepository.findById(username).isEmpty()) {
            return CompletableFuture.completedFuture(ResponseEntity.notFound().build());
        }
        userRepository.deleteById(username);
        return CompletableFuture.completedFuture(ResponseEntity.ok().build());
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
    @Async
    public CompletableFuture<ResponseEntity<UserResponseDTO>> updatePassword(UserSaveDTO details) {
        var crypt = new CryptPassword();
        var user = userRepository.findById(details.username());
        if (user.isEmpty()) {
            return CompletableFuture.completedFuture(ResponseEntity.notFound().build());
        }
        user.get().setPassword(crypt.hash(details.password()));
        var updatedUser = userRepository.save(user.get());
        return CompletableFuture.completedFuture(ResponseEntity
                .created(URI.create("/user/update/" + updatedUser.getUsername()))
                .body(new UserResponseDTO(updatedUser.getUsername())));
    }

    /**
     * Retrieve a user with the specified username
     *
     * @param username the specified username
     *
     * @return 200 (ok) http response containing the retrieved user,
     * 		   404 (not found) http response otherwise
     */
    @Async
    public CompletableFuture<ResponseEntity<UserResponseDTO>> existById(String username) {
        Objects.requireNonNull(username);
        if (userRepository.existsById(username)) {
            return CompletableFuture.completedFuture(ResponseEntity.ok().build());
        }
        return CompletableFuture.completedFuture(ResponseEntity.notFound().build());
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
    @Async
    public CompletableFuture<ResponseEntity<UserResponseDTO>> correctCredentials(UserSaveDTO credentials) {
        var userContainer = getUser(credentials);
        if (userContainer.isEmpty()) {
            return CompletableFuture.completedFuture(ResponseEntity.notFound().build());
        }
        var user = userContainer.get();
        return CompletableFuture.completedFuture(ResponseEntity.ok(new UserResponseDTO(user.getUsername())));
    }
}
