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
import com.example.rosetta.controller.QuestionAdapter;
import com.example.rosetta.model.Client;
import com.example.rosetta.model.Devis;
import com.example.rosetta.model.Pack;
import com.example.rosetta.model.Question;

import java.util.ArrayList;

/**
 * Cette classe permet de mettre en place l'ensemble des controleurs correspondants à la vue
 * associée : l'interface de Devis.
 *
 * @author Lucy
 * @version 2.0
 */
public class DevisFragment extends Fragment {

    private View rootView;
    private Controleur controleur;

    private ArrayList<Devis> listeDevis;
    private ArrayList<Devis> listeDevisPourCeClient;
    private DevisAdapter adapteurDevis;
    private ListView listeViewDevis;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.rootView = inflater.inflate(R.layout.fragment_devis_layout, container, false);

        // Le controleur principal (accès à la base de données interne)
        this.controleur = Controleur.getInstance(this.getContext());

        //========================== Liste des Devis selon le Client affiché ========================
        this.listeDevis = new ArrayList<Devis>(this.controleur.getListeDevis());

        // La liste déroulante des clients
        Spinner spinnerDevisClients = (Spinner) rootView.findViewById(R.id.spinner_devisClients);
        ArrayAdapter<Client> adapterSpinner = new ArrayAdapter<Client>(this.getContext(), android.R.layout.simple_spinner_dropdown_item, Controleur.getListeClients());
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDevisClients.setAdapter(adapterSpinner);


        // Initialisation de l'adapter pour devis
        this.listeViewDevis = (ListView) rootView.findViewById(R.id.listView_devis);
        this.adapteurDevis= new DevisAdapter(this.getActivity(), this.listeDevis);
        this.listeViewDevis.setAdapter(this.adapteurDevis);

        // ========================== Boutons ======================================================
        Button nouveauDevisButton = (Button) rootView.findViewById(R.id.nouveauDevisButton);
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

        return this.rootView;
    }
}
