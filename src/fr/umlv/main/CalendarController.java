package fr.umlv.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    @PostMapping("/calendar")
    public ResponseEntity<UserResponse> saveUser(@Validated UserSave input) {
        var newUser = userService.addUser(input.username(), input.password());
        return new ResponseEntity<>(new UserResponse(newUser.getBody().id(), newUser.getBody().username()), HttpStatus.OK);
    }
    */

    @RequestMapping("/hello")
    public String sayHello() {
        return "Hello";
    }

    @GetMapping("/getAll")
    public List<Event> getEvent() {
        return eventService.getEvents();
    }

    @PostMapping("/event/save")
    public ResponseEntity<EventResponseDTO> addEvent(@RequestBody EventSaveDTO event) {
        Objects.requireNonNull(event);
        return eventService.addEvent(event.date(), event.heure(), event.info());
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
