package fr.umlv.back.event;

import java.util.Objects;

/**
 * This class is responsible of containing the information transmitted during a
 * request mapping
 */
public record EventSaveDTO(String title, String start, String end, String info) {

	/**
	 * Compact constructor that verify the validity of the arguments
	 *
	 * @throws NullPointerException if one the specified argument is null
	 */
    public EventSaveDTO {
        Objects.requireNonNull(title);
        Objects.requireNonNull(start);
        Objects.requireNonNull(end);
        Objects.requireNonNull(info);
    }

	/**
	 * Contructs the object with a default ending date (dateEnd field)
	 *
	 * @throws NullPointerException if one the specified argument is null
	 */
    public EventSaveDTO(String title, String start, String info) {
        this(title, start, start, info);
    }
}
