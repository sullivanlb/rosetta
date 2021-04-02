import React, { Component, Fragment } from "react";
import { Row, Col, Container } from "react-bootstrap";
import { MDBCol, MDBIcon } from "mdbreact";
import Supprimer from "../composants/SupprimerComposantPack";
import "../style/ComposantsPacks.css";
import ListeComposants from "../composants/ListeComposants";
import ListePacks from "../composants/ListePacks";
import AffichageComposantsPacks from "../composants/AffichageComposantsPacks";
import Navigation from "../composants/Navigation";
import axios from "axios";

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
        composants: [],
      },
      state2: {
        idToDisplay: 2,
        packs: [],
      }
    };

    this.typeSelectionne = "";
    this.tab = this.state.state1.composants;
    this.idElement = this.state.state1.idToDisplay;

    this.changeStateX = this.changeStateX.bind(this);
    this.onChangeSearchInput = this.onChangeSearchInput.bind(this);
    this.supprimerComposantPack = this.supprimerComposantPack.bind(this);
  }

  async componentDidMount() {

    // Récupération des composants
    var composants_liste = [];
    await axios.get(`http://api/composant/tousLesComposants`).then((res) => {
      composants_liste = res.data;
    });

    // Récupération des packs
    var packs_liste = [];
    await axios.get(`http://api/pack/tousLesPacks`).then((res) => {
      packs_liste = res.data;
    });

    // Récupération de toutes les liaison Pack-Composant
    var appartientPC_liste = [];
    await axios.get(`http://api/appartientpc/tousLesElements`).then((res) => {
      appartientPC_liste = res.data;
    });

    // Recréation des composants
    var composants = [];
    composants_liste.map((composant) => {
      composants.push({
        idComposant: composant.idComposant,
        nomComposant: composant.nomComposant,
        uniteComposant: composant.uniteComposant,
        prixComposant: composant.prixComposant,
        type: "composant",
      });
    });

    // Recréation des packs
    var packs = [];
    packs_liste.map((pack) => {
      var composantsPack = [];

      // Récupération de ses questions
      appartientPC_liste.map((liaison) => {
        if (liaison.unPack === pack.idPack) {
          composants_liste.map((composant) => {
            if (composant.idComposant === liaison.unComposant) {
              if (liaison.quantite !== undefined) {
                composantsPack.push({
                  idComposant: composant.idComposant,
                  nomComposant: composant.nomComposant,
                  uniteComposant: composant.uniteComposant,
                  prixComposant: composant.prixComposant,
                  quantite: liaison.quantite,
                  type: "composant",
                });
              } else {
                composantsPack.push({
                  idComposant: composant.idComposant,
                  nomComposant: composant.nomComposant,
                  uniteComposant: composant.uniteComposant,
                  prixComposant: composant.prixComposant,
                  quantite: 1,
                  type: "composant",
                });
              }
            }
          });
        }
      });

      packs.push({
        idPack: pack.idPack,
        nomPack: pack.nomPack,
        composants: composantsPack,
        type: "pack",
      });
    });

    var state1_tmp = this.state.state1;
    state1_tmp.composants = composants;
    var state2_tmp = this.state.state2;
    state2_tmp.packs = packs;

    this.setState({ state1: state1_tmp });
    this.setState({ state2: state2_tmp });
  }

  changeStateX(type, id) {
    if (type === "composant") {
      this.setState({ stateX: 1 });
      var stateTmp = this.state.state1;
      stateTmp.idToDisplay = id;
      this.setState({ state1: stateTmp });
      this.tab = this.state.state1.composants;
      this.idElement = this.state.state1.idToDisplay;
      this.typeSelectionne = "composant";
    } else {
      this.setState({ stateX: 2 });
      var stateTmp = this.state.state2;
      stateTmp.idToDisplay = id;
      this.setState({ state2: stateTmp });
      this.tab = this.state.state2.packs;
      this.idElement = this.state.state2.idToDisplay;
      this.typeSelectionne = "pack";
    }
  }

  /**
   * Mettre à jour la liste des composants et packs à afficher, suivant la recherche de l'utilisateur
   */
   async onChangeSearchInput() {

    // Récupération des composants
    var composants_liste = [];
    await axios.get(`http://api/composant/tousLesComposants`).then((res) => {
      composants_liste = res.data;
    });

    // Récupération des packs
    var packs_liste = [];
    await axios.get(`http://api/pack/tousLesPacks`).then((res) => {
      packs_liste = res.data;
    });

    // Récupération de toutes les liaison Pack-Composant
    var appartientPC_liste = [];
    await axios.get(`http://api/appartientpc/tousLesElements`).then((res) => {
      appartientPC_liste = res.data;
    });

    // Recréation des composants
    var composants = [];
    composants_liste.map((composant) => {
      composants.push({
        idComposant: composant.idComposant,
        nomComposant: composant.nomComposant,
        uniteComposant: composant.uniteComposant,
        prixComposant: composant.prixComposant,
        type: "composant",
      });
    });

    // Recréation des packs
    var packs = [];
    packs_liste.map((pack) => {
      var composantsPack = [];

      // Récupération de ses questions
      appartientPC_liste.map((liaison) => {
        if (liaison.unPack == pack.idPack) {
          composants_liste.map((composant) => {
            if (composant.idComposant == liaison.unComposant) {
              if (liaison.quantite != undefined) {
                composantsPack.push({
                  idComposant: composant.idComposant,
                  nomComposant: composant.nomComposant,
                  uniteComposant: composant.uniteComposant,
                  prixComposant: composant.prixComposant,
                  quantite: liaison.quantite,
                  type: "composant",
                });
              } else {
                composantsPack.push({
                  idComposant: composant.idComposant,
                  nomComposant: composant.nomComposant,
                  uniteComposant: composant.uniteComposant,
                  prixComposant: composant.prixComposant,
                  quantite: 1,
                  type: "composant",
                });
              }
            }
          });
        }
      });

      packs.push({
        idPack: pack.idPack,
        nomPack: pack.nomPack,
        composants: composantsPack,
        type: "pack",
      });
    });

    var state1_tmp = this.state.state1;
    state1_tmp.composants = composants;
    var state2_tmp = this.state.state2;
    state2_tmp.packs = packs;

    this.setState({ state1: state1_tmp });
    this.setState({ state2: state2_tmp });

    if (document.getElementById("search-input").value.length != 0) {
      // Mise à jour de la liste des composants
      var stateTmp = this.state.state1;
      composants_liste = [];

      this.state.state1.composants.map((composant) => {
        var wordFound = false;

        if ((composant.nomComposant != null && composant.nomComposant.toUpperCase().includes(document.getElementById("search-input").value.toUpperCase()))
            || (composant.uniteComposant != null && composant.uniteComposant.toUpperCase().includes(document.getElementById("search-input").value.toUpperCase()))
            || (composant.prixComposant != null && composant.prixComposant.toUpperCase().includes(document.getElementById("search-input").value.toUpperCase()))) {
          wordFound = true;
        }

        if (wordFound) {
          composants_liste.push({
            idComposant: composant.idComposant,
            nomComposant: composant.nomComposant,
            uniteComposant: composant.uniteComposant,
            prixComposant: composant.prixComposant,
            type: "composant",
          });
        }
      });

      stateTmp.composants = composants_liste;
      this.setState({ state1: stateTmp });

      // Mise à jour de la liste des packs
      stateTmp = this.state.state2;
      var pack_liste = [];

      this.state.state2.packs.map((pack) => {
        var wordFound = false;

        if ((pack.nomPack != null && pack.nomPack.toUpperCase().includes(document.getElementById("search-input").value.toUpperCase()))) {
          wordFound = true;
        }

        pack.composants.map((composant) => {
          if ((composant.nomComposant != null && composant.nomComposant.toUpperCase().includes(document.getElementById("search-input").value.toUpperCase()))
              || (composant.uniteComposant != null && composant.uniteComposant.toUpperCase().includes(document.getElementById("search-input").value.toUpperCase()))
              || (composant.prixComposant != null && composant.prixComposant.toUpperCase().includes(document.getElementById("search-input").value.toUpperCase()))) {
            wordFound = true;
          }
        });

        if (wordFound) {
          pack_liste.push({
            idPack: pack.idPack,
            nomPack: pack.nomPack,
            type: "pack",
            composants: [],
          });
        }
      });

      stateTmp.packs = pack_liste;
      this.setState({ state2: stateTmp });
    }
  }

  /**
   * Envoi une requête de suppression au serveur
   */
   async supprimerComposantPack() {
    if (this.typeSelectionne === "composant") {
      await axios
      .delete(`http://api/composant/supprimerComposant/${this.state.state1.idToDisplay}`)
      .then((res) => {
        console.log(res.data);
      });

    window.location.reload();
    } else if (this.typeSelectionne === "pack") {
      await axios
      .delete(`http://api/pack/supprimerPack/${this.state.state2.idToDisplay}`)
      .then((res) => {
        console.log(res.data);
      });

    window.location.reload();
    }
  }

  render() {
    return (
      <Fragment>
        <Navigation/>
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
                    id="search-input"
                    className="form-control form-control-sm ml-3 w-75"
                    type="text"
                    placeholder="Rechercher un packs/composants"
                    aria-label="Rechercher"
                    onChange={this.onChangeSearchInput}
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
              <Supprimer 
                action={this.supprimerComposantPack}/>
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
