package fr.umlv.back.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

/**
 * This class provide basic features for communication between the database and
 * the web layout for the user table.
 */
@RestController
public class UserController {
    private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	/**
	 * Add a new user into the database according to the specified user info
	 *
	 * @param user the specified user info
	 *
	 * @throws NullPointerException if the specified user info is null
	 * @return 200 (ok) http response with the added user
	 */
    @PostMapping("/user/save")
    public ResponseEntity<UserResponseDTO> addUser(@RequestBody UserSaveDTO user)
			throws ExecutionException, InterruptedException {
        Objects.requireNonNull(user);
		var data = userService.addUser(user.username(), user.password()).get();
		if(data.isEmpty()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		var userResponse = data.get();
		var uri = URI.create("/users/save/" + userResponse.username());
		return ResponseEntity.created(uri).body(userResponse);
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
    public ResponseEntity<UserResponseDTO> removeUser(@PathVariable String id)
			throws ExecutionException, InterruptedException {
		var result = userService.removeUser(id);
		if(result.get()) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
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
    public ResponseEntity<UserResponseDTO> existId(@PathVariable String id)
			throws ExecutionException, InterruptedException {
		Objects.requireNonNull(id);
		var data = userService.existById(id).get();
		if(data) {
			return ResponseEntity.ok().build();
		}
        return ResponseEntity.notFound().build();
    }

	@PutMapping("/user/update")
	public ResponseEntity<UserResponseDTO> updatePassword(@RequestBody UserSaveDTO details)
			throws ExecutionException, InterruptedException {
		Objects.requireNonNull(details);
		var data = userService.updatePassword(details).get();
		if(data.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		var user = data.get();
		var uri = URI.create("/user/update/" + user.username());
		return ResponseEntity.created(uri).body(user);
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
	public ResponseEntity<UserResponseDTO> correctCredentials(@RequestBody UserSaveDTO credentials)
			throws ExecutionException, InterruptedException {
		Objects.requireNonNull(credentials);
		var data = userService.correctCredentials(credentials).get();
		if(data.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		var user = data.get();
		return ResponseEntity.ok(user);
	}
}
