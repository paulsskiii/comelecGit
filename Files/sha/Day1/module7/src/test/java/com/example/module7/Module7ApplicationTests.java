package com.example.module7;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

// STATIC IMPORTS: These allow us to write fluent, readable tests
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@EnableAutoConfiguration
@AutoConfigureMockMvc // 1. Configures the "Fake" Browser (MockMvc)
class DemoApplicationTests {

    @Autowired
    private MockMvc mockMvc; // 2. Injects the tool to perform requests

    @Test
    void shouldReturnSystemOperational() throws Exception {
        // 3. The Test Scenario: Perform Request -> Expect Status -> Expect Content
        this.mockMvc.perform(get("/status"))
                .andExpect(status().isOk()) // Expect HTTP 200
                .andExpect(content().string("System Operational"));
    }
}
