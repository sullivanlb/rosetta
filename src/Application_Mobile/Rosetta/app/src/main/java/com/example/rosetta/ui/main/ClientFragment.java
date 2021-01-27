package com.example.rosetta.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.rosetta.R;
import com.example.rosetta.controller.ClientAdapter;
import com.example.rosetta.controller.ControleurClientCheckSexe;
import com.example.rosetta.controller.Controleur;
import com.example.rosetta.controller.ControleurClientEnregistrer;
import com.example.rosetta.controller.ControleurClientNouveauClient;
import com.example.rosetta.controller.ControleurListeClients;
import com.example.rosetta.model.Client;

import java.util.ArrayList;

public class ClientFragment extends Fragment {

    private Controleur controleur;
    private ArrayList<Client> listeClients;
    private ClientAdapter adapteur;
    private ListView liste;
    private Button enregistrerBouton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_client_layout, container, false);

        // Le controleur principal (Accès à la BDD interne)
        this.controleur = Controleur.getInstance(this.getContext());

        // Le bouton pour enregistrer les données d'un client (ajouter/sauvegarder)
        this.enregistrerBouton = (Button) rootView.findViewById(R.id.EnregistrerButton);
        ControleurClientEnregistrer controleurEnregistrerClient = new ControleurClientEnregistrer(this);
        this.enregistrerBouton.setOnClickListener(controleurEnregistrerClient);

        // Le bouton pour commencer à créer un nouveau client
        Button nouveauClientBouton = (Button) rootView.findViewById(R.id.nouveauClientButton);
        ControleurClientNouveauClient controleurClientNouveauClient = new ControleurClientNouveauClient(this);
        nouveauClientBouton.setOnClickListener(controleurClientNouveauClient);

        // Les cases à cocher pour spécifier le sexe du client
        CheckBox femmeButton = (CheckBox) rootView.findViewById(R.id.femmeCheckbox);
        CheckBox hommeButton = (CheckBox) rootView.findViewById(R.id.hommeCheckbox);
        CheckBox autreButton = (CheckBox) rootView.findViewById(R.id.autreCheckbox);
        ArrayList<CheckBox> listeCases = new ArrayList<CheckBox>();
        listeCases.add(femmeButton);
        listeCases.add(hommeButton);
        listeCases.add(autreButton);
        ControleurClientCheckSexe controleurCheckSexeClient = new ControleurClientCheckSexe(listeCases);
        femmeButton.setOnClickListener(controleurCheckSexeClient);
        hommeButton.setOnClickListener(controleurCheckSexeClient);
        autreButton.setOnClickListener(controleurCheckSexeClient);

        this.listeClients = this.controleur.getListeClients();

        // Initialisation de l'adapteur pour clients
        this.liste = (ListView) rootView.findViewById(R.id.listView_clients);
        ControleurListeClients controleurListeClients = new ControleurListeClients(this);
        this.liste.setOnItemClickListener(controleurListeClients);
        this.adapteur = new ClientAdapter(this.getActivity(), listeClients);
        this.liste.setAdapter(this.adapteur);

        return rootView;
    }

    public void actualiserListeClients() {
        this.adapteur.notifyDataSetChanged();
    }

    public ListView getListe() {
        return liste;
    }

    public ClientAdapter getAdapteur() {
        return adapteur;
    }
}
