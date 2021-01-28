import React from 'react';
import { Col, Button, Form, Container, Row, FormLabel, FormControl, FormGroup } from 'react-bootstrap';
import FormCheckInput from 'react-bootstrap/esm/FormCheckInput';
import FormFileInput from 'react-bootstrap/esm/FormFileInput';



const NouveauDevis = () => {
    return(
        <Container className="nouveau_devis_form">
            <Row>
                <Col><br></br></Col>
            </Row>
            
            <Form.Group >
                <FormLabel>Devis n° </FormLabel>
                <FormControl placeholder="Entrer le numéro du devis"/>
            </Form.Group>

            
            <Form.Group>
            <FormLabel>Edité le </FormLabel>
            <FormControl placeholder="Entrer la date de la réalisation du devis"/>
            </Form.Group>
         
            <Form.Group>
            <FormLabel>Client  </FormLabel>
            <select>
                <option>Sélectionner un client </option>
                <option>Alice Gontard</option>
                <option>Christophe Garcia</option>
                <option>Sullivan LeBoeuf</option>
            </select>
            </Form.Group>

            <Form.Group>
                <FormLabel>Début des travaux le </FormLabel>
                <FormControl placeholder="Entrer la date où les travaux vont commencé"/>
            </Form.Group>

            <Form.Group>
            <FormLabel>Scénario  </FormLabel>
            <select>
                <option>Sélectionner un scénario </option>
                <option>Installation d'un chauffe-eau électrique </option>/option>
                <option>Remplacement de la baignoire en douche</option>
            </select>
            </Form.Group>

            <Form.Group>
                <FormLabel>Durée estimée des travaux </FormLabel>
                <FormControl placeholder="Entrer la durée estimée"/>
            </Form.Group>

            <Form.Group>
                <FormLabel>Description : </FormLabel>
                <FormControl placeholder="Entrer la description"/>
            </Form.Group>

            <Button variant="ligth" type="submit">
                Ajouter une ligne 
            </Button>

            <Button variant="ligth" type="submit">
                Générer le devis en PDF
            </Button>
            
        </Container>
    )
}

export default NouveauDevis;
