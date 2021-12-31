package fr.umlv.back.user;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.umlv.back.crypt.CryptPassword;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class UserWebTest {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void initialize() {
        var username = "hello";
        var password = new CryptPassword().hash("world");
        var user = new User(username, password);

        userRepository.save(user);
    }

    @Test
    void shouldBeSuccessfulWhenAddingUser() throws Exception {
        var mockRequest = MockMvcRequestBuilders
                .get("/user/exist/hello");
        mockMvc.perform(mockRequest)
                .andExpect(MockMvcResultMatchers.status().isOk());
	}

    @Test
    void shouldHaveCorrectCredentials() throws Exception {
        var mockRequest = MockMvcRequestBuilders
                .post("/user/exist")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(new UserSaveDTO("hello", "world")));
        mockMvc.perform(mockRequest)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void shouldHaveDeleteUserCorrectly() throws Exception {
        var mockRequest = MockMvcRequestBuilders
                .delete("/user/delete/hello");
        mockMvc.perform(mockRequest)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void shouldHaveHaveConflictWithSameUsername() throws Exception {
        var mockRequest = MockMvcRequestBuilders
                .post("/user/save")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(new UserSaveDTO("hello", "ok")));
        mockMvc.perform(mockRequest)
                .andExpect(MockMvcResultMatchers.status().isConflict());
    }
}
