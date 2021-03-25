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
        {this.props.state.scenarios.map((scenario) => {
          if (this.props.state.idToDisplay === scenario.idScenario) {
            return <div className="card card-body mb-3" key={this.props.state.scenarios.idScenario}>
              <h4>{scenario.nomScenario}</h4>
              <ul className="list-group">
              <div className="card card-body mb-3" key={scenario.idScenario}>
              <ul className="list-group">
              <li className="list-group-item">Nom : {scenario.nomScenario}</li>
              </ul>
              </div>
              <div className="card card-body mb-3" key={scenario.idScenario}>
              <ul className="list-group">
                <li className="list-group-item">Questions : </li>
                {scenario.questions.map((question) => {
                  return <li className="list-group-item">{question.nomQuestion}</li>
                  })
                }
                </ul>
                </div>
                <div className="card card-body mb-3" key={scenario.idScenario}>
                <ul className="list-group">
                  <li className="list-group-item">Composants : </li>
                  {scenario.composants.map((composant) => {
                    return <li className="list-group-item">{composant.nomComposant}</li>
                    })
                  }
                  </ul>
                  </div>
                  <div className="card card-body mb-3" key={scenario.idScenario}>
                  <ul className="list-group">
                    <li className="list-group-item">Packs : </li>
                    {scenario.packs.map((pack) => {
                      return <li className="list-group-item">{pack.nomPack}</li>
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
