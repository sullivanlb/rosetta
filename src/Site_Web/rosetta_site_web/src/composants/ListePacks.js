import React, { Component, Fragment } from "react";
import { ListGroup, ListGroupItem } from "react-bootstrap";

/**
 * @description Ce composant représente la section de la page ComposantsPacks qui énumère les packs.
 *
 * @author Alice GONTARD
 */
export default class ListePacks extends Component {

  render() {
    return (
      <Fragment>
        <ListGroup>
          {this.props.state.state2.packs.map((packs) => (
            <ListGroupItem
              href={packs.id}
              onClick={() => this.props.action(packs.type, packs.id)}
              value={packs.id}
            >
              {packs.nom}
            </ListGroupItem>
          ))}
        </ListGroup>
      </Fragment>
    );
  }
}
