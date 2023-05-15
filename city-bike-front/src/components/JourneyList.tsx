import { Container, Paper, Table, TableContainer, TableHead, TableBody, TableRow, TableCell, SelectChangeEvent } from "@mui/material"
import { ChangeEvent, useEffect, useState } from "react"
import { Journey } from "../types"
import journeyService from '../services/journeys'
import PaginationControl from "./PaginationControl"

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

    const handlePageChange = (event: ChangeEvent<unknown>, pageNumber : number) => {
        setPage(pageNumber)
    }

    const handlePerPageChange = (event: SelectChangeEvent<number>) => {
        setPerPage(event.target.value as number)
        setPage(1)
    }

    return (
        <Container maxWidth="md">
            <h3>Journeys</h3>
            <PaginationControl 
                handlePageChange={handlePageChange}
                handlePerPageChange={handlePerPageChange}
                perPage={perPage}
                pageCount={pageCount}
            />
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
                                <TableCell>{journey.departureStation.name}</TableCell>
                                <TableCell>{journey.returnStation.name}</TableCell>
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