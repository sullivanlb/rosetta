import React from 'react';
import { Row, Col, Container, Form, Button, Alert } from 'react-bootstrap';
import "../style/Accueil.css";

const Accueil = () => {
    return (
        <div>
            <Container className="cont" fluid>
                <Row>
                    <Col className="col1" md={10}>
                        
                        <h2>Bienvenue sur Allô Dépanne Service</h2>
                        <img class="ImagelogoPlombier" src="/img/logo-plombiers.png" alt="" />

                        <p> txtxttxt</p>
                        <nobr>
                            <img class="Imageoutil" src="/img/outil.jpg" alt="" />
                            <img class="Imagetableauelectrique" src="/img/tableau-electrique.jpg" alt="" />
                            <img class="Iamgechuaffage" src="/img/chauffage.jpg" alt="" />
                        </nobr>
                        
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