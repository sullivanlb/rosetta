import React from 'react';
import { Row, Col, Container } from 'react-bootstrap';
import { MDBCol, MDBIcon} from "mdbreact";
import { Link } from'react-router-dom';
import Supprimer from '../composants/Supprimer';
import "../style/Client.css";


const Client = () => {
    return (
        <div>
            
            <h3>Client</h3>
            <img class="ImagelogoPlombier" src="/img/logo-plombiers.png" alt="" />
            <Container fluid>
                <Row>
                    <Col className="col1-liste" md={2}>
                    
                    {/*
                    <div class="list-group">
                    <button type="button" class="list-group-item list-group-item-action active">
                        Liste des clients
                    </button>
                        <button type="button" class="list-group-item list-group-item-action">Client1</button>
                        <button type="button" class="list-group-item list-group-item-action">Client2</button>
                        <button type="button" class="list-group-item list-group-item-action">Client3</button>
                        <button type="button" class="list-group-item list-group-item-action">client4</button>
                    </div>/>*/}

                    </Col>
                    <Col className="col2-affichage" md={8}>
                    <MDBCol md="6">
                    <form className="form-inline mt-4 mb-4">
                        <MDBIcon icon="search" />
                        <input className="form-control form-control-sm ml-3 w-75" type="text" placeholder="Rechercher un client" aria-label="Rechercher" />
                    </form>
                    </MDBCol>

                    </Col>
                    <Col className="col3-button" md={2}>
                    <Link  class="btn btn-light" to="/client/create">Nouveau client</Link>
                    <Link  class="btn btn-light" to="/client/modify">Modifier client</Link>
                    <Supprimer/>

                    </Col>
                </Row>
            </Container>
        </div>
    );
};

export default Client;