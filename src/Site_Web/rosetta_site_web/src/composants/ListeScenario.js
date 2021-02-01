import React, { Component, Fragment } from "react";
import { ListGroup, ListGroupItem } from "react-bootstrap";

export default class ListeScenario extends Component {
  state = this.props.state;

  render() {
    return (
      <Fragment>
        <ListGroup>
          {this.state.scenario.map((scenario) => (
            <ListGroupItem
              href={scenario.id}
              onClick={() => this.props.action(scenario.id)}
              value={scenario.id}
            >
              {scenario.nom}
            </ListGroupItem>
          ))}
        </ListGroup>
      </Fragment>
    );
  }
}
