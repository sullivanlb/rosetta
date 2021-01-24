import React from 'react';


const NouveauClient = () => {
    return(

        <div>
        <h1>Nouveau Client </h1>


            <label for="nom">Nom :</label>
            <input type="text" id="nom" name="client_nom" placeholder="ex: Dubois"
            pattern="[a-zA-Z]+-?[a-zA-Z]*"></input>
            <br></br>

            <label for="prenom">Prénom :</label>
            <input type="text" id="prenom" name="client_prenom" placeholder="ex: Clément"
            pattern="[a-zA-Z]+-?[a-zA-Z]*"></input>
            <br></br>

            <label for="genre">Genre :</label>
            <select id="sexe">
            <option value="F" name="sexe">Femme</option>
            <option value="M" name="sexe">Homme</option>
            <option value="A" name="sexe">Autre</option>
            </select>
            <br></br>

            <label for="adresse">Adresse :</label>
            <input type="adresse" id="adresse" name="client_adresse"
            placeholder="ex: 8 Rue Michel de Montaigne, 56000 Vannes"></input>
            <br></br>

            <label for="mail">Amail :</label>
            <input type="email" id="mail" name="client_mail"
            placeholder="ex: dubois.clement@gmail.com"></input>
            <br></br>

            <label for="telephone">Numéro de Téléphone :</label>
            <input type="telephone" id="telephone" name="client_telephone"
            placeholder="06 35 21 14 48"></input>
            <br></br>

        </div>

    )
}