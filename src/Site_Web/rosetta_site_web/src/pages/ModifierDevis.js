import React, { Component, Fragment } from "react";
import { Col, Form, Container, Row, Button, Table } from "react-bootstrap";
import "../style/NouveauClient.css";
import axios from "axios";

/**
 * @description Ce composant représente la page pour créer un client
 *
 * @author Lucy Gastebois
 */
export default class ModifierDevis extends Component {
  constructor(props) {
    super(props);

    this.state = {
      // id: this.props.location.params.devis[0].idDevis,
      clients: [],
      rows: [
        {
          id: "1",
          ref: "85236",
          description: "description",
          quantite: "10",
          unite: "2,20",
          prix: "0",
        },
        {
          id: "2",
          ref: "44556",
          description: "description n°2",
          quantite: "95",
          unite: "0.85",
          prix: "0",
        },
      ],
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
        nomDevis: this.props.location.params.devis[0].nomDevis,
        dateEdition: this.props.location.params.devis[0].dateEditionDevis,
        dateDebutTravaux: this.props.location.params.devis[0].dateTravauxDevis,
        dureeDevis: this.props.location.params.devis[0].dureeDevis,
        descriptionDevis: this.props.location.params.devis[0].descriptionDevis,
        leClient: this.props.location.params.devis[0].leClient,
      },
    };
  }

  componentDidMount() {
    axios.get(`http://api/client/tousLesClients`).then((res) => {
      const clients = res.data;
      this.setState({ clients });
    });
  }

  handleAddID = async (e) => {
    await this.setState({
      id: e.target.value,
    });
  };

  handleAddNom = async (e) => {
    await this.setState({
      nom: e.target.value,
    });
  };

  handleAddDescription = async (e) => {
    await this.setState({
      description: e.target.value,
    });
  };

  handleAddDuree = async (e) => {
    await this.setState({
      duree: e.target.value,
    });
  };

  handleAddDateEdition = async (e) => {
    await this.setState({
      dateEdition: e.target.value,
    });
  };

  handleAddDateTravaux = async (e) => {
    await this.setState({
      dateTravaux: e.target.value,
    });
  };

  handleAddClient = async (e) => {
    await this.setState({
      leClient: e.target.value,
    });
  };

  handleSubmit = async (e) => {
    console.log(this.state);

    // var regExp = /\(([^)]+)\)/;
    // var matches = regExp.exec(this.state.inputs.leClient);

    // e.preventDefault();
    // let formData = new FormData();
    // formData.append("nom", this.state.inputs.nomDevis);
    // formData.append("description", this.state.inputs.descriptionDevis);
    // formData.append("duree", this.state.inputs.dureeDevis);
    // formData.append("dateEdition", this.state.inputs.dateEdition);
    // formData.append("dateTravaux", this.state.inputs.dateDebutTravaux);
    // formData.append("leClient", matches[1]);

    // await axios.post("http://api/devis/modifierDevis", formData).then((res) => {
    //   console.log(res.data);
    // });

    // window.location = "/devis";
  };
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
          <Form.Label> Allô Dépanne Service </Form.Label>
        </Row>

        <Row>
          <Form.Label> Thierry Hourdier </Form.Label>
        </Row>

        <Row>
          <Col>
            <br></br>
          </Col>
        </Row>

        <Form.Group>
          <Form.Label>Nom du devis : </Form.Label>
          <Form.Control
            type="text"
            onChange={this.handleAddNom}
            placeholder={this.state.inputs.nomDevis}
          />
        </Form.Group>

        <Row>
          <Col md="2">
            <Form.Label>Edité le </Form.Label>
            <Form.Control
              type="text"
              onChange={this.handleAddDateEdition}
              placeholder={this.state.inputs.dateEdition}
            />
          </Col>

          <Col md="6"></Col>

          <Col md="4">
            <select onChange={this.handleAddClient}>
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
            <Form.Label>Début des travaux le </Form.Label>
            <Form.Control
              type="text"
              onChange={this.handleAddDateTravaux}
              placeholder={this.state.inputs.dateDebutTravaux}
            />
          </Col>

          <Col md="4">
            <Form.Label>Durée estimée des travaux </Form.Label>
            <Form.Control
              onChange={this.handleAddDateTravaux}
              placeholder={this.state.inputs.dureeDevis}
            />
          </Col>
        </Row>

        <Row>
          <Col>
            <br></br>
          </Col>
        </Row>

        <Form.Label>Description : </Form.Label>
        <Form.Control
          onChange={this.handleAddDescription}
          placeholder={this.state.inputs.descriptionDevis}
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
                <Form.Control
                  as="select"
                  onChange={(e) => this.handleSelect(row.id, e)}
                  defaultValue={row.ref}
                >
                  <option>Choose...</option>
                  {this.state.rows.map((row) => (
                    <option>{row.ref}</option>
                  ))}
                </Form.Control>
              </Col>
              <Col md="3">
                <Form.Control
                  readOnly
                  defaultValue={
                    this.state.autoCompleteRows.filter(
                      (rowFilter) => row.id === rowFilter.id
                    )[0].description
                  }
                />
              </Col>
              <Col md="2">
                <Form.Control
                  onChange={(e) => this.handleQuantite(row.id, e)}
                  type="number"
                  defaultValue={row.quantite}
                />
              </Col>
              <Col md="2">
                <Form.Control
                  readOnly
                  onChange={(e) => this.handleUnite(row.id, e)}
                  defaultValue={
                    this.state.autoCompleteRows.filter(
                      (rowFilter) => row.id === rowFilter.id
                    )[0].unite
                  }
                />
              </Col>
              <Col md="2">
                <Form.Control readOnly value={row.prix} />
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
          <Form.Label> Prix Hors Taxe </Form.Label>
          <Form.Control readOnly value={this.state.prixTotal} />
        </Col>
        <Col md="4">
          <Form.Label> TVA (%) </Form.Label>
          <Form.Control onChange={this.handleTVA} defaultValue={20} />
        </Col>
        <Col md="4">
          <Form.Label> Prix </Form.Label>
          <Form.Control
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
