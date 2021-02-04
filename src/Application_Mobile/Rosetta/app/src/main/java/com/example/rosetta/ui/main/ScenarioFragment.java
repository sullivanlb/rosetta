package com.example.rosetta.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.rosetta.R;
import com.example.rosetta.controller.ScenarioAdapter;
import com.example.rosetta.model.Scenario;

import java.util.ArrayList;

/**
 * Cette classe permet de mettre en place l'ensemble des controleurs correspondants à la vue
 * associée : l'interface de Scenario.
 *
 * @author Alice
 * @version 2.0
 */
public class ScenarioFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_scenario_layout, container, false);

        // Temporaire : les données sont brutes. Elles seront ensuite récupérées depuis la base de
        // données
        ArrayList<Scenario> scenarioListArray = new ArrayList<Scenario>();
        Scenario test1 = new Scenario(1, "Nom court");
        Scenario test2 = new Scenario(2, "Test");
        Scenario test3 = new Scenario(2, "Scenario 3");
        Scenario test4 = new Scenario(2, "Nom de scenario");
        Scenario test5 = new Scenario(2, "Nom très long de scénario comme ça on peut tester tous les cas possibles (exhaustivité)");
        Scenario test6 = new Scenario(2, "Encore un test de nom très long, le voici : deuxieme nom très long de scénario comme ça on peut tester vraiment tous les cas possibles (exhaustivité encore et encore)");
        Scenario test7 = new Scenario(2, "Salle de bain");
        Scenario test8 = new Scenario(2, "Chauffage maison");
        Scenario test9 = new Scenario(2, "Chauffage chambre 2");
        Scenario test10 = new Scenario(2, "Baignoire");
        Scenario test11 = new Scenario(2, "Chauffe-eau");
        Scenario test12 = new Scenario(2, "Scenario facile");
        Scenario test13 = new Scenario(2, "Scenario moyen");
        Scenario test14 = new Scenario(2, "Scenario difficile");
        Scenario test15 = new Scenario(2, "Scenario normal");

        // On ajoute les scénarios dans la liste de scénarios
        scenarioListArray.add(test1);
        scenarioListArray.add(test2);
        scenarioListArray.add(test3);
        scenarioListArray.add(test4);
        scenarioListArray.add(test5);
        scenarioListArray.add(test6);
        scenarioListArray.add(test7);
        scenarioListArray.add(test8);
        scenarioListArray.add(test9);
        scenarioListArray.add(test10);
        scenarioListArray.add(test11);
        scenarioListArray.add(test12);
        scenarioListArray.add(test13);
        scenarioListArray.add(test14);
        scenarioListArray.add(test15);

        // Initialisation de l'adapter pour scenario
        ScenarioAdapter adapter = new ScenarioAdapter(this.getActivity(), scenarioListArray);
        ListView list = (ListView) rootView.findViewById(R.id.scenarioListe);
        list.setAdapter(adapter);

        return rootView;
    }
}
