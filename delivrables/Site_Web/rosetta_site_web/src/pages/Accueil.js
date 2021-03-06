import React, { Fragment, Component } from "react";
import { Row, Col, Container, Form, Button, Alert } from "react-bootstrap";
import "../style/Accueil.css";
import Carousel from "../composants/Carousel";
import OpenStreetMap from "../composants/OpenStreetMap";
import axios from "axios";

/**
 * Ce composant représente la page d'accueil, elle permet de :
 *  - Présenter l'entreprise, numéro, adresse et maps.
 *  - Afficher un diaporam (carousel) de photos.
 * 
 * @author Sullivan LEBOEUF && Lucy Gastebois
 */
export default class Accueil extends Component {
  state = {
    nomUtilisateur: "",
    mdp: "",
    connexion: "ECHOUEE",
  };

  handleAddNomUtilisateur = (e) => {
    this.setState({
      nomUtilisateur: e.target.value,
     });
   };
 
   handleAddMdp = (e) => {
     this.setState({
      mdp: e.target.value,
     });
   };

  handleSubmit = async (e) => {
    e.preventDefault();
    let formData = new FormData();
    formData.append("nomUtilisateur", this.state.nomUtilisateur);
    formData.append("mdp", this.state.mdp);
    await axios
      .post("http://api/connexion", formData)
      .then(res => {
        console.log(res.data);
        this.setState({connexion: res.data.connexion});
       });
  };

  render () {
    return (
      <Fragment>
        {
          this.state.connexion === "REUSSIE" ?
          <nav>
            <ul className="nav-links">
              <li>
                <a href="/">Accueil </a>
              </li>
              <li>
                <a href="/client">Client</a>
              </li>
              <li>
                <a href="/devis">Devis</a>
              </li>
              <li>
                <a href="/scenario">Scénario</a>
              </li>
            </ul>

            <nav className="navbar-toggler">
              <div className="navbar-collapse collapse" id="collapsingNavbar3">
                <ul className="navbar-nav mx-auto">
                  <li className="nav-item active">
                    <a className="nav-link" href="/">
                      Accueil
                    </a>
                  </li>
                  <li className="nav-item">
                    <a className="nav-link" href="/client">
                      Client
                    </a>
                  </li>
                  <li className="nav-item">
                    <a className="nav-link" href="/devis">
                      Devis
                    </a>
                  </li>
                  <li className="nav-item">
                    <a className="nav-link" href="/scenario">
                      Scenario
                    </a>
                  </li>
                </ul>
              </div>
            </nav>
          </nav> :
          <nav>
            <ul className="nav-links">
              <li>
                <a href="/">Accueil </a>
              </li>
            </ul>

            <nav className="navbar-toggler">
              <div className="navbar-collapse collapse" id="collapsingNavbar3">
                <ul className="navbar-nav mx-auto">
                  <li className="nav-item active">
                    <a className="nav-link" href="/">
                      Accueil
                    </a>
                  </li>
                </ul>
              </div>
            </nav>
          </nav>
        }
        <Container fluid>
          <Row>
            <Col className="col1" md={10}>
              <h2>Bienvenue sur Allô Dépanne Service</h2>
              <img
                className="ImagelogoPlombier"
                src="/img/logo-plombiers.png"
                alt=""
              />

              <p className="description mb-5">
                Vous avez des projets d'installation de salle de bain ? <br></br>
                Nous prenons en charge tous vos travaux de plomberie que ce soit
                en neuf ou en rénovation.<br></br>
                La vente et l'installation de salle de bain font également partie
                de nos prestations. Faites-nous part de votre projet.{" "}
              </p>
              <Carousel className="carousel" />
              <span className="vertical-line"></span>
            </Col>
            
            <Col className="col2" md={2}>
              <Alert className="ContactAlert" variant="dark">
                Contact :
              </Alert>
              <img className="ImageTelephone" src="/img/call.png" alt="" />
              <h6 className="TexteTelephone">06.60.35.75.45</h6>
              <br></br>
              <br></br>
              <img className="pointeur" src="/img/location-pointer.png" alt="" />
              <h6 className="TexteRue">90 avenue Marne, 56000 Vannes</h6>
              <br></br>
              <OpenStreetMap/>
              <Form>
                <Alert className="ConnexionAlert" variant="dark">
                  Connexion :
                </Alert>
                <Form.Group controlId="formGroupEmail">
                  <Form.Label>Login</Form.Label>
                  <Form.Control
                    type="text" 
                    placeholder="Entrez votre login"
                    onChange={this.handleAddNomUtilisateur} />
                </Form.Group>
                <Form.Group controlId="formGroupPassword">
                  <Form.Label>Mot de passe</Form.Label>
                  <Form.Control 
                    type="password" 
                    placeholder="Mot de passe"
                    onChange={this.handleAddMdp} />
                </Form.Group>
                <Button
                  variant="Primary" 
                  type="submit" 
                  size="sm"
                  className="btn btn-light"
                  onClick={this.handleSubmit.bind(this)}
                >
                  Valider
                </Button>
              </Form>
              <br></br><br></br><br></br><br></br><br></br><br></br>
              <br></br><br></br><br></br><br></br><br></br><br></br>
            </Col>
          </Row>
        </Container>
      </Fragment>
    );
  }
}
