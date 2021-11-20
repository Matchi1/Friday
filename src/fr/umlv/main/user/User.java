package fr.umlv.main.user;

import fr.umlv.main.crypt.CryptPassword;
import fr.umlv.main.event.Event;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.persistence.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
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

    public User(String username, String password) throws IllegalBlockSizeException, NoSuchPaddingException, BadPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);
        this.username = username;
        this.password = CryptPassword.cryptedPassword(password);
    }

    public User() {

    }


    public void setPassword(String password) throws IllegalBlockSizeException, NoSuchPaddingException, BadPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        this.password = CryptPassword.cryptedPassword(password);
    }

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        return CryptPassword.decryptedPassword(password);
    }
}
