import { Container, Grid, Card, CardContent } from "@mui/material"
import { useParams } from "react-router-dom";
import { useAppSelector } from "../hooks"
import { Station, TopListItem } from "../types"
import journeyService from "../services/journeys"
import { useEffect, useState } from "react";

const ViewStation = () => {

    const stationId = Number(useParams().id);

    const [departureCount, setDepartureCount] = useState<number | undefined>(undefined)
    const [arrivalCount, setArrivalCount] = useState<number | undefined>(undefined)
    const [topDestinations, setTopDestinations] = useState<TopListItem[] | undefined>(undefined)
    const [topDepartureStations, setTopDepartureStations] = useState<TopListItem[] | undefined>(undefined)
    const [averageFrom, setAverageFrom] = useState<number | undefined>(undefined)
    const [averageTo, setAverageTo] = useState<number | undefined>(undefined)

    useEffect(() => {
        journeyService.getStationDepartures(stationId.toString()).then(totalDepartures => setDepartureCount(totalDepartures))
        journeyService.getStationArrivals(stationId.toString()).then(totalArrivals => setArrivalCount(totalArrivals))
        journeyService.getTopFiveDestinations(stationId.toString()).then(topFiveDestinations => setTopDestinations(topFiveDestinations))
        journeyService.getTopFiveDepartureStations(stationId.toString()).then(topFiveDepartureStations => setTopDepartureStations(topFiveDepartureStations))
        journeyService.getAverageFrom(stationId.toString()).then(averageF => setAverageFrom(averageF))
        journeyService.getAverageTo(stationId.toString()).then(averageT => setAverageTo(averageT))
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
            <Container maxWidth="sm">
                <h1></h1>
            <Grid container columnSpacing={8} rowSpacing={4}>
                <Grid item id="stationInfo"  xs={12}>
                    <Card style={{alignContent: 'center', alignItems: 'center'}}>
                        <CardContent>
                            <h2>{station.name}</h2>
                            <h4>{station.address}</h4>
                        </CardContent>
                    </Card>
                </Grid>
                <Grid item id="departureCount" xs={6}>
                    <Card style={{backgroundColor: "lightgrey"}}>
                        <CardContent>
                            <h1>{departureCount}</h1>
                            journeys from the station
                        </CardContent>
                    </Card>
                </Grid>
                <Grid item id="arrivalCount" xs={6}>
                    <Card style={{backgroundColor: "lightgrey"}}>
                        <CardContent>
                            <h1>{arrivalCount}</h1>
                            journeys to the station</CardContent>
                    </Card>
                </Grid>
                <Grid item id="top5ReturnStations" xs={6}>
                    <Card style={{backgroundColor: "lightgrey"}}>
                        <CardContent>
                            <h4>TOP 5 return stations</h4>
                            <ol>
                                {topDestinations?.map(d => (
                                    <li key={d.id}>{d.name} ({d.count})</li>
                                ))}
                            </ol>
                        </CardContent>
                    </Card>
                </Grid>
                <Grid item id="top5DepartureStations" xs={6}>
                    <Card style={{backgroundColor: "lightgrey"}}>
                        <CardContent>
                            <h4>TOP 5 departure stations</h4>
                            <ol>
                                {topDepartureStations?.map(d => (
                                    <li key={d.id}>{d.name} ({d.count})</li>
                                ))}
                            </ol>
                        </CardContent>
                    </Card>
                </Grid>
                <Grid item id="averageFrom" xs={6}>
                    <Card style={{backgroundColor: "lightgrey"}}>
                        <CardContent>
                            <h1>{averageFrom?.toFixed(0)} m</h1>
                            average travel distance from here
                        </CardContent>
                    </Card>
                </Grid>
                <Grid item id="averageTo" xs={6}>
                    <Card style={{backgroundColor: "lightgrey"}}>
                        <CardContent>
                            <h1>{averageTo?.toFixed(0)} m</h1>
                            average travel distance to here
                        </CardContent>
                    </Card>
                </Grid>
            </Grid>
            </Container>
        </Container>
    )
}

export default ViewStation