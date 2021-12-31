package fr.umlv.back.event;

import fr.umlv.back.formatter.DateFormatter;
import fr.umlv.back.formatter.EventFormatter;
import fr.umlv.back.user.User;

import javax.persistence.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.*;

/**
 * This class is responsible of representing an event and its characteristic
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

	/**
	 * Pointor to the corresponding user in the database
	 */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

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
    private Event(String title, LocalDate dateStart, LocalDate dateEnd, String info) {
        this.title = Objects.requireNonNull(title);
        this.dateStart = Objects.requireNonNull(dateStart);
        this.dateEnd = Objects.requireNonNull(dateEnd);
        this.info = Objects.requireNonNull(info);
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
        return new Event(eventDetails.title(), start, end, eventDetails.info());
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
        return user.getUsername();
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
        return dateStart.equals(event.dateStart) && dateEnd.equals(event.dateEnd) && user.equals(event.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateStart, dateEnd, user);
    }

    @Override
    public String toString() {
        var formatter = new EventFormatter();
        return formatter.format(this);
    }
}
