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

/**
 * This class provides services for event table access such as adding a new
 * event, removing an event and updating an event.
 */
@Service
public class EventService {
    @Autowired
    private EventRepo eventRepository;

	/**
	 * Verify if an event is present in the database using the specified event
	 * details as an example.
	 *
	 * @param eventDetails the specified event details
	 *
	 * @return true if an event is respecting the details
	 * 		   else false
	 */
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

	/**
	 * Add an event to the DB using the specified event details
	 *
	 * @param eventDetails the specified event details
	 *
	 * @throws NullPointerException if the specified details is null
	 * @return an entity response containing the added event information 
	 */
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

	/**
	 * Update an event with specified id from the DB using the specified event 
	 * details
	 *
	 * @param id the specified id
	 * @param eventDetails the specified event details
	 *
	 * @throws NullPointerException if the specified details is null
	 * @throws EntityNotFoundException if the event with the specified id was not found 
	 * @return an entity response containing the updated event information 
	 */
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

	/**
	 * Remove an event from the DB with the specified id
	 *
	 * @param id the specified id
	 *
	 * @throws NullPointerException if the specified details is null
	 * @return an 200 http response
	 */
    public ResponseEntity<EventResponseDTO> removeEvent(UUID id, User userId) {
        var event = eventRepository.findById(id);
        if (event.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        eventRepository.delete(event.get());
        return ResponseEntity.ok().build();
    }

	/**
	 * Remove an event from the DB with the specified id
	 *
	 * @param id the specified id
	 *
	 * @throws NullPointerException if the specified details is null
	 * @return an 200 http response if the event was found
	 * 		   else an 404 http response
	 */
    public ResponseEntity<EventResponseDTO> removeEventById(UUID id) {
        var event = eventRepository.findById(id);
        if (event.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        eventRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

	/**
	 * Retrieve all the events from the DB
	 *
	 * @return a 200 http response containing a list of all the events
	 */
    public ResponseEntity<List<EventResponseDTO>> getEvents() {
        var events = eventRepository.findAll();
        var response = events.stream()
                .map(EventResponseDTO::new)
                .toList();
        return ResponseEntity.ok(response);
    }

	/**
	 * Retrieve an event with the specified id
	 *
	 * @param id the specified id
	 *
	 * @return a 200 http response containing the retrieved event
	 * 		   else a 404 http response
	 */
    public ResponseEntity<EventResponseDTO> getEventById(UUID id) {
        var event = eventRepository.findById(id);
        if(event.isPresent()) {
            return ResponseEntity.ok(new EventResponseDTO(event.get()));
        }
        return ResponseEntity.notFound().build();
    }
}
