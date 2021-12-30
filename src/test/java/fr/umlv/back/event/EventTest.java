package fr.umlv.back.event;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

public class EventTest {
    @Test
    public void shouldThrowNPECreatedEventWithNullParameters() {
        Assertions.assertThrows(NullPointerException.class, () -> Event.createEvent(null));
    }

    @Test
    public void shouldThrowNPECreateEventSaveDTOWithNullParameters() {
        Assertions.assertThrows(NullPointerException.class, () -> new EventSaveDTO(null, null, null, ""));
    }

    @Test
    public void shouldReturnCorrectEventNoPeriodString() throws ParseException {
        var start = "2000/01/01 00:00";
        var event = Event.createEvent(new EventSaveDTO("hello", start, "no info"));
        Assertions.assertEquals("hello:20000101T0000:no info", event.toString());
    }

    @Test
    public void shouldReturnCorrectEventWithPeriodString() throws ParseException {
        var start = "2000/01/01 00:00";
        var end = "2001/01/01 00:00";
        var event = Event.createEvent(new EventSaveDTO("hello", start, end, "no info"));
        Assertions.assertEquals("hello:20000101T0000/20010101T0000:no info", event.toString());
    }
}
