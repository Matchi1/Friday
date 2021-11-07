package fr.umlv.main;

import fr.umlv.main.event.*;
import fr.umlv.main.user.UserCredentialDTO;
import fr.umlv.main.user.UserResponseDTO;
import fr.umlv.main.user.UserSaveDTO;
import fr.umlv.main.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
public class CalendarController {
    @Autowired
    private EventService eventService;
    @Autowired
    private UserService userService;

    /*
    @RequestMapping("/hello")
    public String sayHello() {
        return "Hello";
    }

    @GetMapping("/getAll")
    public List<Event> getEvent() {
        return eventService.getEvents();
    }
    */
    @PostMapping("/event/save")
    public ResponseEntity<EventResponseDTO> addEvent(@RequestBody EventSaveDTO event) {
        Objects.requireNonNull(event);
        return eventService.addEvent(event.date(), event.heure(), event.info());
    }

    @DeleteMapping("/event/delete")
    public ResponseEntity<?> removeEvent(@RequestBody EventCredentialDTO event) {
        Objects.requireNonNull(event);
        return eventService.removeEvent(event.id(), event.userId());
    }


    /*
    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }
     */

    @PostMapping("/users/save")
    public ResponseEntity<UserResponseDTO> putUser(@RequestBody UserSaveDTO user) {
        Objects.requireNonNull(user);
        return userService.addUser(user.username(), user.password());
    }

    @DeleteMapping("/users/delete")
    public ResponseEntity<?> removeUser(@RequestBody UserCredentialDTO user) {
        Objects.requireNonNull(user);
        return userService.removeUser(user.id(), user.password());
    }
}
