package com.example.rosetta.controller;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rosetta.R;
import com.example.rosetta.model.Question;
import com.example.rosetta.ui.main.NouveauScenarioFragment;

/**
 * Cette classe permet d'ajouter une question et d'enregistrer celle-ci dans l'Arrayliste
 *
 * @author Lucy
 * @version 2.0
 */


public class ControleurAjoutQuestionScenario implements View.OnClickListener {

    private NouveauScenarioFragment nouveauScenarioFragment;

    /**
     * Le constructeur crée une nouvelle forme de ControleurEnregistrerNouveauPack
     *
     * @param nSf le contexte {@link NouveauScenarioFragment}
     */
    public ControleurAjoutQuestionScenario (NouveauScenarioFragment nSf) {
        this.nouveauScenarioFragment = nSf;
    }

    @Override
    public void onClick(View v) {

        // Récupération du champs du formulaire
        EditText editQuestion = (EditText) this.nouveauScenarioFragment.getView().findViewById(R.id.ajout_questionEditText);
        String questionStr = editQuestion.getText().toString();

        if(questionStr != null && questionStr.length() > 0){

            //Ajoute la question à l'Arrayliste
            Question question = new Question(1, questionStr);
            this.nouveauScenarioFragment.getListeQuestionTemporaire().add(question);

            //Remet le champ à vide
            editQuestion.setText("");

        }
        else{
            Toast.makeText(this.nouveauScenarioFragment.getView().getContext(), "Veuillez saisir une question.", Toast.LENGTH_LONG).show();
        }
    }
}
