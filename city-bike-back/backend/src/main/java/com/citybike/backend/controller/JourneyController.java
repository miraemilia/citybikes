package com.citybike.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.citybike.backend.model.Journey;
import com.citybike.backend.repository.JourneyRepository;
import com.citybike.backend.util.Helper;
import com.citybike.backend.util.TopListItem;

@RestController
public class JourneyController {

    @Autowired
    private JourneyRepository journeyRepository;

    @GetMapping("/api/journeys/{page}")
    public Page<Journey> allJourneysByPage(@RequestParam int perPage, @PathVariable int page){
        Pageable journeyPage = PageRequest.of(page, perPage);
        return journeyRepository.findAll(journeyPage);
    }

    @GetMapping("/api/journeys/totalDepartures")
    public int totalStationDepartures(@RequestParam int stationId) {
        int total = journeyRepository.countByDepartureStationId(stationId);
        if (total == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No journeys found.");
        } else {
            return total;
        }
    }

    @GetMapping("/api/journeys/totalArrivals")
    public int totalStationArrivals(@RequestParam int stationId) {
        int total = journeyRepository.countByReturnStationId(stationId);
        if (total == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No journeys found.");
        } else {
            return total;
        }
    }

    @GetMapping("/api/journeys/averageDistanceFrom")
    public double getAverageDistanceFrom(@RequestParam int stationId) {
        double average = journeyRepository.averageDistanceFrom(stationId);
        if (average == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No journeys found.");
        } else {
            return average;
        }
    }

    @GetMapping("/api/journeys/averageDistanceTo")
    public double getAverageDistanceTo(@RequestParam int stationId) {
        double average = journeyRepository.averageDistanceTo(stationId);
        if (average == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No journeys found.");
        } else {
            return average;
        }
    }


    @GetMapping("/api/journeys/topDestinations")
    public List<TopListItem> topFiveDestinations(@RequestParam int stationId) {
        Pageable topFive = PageRequest.of(0, 5);
        List<Object[]> topDestinations = journeyRepository.topDestinations(stationId, topFive);
        if (topDestinations.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No journeys found.");
        } else {
        List<TopListItem> topList = Helper.topListObjectArrayToTopListItem(topDestinations);
        return topList;
        }
    }

    @GetMapping("/api/journeys/topDepartureStations")
    public List<TopListItem> topFiveDepartureStations(@RequestParam int stationId) {
        Pageable topFive = PageRequest.of(0, 5);
        List<Object[]> topDepartures = journeyRepository.topDepartureStations(stationId, topFive);
        if (topDepartures.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No journeys found.");
        } else {
        List<TopListItem> topList = Helper.topListObjectArrayToTopListItem(topDepartures);
        return topList;
        }
    }
}