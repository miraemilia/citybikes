package com.citybike.backend;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public void run(String... args) throws Exception {

        stationRepository.deleteAll();
        File stationFile = ResourceUtils.getFile("classpath:data/stations.csv");
        try {
            List<Station> stations = CSVReader.csvToStations(new FileInputStream(stationFile));
            stationRepository.saveAll(stations);
        } catch (Exception e) {
            e.printStackTrace();
        }

        journeyRepository.deleteAll();
        File file = ResourceUtils.getFile("classpath:data/testjourneys.csv");
        try {
            ArrayList<String[]> journeyData = CSVReader.csvToJourneys(new FileInputStream(file));
            ArrayList<Journey> journeys = new ArrayList<>();
            for (String[] j : journeyData) {
                Station dStation = stationRepository.findById(Integer.parseInt(j[2]));
                Station rStation = stationRepository.findById(Integer.parseInt(j[3]));
                Journey journey = new Journey(
                    j[0],
                    j[1],
                    dStation,
                    rStation,
                    Integer.parseInt(j[4]),
                    Integer.parseInt(j[5])
                );
                journeys.add(journey);
            }
            journeyRepository.saveAll(journeys);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
