package fr.umlv.main;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRep extends JpaRepository<Calendar, Integer> {

    Calendar findById(int id);

    void delete(int id);
}
