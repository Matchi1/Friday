package fr.umlv.main.back.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

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
	 * @return 200 (ok) http response with the added user
	 */
    @PostMapping("/user/save")
    public ResponseEntity<UserResponseDTO> addUser(@RequestBody UserSaveDTO user) {
        Objects.requireNonNull(user);
        return userService.addUser(user.username(), user.password());
    }

	/**
	 * Remove a user from the database according the specified id
	 *
	 * @param id the specified id
	 * 
	 * @throws NullPointerException if the specified id is null
	 * @return 200 (ok) http response if the user was found
	 * 	 	   404 (not found) http response otherwise
	 */
    @DeleteMapping("/user/delete/{id}")
    public ResponseEntity<UserResponseDTO> removeUser(@PathVariable String id) {
        return userService.removeUser(id);
    }

	/**
	 * Retrieve a user from the database with the specified id
	 *
	 * @param id the specified id
	 *
	 * @throws NullPointerException if the specified id is null
	 * @return 200 (ok) http response containing the retrieved user,
	 *         404 (not found) http response otherwise
	 */
    @GetMapping("/user/exist/{id}")
    public ResponseEntity<UserResponseDTO> existId(@PathVariable String id) {
		Objects.requireNonNull(id);
        return userService.existById(id);
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
	@PostMapping("/user/exist")
	public ResponseEntity<UserResponseDTO> correctCredentials(@RequestBody UserSaveDTO credentials) {
		Objects.requireNonNull(credentials);
		return userService.correctCredentials(credentials);
	}

	/**
	 * Retrieve all users from the database
	 *
	 * @return 404 (not found) http response if no users was found,
	 *   	   200 (ok) http response with all users otherwise
	 */
    @GetMapping("/user/all")
    public ResponseEntity<List<UserResponseDTO>> getAll() {
        return userService.getAllUsers();
    }
}
