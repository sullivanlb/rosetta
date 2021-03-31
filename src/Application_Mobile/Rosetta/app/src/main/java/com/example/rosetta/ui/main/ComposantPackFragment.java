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
import com.example.rosetta.controller.ComposantAdapter;
import com.example.rosetta.controller.Controleur;
import com.example.rosetta.controller.ControleurEnregistrerModificationComposantPack;
import com.example.rosetta.controller.ControleurListeComposants;
import com.example.rosetta.controller.ControleurListePack;
import com.example.rosetta.controller.PackAdapter;
import com.example.rosetta.model.Client;
import com.example.rosetta.model.Composant;
import com.example.rosetta.model.Pack;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

/**
 * Cette classe permet de mettre en place l'ensemble des controleurs correspondants à la vue
 * associée : l'interface de fragment_composantpack_layout.
 * Elle est accessible depuis le bouton composant/pack de l'interface Scénario .
 *
 * @author Alice
 * @version 2.0
 */

public class ComposantPackFragment extends Fragment {

    private Controleur controleur;
    private View rootView;
    private ArrayList<Composant> listeComposants;
    private ArrayList<Pack> listePacks;

    private ComposantAdapter adapteurComposant;
    private ListView listeViewComposant;
    private int indiceSelectionnerComposant;
    private int idSelectionnnerComposant;

    private  PackAdapter adapteurPack;
    private ListView listeViewPack;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.rootView = inflater.inflate(R.layout.fragment_composantpack_layout, container, false);

        // Le controleur principal (accès à la base de données interne)
        this.controleur = Controleur.getInstance(this.getContext());

        // =============================== COMPOSANT ================================================
        // La liste des composants récupérée depuis la base de données interne
       this.listeComposants = new ArrayList<Composant>(this.controleur.getListeComposants());

        // Initialisation de l'adapteur pour les composants
        this.listeViewComposant = (ListView) rootView.findViewById(R.id.listView_composants_composantspacks);
        ControleurListeComposants controleurListeComposants = new ControleurListeComposants(this);
        this.listeViewComposant.setOnItemClickListener(controleurListeComposants);

        this.adapteurComposant = new ComposantAdapter(this.getActivity(), listeComposants);
        this.listeViewComposant.setAdapter(this.adapteurComposant);

        //================================= PACK ====================================================

        // La liste des composants récupérée depuis la base de données interne
        this.listePacks = new ArrayList<Pack>(this.controleur.getListePacks());

        // Initialisation de l'adapter pour les packs
        this.listeViewPack = (ListView) rootView.findViewById(R.id.listView_packs_composantspacks);
        ControleurListePack controleurListePack = new ControleurListePack(this);
        this.listeViewPack.setOnItemClickListener(controleurListePack);

        this.adapteurPack = new PackAdapter(this.getActivity(), listePacks);
        this.listeViewPack.setAdapter(this.adapteurPack);


        //================================= BOUTONS ====================================================

        // Bouton pour enregistré les modifications d'un composant ou d'un pack.
        Button modificationComposantPack = (Button) rootView.findViewById(R.id.composantpackEnregistrerButton);
        ControleurEnregistrerModificationComposantPack controleurEnregistrerModificationComposantPack = new ControleurEnregistrerModificationComposantPack(this);
        modificationComposantPack.setOnClickListener(controleurEnregistrerModificationComposantPack);

        //Bouton pour  revenir sur l'interface de Scénario
        Button retourBouton = (Button) rootView.findViewById(R.id.composantpackRetourButton);
        retourBouton.setOnClickListener(new View.OnClickListener() {
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


        //Ajouter Un composant
        FloatingActionButton ajoutComposantButton = (FloatingActionButton) rootView.findViewById(R.id.ajoutComposantsButton);
        ajoutComposantButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SectionsPagerAdapter.setScenarioFragment("AjoutComposantFragment");
                FragmentManager frman = getFragmentManager();
                FragmentTransaction ftran = frman.beginTransaction();
                Fragment leFrag = new AjoutComposantFragment();
                ftran.replace(R.id.view_pager, leFrag);
                ftran.commit();
            }
        });

        //Ajouter Un Pack
        FloatingActionButton ajoutPackButton = (FloatingActionButton) rootView.findViewById(R.id.ajoutPacksButton);
        ajoutPackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SectionsPagerAdapter.setScenarioFragment("AjoutPackFragment");
                FragmentManager frman = getFragmentManager();
                FragmentTransaction ftran = frman.beginTransaction();
                Fragment leFrag = new AjoutPackFragment();
                ftran.replace(R.id.view_pager, leFrag);
                ftran.commit();
            }
        });

        // Effectuer une recherche sur la liste de composante et sur la liste de packs
        EditText recherche = (EditText) rootView.findViewById(R.id.rechercher_composants_packs);
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
     * Getter
     * @return la liste des composants
     */
    public ArrayList<Composant> getListeComposants() {
        return listeComposants;
    }


    /**
     * Getter
     * @return l'indice selectionné
     */
    public int getIndiceSelectionnerComposant() {
        return indiceSelectionnerComposant;
    }

    /**
     * Getter
     * @return l'identifiant du composant selectionne
     */
    public int getIdSelectionnnerComposant() {
        return idSelectionnnerComposant;
    }

    /**
     * Setter
     * @param indiceSelectionnerComposant à modifier
     */
    public void setIndiceSelectionnerComposant(int indiceSelectionnerComposant) {
        this.indiceSelectionnerComposant = indiceSelectionnerComposant;
    }

    /**
     * Setter
     * @param idSelectionnnerComposant à modifier
     */
    public void setIdSelectionnnerComposant(int idSelectionnnerComposant) {
        this.idSelectionnnerComposant = idSelectionnnerComposant;
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
