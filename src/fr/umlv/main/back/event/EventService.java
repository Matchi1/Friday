package fr.umlv.main.back.event;

import fr.umlv.main.back.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class EventService {
    @Autowired
    private EventRepo eventRepository;

    public ResponseEntity<EventResponseDTO> addEvent(EventSaveDTO eventDetails) {
        Objects.requireNonNull(eventDetails);
        var event = Event.createEvent(eventDetails);
        eventRepository.save(event);
        return ResponseEntity.status(HttpStatus.CREATED).body(new EventResponseDTO(event.getId()));
    }

    public ResponseEntity<EventResponseDTO> updateEvent(UUID id, EventSaveDTO eventSave) {
        var event = eventRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Event not found for this Id :: " + id));
        event.setDateStart(eventSave.dateStart());
        event.setDateEnd(eventSave.dateEnd());
        event.setInfo(eventSave.info());
        final var updatedEvent = eventRepository.save(event);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new EventResponseDTO(updatedEvent.getId()));
    }

    public ResponseEntity<EventResponseDTO> removeEvent(UUID id, User userId) {
        var event = eventRepository.findById(id);
        if (event.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        eventRepository.delete(event.get());
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<EventResponseDTO> removeEventById(UUID id) {
        var event = eventRepository.findById(id);
        if (event.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        eventRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<List<EventResponseDTO>> getEvents() {
        var events = eventRepository.findAll();
        var response = events.stream()
                .map(event -> new EventResponseDTO(event.getId()))
                .toList();
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<EventResponseDTO> getEventById(UUID id) {
        var event = eventRepository.findById(id);
        if(event.isPresent()) {
            return ResponseEntity.ok(new EventResponseDTO(event.get().getId()));
        }
        return ResponseEntity.notFound().build();
    }
}
