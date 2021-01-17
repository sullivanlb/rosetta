import React from "react";
import { MDBCol, MDBContainer, MDBRow, MDBFooter } from "mdbreact";
import "../style/Footer.css";

const FooterPage = () => {
  return (
    <MDBFooter color="elegant-color" className="font-small pt-4 mt-4"  position="bottom">
      <MDBContainer fluid className="text-center text-md-left">
        <MDBRow>
          <MDBCol md="6">
            <h5 className="title pl-4 pr-4">ADS - Allo Dépanne Service</h5>
            <p className="description pl-4 pr-4">
              Here you can use rows and columns here to organize your footer
              content.
            </p>
          </MDBCol>
          <MDBCol md="2">
            <h5 className="title text-center">Liens</h5>
            <ul>
              <li className="list-unstyled text-center">
                <a href="/accueil">Accueil</a>
              </li>
              <li className="list-unstyled text-center">
                <a href="#!">Autres</a>
              </li>
            </ul>
          </MDBCol>
        </MDBRow>
      </MDBContainer>
      <div className="footer-copyright text-center py-3">
        <MDBContainer fluid>
          &copy; {new Date().getFullYear()} Copyright: <a href="/"> ADS.com </a>
        </MDBContainer>
      </div>
    </MDBFooter>
  );
}

export default FooterPage;