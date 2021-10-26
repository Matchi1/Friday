package fr.umlv.main;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "Calendar")
public class Event {
    @Column(nullable = false)
    @Id
    @GeneratedValue
    private UUID id;

    private String pwd;

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

    public String getPwd() {
        return pwd;
    }

    public UUID getId() {
        return id;
    }


}
