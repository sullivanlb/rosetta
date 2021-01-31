import React from "react";
import { MDBCol, MDBContainer, MDBRow, MDBFooter } from "mdbreact";
import "../style/Footer.css";

const FooterPage = () => {
  return (
    <MDBFooter
      color="black"
      className="mainFoot font-small pt-4 mt-4"
      position="fixed-bottom"
    >
      <MDBContainer fluid className="text-center text-md-left">
        <MDBRow>
          <img className="logo ml-4 mt-2" src="/img/logo.jpg" alt="" />
          <MDBCol md="4">
            <h5 className="title pl-4 ">ADS - Allo Dépanne Service</h5>
            <p className="description pl-4 pr-4">
              ADS prend en charge tous vos travaux de plomberie que ce soit en
              neuf ou en rénovation. La vente et l'installation de salle de bain
              font également partie de nos prestations. Faites-nous part de
              votre projet.
            </p>
          </MDBCol>
          <MDBCol md="2">
            <h5 className="title">Horaires</h5>
            <ul>
              <li className="list-unstyled">Lun - Ven : 08:30 - 19:00</li>
              <li className="list-unstyled">Sam - Dim : Fermé(e)</li>
            </ul>
          </MDBCol>
          <MDBCol md="3">
            <h5 className="title">Informations pratiques</h5>
            <ul>
              <li className="list-unstyled">
                Adresse : 90 avenue Marne , Vannes, 56000, FR
              </li>
              <li className="list-unstyled">Téléphone : 06.60.35.75.45</li>
              <li className="list-unstyled">Email : thourdier@free.fr</li>
            </ul>
          </MDBCol>
          <MDBCol md="2">
            <h5 className="title">Nos activités</h5>
            <ul>
              <li className="list-unstyled">Plomberie</li>
              <li className="list-unstyled">
                Électricité générale (entreprises)
              </li>
              <li className="list-unstyled">
                Salles de bain (vente, installation)
              </li>
            </ul>
          </MDBCol>
        </MDBRow>
      </MDBContainer>
      <div className="footer-copyright text-center pb-2 pt-0">
        <MDBContainer fluid>
          &copy; {new Date().getFullYear()} Copyright:{" "}
          <a href="https://hourdierthierry.site-solocal.com/"> ADS.com </a>
        </MDBContainer>
      </div>
    </MDBFooter>
  );
};

export default FooterPage;
