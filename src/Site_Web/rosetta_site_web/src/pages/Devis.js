import React, { Component, Fragment } from "react";
import { Row, Col, Container } from "react-bootstrap";
import { MDBCol, MDBIcon } from "mdbreact";
import { Link } from "react-router-dom";
import Supprimer from "../composants/SupprimerDevis";
import "../style/Devis.css";
import ListeDevis from "../composants/ListeDevis";
import AffichageDevis from "../composants/AffichageDevis";
import Navigation from "../composants/Navigation";
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
  constructor(props) {
    super(props);

    this.state = {
      idToDisplay: 1,
      devis: [],
    };

    this.affichageInfoDevis = this.affichageInfoDevis.bind(this);
    this.onChangeSearchInput = this.onChangeSearchInput.bind(this);
    this.supprimerDevis = this.supprimerDevis.bind(this);
  }

  async componentDidMount() {
    var tousLesDevis = [];
    var devis_liste = [];
    var clients_liste = [];
    var composants_liste = [];
    var packs_liste = [];
    var appartientCD_liste = [];
    var appartientDC_liste = [];
    var appartientDP_liste = [];
    var appartientPC_liste = [];

    // Récupération de tous les devis
    await axios.get(`http://api/devis/tousLesDevis`).then((res) => {
      devis_liste = res.data;
    });

    // Récupération de tous les clients
    await axios.get(`http://api/client/tousLesClients`).then((res) => {
      clients_liste = res.data;
    });

    // Récupération de tous les composants
    await axios.get(`http://api/composant/tousLesComposants`).then((res) => {
      composants_liste = res.data;
    });

    // Récupération de tous les packs
    await axios.get(`http://api/pack/tousLesPacks`).then((res) => {
      packs_liste = res.data;
    });

    // Récupération de toutes les liaisons Client-Devis
    await axios.get(`http://api/appartientcd/tousLesElements`).then((res) => {
      appartientCD_liste = res.data;
    });

    // Récupération de toutes les liaisons Devis-Composant
    await axios.get(`http://api/appartientdc/tousLesElements`).then((res) => {
      appartientDC_liste = res.data;
    });

    // Récupération de toutes les liaisons Devis-Pack
    await axios.get(`http://api/appartientdp/tousLesElements`).then((res) => {
      appartientDP_liste = res.data;
    });

    // Récupération de toutes les liaisons Pack-Composant
    await axios.get(`http://api/appartientpc/tousLesElements`).then((res) => {
      appartientPC_liste = res.data;
    });

    // Recréation des devis
    devis_liste.map((devis) => {
      var nomDuClient = "";
      var prenomDuClient = "";
      var prixDevis = 0;

      // Récupération du nom du client
      appartientCD_liste.map((liaison) => {
        if (liaison.unDevis == devis.idDevis) {
          clients_liste.map((client) => {
            if (client.idClient == liaison.unClient) {
              nomDuClient = client.nomClient;
              prenomDuClient = client.prenomClient;
            }
          });
        }
      });

      // Comptage du prix des composants
      appartientDC_liste.map((liaison) => {
        if (liaison.unDevis == devis.idDevis) {
          composants_liste.map((composant) => {
            if (composant.idComposant == liaison.unComposant) {
              prixDevis = prixDevis + (parseFloat(composant.prixComposant) * liaison.quantite);
            }
          });
        }
      });

      // Comptage du prix des packs
      appartientDP_liste.map((liaison) => {
        if (liaison.unDevis == devis.idDevis) {
          packs_liste.map((pack) => {
            if (pack.idPack == liaison.unPack) {
              appartientPC_liste.map((liaison2) => {
                if (liaison2.unPack == pack.idPack) {
                  composants_liste.map((composant) => {
                    if (composant.idComposant == liaison2.unComposant) {
                      prixDevis = prixDevis + (parseFloat(composant.prixComposant) * liaison2.quantite * liaison.quantite);
                    }
                  });
                }
              });
            }
          });
        }
      });

      tousLesDevis.push({
        idDevis: devis.idDevis,
        nomDevis: devis.nomDevis,
        descriptionDevis: devis.descriptionDevis,
        dureeDevis: devis.dureeDevis,
        dateEditionDevis: devis.dateEditionDevis,
        dateTravauxDevis: devis.dateTravauxDevis,
        nomClient: nomDuClient,
        prenomClient: prenomDuClient,
        prixDevis: prixDevis,
      });
    });

    this.setState({ devis: tousLesDevis });
  }

  /**
   * Envoi une requête de suppression au serveur
   */
   async supprimerDevis() {
    await axios
      .delete(`http://api/devis/supprimerDevis/${this.state.idToDisplay}`)
      .then((res) => {
        console.log(res.data);
      });

    // window.location.reload();
  }

  /**
   * Modifier l'état du devis à afficher
   *
   * @param {int} id l'identifiant du devis à afficher
   */
  affichageInfoDevis(id){
    this.setState({ idToDisplay: id});
  }

  /**
   * Mettre à jour la liste des devis à afficher, suivant la recherche de l'utilisateur
   */
  async onChangeSearchInput() {
    var tousLesDevis = [];
    var devis_liste = [];
    var clients_liste = [];
    var composants_liste = [];
    var packs_liste = [];
    var appartientCD_liste = [];
    var appartientDC_liste = [];
    var appartientDP_liste = [];
    var appartientPC_liste = [];

    // Récupération de tous les devis
    await axios.get(`http://api/devis/tousLesDevis`).then((res) => {
      devis_liste = res.data;
    });

    // Récupération de tous les clients
    await axios.get(`http://api/client/tousLesClients`).then((res) => {
      clients_liste = res.data;
    });

    // Récupération de tous les composants
    await axios.get(`http://api/composant/tousLesComposants`).then((res) => {
      composants_liste = res.data;
    });

    // Récupération de tous les packs
    await axios.get(`http://api/pack/tousLesPacks`).then((res) => {
      packs_liste = res.data;
    });

    // Récupération de toutes les liaisons Client-Devis
    await axios.get(`http://api/appartientcd/tousLesElements`).then((res) => {
      appartientCD_liste = res.data;
    });

    // Récupération de toutes les liaisons Devis-Composant
    await axios.get(`http://api/appartientdc/tousLesElements`).then((res) => {
      appartientDC_liste = res.data;
    });

    // Récupération de toutes les liaisons Devis-Pack
    await axios.get(`http://api/appartientdp/tousLesElements`).then((res) => {
      appartientDP_liste = res.data;
    });

    // Récupération de toutes les liaisons Pack-Composant
    await axios.get(`http://api/appartientpc/tousLesElements`).then((res) => {
      appartientPC_liste = res.data;
    });

    // Recréation des devis
    devis_liste.map((devis) => {
      var nomDuClient = "";
      var prenomDuClient = "";
      var prixDevis = 0;

      // Récupération du nom du client
      appartientCD_liste.map((liaison) => {
        if (liaison.unDevis == devis.idDevis) {
          clients_liste.map((client) => {
            if (client.idClient == liaison.unClient) {
              nomDuClient = client.nomClient;
              prenomDuClient = client.prenomClient;
            }
          });
        }
      });

      // Comptage du prix des composants
      appartientDC_liste.map((liaison) => {
        if (liaison.unDevis == devis.idDevis) {
          composants_liste.map((composant) => {
            if (composant.idComposant == liaison.unComposant) {
              prixDevis = prixDevis + (parseFloat(composant.prixComposant) * liaison.quantite);
            }
          });
        }
      });

      // Comptage du prix des packs
      appartientDP_liste.map((liaison) => {
        if (liaison.unDevis == devis.idDevis) {
          packs_liste.map((pack) => {
            if (pack.idPack == liaison.unPack) {
              appartientPC_liste.map((liaison2) => {
                if (liaison2.unPack == pack.idPack) {
                  composants_liste.map((composant) => {
                    if (composant.idComposant == liaison2.unComposant) {
                      prixDevis = prixDevis + (parseFloat(composant.prixComposant) * liaison2.quantite * liaison.quantite);
                    }
                  });
                }
              });
            }
          });
        }
      });

      tousLesDevis.push({
        idDevis: devis.idDevis,
        nomDevis: devis.nomDevis,
        descriptionDevis: devis.descriptionDevis,
        dureeDevis: devis.dureeDevis,
        dateEditionDevis: devis.dateEditionDevis,
        dateTravauxDevis: devis.dateTravauxDevis,
        nomClient: nomDuClient,
        prenomClient: prenomDuClient,
        prixDevis: prixDevis,
      });
    });

    this.setState({ devis: tousLesDevis });

    if (document.getElementById("search-input").value.length != 0) {
      var devis_liste = [];

      this.state.devis.map((devis) => {
        var wordFound = false;

        if ((devis.nomDevis != null && devis.nomDevis.toUpperCase().includes(document.getElementById("search-input").value.toUpperCase()))
            || (devis.descriptionDevis != null && devis.descriptionDevis.toUpperCase().includes(document.getElementById("search-input").value.toUpperCase()))
            || (devis.dureeDevis != null && devis.dureeDevis.toUpperCase().includes(document.getElementById("search-input").value.toUpperCase()))
            || (devis.dateEditionDevis != null && devis.dateEditionDevis.toUpperCase().includes(document.getElementById("search-input").value.toUpperCase()))
            || (devis.dateTravauxDevis != null && devis.dateTravauxDevis.toUpperCase().includes(document.getElementById("search-input").value.toUpperCase()))
            || (devis.nomClient != null && devis.nomClient.toUpperCase().includes(document.getElementById("search-input").value.toUpperCase()))
            || (devis.prenomClient != null && devis.prenomClient.toUpperCase().includes(document.getElementById("search-input").value.toUpperCase()))
            || (devis.prix != null && devis.prix.toUpperCase().includes(document.getElementById("search-input").value.toUpperCase()))) {
          wordFound = true;
        }

        if (wordFound) {
          devis_liste.push({
            idDevis: devis.idDevis,
            nomDevis: devis.nomDevis,
            descriptionDevis: devis.descriptionDevis,
            dureeDevis: devis.dureeDevis,
            dateEditionDevis: devis.dateEditionDevis,
            dateTravauxDevis: devis.dateTravauxDevis,
            nomClient: devis.nomClient,
            prenomClient: devis.prenomClient,
            prixDevis: devis.prixDevis,
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
        <Navigation/>
        <h3>Devis</h3>
        <img className="ImagelogoPlombier" src="/img/logo-plombiers.png" alt="" />
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
              <ListeDevis state={this.state} action={this.affichageInfoDevis} />
            </Col>

            <Col className="col2-affichageDevis" md={6}>
              <MDBCol md="12">
                <form className="form-inline mt-4 mb-4">
                  <AffichageDevis
                    key={this.state.idToDisplay}
                    state={this.state}
                  />
                </form>
              </MDBCol>
            </Col>

            <Col className="col2-ButtonDevis" md={2}>
              <Link className="btn btn-light" to="/devis/nouveau">
                Nouveau Devis
              </Link>
              <Link
                className="btn btn-light"
                to={{
                  pathname: "/devis/modifier",
                  params: {
                    idToDisplay: this.state.idToDisplay,
                    devis: this.state.devis.filter(
                      (devis) => devis.idDevis === this.state.idToDisplay
                    ),
                  },
                }}
              >
                Modifier devis
              </Link>
              <Supprimer action={this.supprimerDevis} />
            </Col>
          </Row>
        </Container>
      </Fragment>
    );
  }
}
