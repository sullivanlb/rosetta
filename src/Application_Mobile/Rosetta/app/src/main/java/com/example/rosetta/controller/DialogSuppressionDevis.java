package com.example.rosetta.controller;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.rosetta.ui.main.DevisFragment;

/**
 * Cette classe permet d'afficher une boîte de dialog pour demander la confirmation de la suppression d'un devis.
 *
 * @author Alice
 * @version 2.0
 */

public class DialogSuppressionDevis extends AppCompatDialogFragment {

    private DevisFragment devisFragment;

    /**
     * Le constructeur.
     *
     * @param df le contexte {@link DevisFragment}
     */
    public DialogSuppressionDevis(DevisFragment df) {
        this.devisFragment = df;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Suppression d'un devis")
                .setMessage("Voulez-vous supprimer ce devis ? ")
                .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //suppression dans la table devis
                        Controleur.getInstance(devisFragment.getContext()).supprimerDevis(devisFragment.getIdDevis());

                        //suppression dans la table client devis
                        Controleur.getInstance(devisFragment.getContext()).supprimerDevisCD(devisFragment.getIdDevis());

                        //suppression dans la table devis composant
                        Controleur.getInstance(devisFragment.getContext()).supprimerDevisDC(devisFragment.getIdDevis());

                        //suppression dans la table devis pack
                        Controleur.getInstance(devisFragment.getContext()).supprimerDevisDP(devisFragment.getIdDevis());


                        devisFragment.actualiserListeDevis();


                        Toast.makeText(devisFragment.getContext(), "Devis supprimé.", Toast.LENGTH_LONG).show();
                    }
                });
        builder.setNegativeButton("Non", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) { }
        });

        return builder.create();
    }
}
