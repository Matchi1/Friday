package fr.umlv.main;

import com.google.common.eventbus.EventBus;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = "EVENT_DB")
public class Event {
    @Id
    @GeneratedValue
    @Column(nullable = false)
    private int id;

    @ManyToOne
    private User date;

    @ManyToOne
    private User heure;

    @ManyToOne
    private User info;

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", date=" + date +
                ", heure=" + heure +
                ", info=" + info +
                '}';
    }
}
