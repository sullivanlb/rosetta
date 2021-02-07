import React, { Component } from "react";
import {MDBNavbar, MDBNavbarBrand, MDBNavbarNav, MDBNavItem, MDBNavLink, MDBCollapse, MDBContainer, MDBHamburgerToggler} from "mdbreact";
import { BrowserRouter as Router } from "react-router-dom";

/**
 * Ce composant représente le menu contenant un menu hamburger
 * Nous ne l'utilisons pas dans notre site web pour l'instant
 * 
 * @author Lucy Gastebois
 */

class NavbarPage extends Component {
  state = {
    collapse1: false,
    collapseID: "",
  };

  toggleCollapse = (collapseID) => () => {
    this.setState((prevState) => ({
      collapseID: prevState.collapseID !== collapseID ? collapseID : "",
    }));
  };

  toggleSingleCollapse = (collapseId) => {
    this.setState({
      ...this.state,
      [collapseId]: !this.state[collapseId],
    });
  };

  render() {
    return (
      <Router>
        <MDBContainer>
          <MDBNavbar
            color="amber lighten-4"
            style={{ marginTop: "20px" }}
            light
          >
            <MDBContainer>
              <MDBNavbarBrand>Menu</MDBNavbarBrand>
              <MDBHamburgerToggler
                color="#d3531a"
                id="hamburger"
                onClick={() => this.toggleSingleCollapse("collapse1")}
              />
              <MDBCollapse isOpen={this.state.collapse1} navbar>
                <MDBNavbarNav left>
                  <MDBNavItem active>
                    <MDBNavLink to="/">Accueil</MDBNavLink>
                  </MDBNavItem>
                  <MDBNavItem>
                    <MDBNavLink to="./Client">Client</MDBNavLink>
                  </MDBNavItem>
                  <MDBNavItem>
                    <MDBNavLink to="./Devis">Devis</MDBNavLink>
                  </MDBNavItem>
                  <MDBNavItem>
                    <MDBNavLink to="./Scenario">Scénario</MDBNavLink>
                  </MDBNavItem>
                </MDBNavbarNav>
              </MDBCollapse>
            </MDBContainer>
          </MDBNavbar>
        </MDBContainer>
      </Router>
    );
  }
}

export default NavbarPage;
