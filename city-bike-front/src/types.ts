export interface Journey {
    id: number, 
    departureDate: Date,
    returnDate: Date, 
    departureStation: Station,
    returnStation: Station,
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