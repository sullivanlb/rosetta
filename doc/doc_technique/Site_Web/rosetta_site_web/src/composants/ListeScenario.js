import React, { Component, Fragment } from "react";
import { ListGroup, ListGroupItem } from "react-bootstrap";

/**
 * @description Ce composant représente la section de la page Scénario qui énumère les scénarios.
 *
 * @author Alice GONTARD, Christophe GARCIA
 */
export default class ListeScenario extends Component {
  render() {
    return (
      <Fragment>
        <ListGroup>
          {this.props.state.scenarios.map((scenario) => (
            <ListGroupItem
              href={scenario.idScenario}
              onClick={() => this.props.action(scenario.idScenario)}
              value={scenario.idScenario}
            >
              {scenario.nomScenario}
            </ListGroupItem>
          ))}
        </ListGroup>
      </Fragment>
    );
  }
}
