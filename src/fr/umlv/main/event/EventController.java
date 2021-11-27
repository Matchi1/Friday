package fr.umlv.main.event;

import fr.umlv.main.user.UserCredentialDTO;
import fr.umlv.main.user.UserResponseDTO;
import fr.umlv.main.user.UserSaveDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
public class EventController {
    @Autowired
    private EventService eventService;

    @PostMapping("/event/add")
    public ResponseEntity<EventResponseDTO> addEvent(@RequestBody EventSaveDTO event) {
        Objects.requireNonNull(event);
        return eventService.addEvent(event);
    }

    @DeleteMapping("/event/delete")
    public ResponseEntity<EventResponseDTO> removeEvent(@RequestBody EventCredentialDTO event) {
        Objects.requireNonNull(event);
        return eventService.removeEvent(event.id(), event.userId());
    }

    @PutMapping("/event/update/{id}")
    public ResponseEntity<EventResponseDTO> updateEvent(
            @PathVariable UUID id,
            @RequestBody EventSaveDTO event) {
        Objects.requireNonNull(event);
        Objects.requireNonNull(id);
        return eventService.updateEvent(id, event);
    }

    @GetMapping("/events/getAll")
    public ResponseEntity<List<EventResponseDTO>> getEvents() {
        return eventService.getEvents();
    }

    @GetMapping("/events/get/{id}")
    public ResponseEntity<EventResponseDTO> getEvent(@PathVariable UUID id) {
        Objects.requireNonNull(id);
        return eventService.getEventById(id);
    }
}
