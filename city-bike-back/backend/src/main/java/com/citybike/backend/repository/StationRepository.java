package com.citybike.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.citybike.backend.model.Station;

public interface StationRepository extends JpaRepository<Station, Integer> {
    Station findById(int id);
    
}
