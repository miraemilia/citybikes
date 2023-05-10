package com.citybike.backend;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.citybike.backend.repository.JourneyRepository;
import com.citybike.backend.util.Helper;
import com.citybike.backend.util.TopListItem;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class JourneyRepositoryTests {

    @Autowired
    private JourneyRepository journeyRepository;

    //The expected values for the following tests are based on journeys (6) from and to 547/Jämeräntaival in the file testjourneys.csv 
    //(saved to database at project start up with "test" profile)

    //547,Jämeräntaival,547,Jämeräntaival,1227
    //547,Jämeräntaival,547,Jämeräntaival,1872
    //547,Jämeräntaival,547,Jämeräntaival,739
    //547,Jämeräntaival,533,Tekniikantie,1128
    //529,Keilaniemi (M),547,Jämeräntaival,1733
    //553,Metsänneidonpolku,547,Jämeräntaival,2200

    @Test
    public void totalDeparturesCorrect() throws Exception {
        int totalDeparturesFrom547 = journeyRepository.countByDepartureStationId(547);
        assertEquals(4, totalDeparturesFrom547);
    }

    @Test
    public void totalArrivalsCorrect() throws Exception {
        int totalArrivalsTo547 = journeyRepository.countByReturnStationId(547);
        assertEquals(5, totalArrivalsTo547);
    }

    @Test
    public void averageDistanceFromCorrect() throws Exception {
        double averageDistanceFrom547 = journeyRepository.averageDistanceFrom(547);
        int[] journeysFrom = {1227, 1872, 739, 1128};
        double sum = 0;
        for (int distance : journeysFrom) {
            sum += distance;
        }
        double average = sum/journeysFrom.length;
        assertEquals(average, averageDistanceFrom547);
    }

    @Test
    public void averageDistanceToCorrect() throws Exception {
        double averageDistanceTo547 = journeyRepository.averageDistanceTo(547);
        int[] journeysTo = {1227, 1872, 739, 1733, 2200};
        double sum = 0;
        for (int distance : journeysTo) {
            sum += distance;
        }
        double average = sum/journeysTo.length;
        assertEquals(average, averageDistanceTo547);
    }
    
    @Test
    public void topFiveDestinationsCorrect() throws Exception {
        Pageable pageable = PageRequest.of(0, 5);
        List<Object[]> topDestinations = journeyRepository.topDestinations(547, pageable);
        List<TopListItem> topFiveFromDatabase = Helper.topListObjectArrayToTopListItem(topDestinations);
        List<TopListItem> topFiveManually = new ArrayList<TopListItem>();
        topFiveManually.add(new TopListItem(547, "Jämeräntaival", 3));
        topFiveManually.add(new TopListItem(533, "Tekniikantie", 1));
        assertEquals(topFiveManually, topFiveFromDatabase);
    }

    @Test
    public void topFiveDepartureStationsCorrect() throws Exception {
        Pageable pageable = PageRequest.of(0, 5);
        List<Object[]> topDepartureStations = journeyRepository.topDepartureStations(547, pageable);
        List<TopListItem> topFiveFromDatabase = Helper.topListObjectArrayToTopListItem(topDepartureStations);
        List<TopListItem> topFiveManually = new ArrayList<TopListItem>();
        topFiveManually.add(new TopListItem(547, "Jämeräntaival", 3));
        topFiveManually.add(new TopListItem(529, "Keilaniemi (M)", 1));
        topFiveManually.add(new TopListItem(553, "Metsänneidonpolku", 1));
        assertEquals(topFiveManually, topFiveFromDatabase);
    }
}
