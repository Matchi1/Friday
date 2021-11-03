package fr.umlv.main;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity(name = "USER_DB")
public class User {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
