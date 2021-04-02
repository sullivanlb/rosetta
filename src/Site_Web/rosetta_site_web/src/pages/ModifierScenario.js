import React, { Component, Fragment } from "react";
import {
  Row,
  Col,
  Container,
  FormLabel,
  FormControl,
  Button,
} from "react-bootstrap";
import { MDBCol, MDBIcon } from "mdbreact";
import "../style/Scenario.css";
import "../style/NouveauClient.css";
import ListeComposantsNouveauScenario from "../composants/ListeComposantsNouveauScenario";
import ListePacksNouveauScenario from "../composants/ListePacksNouveauScenario";
import AffichageQuestions from "../composants/AffichageQuestions";
import AffichageNomScenario from "../composants/AffichageNomScenario";
import AffichageComposantsPacksNouveauScenario from "../composants/AffichageComposantsPacksNouveauScenario";
import Navigation from "../composants/Navigation";
import axios from "axios";

/**
 * @description Ce composant représente la page pour modifier un scenario
 *
 * @author Christophe GARCIA
 */
export default class ModifierScenario extends Component {
  constructor(props) {
    super(props);

    this.state = {
      composants: [],
      composantsRecherches: [],
      packs: [],
      packsRecherches: [],
      scenario: {
        idScenario: this.props.location.params.scenarios[0].idScenario,
        nomScenario: this.props.location.params.scenarios[0].nomScenario,
        questions: this.props.location.params.scenarios[0].questions,
      },
    };

    this.addComposantPack = this.addComposantPack.bind(this);
    this.supprimerScenario = this.supprimerScenario.bind(this);
    this.onChangeSearchInput = this.onChangeSearchInput.bind(this);
    this.handleKeyPressQuestion = this.handleKeyPressQuestion.bind(this);
    this.handleKeyPressNom = this.handleKeyPressNom.bind(this);
  }

  async componentDidMount() {
    await axios.get(`http://api/composant/tousLesComposants`).then((res) => {
      var composantsBDD = res.data;
      var composants = [];
      composantsBDD.map((composant) => {
        var estAjoute = false;
        var quantite = 0;
        this.props.location.params.scenarios[0].composants.forEach(
          (composant2) => {
            if (composant.idComposant === composant2.idComposant) {
              estAjoute = true;
              quantite = composant2.quantite;
            }
          }
        );

        if (estAjoute) {
          composants.push({
            idComposant: composant.idComposant,
            nomComposant: composant.nomComposant,
            uniteComposant: composant.uniteComposant,
            prixComposant: composant.prixComposant,
            type: "composant",
            quantite: quantite,
            ajoute: estAjoute,
          });
        } else {
          composants.push({
            idComposant: composant.idComposant,
            nomComposant: composant.nomComposant,
            uniteComposant: composant.uniteComposant,
            prixComposant: composant.prixComposant,
            type: "composant",
            quantite: 0,
            ajoute: estAjoute,
          });
        }
      });
      this.setState({ composants: composants });
      this.setState({ composantsRecherches: composants });
    });

    await axios.get(`http://api/pack/tousLesPacks`).then((res) => {
      var packsBDD = res.data;
      var packs = [];
      packsBDD.map((pack) => {
        var estAjoute = false;
        var quantite = 0;
        this.props.location.params.scenarios[0].packs.forEach((pack2) => {
          if (pack.idPack === pack2.idPack) {
            estAjoute = true;
            quantite = pack2.quantite;
          }
        });

        if (estAjoute) {
          packs.push({
            idPack: pack.idPack,
            nomPack: pack.nomPack,
            type: "pack",
            quantite: quantite,
            ajoute: estAjoute,
          });
        } else {
          packs.push({
            idPack: pack.idPack,
            nomPack: pack.nomPack,
            type: "pack",
            quantite: 0,
            ajoute: estAjoute,
          });
        }
      });
      this.setState({ packs: packs });
      this.setState({ packsRecherches: packs });
    });
  }

  addComposantPack(type, id) {
    if (type === "composant") {
      var composants = this.state.composants;

      this.state.composants.map((composant) => {
        if (composant.idComposant === id) {
          if (composant.ajoute) {
            // Enlève le composant du nouveau scénario
            composants.map((composant) => {
              if (composant.idComposant === id) {
                composant.ajoute = false;
              }
            });
          } else {
            var quantite = -1;
            while (quantite < 0) {
              quantite = prompt("Combien en voulez-vous ?");
            }

            // Ajoute le composant au nouveau scénario
            composants.map((composant) => {
              if (composant.idComposant == id) {
                composant.ajoute = true;
                composant.quantite = quantite;
              }
            });
          }
        }
      });

      this.setState({ composants: composants });
    } else {
      var packs = this.state.packs;

      this.state.packs.map((pack) => {
        if (pack.idPack === id) {
          if (pack.ajoute) {
            // Enlève le pack du nouveau scénario
            packs.map((pack) => {
              if (pack.idPack === id) {
                pack.ajoute = false;
              }
            });
          } else {
            var quantite = -1;
            while (quantite < 0) {
              quantite = prompt("Combien en voulez-vous ?");
            }

            // Ajoute le pack au nouveau scénario
            packs.map((pack) => {
              if (pack.idPack === id) {
                pack.ajoute = true;
                pack.quantite = quantite;
              }
            });
          }
        }
      });

      this.setState({ packs: packs });
    }
  }

  /**
   * Envoi une requête de suppression au serveur
   */
  async supprimerScenario() {
    await axios
      .delete(
        `http://api/scenario/supprimerScenario/${this.state.scenario.idScenarioy}`
      )
      .then((res) => {
        console.log(res.data);
      });

    window.location.reload();
  }

  /**
   * Mettre à jour la liste des composants et packs à afficher, suivant la recherche de l'utilisateur
   */
  async onChangeSearchInput() {
    await axios.get(`http://api/composant/tousLesComposants`).then((res) => {
      var composantsBDD = res.data;
      var composants = [];
      composantsBDD.map((composant) => {
        composants.push({
          idComposant: composant.idComposant,
          nomComposant: composant.nomComposant,
          uniteComposant: composant.uniteComposant,
          prixComposant: composant.prixComposant,
          type: "composant",
          quantite: 0,
          ajoute: false,
        });
      });
      this.setState({ composantsRecherches: composants });
    });

    await axios.get(`http://api/pack/tousLesPacks`).then((res) => {
      var packsBDD = res.data;
      var packs = [];
      packsBDD.map((pack) => {
        packs.push({
          idPack: pack.idPack,
          nomPack: pack.nomPack,
          type: "pack",
          quantite: 0,
          ajoute: false,
        });
      });
      this.setState({ packsRecherches: packs });
    });

    if (document.getElementById("search-input").value.length !== 0) {
      // Mise à jour de la liste des composants
      var composants_liste = [];

      this.state.composantsRecherches.map((composant) => {
        if (
          composant.nomComposant != null &&
          composant.nomComposant
            .toUpperCase()
            .includes(
              document.getElementById("search-input").value.toUpperCase()
            )
        ) {
          composants_liste.push({
            idComposant: composant.idComposant,
            nomComposant: composant.nomComposant,
            uniteComposant: composant.uniteComposant,
            prixComposant: composant.prixComposant,
            type: "composant",
            quantite: 0,
            ajoute: false,
          });
        }
      });

      this.setState({ composantsRecherches: composants_liste });

      // Mise à jour de la liste des packs
      var packs_liste = [];

      this.state.packsRecherches.map((pack) => {
        if (
          pack.nomPack != null &&
          pack.nomPack
            .toUpperCase()
            .includes(
              document.getElementById("search-input").value.toUpperCase()
            )
        ) {
          packs_liste.push({
            idPack: pack.idPack,
            nomPack: pack.nomPack,
            type: "pack",
            quantite: 0,
            ajoute: false,
          });
        }
      });

      this.setState({ packsRecherches: packs_liste });
    }
  }

  handleKeyPressQuestion(event) {
    // Quand la touche ENTRER est tapée lorsqu'on écrit une nouvelle question
    if (event.charCode === 13) {
      var scenarioTmp = this.state.scenario;
      scenarioTmp.questions.push({ nomQuestion: event.target.value });
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

    // Id & Nom du scénario
    formData.append("nom", this.state.scenario.nomScenario);

    // Les questions du scénario
    formData.append("questionsTaille", this.state.scenario.questions.length);
    var i = 0;
    this.state.scenario.questions.map((question) => {
      formData.append("question" + i + "_nom", question.nomQuestion);
      i = i + 1;
    });

    // Les composants du scénario
    var i = 0;
    this.state.composants.map((composant) => {
      if (composant.ajoute) {
        formData.append("composant" + i + "_id", composant.idComposant);
        formData.append("composant" + i + "_quantite", composant.quantite);
        i = i + 1;
      }
    });
    formData.append("composantsTaille", i);

    // Les packs du scénario
    this.state.packs.map((pack) => {
      if (pack.ajoute) {
        formData.append("pack" + i + "_id", pack.idPack);
        formData.append("pack" + i + "_quantite", pack.quantite);
        i = i + 1;
      }
    });
    formData.append("packsTaille", i);

    await axios
      .delete(
        `http://api/scenario/supprimerScenario/${this.state.scenario.idScenario}`
      )
      .then((res) => {
        console.log(res.data);
      });

    await axios
      .post("http://api/scenario/ajoutScenario", formData)
      .then((res) => {
        console.log(res.data);
      });

    window.location = "/scenario";
  };

  render() {
    return (
      <Fragment>
        <Navigation />
        <AffichageNomScenario nom={this.state.scenario.nomScenario} />
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
              <Row>
                <Col>
                  {" "}
                  <br></br> <br></br>{" "}
                </Col>
              </Row>
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
                <br></br>
                <br></br>
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
                <AffichageQuestions scenario={this.state.scenario} />
              </Row>
              <Row>
                <br></br>
                <br></br>
              </Row>
              <Row>
                <AffichageComposantsPacksNouveauScenario state={this.state} />
              </Row>
            </Col>

            <Col className="col3-button" md={2}>
              <Button
                className="btn btn-light"
                onClick={this.handleSubmit.bind(this)}
              >
                Valider
              </Button>
              <button type="button" class="btn btn-light">
                <a
                  style={{ color: "black", "text-decoration": "none" }}
                  href="/scenario"
                >
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
