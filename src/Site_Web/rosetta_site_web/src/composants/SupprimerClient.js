import React, { useState, Fragment } from "react";
import { Button, Modal } from "react-bootstrap";

/**
 * This function will create an advertising when delete button clicked
 * 
 * @author Sullivan LEBOEUF
 */
function Avertissement() {
  const [show, setShow] = useState(false);

  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);

  return (
    <Fragment>
      <Button variant="btn btn-light" onClick={handleShow}>
        Supprimer Client
      </Button>

      <Modal show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>Avertissement</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          Attention, le client selectionné sera supprimé définitivement !
        </Modal.Body>
        <Modal.Footer>
          <Button variant="ligth" onClick={handleClose}>
            Annuler
          </Button>
          <Button variant="danger" onClick={handleClose}>
            Supprimer
          </Button>
        </Modal.Footer>
      </Modal>
    </Fragment>
  );
}

export default Avertissement;
