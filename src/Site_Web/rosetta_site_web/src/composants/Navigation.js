import React, {useState, useEffect} from 'react'
import "../style/Navigation.css"

export default function Navigation() {
    return(
        <nav>
            <div class="hamburger">
                <div class="line"></div>
                <div class="line"></div>
                <div class="line"></div>
            </div>
            <ul class="nav-links">
                <li><a href="/">Accueil </a></li>
                <li><a href="./Client">Client</a></li>
                <li><a href="./Devis">Devis</a></li>
                <li><a href="./Scenario">Scénario</a></li>
            </ul>
        </nav>
        
    )
    
}
