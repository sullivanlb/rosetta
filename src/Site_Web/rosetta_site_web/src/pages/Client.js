import React from "react";
import { Row, Col, Container } from "react-bootstrap";
import { MDBCol, MDBIcon } from "mdbreact";
import { Link } from "react-router-dom";
import Supprimer from "../composants/Supprimer";
import "../style/Client.css";
import ListeClient from "../composants/ListeClient";

const Client = () => {
  return (
    <div>
      <h3>Client</h3>
      <img class="ImagelogoPlombier" src="/img/logo-plombiers.png" alt="" />
      <Container fluid>
        <Row>
          <Col className="col1-liste" md={2}>
            <ListeClient />
          </Col>
          <Col className="col2-affichage" md={8}>
            <MDBCol md="6">
              <form className="form-inline mt-4 mb-4">
                <MDBIcon icon="search" />
                <input
                  className="form-control form-control-sm ml-3 w-75"
                  type="text"
                  placeholder="Rechercher un client"
                  aria-label="Rechercher"
                />
              </form>
            </MDBCol>
          </Col>
          <Col className="col3-button" md={2}>
            <Link class="btn btn-light" to="/client/create">
              Nouveau client
            </Link>
            <Link class="btn btn-light" to="/client/modify">
              Modifier client
            </Link>
            <Supprimer />
          </Col>
        </Row>
      </Container>
    </div>
  );
};

export default Client;
