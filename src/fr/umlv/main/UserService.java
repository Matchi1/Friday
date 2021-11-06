package fr.umlv.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepository;

    public ResponseEntity<UserResponse> addUser(String username, String pswd) {
        var user = new User(username, pswd);
        var createdUser =  userRepository.save(user);
        return ResponseEntity
                .created(URI.create("/users/save/" + createdUser.getId()))
                .body(new UserResponse(createdUser.getId(), createdUser.getUsername()));
    }

    public void removeUser(User user) {
        userRepository.delete(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public ResponseEntity<UserResponse> getUserById(UUID id) {
        var user = userRepository.findById(id);
        var response = new UserResponse(id, user.get().getUsername());
        return ResponseEntity.ok(response);
    }
}
