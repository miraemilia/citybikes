package com.citybike.backend;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

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

        String resource = "classpath:data/testjourneys.csv";

        stationRepository.deleteAll();
        File stationFile = ResourceUtils.getFile("classpath:data/stations.csv");
        try {
            List<Station> stations = CSVReader.csvToStations(new FileInputStream(stationFile));
            stationRepository.saveAll(stations);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (activeProfiles.length > 0) {
            System.out.println(activeProfiles[0]);
            if (activeProfiles[0].equals("dev")) {
                resource = "classpath:data/2021-05-31.csv";
            } else if (activeProfiles[0].equals("prod")){
                resource = "classpath:data/2021-05-31.csv";
            }
        }
        System.out.println(resource);
     

        journeyRepository.deleteAll();
        File file = ResourceUtils.getFile(resource);
        ArrayList<String[]> journeyData = CSVReader.csvToJourneys(new FileInputStream(file));
        insertJourneys(journeyData);
    }

    @Transactional
    public void insertJourneys (ArrayList<String[]> journeyData) {
        for (String[] j : journeyData) {
            try {
                journeyRepository.save(new Journey(
                    j[0],
                    j[1],
                    stationRepository.findById(Integer.parseInt(j[2])),
                    stationRepository.findById(Integer.parseInt(j[3])),
                    Integer.parseInt(j[4]),
                    Integer.parseInt(j[5])
                ));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
