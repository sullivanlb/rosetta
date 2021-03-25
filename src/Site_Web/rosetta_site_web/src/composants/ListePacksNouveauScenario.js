import React, { Component, Fragment } from "react";
import { ListGroup, ListGroupItem } from "react-bootstrap";

/**
 * @description Ce composant représente la section de la page NouveauScenario qui énumère les packs.
 *
 * @author Christophe GARCIA
 */
export default class ListePacksNouveauScenario extends Component {

  render() {
    return (
      <Fragment>
        <ListGroup>
          {this.props.state.packs.map((pack) => (
            <ListGroupItem
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
