import { Container, Link } from "@mui/material"
import { useAppSelector } from "../hooks"

const StationList = () => {

    const stations = useAppSelector(state => state.stations.value)

    return (
        <Container maxWidth="md">
            <h3>Stations listing</h3>
            <ul>
                {stations.map((station) => (
                    <li key={station.id}>
                        <Link href={`/stations/${station.id}`}>{station.name}</Link>
                    </li>
                ))}
            </ul>
        </Container>
    )
}

export default StationList