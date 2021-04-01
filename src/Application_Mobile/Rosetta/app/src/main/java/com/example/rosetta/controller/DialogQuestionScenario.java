package com.example.rosetta.controller;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.rosetta.ui.main.NouveauScenarioFragment;

/**
 * Cette classe permet d'afficher une boîte de dialog pour demander la confirmation de la suppression d'une question.
 *
 * @author Lucy
 * @version 2.0
 */

public class DialogQuestionScenario extends AppCompatDialogFragment {

    private NouveauScenarioFragment nouveauScenarioFragment;

    /**
     * Le constructeur crée une nouvelle forme de ControleurListeComposants
     *
     * @param nSf le contexte {@link NouveauScenarioFragment}
     */

    public DialogQuestionScenario(NouveauScenarioFragment nSf){
        if(nSf != null){
            this.nouveauScenarioFragment = nSf;
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Suppression d'une question")
                .setMessage("Voulez-vous supprimer cette question ? ")
                .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int position = nouveauScenarioFragment.getIndiceSelectionnerQuestion();
                        System.out.println("Position : " +position);
                        System.out.println("Nom : " + nouveauScenarioFragment.getListeQuestions().get(position).getNomQuestion());
                        nouveauScenarioFragment.getListeQuestions().remove(position);
                        QuestionAdapter2 adapter = new QuestionAdapter2(getActivity(), nouveauScenarioFragment.getListeQuestions());
                        nouveauScenarioFragment.getListeViewQuestion().setAdapter(adapter);


                        Toast.makeText(nouveauScenarioFragment.getContext(), "Question supprimée.", Toast.LENGTH_LONG).show();
                    }
                });
        builder.setNegativeButton("Non", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) { }
        });

        return builder.create();
    }
}
