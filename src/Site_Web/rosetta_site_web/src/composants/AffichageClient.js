import React, { Component } from "react";

export default class AffichageClient extends Component {
  state = this.props.state;

  render() {
    return (
      <div className="card card-body mb-3">
        {this.state.client.map((client) => {
          console.log(this.state.idToDisplay + " | " + client.id);
          if (this.state.idToDisplay === client.id){
            <div>
              <h4>{client.nom}</h4>
              <ul className="list-group">
                <li className="list-group-item">Nom : {client.nom}</li>
                <li className="list-group-item">Prenom : {client.prenom}</li>
                <li className="list-group-item">Email : {client.email}</li>
                <li className="list-group-item">Téléphone : {client.tel}</li>
              </ul>
            </div>
          }
        })}
      </div>
    );
  }
}
