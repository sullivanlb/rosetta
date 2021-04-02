package com.example.rosetta.ui.main;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.rosetta.R;
import com.example.rosetta.controller.ClientAdapter;
import com.example.rosetta.controller.ControleurClientCheckSexe;
import com.example.rosetta.controller.Controleur;
import com.example.rosetta.controller.ControleurEnregistrerNouveauClient;
import com.example.rosetta.controller.ControleurClientSupprimer;
import com.example.rosetta.controller.ControleurListeClients;
import com.example.rosetta.model.Client;

import java.util.ArrayList;

/**
 * Cette classe permet de mettre en place l'ensemble des controleurs correspondants à la vue
 * associée : l'interface de Client.
 * On peut ajouter, modifier, supprimer ou rechercher un client.
 *
 * @author Alice, Christophe, Lucy
 * @version 2.0
 */
public class ClientFragment extends Fragment {

    private View rootView;
    private Controleur controleur;
    private ArrayList<Client> listeClients;
    private ClientAdapter adapteur;
    private ListView liste;
    private Button enregistrerBouton;
    private int indiceSelectionner = -1;
    private int idClient;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.rootView = inflater.inflate(R.layout.fragment_client_layout, container, false);

        // Le controleur principal (accès à la base de données interne)
        this.controleur = Controleur.getInstance(this.getContext());

        // La liste des clients récupérée depuis la base de données interne
        this.listeClients = new ArrayList<Client>(this.controleur.getListeClients());

        // bouton pour enregistrer les données d'un client (ajouter/modifier)
        this.enregistrerBouton = (Button) rootView.findViewById(R.id.EnregistrerButton);
        ControleurEnregistrerNouveauClient controleurEnregistrerClient = new ControleurEnregistrerNouveauClient(this);
        this.enregistrerBouton.setOnClickListener(controleurEnregistrerClient);

        // Le bouton Nouveau Client qui vide les champs
        Button nouveauClientBouton = (Button) rootView.findViewById(R.id.nouveauClientButton);
        nouveauClientBouton.setOnClickListener(controleurEnregistrerClient);

        //bouton pour supprimer un client
        Button supprimerClientBouton  = (Button) rootView.findViewById(R.id.SupprimerClientButton);
        ControleurClientSupprimer controleurClientSupprimer = new ControleurClientSupprimer(this);
        supprimerClientBouton.setOnClickListener(controleurClientSupprimer);


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

        // Initialisation de l'adapteur pour clients
        this.liste = (ListView) rootView.findViewById(R.id.listView_clients);
        ControleurListeClients controleurListeClients = new ControleurListeClients(this);
        this.liste.setOnItemClickListener(controleurListeClients);

        // Effectuer une recherche sur la liste de client
        EditText recherche = (EditText) rootView.findViewById(R.id.rechercher_client);
        recherche.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {

                String input = s.toString();

                if (input != null && !input.equals("") && !input.isEmpty()) {
                    ArrayList<Client> rechercheListClient = new ArrayList<Client>();
                    for (Client c : listeClients) {
                        if (c.getNomClient().contains(input) || c.getPrenomClient().contains(input)) {
                            rechercheListClient.add(c);
                        }
                    }
                    listeClients = rechercheListClient;
                    adapteur = new ClientAdapter(getActivity(), listeClients);
                    liste.setAdapter(adapteur);
                }
                else {
                    listeClients = new ArrayList<Client>(controleur.getListeClients());
                    adapteur = new ClientAdapter(getActivity(), listeClients);
                    liste.setAdapter(adapteur);
                }
            }
        });

        this.adapteur = new ClientAdapter(this.getActivity(), listeClients);
        this.liste.setAdapter(this.adapteur);
        this.setIndiceSelectionner(-1);

        return rootView;
    }

    /**
     * Getter
     * @return la liste des clients
     */
    public ArrayList<Client> getListeClients() {
        return listeClients;
    }

    /**
     * Getter
     * @return l'indice selectionner
     */
    public int getIndiceSelectionner() {
        return indiceSelectionner;
    }

    /**
     * Getter
     * @return l'id du client selectionner
     */
    public int getIdClient() {
        return idClient;
    }

    /**
     * Setter
     * @param indiceSelectionner à modifier
     */
    public void setIndiceSelectionner(int indiceSelectionner) {
        this.indiceSelectionner = indiceSelectionner;
    }

    /**
     * Setter
     * @param idClient à modifier
     */
    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    /**
     * Permet d'actualiser la liste affichée des clients lorsqu'un client est ajouté, supprimé ou
     * ses informations ont été modifiées.
     * Met à jour la liste en fonction de la recherche.
     */
    public void actualiserListeClients() {
        listeClients = new ArrayList<Client>(controleur.getListeClients());
        adapteur = new ClientAdapter(getActivity(), listeClients);
        liste.setAdapter(adapteur);
        this.rechercher();
    }

    /**
     * Cette méthode permet de mettre à jour la recherche, et d'afficher les clients recherchés.
     */
    public void rechercher() {

        EditText recherche = (EditText) rootView.findViewById(R.id.rechercher_client);
        String input = recherche.getText().toString();

        ArrayList<Client> rechercheListClient = new ArrayList<Client>();
        for (Client c : listeClients) {
            if (c.getNomClient().contains(input) || c.getPrenomClient().contains(input)) {
                rechercheListClient.add(c);
            }
        }
        listeClients = rechercheListClient;
        adapteur = new ClientAdapter(getActivity(), listeClients);
        liste.setAdapter(adapteur);
    }
}
