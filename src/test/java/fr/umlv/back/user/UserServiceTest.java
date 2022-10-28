package fr.umlv.back.user;

import static org.mockito.Mockito.*;

import fr.umlv.back.crypt.CryptPassword;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class UserServiceTest {

    private UserService service;
    private final CryptPassword encryptor = new CryptPassword();

    @Nested
    @DisplayName("Test the addUser method")
    class TestAddUser {

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
            setup_item(repo, "William", "new password", true);
            service = new UserService(repo);
        }

        @Test
        void testUpdatePassword() throws ExecutionException, InterruptedException {
            var user = new UserSaveDTO("William", "new password");
            var future = service.updatePassword(user);
            Assertions.assertTrue(future.isDone());
            var data = future.get();
            Assertions.assertTrue(data.isPresent());
            var got = data.get();
            Assertions.assertNotNull(got);
            Assertions.assertEquals(got.username(), user.username());
        }

        @Test
        void testNoUpdatePassword() throws ExecutionException, InterruptedException {
            var user = new UserSaveDTO("john", "new password");
            var future = service.updatePassword(user);
            Assertions.assertTrue(future.isDone());
            var data = future.get();
            Assertions.assertTrue(data.isEmpty());
        }
    }

    @Nested
    @DisplayName("Test the removeUser method")
    class TestRemoveUser {

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
        void testRemoveUser() throws ExecutionException, InterruptedException {
            var user = "William";
            var future = service.removeUser(user);
            Assertions.assertTrue(future.isDone());
            var removed = future.get();
            Assertions.assertTrue(removed);
        }

        @Test
        void testNoRemoveUser() throws ExecutionException, InterruptedException {
            var user = "James";
            var future = service.removeUser(user);
            Assertions.assertTrue(future.isDone());
            var removed = future.get();
            Assertions.assertFalse(removed);
        }
    }
}
