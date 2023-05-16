package com.citybike.backend;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.ResourceUtils;

import com.citybike.backend.model.Station;
import com.citybike.backend.service.CSVReader;

@ActiveProfiles("test")
public class StationValidationTests {

    @Test
    void stationsValidated() throws Exception {
        File stationFile = ResourceUtils.getFile("classpath:testdata/testvalidatestations.csv");
        List<Station> stations = CSVReader.csvToStations(new FileInputStream(stationFile));
        assertEquals(10, stations.size());
    }
}
