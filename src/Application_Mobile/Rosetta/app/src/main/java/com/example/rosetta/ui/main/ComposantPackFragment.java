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
import com.example.rosetta.controller.Controleur;
import com.example.rosetta.controller.ControleurEnregistrerModificationComposantPack;
import com.example.rosetta.controller.ControleurListeComposants;
import com.example.rosetta.controller.PackAdapter;
import com.example.rosetta.model.Composant;
import com.example.rosetta.model.Pack;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * Cette classe permet de mettre en place l'ensemble des controleurs correspondants à la vue
 * associée : l'interface de fragment_composantpack_layout.
 * Elle est accessible depuis le bouton composant/pack de l'interface Scénario .
 *
 * @author Alice
 * @version 2.0
 */

public class ComposantPackFragment extends Fragment {

    private ArrayList<Composant> listeComposants;
    private Controleur controleur;
    private ComposantAdapter adapteurComposant;
    private ListView listeViewComposant;
    private int indiceSelectionnerComposant;
    private int idComposant;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_composantpack_layout, container, false);

        // Le controleur principal (accès à la base de données interne)
        this.controleur = Controleur.getInstance(this.getContext());

        // =============================== COMPOSANT ================================================
        // La liste des composants récupérée depuis la base de données interne
       this.listeComposants = new ArrayList<Composant>(this.controleur.getListeComposants());

        // Initialisation de l'adapteur pour composant
        this.listeViewComposant = (ListView) rootView.findViewById(R.id.listView_composants_composantspacks);
        ControleurListeComposants controleurListeComposants = new ControleurListeComposants(this);
        this.listeViewComposant.setOnItemClickListener(controleurListeComposants);

        this.adapteurComposant = new ComposantAdapter(this.getActivity(), listeComposants);
        this.listeViewComposant.setAdapter(this.adapteurComposant);

        //================================= PACK ====================================================
        // Temporaire : les données sont brutes. Elles seront ensuite récupérées depuis la base de
        // données
        ArrayList<Pack> listePack = new ArrayList<Pack>();
        Pack pack1 = new Pack(1,"pack1");
        Pack pack2 = new Pack(2,"pack1");
        Pack pack3 = new Pack(3,"pack1");
        Pack pack4 = new Pack(4,"pack1");

        //On ajoute la liste des packs
        listePack.add(pack1);
        listePack.add(pack2);
        listePack.add(pack3);
        listePack.add(pack4);

        // Initialisation de l'adapter pour le composant
        PackAdapter adapterPack = new PackAdapter(this.getActivity(), listePack);
        ListView listPack = (ListView) rootView.findViewById(R.id. listView_packs_composantspacks);
        listPack.setAdapter(adapterPack);


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

        /**enregistrerScenario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SectionsPagerAdapter.setScenarioFragment("ScenarioFragment");
                FragmentManager frman = getFragmentManager();
                FragmentTransaction ftran = frman.beginTransaction();
                Fragment leFrag = new ScenarioFragment();
                ftran.replace(R.id.view_pager, leFrag);
                ftran.commit();
            }
        });*/

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

        return rootView;
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
    public int getIdComposant() {
        return idComposant;
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
     * @param idComposant à modifier
     */
    public void setIdComposant(int idComposant) {
        this.idComposant = idComposant;
    }

    /**
     * Permet d'actualiser la liste affichée des composants lorsqu'un composant est ajouté, supprimé ou
     * ses informations ont été modifiées.
     */
    public void actualiserListeComposants() {
        listeComposants = new ArrayList<Composant>(controleur.getListeComposants());
        adapteurComposant = new ComposantAdapter(getActivity(), listeComposants);
        listeViewComposant.setAdapter(adapteurComposant);
    }
}
