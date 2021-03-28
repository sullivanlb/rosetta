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
import com.example.rosetta.controller.ComposantAdapter;
import com.example.rosetta.controller.PackAdapter;
import com.example.rosetta.model.Composant;
import com.example.rosetta.model.Pack;

import java.util.ArrayList;

/**
 * Cette classe permet de mettre en place l'ensemble des controleurs correspondants à la vue
 * associée : l'interface de fragment_nouveaumodifier_scenario_layout.
 * Elle est accessible depuis le bouton nouveau Scénario de l'interface Scénario .
 *
 * @author Alice, Lucy
 * @version 2.0
 */

public class NouveauModifierScenarioFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_nouveaumodifier_scenario_layout, container, false);

        // Temporaire : les données sont brutes. Elles seront ensuite récupérées depuis la base de
        // données
        ArrayList<Composant> listeComposant = new ArrayList<Composant>();
        Composant composant1 = new Composant(1,"composant1", "m", 0.80);
        Composant composant2 = new Composant(2,"tuyau ", "kg", 1.20);
        Composant composant3 = new Composant(3,"test", "cm", 2.00);
        Composant composant4 = new Composant(4,"composant lonnnnnnnnng", "l", 0.30);


        //On ajoute la liste des composants
        listeComposant.add(composant1);
        listeComposant.add(composant2);
        listeComposant.add(composant3);
        listeComposant.add(composant4);

        // Initialisation de l'adapter pour le composant
        ComposantAdapter adapterComposant = new ComposantAdapter(this.getActivity(), listeComposant);
        ListView listComposant = (ListView) rootView.findViewById(R.id.listView_composants_nouveauscenario);
        listComposant.setAdapter(adapterComposant);


        // Temporaire : les données sont brutes. Elles seront ensuite récupérées depuis la base de
        // données
        ArrayList<Pack> listePack = new ArrayList<Pack>();
        Pack pack1 = new Pack(1,"pack1");
        Pack pack2 = new Pack(2,"pack1");
        Pack pack3 = new Pack(3,"pack1");
        Pack pack4 = new Pack(4,"pack1");

        //On ajoute la liste des composants
        listePack.add(pack1);
        listePack.add(pack2);
        listePack.add(pack3);
        listePack.add(pack4);

        // Initialisation de l'adapter pour le composant
        PackAdapter adapterPack = new PackAdapter(this.getActivity(), listePack);
        ListView listPack = (ListView) rootView.findViewById(R.id.listView_packs_nouveauscenario);
        listPack.setAdapter(adapterPack);

        Button annulerButton = (Button) rootView.findViewById(R.id.nouveauscenarioAnnulerButton);
        Button enregistrerButton = (Button) rootView.findViewById(R.id.nouveauscenarioEnregistrerButton);

        annulerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SectionsPagerAdapter.setScenarioFragment("ScenarioFragment");
                FragmentManager frman = getFragmentManager();
                FragmentTransaction ftran = frman.beginTransaction();
                Fragment leFrag = new ScenarioFragment();
                ftran.replace(R.id.view_pager, leFrag);
                ftran.commit();
            }
        });

        enregistrerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SectionsPagerAdapter.setScenarioFragment("ScenarioFragment");
                FragmentManager frman = getFragmentManager();
                FragmentTransaction ftran = frman.beginTransaction();
                Fragment leFrag = new ScenarioFragment();
                ftran.replace(R.id.view_pager, leFrag);
                ftran.commit();
            }
        });

        return rootView;
    }
}
