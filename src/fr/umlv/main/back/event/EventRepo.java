package fr.umlv.main.back.event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * This interface provides communication between the event table from a DB and
 * the software layer.
 */
@Repository
public interface EventRepo extends JpaRepository<Event, UUID> {
}
