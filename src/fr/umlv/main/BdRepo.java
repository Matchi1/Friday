package fr.umlv.main;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BdRepo extends JpaRepository<Calendar, Integer> {
}
