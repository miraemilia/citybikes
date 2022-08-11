import axios from "axios"
import { Station } from "../types"

const baseUrl = '/api/stations'

const getAll = async () => {
    const response = await axios.get(baseUrl)
    const stations : Station[] = response.data
    console.log(stations.length)
    return stations
}

export default { getAll }