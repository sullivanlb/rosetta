import React, { Component, Fragment } from "react";
import { ListGroup, ListGroupItem } from "react-bootstrap";

/**
 * @description Ce composant représente la section de la page ComposantsPacks qui énumère les composants.
 *
 * @author Alice GONTARD, Christophe GARCIA
 */
export default class ListeComposants extends Component {

  render() {
    return (
      <Fragment>
        <ListGroup>
          {this.props.state.state1.composants.map((composant) => (
            <ListGroupItem
              key={composant.idComposant}
              href={composant.idComposant}
              onClick={() => this.props.action(composant.type, composant.idComposant)}
              value={composant.idComposant}
            >
              {composant.nomComposant}
            </ListGroupItem>
          ))}
        </ListGroup>
      </Fragment>
    );
  }
}
