package fr.umlv.back.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This interface provides communication between the user table from a DB and
 * the software layer.
 */
@Repository
public interface UserRepo extends JpaRepository<User, String> {}
