package fr.umlv.back.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * This class provide basic features for communication between the database and
 * the web layout for the event table.
 */
@RestController
public class EventController {
    @Autowired
    private EventService eventService;

	/**
	 * Add a new event into the database according to the specified event info
	 *
	 * @param event the specified event info
	 *
	 * @throws NullPointerException if the specified event info is null
	 * @return 409 (conflict) http response if there is the same event in the db
	 * 		   201 (created) http response otherwise
	 */
    @PostMapping("/event/save")
    public ResponseEntity<EventResponseDTO> addEvent(@RequestBody EventSaveDTO event) {
        Objects.requireNonNull(event);
        return eventService.addEvent(event);
    }

	/**
	 * Remove an event from the database according the specified event info
	 *
	 * @param event the specified event info
	 * 
	 * @throws NullPointerException if the specified event info is null
	 * @return 200 (ok) http response if the corresponding event was deleted
	 * 		   404 (not found) http response otherwise
	 */
    @DeleteMapping("/event/delete")
    public ResponseEntity<EventResponseDTO> removeEvent(@RequestBody EventCredentialDTO event) {
        Objects.requireNonNull(event);
        return eventService.removeEvent(event.id());
    }

	/**
	 * Retrieve all the event from the database
	 *
	 * @return 200 (ok) http response containing a list of all the events,
	 * 		   404 (not found) http response otherwise
	 */
    @GetMapping("/event/all")
    public ResponseEntity<List<EventResponseDTO>> getEvents() {
        return eventService.getEvents();
    }

	/**
	 * Remove an event from the database according the specified event id
	 *
	 * @param id the specified event id
	 * 
	 * @throws NullPointerException if the specified event id is null
	 * @return 200 (ok) http response if the event was found
	 * 		   404 (not found) http response otherwise
	 */
    @DeleteMapping("/event/delete/{id}")
    public ResponseEntity<EventResponseDTO> removeEventById(@PathVariable UUID id) {
        Objects.requireNonNull(id);
        return eventService.removeEventById(id);
    }

	/**
	 * Update an event with the specified id and it will be replaced by the
	 * specified event info.
	 *
	 * @param id the specified id
	 * @param event the specified event info
	 *
	 * @throws NullPointerException if the specified id or event info is null
	 * @return 202 http response if the event was updated
	 */
    @PutMapping("/event/update/{id}")
    public ResponseEntity<EventResponseDTO> updateEvent(
            @PathVariable UUID id,
            @RequestBody EventSaveDTO event) {
        Objects.requireNonNull(event);
        Objects.requireNonNull(id);
        return eventService.updateEvent(id, event);
    }

	/**
	 * Retrieve an event from the database with the specified id
	 *
	 * @param id the specified id
	 *
	 * @throws NullPointerException if the specified id is null
	 * @return 200 (ok) http response containing the retrieved event
	 * 		   404 (not found) http response otherwise
	 */
    @GetMapping("/event/get/{id}")
    public ResponseEntity<EventResponseDTO> getEvent(@PathVariable UUID id) {
        Objects.requireNonNull(id);
        return eventService.getEventById(id);
    }

	/**
	 * Retrieve all the events of the day
	 *
	 * @return 200 (ok) http response containing all the events of the day
	 * 		   404 (not found) http response otherwise
	 */
	@GetMapping("/event/get/day")
	public ResponseEntity<List<EventResponseDTO>> getEventOfTheDay() {
		return eventService.getEventOfTheDay();
	}
}
