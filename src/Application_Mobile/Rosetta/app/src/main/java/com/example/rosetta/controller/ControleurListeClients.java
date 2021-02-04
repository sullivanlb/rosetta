package com.example.rosetta.controller;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.rosetta.R;
import com.example.rosetta.model.Client;
import com.example.rosetta.model.Sexe;
import com.example.rosetta.ui.main.ClientFragment;

/**
 * Cette classe permet d'afficher les informations du client sélectionné dans le formulaire.
 *
 * @author Christophe
 * @version 2.0
 */
public class ControleurListeClients implements AdapterView.OnItemClickListener {

    private ClientFragment clientFragment;

    /**
     * Le constructeur crée une nouvelle forme de ControleurListeClients
     *
     * @param cf le contexte {@link ClientFragment}
     */
    public ControleurListeClients(ClientFragment cf) {
        if (cf != null) {
            this.clientFragment = cf;
        }
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        // Récupération des informations du client sélectionné
        Client client = Controleur.getInstance(this.clientFragment.getContext()).getListeClients().get(position);

        // Récupération des champs du formulaire
        EditText editNom = (EditText) this.clientFragment.getView().findViewById(R.id.NomEditText);
        EditText editPrenom = (EditText) this.clientFragment.getView().findViewById(R.id.PrenomEditText);
        EditText editAdresse = (EditText) this.clientFragment.getView().findViewById(R.id.AdresseEditText);
        EditText editEmail = (EditText) this.clientFragment.getView().findViewById(R.id.EmailEditText);
        EditText editTelephone = (EditText) this.clientFragment.getView().findViewById(R.id.TelephoneEditText);
        CheckBox femmeButton = (CheckBox) this.clientFragment.getView().findViewById(R.id.femmeCheckbox);
        CheckBox hommeButton = (CheckBox) this.clientFragment.getView().findViewById(R.id.hommeCheckbox);
        CheckBox autreButton = (CheckBox) this.clientFragment.getView().findViewById(R.id.autreCheckbox);

        // Remplit les champs suivant les informations du client
        editNom.setText(client.getNomClient(), null);
        editPrenom.setText(client.getPrenomClient(), null);
        editAdresse.setText(client.getAdresseClient(), null);
        editEmail.setText(client.getEmailClient(), null);
        editTelephone.setText(client.getTelClient(), null);

        // Coche la case suivant le sexe du client
        if (client.getSexeClient() == Sexe.FEMME) {
            femmeButton.setChecked(true);
            hommeButton.setChecked(false);
            autreButton.setChecked(false);
        } else if (client.getSexeClient() == Sexe.HOMME) {
            femmeButton.setChecked(false);
            hommeButton.setChecked(true);
            autreButton.setChecked(false);
        } else {
            femmeButton.setChecked(false);
            hommeButton.setChecked(false);
            autreButton.setChecked(true);
        }
    }
}
