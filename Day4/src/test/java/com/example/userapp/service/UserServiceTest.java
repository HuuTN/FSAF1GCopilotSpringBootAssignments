import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.example.userapp.dto.UserDTO;
import com.example.userapp.entity.User;
import com.example.userapp.exception.UserNotFoundException;
import com.example.userapp.repository.UserRepository;
import com.example.userapp.service.UserService;
import com.example.userapp.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

public class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    private User user;
    private UserDTO userDTO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User(1L, "John Doe", "john.doe@example.com");
        userDTO = new UserDTO(1L, "John Doe", "john.doe@example.com");
    }

    @Test
    public void testCreateUser() {
        when(userRepository.save(any(User.class))).thenReturn(user);
        UserDTO createdUser = userService.create(userDTO);
        assertEquals(user.getName(), createdUser.getName());
        assertEquals(user.getEmail(), createdUser.getEmail());
    }

    @Test
    public void testGetUserById() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        User foundUser = userService.getById(1L);
        assertEquals(user.getName(), foundUser.getName());
    }

    @Test
    public void testGetUserByIdNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());
        try {
            userService.getById(1L);
        } catch (UserNotFoundException e) {
            assertEquals("User not found", e.getMessage());
        }
    }

    // Additional tests for update and delete methods can be added here
}