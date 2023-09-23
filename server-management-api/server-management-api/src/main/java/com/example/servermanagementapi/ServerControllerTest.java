package com.example.servermanagementapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class ServerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCreateServerEndpoint() throws Exception {
        // Create a JSON request body for creating a server
        String requestBody = "{\n" +
                "    \"name\": \"Test Server\",\n" +
                "    \"language\": \"Java\",\n" +
                "    \"framework\": \"Spring Boot\"\n" +
                "}";

        // Send a POST request to the createServer endpoint
        mockMvc.perform(MockMvcRequestBuilders.post("/api/servers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Test Server"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.language").value("Java"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.framework").value("Spring Boot"));
    }
}
