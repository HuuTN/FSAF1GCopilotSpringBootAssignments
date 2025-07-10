import com.example.usermanagement.dto.UserDTO;
import com.example.usermanagement.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllUsers() {
        Pageable pageable = PageRequest.of(0, 10);
        List<UserDTO> users = Arrays.asList(new UserDTO(), new UserDTO());
        Page<UserDTO> page = new PageImpl<>(users);

        when(userService.getAllUsers(pageable)).thenReturn(page);

        ResponseEntity<Page<UserDTO>> response = userController.getAllUsers(pageable);

        assertEquals(2, response.getBody().getContent().size());
        verify(userService, times(1)).getAllUsers(pageable);
    }

    @Test
    void testGetUserById() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);

        when(userService.getUserById(1L)).thenReturn(userDTO);

        ResponseEntity<UserDTO> response = userController.getUserById(1L);

        assertEquals(1L, response.getBody().getId());
        verify(userService, times(1)).getUserById(1L);
    }

    @Test
    void testCreateUser() {
        UserDTO userDTO = new UserDTO();
        userDTO.setName("John Doe");

        when(userService.createUser(userDTO)).thenReturn(userDTO);

        ResponseEntity<UserDTO> response = userController.createUser(userDTO);

        assertEquals("John Doe", response.getBody().getName());
        verify(userService, times(1)).createUser(userDTO);
    }

    @Test
    void testUpdateUser() {
        UserDTO userDTO = new UserDTO();
        userDTO.setName("Jane Doe");

        when(userService.updateUser(1L, userDTO)).thenReturn(userDTO);

        ResponseEntity<UserDTO> response = userController.updateUser(1L, userDTO);

        assertEquals("Jane Doe", response.getBody().getName());
        verify(userService, times(1)).updateUser(1L, userDTO);
    }

    @Test
    void testDeleteUser() {
        doNothing().when(userService).deleteUser(1L);

        ResponseEntity<Void> response = userController.deleteUser(1L);

        assertEquals(204, response.getStatusCodeValue());
        verify(userService, times(1)).deleteUser(1L);
    }
}