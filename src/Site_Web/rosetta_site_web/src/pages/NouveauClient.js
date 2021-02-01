import React, { Component } from "react";
import { Col, Button, Form, Container, Row } from 'react-bootstrap';
import "../style/NouveauClient.css"
import axios from 'axios';

export default class NouveauClient extends Component {
    state = {
        nom: "",
        prenom: "",
        email: "",
        adresse: "",
        tel: "",
        sexe: ""
    };

    handleAddNom = async e => {
        await this.setState({
            nom: e.target.value
        })
    }

    handleAddPrenom = async e => {
        await this.setState({
            prenom: e.target.value
        })
    }

    handleAddEmail = async e => {
        await this.setState({
            email: e.target.value
        })
    }

    handleAddAdresse = async e => {
        await this.setState({
            adresse: e.target.value
        })
    }

    handleAddTel = async e => {
        await this.setState({
            tel: e.target.value
        })
    }

    handleAddSexeHomme = async e => {
        await this.setState({
            sexe: "homme"
        })
    }

    handleAddSexeFemme = async e => {
        await this.setState({
            sexe: "femme"
        })
    }

    handleAddSexeAutre = async e => {
        await this.setState({
            sexe: "autre"
        })
    }

    handleSubmit = e => {
        e.preventDefault();
        console.log(this.state.nom + "\n" + this.state.prenom + "\n" + this.state.email + "\n" + this.state.adresse + "\n" + this.state.tel + "\n" + this.state.sexe);
        let formData = new FormData();
        formData.append("nom", this.state.nom);
        const url = "http://localhost:3000/api";
        axios.post(url, formData)
        .then(res => console.log(res.data))
        .catch(err => console.log(err));
    }

    render() {
        return (
            <Container className = "create_client_form">
                <Row>
                    <Col><br></br></Col>
                </Row>
                <Form>
                    <Form.Row>
                        <Form.Group as={Col}>
                        <Form.Label>Nom</Form.Label>
                        <Form.Control type="text" placeholder="Entrez le nom" onChange={this.handleAddNom} />
                        </Form.Group>

                        <Form.Group as={Col}>
                        <Form.Label>Prénom</Form.Label>
                        <Form.Control type="text" placeholder="Entrez le prénom" onChange={this.handleAddPrenom} />
                        </Form.Group>
                    </Form.Row>

                    <Form.Group controlId="formGridEmail">
                        <Form.Label>Email</Form.Label>
                        <Form.Control type="email" placeholder="Entrez l'adresse email" onChange={this.handleAddEmail} />
                    </Form.Group>

          <Form.Group controlId="formGridAddress2">
            <Form.Label>Adresse</Form.Label>
            <Form.Control placeholder="Ex : Numéro et libellé de la voie, Lieu dit, Code postal, Pays" />
          </Form.Group>

                    <Form.Group controlId="formGridAddress2">
                        <Form.Label>Adresse</Form.Label>
                        <Form.Control placeholder="Numéro et libellé de la voie, Lieu dit, Code postal, Pays" onChange={this.handleAddAdresse} />
                    </Form.Group>

                    <Form.Row>
                        <Form.Group as={Col} controlId="number">
                        <Form.Label>Numéro de téléphone</Form.Label>
                        <Form.Control placeholder="Entrez le numéro de téléphone" onChange={this.handleAddTel} />
                        </Form.Group>
                    </Form.Row>

                    <Form.Group>
                        <Form.Check
                            type="radio"
                            label="Femme"
                            name="formHorizontalRadios"
                            id="formHorizontalRadios2"
                            onChange={this.handleAddSexeFemme} 
                        />
                        <Form.Check
                            type="radio"
                            label="Homme"
                            name="formHorizontalRadios"
                            id="formHorizontalRadios1"
                            onChange={this.handleAddSexeHomme} 
                        />
                        <Form.Check
                            type="radio"
                            label="Autre"
                            name="formHorizontalRadios"
                            id="formHorizontalRadios3"
                            onChange={this.handleAddSexeAutre} 
                        />
                        </Form.Group>
                    <button type="button" class="btn btn-light" onClick={this.handleSubmit.bind(this)} >
                        Valider
                    </button>
                </Form>
            </Container>
        );
    }
}
