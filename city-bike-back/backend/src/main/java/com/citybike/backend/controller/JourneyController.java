package com.citybike.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

}