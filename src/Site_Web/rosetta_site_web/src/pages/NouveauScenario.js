import React, { Component, Fragment } from "react";
import { Row, Col, Container,FormLabel,FormControl, Form, Button } from "react-bootstrap";
import { MDBCol, MDBIcon } from "mdbreact";
import Supprimer from "../composants/SupprimerScenario";
import "../style/Scenario.css";
import ListeComposantsNouveauScenario from "../composants/ListeComposantsNouveauScenario";
import ListePacksNouveauScenario from "../composants/ListePacksNouveauScenario";
import AffichageQuestions from "../composants/AffichageQuestions";
import AffichageNomScenario from "../composants/AffichageNomScenario";
import axios from "axios";

/**
 * @description Ce composant représente la page pour créer un scénario
 *
 * @author Christophe GARCIA, Alice GONTARD
 */
export default class NouveauScenario extends Component {
  constructor(props) {
    super(props);

    this.state = {
      composants: [],
      packs: [],
      scenario: {
        nomScenario: "Faire en sorte de récupérer le nom du scénario en cours de modification",
        questions: [],
        composants: [],
        packs: [],
      },
    };

    this.addComposantPack = this.addComposantPack.bind(this);
    this.onChangeSearchInput = this.onChangeSearchInput.bind(this);
    this.handleKeyPressQuestion = this.handleKeyPressQuestion.bind(this);
    this.handleKeyPressNom = this.handleKeyPressNom.bind(this);
  }

  componentDidMount() {
    axios.get(`http://api/composant/tousLesComposants`)
      .then((res) => {
        const composants = res.data;
        this.setState({ composants: composants });
      });

    axios.get(`http://api/pack/tousLesPacks`)
      .then((res) => {
        const packs = res.data;
        this.setState({ packs: packs });
      });
  }

  addComposantPack(type, id) {
    // do nothing for now
    if (type === "composant") {
      // if (!composant.added) composant.added: true,
      // else composant.added: false,

      // add dans la liste Composants à gauche + créer AffichageComposantsNouveauScenario.js
    } else {
      // if (!pack.added) pack.added: true,
      // else pack.added: false,

      // add dans la liste Composants à gauche + créer AffichagePacksNouveauScenario.js
    }
  }

  /**
   * Mettre à jour la liste des composants et packs à afficher, suivant la recherche de l'utilisateur
   */
   async onChangeSearchInput() {
    await axios.get(`http://api/composant/tousLesComposants`).then((res) => {
      const composants = res.data;
      this.setState({ composants });
    });

    await axios.get(`http://api/pack/tousLesPacks`).then((res) => {
      const packs = res.data;
      this.setState({ packs });
    });

    if (document.getElementById("search-input").value.length != 0) {
      // Mise à jour de la liste des composants
      var composants_liste = [];

      this.state.composants.map((composant) => {
        if (composant.nomComposant != null && composant.nomComposant.toUpperCase().includes(document.getElementById("search-input").value.toUpperCase())) {
          composants_liste.push({
            idComposant: composant.idComposant,
            nomComposant: composant.nomComposant,
            uniteComposant: composant.uniteComposant,
            prixComposant: composant.prixComposant,
            type: "composant",
          });
        }
      });

      this.setState({ composants: composants_liste });

      // Mise à jour de la liste des packs
      var packs_liste = [];

      this.state.packs.map((pack) => {
        if (pack.nomPack != null && pack.nomPack.toUpperCase().includes(document.getElementById("search-input").value.toUpperCase())) {
          packs_liste.push({
            idPack: pack.idPack,
            nomPack: pack.nomPack,
            type: "pack",
          });
        }
      });

      this.setState({ packs: packs_liste });
    }
  }

  handleKeyPressQuestion(event) {
    // Quand la touche ENTRER est tapée lorsqu'on écrit une nouvelle question
    if (event.charCode === 13) {
      var scenarioTmp = this.state.scenario;
      scenarioTmp.questions.push({nomQuestion: event.target.value});
      this.setState({ scenario: scenarioTmp });
      document.getElementById("form-question").value = "";
    }
  }

  handleKeyPressNom(event) {
    // Quand la touche ENTRER est tapée lorsqu'on écrit le nom du scénario
    if (event.charCode === 13) {
      var scenarioTmp = this.state.scenario;
      scenarioTmp.nomScenario = event.target.value;
      this.setState({ scenario: scenarioTmp });
      document.getElementById("form-nom").value = "";
    }
  }

  handleSubmit = async (e) => {
    e.preventDefault();
    let formData = new FormData();
    
    // Nom du scénario
    formData.append("nom", this.state.scenario.nomScenario);

    // Les questions du scénario
    formData.append("questionsTaille", this.state.scenario.questions.length);
    var i = 0;
    this.state.scenario.questions.map((question) => {
      formData.append("question" + i + "_nom", question.nomQuestion);
      i = i + 1;
    });

    // Les composants du scénario
    formData.append("composantsTaille", this.state.scenario.composants.length);
    var i = 0;
    this.state.scenario.composants.map((composant) => {
      formData.append("composant" + i + "_nom", composant.nomComposant);
      formData.append("composant" + i + "_unite", composant.uniteComposant);
      formData.append("composant" + i + "_prix", composant.prixComposant);
      i = i + 1;
    });

    // Les packs du scénario
    formData.append("packsTaille", this.state.scenario.packs.length);
    var i = 0;
    this.state.scenario.packs.map((pack) => {
      formData.append("pack" + i + "_nom", pack.nomPack);
      i = i + 1;
    });

    await axios
      .post("http://api/scenario/ajoutScenario", formData)
      .then(res => {
        console.log(res.data);
      });

    // Retour sur la page des scénarios
    window.location = "/scenario";
  };

  render() {
    return (
      <Fragment>
        <AffichageNomScenario
          nom={this.state.scenario.nomScenario}
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
              <ListeComposantsNouveauScenario
                state={this.state}
                action={this.addComposantPack}
              />
              <Row><Col> <br></br>  <br></br> </Col></Row>
              <ListePacksNouveauScenario
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
            <button
              type="button"
              class="btn btn-light"
              onClick={this.handleSubmit.bind(this)}
            >
              Enregistrer
            </button>
              <Supprimer />
              <button
                type="button"
                class="btn btn-light"
              >
                <a style={{"color":"black","text-decoration":"none"}} href="/scenario">
                  Annuler
                </a>
              </button>
            </Col>
          </Row>
        </Container>
      </Fragment>
    );
  }
}
