package com.example.rosetta.ui.main;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.rosetta.R;
import com.example.rosetta.controller.ClientAdapter;
import com.example.rosetta.controller.Controleur;
import com.example.rosetta.controller.ControleurListeClientDevis;
import com.example.rosetta.controller.ControleurListeScenarioDevis;
import com.example.rosetta.controller.ControleurSuivantDevis1;
import com.example.rosetta.controller.QuestionAdapter2;
import com.example.rosetta.controller.ScenarioAdapter2;
import com.example.rosetta.model.Client;
import com.example.rosetta.model.Question;
import com.example.rosetta.model.Scenario;

import java.util.ArrayList;

/**
 * Cette classe permet de mettre en place l'ensemble des controleurs correspondants à la vue
 * associée : l'interface de fragment_nouveaudevis_client_scenario, .
 * Elle est accessible depuis le bouton nouveau Devis le  de l'interface Devis.
 *
 * @author ALice, Lucy
 * @version 2.0
 */

public class NouveauDevisClientScenarioFragment extends Fragment {

    private View rootView;
    private Controleur controleur;

    private ArrayList<Client> listeClients;
    private ClientAdapter adapteurClient;
    private ListView listeViewClient;
    private int indiceSelectionnerClient;
    private int idSelectionnnerClient;
    private Client clientChoisi;

    private ArrayList<Scenario> listeScenarios;
    private ListView listeViewScenarios;
    private ScenarioAdapter2 adapteurScenarios;
    private int indiceSelectionnerScenario;
    private int idSelectionnnerScenario;

    private ArrayList<Scenario> listScenarioTemporaire;
    private ListView listViewScenarioTemp;
    private ScenarioAdapter2 adapteurScenariosTemp;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.rootView = inflater.inflate(R.layout.fragment_nouveaudevis_client_scenario, container, false);

        // Le controleur principal (accès à la base de données interne)
        this.controleur = Controleur.getInstance(this.getContext());


        // ======================= Client ==========================================================

        // La liste des clients récupérée depuis la base de données interne
        this.listeClients = new ArrayList<Client>(this.controleur.getListeClients());

        // Initialisation de l'adapteur pour clients
        this.listeViewClient = (ListView) rootView.findViewById(R.id.listView_clients_nouveauDevis);
        ControleurListeClientDevis controleurListeClientDevis = new ControleurListeClientDevis(this);
        this.listeViewClient.setOnItemClickListener(controleurListeClientDevis);

        this.adapteurClient = new ClientAdapter(this.getActivity(), listeClients);
        this.listeViewClient.setAdapter(this.adapteurClient);


        //============================ Scénario ====================================================

        // La liste des scébarios récupérée depuis la base de données interne
        this.listeScenarios = new ArrayList<Scenario>(this.controleur.getListeScenarios());

        // Initialisation de l'adapteur pour clients
        this.listeViewScenarios = (ListView) rootView.findViewById(R.id.listView_scenarios_nouveauDevis);
        ControleurListeScenarioDevis controleurListeScenarioDevis = new ControleurListeScenarioDevis(this);
        this.listeViewScenarios.setOnItemClickListener(controleurListeScenarioDevis);

        this.adapteurScenarios= new ScenarioAdapter2(this.getActivity(), listeScenarios);
        this.listeViewScenarios.setAdapter(this.adapteurScenarios);

        //Initialisation de la liste des scénarios temporaire
        this.listScenarioTemporaire = new ArrayList<Scenario>();

        this.listViewScenarioTemp = (ListView) rootView.findViewById(R.id.listView_scenarios_choisis);
        this.adapteurScenariosTemp= new ScenarioAdapter2(this.getActivity(), listScenarioTemporaire);
        listViewScenarioTemp.setAdapter(this.adapteurScenariosTemp);


        //=============================== Boutons ==================================================

        // Bouton permettant d'enregistrer le client et les scénarios choisis
        Button nouveauDevisCSSuivantButton = (Button) rootView.findViewById(R.id.nouveauDevisCSSuivantButton);
        ControleurSuivantDevis1 controleurSuivantDevis1 = new ControleurSuivantDevis1(this);
        nouveauDevisCSSuivantButton.setOnClickListener(controleurSuivantDevis1);

        //Bouton pour revenir en arrirère
        Button nouveauDevisCSRetourButton = (Button) rootView.findViewById(R.id.nouveauDevisCSRetourButton);
        nouveauDevisCSRetourButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SectionsPagerAdapter.setDevisFragment("DevisFragment");
                FragmentManager frman = getFragmentManager();
                FragmentTransaction ftran = frman.beginTransaction();
                Fragment leFrag = new DevisFragment();
                ftran.replace(R.id.view_pager, leFrag);
                ftran.commit();
            }
        });

        // Effectuer une recherche sur la liste de client et de scénario
        EditText recherche = (EditText) rootView.findViewById(R.id.rechercher_client_scenario);
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
                    adapteurClient = new ClientAdapter(getActivity(), listeClients);
                    listeViewClient.setAdapter(adapteurClient);

                    ArrayList<Scenario> rechercherListScenario = new ArrayList<Scenario>();
                    for(Scenario scenario : listeScenarios){
                        if(scenario.getNomScenario().contains(input)){
                            rechercherListScenario.add(scenario);
                        }
                    }
                    listeScenarios = rechercherListScenario;
                    adapteurScenarios = new ScenarioAdapter2(getActivity(), listeScenarios);
                    listeViewScenarios.setAdapter(adapteurScenarios);
                }
                else {
                    listeClients = new ArrayList<Client>(controleur.getListeClients());
                    adapteurClient = new ClientAdapter(getActivity(), listeClients);
                    listeViewClient.setAdapter(adapteurClient);

                    listeScenarios = new ArrayList<Scenario>(controleur.getListeScenarios());
                    adapteurScenarios = new ScenarioAdapter2(getActivity(), listeScenarios);
                    listeViewScenarios.setAdapter(adapteurScenarios);
                }
            }
        });

        return this.rootView;
    }

    /**
     * Getter
     *
     * @return la liste des clients
     */
    public ArrayList<Client> getListeClients() {
        return listeClients;
    }

    /**
     * Getter
     *
     * @return la liste des scénarios
     */
    public ArrayList<Scenario> getListeScenarios() {
        return listeScenarios;
    }

    /**
     * Getter
     *
     * @return la liste des scénarios temporairement
     */
    public ArrayList<Scenario> getListScenarioTemporaire() {
        return listScenarioTemporaire;
    }

    /**
     * Getter
     *
     * @return la listeView des scénarios temporairement
     */
    public ListView getListViewScenarioTemp() {
        return listViewScenarioTemp;
    }

    /**
     * Getter
     *
     * @return le client sélectionner
     */
    public Client getClientChoisi() {
        return clientChoisi;
    }

    /**
     * Setter
     *
     * @param  clientChoisi à modifier
     */
    public void setClientChoisi(Client clientChoisi) {
        this.clientChoisi = clientChoisi;
    }

    /**
     * Getter
     *
     * @return l'indeice sélectionné dans la liste des Clients
     */
    public int getIndiceSelectionnerClient() {
        return indiceSelectionnerClient;
    }

    /**
     * Setter
     *
     * @param indiceSelectionnerClient à modifier
     */

    public void setIndiceSelectionnerClient(int indiceSelectionnerClient) {
        this.indiceSelectionnerClient = indiceSelectionnerClient;
    }

    /**
     * Getter
     *
     * @return l'id du client sélectionné
     */

    public int getIdSelectionnnerClient() {
        return idSelectionnnerClient;
    }

    /**
     * Setter
     *
     * @param idSelectionnnerClient à modifier
     */

    public void setIdSelectionnnerClient(int idSelectionnnerClient) {
        this.idSelectionnnerClient = idSelectionnnerClient;
    }

    /**
     * Getter
     *
     * @return l'indeice sélectionné dans la liste des scénarios
     */
    public int getIndiceSelectionnerScenario() {
        return indiceSelectionnerScenario;
    }

    /**
     * Setter
     *
     * @param indiceSelectionnerScenario à modifier
     */
    public void setIndiceSelectionnerScenario(int indiceSelectionnerScenario) {
        this.indiceSelectionnerScenario = indiceSelectionnerScenario;
    }

    /**
     * Getter
     *
     * @return l'id du scénario sélectionné
     */
    public int getIdSelectionnnerScenario() {
        return idSelectionnnerScenario;
    }

    /**
     * Setter
     *
     * @param idSelectionnnerScenario à modifier
     */
    public void setIdSelectionnnerScenario(int idSelectionnnerScenario) {
        this.idSelectionnnerScenario = idSelectionnnerScenario;
    }

    /**
     * Permet d'actualiser la liste affichée des clients lorsqu'un client est ajouté, supprimé ou
     * ses informations ont été modifiées.
     * Met à jour la liste en fonction de la recherche.
     */
    public void actualiserListeClients() {
        listeClients = new ArrayList<Client>(controleur.getListeClients());
        adapteurClient = new ClientAdapter(getActivity(), listeClients);
        listeViewClient.setAdapter(adapteurClient);
        this.rechercher();
    }

    /**
     * Permet d'actualiser la liste affichée des clients lorsqu'un client est ajouté, supprimé ou
     * ses informations ont été modifiées.
     * Met à jour la liste en fonction de la recherche.
     */
    public void actualiserListeScenarios() {
        listeScenarios = new ArrayList<Scenario>(controleur.getListeScenarios());
        adapteurScenarios = new ScenarioAdapter2(getActivity(), listeScenarios);
        listeViewScenarios.setAdapter(adapteurScenarios);
        this.rechercher();
    }

    /**
     * Cette méthode permet de mettre à jour la recherche, et d'afficher les clients et les scénarios recherchés.
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
        adapteurClient = new ClientAdapter(getActivity(), listeClients);
        listeViewClient.setAdapter(adapteurClient);

        ArrayList<Scenario> rechercherListScenario = new ArrayList<Scenario>();
        for(Scenario scenario : listeScenarios){
            if(scenario.getNomScenario().contains(input)){
                rechercherListScenario.add(scenario);
            }
        }
        listeScenarios = rechercherListScenario;
        adapteurScenarios = new ScenarioAdapter2(getActivity(), listeScenarios);
        listeViewScenarios.setAdapter(adapteurScenarios);
    }
}
