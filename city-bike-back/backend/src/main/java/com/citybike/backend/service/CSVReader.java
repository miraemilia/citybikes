package com.citybike.backend.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import com.citybike.backend.model.Journey;
import com.citybike.backend.model.Station;

public class CSVReader {
  
    public static List<Journey> csvToJourneys(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
            List<Journey> journeys = new ArrayList<Journey>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            for (CSVRecord csvRecord: csvRecords) {
                try {
                    if (Integer.parseInt(csvRecord.get("Covered distance (m)")) >= 10 && Integer.parseInt(csvRecord.get("Duration (sec.)")) >=10) {
                        Journey journey = new Journey(
                            csvRecord.get("Departure"),
                            csvRecord.get("Return"),
                            Integer.parseInt(csvRecord.get("Departure station id")),
                            csvRecord.get("Departure station name"),
                            Integer.parseInt(csvRecord.get("Return station id")),
                            csvRecord.get("Return station name"),
                            Integer.parseInt(csvRecord.get("Covered distance (m)")),
                            Integer.parseInt(csvRecord.get("Duration (sec.)"))
                        );
                        journeys.add(journey);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        return journeys;
        } catch (IOException e) {
            throw new RuntimeException("failed to parse CSV file: " + e.getMessage());
        }
    }

    public static List<Station> csvToStations(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
            List<Station> stations = new ArrayList<Station>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            for (CSVRecord csvRecord: csvRecords) {
                try {
                    Station station = new Station(
                        Integer.parseInt(csvRecord.get("ID")),
                        csvRecord.get("Name"),
                        csvRecord.get("Osoite"),
                        csvRecord.get("x"),
                        csvRecord.get("y")
                    );
                    stations.add(station);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return stations;
            } catch (IOException e) {
                throw new RuntimeException("failed to parse CSV file: " + e.getMessage());
            }
    }
}