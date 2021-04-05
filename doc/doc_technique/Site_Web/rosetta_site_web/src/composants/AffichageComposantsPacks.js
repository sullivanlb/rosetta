import React, { Component, Fragment } from "react";

/**
 * @description Ce composant représente la section de la page ComposantsPacks qui affiche ses informations
 *
 * @author Christophe GARCIA
 */
export default class AffichageComposantsPacks extends Component {

  render() {
    return (
      <Fragment>
        {this.props.tab.map((element) => {
          if (element.type === "composant") {
            if (this.props.idElement === element.idComposant) {
              return <div className="card card-body mb-3" key={this.props.tab.idComposant}>
                <h4>Composant</h4>
                <ul className="list-group">
                  <li className="list-group-item">Nom : {element.nomComposant}</li>
                  <li className="list-group-item">Unite : {element.uniteComposant}</li>
                  <li className="list-group-item">Prix : {element.prixComposant}</li>
                </ul>
              </div>
            }
          } else if (element.type === "pack") {
            if (this.props.idElement === element.idPack) {
              return <div className="card card-body mb-3" key={this.props.tab.idPack}>
                <h4>Pack</h4>
                <ul className="list-group">
                  <li className="list-group-item">Nom : {element.nomPack}</li>
                </ul>
                <br></br>
                <h6>Composants</h6>
                <ul className="list-group">
                  {element.composants.map((composant) => {
                    return <li className="list-group-item">{composant.nomComposant} : {composant.quantite}</li>
                  })}
                </ul>
              </div>
            }
          }
        })}
      </Fragment>
    );
  }
}
