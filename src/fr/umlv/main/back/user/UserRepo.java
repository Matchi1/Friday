package fr.umlv.main.back.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

/**
 * This interface provides communication between the user table from a DB and
 * the software layer.
 */
@Repository
public interface UserRepo extends JpaRepository<User, UUID> {
}
