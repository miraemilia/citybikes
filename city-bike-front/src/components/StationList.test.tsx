import React from 'react'
import * as reactRedux from 'react-redux'
import '@testing-library/jest-dom'
import { render } from '@testing-library/react'
import StationList from './StationList'
import { Journey, Station } from '../types';
import { useAppSelector } from '../hooks'
import store from '../store'

jest.mock('../hooks')

describe('station list tests', () => {
    const mockJourneys : Journey[] = []
    const mockStations : Station[] = [
        {
            id: 1,
            name: "Asema",
            address: "Asematie 1",
            x:"25",
            y: "60"
        },
        {
            id: 2,
            name: "Pysäkki",
            address: "Pysäkkikatu 1",
            x: "25",
            y: "60"
        }
    ]
    beforeEach(() => {
        jest.resetAllMocks()

        const hooks = { useAppSelector }
        jest.spyOn(hooks, 'useAppSelector').mockImplementation((callback) => callback({journeys : { value : mockJourneys}, stations: { value : mockStations}}))
    })
    test('renders station names', () => {

        const component = render(<reactRedux.Provider store={store}><StationList/></reactRedux.Provider>)
        expect(component.container).toHaveTextContent("Asema")
        expect(component.container).toHaveTextContent("Pysäkki")

    })
})