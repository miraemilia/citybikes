export interface Journey {
    id: number, 
    departureDate: string,
    returnDate: string, 
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

export interface TopListItem {
    id: number,
    name: string,
    count: number
}