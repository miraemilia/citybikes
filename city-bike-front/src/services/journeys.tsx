import axios from "axios"
import { Journey } from "../types"

const baseUrl = '/api/journeys'

const getAll = async () => {
    const response = await axios.get(baseUrl)
    const journeys : Journey[] = response.data
    console.log(journeys.length)
    return journeys
}

const getStationDepartures = async (stationId : string) => {
    const response = await axios.get(baseUrl + `/${stationId}/totalDepartures`)
    console.log("departures:", response.data)
    const totalDepartures : number = response.data
    return totalDepartures
}

const getStationArrivals = async (stationId : string) => {
    const response = await axios.get(baseUrl + `/${stationId}/totalArrivals`)
    console.log("arrivals:", response.data)
    const totalArrivals : number = response.data
    return totalArrivals
}

const getTopFiveDestinations = async (stationId : string) => {
    const response = await axios.get(baseUrl + `/${stationId}/topDestinations`)
    console.log("destinations:", response.data)
    const topDestinations : Array<Array<string>> = response.data
    return topDestinations
}

const getTopFiveDepartureStations = async (stationId : string) => {
    const response = await axios.get(baseUrl + `/${stationId}/topDepartureStations`)
    console.log("departure stations:", response.data)
    const topDepartureStations : Array<Array<string>> = response.data
    return topDepartureStations
}

export default { getAll, getStationDepartures, getStationArrivals, getTopFiveDestinations, getTopFiveDepartureStations }