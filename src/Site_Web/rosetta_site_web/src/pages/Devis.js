import React from 'react';
import { Row, Col, Container, Form, Button } from 'react-bootstrap';
import { MDBCol, MDBIcon} from "mdbreact";
import "../style/Devis.css";


const Devis = () => {
    return (
        <div>
             <h3>Devis</h3>
            <img class="ImagelogoPlombier" src="/img/logo-plombiers.png" alt="" />
            <Container fluid>
                <Row>
                    <Col className="col1-AffichageDevis" md={10}>
                    <MDBCol md="6">
                    <form className="form-inline mt-4 mb-4">
                        <MDBIcon icon="search" />
                        <input className="form-control form-control-sm ml-3 w-75" type="text" placeholder="Rechercher" aria-label="Rechercher" />
                    </form>
                    </MDBCol>

                   

                    </Col>
                    <Col className="col2-ButtonDevis" md={2}>

                    <button type="button" class="btn btn-light">Ajouter Devis</button>
                    <button type="button" class="btn btn-light">Modifier Devis</button>
                    <button type="button" class="btn btn-light">Supprimer Devis</button>

                    </Col>
                </Row>
            </Container>
        </div>
    );
};

export default Devis;