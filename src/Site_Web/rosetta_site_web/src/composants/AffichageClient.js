import React, { Component, Fragment } from "react";

/**
 * @description Ce composant représente la section de la page Client qui affiche ses informations
 * 
 * @author Sullivan LEBOEUF
 */
export default class AffichageClient extends Component {

  render() {
    return (
      <Fragment>
        {this.props.state.clients.map((client) => {
          if (this.props.state.idToDisplay === client.idClient) {
            return <div className="card card-body mb-3" key={this.props.state.clients.idClient}>
              <h4>{client.nomClient}</h4>
              <ul className="list-group">
                <li className="list-group-item">Nom : {client.nomClient}</li>
                <li className="list-group-item">Prenom : {client.prenomClient}</li>
                <li className="list-group-item">Adresse : {client.adresseClient}</li>
                <li className="list-group-item">Email : {client.emailClient}</li>
                <li className="list-group-item">Téléphone : {client.telClient}</li>
              </ul>
            </div>
          }
        })}
      </Fragment>
    );
  }
}