package com.example.rosetta.controller;


import android.view.View;
import android.widget.AdapterView;

import com.example.rosetta.model.Pack;
import com.example.rosetta.model.Scenario;
import com.example.rosetta.ui.main.NouveauScenarioFragment;
import com.example.rosetta.ui.main.ScenarioFragment;

public class ControleurListeScenario implements AdapterView.OnItemClickListener {

    private ScenarioFragment scenarioFragment;

    /**
     * Le constructeur crée une nouvelle forme de ControleurListeComposants
     *
     * @param sf le contexte {@link ScenarioFragment}
     */

    public ControleurListeScenario(ScenarioFragment sf) {
        if(sf != null){
            this.scenarioFragment = sf;
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        // Récupération des informations du scénario sélectionné
        Scenario scenario = this.scenarioFragment.getListeScenarios().get(position);
        this.scenarioFragment.setIdSelectionnnerScenario(scenario.getIdScenario());
        this.scenarioFragment.setIndiceSelectionnerScenario(position);

    }
}
