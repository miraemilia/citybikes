import { Container, Paper, Table, TableContainer, TableHead, TableBody, TableRow, TableCell, Pagination, FormControl, InputLabel, Select, MenuItem, SelectChangeEvent } from "@mui/material"
import { SetStateAction, useEffect, useState } from "react"
import { Journey } from "../types"
import journeyService from '../services/journeys'

const JourneyList = () => {

    const [ journeys, setJourneys ] = useState<Array<Journey>>([])
    const [ page, setPage ] = useState<number>(1)
    const [ perPage, setPerPage ] = useState<number>(25)    
    const [ pageCount, setPageCount ] = useState<number>(0)

    useEffect(() => {
        journeyService.getJourneyPage(perPage, page-1).then(pageInfo => {
            setJourneys(pageInfo.journeys)
            setPageCount(pageInfo.totalPages)
        })
    }, [page, perPage])

    const handlePageChange = (event: any, value: SetStateAction<number>) => {
        setPage(value)
    }

    const handlePerPageChange = (event: SelectChangeEvent<number>) => {
        setPerPage(event.target.value as number)
    }

    return (
        <Container maxWidth="md">
            <h3>Journeys</h3>
            <FormControl variant="standard" sx={{ m: 1, minWidth: 120 }}>
                <InputLabel >Journeys per page</InputLabel>
                <Select
                    value={perPage}
                    onChange={handlePerPageChange}
                    label="JourneysPerPage"
                >
                    <MenuItem value={25}>25</MenuItem>
                    <MenuItem value={50}>50</MenuItem>
                    <MenuItem value={75}>75</MenuItem>
                    <MenuItem value={100}>100</MenuItem>
                </Select>
            </FormControl>
            <Pagination count={pageCount} showFirstButton showLastButton onChange={handlePageChange}/>
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