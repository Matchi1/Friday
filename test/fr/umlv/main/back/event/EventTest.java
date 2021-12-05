package fr.umlv.main.back.event;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void shouldThrowNPECreatedEventWithNullParameters() {
        Assertions.assertThrows(NullPointerException.class, () -> Event.createEvent(null));
    }

    @Test
    public void shouldThrowNPECreateEventSaveDTOWithNullParameters() {
        Assertions.assertThrows(NullPointerException.class, () -> new EventSaveDTO(null, null, null, ""));
    }

}
