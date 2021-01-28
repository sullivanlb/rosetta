import React, { Component } from "react";
import { ListGroup, ListGroupItem } from "react-bootstrap";

export default class ListeClient extends Component {
  state = this.props.state;

  montrerClient = () => {
    console.log(this.state.client.length);
  };

  render() {
    return (
      <div>
        <ListGroup>
          {this.state.client.map((client) => (
            <ListGroupItem href={client.id} onClick={this.montrerClient}>
              {client.nom} {client.prenom}
            </ListGroupItem>
          ))}
        </ListGroup>
      </div>
    );
  }
}
