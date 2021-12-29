package java.fr.umlv.back.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.umlv.back.user.UserController;
import fr.umlv.back.user.UserSaveDTO;
import fr.umlv.back.user.UserService;
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

@WebMvcTest(UserController.class)
public class UserWebTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

	@MockBean
	private UserService userService;

    @Test
    void shouldBeSuccessfulWhenAddingUser() throws Exception {
        var username = "matchi";
        var password = "azerty";
        var userDetails = new UserSaveDTO(username, password);
        var mockRequest = MockMvcRequestBuilders
                .post("/user/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDetails));;
        Mockito.when(userService.addUser(username, password))
                .thenReturn(new ResponseEntity<>(HttpStatus.CREATED));
        mockMvc.perform(mockRequest)
                .andExpect(MockMvcResultMatchers.status().isCreated());
	}

    void shouldHaveSameCryptValue() {
    }
}
