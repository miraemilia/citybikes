package com.citybike.backend.util;

public class JourneyImport {
    public String departureDate;
    public String returnDate;
    public int departureStationId;
    public int returnStationId;
    public int distance;
    public int duration;

    public JourneyImport(String dDate, String rDate, int dId, int rId, int dist, int dur) {
        departureDate = dDate;
        returnDate = rDate;
        departureStationId = dId;
        returnStationId = rId;
        distance = dist;
        duration = dur;
    }
}
