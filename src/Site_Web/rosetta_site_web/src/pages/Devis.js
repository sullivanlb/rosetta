import React, {Component, Fragment } from "react";
import { Row, Col, Container, Form, Button } from "react-bootstrap";
import { MDBCol, MDBIcon } from "mdbreact";
import { Link } from "react-router-dom";
import Supprimer from "../composants/SupprimerDevis";
import "../style/Devis.css";
import ListeDevis from "../composants/ListeDevis";
import AffichageDevis from "../composants/AffichageDevis";
import axios from "axios";

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
      devis: [],
    };

    this.affichageInfoDevis = this.affichageInfoDevis.bind(this);
    this.onChangeSearchInput = this.onChangeSearchInput.bind(this);
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
   * Mettre à jour la liste des devis à afficher, suivant la recherche de l'utilisateur
   */
  async onChangeSearchInput() {
    await axios.get(`http://api/devis/tousLesDevis`).then((res) => {
      const devis = res.data;
      this.setState({ devis });
    });

    if (document.getElementById("search-input").value.length != 0) {
      var devis_liste = [];

      this.state.devis.map((devis) => {
        var wordFound = false;

        if ((devis.date != null && devis.date.toUpperCase().includes(document.getElementById("search-input").value.toUpperCase()))
            || (devis.client != null && devis.client.toUpperCase().includes(document.getElementById("search-input").value.toUpperCase()))
            || (devis.scenario != null && devis.scenario.toUpperCase().includes(document.getElementById("search-input").value.toUpperCase()))
            || (devis.prix != null && devis.prix.toUpperCase().includes(document.getElementById("search-input").value.toUpperCase()))) {
          wordFound = true;
        }

        if (wordFound) {
          devis_liste.push({
            id: devis.id,
            date: devis.date,
            client: devis.client,
            scenario: devis.scenario,
            prix: devis.prix,
          });
        }
      });

      this.setState({ devis: devis_liste });
    }
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
              <MDBCol md="12">
                <form className="form-inline mt-4 mb-4">
                  <MDBIcon icon="search" />
                  <input
                    id="search-input"
                    className="form-control form-control-sm ml-3 w-75"
                    type="text"
                    placeholder="Rechercher un devis"
                    aria-label="Rechercher"
                    onChange={this.onChangeSearchInput}
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



