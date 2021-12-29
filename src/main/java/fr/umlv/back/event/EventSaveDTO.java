package fr.umlv.back.event;
import fr.umlv.back.DateDetails;

import java.util.Objects;

/**
 * This class is responsible of containing the information transmitted during a
 * request mapping
 */
public record EventSaveDTO(String title, DateDetails dateStart, DateDetails dateEnd, String info) {

	/**
	 * Compact constructor that verify the validity of the arguments
	 *
	 * @throws NullPointerException if one the specified argument is null
	 */
    public EventSaveDTO {
        Objects.requireNonNull(title);
        Objects.requireNonNull(dateStart);
        Objects.requireNonNull(dateEnd);
        Objects.requireNonNull(info);
    }

	/**
	 * Contructs the object with a default ending date (dateEnd field)
	 *
	 * @throws NullPointerException if one the specified argument is null
	 */
    public EventSaveDTO(String title, DateDetails dateStart, String info) {
        this(title, dateStart, dateStart, info);
    }
}
