import { configureStore } from '@reduxjs/toolkit'
import stationReducer from './reducers/stationReducer'
import journeyReducer from './reducers/journeyReducer'

const store = configureStore({
    reducer: {
        journeys: journeyReducer,
        stations: stationReducer
    }
})

export default store;

export type RootState = ReturnType<typeof store.getState>
export type AppDispatch = typeof store.dispatch