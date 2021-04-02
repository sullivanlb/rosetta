package com.example.rosetta.controller;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.rosetta.ui.main.ScenarioFragment;

/**
 * Cette classe permet d'afficher une boîte de dialog pour demander la confirmation de la suppression d'un scénario.
 *
 * @author Lucy
 * @version 2.0
 */


public class DialogSuppressionScenario extends AppCompatDialogFragment {

    private ScenarioFragment scenarioFragment;

    /**
     * Le constructeur crée une nouvelle forme de ControleurListeComposants
     *
     * @param sf le contexte {@link ScenarioFragment}
     */

    public DialogSuppressionScenario(ScenarioFragment sf) {
        if(sf != null){
            this.scenarioFragment = sf;
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Suppression d'un scénario")
                .setMessage("Voulez-vous supprimer ce scénarios ? ")
                .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //suppression dans la table scénario
                        System.out.println("id selectionné : "  + scenarioFragment.getIdSelectionnnerScenario());
                        Controleur.getInstance(scenarioFragment.getContext()).supprimerScenario(scenarioFragment.getIdSelectionnnerScenario());

                        //suppression dans la table scénario question
                        Controleur.getInstance(scenarioFragment.getContext()).supprimerScenarioSQ(scenarioFragment.getIdSelectionnnerScenario());

                        //suppression dans la table scénario pack
                        Controleur.getInstance(scenarioFragment.getContext()).supprimerScenarioSP(scenarioFragment.getIdSelectionnnerScenario());

                        //suppression dans la table scénario composant
                        Controleur.getInstance(scenarioFragment.getContext()).supprimerScenarioSC(scenarioFragment.getIdSelectionnnerScenario());


                        scenarioFragment.actualiserListeScenarios();

                        Toast.makeText(scenarioFragment.getContext(), "Scénario supprimé.", Toast.LENGTH_LONG).show();
                    }
                });
        builder.setNegativeButton("Non", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) { }
        });

        return builder.create();
    }
}
