import React from 'react';
import { Row, Col, Container } from 'react-bootstrap';
import "../style/Accueil.css";

const Accueil = () => {
    return (
        <div>
            <Container className="cont" fluid>
                <Row>
                    <Col className="col1" md={10}></Col>
                    <Col className="col2" md={2}></Col>
                </Row>
            </Container>
        </div>
    );
};

export default Accueil;