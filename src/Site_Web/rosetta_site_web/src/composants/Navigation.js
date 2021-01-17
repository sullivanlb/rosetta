import React, {Component} from 'react';
import {NavLink} from 'react-router-dom';
import {Navbar, Nav} from 'react-bootstrap';

export class Navigation extends Component {
    render(){
        return(

            
            <Nav class = "navbar navbar-expand-lg navbar-dark bg-dark">
            <a class="navbar-brand" href="/">Menu</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav text-white">
                <li class="nav-item active text-white">
                    <a class="nav-link text-white" href="/">Accueil <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-white" href="/Client">Client</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-white" href="/Devis">Devis</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-white" href="/Scenario">Scenario</a>
                </li>
                </ul>
            </div>
            </Nav>
            

        )
    }
}