package com.example.rosetta.controller;

import android.view.View;
import android.widget.AdapterView;

import com.example.rosetta.model.Question;
import com.example.rosetta.ui.main.NouveauScenarioFragment;

/**
 * Cette classe permet d'afficher les informations d'une question sélectionné dans le formulaire.
 *
 * @author Lucy
 * @version 2.0
 */

public class ControleurListeQuestionScenario implements AdapterView.OnItemClickListener{

    private NouveauScenarioFragment nouveauScenarioFragment;

    /**
     * Le constructeur crée une nouvelle forme de ControleurListeComposants
     *
     * @param nSf le contexte {@link NouveauScenarioFragment}
     */

    public  ControleurListeQuestionScenario(NouveauScenarioFragment nSf) {
        if(nSf != null){
            this.nouveauScenarioFragment = nSf;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        // Récupération des informations du pack sélectionné
        Question question = this.nouveauScenarioFragment.getListeQuestionTemporaire().get(position);
        this.nouveauScenarioFragment.setIndiceSelectionnerQuestion(position);

        //Demande la confirmation de supprimer la question.
        openDialog();

    }

    /**
     * Cette méthode lance la boite de dialogue permettant de supprimer une question
     */
    public void openDialog(){

        DialogQuestionScenario dialogQuestionScenario = new DialogQuestionScenario(this.nouveauScenarioFragment);
        dialogQuestionScenario.show(this.nouveauScenarioFragment.getActivity().getSupportFragmentManager(),"Supprimer Question" );
    }
}
