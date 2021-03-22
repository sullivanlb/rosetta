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
          if (element.type === "composants") {
            if (this.props.idElement === element.id) {
              return <div className="card card-body mb-3" key={this.props.tab.id}>
                <h4>{element.nom}</h4>
                <ul className="list-group">
                  <li className="list-group-item">Nom : {element.nom}</li>
                  <li className="list-group-item">Unite : {element.unite}</li>
                  <li className="list-group-item">Prix : {element.prix}</li>
                </ul>
              </div>
            }
          } else if (element.type === "packs") {
            if (this.props.idElement === element.id) {
              return <div className="card card-body mb-3" key={this.props.tab.id}>
                <h4>{element.nom}</h4>
                <ul className="list-group">
                  <li className="list-group-item">Nom : {element.nom}</li>
                </ul>
              </div>
            }
          }
        })}
      </Fragment>
    );
  }
}
