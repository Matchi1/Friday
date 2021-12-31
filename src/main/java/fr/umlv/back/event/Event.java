package fr.umlv.back.event;

import fr.umlv.back.formatter.DateFormatter;
import fr.umlv.back.formatter.EventFormatter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

/**
 * This class is responsible for representing an event and its characteristic
 */
@Entity(name = "EVENT_DB")
public class Event {
    @Id
    @GeneratedValue
    @Column(nullable = false)
    private UUID id;

    @Column
    private String title;

    @Column(nullable = false)
    private LocalDate dateStart;

    @Column(nullable = false)
    private LocalDate dateEnd;

    @Column(nullable = false)
    private String info;

    @Column(nullable = false)
    private String username;

	/**
	 * Constructs an empty event
	 */
    public Event() {
    }

	/**
	 * Constructs an event according to the specified arguments.
	 *
	 * @param title the title of the event
	 * @param dateStart the starting date of the event
	 * @param dateEnd the ending date of the event
	 * @param info the description of the event
	 *
	 * @throws NullPointerException if one of the specified argument is null
	 */
    private Event(String title, LocalDate dateStart, LocalDate dateEnd, String username, String info) {
        this.title = Objects.requireNonNull(title);
        this.dateStart = Objects.requireNonNull(dateStart);
        this.dateEnd = Objects.requireNonNull(dateEnd);
        this.info = Objects.requireNonNull(info);
        this.username = username;
    }

	/**
	 * Create an event according the specified event details
	 *
	 * @param eventDetails the specified event details
	 *
	 * @throws NullPointerException if the specified event details is null
	 */
    public static Event createEvent(EventSaveDTO eventDetails) {
        Objects.requireNonNull(eventDetails);
        var formatter = new DateFormatter();
        var start = formatter.formatFromStringToDate(eventDetails.start());
        var end = formatter.formatFromStringToDate(eventDetails.end());
        return new Event(eventDetails.title(), start, end, eventDetails.user(), eventDetails.info());
    }

	/**
	 * Return the id of the event
	 *
	 * @return Return the id of the event
	 */
    public UUID getId() {
        return id;
    }

	/**
	 * Return the title of the event
	 *
	 * @return Return the title of the event
	 */
    public String getTitle() {
        return title;
    }

	/**
	 * Return the starting date of the event
	 *
	 * @return Return the starting date of the event
	 */
    public LocalDate getDateStart() {
        return dateStart;
    }

	/**
	 * Return the ending date of the event
	 *
	 * @return Return the ending date of the event
	 */
    public LocalDate getDateEnd() {
        return dateEnd;
    }

	/**
	 * Return the description of the event
	 *
	 * @return Return the description of the event
	 */
    public String getInfo() {
        return info;
    }

    /**
     * Retrieve the associated username to this event
     *
     * @return the associated username to this event
     */
    public String user() {
        return username;
    }

    /**
     * Update this event according to the specified event details
     *
     * @param details the specified event details
     */
    public void eventUpdate(EventSaveDTO details) {
        var formatter = new DateFormatter();
        this.title = details.title();
        this.dateStart = formatter.formatFromStringToDate(details.start());
        this.dateEnd = formatter.formatFromStringToDate(details.end());
        this.info = details.info();
        this.username = details.user();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        var event = (Event) o;
        return dateStart.equals(event.dateStart) && dateEnd.equals(event.dateEnd) && username.equals(event.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateStart, dateEnd, username);
    }

    @Override
    public String toString() {
        var formatter = new EventFormatter();
        return formatter.format(this);
    }
}
