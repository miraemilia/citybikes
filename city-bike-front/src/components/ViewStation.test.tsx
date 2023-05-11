import React from 'react'
import * as reactRedux from 'react-redux'
import '@testing-library/jest-dom';
import { render } from '@testing-library/react'
import { Journey, Station, TopListItem } from '../types';
import ViewStation from './ViewStation'
import Router from 'react-router-dom';
import { useAppSelector } from '../hooks';
import store from '../store';

    jest.mock('react', () => ({
        ...jest.requireActual('react'),
        useState: jest.fn(),
    }));

    jest.mock('react-router-dom', () => ({
        ...jest.requireActual('react-router-dom'),
        useParams: jest.fn(),
    }));

    jest.mock('../hooks')

describe('view station tests', () => {

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
    const top5Departures : TopListItem[] = [
        { id: 42, name: "Eka", count: 23},
        { id: 54, name: "Toka", count: 63},
        { id: 73, name: "Kolmas", count: 24},
        { id: 25, name: "Neljäs", count: 53},
        { id: 83, name: "Viides", count: 66}
    ]
    const top5Arrivals : TopListItem[] = [
        { id: 42, name: "Eka", count: 84},
        { id: 54, name: "Toka", count: 72},
        { id: 73, name: "Kolmas", count: 92},
        { id: 25, name: "Neljäs", count: 88},
        { id: 83, name: "Viides", count: 79}
    ]

    beforeEach(() => {
        jest.resetAllMocks()

        jest.spyOn(Router, 'useParams').mockReturnValue({ id: "1" });

        const hooks = { useAppSelector }
        jest.spyOn(hooks, 'useAppSelector').mockImplementation((callback) => callback({journeys : { value : mockJourneys}, stations: { value : mockStations}}))

        jest.spyOn(React, 'useState').mockImplementationOnce(() => [123, jest.fn()])
        jest.spyOn(React, 'useState').mockImplementationOnce(() => [234, jest.fn()])
        jest.spyOn(React, 'useState').mockImplementationOnce(() => [top5Departures, jest.fn()])
        jest.spyOn(React, 'useState').mockImplementationOnce(() => [top5Arrivals, jest.fn()])  
        jest.spyOn(React, 'useState').mockImplementationOnce(() => [432, jest.fn()])  
        jest.spyOn(React, 'useState').mockImplementationOnce(() => [543, jest.fn()])


    })

    test('station info rendered', () => {

        const component = render(<reactRedux.Provider store={store}><ViewStation/></reactRedux.Provider>)

        const gridItem = component.container.querySelector('#stationInfo')
        expect(gridItem).toHaveTextContent("Asema")
        expect(gridItem).toHaveTextContent("Asematie 1")
    })
    
    test('departure count rendered', () => {

        const component = render(<reactRedux.Provider store={store}><ViewStation/></reactRedux.Provider>)

        const gridItem = component.container.querySelector('#departureCount')
        expect(gridItem).toHaveTextContent("123")
    })

    test('arrival count rendered', () => {

        const component = render(<reactRedux.Provider store={store}><ViewStation/></reactRedux.Provider>)

        const gridItem = component.container.querySelector('#arrivalCount')
        expect(gridItem).toHaveTextContent("234")
    })
})
