import React from 'react';
import { Col, Button, Form, Container } from 'react-bootstrap';


const NouveauClient = () => {
    return(
        <Container>
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

                    <Form.Group as={Col} controlId="formGridState">
                    <Form.Label>State</Form.Label>
                    <Form.Control as="select" defaultValue="Choose...">
                        <option>Choose...</option>
                        <option>...</option>
                    </Form.Control>
                    </Form.Group>
                </Form.Row>

                <Form.Group id="formGridCheckbox">
                    <Form.Check type="checkbox" label="Check me out" />
                </Form.Group>

                <Button variant="primary" type="submit">
                    Submit
                </Button>
            </Form>
        </Container>

    )
}
export default NouveauClient;