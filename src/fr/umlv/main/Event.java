package fr.umlv.main;

import com.google.common.eventbus.EventBus;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "event")
public class Event {
    @Id
    @GeneratedValue
    @Column(nullable = false)
    private UUID id;

    @ManyToOne
    @Column(name = "date")
    private Event date;

    @ManyToOne
    @Column(name = "heure")
    private Event heure;

    @ManyToOne
    @Column(name = "info")
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

    public UUID getId() {
        return id;
    }


}
