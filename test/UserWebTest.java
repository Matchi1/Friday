import com.fasterxml.jackson.databind.ObjectMapper;
import fr.umlv.main.CalendarController;
import fr.umlv.main.user.UserSaveDTO;
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
public class UserWebTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CalendarController controller;

    @Test
    void addUserRequestPath() throws Exception {
        var userSave = new UserSaveDTO("matchi", "test");
        var mockRequest = MockMvcRequestBuilders
                .post("/user/add")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(userSave));
        mockMvc.perform(mockRequest)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

	@Test
	void getUserRequestPath() throws Exception {
        var mockRequest = MockMvcRequestBuilders
                .get("/user/get");
        mockMvc.perform(mockRequest)
                .andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void updateUserRequestPath() {
	}

	@Test
	void removeUserRequestPath() {
	}

	@Test
	void userAddSuccessful() {
	}

	@Test
	void userUpdateSuccessful() {
	}

	@Test
	void userRemoveSuccessful() {
	}
}
