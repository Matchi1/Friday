package fr.umlv.main;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Calendar {
    @Id
    @GeneratedValue
    private Integer id;

    private String date;
    private String heure;
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
