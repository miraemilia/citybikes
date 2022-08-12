import { Container } from "@mui/material"
import { useParams } from "react-router-dom";
import { useAppSelector } from "../hooks"
import { Station } from "../types"

const ViewStation = () => {

    const stationId = Number(useParams().id);

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
            <h3>{station.name}</h3>
        </Container>
    )
}

export default ViewStation