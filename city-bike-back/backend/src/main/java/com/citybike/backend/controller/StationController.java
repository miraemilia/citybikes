package com.citybike.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
        return stationRepository.findById(id);
    }
}