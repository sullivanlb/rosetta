import React, { Component } from "react";
import { MapContainer, TileLayer, Marker } from "react-leaflet";
import "leaflet/dist/leaflet.css";
import icon from "leaflet/dist/images/marker-icon.png";
import iconShadow from "leaflet/dist/images/marker-shadow.png";
import L from "leaflet";

const position = [47.660137584787215, -2.7846692596596148];

let DefaultIcon = L.icon({
  iconUrl: icon,
  shadowUrl: iconShadow,
});

L.Marker.prototype.options.icon = DefaultIcon;

/**
 * @description Ce composant représente la carte de la page d'accueil, elle présente directement l'emplacement de ADS
 * 
 * @author Sullivan LEBOEUF
 */
export default class OpenStreetMap extends Component {
  render() {
    return (
      <MapContainer
        className="map"
        center={position}
        zoom={13}
        scrollWheelZoom={true}
        style={{ width: "100%", height: "30%" }}
      >
        <TileLayer
          attribution='&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
          url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
        />
        <Marker position={position}></Marker>
      </MapContainer>
    );
  }
}
