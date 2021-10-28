package fr.umlv.main;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "event")
public class Event {
    @Id
    @GeneratedValue
    @Column(nullable = false)
    private UUID id;

    @Column(name = "date")
    private String date;

    @Column(name = "heure")
    private String heure;

    @Column(name = "info")
    private String info;

    public String getInfo() {
        return info;
    }

    public String getHeure() {
        return heure;
    }

    public String getDate() {
        return date;
    }

    public UUID getId() {
        return id;
    }


}
