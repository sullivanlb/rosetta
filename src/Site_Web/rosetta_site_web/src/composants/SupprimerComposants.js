import React, { useState } from "react";
import { Button, Modal } from "react-bootstrap";

function Avertissement() {
  const [show, setShow] = useState(false);

  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);

  return (
    <>
      <Button variant="btn btn-light" onClick={handleShow}>
        Supprimer
      </Button>

      <Modal show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>Avertissement</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          Attention, le composant selectionné sera supprimé définitivement !
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
    </>
  );
}

export default Avertissement;