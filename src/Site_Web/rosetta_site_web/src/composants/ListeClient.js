import React, { Component } from "react";
import { ListGroup, ListGroupItem } from "react-bootstrap";

export default class ListeClient extends Component {
  state = this.props.state;

  render() {
    return (
      <div>
        <ListGroup>
          {this.state.client.map((client) => (
            <ListGroupItem
              href={client.id}
              onClick={() => this.props.action(client.id)}
              value={client.id}
            >
              {client.nom} {client.prenom}
            </ListGroupItem>
          ))}
        </ListGroup>
      </div>
    );
  }
}
