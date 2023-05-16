package com.citybike.backend;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import com.citybike.backend.model.Journey;
import com.citybike.backend.model.Station;
import com.citybike.backend.repository.JourneyRepository;
import com.citybike.backend.repository.StationRepository;
import com.citybike.backend.service.CSVReader;
import com.citybike.backend.util.JourneyImport;

@Component
public class DatabaseRunner implements CommandLineRunner {

    @Autowired
    private JourneyRepository journeyRepository;

    @Autowired
    private StationRepository stationRepository;

    @Value("${spring.profiles.active:}")
    private String[] activeProfiles; 

    @Override
    public void run(String... args) throws Exception {

        String resource = "classpath:testdata/testjourneys.csv";

        if (activeProfiles.length > 0) {
            System.out.println(activeProfiles[0]);
            if (activeProfiles[0].equals("dev")) {
                resource = "classpath:data/2021-05-31.csv";
            } else if (activeProfiles[0].equals("prod")){
                resource = "classpath:data/2021-05-31.csv"; //change file after solving database init speed up
            }
        }
        System.out.println(resource);

        stationRepository.deleteAll();
        File stationFile = ResourceUtils.getFile("classpath:data/stations.csv");
        try {
            List<Station> stations = CSVReader.csvToStations(new FileInputStream(stationFile));
            stationRepository.saveAll(stations);
        } catch (Exception e) {
            e.printStackTrace();
        }

        journeyRepository.deleteAll();
        File file = ResourceUtils.getFile(resource);
        List<JourneyImport> journeyData = CSVReader.csvToJourneys(new FileInputStream(file));
        insertJourneys(journeyData);
    }

    public void insertJourneys (List<JourneyImport> journeyData) {
        for (JourneyImport j : journeyData) {
            try {
                journeyRepository.save(new Journey(
                    j.departureDate,
                    j.returnDate,
                    stationRepository.findById(j.departureStationId),
                    stationRepository.findById(j.returnStationId),
                    j.distance,
                    j.duration
                ));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
