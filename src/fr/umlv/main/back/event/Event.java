package fr.umlv.main.back.event;

import fr.umlv.main.DateDetails;
import fr.umlv.main.back.formatter.EventFormatter;
import fr.umlv.main.back.user.User;

import javax.persistence.*;
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
    private Date dateStart;

    @Column(nullable = false)
    private Date dateEnd;

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
    private Event(String title, Date dateStart, Date dateEnd, String info) {
        this.title = Objects.requireNonNull(title);
        this.dateStart = Objects.requireNonNull(dateStart);
        this.dateEnd = Objects.requireNonNull(dateEnd);
        this.info = Objects.requireNonNull(info);
    }

	/**
	 * Create Date according to the specified date details
	 *
	 * @param details the specified date details
	 */
    private static Date createDateFromDateDetails(DateDetails details) {
        var calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(details.year(), details.month(), details.day(), details.hour(), details.minute());
        return calendar.getTime();
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
        var start = createDateFromDateDetails(eventDetails.dateStart());
        var end = createDateFromDateDetails(eventDetails.dateEnd());
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
    public Date getDateStart() {
        return dateStart;
    }

	/**
	 * Return the ending date of the event
	 *
	 * @return Return the ending date of the event
	 */
    public Date getDateEnd() {
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
	 * Set the starting date according to the specified date details
	 *
	 * @param dateStartDetails the specified date details
	 */
    public void setDateStart(DateDetails dateStartDetails) {
        this.dateStart = createDateFromDateDetails(dateStartDetails);
    }

	/**
	 * Set the ending date according to the specified date details
	 *
	 * @param dateEndDetails the specified date details
	 */
    public void setDateEnd(DateDetails dateEndDetails) {
        this.dateEnd = createDateFromDateDetails(dateEndDetails);
    }

	/**
	 * Set the description according to the specified description
	 *
	 * @param info the specified description
	 */
    public void setInfo(String info) {
        this.info = info;
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
