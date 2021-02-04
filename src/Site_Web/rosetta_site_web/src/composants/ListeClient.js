import React, { Component } from "react";
import { ListGroup, ListGroupItem } from "react-bootstrap";
import axios from 'axios';

export default class ListeClient extends Component {
  state = this.props.state;

  handleSubmit = e => {
    e.preventDefault();
    console.log(this.state.nom + "\n" + this.state.prenom + "\n" + this.state.email + "\n" + this.state.adresse + "\n" + this.state.tel + "\n" + this.state.sexe);
    let formData = new FormData();
    formData.append("nom", this.state.nom);
    formData.append("prenom", this.state.prenom);
    formData.append("adresse", this.state.adresse);
    formData.append("email", this.state.email);
    formData.append("tel", this.state.tel);
    formData.append("sexe", this.state.sexe);
    
    // axios.post(url, formData)
    // .then(res => console.log(res.data))
    // .catch(err => console.log(err));

    const url = "http://localhost/api/";
    axios.get(url)
    .then(function (response) {
      // handle success
      console.log(response);
    })
    .catch(function (error) {
      // handle error
      console.log(error);
    });
  }

  render() {
    return (
      <div>
        <ListGroup>
          {this.state.client.map((client) => (
            <ListGroupItem
              href={client.id}
              onClick={() => this.props.action(client.id)}
              value={client.id}
            >
              {client.nom} {client.prenom}
            </ListGroupItem>
          ))}
        </ListGroup>
      </div>
    );
  }
}
