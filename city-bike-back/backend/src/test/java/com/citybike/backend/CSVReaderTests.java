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
import com.citybike.backend.util.JourneyImport;

@ActiveProfiles("test")
public class CSVReaderTests {

    @Test
    void stationsReadFromFile() throws Exception {
        File stationFile = ResourceUtils.getFile("classpath:testdata/testCSVstations.csv");
        List<Station> stations = CSVReader.csvToStations(new FileInputStream(stationFile));
        assertEquals(10, stations.size());
    }

    @Test
    void journeysReadFromFile() throws Exception {
        File journeyFile = ResourceUtils.getFile("classpath:testdata/testCSVjourneys.csv");
        List<JourneyImport> journeys = CSVReader.csvToJourneys(new FileInputStream(journeyFile));
        assertEquals(10, journeys.size());
    }

}
