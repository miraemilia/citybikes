import axios from "axios"
import { Journey } from "../types"

const baseUrl = '/api/journeys'

const getAll = async () => {
    const response = await axios.get(baseUrl)
    const journeys : Journey[] = response.data
    console.log(journeys.length)
    return journeys
}

export default { getAll }