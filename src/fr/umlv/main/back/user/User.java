package fr.umlv.main.back.user;

import fr.umlv.main.back.crypt.CryptPassword;
import fr.umlv.main.back.event.Event;

import javax.crypto.IllegalBlockSizeException;
import javax.persistence.*;
import java.security.InvalidKeyException;
import java.util.ArrayList;
import java.util.Objects;
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

    @OneToMany(mappedBy = "user")
    private ArrayList<Event> events;

    public User(String username, String password)  {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);
        this.username = username;
        this.password = password;
    }

    public User() {
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }
}
