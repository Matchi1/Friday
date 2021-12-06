package fr.umlv.main.back.event;

import fr.umlv.main.back.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import javax.persistence.EntityNotFoundException;
import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class EventService {
    @Autowired
    private EventRepo eventRepository;

    private boolean containsEvent(EventSaveDTO eventDetails) {
        var exempleMatcher = ExampleMatcher.matchingAll()
                .withMatcher("id", ExampleMatcher.GenericPropertyMatchers.ignoreCase())
                .withMatcher("title", ExampleMatcher.GenericPropertyMatchers.ignoreCase())
                .withMatcher("information", ExampleMatcher.GenericPropertyMatchers.ignoreCase())
                .withMatcher("user", ExampleMatcher.GenericPropertyMatchers.ignoreCase());
        var example = Example.of(Event.createEvent(eventDetails), exempleMatcher);
        var event = eventRepository.findOne(example);
        return event.isPresent();
    }

    public ResponseEntity<EventResponseDTO> addEvent(EventSaveDTO eventDetails) {
        Objects.requireNonNull(eventDetails);
        if(containsEvent(eventDetails)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        var event = Event.createEvent(eventDetails);
        final var addedEvent = eventRepository.save(event);
        return ResponseEntity.status(HttpStatus.CREATED)
                .location(URI.create("/event/get/" + addedEvent.getId()))
                .body(new EventResponseDTO(event));
    }

    public ResponseEntity<EventResponseDTO> updateEvent(UUID id, EventSaveDTO eventSave) {
        var event = eventRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Event not found for this Id :: " + id));
        event.setDateStart(eventSave.dateStart());
        event.setDateEnd(eventSave.dateEnd());
        event.setInfo(eventSave.info());
        final var updatedEvent = eventRepository.save(event);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .location(URI.create("/event/get/" + updatedEvent.getId()))
                .body(new EventResponseDTO(updatedEvent));
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
                .map(EventResponseDTO::new)
                .toList();
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<EventResponseDTO> getEventById(UUID id) {
        var event = eventRepository.findById(id);
        if(event.isPresent()) {
            return ResponseEntity.ok(new EventResponseDTO(event.get()));
        }
        return ResponseEntity.notFound().build();
    }
}
