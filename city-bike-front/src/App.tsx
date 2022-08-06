import { Container } from '@mui/material';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Header from './components/Header'
import JourneyList from './components/JourneyList';
import Station from './components/Station';
import StationList from './components/StationList';


const App = () => {
  return (
    <Container>
      <Header />
      <BrowserRouter>
        <Routes>
          <Route path='journeys' element={<JourneyList />} />
          <Route path='stations' element={<StationList />} />
          <Route path='/stations/:id' element={<Station />} />
        </Routes>
      </BrowserRouter>
    </Container>
  );
}

export default App;
