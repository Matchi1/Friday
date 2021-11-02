package fr.umlv.main;

import org.apache.catalina.User;
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
}
