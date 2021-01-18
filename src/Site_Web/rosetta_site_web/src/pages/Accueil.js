import React from 'react';
import { Row, Col, Container, Form, Button, Alert } from 'react-bootstrap';
import "../style/Accueil.css";

const Accueil = () => {
    return (
        <div>
            <Container className="cont" fluid>
                <Row>
                    <Col className="col1" md={10}>
                        
                    </Col>
                    <Col className="col2" md={2}>
                        <Form>
                        <Alert variant="secondary">
                        Connexion :
                        </Alert>
                            <Form.Group controlId="formGroupEmail">
                                <Form.Label>Login</Form.Label>
                                <Form.Control type="email" placeholder="Entrez votre login" />
                            </Form.Group>
                            <Form.Group controlId="formGroupPassword">
                                <Form.Label>Mot de passe</Form.Label>
                                <Form.Control type="password" placeholder="Mot de passe" />
                            </Form.Group>
                            <Button variant="Primary" type="submit" size="sm">
                                Connexion
                            </Button>
                        </Form>
                    </Col>
                </Row>
            </Container>
        </div>
    );
};

export default Accueil;