import { Container } from "@mui/material"

const Home = () => {
    return (
        <Container maxWidth="md">
            <h3>Info</h3>
            <p>This app displays data on journeys made with city bikes in the Helsinki Capital area.</p>
            <p>You can choose a station from the stations list and see data on journeys from and to the station on May 31st 2021. The journeys page lists 100 journeys made on that day.</p>
        </Container>
    )
}

export default Home