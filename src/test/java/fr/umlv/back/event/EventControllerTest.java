package fr.umlv.back.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@SpringBootTest
@AutoConfigureMockMvc
public class EventControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

	@Autowired
	private EventRepo eventRepo;

	private UUID currentId;

	@BeforeEach
	public void initialize() {
		var details = new EventSaveDTO("test", "11/10/2021, 23:14:00 GMT+1", "11/10/2022, 23:14:00 GMT+1", "hello world");
		var event = Event.createEvent(details);

		this.currentId = eventRepo.save(event).getId();
	}

    @Test
    void shouldRespond409WhenAddSameDate() throws Exception {
		var details = new EventSaveDTO("test", "11/10/2021, 23:14:00 GMT+1", "11/10/2022, 23:14:00 GMT+1","ok", "hello world");
        var mockRequest = MockMvcRequestBuilders
                .post("/event/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(details));
        mockMvc.perform(mockRequest)
                .andExpect(MockMvcResultMatchers.status().isConflict());
    }

	@Test
	void shouldThrowOkWhenUpdateEvent() throws Exception {
		var eventSave = new EventSaveDTO("changed titled", "01/28/2021, 23:14:00 GMT+1", "ok", "add event test");
		var mockRequest = MockMvcRequestBuilders
				.put("/event/update/" + currentId)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(eventSave));
		mockMvc.perform(mockRequest)
				.andExpect(MockMvcResultMatchers.status().isAccepted());
	}

	@Test
	void shouldThrowNotFoundExceptionWhenEventIdNotExist() throws Exception {
		var eventSave = new EventSaveDTO("changed titled", "01/28/2021, 23:14:00 GMT+1", "user", "add event test");
		var mockRequest = MockMvcRequestBuilders
				.put("/event/update/" + UUID.randomUUID())
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(eventSave));
		mockMvc.perform(mockRequest)
				.andExpect(MockMvcResultMatchers.status().isNotFound());
	}

	@Test
	void shouldRespond404WhenNoSuchEvent() throws Exception {
		var eventDelete = new EventCredentialDTO(UUID.randomUUID(), null);
		var mockRequest = MockMvcRequestBuilders
				.delete(URI.create("/event/delete"))
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(eventDelete));
		mockMvc.perform(mockRequest)
				.andExpect(MockMvcResultMatchers.status().isNotFound());
	}

	@Test
	void shouldRespondIsOkWhenRemoveEvent() throws Exception {
		var mockRequest = MockMvcRequestBuilders
				.delete(URI.create("/event/delete/" + currentId));
		mockMvc.perform(mockRequest)
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
}
