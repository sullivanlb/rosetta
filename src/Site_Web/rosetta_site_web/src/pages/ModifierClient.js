import React from 'react';
import { Col, Button, Form, Container, Row } from 'react-bootstrap';
import "../style/NouveauClient.css"


const NouveauClient = () => {
    return(
            <Container className = "create_client_form">
                <Row>
                    <Col><br></br></Col>
                </Row>
                <Form>
                    <Form.Row>
                        <Form.Group as={Col}>
                        <Form.Label>Nom</Form.Label>
                        <Form.Control type="text" placeholder="Entrez le nom" />
                        </Form.Group>

                        <Form.Group as={Col}>
                        <Form.Label>Prénom</Form.Label>
                        <Form.Control type="text" placeholder="Entrez le prénom" />
                        </Form.Group>
                    </Form.Row>

                    <Form.Group controlId="formGridEmail">
                        <Form.Label>Email</Form.Label>
                        <Form.Control type="email" placeholder="Enter email" />
                    </Form.Group>


                    <Form.Group controlId="formGridAddress2">
                        <Form.Label>Adresse</Form.Label>
                        <Form.Control placeholder="Numéro et libellé de la voie, Lieu dit, Code postal, Pays" />
                    </Form.Group>

                    <Form.Row>
                        <Form.Group as={Col} controlId="number">
                        <Form.Label>Numéro de téléphone</Form.Label>
                        <Form.Control />
                        </Form.Group>
                    </Form.Row>

                    <Form.Group>
                        <Form.Check
                            type="radio"
                            label="Homme"
                            name="formHorizontalRadios"
                            id="formHorizontalRadios1"
                        />
                        <Form.Check
                            type="radio"
                            label="Femme"
                            name="formHorizontalRadios"
                            id="formHorizontalRadios2"
                        />
                        <Form.Check
                            type="radio"
                            label="Autre"
                            name="formHorizontalRadios"
                            id="formHorizontalRadios3"
                        />
                        </Form.Group>

                    <Button variant="primary" type="submit">
                        Valider
                    </Button>
                </Form>
            </Container>
    )
}

export default NouveauClient;