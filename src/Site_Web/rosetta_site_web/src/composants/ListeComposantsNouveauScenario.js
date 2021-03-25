import React, { Component, Fragment } from "react";
import { ListGroup, ListGroupItem } from "react-bootstrap";

/**
 * @description Ce composant représente la section de la page NouveauScenario qui énumère les composants.
 *
 * @author Christophe GARCIA
 */
export default class ListeComposantsNouveauScenario extends Component {

  render() {
    return (
      <Fragment>
        <ListGroup>
          {this.props.state.composants.map((composant) => (
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
