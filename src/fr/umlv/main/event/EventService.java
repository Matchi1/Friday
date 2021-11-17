package fr.umlv.main.event;

import fr.umlv.main.user.User;
import org.hibernate.jpa.boot.spi.EntityManagerFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class EventService {
    @Autowired
    private EventRepo eventRepository;

    public ResponseEntity<EventResponseDTO> addEvent(EventSaveDTO eventSave) {
        var event = new Event(eventSave);
        eventRepository.save(event);
        return new ResponseEntity<>(new EventResponseDTO(event.getId()), HttpStatus.CREATED);
    }

    public ResponseEntity<EventSaveDTO> updateEvent(UUID id, EventSaveDTO eventSave) {
        var event = eventRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Event not found for this Id :: " + id));
        event.setDate(eventSave.date());
        event.setHeure(eventSave.heure());
        event.setInfo(eventSave.info());
        final var updatedEvent = eventRepository.save(event);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new EventSaveDTO(updatedEvent));
    }

    public ResponseEntity<EventSaveDTO> removeEvent(UUID id, User userId) {
        var event = eventRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Event not found for this Id :: " + id));
        eventRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new EventSaveDTO(event));
    }

    public ResponseEntity<List<EventSaveDTO>> getEvents() {
        var eventResponse = new ArrayList<EventSaveDTO>();
        eventRepository.findAll()
                .forEach(event -> eventResponse.add(new EventSaveDTO(event)));
        return ResponseEntity.status(HttpStatus.OK).body(eventResponse);
    }

    public ResponseEntity<EventSaveDTO> getEventById(UUID id) {
        Objects.requireNonNull(id);
        var event = eventRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Event not found for this Id :: " + id));
        return ResponseEntity.status(HttpStatus.OK).body(new EventSaveDTO(event));
    }
}
