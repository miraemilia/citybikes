package com.citybike.backend.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.citybike.backend.model.Journey;

@Repository
public interface JourneyRepository extends JpaRepository<Journey, Long> {
     Page<Journey> findAll(Pageable pageable);
     int countByDepartureStationId (int id);
     int countByReturnStationId(int id);

     @Query("SELECT AVG(j.distance) FROM Journey j WHERE j.departureStation.id = :id")
     double averageDistanceFrom(@Param("id") int id);

     @Query("SELECT AVG(j.distance) FROM Journey j WHERE j.returnStation.id = :id")
     double averageDistanceTo(@Param("id") int id);

     @Query("SELECT j.returnStation.id, j.returnStation.name, COUNT(j) AS returncount FROM Journey j WHERE j.departureStation.id = :id GROUP BY j.returnStation.id ORDER BY returncount DESC")
     List<Object[]> topDestinations(@Param("id") int id, Pageable pageable);

     @Query("SELECT j.departureStation.id, j.departureStation.name, COUNT(j) AS departurecount FROM Journey j WHERE j.returnStation.id = :id GROUP BY j.departureStation.id ORDER BY departurecount DESC")
     List<Object[]> topDepartureStations(@Param("id") int id, Pageable pageable);

}
