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
    private Event date;

    @ManyToOne
    private Event heure;

    @ManyToOne
    private Event info;

    public Event getInfo() {
        return info;
    }

    public Event getHeure() {
        return heure;
    }

    public Event getDate() {
        return date;
    }

    public int getId() {
        return id;
    }


}
