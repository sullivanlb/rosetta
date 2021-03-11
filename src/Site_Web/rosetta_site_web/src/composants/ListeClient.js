import React, { Component, Fragment } from "react";
import { ListGroup, ListGroupItem } from "react-bootstrap";

/**
 * @description Ce composant représente la section de la page Client qui énumère les clients
 * 
 * @author Sullivan LEBOEUF & Christophe GARCIA
 */
export default class ListeClient extends Component {
  render() {
    return (
      <Fragment>
        <ListGroup>
          {this.props.state.client.map((client) => (
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
