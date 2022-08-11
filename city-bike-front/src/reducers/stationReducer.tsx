import { createSlice, PayloadAction } from '@reduxjs/toolkit'

import { Station } from '../types'

interface StationState {
    value: Station[]
  }
  
const initialState = { value: [] } as StationState

const stationSlice = createSlice({
    name: 'stations',
    initialState,
    reducers: {
        setStations(state, action : PayloadAction<Station[]>) {
            state.value = action.payload
        }
    }
})

export const { setStations } = stationSlice.actions
export default stationSlice.reducer