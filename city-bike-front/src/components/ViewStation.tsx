import { Container, Grid, Card, CardContent } from "@mui/material"
import { useParams } from "react-router-dom";
import { useAppSelector } from "../hooks"
import { Station } from "../types"
import journeyService from "../services/journeys"
import { useEffect, useState } from "react";

const ViewStation = () => {

    const stationId = Number(useParams().id);

    const [departureCount, setDepartureCount] = useState<number | undefined>(undefined)
    const [arrivalCount, setArrivalCount] = useState<number | undefined>(undefined)
    const [topDestinations, setTopDestinations] = useState<Array<Array<number>> | undefined>(undefined)
    const [topDepartureStations, setTopDepartureStations] = useState<Array<Array<number>> | undefined>(undefined)

    useEffect(() => {
        journeyService.getStationDepartures(stationId.toString()).then(totalDepartures => setDepartureCount(totalDepartures))
        journeyService.getStationArrivals(stationId.toString()).then(totalArrivals => setArrivalCount(totalArrivals))
        journeyService.getTopFiveDestinations(stationId.toString()).then(topFiveDestinations => setTopDestinations(topFiveDestinations))
        journeyService.getTopFiveDepartureStations(stationId.toString()).then(topFiveDepartureStations => setTopDepartureStations(topFiveDepartureStations))
      }, [])
    
    const station : Station | undefined = useAppSelector(state => state.stations.value.find((station) => station.id === Number(stationId)))

    if (station === null || station === undefined) {
        return (
            <Container>
                Station not found.
            </Container>
        )
    }

    return (
        <Container maxWidth="md">
            <h2>{station.name}</h2>
            <h4>{station.address}</h4>
            <Container maxWidth="sm">
            <Grid container spacing={8}>
                <Grid item xs={6}>
                    <Card>
                        <CardContent>
                            <h1>{departureCount}</h1>
                            journeys from the station
                        </CardContent>
                    </Card>
                </Grid>
                <Grid item xs={6}>
                    <Card>
                        <CardContent>
                            <h1>{arrivalCount}</h1>
                            journeys to the station</CardContent>
                    </Card>
                </Grid>
                <Grid item xs={6}>
                    <Card>
                        <CardContent>
                            <h4>TOP 5 return stations</h4>
                            <ol>
                                {topDestinations?.map(d => (
                                    <li key={d[0]}>{d[0]} ({d[1]})</li>
                                ))}
                            </ol>
                        </CardContent>
                    </Card>
                </Grid>
                <Grid item xs={6}>
                    <Card>
                        <CardContent>
                            <h4>TOP 5 departure stations</h4>
                            <ol>
                                {topDepartureStations?.map(d => (
                                    <li key={d[0]}>{d[0]} ({d[1]})</li>
                                ))}
                            </ol>
                        </CardContent>
                    </Card>
                </Grid>
            </Grid>
            </Container>
        </Container>
    )
}

export default ViewStation