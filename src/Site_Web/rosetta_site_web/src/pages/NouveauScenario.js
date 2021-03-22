import React, { Component, Fragment } from "react";
import { Row, Col, Container,FormLabel,FormControl, Form, Button } from "react-bootstrap";
import { MDBCol, MDBIcon } from "mdbreact";
import Supprimer from "../composants/SupprimerScenario";
import "../style/Scenario.css";
import ListeComposants from "../composants/ListeComposants";
import ListePacks from "../composants/ListePacks";
import AffichageQuestions from "../composants/AffichageQuestions";
import AffichageNomScenario from "../composants/AffichageNomScenario";
import axios from "axios";

/**
 * @description Ce composant représente la page pour créer un scénario
 *
 * @author Alice GONTARD, Christophe GARCIA
 */
export default class NouveauScenario extends Component {
  constructor(props) {
    super(props);

    this.state = {
      stateX: 1,
      state1: {
        idToDisplay: 1,
        composants: [],
      },
      state2: {
        idToDisplay: 2,
        packs: [],
      },
      scenario: {
        nom: "",
        questions: [],
        composants: [],
      },
    };

    this.addComposantPack = this.addComposantPack.bind(this);
    this.onChangeSearchInput = this.onChangeSearchInput.bind(this);
    this.handleKeyPressQuestion = this.handleKeyPressQuestion.bind(this);
    this.handleKeyPressNom = this.handleKeyPressNom.bind(this);
  }

  addComposantPack(type, id) {
    // do nothing for now
    if (type === "composants") {
      // if (selected) {
        var composantTmp = null;

        this.state.state1.composants.map((composant) => {
          if (composant.id == id) {
            composantTmp = composant;
          }
        });

        this.setState();
      //}
      // set background color : orange
      // add dans state.added genre (seulement l'id ou jsp)
    } else {
      // set background color : orange
      // add dans state.added genre (seulement l'id ou jsp)
    }
  }

  /**
   * Mettre à jour la liste des composants et packs à afficher, suivant la recherche de l'utilisateur
   */
   async onChangeSearchInput() {
    await axios.get(`http://api/composant/tousLesComposants`).then((res) => {
      const composants = res.data;
      var stateTmp = this.state.state1;
      stateTmp.composants = composants;
      this.setState({ state1: stateTmp });
    });

    await axios.get(`http://api/pack/tousLesPacks`).then((res) => {
      const packs = res.data;
      var stateTmp = this.state.state2;
      stateTmp.packs = packs;
      this.setState({ state2: stateTmp });
    });

    if (document.getElementById("search-input").value.length != 0) {
      // Mise à jour de la liste des composants
      var state1_tmp = this.state.state1;
      var composants_liste = [];

      this.state.state1.composants.map((composant) => {
        if (composant.nom != null && composant.nom.toUpperCase().includes(document.getElementById("search-input").value.toUpperCase())) {
          composants_liste.push({
            id: composant.id,
            nom: composant.nom,
            unite : composant.unite,
            prix : composant.prix,
            type: composant.type,
          });
        }
      });

      state1_tmp.composants = composants_liste;
      this.setState({ state1: state1_tmp });

      // Mise à jour de la liste des packs
      var state2_tmp = this.state.state2;
      var packs_liste = [];

      this.state.state2.packs.map((pack) => {
        if (pack.nom.toUpperCase().includes(document.getElementById("search-input").value.toUpperCase())) {
          packs_liste.push({
            id: pack.id,
            nom: pack.nom,
            type: pack.type,
          });
        }
      });

      state2_tmp.packs = packs_liste;
      this.setState({ state2: state2_tmp });
    }
  }

  handleKeyPressQuestion(event) {
    // Quand la touche ENTRER est tapée lorsqu'on écrit une nouvelle question
    if (event.charCode === 13) {
      var scenarioTmp = this.state.scenario;
      scenarioTmp.questions.push({nom: event.target.value});
      this.setState({ scenario: scenarioTmp });
      document.getElementById("form-question").value = "";
    }
  }

  handleKeyPressNom(event) {
    // Quand la touche ENTRER est tapée lorsqu'on écrit le nom du scénario
    if (event.charCode === 13) {
      var scenarioTmp = this.state.scenario;
      scenarioTmp.nom = event.target.value;
      this.setState({ scenario: scenarioTmp });
      document.getElementById("form-nom").value = "";
    }
  }

  render() {
    return (
      <Fragment>
        <h3>Nouveau scénario</h3>
        <AffichageNomScenario
          nom={this.state.scenario.nom}
        />
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
                      placeholder="Rechercher"
                      aria-label="Rechercher"
                      onChange={this.onChangeSearchInput}
                    />
                  </form>
                </MDBCol>
              </Row>
              <ListeComposants
                state={this.state}
                action={this.addComposantPack}
              />
              <Row><Col> <br></br>  <br></br> </Col></Row>
              <ListePacks
                state={this.state}
                action={this.addComposantPack}
              />
            </Col>
            <Col className="col2-Ajout" md={6}>
              <Row>
                <FormLabel>Nom : </FormLabel>
                <FormControl
                  id="form-nom"
                  placeholder="Entrer le nom du scénario"
                  onKeyPress={this.handleKeyPressNom}
                />
              </Row>
              <Row>
                <Row><br></br><br></br></Row>
              </Row>
              <Row>
                <FormLabel>Questions : </FormLabel>
                <FormControl
                  id="form-question"
                  placeholder="Entrer une nouvelle question pour ce scénario"
                  onKeyPress={this.handleKeyPressQuestion}
                />
              </Row>
              <Row>
                <AffichageQuestions
                  scenario={this.state.scenario}
                />
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
        </Container>
      </Fragment>
    );
  }
}
