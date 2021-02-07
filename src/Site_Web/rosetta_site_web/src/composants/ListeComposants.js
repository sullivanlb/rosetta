import React, { Component, Fragment } from "react";
import { ListGroup, ListGroupItem } from "react-bootstrap";

/**
 * @description This component represent the section in page ComposantsPacks which list the component.
 *
 * @author Alice GONTARD
 */

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
            </ListGroupItem>
          ))}
        </ListGroup>
      </Fragment>
    );
  }
}
