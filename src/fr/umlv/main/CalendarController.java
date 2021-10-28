package fr.umlv.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
        Objects.requireNonNull(input);
        var newUser = userService.addUser(input.username(), input.password());
        return new ResponseEntity<>(new UserResponse(newUser.getBody().id(), newUser.getBody().username()), HttpStatus.OK);
        //return ResponseEntity.created(new UserResponse(newUser.getBody().id(), newUser.getBody().username()));
    }
    */
    
    @GetMapping("/calendar")
    public List<Event> getEvent() {
        return calendarService.getEvents();
    }

    @PostMapping("/calendar/put")
    public void addEvent(@RequestBody Event event) {
        System.out.println(event.toString());
        calendarService.addEvent(event);
    }

    /*
    @PutMapping("/put")
    public ResponseEntity<UserResponse> updatePassword(@Validated UserSave input) {
        var newPwd = userRepo.updatePassword(input.password());
        return new ResponseEntity<UserSave>();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<UserResponse> deleteUser(@Validated UserSave input) {
        userService.removeUser();
    }
    */
}

