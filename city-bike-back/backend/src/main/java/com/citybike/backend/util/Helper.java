package com.citybike.backend.util;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.citybike.backend.model.Station;

public class Helper {

    public static List<TopListItem> topListObjectArrayToTopListItem(List<Object[]> topObjectList) {
        List<TopListItem> topList = new ArrayList<TopListItem>();
        for (Object[] resultObject : topObjectList) {
            Integer id = (Integer) resultObject[0];
            String name = (String) resultObject[1];
            Long countLong = (Long) resultObject[2];
            Integer count = countLong.intValue();
            TopListItem topListItem = new TopListItem(id, name, count);
            topList.add(topListItem);
        }
        return topList;
    }

    public static boolean stationIsValid(Station station) {
        if (!intIsPositive(station.getId())) {
            return false;
        }
        if (!stringIsLongitude(station.getX())) {
            return false;
        }
        if (!stringIsLatitude(station.getY())) {
            return false;
        }
        return true;
    }

    public static boolean journeyImportIsValid(JourneyImport journey) {
        if (!stringIsDate(journey.departureDate)) {
            return false;
        }
        if (!stringIsDate(journey.returnDate)) {
            return false;
        }
        if (!departureBeforeReturn(journey.departureDate, journey.returnDate)) {
            return false;
        }
        if (!intIsPositive(journey.departureStationId)) {
            return false;
        }
        if (!intIsPositive(journey.returnStationId)) {
            return false;
        }
        if (!intIsPositive(journey.distance) || !intIsMoreThanTen(journey.distance)) {
            return false;
        }
        if (!intIsPositive(journey.duration) || !intIsMoreThanTen(journey.duration)) {
            return false;
        }
        return true;
    }

    public static boolean stringIsLongitude(String xCoordinateString) {
        double xCoordinate;
        try {
            xCoordinate = Double.parseDouble(xCoordinateString);
        } catch (Exception e) {
            return false;
        }
        if (xCoordinate < -180 || xCoordinate > 180) {
            return false;
        }
        return true;
    }

    public static boolean stringIsLatitude(String yCoordinateString) {
        double yCoordinate;
        try {
            yCoordinate = Double.parseDouble(yCoordinateString);
        } catch (Exception e) {
            return false;
        }
        if (yCoordinate < -90 || yCoordinate > 90) {
            return false;
        }
        return true;
    }

    public static boolean stringIsDate(String journeyDate) {
        try {
            LocalDateTime.parse(journeyDate);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static boolean departureBeforeReturn(String departureDate, String returnDate) {
        return LocalDateTime.parse(returnDate).isAfter(LocalDateTime.parse(departureDate));
    }

    public static boolean intIsPositive(int number) {
        if (number < 0) {
            return false;
        }
        return true;
    }

    public static boolean intIsMoreThanTen(int number) {
        if (number >= 10) {
            return true;
        }
        return false;
    }
    
}
