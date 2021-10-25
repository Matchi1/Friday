package fr.umlv.main;

import org.apache.http.HttpResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepo extends JpaRepository<CalendarEntity, UUID> {

    ResponseEntity<UserResponse> saveUser(String username, String password);
}
