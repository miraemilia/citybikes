package com.citybike.backend;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.citybike.backend.model.Station;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class StationControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllStatusOk() throws Exception {
        mockMvc.perform(get("/api/stations")).andExpectAll(
            status().isOk(),
            content().contentType("application/json")
            );
  }

    @Test
    public void getAllNotEmpty() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        MvcResult result = mockMvc.perform(get("/api/stations"))
            .andReturn();
        String content = result.getResponse().getContentAsString();
        Station[] stations = objectMapper.readValue(content, new TypeReference<Station[]>(){});
        assertTrue(stations.length > 0);
    }

    @Test
    public void getByIdStatusOk() throws Exception {
        mockMvc.perform(get("/api/stations/001"))
            .andExpect(status().isOk());
    }

    @Test
    public void getByIdKaivopuistoFound() throws Exception {
        String stationId = "001";
        mockMvc.perform(get("/api/stations/" + stationId))
            .andExpect(jsonPath("$.name").value("Kaivopuisto"));
    }

    @Test
    public void getByIdNonexistent() throws Exception {
        mockMvc.perform(get("/api/stations/99999"))
            .andExpect(status().is(404));
    }

    @Test
    public void getByIdInvalid() throws Exception {
        mockMvc.perform(get("/api/stations/yksi"))
            .andExpect(status().is(400));
    }
    
}