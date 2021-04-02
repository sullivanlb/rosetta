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
import com.example.rosetta.controller.ComposantAdapter;
import com.example.rosetta.controller.Controleur;
import com.example.rosetta.controller.ControleurListeComposants;
import com.example.rosetta.controller.ControleurListePack;
import com.example.rosetta.controller.PackAdapter;
import com.example.rosetta.model.Composant;
import com.example.rosetta.model.Pack;

import java.util.ArrayList;

/**
 * Cette classe permet de mettre en place l'ensemble des controleurs correspondants à la vue
 * associée : l'interface de fragment_nouveaudevis_final_layout.
 * Elle est accessible depuis le bouton nouveau Devis/suivant le  de l'interface Devis.
 *
 * @author Lucy
 * @version 2.0
 */

public class NouveauDevisFragment extends Fragment {

    private View rootView;
    private Controleur controleur;

    private ArrayList<Composant> listeComposants;
    private ListView listeViewComposant;
    private ComposantAdapter adapteurComposant;

    private ArrayList<Pack> listePacks;
    private ListView listeViewPack;
    private PackAdapter adapteurPack;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.rootView = inflater.inflate(R.layout.fragment_nouveaudevis_final_layout, container, false);

        // Le controleur principal (accès à la base de données interne)
        this.controleur = Controleur.getInstance(this.getContext());

        // =============================== COMPOSANT ================================================
        // La liste des composants récupérée depuis la base de données interne
        this.listeComposants = new ArrayList<Composant>(this.controleur.getListeComposants());

        // Initialisation de l'adapteur pour les composants
        this.listeViewComposant = (ListView) rootView.findViewById(R.id.listView_composants_nouveaudevis);
        ControleurListeComposants controleurListeComposants = new ControleurListeComposants(this);
        this.listeViewComposant.setOnItemClickListener(controleurListeComposants);

        this.adapteurComposant = new ComposantAdapter(this.getActivity(), listeComposants);
        this.listeViewComposant.setAdapter(this.adapteurComposant);

        //================================= PACK ====================================================

        // La liste des composants récupérée depuis la base de données interne
        this.listePacks = new ArrayList<Pack>(this.controleur.getListePacks());

        // Initialisation de l'adapter pour les packs
        this.listeViewPack = (ListView) rootView.findViewById(R.id.listView_packs_nouveaudevis);
        ControleurListePack controleurListePack = new ControleurListePack(this);
        this.listeViewPack.setOnItemClickListener(controleurListePack);

        this.adapteurPack = new PackAdapter(this.getActivity(), listePacks);
        this.listeViewPack.setAdapter(this.adapteurPack);


        //============================= Boutons ======================================================


        //bouton qui permet de revenir en arrière
        Button nouveaudevisRetourButton = (Button) rootView.findViewById(R.id.nouveaudevisRetourButton);
        nouveaudevisRetourButton.setOnClickListener(new View.OnClickListener() {
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

        //Bouton pour enregistrer un élèment dans la liste
        Button nouveaudevisEnregistrerListeButton = (Button) rootView.findViewById(R.id.nouveaudevisEnregistrerListeButton);
        nouveaudevisEnregistrerListeButton.setOnClickListener(new View.OnClickListener() {
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

        //Bouton pour enregistrer un nouveau devis
        Button nouveaudevisEnregistrerDevisButton = (Button) rootView.findViewById(R.id.nouveaudevisEnregistrerDevisButton);
        nouveaudevisEnregistrerDevisButton.setOnClickListener(new View.OnClickListener() {
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

        // Effectuer une recherche sur la liste de composante et sur la liste de packs
        EditText recherche = (EditText) rootView.findViewById(R.id.rechercher_composants_packs_nouveaudevis);
        recherche.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {

                String input = s.toString();

                if (input != null && !input.equals("") && !input.isEmpty()) {

                    ArrayList<Composant> rechercheListComposant= new ArrayList<Composant>();
                    ArrayList<Pack> rechercheListPack = new ArrayList<Pack>();
                    for (Composant c : listeComposants) {
                        if (c.getNomComposant().contains(input)) {
                            rechercheListComposant.add(c);
                        }
                    }
                    listeComposants = rechercheListComposant;
                    adapteurComposant = new ComposantAdapter(getActivity(), listeComposants);
                    listeViewComposant.setAdapter(adapteurComposant);

                    for(Pack p : listePacks){
                        if(p.getNomPack().contains(input)){
                            rechercheListPack.add(p);
                        }
                    }
                    listePacks = rechercheListPack;
                    adapteurPack = new PackAdapter(getActivity(), listePacks);
                    listeViewPack.setAdapter(adapteurPack);

                }
                else {
                    listeComposants = new ArrayList<Composant>(controleur.getListeComposants());
                    adapteurComposant = new ComposantAdapter(getActivity(), listeComposants);
                    listeViewComposant.setAdapter(adapteurComposant);

                    listePacks = new ArrayList<Pack>(controleur.getListePacks());
                    adapteurPack = new PackAdapter(getActivity(), listePacks);
                    listeViewPack.setAdapter(adapteurPack);
                }
            }
        });

        return this.rootView;
    }

    /**
     * Permet d'actualiser la liste affichée des composants lorsqu'un composant est ajouté, ou
     * ses informations ont été modifiées.
     */
    public void actualiserListeComposants() {
        listeComposants = new ArrayList<Composant>(controleur.getListeComposants());
        adapteurComposant = new ComposantAdapter(getActivity(), listeComposants);
        listeViewComposant.setAdapter(adapteurComposant);
        rechercher();
    }

    /**
     * Permet d'actualiser la liste affichée des composants lorsqu'un pack est ajouté, ou
     * ses informations ont été modifiées.
     */
    public void actualiserListePacks(){
        listePacks = new ArrayList<Pack>(controleur.getListePacks());
        adapteurPack = new PackAdapter(getActivity(), listePacks);
        listeViewPack.setAdapter(adapteurPack);
        rechercher();
    }

    /**
     * Cette méthode permet de mettre à jour la recherche, et d'afficher les composant et les packs recherchés.
     */
    public void rechercher() {

        EditText recherche = (EditText) rootView.findViewById(R.id.rechercher_composants_packs);
        String input = recherche.getText().toString();

        ArrayList<Composant> rechercheListComposant= new ArrayList<Composant>();
        ArrayList<Pack> rechercheListPack = new ArrayList<Pack>();
        for (Composant c : listeComposants) {
            if (c.getNomComposant().contains(input)) {
                rechercheListComposant.add(c);
            }
        }
        listeComposants = rechercheListComposant;
        adapteurComposant = new ComposantAdapter(getActivity(), listeComposants);
        listeViewComposant.setAdapter(adapteurComposant);

        for(Pack p : listePacks){
            if(p.getNomPack().contains(input)){
                rechercheListPack.add(p);
            }
        }
        listePacks = rechercheListPack;
        adapteurPack = new PackAdapter(getActivity(), listePacks);
        listeViewPack.setAdapter(adapteurPack);
    }
}
