package fr.umlv.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    public ResponseEntity<?> removeUser(UUID id, String pswd) {
        var user = userRepository.findById(id);
        if (user.isEmpty()) return ResponseEntity.notFound().build();
        userRepository.delete(user.get());
        return ResponseEntity.ok().build();
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
