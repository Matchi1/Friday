package fr.umlv.main.back.user;

import fr.umlv.main.back.crypt.CryptPassword;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * This class provides services for user table access such as adding a new
 * user, removing an user and updating an user.
 */
@Service
public class UserService {
    @Autowired
    private UserRepo userRepository;

	/**
	 * Add an user to the DB using the specified user details
	 *
	 * @param username the specified username
	 * @param password the specified password
	 *
	 * @throws NullPointerException if one of the specified details is null
	 * @return an entity response containing the added user information 
	 */
    public ResponseEntity<UserResponseDTO> addUser(String username, String password) {
		Objects.requireNonNull(username);
        var crypt = new CryptPassword();
        var user = new User(username, crypt.hash(password));
        var createdUser =  userRepository.save(user);
        return ResponseEntity
                .created(URI.create("/users/save/" + createdUser.getId()))
                .body(new UserResponseDTO(createdUser.getId(), createdUser.getUsername()));
    }

	/**
	 * Remove an user from the DB with the specified id
	 *
	 * @param id the specified id
	 *
	 * @throws NullPointerException if the specified id is null
	 * @return an 200 http response if the user was found
	 * 		   else 404
	 */
    public ResponseEntity<UserResponseDTO> removeUser(UUID id) {
        Objects.requireNonNull(id);
        if (userRepository.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        userRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

	/**
	 * Update the password of a user with the specified id
	 *
	 * @param id the specified id
	 * @param newPassword the new password
	 *
	 * @throws NullPointerException if the specified details is null
	 *
	 * @return 201 (created) http response if the password of the user has been
	 * changed else 404 (not found)
	 */
    public ResponseEntity<UserResponseDTO> updatePassword(UUID id , String newPassword) {
        var crypt = new CryptPassword();
        var user = userRepository.findById(id);
        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        user.get().setPassword(crypt.hash(newPassword));
        var updatedUser = userRepository.save(user.get());
        return ResponseEntity
                .created(URI.create("/user/update/" + updatedUser.getId()))
                .body(new UserResponseDTO(updatedUser.getId(), updatedUser.getUsername()));
    }

	/**
	 * Retrieve an user with the specified id
	 *
	 * @param id the specified id
	 *
	 * @return a 200 http response containing the retrieved user
	 * 		   else a 404 http response
	 */
    public ResponseEntity<UserResponseDTO> getUserById(UUID id) {
        Objects.requireNonNull(id);
        var userContainer = userRepository.findById(id);
        if (userContainer.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var user = userContainer.get();
        return ResponseEntity.ok(new UserResponseDTO(user.getId(), user.getUsername()));
    }

    /**
     * Retrieve a user with the specified username
     *
     * @param username the specified username
     *
     * @return a 200 http response containing the retrieved user
     * 		   else a 404 http response
     */
    public ResponseEntity<UserResponseDTO> getUserByUsername(String username) {
        Objects.requireNonNull(username);
        var exampleMatcher = ExampleMatcher.matching()
                .withIgnorePaths("id", "events", "password");
        var example = Example.of(new User(username, ""), exampleMatcher);
        var userContainer = userRepository.findOne(example);
        if (userContainer.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var user = userContainer.get();
        return ResponseEntity.ok(new UserResponseDTO(user.getId(), user.getUsername()));
    }

    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        var users = userRepository.findAll().stream()
                .map(user -> new UserResponseDTO(user.getId(), user.getUsername()))
                .toList();
        return ResponseEntity.ok(users);
    }
}
