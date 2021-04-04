package com.example.rosetta.controller;


import android.view.View;
import android.widget.AdapterView;

import com.example.rosetta.model.Scenario;
import com.example.rosetta.ui.main.NouveauDevisClientScenarioFragment;

import java.util.ArrayList;

/**
 * Cette classe permet d'afficher les informations du scénario sélectionné dans le formulaire Devis.
 *
 * @author Lucy
 * @version 2.0
 */

public class ControleurListeScenarioDevis implements AdapterView.OnItemClickListener {

    private NouveauDevisClientScenarioFragment nouveauDevisClientScenarioFragment;

    /**
     * Le constructeur crée une nouvelle forme de ControleurListeClients
     *
     * @param nDcS le contexte {@link NouveauDevisClientScenarioFragment }
     */
    public ControleurListeScenarioDevis(NouveauDevisClientScenarioFragment nDcS ) {
        if (nDcS  != null) {
            this.nouveauDevisClientScenarioFragment = nDcS ;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        // Récupération des informations du scénario sélectionné
        Scenario scenario = this.nouveauDevisClientScenarioFragment.getListeScenarios().get(position);
        this.nouveauDevisClientScenarioFragment.setIdSelectionnnerScenario(scenario.getIdScenario());
        this.nouveauDevisClientScenarioFragment.setIndiceSelectionnerScenario(position);

        boolean estDejaAjoute = false;
        int idScenario = this.nouveauDevisClientScenarioFragment.getListeScenarios().get(position).getIdScenario();

        for(Scenario sce : this.nouveauDevisClientScenarioFragment.getListScenarioTemporaire()){
            if(sce.getIdScenario() == idScenario){
                estDejaAjoute = true;
            }
        }

        if(!estDejaAjoute){
            this.nouveauDevisClientScenarioFragment.getListScenarioTemporaire().add(scenario);
        }
        else{
            this.nouveauDevisClientScenarioFragment.getListScenarioTemporaire().remove(position);
        }

        ScenarioAdapter2 adapter2 = new ScenarioAdapter2(this.nouveauDevisClientScenarioFragment.getActivity(), this.nouveauDevisClientScenarioFragment.getListScenarioTemporaire());
        this.nouveauDevisClientScenarioFragment.getListViewScenarioTemp().setAdapter(adapter2);
    }
}
