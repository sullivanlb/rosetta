import React, { Component, Fragment } from "react";
import { ListGroup, ListGroupItem } from "react-bootstrap";

/**
 * @description This component represent the section in page ComposantsPacks which list the pack.
 *
 * @author Alice GONTARD
 */

export default class ListePacks extends Component {
  state2 = this.props.state;

  render() {
    return (
      <Fragment>
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
      </Fragment>
    );
  }
}
