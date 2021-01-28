import React, { Component } from "react";
import { ListGroup, ListGroupItem } from "react-bootstrap";
import AffichageClient from "./AffichageClient";

export default class ListeClient extends Component {
  state = {
    show: true,
    contact: [
      {
        id: 1,
        nom: "Jacque",
        prenom: "Lors",
        email: "jacquo@gmai.com",
        tel: "0652124100",
      },
      {
        id: 2,
        nom: "Marie",
        prenom: "Poli",
        email: "popolli@hotmail.com",
        tel: "0245789914",
      },
      {
        id: 3,
        nom: "Marc",
        prenom: "Castier",
        email: "castanier@superfree.com",
        tel: "0155669988",
      },
    ],
  };

  montrerClient = () => {
    this.setState({
      show: !this.state.show,
    });
    <AffichageClient />;
  };

  render() {
    return (
      <div>
        <ListGroup>
          <ListGroupItem href="#link1" onClick={this.montrerClient}>
            Link 1
          </ListGroupItem>
          <ListGroupItem href="#link2">Link 2</ListGroupItem>
          <ListGroupItem href="#link3">Link 3</ListGroupItem>
        </ListGroup>
      </div>
    );
  }
}
