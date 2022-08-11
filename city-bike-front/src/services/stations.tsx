import axios from "axios"

const baseUrl = 'http://localhost:8080/api/stations'

const getAll = async () => {
    const response = await axios.get(baseUrl)
    return response.data
}

export default { getAll }