import { createSlice, PayloadAction } from '@reduxjs/toolkit'

import { Journey } from '../types'

interface JourneyState {
    value: Journey[]
  }
  
const initialState = { value: [] } as JourneyState

const journeySlice = createSlice({
    name: 'journeys',
    initialState,
    reducers: {
        setJourneys(state, action: PayloadAction<Journey[]>) {
            state.value = action.payload
        }
    }
})

export const { setJourneys } = journeySlice.actions
export default journeySlice.reducer