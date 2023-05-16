import { Container, Link } from "@mui/material"
import { useAppSelector } from "../hooks"
import { useState } from "react"

const StationList = () => {

    const stations = useAppSelector(state => state.stations.value)
    const [filterString, setFilterString] = useState('')

    return (
        <Container maxWidth="md">
            <h3>Stations listing</h3>

            <label htmlFor="filter">Set filter:</label>
            <input
                id="filter"
                value={filterString}
                onChange={event => setFilterString(event.target.value)}
            />

            <ul>
                {stations.filter(station => station.name.includes(filterString)).map((filteredStation) => (
                    <li key={filteredStation.id}>
                        <Link href={`/stations/${filteredStation.id}`}>{filteredStation.name}</Link>
                    </li>
                ))}
            </ul>
        </Container>
    )
}

export default StationList