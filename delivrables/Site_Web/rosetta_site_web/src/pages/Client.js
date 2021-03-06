import React, { Component, Fragment } from "react";
import { Row, Col, Container } from "react-bootstrap";
import { MDBCol, MDBIcon } from "mdbreact";
import { Link } from "react-router-dom";
import Supprimer from "../composants/SupprimerClient";
import "../style/Client.css";
import ListeClient from "../composants/ListeClient";
import AffichageClient from "../composants/AffichageClient";
import Navigation from "../composants/Navigation";
import axios from "axios";

/**
 * Ce composant représente la page client dans laquelle nous pourrons :
 * - afficher la liste du client et son affichage d'informations
 * - avoir un bouton pour insérer / éditer / supprimer un client
 *
 * @author Sullivan LEBOEUF
 */
export default class Client extends Component {
  constructor(props) {
    super(props);

    this.state = {
      idToDisplay: 1,
      clients: [],
      search: null,
    };

    this.affichageInfoClient = this.affichageInfoClient.bind(this);
    this.supprimerClient = this.supprimerClient.bind(this);
  }

  componentDidMount() {
    axios.get(`http://api/client/tousLesClients`).then((res) => {
      const clients = res.data;
      this.setState({ clients });
    });
  }

  /**
   * Envoi une requête de suppression au serveur
   */
  async supprimerClient() {
    await axios
      .delete(`http://api/client/supprimerClient/${this.state.idToDisplay}`)
      .then((res) => {
        console.log(res.data);
      });

    window.location.reload();
  }

  /**
   * Modifier l'état du client à afficher
   *
   * @param {int} id - l'identifiant du client à afficher
   */
  affichageInfoClient(id) {
    this.setState({ idToDisplay: id });
  }

  /**
   * Mettre à jour la liste des scénarios à afficher, suivant la recherche de l'utilisateur
   */
  onChangeSearchInput(e) {
    let motcle = e.target.value;
    this.setState({ search: motcle })
  }

  /**
   * Méthode permettant l'affichage des composants
   */
  render() {
    return (
      <Fragment>
        <Navigation/>
        <h3>Client</h3>
        <img
          className="ImagelogoPlombier ml-3"
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
                      id="search-input"
                      className="form-control form-control-sm ml-3 w-75"
                      type="text"
                      placeholder="Rechercher un client"
                      aria-label="Rechercher"
                      onChange={(e) => this.onChangeSearchInput(e)}
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
                  <AffichageClient
                    key={this.state.idToDisplay}
                    state={this.state}
                  />
                </form>
              </MDBCol>
            </Col>
            <Col className="col3-button" md={2}>
              <Link className="btn btn-light" to="/client/nouveau">
                Nouveau client
              </Link>
              <Link
                className="btn btn-light"
                to={{
                  pathname: "/client/modifier",
                  params: {
                    idToDisplay: this.state.idToDisplay,
                    client: this.state.clients.filter(
                      (client) => client.idClient === this.state.idToDisplay
                    ),
                  },
                }}
              >
                Modifier client
              </Link>
              <Supprimer action={this.supprimerClient} />
            </Col>
          </Row>
        </Container>
      </Fragment>
    );
  }
}
