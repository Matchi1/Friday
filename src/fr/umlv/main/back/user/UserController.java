package fr.umlv.main.back.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * This class provide basic features for communication between the database and
 * the web layout for the user table.
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;

	/**
	 * Add a new user into the database according to the specified user info
	 *
	 * @param user the specified user info
	 *
	 * @throws NullPointerException if the specified user info is null
	 * @return a response entity with the added user
	 */
    @PostMapping("/user/save")
    public ResponseEntity<UserResponseDTO> addUser(@RequestBody UserSaveDTO user) {
        Objects.requireNonNull(user);
        return userService.addUser(user.username(), user.password());
    }

	/**
	 * Remove an user from the database according the specified user info
	 *
	 * @param user the specified user info
	 * 
	 * @throws NullPointerException if the specified user info is null
	 * @return a response entity with the removed user
	 */
    @DeleteMapping("/user/delete")
    public ResponseEntity<UserResponseDTO> removeUser(@RequestBody UserCredentialDTO user) {
        Objects.requireNonNull(user);
        return userService.removeUser(user.id());
    }

	/**
	 * Retrieve an user from the database with the specified id
	 *
	 * @param id the specified id
	 *
	 * @throws NullPointerException if the specified id is null
	 * @return a response entity containing the corresponding user
	 */
    @GetMapping("/user/get/{id}")
    public ResponseEntity<UserResponseDTO> getAll(
            @PathVariable UUID id,
            @RequestBody UserCredentialDTO user) {
        Objects.requireNonNull(id);
        Objects.requireNonNull(user);
        return userService.getUserById(id);
    }

	/**
	 * Retrieve all the user from the database
	 *
	 * @return a response entity containing all the user
	 */
    @GetMapping("/user/all")
    public ResponseEntity<List<UserResponseDTO>> getAll(@RequestBody UserCredentialDTO user) {
        Objects.requireNonNull(user);
        return userService.getAllUsers();
    }
}
