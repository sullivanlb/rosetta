import React, { Component, Fragment } from "react";
import { Row, Col, Container } from "react-bootstrap";
import { MDBCol, MDBIcon } from "mdbreact";
import { Link } from "react-router-dom";
import Supprimer from "../composants/Supprimer";
import "../style/Client.css";
import ListeClient from "../composants/ListeClient";
import AffichageClient from "../composants/AffichageClient";

export default class Client extends Component {
  constructor(props) {
    super(props);

    this.state = {
      idToDisplay: 1,
      client: [],
    };

    this.affichageInfoClient = this.affichageInfoClient.bind(this);
  }

  affichageInfoClient(id) {
    this.setState({ idToDisplay: id });
  }

  render() {
    return (
      <Fragment>
        <h3>Client</h3>
        <img
          class="ImagelogoPlombier ml-3"
          src="/img/logo-plombiers.png"
          alt=""
        />
        <Container fluid>
          <Row>
            <Col className="col1-liste" md={4}>
              <Row>
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
              </Row>
              <ListeClient
                state={this.state}
                action={this.affichageInfoClient}
              />
            </Col>
            <Col className="col2-affichage" md={6}>
              <MDBCol md="12">
                <form className="form-inline mt-4 mb-4">
                  <AffichageClient key={this.state.idToDisplay} state={this.state} />
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
      </Fragment>
    );
  }
}
