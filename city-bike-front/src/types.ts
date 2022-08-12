export interface Journey {
    id: number, 
    departureDate: Date,
    returnDate: Date, 
    departureStation: number,
    departureStationName: string,
    returnStation: number,
    returnStationName: string,
    distance: number,
    duration: number
}

export interface Station {
    id: number,
    name: string,
    address: string,
    x: string,
    y: string
}