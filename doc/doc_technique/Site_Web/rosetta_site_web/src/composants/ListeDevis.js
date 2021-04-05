import React, { Component, Fragment } from "react";
import { ListGroup, ListGroupItem } from "react-bootstrap";

/**
 * Ce composant représente la section de la page Devis qui énumère les devis
 * 
 * @author Lucy Gastebois, Christophe GARCIA
 */
export default class ListeDevis extends Component {
    render() {
      return (
        <Fragment>
          <ListGroup >
            {this.props.state.devis.map((devis) => (
              <ListGroupItem
                href={devis.idDevis}
                onClick={() => this.props.action(devis.idDevis)}
                value={devis.idDevis}
              >
                {devis.nomDevis} - {devis.prenomClient} {devis.nomClient} - {devis.dateEditionDevis} 
              </ListGroupItem>
            ))}
          </ListGroup>
        </Fragment>
      );
    }
  }

 