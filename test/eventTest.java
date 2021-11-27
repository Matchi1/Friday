import fr.umlv.main.event.Event;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class eventTest {
    @Test
    public void eventArgumentNotNull() {
        assertThrows(NullPointerException.class, () -> new Event(null, null, null));
    }
    @Test
    public void eventShouldHaveArgument() {
        assertThrows(IllegalArgumentException.class, Event::new);
    }

    @Test
    public void eventIdNotNull() {
        var event = new Event("2021-11-10", "22:01", "test id");
        assertNotEquals(null, event.getId());
    }

    @Test
    public void eventDateNotNull() {
        var event = new Event("2021-11-10", "22:01", "test date");
        assertNotEquals(null, event.getDate());
    }

    @Test
    public void eventHourNotNull() {
        var event = new Event("2021-11-10", "22:01", "test hour");
        assertNotEquals(null, event.getHeure());
    }

    @Test
    public void eventInfoNotNull() {
        var event = new Event("2021-11-10", "22:01", "test info");
        assertNotEquals(null, event.getInfo());
    }
}
