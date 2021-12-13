package fr.umlv.main.back.event;

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
	 * @return a response entity with the added event
	 */
    @PostMapping("/event/add")
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
	 * @return a response entity with the removed event
	 */
    @DeleteMapping("/event/delete")
    public ResponseEntity<EventResponseDTO> removeEvent(@RequestBody EventCredentialDTO event) {
        Objects.requireNonNull(event);
        return eventService.removeEvent(event.id(), event.user());
    }

	/**
	 * Retrieve all the event from the database
	 *
	 * @return a response entity containing all the events
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
	 * @return a response entity with the removed event
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
	 * @return a response entity containing the updated event
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
	 * @return a response entity containing the corresponding event
	 */
    @GetMapping("/event/get/{id}")
    public ResponseEntity<EventResponseDTO> getEvent(@PathVariable UUID id) {
        Objects.requireNonNull(id);
        return eventService.getEventById(id);
    }
}
