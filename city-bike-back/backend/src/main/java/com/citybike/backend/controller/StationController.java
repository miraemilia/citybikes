package com.citybike.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.citybike.backend.model.Station;
import com.citybike.backend.repository.StationRepository;

@RestController
public class StationController {
    
    @Autowired
    private StationRepository stationRepository;

    @GetMapping("/api/stations")
    public List<Station> allStations(){
        return stationRepository.findAll();
    }

    @GetMapping("/api/stations/{id}")
    public Station individualStation(@PathVariable int id){
        Station station = stationRepository.findById(id);
        if (station == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Station non-existent.");
        }
        else {
            return stationRepository.findById(id);
        }

    }
}