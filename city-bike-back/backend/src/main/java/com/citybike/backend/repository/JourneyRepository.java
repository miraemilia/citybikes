package com.citybike.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.citybike.backend.model.Journey;

public interface JourneyRepository extends JpaRepository<Journey, Long> {
     // add pagination
}
