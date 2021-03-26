import React, { Component, Fragment } from "react";

/**
 * Ce composant représente la section de la page Devis qui affiche ses informations
 * 
 * @author Lucy Gastebois, Christophe GARCIA
 */
export default class AffichageDevis extends Component {
  render() {
    return (
      <Fragment>
        {this.props.state.devis.map((devis) => {
          if (this.props.state.idToDisplay === devis.idDevis) {
            return <div className="card card-body mb-3" key={this.props.state.devis.id}>
              <h4>{devis.nomDevis}</h4>
              <ul className="list-group">
                <li className="list-group-item"><strong>Client : </strong>{devis.nomClient.toUpperCase()} {devis.prenomClient}</li>
                <li className="list-group-item"><strong>Description : </strong>{devis.descriptionDevis}</li>
                <li className="list-group-item"><strong>Durée : </strong>{devis.dureeDevis}</li>
                <li className="list-group-item"><strong>Date d'édition : </strong>{devis.dateEditionDevis}</li>
                <li className="list-group-item"><strong>Date des travaux : </strong>{devis.dateTravauxDevis}</li>
                <li className="list-group-item"><strong>Prix : </strong>{devis.prixDevis} €</li>
              </ul>
            </div>
          }
        })}
      </Fragment>
    );
  }
}