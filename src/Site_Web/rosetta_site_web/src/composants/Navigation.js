import React from "react";
import "../style/Navigation.css";
//import Hamburger from 'hamburger-react'

export default function Navigation() {
  return (
    <nav>
      <ul className="nav-links">
        <li>
          <a href="/">Accueil </a>
        </li>
        <li>
          <a href="/client">Client</a>
        </li>
        <li>
          <a href="/devis">Devis</a>
        </li>
        <li>
          <a href="/scenario">Scénario</a>
        </li>
      </ul>

      <nav className="navbar-toggler">
        <button
          className="navbar-toggler ml-auto"
          type="button"
          data-toggle="collapse"
          data-target="#collapsingNavbar3"
        >
          <span className="navbar-toggler-icon"></span>
        </button>
        <div className="navbar-collapse collapse" id="collapsingNavbar3">
          <ul className="navbar-nav mx-auto">
            <li className="nav-item active">
              <a className="nav-link" href="/">
                Accueil
              </a>
            </li>
            <li className="nav-item">
              <a className="nav-link" href="/client">
                Client
              </a>
            </li>
            <li className="nav-item">
              <a className="nav-link" href="/devis">
                Devis
              </a>
            </li>
            <li className="nav-item">
              <a className="nav-link" href="/scenario">
                Scenario
              </a>
            </li>
          </ul>
        </div>
      </nav>
    </nav>
  );
}
