import React, { Component, Fragment } from "react";
import { Row, Col, Container,FormLabel,FormControl, Form, Button } from "react-bootstrap";
import { MDBCol, MDBIcon } from "mdbreact";
import Supprimer from "../composants/SupprimerScenario";
import "../style/Scenario.css";
import ListeComposants from "../composants/ListeComposants";
import ListePacks from "../composants/ListePacks";

/**
 * @description This component represent the page to create a scenario
 *
 * @author Alice GONTARD
 */

export default class NouveauScenario extends Component {

  constructor(props) {
  super(props);

      this.state1 = {
        idToDisplay: 1,
        composants: [
          {
            id: 1,
            nom: "Tuyaux",
            unite : " m ",
            prix : 5,
          },
          {
            id: 2,
            nom: "cirage",
            unite : " Litre ",
            prix : 8,
          },
          {
            id: 3,
            nom: "Joints",
            unite : " kg ",
            prix : 2,
          },
          {
            id: 4,
            nom: "Vis",
            unite : null,
            prix : 1,
          },
          {
            id: 5,
            nom: "Bruleur",
            unite : null,
            prix : 15,
          },
        ],
      };

      this.state2 = {
        idToDisplay: 2,
        packs: [
          {
            id: 1,
            nom: "packs n°1",
          },
          {
            id: 2,
            nom: "packs n°2",
          },
          {
            id: 3,
            nom: "packs n°3",
          },
          {
            id: 4,
            nom: "packs n°4",
          },
          {
            id: 5,
            nom: "packs n°5",
          },
        ],
      };


    this.affichageInfoComposants = this.affichageInfoComposants.bind(this);
    this.affichageInfoPacks = this.affichageInfoPacks.bind(this);
  }

  affichageInfoComposants(id) {
    this.setState({ idToDisplay: id });
  }
  affichageInfoPacks(id) {
    this.setState({ idToDisplay: id });
  }

  render() {
    return (
      <Fragment>
        <h3>Nouveau scénario</h3>
        <img
          class="ImagelogoPlombier ml-3"
          src="/img/logo-plombiers.png"
          alt=""
        />
        <Container fluid>
          <Row>
            <Col className="col1-liste" md={4}>
              <Row>
                <input
                  className="form-control form-control-sm ml-3 w-75 mb-2"
                  type="text"
                  placeholder="Rechercher"
                  aria-label="Rechercher"
                />
              </Row>
              <ListeComposants
                state={this.state1}
                action={this.affichageInfoComposants}
              />
              <Row><Col> <br></br>  <br></br> </Col></Row>
              <ListePacks
                state={this.state2}
                action={this.affichageInfoPacks}
              />
            </Col>
            <Col className="col2-Ajout" md={6}>
              <Row>
              <FormLabel>Nom : </FormLabel>
              <FormControl placeholder="Entrer le nom du scénario"/>
              </Row>
              <Row>
                  <Row><br></br><br></br></Row>
              </Row>
              <Row>
              <FormControl placeholder="Entrer Une nouvelle question pour ce scénario"/>
              </Row>
            </Col>

            <Col className="col3-button" md={2}>
            <button type="button" class="btn btn-light">
              Enregistrer
            </button>
              <Supprimer />
              <button type="button" class="btn btn-light">
                Annuler
              </button>
            </Col>
          </Row>
          <Row><Col> <br></br> <br></br> <br></br> <br></br> <br></br> </Col></Row>
          <Row><Col> <br></br> <br></br> <br></br> <br></br> <br></br> </Col></Row>
          <Row><Col> <br></br> <br></br> <br></br> <br></br> <br></br> </Col></Row>
          <Row><Col> <br></br> <br></br> <br></br> <br></br> <br></br> </Col></Row>
          <Row><Col> <br></br> <br></br> <br></br> <br></br> <br></br> </Col></Row>
          <Row><Col> <br></br> <br></br> <br></br> <br></br> <br></br> </Col></Row>
          <Row><Col> <br></br> <br></br> <br></br> <br></br> <br></br> </Col></Row>

        </Container>
      </Fragment>
    );
  }
}
