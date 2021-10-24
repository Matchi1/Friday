package fr.umlv.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CalendarController {

    private UserRep userRepo;

    //Recup un produit par un Id
    @GetMapping(value = "/test/{id}")
    public Calendar printCalendar(@PathVariable int id) {
        return userRepo.findById(id);
    }

    @DeleteMapping (value = "/test/{id}")
    public void deleteCalendar(@PathVariable int id) {
        userRepo.delete(id);
    }

}
