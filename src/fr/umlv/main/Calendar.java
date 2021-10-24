package fr.umlv.main;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Table(name = "Calendar")
public class Calendar {
    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private String date;

    @Column
    private String heure;

    @Column
    private String info;

    public Integer getId() {
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
}
