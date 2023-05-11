package com.citybike.backend;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.ResourceUtils;

import com.citybike.backend.service.CSVReader;
import com.citybike.backend.util.JourneyImport;

@ActiveProfiles("unittest")
public class JourneyValidationTests {

    @Test
    void journeysValidated() throws Exception {
        File journeyFile = ResourceUtils.getFile("classpath:testdata/testvalidatejourneys.csv");
        List<JourneyImport> journeys = CSVReader.csvToJourneys(new FileInputStream(journeyFile));
        assertEquals(8, journeys.size());
    }

    @Test 
    void validJourneyFound() throws Exception {
        File journeyFile = ResourceUtils.getFile("classpath:testdata/testvalidatejourneys.csv");
        List<JourneyImport> journeys = CSVReader.csvToJourneys(new FileInputStream(journeyFile));
        JourneyImport journey = new JourneyImport("2021-05-31T23:50:19","2021-06-01T00:05:58",116,145,3248,935);
        assertTrue(journeys.contains(journey));
    }

    @Test 
    void journeyShortDistanceValidated() throws Exception {
        File journeyFile = ResourceUtils.getFile("classpath:testdata/testvalidatejourneys.csv");
        List<JourneyImport> journeys = CSVReader.csvToJourneys(new FileInputStream(journeyFile));
        JourneyImport shortDistance = new JourneyImport("2021-05-31T23:57:25", "2021-06-01T00:05:46", 94, 100, 0, 500);
        assertFalse(journeys.contains(shortDistance));
    }

    @Test 
    void journeyDistanceValidated() throws Exception {
        File journeyFile = ResourceUtils.getFile("classpath:testdata/testvalidatejourneys.csv");
        List<JourneyImport> journeys = CSVReader.csvToJourneys(new FileInputStream(journeyFile));
        JourneyImport invalidDistance = new JourneyImport("2021-05-31T23:57:25", "2021-06-01T00:05:46", 94, 100, -40, 500);
        assertFalse(journeys.contains(invalidDistance));
    }

    @Test 
    void journeyShortDurationValidated() throws Exception {
        File journeyFile = ResourceUtils.getFile("classpath:testdata/testvalidatejourneys.csv");
        List<JourneyImport> journeys = CSVReader.csvToJourneys(new FileInputStream(journeyFile));
        JourneyImport shortDuration = new JourneyImport("2021-05-31T23:54:48","2021-06-01T00:00:57",292,133,1713,0);
        assertFalse(journeys.contains(shortDuration));
    }

    @Test 
    void journeyDurationValidated() throws Exception {
        File journeyFile = ResourceUtils.getFile("classpath:testdata/testvalidatejourneys.csv");
        List<JourneyImport> journeys = CSVReader.csvToJourneys(new FileInputStream(journeyFile));
        JourneyImport invalidDistance = new JourneyImport("2021-05-31T23:57:25", "2021-06-01T00:05:46", 94, 100, 1000, -100);
        assertFalse(journeys.contains(invalidDistance));
    }

    @Test 
    void journeyDepartureDateValidated() throws Exception {
        File journeyFile = ResourceUtils.getFile("classpath:testdata/testvalidatejourneys.csv");
        List<JourneyImport> journeys = CSVReader.csvToJourneys(new FileInputStream(journeyFile));
        JourneyImport invalidDepatureDate = new JourneyImport("12345","2021-06-01T00:00:57",292,133,1713,500);
        assertFalse(journeys.contains(invalidDepatureDate));
    }
    
    @Test 
    void journeyReturnDateValidated() throws Exception {
        File journeyFile = ResourceUtils.getFile("classpath:testdata/testvalidatejourneys.csv");
        List<JourneyImport> journeys = CSVReader.csvToJourneys(new FileInputStream(journeyFile));
        JourneyImport invalidReturnDate = new JourneyImport("2021-06-01T00:00:57","12345",292,133,1713,500);
        assertFalse(journeys.contains(invalidReturnDate));
    }

    @Test 
    void journeyDepartureBeforeReturnValidated() throws Exception {
        File journeyFile = ResourceUtils.getFile("classpath:testdata/testvalidatejourneys.csv");
        List<JourneyImport> journeys = CSVReader.csvToJourneys(new FileInputStream(journeyFile));
        JourneyImport invalidReturnDate = new JourneyImport("2021-06-01T00:00:57","2021-05-31T23:54:48",292,133,1713,500);
        assertFalse(journeys.contains(invalidReturnDate));
    }

    @Test 
    void invalidStationId() throws Exception {
        File journeyFile = ResourceUtils.getFile("classpath:testdata/testvalidatejourneys.csv");
        List<JourneyImport> journeys = CSVReader.csvToJourneys(new FileInputStream(journeyFile));
        JourneyImport invalidDepartureId = new JourneyImport("2021-05-31T23:50:19","2021-06-01T00:05:58",-116,145,3248,935);
        JourneyImport invalidReturnId = new JourneyImport("2021-05-31T23:50:19","2021-06-01T00:05:58",116,-145,3248,935);
        assertFalse(journeys.contains(invalidDepartureId));
        assertFalse(journeys.contains(invalidReturnId));
    }
    
}
