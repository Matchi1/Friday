package fr.umlv.main;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Calendar {
    @Id
    private Long id;

    @Column
    private String name;
}
