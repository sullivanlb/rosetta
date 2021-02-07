import React, { Component, Fragment } from "react";
import { ListGroup, ListGroupItem } from "react-bootstrap";

/**
 * Ce composant représente la section de la page Devis qui énumère les devis
 * 
 * @author Lucy Gastebois
 */

export default class ListeDevis extends Component {
    state = this.props.state;
  
    render() {
      return (
        <Fragment>
          <ListGroup >
            {this.state.devis.map((devis) => (
              <ListGroupItem
                href={devis.id}
                onClick={() => this.props.action(devis.id)}
                value={devis.id}
              >
                Devis n°{devis.id} - {devis.client} - {devis.scenario} 
              </ListGroupItem>
            ))}
          </ListGroup>
        </Fragment>
      );
    }
  }

 