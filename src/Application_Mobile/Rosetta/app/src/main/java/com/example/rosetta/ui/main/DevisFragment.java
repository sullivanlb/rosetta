package com.example.rosetta.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.rosetta.R;
import com.example.rosetta.controller.Controleur;
import com.example.rosetta.controller.DevisAdapter;
import com.example.rosetta.model.Client;
import com.example.rosetta.model.Devis;

import java.util.ArrayList;

/**
 * Cette classe permet de mettre en place l'ensemble des controleurs correspondants à la vue
 * associée : l'interface de Devis.
 *
 * @author Alice ~ Lucy
 * @version 2.0
 */
public class DevisFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_devis_layout, container, false);

        // Temporaire : les données sont brutes. Elles seront ensuite récupérées depuis la base de
        // données
        ArrayList<Devis> listeDevis = new ArrayList<Devis>();
        Devis devis1 = new Devis(1,"Devis 1 normal");
        Devis devis2 = new Devis(2, "Devis 2 ok");
        Devis devis3 = new Devis(3, "Devis 3 qui a un nom tres tres tres loooong pour voir si ça passe, comme cela nous testons plusieurs cas possibles, afin d'etre le plus exhaustifs que possible");
        Devis devis4 = new Devis(4, "Devis 4 normal");
        Devis devis5 = new Devis(5, "Devis 5 qui est ok");
        Devis devis6= new Devis(6, "Devis 6 a aussi un nom qui va etre tres tres tres loooong");
        Devis devis7 = new Devis(7, "Devis 7 est ok");
        Devis devis8 = new Devis(8, "Devis 8 normal");
        Devis devis9 = new Devis(9, "Devis 9 est ok");
        Devis devis10 = new Devis(10, "Devis 10 normal");
        Devis devis11 = new Devis(11, "Devis 11 est aussi tres tres looong");
        Devis devis12 = new Devis(12, "Devis 12");
        Devis devis13 = new Devis(13, "Devis 13");
        Devis devis14 = new Devis(14, "Devis 14 est assez long quand meme aussi");
        Devis devis15 = new Devis(15, "Devis 15 normal");

        // On ajoute les devis à la liste de devis
        listeDevis.add(devis1);
        listeDevis.add(devis2);
        listeDevis.add(devis3);
        listeDevis.add(devis4);
        listeDevis.add(devis5);
        listeDevis.add(devis6);
        listeDevis.add(devis7);
        listeDevis.add(devis8);
        listeDevis.add(devis9);
        listeDevis.add(devis10);
        listeDevis.add(devis11);
        listeDevis.add(devis12);
        listeDevis.add(devis13);
        listeDevis.add(devis14);
        listeDevis.add(devis15);

        // La liste déroulante des clients
        Spinner spinnerDevisClients = (Spinner) rootView.findViewById(R.id.spinner_devisClients);
        ArrayAdapter<Client> adapterSpinner = new ArrayAdapter<Client>(this.getContext(), android.R.layout.simple_spinner_dropdown_item, Controleur.getListeClients());
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDevisClients.setAdapter(adapterSpinner);

        // Initialisation de l'adapter pour devis
        DevisAdapter adapter = new DevisAdapter(this.getActivity(), listeDevis);
        ListView list = (ListView) rootView.findViewById(R.id.listView_devis);
        list.setAdapter(adapter);

        Button editerDevisButton = (Button) rootView.findViewById(R.id.editerDevisButton);
        Button nouveauDevisButton = (Button) rootView.findViewById(R.id.nouveauDevisButton);

        editerDevisButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SectionsPagerAdapter.setDevisFragment("NouveauDevisFragment");
                FragmentManager frman = getFragmentManager();
                FragmentTransaction ftran = frman.beginTransaction();
                Fragment leFrag = new NouveauDevisFragment();
                ftran.replace(R.id.view_pager, leFrag);
                ftran.commit();
            }
        });

        nouveauDevisButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SectionsPagerAdapter.setDevisFragment("NouveauDevisClientScenarioFragment");
                FragmentManager frman = getFragmentManager();
                FragmentTransaction ftran = frman.beginTransaction();
                Fragment leFrag = new NouveauDevisClientScenarioFragment();
                ftran.replace(R.id.view_pager, leFrag);
                ftran.commit();
            }
        });

        return rootView;
    }
}
