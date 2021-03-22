import React, {Component, Fragment } from "react";
import { Row, Col, Container, Form, Button } from "react-bootstrap";
import { MDBCol, MDBIcon } from "mdbreact";
import { Link } from "react-router-dom";
import Supprimer from "../composants/SupprimerScenario";
import "../style/Scenario.css";
import ListeScenario from "../composants/ListeScenario";
import AffichageScenario from "../composants/AffichageScenario";

/**
 * Ce composant représente la page de scénario dans laquelle nous pourrons
 * - afficher la liste du scénario et son affichage d'informations
 * - avoir un bouton pour insérer / éditer / supprimer un scénario
 * - avoir un bouton pour accéder à la page Composants/Packs
 *
 * @author Alice GONTARD, Christophe GARCIA
 */
export default class Scenario extends Component {
  constructor(props) {
    super(props);

    this.state = {
      idToDisplay: 1,
      list: {
        scenario: [
          {
            id: 1,
            nom: "Installation chaudière",
            questions:[
              {
                id: 1,
                nom: "Quelle type de chaudière?"
              },
              {
                id: 2,
                nom: "Combien de raccordement à faire?"
              },
              {
                id: 3,
                nom: "Vérification de la place dans la pièce ?"
              },
            ],
            composants:[
              {
                id: 1,
                nom: "Tuyaux"
              },
              {
                id: 2,
                nom: "Laine de verre"
              },
              {
                id: 3,
                nom: "Bruleur"
              },
            ],
            packs: [
              {
                id: 1,
                nom: "packs n°1",
              },
            ],
          },
          {
            id: 2,
            nom: "Fuite d'eau",
            questions:[
              {
                id: 1,
                nom: "Problème avec le robinet?"
              },
              {
                id: 2,
                nom: "Les tuyaux sont-ils endommagés?"
              },
            ],
            composants:[
              {
                id: 1,
                nom: "Robinet"
              },
              {
                id: 2,
                nom: "Joints"
              },
              {
                id: 3,
                nom: "Tuyaux"
              },
            ],
            packs:[
              {
                id: null,
                nom: null,
              },
            ],
          },
          {
            id: 3,
            nom: "Installation chauffage",
            questions:[
              {
                id: 1,
                nom: "Quelle type de chauffage?"
              },
              {
                id: 2,
                nom: "Combien de m2 fait la pièce à chauffer?"
              },
            ],
            composants:[
              {
                id: 1,
                nom: "Radiateur"
              },
              {
                id: 2,
                nom: "Tuyaux"
              },
              {
                id: 3,
                nom: "Robinet"
              },
              {
                id: 4,
                nom: "Joints"
              },
            ],
            packs: [
              {
                id: 2,
                nom: "packs n°2",
              },
              {
                id: 3,
                nom: "packs n°3",
              },
            ],
          },
        ],
      },
    };

    this.affichageInfoScenario = this.affichageInfoScenario.bind(this);
    this.onChangeSearchInput = this.onChangeSearchInput.bind(this);
  }

  affichageInfoScenario(id) {
    this.setState({ idToDisplay: id });
  }

  onChangeSearchInput() {
    // Récupère tous les scenarios de la BDD -> setState({list})
    // {insérer le code} 
    // (peut-on appeler la méthode this.componentDidMount() quand elle sera faite ?)

    if (document.getElementById("search-input").value.length != 0) {
      // Mise à jour de la liste des scénarios
      var list_tmp = this.state.list;
      var scenarios_liste = [];

      this.state.list.scenario.map((scenario) => {
        var wordFound = false;

        if (scenario.nom != null && scenario.nom.toUpperCase().includes(document.getElementById("search-input").value.toUpperCase())) {
          wordFound = true;
        } else {
          scenario.questions.map((question) => {
            if (question.nom != null && question.nom.toUpperCase().includes(document.getElementById("search-input").value.toUpperCase())) {
              wordFound = true;
            }
          });

          if (!wordFound) {
            scenario.composants.map((composant) => {
              if (composant.nom != null && composant.nom.toUpperCase().includes(document.getElementById("search-input").value.toUpperCase())) {
                wordFound = true;
              }
            });
          }

          if (!wordFound) {
            scenario.packs.map((pack) => {
              if (pack.nom != null && pack.nom.toUpperCase().includes(document.getElementById("search-input").value.toUpperCase())) {
                wordFound = true;
              }
            });
          }
        }

        if (wordFound) {
          var questions_liste = [];
          scenario.questions.map((question) => {
            questions_liste.push({
              id: question.id,
              nom: question.nom,
            });
          });

          var composants_liste = [];
          scenario.composants.map((composant) => {
            composants_liste.push({
              id: composant.id,
              nom: composant.nom,
            });
          });

          var packs_liste = [];
          scenario.packs.map((pack) => {
            packs_liste.push({
              id: pack.id,
              nom: pack.nom,
            });
          });

          scenarios_liste.push({
            id: scenario.id,
            nom: scenario.nom,
            questions: questions_liste,
            composants: composants_liste,
            packs: packs_liste,
          });
        }
      });

      list_tmp.scenario = scenarios_liste;
      this.setState({ list: list_tmp });
    } else {

      // ENLEVER LE 'ELSE' QUAND LA LIAISON AVEC LA BDD SERA FAITE
      // (cf avant le 'if ()')
      
      var list_tmp = this.state.list;
      var scenarios_liste = [];

      scenarios_liste.push({
        id: 1,
        nom: "Installation chaudière",
        questions:[
          {
            id: 1,
            nom: "Quelle type de chaudière?"
          },
          {
            id: 2,
            nom: "Combien de raccordement à faire?"
          },
          {
            id: 3,
            nom: "Vérification de la place dans la pièce ?"
          },
        ],
        composants:[
          {
            id: 1,
            nom: "Tuyaux"
          },
          {
            id: 2,
            nom: "Laine de verre"
          },
          {
            id: 3,
            nom: "Bruleur"
          },
        ],
        packs: [
          {
            id: 1,
            nom: "packs n°1",
          },
        ],
      });
      scenarios_liste.push({
        id: 2,
        nom: "Fuite d'eau",
        questions:[
          {
            id: 1,
            nom: "Problème avec le robinet?"
          },
          {
            id: 2,
            nom: "Les tuyaux sont-ils endommagés?"
          },
        ],
        composants:[
          {
            id: 1,
            nom: "Robinet"
          },
          {
            id: 2,
            nom: "Joints"
          },
          {
            id: 3,
            nom: "Tuyaux"
          },
        ],
        packs:[
          {
            id: null,
            nom: null,
          },
        ],
      });
      scenarios_liste.push({
        id: 3,
        nom: "Installation chauffage",
        questions:[
          {
            id: 1,
            nom: "Quelle type de chauffage?"
          },
          {
            id: 2,
            nom: "Combien de m2 fait la pièce à chauffer?"
          },
        ],
        composants:[
          {
            id: 1,
            nom: "Radiateur"
          },
          {
            id: 2,
            nom: "Tuyaux"
          },
          {
            id: 3,
            nom: "Robinet"
          },
          {
            id: 4,
            nom: "Joints"
          },
        ],
        packs: [
          {
            id: 2,
            nom: "packs n°2",
          },
          {
            id: 3,
            nom: "packs n°3",
          },
        ],
      });

      list_tmp.scenario = scenarios_liste;
      this.setState({ list: list_tmp });
    }
  }

  render() {
    return (
      <Fragment>
        <h3>Scénario</h3>
        <img
          class="ImagelogoPlombier ml-3"
          src="/img/logo-plombiers.png"
          alt=""
        />
        <Container fluid>
          <Row>
            <Col className="col1-liste" md={4}>
              <Row>
              <MDBCol md="12">
                <form className="form-inline mt-4 mb-4">
                  <MDBIcon icon="search" />
                  <input
                    id="search-input"
                    className="form-control form-control-sm ml-3 w-75"
                    type="text"
                    placeholder="Rechercher un scénario"
                    aria-label="Rechercher"
                    onChange={this.onChangeSearchInput}
                  />
                </form>
              </MDBCol>
              </Row>
              <ListeScenario
                state={this.state}
                action={this.affichageInfoScenario}
              />
            </Col>
            <Col className="col2-affichage" md={6}>
              <MDBCol md="12">
                <form className="form-inline mt-4 mb-4">
                  <AffichageScenario key={this.state.idToDisplay} state={this.state} />
                </form>
              </MDBCol>
            </Col>
            <Col className="col3-button" md={2}>
              <Link class="btn btn-light" to="/scenario/nouveauscenario">
                Nouveau scénario
              </Link>
              <button type="button" class="btn btn-light">
                Modifier scénario
              </button>
              <Supprimer />
              <Link class="btn btn-light" to="/scenario/composantspacks">
                Composants/Packs
              </Link>
            </Col>
          </Row>
          <Row><Col> <br></br> <br></br> <br></br> <br></br> <br></br> <br></br> </Col></Row>
        </Container>
      </Fragment>
    );
  }
}
