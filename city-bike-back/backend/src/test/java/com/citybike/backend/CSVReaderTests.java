package com.citybike.backend;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

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

    @Test
    void journeysValidated() throws Exception {
        File journeyFile = ResourceUtils.getFile("classpath:testdata/testvalidatejourneys.csv");
        List<JourneyImport> journeys = CSVReader.csvToJourneys(new FileInputStream(journeyFile));
        assertEquals(8, journeys.size());
    }

    @Test 
    void journeyDistanceValidated() throws Exception {
        File journeyFile = ResourceUtils.getFile("classpath:testdata/testvalidatejourneys.csv");
        List<JourneyImport> journeys = CSVReader.csvToJourneys(new FileInputStream(journeyFile));
        JourneyImport shortDistance = new JourneyImport("2021-05-31T23:57:25", "2021-06-01T00:05:46", 94, 100, 0, 500);
        assertFalse(journeys.contains(shortDistance));
    }

    @Test 
    void journeyDurationValidated() throws Exception {
        File journeyFile = ResourceUtils.getFile("classpath:testdata/testvalidatejourneys.csv");
        List<JourneyImport> journeys = CSVReader.csvToJourneys(new FileInputStream(journeyFile));
        JourneyImport shortDuration = new JourneyImport("2021-05-31T23:54:48","2021-06-01T00:00:57",292,133,1713,0);
        assertFalse(journeys.contains(shortDuration));
    }
    
}
