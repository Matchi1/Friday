package fr.umlv.main.user;

import fr.umlv.main.crypt.CryptPassword;
import fr.umlv.main.event.Event;

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
    private byte[] password;

    @OneToMany(mappedBy = "user")
    private ArrayList<Event> user;


    public User(String username, String password) throws IllegalBlockSizeException, InvalidKeyException {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);
        CryptPassword cryptPassword = new CryptPassword();
        this.username = username;
        this.password = cryptPassword.cryptedPassword(password);
    }

    public User() {

    }


    public void setPassword(String password) throws IllegalBlockSizeException, InvalidKeyException {
        CryptPassword cryptPassword = new CryptPassword();
        this.password = cryptPassword.cryptedPassword(password);
    }

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() throws IllegalBlockSizeException, InvalidKeyException {
        CryptPassword cryptPassword = new CryptPassword();;
        return cryptPassword.decryptedPassword(password);
    }
}
