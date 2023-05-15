import { Container, Pagination, FormControl, InputLabel, Select, MenuItem, SelectChangeEvent } from "@mui/material"
import { ChangeEvent } from "react"

type PaginationProps = {
    handlePageChange: (event: ChangeEvent<unknown>, pageNumber: number) => void,
    handlePerPageChange: (event: SelectChangeEvent<number>) => void,
    perPage: number,
    pageCount: number
}

const PaginationControl = ({ handlePageChange, handlePerPageChange, perPage, pageCount } : PaginationProps) => {

    return (
        <Container>
            <FormControl id = "selectPerPage" variant="standard" sx={{ m: 1, minWidth: 120 }}>
                <InputLabel>Journeys per page</InputLabel>
                <Select
                    inputProps={{"data-testid" : "selectPerPage"}}
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
            <Pagination 
            id = "pagination"
            count={pageCount}
            showFirstButton
            showLastButton
            onChange={handlePageChange}/>
        </Container>
    )
}

export default PaginationControl