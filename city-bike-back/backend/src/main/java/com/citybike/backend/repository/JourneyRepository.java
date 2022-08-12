package com.citybike.backend.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.citybike.backend.model.Journey;

public interface JourneyRepository extends JpaRepository<Journey, Long> {
     // add pagination
     int countByDepartureStation(int id);
     int countByReturnStation(int id);

     @Query("SELECT j.returnStation, j.returnStationName, COUNT(j) AS returncount FROM Journey j WHERE j.departureStation = :id GROUP BY j.returnStation ORDER BY returncount DESC")
     List<Object[]> topDestinations(@Param("id") int id, Pageable pageable);

     @Query("SELECT j.departureStation, j.departureStationName, COUNT(j) AS departurecount FROM Journey j WHERE j.returnStation = :id GROUP BY j.departureStation ORDER BY departurecount DESC")
     List<Object[]> topDepartureStations(@Param("id") int id, Pageable pageable);
}
