import React, { Component, Fragment } from "react";
import { ListGroup, ListGroupItem } from "react-bootstrap";

export default class ListeComposants extends Component {
  state1 = this.props.state;

  render() {
    return (
      <Fragment>
        <ListGroup>
          {this.state1.composants.map((composants) => (
            <ListGroupItem
              href={composants.id}
              onClick={() => this.props.action(composants.id)}
              value={composants.id}
            >
              {composants.nom}
              {composants.unite}
              {composants.prix}
            </ListGroupItem>
          ))}
        </ListGroup>
      </Fragment>
    );
  }
}
