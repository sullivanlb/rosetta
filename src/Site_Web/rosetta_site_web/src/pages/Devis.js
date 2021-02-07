import React, {Component, Fragment } from "react";
import { Row, Col, Container, Form, Button } from "react-bootstrap";
import { MDBCol, MDBIcon } from "mdbreact";
import { Link } from "react-router-dom";
import Supprimer from "../composants/SupprimerDevis";
import "../style/Devis.css";
import ListeDevis from "../composants/ListeDevis";
import AffichageDevis from "../composants/AffichageDevis";

/**
 * Ce composant représente la page Devis, elle permet de :
 *  - Afficher la liste des devis enregistrés
 *  - Avoir un bouton ajouter/modifier/supprimer un devis
 * 
 * @author Lucy Gastebois
 */

export default class Devis extends Component {

  /**
   * Constructeur permettant d'entrée les données concernant le devis
   * @param {*} props 
   */
  constructor(props){
    super(props);

    this.state = {
      idToDisplay: 1,
      devis: [
        {
          id: 1,
          date: "01/02/2021",
          client: "Jacque Lors",
          scenario: "Installation d'un chauffe-eau électrique",
          prix: "2 040€",
        },
        {
          id: 2,
          date: "12/01/2021",
          client: "Marie Poli",
          scenario: "Remplacement d'une baignoire en douche",
          prix: "1 200€"
        },
        {
          id: 3,
          date: "25/01/2021",
          client: "Marc Castier",
          scenario: "Installation d'un chauffe-eau électrique",
          prix: "2 040€"
        },
      ],
    };

    this.affichageInfoDevis = this.affichageInfoDevis.bind(this);
  }

  /**
   * Modifier l'état du devis à afficher
   *
   * @param {l'identifiant du devis à afficher} id
   */

  affichageInfoDevis(id){
    this.setState({ idToDisplay: id});
  }

  /**
   * Méthode permettant l'affichage des composants 
   */
  render() {
    return (
      <Fragment>
        <h3>Devis</h3>
        <img class="ImagelogoPlombier" src="/img/logo-plombiers.png" alt="" />
        <Container fluid>
          <Row>
            <Col className="col1-AffichageDevis" md={4}>
              <Row>
              <MDBCol md="6">
                <form className="form-inline mt-4 mb-4">
                  <MDBIcon icon="search" />
                  <input
                    className="form-control form-control-sm ml-3 w-75"
                    type="text"
                    placeholder="Rechercher un devis"
                    aria-label="Rechercher"
                  />
                </form>
              </MDBCol>
              </Row>
              <ListeDevis
                state={this.state}
                action={this.affichageInfoDevis}
              />
            </Col>

            <Col className="col2-affichageDevis" md={6}>
            <MDBCol md="12">
                <form className="form-inline mt-4 mb-4">
                <AffichageDevis key={this.state.idToDisplay} state={this.state} />
                </form>
            </MDBCol>
            </Col>

            <Col className="col2-ButtonDevis" md={2}>
              <Link class="btn btn-light" to="/devis/nouveau">
                Ajouter Devis
              </Link>
              <Link class="btn btn-light" to="/devis/modifier">
                Modifier Devis
              </Link>
              <Supprimer />
            </Col>
          </Row>
        </Container>
      </Fragment>
    );
  }
}



