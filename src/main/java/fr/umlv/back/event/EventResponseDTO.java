package fr.umlv.back.event;

import java.util.Date;
import java.util.UUID;

/**
 * This class contains all the necessary information about an event that the
 * user needs to know.
 */
public record EventResponseDTO(UUID id, String title, Date start, Date end, String information) {
	/**
	 * Contructs a response using the information contained in the specified
	 * event
	 *
	 * @param event the specified event
	 */
    public EventResponseDTO(Event event) {
        this(event.getId(), event.getTitle(), event.getDateStart(), event.getDateEnd(), event.getInfo());
    }
}
