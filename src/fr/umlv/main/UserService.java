package fr.umlv.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserService {
    @Autowired
    private UserRepo userRepository;

    public void addUser(User user) {
        userRepository.save(user);
    }

    public void removeUser(User user) {
        userRepository.delete(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
