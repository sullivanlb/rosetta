import React from 'react';
import { Row, Col, Container, Form, Button } from 'react-bootstrap';
import {Link } from'react-router-dom';
import "../style/Client.css";


const Client = () => {
    return (
        <div>
            
            <h3>Client</h3>
            <img class="ImagelogoPlombier" src="/img/logo-plombiers.png" alt="" />
            <Container fluid>
                <Row>
                    <Col className="col1-liste" md={2}>
                    <div class="list-group">
                    <button type="button" class="list-group-item list-group-item-action active">
                        Liste des clients
                    </button>
                        <button type="button" class="list-group-item list-group-item-action">Dapibus ac facilisis in</button>
                        <button type="button" class="list-group-item list-group-item-action">Morbi leo risus</button>
                        <button type="button" class="list-group-item list-group-item-action">Porta ac consectetur ac</button>
                        <button type="button" class="list-group-item list-group-item-action">Vestibulum at eros</button>
                    </div>

                    </Col>
                    <Col className="col2-affichage" md={8}>

                    </Col>
                    <Col className="col3-button" md={2}>
                    <Link  class="btn btn-light" to="/client/create">Nouveau client</Link>
                    <button type="button" class="btn btn-light">Modifier client</button>
                    <button type="button" class="btn btn-light">Supprimer client</button>

                    </Col>
                </Row>
            </Container>
        </div>
    );
};

export default Client;