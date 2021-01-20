import React, { Component } from 'react';
import { Row, Col, Container, Form, Button, Alert } from 'react-bootstrap';
import GoogleMapReact from 'google-map-react';
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
                        <h3>Contact</h3>
                        <img className="ImageTelephone" src="/img/call.png" alt="" />
                        <h6 className="TexteTelephone">06.60.35.75.45</h6>
                        <br></br>
                        <br></br>
                        <img className="pointeur" src="/img/location-pointer.png" alt="" />
                        <h6 className="TexteRue">90 avenue Marne, 56000 Vannes</h6>
                        <br></br>
                        <Form>
                        <Alert variant="secondary">
                        Connexion :
                        </Alert>
                            <Form.Group controlId="formGroupEmail">
                                <Form.Label>Login</Form.Label>
                                <Form.Control type="text" placeholder="Entrez votre login" />
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