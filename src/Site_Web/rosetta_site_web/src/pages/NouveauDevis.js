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
  state = {
    clients: [],
    rows: [],
    autoCompleteRows: [
      {
        id: "1",
        description: "",
        unite: "",
      },
      {
        id: "2",
        description: "",
        unite: "",
      },
    ],
    prixTotal: 0,
    tva: 20,
    inputs: {
      nomDevis :"",
      dateEdition: "",
      dateDebutTravaux: "",
      dureeDevis: "",
      descriptionDevis: "",
      leClient: "",
      leScenario: "",
    },
  };

  constructor(props) {
    super(props);

    this.ajouterLigne = this.ajouterLigne.bind(this);
    this.supprimerLigne = this.supprimerLigne.bind(this);
    this.handleTVA = this.handleTVA.bind(this);
    this.handlePrix = this.handlePrix.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  async componentDidMount() {
    await axios.get(`http://api/client/tousLesClients`).then((res) => {
      const clients = res.data;
      this.setState({ clients });
    });

    

  }

  handleQuantite(id, e) {
    var rows = this.state.rows;
    rows.filter((row) => row.id === id)[0].quantite = e.target.value;
    this.setState({ rows: rows });

    this.handlePrix(id);
  }

  handleUnite(id, e) {
    var rows = this.state.rows;
    rows.filter((row) => row.id === id)[0].unite = e.target.value;
    this.setState({ rows: rows });

    this.handlePrix(id);
  }

  handlePrix(id) {
    var row = this.state.rows.filter((row) => row.id === id)[0];
    var rows = this.state.rows;
    rows.filter((row) => row.id === id)[0].prix =
      Math.round(parseFloat(row.unite) * parseFloat(row.quantite) * 100) / 100;

    this.setState({ rows: rows });
    this.handlePrixTotal();
  }

  handlePrixTotal() {
    var total = 0;

    this.state.rows.map((row) => (total += row.prix));

    this.setState({ prixTotal: total });
  }

  handleTVA(e) {
    this.setState({ tva: e.target.value });
  }

  handleSelect(id, e) {
    var autoRows = this.state.autoCompleteRows;
    var rows = this.state.rows;

    autoRows.filter((row) => row.id === id)[0].description = rows.filter(
      (row) => row.ref === e.target.value
    )[0].description;
    autoRows.filter((row) => row.id === id)[0].unite = rows.filter(
      (row) => row.ref === e.target.value
    )[0].unite;
    this.setState({ autoCompleteRows: autoRows });
    console.log(this.state);
  }

  handleNom = (e) => {
    this.setState((prevState) => ({
      inputs: { ...prevState.inputs, nomDevis: e.target.value },
    }));
  };

  handleDateEdition = (e) => {
    this.setState((prevState) => ({
      inputs: { ...prevState.inputs, dateEdition: e.target.value },
    }));
  };

  handleDebutTravaux = (e) => {
    this.setState((prevState) => ({
      inputs: { ...prevState.inputs, dateDebutTravaux: e.target.value },
    }));
  };

  handleDebutTravaux = (e) => {
    this.setState((prevState) => ({
      inputs: { ...prevState.inputs, dateDebutTravaux: e.target.value },
    }));
  };

  handleDuree = (e) => {
    this.setState((prevState) => ({
      inputs: { ...prevState.inputs, dureeDevis: e.target.value },
    }));
  };

  handleDescription = (e) => {
    this.setState((prevState) => ({
      inputs: { ...prevState.inputs, descriptionDevis: e.target.value },
    }));
  };

  handleClient = (e) => {
    this.setState((prevState) => ({
      inputs: { ...prevState.inputs, leClient: e.target.value },
    }));
  };

  handleScenario = (e) => {
    this.setState((prevState) => ({
      inputs: { ...prevState.inputs, leScenario: e.target.value },
    }));

    var rows = [];
    await axios.get(`http://api/composant/tousLesComposants`).then((res) => {
      rows = res.data;
    });

    var rowsArr = [];
    rows.map((row) => (
        rowsArr.push({
            id: row.idComposant,
            ref: row.nomComposant,
            quantite: "95",
            unite: row.uniteComposant,
            prix: row.prixComposant,
        })
    ));

    rows = [];
    await axios.get(`http://api/pack/tousLesPacks`).then((res) => {
      rows = res.data;
    });

    var rowsArr = [];
    rows.map((row) => (
        rowsArr.push({
            id: row.idPack,
            ref: row.nomPack,
            quantite: "95",
            prix: "0",
        })
    ));

    this.setState({ rows });
  };

  async handleSubmit(e) {
    var regExp = /\(([^)]+)\)/;
    var matches = regExp.exec(this.state.inputs.leClient);

    e.preventDefault();
    let formData = new FormData();
    formData.append("nom", this.state.inputs.nomDevis);
    formData.append("description", this.state.inputs.descriptionDevis);
    formData.append("duree", this.state.inputs.dureeDevis);
    formData.append("dateEdition", this.state.inputs.dateEdition);
    formData.append("dateTravaux", this.state.inputs.dateDebutTravaux);
    formData.append("leClient", matches[1]);
    await axios
    .post("http://api/devis/ajoutDevis", formData)
      .then(res => {
        console.log(res.data);
      })
    
    window.location = "/devis";
  }

  ajouterLigne() {
    var rows = this.state.rows;
    rows.push({
      id: "",
      ref: "",
      description: "",
      quantite: "",
      unite: "",
      prix: "",
    });
    this.setState({ rows: rows });
  }

  supprimerLigne() {
    var rows = this.state.rows;
    rows.pop();
    this.setState({ rows: rows });
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
          <FormLabel>Nom du devis :  </FormLabel>
          <FormControl 
              onChange={(e) => this.handleNom(e)} 
              placeholder="exemple : Réparation radiateur" />
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
                <option key={client.idClient}>{client.nomClient} ({client.idClient})</option>
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
              <option>Sélectionner un scénario </option>
              <option>Installation d'un chauffe-eau électrique </option>
              <option>Remplacement de la baignoire en douche</option>
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
              <th> Prix </th>
            </tr>
          </thead>
        </Table>

        {this.state.rows.map((row) => (
          <Fragment>
            <Row>
              <Col md="3">
                <FormControl
                  as="select"
                  onChange={(e) => this.handleSelect(row.id, e)}
                  defaultValue={row.ref}
                >
                  <option>Choose...</option>
                  {this.state.rows.map((row) => (
                    <option>{row.ref}</option>
                  ))}
                </FormControl>
              </Col>
              <Col md="3">
                <FormControl
                  readOnly
                  defaultValue={
                    this.state.autoCompleteRows.filter(
                      (rowFilter) => row.id === rowFilter.id
                    ).description
                  }
                />
              </Col>
              <Col md="2">
                <FormControl
                  onChange={(e) => this.handleQuantite(row.id, e)}
                  type="number"
                  defaultValue={row.quantite}
                />
              </Col>
              <Col md="2">
                <FormControl
                  readOnly
                  onChange={(e) => this.handleUnite(row.id, e)}
                  defaultValue={
                    this.state.autoCompleteRows.filter(
                      (rowFilter) => row.id === rowFilter.id
                    ).unite
                  }
                />
              </Col>
              <Col md="2">
                <FormControl readOnly value={row.prix} />
              </Col>
            </Row>
            <br></br>
          </Fragment>
        ))}

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
