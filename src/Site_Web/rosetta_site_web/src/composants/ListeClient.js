import React, { Component, Fragment } from "react";
import { ListGroup, ListGroupItem } from "react-bootstrap";
import axios from 'axios';

/**
 * @description Ce composant représente la section de la page Client qui énumère les clients
 * 
 * @author Sullivan LEBOEUF & Christophe GARCIA
 */
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
    
/*  En cours de développement : récupération de la liste des clients depuis la BDD externe

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
*/

  render() {
    return (
      <Fragment>
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
      </Fragment>
    );
  }
}
