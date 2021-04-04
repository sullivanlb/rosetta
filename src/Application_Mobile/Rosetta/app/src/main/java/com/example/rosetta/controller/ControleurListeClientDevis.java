package com.example.rosetta.controller;

import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;

import com.example.rosetta.R;
import com.example.rosetta.model.Client;
import com.example.rosetta.ui.main.NouveauDevisClientScenarioFragment;

/**
 * Cette classe permet d'afficher les informations du client sélectionné dans le formulaire Devis.
 *
 * @author Lucy
 * @version 2.0
 */

public class ControleurListeClientDevis implements AdapterView.OnItemClickListener {

    private NouveauDevisClientScenarioFragment nouveauDevisClientScenarioFragment;

    /**
     * Le constructeur crée une nouvelle forme de ControleurListeClients
     *
     * @param nDcS le contexte {@link NouveauDevisClientScenarioFragment }
     */
    public ControleurListeClientDevis(NouveauDevisClientScenarioFragment nDcS ) {
        if (nDcS  != null) {
            this.nouveauDevisClientScenarioFragment = nDcS ;
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        // Récupération des informations du client sélectionné
        Client client = this.nouveauDevisClientScenarioFragment.getListeClients().get(position);
        this.nouveauDevisClientScenarioFragment.setIdSelectionnnerClient(client.getIdClient());
        this.nouveauDevisClientScenarioFragment.setIndiceSelectionnerClient(position);
        this.nouveauDevisClientScenarioFragment.setClientChoisi(client);

        //Récupération des informations du formulaire du devis
        EditText editNom = (EditText) this.nouveauDevisClientScenarioFragment.getView().findViewById(R.id.choix_clientEditText);
        editNom.setText(client.getNomClient() + " " + client.getPrenomClient());

    }
}