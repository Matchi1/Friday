package fr.umlv.main;

import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CalendarController {
    @Autowired
    private CalendarService calendarService;

    private final UserRepo userRepo;

    public CalendarController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @PostMapping("/user")
    public ResponseEntity<UserResponse> saveUser(@ResponseBody @Validated UserSave input) {
        var newUser = userRepo.saveUser(input.username(), input.password());
        return ResponseEntity.created(new UserResponse(newUser.getBody().id(), newUser.getBody().username()));

    }

    @GetMapping("/calendar")
    public List<CalendarEntity> getId() {
        return userRepo.findAll();
    }
}
