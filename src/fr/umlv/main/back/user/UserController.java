package fr.umlv.main.back.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/user/save")
    public ResponseEntity<UserResponseDTO> addUser(@RequestBody UserSaveDTO user) {
        Objects.requireNonNull(user);
        return userService.addUser(user.username(), user.password());
    }

    @DeleteMapping("/user/delete")
    public ResponseEntity<UserResponseDTO> removeUser(@RequestBody UserCredentialDTO user) {
        Objects.requireNonNull(user);
        return userService.removeUser(user.id());
    }

    @GetMapping("/user/get/{id}")
    public ResponseEntity<UserResponseDTO> getAll(
            @PathVariable UUID id,
            @RequestBody UserCredentialDTO user) {
        Objects.requireNonNull(id);
        Objects.requireNonNull(user);
        return userService.getUserById(id);
    }

    @GetMapping("/user/all")
    public ResponseEntity<List<UserResponseDTO>> getAll(@RequestBody UserCredentialDTO user) {
        Objects.requireNonNull(user);
        return userService.getAllUsers();
    }
}
