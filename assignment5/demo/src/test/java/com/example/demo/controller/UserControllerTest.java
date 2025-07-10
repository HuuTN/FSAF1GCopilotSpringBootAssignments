package com.example.demo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testCreateUser() throws Exception {
        String uniqueEmail = "integrationuser" + System.currentTimeMillis() + "@example.com";
        String userJson = "{" +
                "\"name\":\"IntegrationUser\"," +
                "\"email\":\"" + uniqueEmail + "\"," +
                "\"password\":\"password123\"}";
        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.email").value(uniqueEmail));
    }

    @Test
    void testGetUserNotFound() throws Exception {
        mockMvc.perform(get("/users/99999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetAllUsers() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").exists());
    }

    @Test
    void testUpdateUser() throws Exception {
        // First create a user
        String uniqueEmail = "integrationuser" + System.currentTimeMillis() + "@example.com";
        String userJson = "{" +
                "\"name\":\"IntegrationUser\"," +
                "\"email\":\"" + uniqueEmail + "\"," +
                "\"password\":\"password123\"}";
        String response = mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();
        Long userId = com.fasterxml.jackson.databind.JsonNode.class.cast(
            new com.fasterxml.jackson.databind.ObjectMapper().readTree(response)
        ).get("id").asLong();
        // Update user
        String updateJson = "{" +
                "\"name\":\"UpdatedName\"," +
                "\"email\":\"" + uniqueEmail + "\"," +
                "\"password\":\"newpassword\"}";
        mockMvc.perform(put("/users/" + userId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(updateJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("UpdatedName"));
    }

    @Test
    void testDeleteUser() throws Exception {
        // First create a user
        String uniqueEmail = "integrationuser" + System.currentTimeMillis() + "@example.com";
        String userJson = "{" +
                "\"name\":\"IntegrationUser\"," +
                "\"email\":\"" + uniqueEmail + "\"," +
                "\"password\":\"password123\"}";
        String response = mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();
        Long userId = com.fasterxml.jackson.databind.JsonNode.class.cast(
            new com.fasterxml.jackson.databind.ObjectMapper().readTree(response)
        ).get("id").asLong();
        // Delete user
        mockMvc.perform(delete("/users/" + userId))
                .andExpect(status().isNoContent());
        // Verify user is deleted
        mockMvc.perform(get("/users/" + userId))
                .andExpect(status().isNotFound());
    }
}
