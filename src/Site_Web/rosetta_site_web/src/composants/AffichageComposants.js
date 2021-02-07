import React, { Component, Fragment } from "react";

/**
 * @description Ce composant représente la section de la page ComposantsPacks qui affiche ses informations
 *
 * @author Alice GONTARD
 */
export default class AffichageComposants extends Component {
  state1 = this.props.state;

  render() {
    return (
      <Fragment>
        {this.state1.composants.map((composants) => {
          if (this.state1.idToDisplay === composants.id) {
            return <div className="card card-body mb-3" key={this.state1.composants.id}>
              <h4>{composants.nom}</h4>
              <ul className="list-group">
                <li className="list-group-item">Nom : {composants.nom}</li>
                <li className="list-group-item">Unité : {composants.unite}</li>
                <li className="list-group-item">Prix : {composants.prix}</li>
              </ul>
            </div>
          }
        })}
      </Fragment>
    );
  }
}
