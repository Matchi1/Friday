package fr.umlv.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Objects;

@Controller
public class CalendarController {
    @Autowired
    private CalendarService calendarService;
    @Autowired
    private UserService userService;

    @PostMapping("/calendar")
    public ResponseEntity<UserResponse> saveUser(@Validated UserSave input) {
        Objects.requireNonNull(input);
        var newUser = userService.addUser(input.username(), input.password());
        return new ResponseEntity<>(new UserResponse(newUser.getBody().id(), newUser.getBody().username()), HttpStatus.OK);
        //return ResponseEntity.created(new UserResponse(newUser.getBody().id(), newUser.getBody().username()));

    }

    @GetMapping("/calendar")
    public List<Event> getId() {
        return calendarService.getEvents();
    }
}
