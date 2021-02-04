package com.example.rosetta.controller;

import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.rosetta.R;
import com.example.rosetta.ui.main.ClientFragment;

/**
 * Cette classe permet de vider les champs lorsque l'utilisateur s'apprête à en ajouter un nouveau.
 *
 * @author Christophe
 * @version 2.0
 */
public class ControleurClientNouveauClient implements View.OnClickListener {

    private ClientFragment clientFragment;

    /**
     * Le constructeur crée une nouvelle forme de ControleurClientNouveauClient.
     *
     * @param cf le contexte {@link ClientFragment}
     */
    public ControleurClientNouveauClient(ClientFragment cf) {
        this.clientFragment = cf;
    }

    @Override
    public void onClick(View v) {

        // Récupération des champs du formulaire
        EditText editNom = (EditText) this.clientFragment.getView().findViewById(R.id.NomEditText);
        EditText editPrenom = (EditText) this.clientFragment.getView().findViewById(R.id.PrenomEditText);
        EditText editAdresse = (EditText) this.clientFragment.getView().findViewById(R.id.AdresseEditText);
        EditText editEmail = (EditText) this.clientFragment.getView().findViewById(R.id.EmailEditText);
        EditText editTelephone = (EditText) this.clientFragment.getView().findViewById(R.id.TelephoneEditText);
        CheckBox femmeButton = (CheckBox) this.clientFragment.getView().findViewById(R.id.femmeCheckbox);
        CheckBox hommeButton = (CheckBox) this.clientFragment.getView().findViewById(R.id.hommeCheckbox);
        CheckBox autreButton = (CheckBox) this.clientFragment.getView().findViewById(R.id.autreCheckbox);

        // N'affiche plus aucune donnée dans le formulaire
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
