import React, { Component, Fragment } from "react";
import { ListGroup, ListGroupItem } from "react-bootstrap";

/**
 * @description Ce composant représente la section de la page ComposantsPacks qui énumère les composants.
 *
 * @author Alice GONTARD
 */
export default class ListeComposants extends Component {

  render() {
    return (
      <Fragment>
        <ListGroup>
          {this.props.state.state1.composants.map((composant) => (
            <ListGroupItem
              key={composant.id}
              href={composant.id}
              onClick={() => this.props.action(composant.type, composant.id)}
              value={composant.id}
            >
              {composant.nom}
            </ListGroupItem>
          ))}
        </ListGroup>
      </Fragment>
    );
  }
}
