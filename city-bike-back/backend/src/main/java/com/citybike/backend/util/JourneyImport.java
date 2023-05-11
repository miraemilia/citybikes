package com.citybike.backend.util;

public class JourneyImport {
    public String departureDate;
    public String returnDate;
    public int departureStationId;
    public int returnStationId;
    public int distance;
    public int duration;

    public JourneyImport(String departureDate, String returnDate, int departureStationId, int returnStationId, int distance, int duration) {
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        this.departureStationId = departureStationId;
        this.returnStationId = returnStationId;
        this.distance = distance;
        this.duration = duration;
    }

    public boolean equals(Object obj){
        JourneyImport journey = (JourneyImport) obj;
        boolean status = false;
        if(this.departureDate.equals(journey.departureDate)
            && this.returnDate.equals(journey.returnDate)
            && this.departureStationId == journey.departureStationId
            && this.returnStationId == journey.returnStationId
            && this.distance == journey.distance
            && this.duration == journey.duration
            ) {
            status = true;
        }
        return status;
    }
}
