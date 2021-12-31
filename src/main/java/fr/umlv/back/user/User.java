package fr.umlv.back.user;

import javax.persistence.*;
import java.util.Objects;

/**
 * This class is responsible of representing an user and its characteristic
 */
@Entity(name = "USERS_DB")
public class User {
    @Id
	@Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

	/**
	 * Contructs an Event according to the specified username and password
	 *
	 * @param username the specified username
	 * @param password the specified password
	 *
	 * @throws NullPointerException if one of the specified argument is null
	 */
    public User(String username, String password)  {
        this.username = Objects.requireNonNull(username);
        this.password = Objects.requireNonNull(password);
    }

    public User() {
    }

	/**
	 * Set the a new password according to the specified password
	 *
	 * @param password the specified password
	 *
	 * @throws NullPointerException if the specified argument is null
	 */
    public void setPassword(String password) {
        this.password = Objects.requireNonNull(password);
    }

	/**
	 * Retrieve the username of the user
	 *
	 * @return the id of the user
	 */
    public String getUsername() {
        return username;
    }
}
