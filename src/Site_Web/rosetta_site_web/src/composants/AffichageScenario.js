import React, { Component, Fragment } from "react";

/**
 * @description Ce composant représente la section de la page Scénario qui affiche ses informations
 *
 * @author Alice GONTARD, Christophe GARCIA
 */
export default class AffichageScenario extends Component {
  render() {
    return (
      <Fragment>
        {this.props.state.list.scenario.map((scenario) => {
          if (this.props.state.idToDisplay === scenario.id) {
            return <div className="card card-body mb-3" key={this.props.state.list.scenario.id}>
              <h4>{scenario.nom}</h4>
              <ul className="list-group">
              <div className="card card-body mb-3" key={scenario.id}>
              <ul className="list-group">
              <li className="list-group-item">Nom : {scenario.nom}</li>
              </ul>
              </div>
              <div className="card card-body mb-3" key={scenario.id}>
              <ul className="list-group">
                <li className="list-group-item">Questions : </li>
                {scenario.questions.map((questions) => {
                  return <li className="list-group-item">{questions.nom}</li>
                  })
                }
                </ul>
                </div>
                <div className="card card-body mb-3" key={scenario.id}>
                <ul className="list-group">
                  <li className="list-group-item">Composants : </li>
                  {scenario.composants.map((composants) => {
                    return <li className="list-group-item">{composants.nom}</li>
                    })
                  }
                  </ul>
                  </div>
                  <div className="card card-body mb-3" key={scenario.id}>
                  <ul className="list-group">
                    <li className="list-group-item">Packs : </li>
                    {scenario.packs.map((packs) => {
                      return <li className="list-group-item">{packs.nom}</li>
                      })
                    }
                    </ul>
                    </div>
              </ul>
            </div>
          }
        })}
      </Fragment>
    );
  }
}
