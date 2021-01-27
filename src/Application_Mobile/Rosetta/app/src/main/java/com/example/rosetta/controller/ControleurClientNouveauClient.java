package com.example.rosetta.controller;

import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.rosetta.R;
import com.example.rosetta.ui.main.ClientFragment;

public class ControleurClientNouveauClient implements View.OnClickListener {

    private ClientFragment clientFragment;

    public ControleurClientNouveauClient(ClientFragment cf) {
        this.clientFragment = cf;
    }

    @Override
    public void onClick(View v) {

        // Affiche plus aucune donnée dans le formulaire
        EditText editNom = (EditText) this.clientFragment.getView().findViewById(R.id.NomEditText);
        EditText editPrenom = (EditText) this.clientFragment.getView().findViewById(R.id.PrenomEditText);
        EditText editAdresse = (EditText) this.clientFragment.getView().findViewById(R.id.AdresseEditText);
        EditText editEmail = (EditText) this.clientFragment.getView().findViewById(R.id.EmailEditText);
        EditText editTelephone = (EditText) this.clientFragment.getView().findViewById(R.id.TelephoneEditText);
        CheckBox femmeButton = (CheckBox) this.clientFragment.getView().findViewById(R.id.femmeCheckbox);
        CheckBox hommeButton = (CheckBox) this.clientFragment.getView().findViewById(R.id.hommeCheckbox);
        CheckBox autreButton = (CheckBox) this.clientFragment.getView().findViewById(R.id.autreCheckbox);

        editPrenom.setText("");
        editNom.setText("");
        editAdresse.setText("");
        editEmail.setText("");
        editTelephone.setText("");
        femmeButton.setChecked(false);
        hommeButton.setChecked(false);
        autreButton.setChecked(false);
    }
}
