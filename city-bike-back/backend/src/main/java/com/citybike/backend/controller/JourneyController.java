package com.citybike.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.citybike.backend.model.Journey;
import com.citybike.backend.repository.JourneyRepository;
import com.citybike.backend.util.Helper;
import com.citybike.backend.util.TopListItem;

@RestController
public class JourneyController {

    @Autowired
    private JourneyRepository journeyRepository;

    @GetMapping("/api/journeys/{perPage}/{page}")
    public Page<Journey> allJourneysByPage(@PathVariable int perPage, @PathVariable int page){
        Pageable journeyPage = PageRequest.of(page, perPage);
        return journeyRepository.findAll(journeyPage);
    }

    @GetMapping("/api/journeys/{stationId}/totalDepartures")
    public int totalStationDepartures(@PathVariable int stationId) {
        return journeyRepository.countByDepartureStationId(stationId);
    }

    @GetMapping("/api/journeys/{stationId}/totalArrivals")
    public int totalStationArrivals(@PathVariable int stationId) {
        return journeyRepository.countByReturnStationId(stationId);
    }

    @GetMapping("/api/journeys/{stationId}/averageDistanceFrom")
    public double getAverageDistanceFrom(@PathVariable int stationId) {
        return journeyRepository.averageDistanceFrom(stationId);
    }

    @GetMapping("/api/journeys/{stationId}/averageDistanceTo")
    public double getAverageDistanceTo(@PathVariable int stationId) {
        return journeyRepository.averageDistanceTo(stationId);
    }


    @GetMapping("/api/journeys/{stationId}/topDestinations")
    public List<TopListItem> topFiveDestinations(@PathVariable int stationId) {
        Pageable topFive = PageRequest.of(0, 5);
        List<Object[]> topDestinations = journeyRepository.topDestinations(stationId, topFive);
        List<TopListItem> topList = Helper.topListObjectArrayToTopListItem(topDestinations);
        return topList;
    }

    @GetMapping("/api/journeys/{stationId}/topDepartureStations")
    public List<TopListItem> topFiveDepartureStations(@PathVariable int stationId) {
        Pageable topFive = PageRequest.of(0, 5);
        List<Object[]> topDepartures = journeyRepository.topDepartureStations(stationId, topFive);
        List<TopListItem> topList = Helper.topListObjectArrayToTopListItem(topDepartures);
        return topList;
    }
}