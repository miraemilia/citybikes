package com.citybike.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.citybike.backend.model.Journey;
import com.citybike.backend.repository.JourneyRepository;

@RestController
public class JourneyController {

    @Autowired
    private JourneyRepository journeyRepository;

    @GetMapping("/api/journeys")
    public List<Journey> allJourneys(){
        return journeyRepository.findAll();
    }

    @GetMapping("/api/journeys/{stationId}/totalDepartures")
    public int totalStationDepartures(@PathVariable int stationId) {
        return journeyRepository.countByDepartureStation(stationId);
    }

    @GetMapping("/api/journeys/{stationId}/totalArrivals")
    public int totalStationArrivals(@PathVariable int stationId) {
        return journeyRepository.countByReturnStation(stationId);
    }

    @GetMapping("/api/journeys/{stationId}/topDestinations")
    public List<Object[]> topFiveDestinations(@PathVariable int stationId) {
        Pageable topFive = PageRequest.of(0, 5);
        return journeyRepository.topDestinations(stationId, topFive);
    }

    @GetMapping("/api/journeys/{stationId}/topDepartureStations")
    public List<Object[]> topFiveDepartureStations(@PathVariable int stationId) {
        Pageable topFive = PageRequest.of(0, 5);
        return journeyRepository.topDepartureStations(stationId, topFive);
    }
}