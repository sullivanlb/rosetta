import React, { Component } from "react";

export default class AffichageClient extends Component {
  render() {
    return (
      <div className="card card-body mb-3">
        <h4>{this.props.nom}</h4>
        <ul className="list-group">
          <li className="list-group-item">Nom : {this.props.nom}</li>
          <li className="list-group-item">Prenom : {this.props.prenom}</li>
          <li className="list-group-item">Email : {this.props.email}</li>
          <li className="list-group-item">Téléphone : {this.props.tel}</li>
        </ul>
      </div>
    );
  }
}
