package com.example.rosetta.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.rosetta.MainActivity;
import com.example.rosetta.R;
import com.example.rosetta.controller.Controleur;
import com.example.rosetta.controller.ControleurDevisSupprimer;
import com.example.rosetta.controller.DevisAdapter;
import com.example.rosetta.model.Client;
import com.example.rosetta.model.Devis;
import com.example.rosetta.model.Sexe;

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
    private ArrayList<Client> listSpinnerClient;


    private int indiceSelectionner = -1;
    private int idDevis;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.rootView = inflater.inflate(R.layout.fragment_devis_layout, container, false);

        // Le controleur principal (accès à la base de données interne)
        this.controleur = Controleur.getInstance(this.getContext());

        //========================== Liste des Devis selon le Client affiché ========================
        this.listeDevis = new ArrayList<Devis>(this.controleur.getListeDevis());
        this.listSpinnerClient = new ArrayList<Client>(this.controleur.getListeClients());

        Client aucunClient = new Client(0, 0, "Tous", " les devis", "a", "a", "2", Sexe.HOMME);
        this.listSpinnerClient.add(aucunClient);

       // La liste déroulante des clients
        Spinner spinnerDevisClients = (Spinner) rootView.findViewById(R.id.spinner_devisClients);
        ArrayAdapter<Client> adapterSpinner = new ArrayAdapter<Client>(this.getContext(), android.R.layout.simple_spinner_dropdown_item, listSpinnerClient);
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDevisClients.setAdapter(adapterSpinner);

        spinnerDevisClients.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                onItemSelectedHandler(parent, view, position, id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });


        // Initialisation de l'adapter pour devis
        this.listeViewDevis = (ListView) rootView.findViewById(R.id.listView_devis);
        this.listeViewDevis.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // Récupération des informations du client sélectionné
                Devis devis = listeDevisPourCeClient.get(position);
                setIdDevis(devis.getIdDevis());

            }
        });

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
                Fragment leFrag = NouveauDevisClientScenarioFragment.getInstance();
                ftran.replace(R.id.view_pager, leFrag);
                ftran.commit();
                MainActivity.refreshFrag();
            }
        });

        //bouton pour supprimer un devis
        Button supprimerDevisBouton  = (Button) rootView.findViewById(R.id.supprimerDevisButton);
        ControleurDevisSupprimer controleurDevisSupprimer = new ControleurDevisSupprimer(this);
        supprimerDevisBouton.setOnClickListener(controleurDevisSupprimer);

        return this.rootView;
    }

    /**
     * Permet d'actualiser la liste affichée des scénarios.
     */
    public void actualiserListeDevis() {
        listeDevis = new ArrayList<Devis>(controleur.getListeDevis());
        adapteurDevis = new DevisAdapter(getActivity(), listeDevis);
        listeViewDevis.setAdapter(adapteurDevis);
    }

    /**
     * Getter
     * @return l'id du devis selectionner
     */
    public int getIdDevis() {
        return idDevis;
    }


    /**
     * Setter
     * @param idDevis à modifier
     */
    public void setIdDevis(int idDevis) {
        this.idDevis = idDevis;
    }

    /**
     * Permet de récupérer le client sélectionné
     * @param adapterView
     * @param view
     * @param position
     * @param id
     */
    private void onItemSelectedHandler(AdapterView<?> adapterView, View view, int position, long id) {
        Adapter adapter = adapterView.getAdapter();
        Client client = (Client) adapter.getItem(position);

        if(client.getNomClient().equalsIgnoreCase("Tous")){

            listeDevisPourCeClient = this.listeDevis;

            this.adapteurDevis= new DevisAdapter(this.getActivity(), listeDevisPourCeClient);
            this.listeViewDevis.setAdapter(this.adapteurDevis);
        }
        else{

            ArrayList<Integer> tousIdClientCD = Controleur.getInstance(getContext()).getTousLesElementsCD_client();
            ArrayList<Integer> tousIdDevisCD = Controleur.getInstance(getContext()).getTousLesElementsCD_devis();
            listeDevisPourCeClient = new ArrayList<>();


            for(int i = 0; i < tousIdClientCD.size(); i++){
                if(client.getIdClient() == tousIdClientCD.get(i)){
                    for (Devis devis : listeDevis){
                        if (tousIdDevisCD.get(i) == devis.getIdDevis()){
                            listeDevisPourCeClient.add(devis);
                        }
                    }
                }
            }

            this.adapteurDevis= new DevisAdapter(this.getActivity(), this.listeDevisPourCeClient);
            this.listeViewDevis.setAdapter(this.adapteurDevis);

        }
    }
}
