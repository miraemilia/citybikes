import { Container, AppBar, Button, Toolbar } from "@mui/material"

const Header = () => {
    return (
        <Container>
            <h1>Helsinki city bike app</h1>
            <AppBar position="static">
                <Toolbar>
                    <Button href="/" variant="contained" color="primary">
                        Home
                    </Button>
                    <Button href="/stations" variant="contained" color="primary">
                        Stations
                    </Button>
                    <Button href="/journeys" variant="contained" color="primary">
                        Journeys
                    </Button>
                </Toolbar>        
            </AppBar>
        </Container>
    )
}

export default Header