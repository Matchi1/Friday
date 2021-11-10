package fr.umlv.main.event;

import fr.umlv.main.user.User;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity(name = "EVENT_DB")
public class Event {
    @Id
    @GeneratedValue
    @Column(nullable = false)
    private UUID id;

    @Column(nullable = false)
    private final String date;

    private final String heure;

    private final String info;

    @ManyToOne
    private User user;

    public Event(String date, String heure, String info) {
        Objects.requireNonNull(date);
        Objects.requireNonNull(heure);
        Objects.requireNonNull(info);
        this.date = date;
        this.heure = heure;
        this.info = info;
    }

    public Event() {
        throw new IllegalArgumentException("Construction should have argument");
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public UUID getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getHeure() {
        return heure;
    }

    public String getInfo() {
        return info;
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
