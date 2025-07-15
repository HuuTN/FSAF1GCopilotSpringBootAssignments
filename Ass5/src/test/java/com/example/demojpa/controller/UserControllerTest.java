package com.example.demojpa.controller;

import com.example.demojpa.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = UserController.class, excludeAutoConfiguration = {
    org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration.class,
    org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration.class
})
public class UserControllerTest {
    @Autowired private MockMvc mockMvc;
    @MockBean private UserService userService;
    @MockBean(name = "jpaAuditingHandler")
    private Object auditingHandler;

    @Test
    void whenGetUserById_thenReturnUserDTO() throws Exception {
        // Arrange
        com.example.demojpa.entity.User user = new com.example.demojpa.entity.User();
        user.setId(1L);
        user.setUsername("John Doe");
        org.mockito.Mockito.when(userService.getUserById(1L)).thenReturn(java.util.Optional.of(user));

        // Act & Assert
        mockMvc.perform(get("/api/v1/users/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.username").value("John Doe"));
    }
}
