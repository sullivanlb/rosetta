import React, {} from 'react'
import "../style/Navigation.css"
import Hamburger from 'hamburger-react'


export default function Navigation() {
    return(

    <nav>

        <ul class="nav-links">
            <li><a href="/">Accueil </a></li>
            <li><a href="./Client">Client</a></li>
            <li><a href="./Devis">Devis</a></li>
            <li><a href="./Scenario">Scénario</a></li>
        </ul>

        <nav class="navbar-toggler">
            <button class="navbar-toggler ml-auto" type="button" data-toggle="collapse" data-target="#collapsingNavbar3">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="navbar-collapse collapse" id="collapsingNavbar3">
                <ul class="navbar-nav mx-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="/">Accueil</a>
                     </li>
                     <li class="nav-item">
                        <a class="nav-link" href="./Client">Client</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="./Devis">Devis</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="./Scenario">Scenario</a>
                    </li>
                </ul>
            </div>
        </nav>
    </nav>   

    )   
}


