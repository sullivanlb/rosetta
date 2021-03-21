import React, { Component, Fragment } from "react";
import { Row, Col, Container, Form, Button } from "react-bootstrap";
import { MDBCol, MDBIcon } from "mdbreact";
import Supprimer from "../composants/SupprimerComposants";
import "../style/ComposantsPacks.css";
import ListeComposants from "../composants/ListeComposants";
import ListePacks from "../composants/ListePacks";
import AffichageComposantsPacks from "../composants/AffichageComposantsPacks";

/**
 * Ce composant représente la page Composants/Packs dans laquelle nous pourrons :
 * - afficher 2 listes de composants et packs, et y afficher des informations
 * - avoir un bouton pour insérer / annuler / supprimer un scénario
 * - créer des packs de composants.
 *
 * @author Alice GONTARD, Christophe GARCIA
 */
export default class ComposantsPacks extends Component {
  constructor(props) {
    super(props);

    this.state = {
      stateX: 1,
      state1: {
        idToDisplay: 1,
        composants: [
          {
            id: 1,
            nom: "Tuyaux",
            unite : " m ",
            prix : 5,
            type: "composants",
          },
          {
            id: 2,
            nom: "cirage",
            unite : " Litre ",
            prix : 8,
            type: "composants",
          },
          {
            id: 3,
            nom: "Joints",
            unite : " kg ",
            prix : 2,
            type: "composants",
          },
          {
            id: 4,
            nom: "Vis",
            unite : null,
            prix : 1,
            type: "composants",
          },
          {
            id: 5,
            nom: "Bruleur",
            unite : null,
            prix : 15,
            type: "composants",
          },
        ],
      },
      state2: {
        idToDisplay: 2,
        packs: [
          {
            id: 1,
            nom: "packs n°1",
            type: "packs",
          },
          {
            id: 2,
            nom: "packs n°2",
            type: "packs",
          },
          {
            id: 3,
            nom: "packs n°3",
            type: "packs",
          },
          {
            id: 4,
            nom: "packs n°4",
            type: "packs",
          },
          {
            id: 5,
            nom: "packs n°5",
            type: "packs",
          },
        ],
      }
    };

    this.tab = this.state.state1.composants;
    this.idElement = this.state.state1.idToDisplay;

    this.changeStateX = this.changeStateX.bind(this);
  }

  changeStateX(type, id) {
    if (type === "composants") {
      this.setState({ stateX: 1 });
      var stateTmp = this.state.state1;
      stateTmp.idToDisplay = id;
      this.setState({ state1: stateTmp });
      this.tab = this.state.state1.composants;
      this.idElement = this.state.state1.idToDisplay;
    } else {
      this.setState({ stateX: 2 });
      var stateTmp = this.state.state2;
      stateTmp.idToDisplay = id;
      this.setState({ state2: stateTmp });
      this.tab = this.state.state2.packs;
      this.idElement = this.state.state2.idToDisplay;
    }
  }

  render() {
    return (
      <Fragment>
        <h3>Composants/Packs</h3>
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
                    placeholder="Rechercher un packs/composants"
                    aria-label="Rechercher"
                  />
                </form>
              </MDBCol>
              </Row>
              <ListeComposants
                state={this.state}
                action={this.changeStateX}
              />
              <Row><Col> <br></br>  <br></br> </Col></Row>
              <ListePacks
                state={this.state}
                action={this.changeStateX}
              />
            </Col>
            <Col className="col2-affichage" md={6}>
              <MDBCol md="12">
                <form className="form-inline mt-4 mb-4">
                  <AffichageComposantsPacks
                    tab={this.tab}
                    idElement={this.idElement}
                     />
                </form>
              </MDBCol>
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
        </Container>
      </Fragment>
    );
  }
}


//export default Scenario;
