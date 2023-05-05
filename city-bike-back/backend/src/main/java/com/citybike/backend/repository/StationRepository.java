package com.citybike.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.citybike.backend.model.Station;

@Repository
public interface StationRepository extends JpaRepository<Station, Integer> {
    Station findById(int id);
    
}
