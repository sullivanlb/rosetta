import React, { Component, Fragment } from "react";

/**
 * @description Ce composant représente la section de la page NouveauScneario qui affiche les composants et packs sélectionnés
 *
 * @author Christophe GARCIA
 */
export default class AffichageComposantsPacksNouveauScenario extends Component {

  render() {
    return (
      <Fragment>
        <div className="card card-body mb-3">
          <div className="card card-body mb-3">
            <h4>Composants</h4>
            <ul className="list-group">
              {this.props.state.composants.map((composant) => {
                if (composant.ajoute) {
                  return <li className="list-group-item">{composant.nomComposant} : {composant.quantite}</li>
                }
              })}
            </ul>
          </div>
          <div className="card card-body mb-3">
            <h4>Packs</h4>
            <ul className="list-group">
              {this.props.state.packs.map((pack) => {
                if (pack.ajoute) {
                  return <li className="list-group-item">{pack.nomPack} : {pack.quantite}</li>
                }
              })}
            </ul>
          </div>
        </div>
      </Fragment>
    );
  }
}
