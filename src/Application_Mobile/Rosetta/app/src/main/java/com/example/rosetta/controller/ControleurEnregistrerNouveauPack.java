package com.example.rosetta.controller;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.rosetta.R;
import com.example.rosetta.model.Composant;
import com.example.rosetta.model.Pack;
import com.example.rosetta.ui.main.AjoutPackFragment;
import com.example.rosetta.ui.main.ClientFragment;
import com.example.rosetta.ui.main.ComposantPackFragment;
import com.example.rosetta.ui.main.SectionsPagerAdapter;

import java.util.ArrayList;

/**
 * Cette classe permet d'ajouter un pack et d'enregistrer les informations de celui-ci
 *
 * @author Lucy
 * @version 2.0
 */

public class ControleurEnregistrerNouveauPack implements View.OnClickListener {

    private AjoutPackFragment ajoutPackFragment;
    private ArrayList<Composant> listeComposantDePack;

    /**
     * Le constructeur crée une nouvelle forme de ControleurEnregistrerNouveauPack
     *
     * @param apf le contexte {@link AjoutPackFragment}
     */
    public ControleurEnregistrerNouveauPack(AjoutPackFragment apf) {
        this.ajoutPackFragment = apf;
    }

    @Override
    public void onClick(View v) {

        //Récupération des informations du formulaire du Composant
        EditText editNom = (EditText) this.ajoutPackFragment.getView().findViewById(R.id.NomAjoutPackEditText);
        String nom = editNom.getText().toString();

        listeComposantDePack = this.ajoutPackFragment.getListeComposantDePack();


        if (nom != null && nom.length() > 0 && listeComposantDePack.size() > 0) {
            Pack pack = new Pack(0, nom);
            Controleur.getInstance(this.ajoutPackFragment.getContext()).creerPack(pack);

            for(int i = 0; i < this.listeComposantDePack.size(); i++){

                Composant composant = listeComposantDePack.get(i);
                Controleur.getInstance(this.ajoutPackFragment.getContext()).creerAppartientPC(pack, composant);

            }

            //Permet de changer de framgment
            SectionsPagerAdapter.setScenarioFragment("ComposantPackFragment");
            FragmentManager frman = this.ajoutPackFragment.getFragmentManager();
            FragmentTransaction ftran = frman.beginTransaction();
            Fragment leFrag = new ComposantPackFragment();
            ftran.replace(R.id.view_pager, leFrag);
            ftran.commit();

            for(int i = 0; i < this.listeComposantDePack.size(); i ++){
                this.listeComposantDePack.remove(i);
            }
            System.out.println("Taille arrayliste " + listeComposantDePack.size());

        }
        else {
            Toast.makeText(this.ajoutPackFragment.getView().getContext(), "Veuillez remplir le formulaire.", Toast.LENGTH_LONG).show();
        }

    }

    public void quantiteComposant(ArrayList<Composant> list){ }
}


