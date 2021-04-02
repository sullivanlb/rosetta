package com.example.rosetta.controller;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.rosetta.ui.main.ClientFragment;

/**
 * Cette classe permet d'afficher une boîte de dialog pour demander la confirmation de la suppression d'un client.
 *
 * @author Lucy
 * @version 2.0
 */

public class DialogSuppressionClient extends AppCompatDialogFragment {

    private ClientFragment clientFragment;

    /**
     * Le constructeur crée une nouvelle forme de ControleurClientSupprimer.
     *
     * @param cf le contexte {@link ClientFragment}
     */
    public DialogSuppressionClient(ClientFragment cf) {
        this.clientFragment = cf;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Suppression d'un client")
                .setMessage("Voulez-vous supprimer ce client ? ")
                .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Controleur.getInstance(clientFragment.getContext()).supprimerClient(clientFragment.getIdClient());
                        clientFragment.actualiserListeClients();


                        Toast.makeText(clientFragment.getContext(), "Client supprimé.", Toast.LENGTH_LONG).show();
                    }
                });
        builder.setNegativeButton("Non", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) { }
        });

        return builder.create();
    }
}
