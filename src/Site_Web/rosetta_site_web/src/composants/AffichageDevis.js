import React, { Component, Fragment } from "react";

/**
 * Ce composant représente la section de la page Devis qui affiche ses informations
 * 
 * @author Lucy Gastebois
 */
export default class AffichageDevis extends Component {
  render() {
    return (
      <Fragment>
        {this.props.state.devis.map((devis) => {
          if (this.props.state.idToDisplay === devis.id) {
            return <div className="card card-body mb-3" key={this.props.state.devis.id}>
              <h4>Devis n°{devis.id}</h4>
              <ul className="list-group">
                <li className="list-group-item">Edité le {devis.date}</li>
                <li className="list-group-item">Client : {devis.client}</li>
                <li className="list-group-item">{devis.scenario}</li>
                <li className="list-group-item">Prix : {devis.prix}</li>
              </ul>
            </div>
          }
        })}
      </Fragment>
    );
  }
}