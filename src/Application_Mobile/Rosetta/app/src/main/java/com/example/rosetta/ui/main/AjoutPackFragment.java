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
import com.example.rosetta.controller.ControleurEnregistrerNouveauComposant;
import com.example.rosetta.controller.ControleurEnregistrerNouveauPack;
import com.example.rosetta.controller.ControleurListeComposantDePack;
import com.example.rosetta.controller.ControleurListeComposants;
import com.example.rosetta.model.Composant;

import java.util.ArrayList;

/**
 * Cette classe permet de mettre en place l'ensemble des controleurs correspondants à la vue
 * associée : l'interface de ajout_pack.
 * Elle est accessible depuis le bouton ajout pack .
 *
 * @author Alice
 * @version 2.0
 */

public class AjoutPackFragment extends Fragment {

    private ArrayList<Composant> listeComposants;
    private  ArrayList<Composant> listeComposantDePack = new ArrayList<Composant>();
    private Controleur controleur;
    private ComposantAdapter adapteurComposant;
    private ListView listeViewComposant;
    ListView listeComposantsPack;
    private int indiceSelectionnerComposant;
    private int idComposant;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_ajout_pack, container, false);

        // Le controleur principal (accès à la base de données interne)
        this.controleur = Controleur.getInstance(this.getContext());

        // La liste des composants récupérée depuis la base de données interne
        this.listeComposants = new ArrayList<Composant>(this.controleur.getListeComposants());

        //Initialisation de la liste des Composants
        this.listeViewComposant = (ListView) rootView.findViewById(R.id.listView_composants_ajoutPack);
        ControleurListeComposantDePack controleurListeComposantDePack = new ControleurListeComposantDePack(this);
        this.listeViewComposant.setOnItemClickListener(controleurListeComposantDePack);
        this.adapteurComposant = new ComposantAdapter(this.getActivity(), listeComposants);
        this.listeViewComposant.setAdapter(this.adapteurComposant);

        //Initialisation de la liste des Composants qui composent un Pack
        this.listeComposantsPack = (ListView) rootView.findViewById(R.id.listView_packDeComposant);
        ComposantAdapter adapteurComposant2 = new ComposantAdapter(this.getActivity(), listeComposantDePack);
        listeComposantsPack.setAdapter(adapteurComposant2);

        //Bouton pour enregistrer un nouveau pack
        Button enregistrer = (Button) rootView.findViewById(R.id.ajoutPackEnregistrerButton);
        ControleurEnregistrerNouveauPack controleurEnregistrerNouveauPack = new ControleurEnregistrerNouveauPack(this);
        enregistrer.setOnClickListener(controleurEnregistrerNouveauPack);

        //Bouton Annuler pour revenir sur l'interface prècèdent
        Button annulerButton = (Button) rootView.findViewById(R.id.ajoutPackAnnulerButton);
        annulerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SectionsPagerAdapter.setScenarioFragment("ComposantPackFragment");
                FragmentManager frman = getFragmentManager();
                FragmentTransaction ftran = frman.beginTransaction();
                Fragment leFrag = new ComposantPackFragment();
                ftran.replace(R.id.view_pager, leFrag);
                ftran.commit();
            }
        });

        return rootView;
    }

    /**
     * Getter
     * @return la liste des composants qui composent ce pack.
     */

    public ArrayList<Composant> getListeComposantDePack() {
        return listeComposantDePack;
    }

    /**
     * Getter
     * @return la liste des composants
     */
    public ArrayList<Composant> getListeComposants() {
        return listeComposants;
    }

    /**
     * Permet d'actualiser la liste affichée des composants qui composent un pack lorsqu'un composant est ajouté.
     */
    public void actualiserListeComposantDePack() {
        adapteurComposant = new ComposantAdapter(getActivity(), listeComposantDePack);
        this.listeComposantsPack.setAdapter(adapteurComposant);
    }

    /**
     * Permet de vider l'ArrayList
     */

    public void viderList(){
        this.listeComposantDePack = new ArrayList<Composant>();
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
}
