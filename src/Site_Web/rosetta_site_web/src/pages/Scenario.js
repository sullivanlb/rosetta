import React from 'react';
import { Row, Col, Container, Form, Button } from 'react-bootstrap';
import { MDBCol, MDBIcon} from "mdbreact";
import Supprimer from '../composants/SupprimerScenario';
import "../style/Scenario.css";

const Scenario = () => {
    return (
        <div>
             <h3>Scénario</h3>
            <img class="ImagelogoPlombier" src="/img/logo-plombiers.png" alt="" />
            <Container fluid>
                <Row>
                    <Col className="col1-ListesScenarios" m={2}>
                    
                    </Col>
                    <Col className="col2-AffichageScenario" md={8}>
                    <MDBCol md="6">
                    <form className="form-inline mt-4 mb-4">
                        <MDBIcon icon="search" />
                        <input className="form-control form-control-sm ml-3 w-75" type="text" placeholder="Rechercher un scénario" aria-label="Rechercher" />
                    </form>
                    </MDBCol>

                    </Col>

                    <Col className="col3-ButtonScenario" md={2}>

                    <button type="button" class="btn btn-light">Ajouter Scénario</button>
                    <button type="button" class="btn btn-light">Modifier Scénario</button>
                    <Supprimer/>

                    </Col>
                </Row>
            </Container>
        </div>
    );
};

export default Scenario;