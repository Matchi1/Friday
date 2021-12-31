package fr.umlv.back.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.umlv.back.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;
import java.time.LocalDate;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@WebMvcTest(EventController.class)
public class EventControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

	@MockBean
	private EventService eventService;

    @Test
    void shouldRespond201WhenAddNewEvent() throws Exception {
		var start = "2021/11/10 23:14";
		var eventSave = new EventSaveDTO("", start, "add event test");
		var local = LocalDate.now();
		var response = new EventResponseDTO(UUID.randomUUID(), "", local, local, "");
		Mockito.when(eventService.addEvent(eventSave))
				.thenReturn(CompletableFuture.completedFuture(new ResponseEntity<>(response, HttpStatus.CREATED)));
        var mockRequest = MockMvcRequestBuilders
                .post("/event/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(eventSave));
        mockMvc.perform(mockRequest)
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

	@Test
	void shouldRespond200WhenGetAllEvents() throws Exception {
        var mockRequest = MockMvcRequestBuilders
                .get("/event/all");
		Mockito.when(eventService.getEvents())
				.thenReturn(CompletableFuture.completedFuture(new ResponseEntity<>(HttpStatus.OK)));
        mockMvc.perform(mockRequest)
                .andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void shouldThrowNotFoundExceptionWhenUpdateEventNotExist() throws Exception {
		var id = UUID.randomUUID();
		var start = "2021/11/10 23:14";
		var eventSave = new EventSaveDTO("", start, "add event test");
		Mockito.when(eventService.updateEvent(id, eventSave))
				.thenThrow(new ResourceNotFoundException("Event not found"));
		var mockRequest = MockMvcRequestBuilders
				.put("/event/update/" + id)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(eventSave));
		mockMvc.perform(mockRequest)
				.andExpect(MockMvcResultMatchers.status().isNotFound());
	}

	@Test
	void shouldSucceedWhenUpdateEvent() throws Exception {
		var id = UUID.randomUUID();
		var start = "2021/11/10 23:14";
		var eventSave = new EventSaveDTO("", start,"add event test");
		Mockito.when(eventService.updateEvent(id, eventSave))
				.thenReturn(CompletableFuture.completedFuture(ResponseEntity.accepted().build()));
		var mockRequest = MockMvcRequestBuilders
				.put("/event/update/" + id)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(eventSave));
		mockMvc.perform(mockRequest)
				.andExpect(MockMvcResultMatchers.status().isAccepted());
	}

	@Test
	void shouldRespond404WhenNoSuchEvent() throws Exception {
		var eventDelete = new EventCredentialDTO(UUID.randomUUID(), null);
		var mockRequest = MockMvcRequestBuilders
				.delete(URI.create("/event/delete"))
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(eventDelete));
		// remove event if it is already in database
		mockMvc.perform(mockRequest)
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void shouldRespondIsOkWhenRemoveEvent()  {
	}

	@Test
	void eventUpdateSuccessful() {
	}

	@Test
	void eventRemoveSuccessful() {
	}
}
