package fr.umlv.main.back.user;

import fr.umlv.main.crypt.CryptPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.crypto.IllegalBlockSizeException;
import java.net.URI;
import java.security.InvalidKeyException;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepository;


    public ResponseEntity<UserResponseDTO> addUser(String username, String password) {
        var crypt = new CryptPassword();
        var user = new User(username, crypt.hash(password));
        var createdUser =  userRepository.save(user);
        return ResponseEntity
                .created(URI.create("/users/save/" + createdUser.getId()))
                .body(new UserResponseDTO(createdUser.getId(), createdUser.getUsername()));
    }

    public ResponseEntity<?> removeUser(UUID id, String password) {
        var user = userRepository.findById(id);
        if (user.isEmpty()) return ResponseEntity.notFound().build();
        userRepository.delete(user.get());
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<UserResponseDTO> updatePassword(UUID id , String newPassword) {
        var crypt = new CryptPassword();
        var user = userRepository.findById(id);
        if (user.isEmpty()) return ResponseEntity.notFound().build();
        user.get().setPassword(crypt.hash(newPassword));
        userRepository.save(user.get());
        return ResponseEntity
                .created(URI.create("/users/update/" + user.get().getId()))
                .body(new UserResponseDTO(user.get().getId(), user.get().getUsername()));
    }


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
