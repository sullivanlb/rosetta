import React, { Component, Fragment } from "react";
import { ListGroup, ListGroupItem } from "react-bootstrap";

/**
 * @description Ce composant représente la section de la page ComposantsPacks qui énumère les packs.
 *
 * @author Alice GONTARD, Christophe GARCIA
 */
export default class ListePacks extends Component {

  render() {
    return (
      <Fragment>
        <ListGroup>
          {this.props.state.state2.packs.map((pack) => (
            <ListGroupItem
              key={pack.idPack}
              href={pack.idPack}
              onClick={() => this.props.action(pack.type, pack.idPack)}
              value={pack.idPack}
            >
              {pack.nomPack}
            </ListGroupItem>
          ))}
        </ListGroup>
      </Fragment>
    );
  }
}
