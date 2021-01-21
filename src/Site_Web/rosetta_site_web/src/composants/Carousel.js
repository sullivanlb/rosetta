import React from 'react'
import Carousel from 'react-bootstrap/Carousel'


export default function DiapoCarousel() {
    return(
    
        <Carousel>
            <Carousel.Item interval={1000}>
                <img
                className="Imageoutil"
                src="/img/outil.jpg"
                alt=""
                />
            <Carousel.Caption>
            
            </Carousel.Caption>
            </Carousel.Item>

            <Carousel.Item interval={500}>
                <img
                className="Imagetableauelectrique"
                src="/img/tableau-electrique.jpg"
                alt=""
                />
            <Carousel.Caption>
                
            </Carousel.Caption>
            </Carousel.Item>

            <Carousel.Item>
                <img
                className="Imagechuaffage"
                src="/img/chauffage.jpg"
                alt=""
                />
            <Carousel.Caption>
               
            </Carousel.Caption>
            </Carousel.Item>
        </Carousel>

    )
}