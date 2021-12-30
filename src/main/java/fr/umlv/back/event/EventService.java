package fr.umlv.back.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import javax.persistence.EntityNotFoundException;
import java.net.URI;
import java.util.*;

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
        var exampleMatcher = ExampleMatcher.matchingAll()
				.withIgnorePaths("id", "title", "info", "user");
        var example = Example.of(Event.createEvent(eventDetails), exampleMatcher);
        var event = eventRepository.findOne(example);
        return event.isPresent();
    }

	/**
	 * Add an event to the DB using the specified event details
	 *
	 * @param eventDetails the specified event details
	 *
	 * @throws NullPointerException if the specified details is null
	 * @return 409 (conflict) http response if there is the same event in the db
	 * 		   201 (created) http response otherwise
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
	 * @param eventSave the specified event details
	 *
	 * @throws NullPointerException if the specified details is null
	 * @throws EntityNotFoundException if the event with the specified id was not found 
	 * @return 202 http response if the event was updated
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
	 * @return 200 (ok) http response if the corresponding event was deleted
	 * 		   404 (not found) http response otherwise
	 */
    public ResponseEntity<EventResponseDTO> removeEvent(UUID id) {
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
	 * @return 200 (ok) http response if the event was found
	 * 		   404 (not found) http response otherwise
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
	 * @return 200 (ok) http response containing a list of all the events,
	 * 		   404 (not found) http response otherwise
	 */
    public ResponseEntity<List<EventResponseDTO>> getEvents() {
        var events = eventRepository.findAll();
		if(events.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
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
	 * @return 200 (ok) http response containing the retrieved event
	 * 		   404 (not found) http response otherwise
	 */
    public ResponseEntity<EventResponseDTO> getEventById(UUID id) {
        var event = eventRepository.findById(id);
        if(event.isPresent()) {
            return ResponseEntity.ok(new EventResponseDTO(event.get()));
        }
        return ResponseEntity.notFound().build();
    }

	/**
	 * Retrieve all the events of the day
	 *
	 * @return 200 (ok) http response containing all the events of the day
	 * 		   404 (not found) http response otherwise
	 */
	public ResponseEntity<List<EventResponseDTO>> getEventOfTheDay(String username) {
		var calendar = Calendar.getInstance();
		var events = eventRepository.findAll();
		var diff = calendar.getActualMaximum(Calendar.HOUR_OF_DAY) - calendar.get(Calendar.HOUR_OF_DAY);
		var current = calendar.getTime();
		calendar.add(Calendar.HOUR, diff);
		var limit = calendar.getTime();
		var eventsOfTheDay = events.stream().filter(event ->
				(event.getDateStart().after(current) && event.getDateStart().before(limit)) && event.user().equals(username))
				.map(EventResponseDTO::new)
				.toList();
		if (eventsOfTheDay.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(eventsOfTheDay);
	}

	/**
	 * Find the closest event to the specified date time
	 *
	 * @param current the specified date time
	 * @param event1 first event
	 * @param event2 second event
	 * @return 1 if the first event is the closest to the specified date time
	 * 		   -1 if the second event is the closest to the specified date time
	 * 		   0 otherwise
	 */
	private int compareRecent(Date current, Event event1, Event event2) {
		if (current.compareTo(event1.getDateStart()) < current.compareTo(event2.getDateStart())) {
			return 1;
		} else if (current.compareTo(event1.getDateStart()) == current.compareTo(event2.getDateStart())) {
			return 0;
		}
		return -1;
	}

	/**
	 * Retrieve most recent event until now
	 *
	 * @return 200 (ok) http response containing the most recent event
	 * 		   404 (not found) http response otherwise
	 */
	public ResponseEntity<EventResponseDTO> getMostRecentEvent(String username) {
		var current = Calendar.getInstance().getTime();
		var events = eventRepository.findAll();
		var recent = events.stream()
				.filter(event -> event.user().equals(username))
				.min((event1, event2) -> compareRecent(current, event1, event2));
		if (recent.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(new EventResponseDTO(recent.get()));
	}
}
