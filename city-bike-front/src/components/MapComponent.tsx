import L, { LatLngTuple } from 'leaflet';
import { MapContainer, TileLayer, Marker, Popup } from 'react-leaflet';
import "leaflet/dist/leaflet.css";
import { Station } from '../types';

type MapProps = {
    station: Station
}

const MapComponent = ({station} : MapProps ) => {

    const coordinates : LatLngTuple = [Number(station.y), Number(station.x)]

    const icon = L.icon({ iconUrl: "/images/marker-icon.png" });
    
    return (
        <MapContainer
            center={coordinates}
            zoom={14}
            style={{height: '400px', width: '100%'}}
        >
            <TileLayer
                attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
                url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
                />
            <Marker position={coordinates} icon={icon}>
                <Popup>
                    <b>{station.name}</b><br/>
                    {station.address}<br/>
                    Latitude: {coordinates[0]}<br />
                    Longitude: {coordinates[1]}
                </Popup>
            </Marker>


        </MapContainer>
    )
}

export default MapComponent