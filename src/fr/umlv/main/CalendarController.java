package fr.umlv.main;

import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.sql.ResultSet;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
public class CalendarController {
    @Autowired
    private CalendarService calendarService;
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
        return calendarService.getEvents();
    }

    @PostMapping("/putOne")
    public String addEvent(@RequestBody Event event) {
        System.out.println(event.toString());
        calendarService.addEvent(event);
        return "ok";
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/users/putOne")
    public ResponseEntity<User> putUser(@RequestBody User user) {
        Objects.requireNonNull(user);
        userService.addUser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/users/removeOne/{user}")
    public ResponseEntity<User> removeUser(@PathVariable User user) {
        Objects.requireNonNull(user);
        userService.removeUser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
