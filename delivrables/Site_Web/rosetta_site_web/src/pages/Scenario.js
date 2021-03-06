import React, { Component, Fragment } from "react";
import { Row, Col, Container } from "react-bootstrap";
import { MDBCol, MDBIcon } from "mdbreact";
import { Link } from "react-router-dom";
import Supprimer from "../composants/SupprimerScenario";
import "../style/Scenario.css";
import ListeScenario from "../composants/ListeScenario";
import AffichageScenario from "../composants/AffichageScenario";
import Navigation from "../composants/Navigation";
import axios from "axios";

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
      scenarios: [],
    };

    this.affichageInfoScenario = this.affichageInfoScenario.bind(this);
    this.supprimerScenario = this.supprimerScenario.bind(this);
    this.onChangeSearchInput = this.onChangeSearchInput.bind(this);
  }

  async componentDidMount() {
    var tousLesScenarios = [];
    var scenarios_liste = [];
    var questions_liste = [];
    var composants_liste = [];
    var packs_liste = [];
    var appartientSQ_liste = [];
    var appartientSC_liste = [];
    var appartientSP_liste = [];

    // Récupération de tous les scénarios
    await axios.get(`http://api/scenario/tousLesScenarios`).then((res) => {
      scenarios_liste = res.data;
    });

    // Récupération de toutes les questions
    await axios.get(`http://api/question/toutesLesQuestions`).then((res) => {
      questions_liste = res.data;
    });

    // Récupération de tous les composants
    await axios.get(`http://api/composant/tousLesComposants`).then((res) => {
      composants_liste = res.data;
    });

    // Récupération de tous les packs
    await axios.get(`http://api/pack/tousLesPacks`).then((res) => {
      packs_liste = res.data;
    });

    // Récupération de toutes les liaisons Scenario-Question
    await axios.get(`http://api/appartientsq/tousLesElements`).then((res) => {
      appartientSQ_liste = res.data;
    });

    // Récupération de toutes les liaisons Scenario-Composant
    await axios.get(`http://api/appartientsc/tousLesElements`).then((res) => {
      appartientSC_liste = res.data;
    });

    // Récupération de toutes les liaisons Scenario-Pack
    await axios.get(`http://api/appartientsp/tousLesElements`).then((res) => {
      appartientSP_liste = res.data;
    });

    // Recréation des scénarios
    scenarios_liste.forEach((scenario) => {
      var questionsScenario = [];
      var composantsScenario = [];
      var packsScenario = [];

      // Récupération de ses questions
      appartientSQ_liste.forEach((liaison) => {
        if (liaison.unScenario === scenario.idScenario) {
          questions_liste.forEach((question) => {
            if (question.idQuestion === liaison.uneQuestion) {
              questionsScenario.push({
                idQuestion: question.idQuestion,
                nomQuestion: question.nomQuestion,
              });
            }
          });
        }
      });

      // Récupération de ses composants
      appartientSC_liste.forEach((liaison) => {
        if (liaison.unScenario === scenario.idScenario) {
          composants_liste.forEach((composant) => {
            if (composant.idComposant === liaison.unComposant) {
              composantsScenario.push({
                idComposant: composant.idComposant,
                nomComposant: composant.nomComposant,
                uniteComposant: composant.uniteComposant,
                prixComposant: composant.prixComposant,
                quantite: liaison.quantite,
              });
            }
          });
        }
      });

      // Récupération de ses packs
      appartientSP_liste.forEach((liaison) => {
        if (liaison.unScenario === scenario.idScenario) {
          packs_liste.forEach((pack) => {
            if (pack.idPack === liaison.unPack) {
              packsScenario.push({
                idPack: pack.idPack,
                nomPack: pack.nomPack,
                quantite: liaison.quantite,
              });
            }
          });
        }
      });

      tousLesScenarios.push({
        idScenario: scenario.idScenario,
        nomScenario: scenario.nomScenario,
        questions: questionsScenario,
        composants: composantsScenario,
        packs: packsScenario,
      });
    });

    this.setState({ scenarios: tousLesScenarios });
  }

  affichageInfoScenario(id) {
    this.setState({ idToDisplay: id });
  }

  /**
   * Envoi une requête de suppression au serveur
   */
  async supprimerScenario() {
    await axios
      .delete(`http://api/scenario/supprimerScenario/${this.state.idToDisplay}`)
      .then((res) => {
        console.log(res.data);
      });

    window.location.reload();
  }

  /**
   * Mettre à jour la liste des scénarios à afficher, suivant la recherche de l'utilisateur
   */
  async onChangeSearchInput() {
    var tousLesScenarios = [];
    var scenarios_liste = [];
    var questions_liste = [];
    var composants_liste = [];
    var packs_liste = [];
    var appartientSQ_liste = [];
    var appartientSC_liste = [];
    var appartientSP_liste = [];

    // Récupération de tous les scénarios
    await axios.get(`http://api/scenario/tousLesScenarios`).then((res) => {
      scenarios_liste = res.data;
    });

    // Récupération de toutes les questions
    await axios.get(`http://api/question/toutesLesQuestions`).then((res) => {
      questions_liste = res.data;
    });

    // Récupération de tous les composants
    await axios.get(`http://api/composant/tousLesComposants`).then((res) => {
      composants_liste = res.data;
    });

    // Récupération de tous les packs
    await axios.get(`http://api/pack/tousLesPacks`).then((res) => {
      packs_liste = res.data;
    });

    // Récupération de toutes les liaisons Scenario-Question
    await axios.get(`http://api/appartientsq/tousLesElements`).then((res) => {
      appartientSQ_liste = res.data;
    });

    // Récupération de toutes les liaisons Scenario-Composant
    await axios.get(`http://api/appartientsc/tousLesElements`).then((res) => {
      appartientSC_liste = res.data;
    });

    // Récupération de toutes les liaisons Scenario-Pack
    await axios.get(`http://api/appartientsp/tousLesElements`).then((res) => {
      appartientSP_liste = res.data;
    });

    // Recréation des scénarios
    scenarios_liste.forEach((scenario) => {
      var questionsScenario = [];
      var composantsScenario = [];
      var packsScenario = [];

      // Récupération de ses questions
      appartientSQ_liste.forEach((liaison) => {
        if (liaison.unScenario === scenario.idScenario) {
          questions_liste.forEach((question) => {
            if (question.idQuestion === liaison.uneQuestion) {
              questionsScenario.push({
                idQuestion: question.idQuestion,
                nomQuestion: question.nomQuestion,
              });
            }
          });
        }
      });

      // Récupération de ses composants
      appartientSC_liste.forEach((liaison) => {
        if (liaison.unScenario === scenario.idScenario) {
          composants_liste.forEach((composant) => {
            if (composant.idComposant === liaison.unComposant) {
              composantsScenario.push({
                idComposant: composant.idComposant,
                nomComposant: composant.nomComposant,
                uniteComposant: composant.uniteComposant,
                prixComposant: composant.prixComposant,
                quantite: liaison.quantite,
              });
            }
          });
        }
      });

      // Récupération de ses packs
      appartientSP_liste.forEach((liaison) => {
        if (liaison.unScenario === scenario.idScenario) {
          packs_liste.forEach((pack) => {
            if (pack.idPack === liaison.unPack) {
              packsScenario.push({
                idPack: pack.idPack,
                nomPack: pack.nomPack,
                quantite: liaison.quantite,
              });
            }
          });
        }
      });

      tousLesScenarios.push({
        idScenario: scenario.idScenario,
        nomScenario: scenario.nomScenario,
        questions: questionsScenario,
        composants: composantsScenario,
        packs: packsScenario,
      });
    });

    this.setState({ scenarios: tousLesScenarios });

    if (document.getElementById("search-input").value.length !== 0) {
      // Mise à jour de la liste des scénarios
      scenarios_liste = [];

      this.state.scenarios.forEach((scenario) => {
        var wordFound = false;

        if (
          scenario.nomScenario != null &&
          scenario.nomScenario
            .toUpperCase()
            .includes(
              document.getElementById("search-input").value.toUpperCase()
            )
        ) {
          wordFound = true;
        } else {
          scenario.questions.forEach((question) => {
            if (
              question.nomQuestion != null &&
              question.nomQuestion
                .toUpperCase()
                .includes(
                  document.getElementById("search-input").value.toUpperCase()
                )
            ) {
              wordFound = true;
            }
          });

          if (!wordFound) {
            scenario.composants.forEach((composant) => {
              if (
                (composant.nomComposant != null &&
                  composant.nomComposant
                    .toUpperCase()
                    .includes(
                      document
                        .getElementById("search-input")
                        .value.toUpperCase()
                    )) ||
                (composant.uniteComposant != null &&
                  composant.uniteComposant
                    .toUpperCase()
                    .includes(
                      document
                        .getElementById("search-input")
                        .value.toUpperCase()
                    )) ||
                (composant.prixComposant != null &&
                  composant.prixComposant
                    .toUpperCase()
                    .includes(
                      document
                        .getElementById("search-input")
                        .value.toUpperCase()
                    ))
              ) {
                wordFound = true;
              }
            });
          }

          if (!wordFound) {
            scenario.packs.forEach((pack) => {
              if (
                pack.nomPack != null &&
                pack.nomPack
                  .toUpperCase()
                  .includes(
                    document.getElementById("search-input").value.toUpperCase()
                  )
              ) {
                wordFound = true;
              }
            });
          }
        }

        if (wordFound) {
          var questions_liste = [];
          scenario.questions.forEach((question) => {
            questions_liste.push({
              idQuestion: question.idQuestion,
              nomQuestion: question.nomQuestion,
            });
          });

          var composants_liste = [];
          scenario.composants.forEach((composant) => {
            composants_liste.push({
              idComposant: composant.idComposant,
              nomComposant: composant.nomComposant,
            });
          });

          var packs_liste = [];
          scenario.packs.forEach((pack) => {
            packs_liste.push({
              idPack: pack.idPack,
              nomPack: pack.nomPack,
            });
          });

          scenarios_liste.push({
            idScenario: scenario.idScenario,
            nomScenario: scenario.nomScenario,
            questions: questions_liste,
            composants: composants_liste,
            packs: packs_liste,
          });
        }
      });

      this.setState({ scenarios: scenarios_liste });
    }
  }

  render() {
    return (
      <Fragment>
        <Navigation />
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
                  <AffichageScenario
                    key={this.state.idToDisplay}
                    state={this.state}
                  />
                </form>
              </MDBCol>
            </Col>
            <Col className="col3-button" md={2}>
              <Link class="btn btn-light" to="/scenario/nouveauscenario">
                Nouveau scénario
              </Link>
              <Link
                className="btn btn-light"
                to={{
                  pathname: "/scenario/modifier",
                  params: {
                    idToDisplay: this.state.idToDisplay,
                    scenarios: this.state.scenarios.filter(
                      (scenario) =>
                        scenario.idScenario === this.state.idToDisplay
                    ),
                  },
                }}
              >
                Modifier scénario
              </Link>
              <Supprimer action={this.supprimerScenario} />
              <Link class="btn btn-light" to="/scenario/composantspacks">
                Composants/Packs
              </Link>
            </Col>
          </Row>
          <Row>
            <Col>
              {" "}
              <br></br> <br></br> <br></br> <br></br> <br></br> <br></br>{" "}
            </Col>
          </Row>
        </Container>
      </Fragment>
    );
  }
}
