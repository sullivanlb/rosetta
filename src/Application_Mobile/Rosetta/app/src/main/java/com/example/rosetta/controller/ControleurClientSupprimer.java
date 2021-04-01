package com.example.rosetta.controller;

import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.rosetta.R;
import com.example.rosetta.model.Client;
import com.example.rosetta.ui.main.ClientFragment;

import java.util.ArrayList;

/**
 * Cette classe permet de supprimer un client lorsque celui-ci est sélectionné.
 *
 * @author Christophe, Lucy
 * @version 2.0
 */
public class ControleurClientSupprimer implements View.OnClickListener {

    private ClientFragment clientFragment;

    /**
     * Le constructeur crée une nouvelle forme de ControleurClientSupprimer.
     *
     * @param cf le contexte {@link ClientFragment}
     */
    public ControleurClientSupprimer(ClientFragment cf) {
        this.clientFragment = cf;
    }

    @Override
    public void onClick(View v) {

        // Récupération des informations du formulaire du client
        EditText editNom = (EditText) this.clientFragment.getView().findViewById(R.id.NomEditText);
        String nom = editNom.getText().toString();
        EditText editPrenom = (EditText) this.clientFragment.getView().findViewById(R.id.PrenomEditText);
        String prenom = editPrenom.getText().toString();
        EditText editAdresse = (EditText) this.clientFragment.getView().findViewById(R.id.AdresseEditText);
        String adresse = editAdresse.getText().toString();
        EditText editEmail = (EditText) this.clientFragment.getView().findViewById(R.id.EmailEditText);
        String email = editEmail.getText().toString();
        EditText editTelephone = (EditText) this.clientFragment.getView().findViewById(R.id.TelephoneEditText);
        String tel = editTelephone.getText().toString();
        CheckBox femmeButton = (CheckBox) this.clientFragment.getView().findViewById(R.id.femmeCheckbox);
        CheckBox hommeButton = (CheckBox) this.clientFragment.getView().findViewById(R.id.hommeCheckbox);
        CheckBox autreButton = (CheckBox) this.clientFragment.getView().findViewById(R.id.autreCheckbox);

        //Demande la confirmation de supprimer la question.
        openDialog();

        // Vidage des champs
        editPrenom.setText("");
        editNom.setText("");
        editAdresse.setText("");
        editEmail.setText("");
        editTelephone.setText("");
        femmeButton.setChecked(false);
        hommeButton.setChecked(false);
        autreButton.setChecked(false);

    }

    public void  openDialog(){

        DialogSuppressionClient dialogSuppressionClient = new DialogSuppressionClient(this.clientFragment);
        dialogSuppressionClient.show(this.clientFragment.getActivity().getSupportFragmentManager(),"Supprimer Client" );
    }
}
