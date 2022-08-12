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

export default { getAll, getStationDepartures }