import axios from "axios"
import { Journey } from "../types"

const baseUrl = '/api/journeys'

const getAll = async () => {
    const response = await axios.get(baseUrl)
    const journeys : Journey[] = response.data.content
    console.log(journeys.length)
    return journeys
}

const getJourneyPage = async (perPage : number, page : number) => {
    const response = await axios.get(baseUrl + `/${perPage}/${page}`)
    const journeys : Journey[] = response.data.content
    const totalPages : number = response.data.totalPages
    console.log(journeys.length, totalPages)
    const pageInfo = { journeys: journeys, totalPages: totalPages}
    return pageInfo
}

const getStationDepartures = async (stationId : string) => {
    const response = await axios.get(baseUrl + `/${stationId}/totalDepartures`)
    console.log("departures:", response.data)
    const totalDepartures : number = response.data
    return totalDepartures
}

const getAverageFrom = async (stationId : string) => {
    const response = await axios.get(baseUrl + `/${stationId}/averageDistanceFrom`)
    console.log("average distance from:", response.data)
    const average : number = response.data
    return average
}

const getAverageTo = async (stationId : string) => {
    const response = await axios.get(baseUrl + `/${stationId}/averageDistanceTo`)
    console.log("average distance to:", response.data)
    const average : number = response.data
    return average
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

export default { getAll, getJourneyPage, getStationDepartures, getStationArrivals, getTopFiveDestinations, getTopFiveDepartureStations, getAverageFrom, getAverageTo }