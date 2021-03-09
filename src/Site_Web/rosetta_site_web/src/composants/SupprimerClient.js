import React, { Component, Fragment } from "react";
import { Button, Modal } from "react-bootstrap";

/**
 * Cette fonction créera un avertissement lorsque le bouton de suppression sera cliqué
 * 
 * @author Sullivan LEBOEUF
 */
 export default class Supprimer extends Component {
   constructor(props) {
     super(props);
     this.state = {
       show: false
     };
   }

   handleClose() {
    this.setState({show: false});
   }

   handleShow() {
    this.setState({show: true});
   }

render(){
  return (
    <Fragment>
      <Button variant="btn btn-light" onClick={this.handleShow()}>
        Supprimer Client
      </Button>

      <Modal show={this.state.show} onHide={this.handleClose()}>
        <Modal.Header closeButton>
          <Modal.Title>Avertissement</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          Attention, le client selectionné sera supprimé définitivement !
        </Modal.Body>
        <Modal.Footer>
          <Button variant="ligth" onClick={() => {
            this.handleClose();
            
            }}>
            Annuler
          </Button>
          <Button variant="danger" onClick={this.handleClose()}>
            Supprimer
          </Button>
        </Modal.Footer>
      </Modal>
    </Fragment>
  );
}
 }
