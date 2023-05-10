package com.citybike.backend;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class JourneyControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getPageStatusOk() throws Exception {
        mockMvc.perform(get("/api/journeys/25/0")).andExpectAll(
            status().isOk(),
            content().contentType("application/json")
            );
    }

    @Test
    public void getPageJourneysPerPageCorrect() throws Exception {
        String journeysPerPage = "25";
        mockMvc.perform(get("/api/journeys/" + journeysPerPage + "/0")).andExpect(
            jsonPath("$.pageable.pageSize").value(journeysPerPage));
    }
    
}
