import React, { Component, Fragment } from "react";
import {
  Col,
  Button,
  Form,
  Container,
  Row,
  FormLabel,
  FormControl,
  Table,
} from "react-bootstrap";
import "../style/NouveauDevis.css";
import axios from "axios";

/**
 * Ce composant représente la page permettant d'ajouter un nouveau devis
 *
 * @author Lucy Gastebois
 */
export default class NouveauDevis extends Component {
  constructor(props) {
    super(props);

    this.state = {
      clients: [],
      scenarios: [],
      composantsPacks: [],
      prixTotal: 0,
      tva: 20,
      inputs: {
        nomDevis: "",
        dateEdition: "",
        dateDebutTravaux: "",
        dureeDevis: "",
        descriptionDevis: "",
        leClient: "",
        leScenario: "",
      },
      idRow: 0,
      idRowModifiable: 0,
    };

    this.ajouterLigne = this.ajouterLigne.bind(this);
    this.supprimerLigne = this.supprimerLigne.bind(this);
    this.handleTVA = this.handleTVA.bind(this);
    this.handlePrix = this.handlePrix.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  async componentDidMount() {
    // Récupération de tous les clients
    await axios.get(`http://api/client/tousLesClients`).then((res) => {
      const clients = res.data;
      this.setState({ clients: clients });
    });

    // Récupération de tous les scénarios
    await axios.get(`http://api/scenario/tousLesScenarios`).then((res) => {
      const scenarios = res.data;
      this.setState({ scenarios: scenarios });
    });

    // Récupération de tous les composants
    var composants_liste = [];
    await axios.get(`http://api/composant/tousLesComposants`).then((res) => {
      composants_liste = res.data;
    });

    // Récupération de tous les packs
    var packs_liste = [];
    await axios.get(`http://api/pack/tousLesPacks`).then((res) => {
      packs_liste = res.data;
    });

    // Récupération de toutes les liaisons Pack-Composant
    var appartientPC_liste = [];
    await axios.get(`http://api/appartientpc/tousLesElements`).then((res) => {
      appartientPC_liste = res.data;
    });

    // Mise en place des composants
    var composantsPacks = [];
    composants_liste.map((composant) => {
      composantsPacks.push({
        id: composant.idComposant,
        ref: composant.nomComposant,
        description: composant.nomComposant,
        quantite: 1,
        unite: composant.uniteComposant,
        prix: 0,
        prixUnitaire: composant.prixComposant,
        type: "composant",
        garde: false,
        idRow: 0,
      });
    });

    // Mise en place des packs (avec le calcul de leur prix)
    packs_liste.map((pack) => {
      var prixPack = 0;
      appartientPC_liste.map((liaison) => {
        if (liaison.unPack === pack.idPack) {
          composants_liste.map((composant) => {
            if (composant.idComposant === liaison.unComposant) {
              prixPack =
                prixPack +
                parseFloat(composant.prixComposant) * liaison.quantite;
            }
          });
        }
      });

      composantsPacks.push({
        id: pack.idPack,
        ref: pack.nomPack,
        description: pack.nomPack,
        quantite: 1,
        unite: "",
        prix: 0,
        prixUnitaire: prixPack,
        type: "pack",
        garde: false,
        idRow: 0,
      });
    });

    this.setState({ composantsPacks: composantsPacks });
  }

  /**
   * Met à jour la quantité de composants/packs de la ligne modifiée
   *
   * @param {*} e
   */
  handleQuantite(id, type, e) {
    if (e.target.value >= 0) {
      var rows = this.state.composantsPacks;

      rows.filter((row) => row.id === id && row.type === type)[0].quantite =
        e.target.value;
      this.setState({ composantsPacks: rows });

      this.handlePrix(id, type);
    }
  }

  /**
   * Met à jour le prix total de la ligne modifiée
   *
   * @param {*} e
   */
  handlePrix(id, type) {
    var rows = this.state.composantsPacks;
    rows.map((row) => {
      if (row.id === id && row.type === type) {
        row.prix = parseFloat(row.prixUnitaire) * parseFloat(row.quantite);
      }
    });

    this.setState({ composantsPacks: rows });
    this.handlePrixTotal();
  }

  /**
   * Met à jour le prix total du devis
   *
   * @param {*} e
   */
  handlePrixTotal() {
    var total = 0;

    this.state.composantsPacks.map((row) => (total += parseFloat(row.prix)));

    this.setState({ prixTotal: total });
  }

  /**
   * Met à jour la TVA sur le devis
   *
   * @param {*} e
   */
  handleTVA(e) {
    this.setState({ tva: e.target.value });
  }

  /**
   * Prépare la mise à jour de la ligne du tableau
   *
   * @param {*} e
   */
  handleClick(e, idRow) {
    this.state.idRowModifiable = idRow;
  }

  /**
   * Met à jour la ligne du tableau
   *
   * @param {*} e
   */
  handleSelect(e, idRow) {
    var composantsPacks = this.state.composantsPacks;
    var myArray = e.target.value.split("-");
    var id = myArray[0].slice(0, -1);
    var nom = myArray[1].substring(id.length);
    var type = "";

    composantsPacks.map((composantPack) => {
      if (composantPack.idRow === this.state.idRowModifiable) {
        composantPack.garde = false;
        composantPack.idRow = 0;
        composantPack.prix = 0;
      }
    });

    composantsPacks.map((composantPack) => {
      if (composantPack.id === id && composantPack.ref === nom) {
        composantPack.garde = true;
        composantPack.idRow = this.state.idRowModifiable;
        composantPack.quantite = 1;
        type = composantPack.type;
      }
    });

    var i = 0;
    composantsPacks.map((composantPack) => {
      if (composantPack.ref === "") {
        composantsPacks.splice(i, 1);
      }
      i++;
    });

    this.setState({ composantsPacks: composantsPacks });

    this.handlePrix(id, type);
  }

  /**
   * Met à jour le nom du devis
   *
   * @param {*} e
   */
  handleNom = (e) => {
    this.setState((prevState) => ({
      inputs: { ...prevState.inputs, nomDevis: e.target.value },
    }));
  };

  /**
   * Met à jour la date d'édition du devis
   *
   * @param {*} e
   */
  handleDateEdition = (e) => {
    this.setState((prevState) => ({
      inputs: { ...prevState.inputs, dateEdition: e.target.value },
    }));
  };

  /**
   * Met à jour la date de travaux du devis
   *
   * @param {*} e
   */
  handleDebutTravaux = (e) => {
    this.setState((prevState) => ({
      inputs: { ...prevState.inputs, dateDebutTravaux: e.target.value },
    }));
  };

  /**
   * Met à jour la durée du devis
   *
   * @param {*} e
   */
  handleDuree = (e) => {
    this.setState((prevState) => ({
      inputs: { ...prevState.inputs, dureeDevis: e.target.value },
    }));
  };

  /**
   * Met à jour la description du devis
   *
   * @param {*} e
   */
  handleDescription = (e) => {
    this.setState((prevState) => ({
      inputs: { ...prevState.inputs, descriptionDevis: e.target.value },
    }));
  };

  /**
   * Met à jour le client du devis
   *
   * @param {*} e
   */
  handleClient = (e) => {
    this.setState((prevState) => ({
      inputs: { ...prevState.inputs, leClient: e.target.value },
    }));
  };

  /**
   * Ajoute les lignes (composants et packs) suivant le scénario sélectionné
   *
   * @param {*} e
   */
  async handleScenario(e) {
    this.setState((prevState) => ({
      inputs: { ...prevState.inputs, leScenario: e.target.value },
    }));

    var myArray = e.target.value.split("(");
    var idAndMore = myArray[myArray.length - 1];
    var myArray2 = idAndMore.split(")");
    var idScenario = myArray2[0];

    // Récupération de tous les composants
    var composants_liste = [];
    await axios.get(`http://api/composant/tousLesComposants`).then((res) => {
      composants_liste = res.data;
    });

    // Récupération de tous les packs
    var packs_liste = [];
    await axios.get(`http://api/pack/tousLesPacks`).then((res) => {
      packs_liste = res.data;
    });

    // Récupération de toutes les liaisons Scenario-Composant
    var appartientSC_liste = [];
    await axios.get(`http://api/appartientsc/tousLesElements`).then((res) => {
      appartientSC_liste = res.data;
    });

    // Récupération de toutes les liaisons Scenario-Pack
    var appartientSP_liste = [];
    await axios.get(`http://api/appartientsp/tousLesElements`).then((res) => {
      appartientSP_liste = res.data;
    });

    // Récupération de toutes les liaisons Pack-Composant
    var appartientPC_liste = [];
    await axios.get(`http://api/appartientpc/tousLesElements`).then((res) => {
      appartientPC_liste = res.data;
    });

    if (e.target.value === "Sélectionner un scénario") {
      // push tous les comp/pack comme componentDidMount
    } else {
      var composantsPacks = [];
      var idRow = 0;

      // Insertion des lignes de composants suivant le scénario
      composants_liste.map((composant) => {
        var appartient = false;

        appartientSC_liste.map((liaison) => {
          // Si le composant appartient au scénario
          if (
            liaison.unScenario === idScenario &&
            composant.idComposant === liaison.unComposant
          ) {
            idRow++;
            appartient = true;
            composantsPacks.push({
              id: composant.idComposant,
              ref: composant.nomComposant,
              description: composant.nomComposant,
              quantite: parseFloat(liaison.quantite),
              unite: composant.uniteComposant,
              prix:
                parseFloat(composant.prixComposant) *
                parseFloat(liaison.quantite),
              prixUnitaire: composant.prixComposant,
              type: "composant",
              garde: true,
              idRow: idRow,
            });
          }
        });

        // Si le composant n'appartient pas au scénario
        if (!appartient) {
          composantsPacks.push({
            id: composant.idComposant,
            ref: composant.nomComposant,
            description: composant.nomComposant,
            quantite: 1,
            unite: composant.uniteComposant,
            prix: 0,
            prixUnitaire: composant.prixComposant,
            type: "composant",
            garde: false,
            idRow: 0,
          });
        }
      });

      // Insertion des lignes de packs suivant le scénario
      packs_liste.map((pack) => {
        var appartient = false;

        // Comptage du prix du pack
        var prixPack = 0;
        appartientPC_liste.map((liaison) => {
          if (liaison.unPack === pack.idPack) {
            composants_liste.map((composant) => {
              if (composant.idComposant == liaison.unComposant) {
                prixPack =
                  prixPack +
                  parseFloat(composant.prixComposant) * liaison.quantite;
              }
            });
          }
        });

        appartientSP_liste.map((liaison) => {
          // Si le pack appartient au scénario
          if (
            liaison.unScenario === idScenario &&
            pack.idPack === liaison.unPack
          ) {
            idRow++;
            appartient = true;
            composantsPacks.push({
              id: pack.idPack,
              ref: pack.nomPack,
              description: pack.nomPack,
              quantite: parseFloat(liaison.quantite),
              unite: pack.unitePack,
              prix: parseFloat(prixPack * parseFloat(liaison.quantite)),
              prixUnitaire: prixPack,
              type: "pack",
              garde: true,
              idRow: idRow,
            });
          }
        });

        // Si le pack n'appartient pas au scénario
        if (!appartient) {
          composantsPacks.push({
            id: pack.idPack,
            ref: pack.nomPack,
            description: pack.nomPack,
            quantite: 1,
            unite: pack.unitePack,
            prix: 0,
            prixUnitaire: prixPack,
            type: "composant",
            garde: false,
            idRow: 0,
          });
        }
      });
    }

    this.setState({ idRow: idRow });
    this.setState({ composantsPacks: composantsPacks });
    this.handlePrixTotal();
  }

  /**
   * Enregistre les modifications dans la base de données
   *
   * @param {*} e
   */
  async handleSubmit(e) {
    var regExp = /\(([^)]+)\)/;
    var matches = regExp.exec(this.state.inputs.leClient);
    var leClient =  matches[1];
    matches = regExp.exec(this.state.inputs.leScenario);
    var leScenario =  matches[1];

    e.preventDefault();
    let formData = new FormData();
    formData.append("nom", this.state.inputs.nomDevis);
    formData.append("description", this.state.inputs.descriptionDevis);
    formData.append("duree", this.state.inputs.dureeDevis);
    formData.append("dateEdition", this.state.inputs.dateEdition);
    formData.append("dateTravaux", this.state.inputs.dateDebutTravaux);
    formData.append("leClient", leClient);
    formData.append("leScenario", leScenario);
    await axios.post("http://api/devis/ajoutDevis", formData).then((res) => {
      console.log(res.data);
    });

    // window.location = "/devis";
  }

  /**
   * Ajoute une ligne vide au tableau
   */
  ajouterLigne() {
    var idRow = this.state.idRow;
    idRow++;

    var composantsPacks = this.state.composantsPacks;
    composantsPacks.push({
      id: "",
      ref: "",
      description: "",
      quantite: "",
      unite: "",
      prix: 0,
      prixUnitaire: "",
      type: "",
      garde: true,
      idRow: idRow,
    });

    this.setState({ idRow: idRow });
    this.setState({ composantsPacks: composantsPacks });
  }

  /**
   * Supprime la dernière ligne du tableau
   */
  supprimerLigne() {
    if (this.state.idRow > 0) {
      var idRow = this.state.idRow - 1;
      this.setState({ idRow: idRow });

      var composantsPacksGardes = [];
      this.state.composantsPacks.map((cp) => {
        if (cp.garde) {
          composantsPacksGardes.push({
            id: cp.id,
            ref: cp.ref,
            description: cp.description,
            quantite: cp.quantite,
            unite: cp.unite,
            prix: cp.prix,
            prixUnitaire: cp.prixUnitaire,
            type: cp.type,
            garde: cp.garde,
            idRow: cp.idRow,
          });
        }
      });
      var idRowLigneASupprimer =
        composantsPacksGardes[composantsPacksGardes.length - 1].idRow;

      var tousLesComposantsPacks = [];
      this.state.composantsPacks.map((cp) => {
        if (cp.idRow === idRowLigneASupprimer) {
          tousLesComposantsPacks.push({
            id: cp.id,
            ref: cp.ref,
            description: cp.description,
            quantite: cp.quantite,
            unite: cp.unite,
            prix: 0,
            prixUnitaire: cp.prixUnitaire,
            type: cp.type,
            garde: false,
            idRow: 0,
          });
        } else {
          tousLesComposantsPacks.push({
            id: cp.id,
            ref: cp.ref,
            description: cp.description,
            quantite: cp.quantite,
            unite: cp.unite,
            prix: 0,
            prixUnitaire: cp.prixUnitaire,
            type: cp.type,
            garde: cp.garde,
            idRow: cp.idRow,
          });
        }
      });

      this.setState({ composantsPacks: tousLesComposantsPacks });
    }
  }

  render() {
    return (
      <Container className="nouveau_devis_form">
        <Row>
          <Col>
            <br></br>
          </Col>
        </Row>

        <Row>
          <Col md="4">
            <img className="logo ml-4 mt-2" src="/img/logo.jpg" alt="" />
          </Col>
        </Row>

        <Row>
          <FormLabel> Allô Dépanne Service </FormLabel>
        </Row>

        <Row>
          <FormLabel> Thierry Hourdier </FormLabel>
        </Row>

        <Row>
          <Col>
            <br></br>
          </Col>
        </Row>

        <Form.Group>
          <FormLabel>Nom du devis : </FormLabel>
          <FormControl
            onChange={(e) => this.handleNom(e)}
            placeholder="exemple : Réparation radiateur"
          />
        </Form.Group>

        <Row>
          <Col md="2">
            <FormLabel>Edité le </FormLabel>
            <FormControl
              onChange={(e) => this.handleDateEdition(e)}
              placeholder="31/01/2021"
            />
          </Col>

          <Col md="6"></Col>

          <Col md="4">
            <select onChange={(e) => this.handleClient(e)}>
              <option>Sélectionner un client </option>
              {this.state.clients.map((client) => (
                <option key={client.idClient}>
                  {client.nomClient} ({client.idClient})
                </option>
              ))}
            </select>
          </Col>
        </Row>

        <Row>
          <Col md="2">
            <FormLabel>Début des travaux le </FormLabel>
            <FormControl
              onChange={(e) => this.handleDebutTravaux(e)}
              placeholder="25/02/2021"
            />
          </Col>

          <Col md="6"></Col>

          <Col md="4">
            <select onChange={(e) => this.handleScenario(e)}>
              <option>Sélectionner un scénario</option>
              {this.state.scenarios.map((scenario) => (
                <option key={scenario.idScenario}>
                  {scenario.nomScenario} ({scenario.idScenario})
                </option>
              ))}
            </select>
          </Col>

          <Col md="4">
            <FormLabel>Durée estimée des travaux </FormLabel>
            <FormControl
              onChange={(e) => this.handleDuree(e)}
              placeholder="2 semaines"
            />
          </Col>
        </Row>

        <Row>
          <Col>
            <br></br>
          </Col>
        </Row>

        <FormLabel>Description : </FormLabel>
        <FormControl
          onChange={(e) => this.handleDescription(e)}
          placeholder="Entrer la description"
        />

        <Row>
          <Col>
            <br></br>
          </Col>
        </Row>

        <div className="alert alert-secondary" role="alert">
          DETAIL DES PIECES
        </div>

        <Table>
          <thead>
            <tr>
              <th> Référence </th>
              <th> Description </th>
              <th> Quantité </th>
              <th> Unité </th>
              <th> Prix (€) </th>
            </tr>
          </thead>
        </Table>

        {this.state.composantsPacks.map((row) => {
          if (row.garde) {
            return (
              <Fragment>
                <Row>
                  <Col md="3">
                    <FormControl
                      as="select"
                      onClick={(e) => this.handleClick(e, row.idRow)}
                      onChange={(e) => this.handleSelect(e, row.idRow)}
                    >
                      <option>Composant/Pack</option>
                      {this.state.composantsPacks.map((composantPack) => {
                        if (composantPack.ref !== "" && !composantPack.garde) {
                          return (
                            <option>
                              {composantPack.id} - {composantPack.ref}
                            </option>
                          );
                        }
                      })}
                    </FormControl>
                  </Col>
                  <Col md="3">
                    <FormControl readOnly value={row.ref} />
                  </Col>
                  <Col md="2">
                    <FormControl
                      onChange={(e) => this.handleQuantite(row.id, row.type, e)}
                      type="number"
                      defaultValue={row.quantite}
                    />
                  </Col>
                  <Col md="2">
                    <FormControl
                      readOnly
                      // onChange={(e) => this.handleUnite(row.id, e)}
                      value={row.unite}
                    />
                  </Col>
                  <Col md="2">
                    <FormControl readOnly value={row.prix} />
                  </Col>
                </Row>
                <br></br>
              </Fragment>
            );
          }
        })}

        <Row>
          <Col>
            <br></br>
          </Col>
        </Row>

        <div className="alert alert-secondary" role="alert">
          TOTAL
        </div>

        <Row></Row>
        <Col md="4">
          <FormLabel> Prix Hors Taxe </FormLabel>
          <FormControl readOnly value={this.state.prixTotal} />
        </Col>
        <Col md="4">
          <FormLabel> TVA (%) </FormLabel>
          <FormControl onChange={this.handleTVA} defaultValue={20} />
        </Col>
        <Col md="4">
          <FormLabel> Prix </FormLabel>
          <FormControl
            readOnly
            value={
              Math.round(
                parseFloat(this.state.prixTotal) *
                  (1 + this.state.tva / 100) *
                  100
              ) / 100
            }
          />
        </Col>

        <Row>
          <Col>
            <br></br>
          </Col>
        </Row>

        <Row>
          <Col md="3">
            <Button variant="ligth" type="submit" onClick={this.handleSubmit}>
              Enregistrer
            </Button>
          </Col>
          <Col md="3">
            <Button variant="ligth" type="submit">
              Générer le devis en PDF
            </Button>
          </Col>
          <Col md="3">
            <Button variant="ligth" type="submit" onClick={this.ajouterLigne}>
              Ajouter une ligne
            </Button>
          </Col>
          <Col md="3">
            <Button
              variant="danger"
              type="submit"
              onClick={this.supprimerLigne}
            >
              Supprimer une ligne
            </Button>
          </Col>
        </Row>
      </Container>
    );
  }
}
