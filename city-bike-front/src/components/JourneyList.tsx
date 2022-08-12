import { Container, Paper, Table, TableContainer, TableHead, TableBody, TableRow, TableCell } from "@mui/material"
import { useAppSelector } from '../hooks'

const JourneyList = () => {

    const journeys = useAppSelector(state => state.journeys.value)

    return (
        <Container maxWidth="md">
            <h3>Journeys</h3>
            <TableContainer component={Paper}>
                <Table>
                    <TableHead>
                        <TableRow>
                            <TableCell>From</TableCell>
                            <TableCell>To</TableCell>
                            <TableCell>Distance (m)</TableCell>
                            <TableCell>Duration (sec)</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {journeys.map((journey) => (
                            <TableRow key={journey.id} >
                                <TableCell>{journey.departureStationName}</TableCell>
                                <TableCell>{journey.returnStationName}</TableCell>
                                <TableCell>{journey.distance}</TableCell>
                                <TableCell>{journey.duration}</TableCell>
                            </TableRow>
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>
        </Container>
    )
}

export default JourneyList