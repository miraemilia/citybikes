import { Container } from '@mui/material';
import { useEffect } from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';

import Header from './components/Header'
import JourneyList from './components/JourneyList';
import Station from './components/Station';
import StationList from './components/StationList';

import { useAppDispatch } from './hooks';
import journeyService from './services/journeys'
import { setJourneys } from './reducers/journeyReducer';
import Home from './components/Home';



const App = () => {

  const dispatch = useAppDispatch();
  
  useEffect(() => {
    journeyService.getAll().then(journeys => dispatch(setJourneys(journeys)))
  }, [dispatch])

  return (
    <Container>
      <Header />
      <BrowserRouter>
        <Routes>
          <Route path='journeys' element={<JourneyList />} />
          <Route path='stations' element={<StationList />} />
          <Route path='/stations/:id' element={<Station />} />
          <Route path='/' element={<Home />} />
        </Routes>
      </BrowserRouter>
    </Container>
  );
}

export default App;
