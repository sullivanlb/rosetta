import React, { Component, Fragment } from "react";
import { ListGroup, ListGroupItem } from "react-bootstrap";

/**
 * @description Ce composant représente la section de la page Client qui énumère les clients
 * 
 * @author Sullivan LEBOEUF & Christophe GARCIA
 */
export default class ListeClient extends Component {
  state = this.props.state;

  handleSubmit = e => {
    e.preventDefault();

    console.log(this.state.client.nomClient + "\n" + this.state.client.prenomClient + "\n" + this.state.client.emailClient + 
    "\n" + this.client.state.adresseClient + "\n" + this.client.state.telClient + "\n" + this.client.state.sexeClient);

    let formData = new FormData();
    formData.append("nom", this.state.client.nomClient);
    formData.append("prenom", this.state.client.prenomClient);
    formData.append("adresse", this.state.client.adresseClient);
    formData.append("email", this.state.client.emailClient);
    formData.append("tel", this.state.client.telClient);
    formData.append("sexe", this.state.client.sexeClient);
  }

  render() {
    return (
      <Fragment>
        <ListGroup>
          {this.state.client.map((client) => (
            <ListGroupItem
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
