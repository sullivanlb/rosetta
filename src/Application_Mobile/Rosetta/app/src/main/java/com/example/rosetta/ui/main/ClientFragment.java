package com.example.rosetta.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.rosetta.MainActivity;
import com.example.rosetta.R;
import com.example.rosetta.controller.ControleurCheckSexeClient;
import com.example.rosetta.controller.ControleurEnregistrerClient;
import com.example.rosetta.model.Client;
import com.example.rosetta.model.Sexe;

import java.util.ArrayList;

public class ClientFragment extends Fragment {

    private ArrayList<String> listeClients;
    private ArrayAdapter<CharSequence> adapterNomClients;
    private ListView listeViewClients;
    private Button enregistrerButton;
    private CheckBox femmeButton;
    private CheckBox hommeButton;
    private CheckBox autreButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_client_layout, container, false);

        // Le bouton pour enregistrer les données d'un client (ajouter/sauvegarder)
        this.enregistrerButton = (Button) rootView.findViewById(R.id.EnregistrerButton);
        ControleurEnregistrerClient controleurEnregistrerClient =
                new ControleurEnregistrerClient(this);
        this.enregistrerButton.setOnClickListener(controleurEnregistrerClient);

        // Les cases à cocher pour spécifier le sexe du client
        this.femmeButton = (CheckBox) rootView.findViewById(R.id.femmeCheckbox);
        this.hommeButton = (CheckBox) rootView.findViewById(R.id.hommeCheckbox);
        this.autreButton = (CheckBox) rootView.findViewById(R.id.autreCheckbox);
        ArrayList<CheckBox> listeCases = new ArrayList<CheckBox>();
        listeCases.add(this.femmeButton);
        listeCases.add(this.hommeButton);
        listeCases.add(this.autreButton);
        ControleurCheckSexeClient controleurCheckSexeClient = new ControleurCheckSexeClient(listeCases);
        this.femmeButton.setOnClickListener(controleurCheckSexeClient);
        this.hommeButton.setOnClickListener(controleurCheckSexeClient);
        this.autreButton.setOnClickListener(controleurCheckSexeClient);

        // Temporaire : tant que nous n'avons pas connecté la BDD (liste des clients)
        this.adapterNomClients = ArrayAdapter.createFromResource(getActivity(), R.array.clients, android.R.layout.simple_list_item_1);
        this.listeClients = new ArrayList<String>();
        this.listeViewClients = (ListView) rootView.findViewById(R.id.listView_clients);
        this.listeViewClients.setAdapter(this.adapterNomClients);

        return rootView;
    }

    public void ajouterClient(View view) {
        EditText editPrenom = (EditText) getView().findViewById(R.id.PrenomEditText);
        String prenom = editPrenom.getText().toString();
        EditText editNom = (EditText) getView().findViewById(R.id.NomEditText);
        String nom = editNom.getText().toString();
        EditText editAdresse = (EditText) getView().findViewById(R.id.AdresseEditText);
        String adresse = editAdresse.getText().toString();
        EditText editEmail = (EditText) getView().findViewById(R.id.EmailEditText);
        String email = editEmail.getText().toString();
        EditText editTelephone = (EditText) getView().findViewById(R.id.TelephoneEditText);
        String telephone = editTelephone.getText().toString();

        if (prenom != null && prenom.length() > 0 && nom != null && nom.length() > 0 && adresse != null && adresse.length() > 0 &&
                ((email != null && email.length() > 0) || (telephone != null && telephone.length() > 0))
                && (this.femmeButton.isChecked() || this.hommeButton.isChecked() || this.autreButton.isChecked())) {
            this.adapterNomClients.add(prenom + " " + nom);

            editPrenom.setText("");
            editNom.setText("");
            editAdresse.setText("");
            editEmail.setText("");
            editTelephone.setText("");
            this.femmeButton.setChecked(false);
            this.hommeButton.setChecked(false);
            this.autreButton.setChecked(false);
        } else {
            Toast.makeText(getActivity(), "Veuillez remplir le formulaire", Toast.LENGTH_LONG)
                    .show();
        }
    }
}
