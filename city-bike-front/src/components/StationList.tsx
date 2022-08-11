import { Container } from "@mui/material"
import { useAppSelector } from "../hooks"

const StationList = () => {

    const stations = useAppSelector(state => state.stations.value)

    return (
        <Container maxWidth="md">
            <h3>Stations</h3>
            <ul>
                {stations.map((station) => (
                    <li key={station.id}>{station.name}</li>
                ))}
            </ul>
        </Container>
    )
}

export default StationList