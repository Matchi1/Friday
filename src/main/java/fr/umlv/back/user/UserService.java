package fr.umlv.back.user;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

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

	/**
	 * Add a user to the DB using the specified user details
	 *
	 * @param details the specified user details
	 *
	 * @throws NullPointerException if one of the specified details is null
	 * @return 200 (ok) http response with the added user
	 */
    @Async
    public CompletableFuture<Optional<UserResponseDTO>> addUser(UserSaveDTO details) {
        if(userRepository.existsById(details.username())) {
            return CompletableFuture.completedFuture(Optional.empty());
        }
        var user = userRepository.save(details.toUser());
        var response = new UserResponseDTO(user.getUsername());
        return CompletableFuture.completedFuture(Optional.of(response));
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
    public CompletableFuture<Boolean> removeUser(String username) {
        if (userRepository.existsById(username)) {
            return CompletableFuture.completedFuture(Boolean.TRUE);
        }
        userRepository.deleteById(username);
        return CompletableFuture.completedFuture(Boolean.FALSE);
    }

	/**
	 * Update the password of a user with the specified username
	 *
	 * @throws NullPointerException if the specified details is null
	 *
	 * @return 201 (created) http response if the password of the user has been changed,
     *         404 (not found) http response otherwise
	 */
    @Async
    public CompletableFuture<Optional<UserResponseDTO>> updatePassword(UserSaveDTO details) {
        if (userRepository.existsById(details.username())) {
            return CompletableFuture.completedFuture(Optional.empty());
        }
        var updatedUser = userRepository.save(details.toUser());
        var response = new UserResponseDTO(updatedUser.getUsername());
        return CompletableFuture.completedFuture(Optional.of(response));
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
    public CompletableFuture<Boolean> existById(String username) {
        var status = Boolean.valueOf(userRepository.existsById(username));
        return CompletableFuture.completedFuture(status);
    }

    /**
     * Verify if a user input a correct credentials for the application login
     *
     * @param details the input credentials
     *
     * @throws NullPointerException if the input credentials is null
     * @return 200 (ok) http response containing a user info if the credentials are correct,
     * 		   404 (not found) http response otherwise
     */
    @Async
    public CompletableFuture<Optional<UserResponseDTO>> correctCredentials(UserSaveDTO details) {
        var userContainer = userRepository.findById(details.username());
        if(userContainer.isEmpty()) {
            return CompletableFuture.completedFuture(Optional.empty());
        }
        var user = userContainer.get();
        if (details.toUser().equals(user)) {
            return CompletableFuture.completedFuture(Optional.empty());
        }
        var response = new UserResponseDTO(user.getUsername());
        return CompletableFuture.completedFuture(Optional.of(response));
    }
}
