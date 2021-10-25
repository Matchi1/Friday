package fr.umlv.main;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "Calendar")
public class CalendarEntity {
    @Id
    @GeneratedValue
    private UUID id;

    private String pwd;

    @ManyToOne
    private CalendarEntity date;

    @ManyToOne
    private CalendarEntity heure;

    @ManyToOne
    private CalendarEntity info;

}
