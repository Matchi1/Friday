import com.fasterxml.jackson.databind.ObjectMapper;
import fr.umlv.main.CalendarController;
import fr.umlv.main.event.EventSaveDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@AutoConfigureWebMvc
@SpringBootTest(classes = SpringBootTest.class)
public class EventWebTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CalendarController controller;

    @Test
    void addEventRequestPath() throws Exception {
        var eventSave = new EventSaveDTO("2021-11-10", "23:14", "add event test");
        var mockRequest = MockMvcRequestBuilders
                .post("/event/ad")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(eventSave));
        mockMvc.perform(mockRequest)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

	@Test
	void getAllEventsRequestPath() throws Exception {
        var mockRequest = MockMvcRequestBuilders
                .get("/events/getAll");
        mockMvc.perform(mockRequest)
                .andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void updateEventRequestPath() {
	}

	@Test
	void removeEventRequestPath() {
	}

	@Test
	void eventAddSuccessful() {
	}

	@Test
	void eventUpdateSuccessful() {
	}

	@Test
	void eventRemoveSuccessful() {
	}
}
