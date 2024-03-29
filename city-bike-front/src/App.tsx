import { Container } from '@mui/material';
import { useEffect } from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';

import Header from './components/Header'
import JourneyList from './components/JourneyList';
import ViewStation from './components/ViewStation';
import StationList from './components/StationList';

import { useAppDispatch } from './hooks';
import stationService from './services/stations'
import Home from './components/Home';
import { setStations } from './reducers/stationReducer';



const App = () => {

  const dispatch = useAppDispatch();
  
  useEffect(() => {
    stationService.getAll().then(stations => dispatch(setStations(stations)))
  }, [dispatch])

  return (
    <Container>
      <Header />
      <BrowserRouter>
        <Routes>
          <Route path='journeys' element={<JourneyList />} />
          <Route path='stations' element={<StationList />} />
          <Route path='/stations/:id' element={<ViewStation />} />
          <Route path='/' element={<Home />} />
        </Routes>
      </BrowserRouter>
    </Container>
  );
}

export default App;
