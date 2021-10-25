package fr.umlv.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CalendarController {
    @Autowired
    private CalendarService calendarService;

    private final BdRepo bdRepo;

    public CalendarController(BdRepo bdRepo) {
        this.bdRepo = bdRepo;
    }

    @GetMapping("/calendar")
    public List<CalendarEntity> getId() {
        return bdRepo.findAll();
    }
}
