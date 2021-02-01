import React, { Component } from "react";
import { ListGroup, ListGroupItem } from "react-bootstrap";

export default class ListePacks extends Component {
  state2 = this.props.state;

  render() {
    return (
      <div>
        <ListGroup>
          {this.state2.packs.map((packs) => (
            <ListGroupItem
              href={packs.id}
              onClick={() => this.props.action(packs.id)}
              value={packs.id}
            >
              {packs.nom}
            </ListGroupItem>
          ))}
        </ListGroup>
      </div>
    );
  }
}
