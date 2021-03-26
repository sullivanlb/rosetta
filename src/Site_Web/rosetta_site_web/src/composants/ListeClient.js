import React, { Component, Fragment } from "react";
import { ListGroup, ListGroupItem } from "react-bootstrap";

/**
 * @description Ce composant représente la section de la page Client qui énumère les clients
 * 
 * @author Sullivan LEBOEUF, Christophe GARCIA
 */
export default class ListeClient extends Component {
  state = this.props.state;

  handleSubmit = e => {
    e.preventDefault();

    let formData = new FormData();
    formData.append("nom", this.state.clients.nomClient);
    formData.append("prenom", this.state.clients.prenomClient);
    formData.append("adresse", this.state.clients.adresseClient);
    formData.append("email", this.state.clients.emailClient);
    formData.append("tel", this.state.clients.telClient);
    formData.append("sexe", this.state.clients.sexeClient);
  }

  render() {
    return (
      <Fragment>
        <ListGroup>
          {this.props.state.clients.map((client) => (
            <ListGroupItem
              key={client.idClient}
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
