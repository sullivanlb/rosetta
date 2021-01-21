import React from 'react'
import Carousel from 'react-bootstrap/Carousel'
import "../style/Carousel.css"
import "../../node_modules/bootstrap/dist/css/bootstrap.css";


export default function DiapoCarousel() {
    return(
    
        <Carousel className="carousel" pauseOnHover={true}>
            <Carousel.Item interval={5000}>
                <img
                className="ImageOutil w-100 h-100"
                src="/img/outil.jpg"
                alt=""
                />
            </Carousel.Item>

            <Carousel.Item interval={5000}>
                <img
                className="ImageTableauElectrique w-100"
                src="/img/tableau-electrique.jpg"
                alt=""
                />
            </Carousel.Item>

            <Carousel.Item interval={5000}>
                <img
                className="ImageChauffage w-100"
                src="/img/chauffage.jpg"
                alt=""
                />
            </Carousel.Item>
        </Carousel>

    )
}