import React, { Component, Fragment } from "react";

export default class AffichageClient extends Component {
  state = this.props.state;

  render() {
    return (
      <Fragment>
        {this.state.client.map((client) => {
          if (this.state.idToDisplay === client.id) {
            return <div className="card card-body mb-3" key={this.state.client.id}>
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
      </Fragment>
    );
  }
}
