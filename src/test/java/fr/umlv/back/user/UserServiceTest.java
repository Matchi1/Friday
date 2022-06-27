package fr.umlv.back.user;

import static org.mockito.Mockito.*;

import fr.umlv.back.crypt.CryptPassword;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class UserServiceTest {

    @Nested
    @DisplayName("Test the addUser method")
    class TestAddUser {

        private UserService service;
        private final CryptPassword encryptor = new CryptPassword();


        void setup_item(UserRepo repo, String name, String password, boolean isPresent) {
            var user = new User(name, encryptor.hash(password));
            when(repo.save(user)).thenReturn(user);
            when(repo.existsById(name)).thenReturn(isPresent);
        }

        @BeforeEach
        void setup() {
            var repo = mock(UserRepo.class);
            setup_item(repo, "john", "1234", false);
            setup_item(repo, "James", "password", false);
            setup_item(repo, "William", "qwerty", true);
            service = new UserService(repo);
        }

        @Test
        void testAddUsers() throws ExecutionException, InterruptedException {
            var users = new ArrayList<UserSaveDTO>();
            users.add(new UserSaveDTO("john", "1234"));
            users.add(new UserSaveDTO("James", "password"));
            for(var user : users) {
                var future = service.addUser(user);
                Assertions.assertTrue(future.isDone());
                var data = future.get();
                Assertions.assertTrue(data.isPresent());
                var got = data.get();
                Assertions.assertNotNull(got);
                Assertions.assertEquals(got.username(), user.username());
            }
        }


        @Test
        void testDoNotAddUserIfPresent() throws ExecutionException, InterruptedException {
            var user = new UserSaveDTO("William", "qwerty");
            var future = service.addUser(user);
            var data = future.get();
            Assertions.assertTrue(data.isEmpty());
        }
    }

    @Nested
    @DisplayName("Test the updateUser method")
    class TestUpdateUser {

    }
}
