import React, { Component } from "react";
import { Col, Form, Container, Row, Button } from "react-bootstrap";
import "../style/NouveauClient.css";
import axios from "axios";

/**
 * @description Ce composant représente la page pour créer un client
 *
 * @author Christophe GARCIA & Sullivan LEBOEUF
 */
export default class ModifierClient extends Component {
  state = {
    id: this.props.location.params.client[0].idClient,
    nom: this.props.location.params.client[0].nomClient,
    prenom: this.props.location.params.client[0].prenomClient,
    email: this.props.location.params.client[0].emailClient,
    adresse: this.props.location.params.client[0].adresseClient,
    tel: this.props.location.params.client[0].telClient,
    sexe: this.props.location.params.client[0].sexeClient,
  };

  handleAddNom = async (e) => {
    await this.setState({
      nom: e.target.value,
    });
  };

  handleAddPrenom = async (e) => {
    await this.setState({
      prenom: e.target.value,
    });
  };

  handleAddEmail = async (e) => {
    await this.setState({
      email: e.target.value,
    });
  };

  handleAddAdresse = async (e) => {
    await this.setState({
      adresse: e.target.value,
    });
  };

  handleAddTel = async (e) => {
    await this.setState({
      tel: e.target.value,
    });
  };

  handleAddSexeHomme = async (e) => {
    await this.setState({
      sexe: "homme",
    });
  };

  handleAddSexeFemme = async (e) => {
    await this.setState({
      sexe: "femme",
    });
  };

  handleAddSexeAutre = async (e) => {
    await this.setState({
      sexe: "autre",
    });
  };

  handleSubmit = async (e) => {
    e.preventDefault();
    let formData = new FormData();
    formData.append("id", this.state.id);
    formData.append("nom", this.state.nom);
    formData.append("prenom", this.state.prenom);
    formData.append("adresse", this.state.adresse);
    formData.append("email", this.state.email);
    formData.append("tel", this.state.tel);
    formData.append("sexe", this.state.sexe);

    await axios
      .post("http://api/client/modifierClient", formData)
      .then(res => {
        console.log(res.data);
      })
    
    window.location = "/client";
  };

  render() {
    return (
      <Container className="create_client_form">
        <Row>
          <Col>
            <br></br>
          </Col>
        </Row>
        <Form>
          <Form.Row>
            <Form.Group as={Col}>
              <Form.Label>Nom</Form.Label>
              <Form.Control
                type="text"
                placeholder={this.state.nom}
                onChange={this.handleAddNom}
              />
            </Form.Group>

            <Form.Group as={Col}>
              <Form.Label>Prénom</Form.Label>
              <Form.Control
                type="text"
                placeholder={this.state.prenom}
                onChange={this.handleAddPrenom}
              />
            </Form.Group>
          </Form.Row>

          <Form.Group controlId="formGridEmail">
            <Form.Label>Email</Form.Label>
            <Form.Control
              type="email"
              placeholder={this.state.email}
              onChange={this.handleAddEmail}
            />
          </Form.Group>

          <Form.Group controlId="formGridAddress2">
            <Form.Label>Adresse</Form.Label>
            <Form.Control
              placeholder={this.state.adresse}
              onChange={this.handleAddAdresse}
            />
          </Form.Group>

          <Form.Row>
            <Form.Group as={Col} controlId="number">
              <Form.Label>Numéro de téléphone</Form.Label>
              <Form.Control
                placeholder={this.state.tel}
                onChange={this.handleAddTel}
              />
            </Form.Group>
          </Form.Row>

          <Form.Group>
            <Form.Check
              type="radio"
              label="Femme"
              name="formHorizontalRadios"
              id="formHorizontalRadios2"
              checked={this.state.sexe === "femme" ? true : false}
              onChange={this.handleAddSexeFemme}
            />
            <Form.Check
              type="radio"
              label="Homme"
              name="formHorizontalRadios"
              id="formHorizontalRadios1"
              checked={this.state.sexe === "homme" ? true : false}
              onChange={this.handleAddSexeHomme}
            />
            <Form.Check
              type="radio"
              label="Autre"
              name="formHorizontalRadios"
              id="formHorizontalRadios3"
              checked={this.state.sexe === "autre" ? true : false}
              onChange={this.handleAddSexeAutre}
            />
          </Form.Group>
          <Button
            className="btn btn-light"
            onClick={this.handleSubmit.bind(this)}
          >
            Valider
          </Button>
        </Form>
      </Container>
    );
  }
}
