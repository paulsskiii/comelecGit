package com.example.bullet_proof;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class BulletProofApplicationTests {

	@Test
	void contextLoads() {
	}

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
