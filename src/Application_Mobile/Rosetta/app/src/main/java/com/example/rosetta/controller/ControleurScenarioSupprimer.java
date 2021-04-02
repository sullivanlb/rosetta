package com.example.rosetta.controller;

import android.view.View;

import com.example.rosetta.model.Pack;
import com.example.rosetta.model.Scenario;
import com.example.rosetta.ui.main.ScenarioFragment;

/**
 * Cette classe permet de supprimer un scenario.
 *
 * @author Lucy
 * @version 2.0
 */

public class ControleurScenarioSupprimer implements View.OnClickListener {

    private ScenarioFragment scenarioFragment;
    private int idASupprimer;

    /**
     * Le constructeur crée une nouvelle forme de ControleurListeComposants
     *
     * @param sf le contexte {@link ScenarioFragment}
     */

    public ControleurScenarioSupprimer(ScenarioFragment sf, int id) {
        if(sf != null){
            this.scenarioFragment = sf;
            this.idASupprimer = id;
        }
    }

    @Override
    public void onClick(View v) {
        //Demande la confirmation de supprimer la question.
        this.scenarioFragment.setIdSelectionnnerScenario(idASupprimer);
        openDialog();
    }

    public void  openDialog(){

        DialogSuppressionScenario  dialogSuppressionScenario = new DialogSuppressionScenario(this.scenarioFragment);
        dialogSuppressionScenario.show(this.scenarioFragment.getActivity().getSupportFragmentManager(),"Supprimer Scénario" );
    }
}
