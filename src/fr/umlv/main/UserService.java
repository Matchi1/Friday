package fr.umlv.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserService {
    @Autowired
    private UserRepo userRepository;

    public void addUser(Event user) {
        userRepository.save(user);
    }

    public void removeUser(Event user) {
        userRepository.delete(user);
    }

    public List<Event> getAllUsers() {
        return userRepository.findAll();
    }
}
