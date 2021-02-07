import React from 'react';
import { Col, Button, Form, Container, Row, FormLabel, FormControl, FormGroup, Table } from 'react-bootstrap';
import '../style/NouveauDevis.css';
import FormCheckInput from 'react-bootstrap/esm/FormCheckInput';
import FormFileInput from 'react-bootstrap/esm/FormFileInput';



const NouveauDevis = () => {
    return(

        <Container className="nouveau_devis_form">
            <Row>
                <Col><br></br></Col>
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
                <Col><br></br></Col>
            </Row>

            <Form.Group >
                <FormLabel>Devis n° </FormLabel>
                <FormControl placeholder="exemple : 156"/>
            </Form.Group>

            <Row >
            <Col md="2">
                <FormLabel>Edité le </FormLabel>
                <FormControl placeholder="31/01/2021"/>
            </Col>

            <Col md="6"></Col>

            <Col md="4">
            <select>
                <option>Sélectionner un client </option>
                <option>Alice Gontard</option>
                <option>Christophe Garcia</option>
                <option>Sullivan LeBoeuf</option>
            </select>
            </Col>
            </Row>

            <Row>
            <Col md="2">
                <FormLabel>Début des travaux le </FormLabel>
                <FormControl placeholder="25/02/2021"/>
            </Col>

            <Col md="6"></Col>

            <Col md="4">
            <select>
                <option>Sélectionner un scénario </option>
                <option>Installation d'un chauffe-eau électrique </option>/option>
                <option>Remplacement de la baignoire en douche</option>
            </select>
            </Col>

            <Col md="4">
            <FormLabel>Durée estimée des travaux </FormLabel>
            <FormControl placeholder="2 semaines"/>
            </Col>
            </Row>

            <Row>
                <Col><br></br></Col>
            </Row>

            <FormLabel>Description : </FormLabel>
            <FormControl placeholder="Entrer la description"/>

            <Row>
                <Col><br></br></Col>
            </Row>

            <div class="alert alert-secondary" role="alert">
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

            <Row>
            <Col md="3">
                <FormControl placeholder="ref : 85236"/>
            </Col>
            <Col md="3">
                <FormControl placeholder="Tuyau"/>
            </Col>
            <Col md="2">
                <FormControl type="number" max="500" min="0" step="1" />
            </Col>
            <Col md="2">
                <FormControl placeholder="2,20"/>
            </Col>
            <Col md="2">
                <FormControl placeholder="2,20"/>
            </Col>
            </Row>

            <Row>
                <Col><br></br></Col>
            </Row>

            <div class="alert alert-secondary" role="alert">
                TOTAL
            </div>

            <Row>

            </Row>
            <Col md="4">
                <FormLabel> Prix Hors Taxe </FormLabel>
                <FormControl placeholder="100"/>
            </Col>
            <Col md="4">
                <FormLabel> TVA (%) </FormLabel>
                <FormControl placeholder="20"/>
            </Col>
            <Col md="4">
                <FormLabel> Prix  </FormLabel>
                <FormControl placeholder="120"/>
            </Col>

            <Row>
                <Col><br></br></Col>
            </Row>

            <Row>
            <Col md="3">
                <Button variant="ligth" type="submit"> Ajouter une ligne </Button>
            </Col>
            <Col md="3">
                <Button variant="ligth" type="submit">Enregistrer </Button>
            </Col>
            <Col md="3">
                <Button variant="ligth" type="submit">Générer le devis en PDF </Button>
            </Col>
            </Row>
        </Container>
    )
}

export default NouveauDevis;
