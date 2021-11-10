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
import java.util.UUID;

@RestController
public class CalendarController {
    @Autowired
    private EventService eventService;
    @Autowired
    private UserService userService;

    @PostMapping("/event/add")
    public ResponseEntity<EventResponseDTO> addEvent(@RequestBody EventSaveDTO event) {
        Objects.requireNonNull(event);
        return eventService.addEvent(event.date(), event.heure(), event.info());
    }

    @DeleteMapping("/event/delete")
    public ResponseEntity<?> removeEvent(@RequestBody EventCredentialDTO event) {
        Objects.requireNonNull(event);
        return eventService.removeEvent(event.id(), event.userId());
    }

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

    @GetMapping("/events/getAll")
    public ResponseEntity<List<EventResponseDTO>> getEvents() {
        return eventService.getEvents();
    }

    @GetMapping("/events/get/{id}")
    public ResponseEntity<EventResponseDTO> getEvent(@PathVariable UUID id) {
        return eventService.getEventById(id);
    }
}
