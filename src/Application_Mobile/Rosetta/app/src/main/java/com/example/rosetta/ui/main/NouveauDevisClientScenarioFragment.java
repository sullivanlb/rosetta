package com.example.rosetta.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.rosetta.R;
import com.example.rosetta.controller.ClientAdapter;
import com.example.rosetta.controller.ScenarioAdapter2;
import com.example.rosetta.model.Client;
import com.example.rosetta.model.Scenario;
import com.example.rosetta.model.Sexe;

import java.util.ArrayList;

/**
 * Cette classe permet de mettre en place l'ensemble des controleurs correspondants à la vue
 * associée : l'interface de fragment_nouveaudevis_client_scenario, .
 * Elle est accessible depuis le bouton nouveau Devis le  de l'interface Devis.
 *
 * @author Lucy
 * @version 2.0
 */

public class NouveauDevisClientScenarioFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_nouveaudevis_client_scenario, container, false);

        // Temporaire : les données sont brutes. Elles seront ensuite récupérées depuis la base de
        // données
        ArrayList<Client> listeClient = new ArrayList<Client>();
        /*Client client1 = new Client(1, "Gontard", "Alice", "Rue du soleil", "alice.gontard@gmail.com", "02 00 99 24 28", Sexe.FEMME);
        Client client2 = new Client(2, "LeBoeuf", "Sullivan", "Rue de la mer", "sullivan.leboeuf@gmail.com", "02 00 99 24 28", Sexe.HOMME);
        Client client3 = new Client(3, "Garcia", "Christophe", "Rue de la plage", "christophe.garcia@gmail.com", "02 00 99 24 28", Sexe.HOMME);

        //On ajoute la liste des clients
        listeClient.add(client1);
        listeClient.add(client2);
        listeClient.add(client3);*/

        // Initialisation de l'adapter pour client
        ClientAdapter adapterclient = new ClientAdapter(this.getActivity(), listeClient);
        ListView listClient = (ListView) rootView.findViewById(R.id.listView_clients_nouveauDevis);
        listClient.setAdapter(adapterclient);

        // Temporaire : les données sont brutes. Elles seront ensuite récupérées depuis la base de
        // données
        ArrayList<Scenario> listeScenario = new ArrayList<Scenario>();
        Scenario scenario1 = new Scenario(1,"Installation d'une baignoire");
        Scenario scenario2 = new Scenario(2,"Installation d'un chauffage");
        Scenario scenario3 = new Scenario(3,"Réparation d'une fuite d'eau");


        //On ajoute la liste des scénarios
        listeScenario.add(scenario1);
        listeScenario.add(scenario2);
        listeScenario.add(scenario3);


        // Initialisation de l'adapter pour scenario2
        ScenarioAdapter2 adapterScenario = new ScenarioAdapter2(this.getActivity(), listeScenario);
        ListView listScenario = (ListView) rootView.findViewById(R.id.listView_scenarios_nouveauDevis);
        listScenario.setAdapter(adapterScenario);

        Button nouveauDevisCSRetourButton = (Button) rootView.findViewById(R.id.nouveauDevisCSRetourButton);
        Button nouveauDevisCSSuivantButton = (Button) rootView.findViewById(R.id.nouveauDevisCSSuivantButton);

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

        nouveauDevisCSSuivantButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SectionsPagerAdapter.setDevisFragment("DevisQuestionFragment");
                FragmentManager frman = getFragmentManager();
                FragmentTransaction ftran = frman.beginTransaction();
                Fragment leFrag = new DevisQuestionFragment();
                ftran.replace(R.id.view_pager, leFrag);
                ftran.commit();
            }
        });

        return rootView;
    }

}
