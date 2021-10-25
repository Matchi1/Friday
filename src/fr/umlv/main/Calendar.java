package fr.umlv.main;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "Calendar")
public class Calendar {
    @Id
    @GeneratedValue
    private UUID id;

    private String pwd;

    @ManyToOne
    private Calendar date;

    @ManyToOne
    private Calendar heure;

    @ManyToOne
    private Calendar info;

}
