package com.citybike.backend;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import org.springframework.util.ResourceUtils;

import com.citybike.backend.model.Journey;
import com.citybike.backend.repository.JourneyRepository;
import com.citybike.backend.service.CSVReader;

@Component
public class DatabaseRunner implements CommandLineRunner {

    @Autowired
    private JourneyRepository journeyRepository;

    @Override
    public void run(String... args) throws Exception {
        journeyRepository.deleteAll();
        File file = ResourceUtils.getFile("classpath:data/testjourneys.csv");
        try {
            List<Journey> journeys = CSVReader.csvToJourneys(new FileInputStream(file));
            journeyRepository.saveAll(journeys);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
