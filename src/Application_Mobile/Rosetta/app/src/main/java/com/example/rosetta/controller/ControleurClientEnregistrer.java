package com.example.rosetta.controller;

import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rosetta.R;
import com.example.rosetta.model.Client;
import com.example.rosetta.ui.main.ClientFragment;

import static com.example.rosetta.model.Sexe.AUTRE;
import static com.example.rosetta.model.Sexe.FEMME;
import static com.example.rosetta.model.Sexe.HOMME;

public class ControleurClientEnregistrer implements View.OnClickListener {

    private ClientFragment clientFragment;

    public ControleurClientEnregistrer(ClientFragment cf) {
        this.clientFragment = cf;
    }

    @Override
    public void onClick(View v) {
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

        if (nom != null && nom.length() > 0 && prenom != null && prenom.length() > 0 && adresse != null && adresse.length() > 0 &&
                ((email != null && email.length() > 0) || (tel != null && tel.length() > 0))
                && (femmeButton.isChecked() || hommeButton.isChecked() || autreButton.isChecked())) {
            Client client = new Client(0, nom, prenom, adresse, email != null ? email : "", tel != null ? tel : "", (femmeButton.isChecked()) ? FEMME :
                    (hommeButton.isChecked()) ? HOMME : AUTRE);
            Controleur.getInstance(this.clientFragment.getContext()).creerClient(client);
            this.clientFragment.actualiserListeClients();

            editPrenom.setText("");
            editNom.setText("");
            editAdresse.setText("");
            editEmail.setText("");
            editTelephone.setText("");
            femmeButton.setChecked(false);
            hommeButton.setChecked(false);
            autreButton.setChecked(false);
        } else {
            Toast.makeText(this.clientFragment.getView().getContext(), "Veuillez remplir le formulaire.", Toast.LENGTH_LONG).show();
        }
    }
}
