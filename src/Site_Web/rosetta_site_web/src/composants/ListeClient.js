import React, { Component, Fragment } from "react";
import { ListGroup, ListGroupItem } from "react-bootstrap";

/**
 * @description Ce composant représente la section de la page Client qui énumère les clients
 * 
 * @author Sullivan LEBOEUF & Christophe GARCIA
 */
export default class ListeClient extends Component {
<<<<<<< HEAD
=======
  state = this.props.state;

  handleSubmit = e => {
    e.preventDefault();

    let formData = new FormData();
    formData.append("nom", this.state.client.nomClient);
    formData.append("prenom", this.state.client.prenomClient);
    formData.append("adresse", this.state.client.adresseClient);
    formData.append("email", this.state.client.emailClient);
    formData.append("tel", this.state.client.telClient);
    formData.append("sexe", this.state.client.sexeClient);
  }

>>>>>>> Site_Web_Scenario
  render() {
    return (
      <Fragment>
        <ListGroup>
          {this.props.state.client.map((client) => (
            <ListGroupItem
<<<<<<< HEAD
              key={client.idClient}
=======
>>>>>>> Site_Web_Scenario
              href={client.idClient}
              onClick={() => this.props.action(client.idClient)}
              value={client.idClient}
            >
              {client.nomClient} {client.prenomClient}
            </ListGroupItem>
          ))}
        </ListGroup>
      </Fragment>
    );
  }
}
