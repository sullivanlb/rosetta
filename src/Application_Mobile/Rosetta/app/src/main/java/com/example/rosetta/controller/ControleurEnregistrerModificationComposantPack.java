package com.example.rosetta.controller;

import android.service.controls.Control;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.rosetta.R;
import com.example.rosetta.model.Client;
import com.example.rosetta.model.Composant;
import com.example.rosetta.model.Pack;
import com.example.rosetta.ui.main.ComposantPackFragment;
import com.example.rosetta.ui.main.SectionsPagerAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Cette classe permet d'enregistrer les modifications apportées sur un composant ou un pack.
 *
 * @author Lucy, Alice
 * @version 2.0
 */

public class ControleurEnregistrerModificationComposantPack implements View.OnClickListener {

    private ComposantPackFragment composantPackFragment;
    private ArrayList<Composant> listeComposantDePack;

    /**
     * Le constructeur crée une nouvelle forme de ControleurListeComposants
     *
     * @param sf le contexte {@link ComposantPackFragment}
     */
    public ControleurEnregistrerModificationComposantPack(ComposantPackFragment sf) {
        if (sf != null) {
            this.composantPackFragment = sf;
        }
    }

    @Override
    public void onClick(View v) {

        CheckBox checkPack = (CheckBox) this.composantPackFragment.getView().findViewById(R.id.checkBoxPack);

        if (checkPack.isChecked()){

            //Suppression des liaisons pack composant
            HashMap<Integer,Integer> duoCompQuantite = new HashMap<Integer,Integer>();
            for (int i = 0; i <  Controleur.getInstance(this.composantPackFragment.getContext()).getTousLesElementsPC_Packs().size(); i++){
                if (Controleur.getInstance(this.composantPackFragment.getContext()).getTousLesElementsPC_Packs().get(i).intValue() == this.composantPackFragment.getIdSelectionnnerPack()){
                    Integer comp = Controleur.getInstance(this.composantPackFragment.getContext()).getTousLesElementsPC_Composants().get(i);
                    Integer quantite = Controleur.getInstance(this.composantPackFragment.getContext()).getTousLesElementsPC_Quantite().get(i);
                    duoCompQuantite.put(comp,quantite);
                }
            }
            Controleur.getInstance(this.composantPackFragment.getContext()).supprimerPackPC(this.composantPackFragment.getIdSelectionnnerPack());

            this.composantPackFragment.actualiserListeComposantDePack();

            //Création du pack modifié
            //Récupération des informations du formulaire du Composant
            EditText editNom = (EditText) this.composantPackFragment.getView().findViewById(R.id.modif_nomPackEditText);
            String nom = editNom.getText().toString();

            listeComposantDePack = this.composantPackFragment.getListeComposantDePack();

            EditText editquant = (EditText) this.composantPackFragment.getView().findViewById(R.id.modif_quantitePackEditText);

            if (nom != null && nom.length() > 0 && listeComposantDePack.size() > 0) {
                for(int i = 0; i < this.listeComposantDePack.size(); i++) {
                    //création des liaisons packs composants
                    Composant composant = listeComposantDePack.get(i);
                    if (!duoCompQuantite.containsKey(composant.getIdComposant())){
                        int qt = Integer.parseInt(editquant.getText().toString());
                        duoCompQuantite.put(composant.getIdComposant(), qt);
                    }
                    int quantite = duoCompQuantite.get(composant.getIdComposant());
                    Controleur.getInstance(this.composantPackFragment.getContext())
                            .creerAppartientPC(this.composantPackFragment.getListePacks().get(this.composantPackFragment.getIndiceSelectionnerPack()), composant, quantite);
                }

                // Vider le champ de la quantite
                editquant.setText("");

                this.composantPackFragment.actualiserListeComposantDePack();
                Toast.makeText(this.composantPackFragment.getView().getContext(), "Enregistré", Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(this.composantPackFragment.getView().getContext(), "Veuillez remplir le formulaire", Toast.LENGTH_LONG).show();
            }

        }
        else{
            //Récupération du champs du formulaire
            EditText editNom = (EditText) this.composantPackFragment.getView().findViewById(R.id.modif_nomComposantEditText);
            EditText editUnite = (EditText) this.composantPackFragment.getView().findViewById(R.id.modif_nomUniteEditText);
            EditText editPrix = (EditText) this.composantPackFragment.getView().findViewById(R.id.modif_nomPrixEditText);

            String nomComposant = editNom.getText().toString();
            String uniteComposant = editUnite.getText().toString();
            String prix = editPrix.getText().toString();

            // Enregirstre le nouveau composant créé
            if(nomComposant != null && nomComposant.length() > 0 && nomComposant != null && nomComposant.length() > 0 && prix != null && prix.length() > 0) {
                double prixComposant = Double.parseDouble(editPrix.getText().toString());

                ArrayList<Composant> listComposant = Controleur.getInstance(this.composantPackFragment.getContext()).getListeComposants();

                if (this.composantPackFragment.getIndiceSelectionnerComposant() >= 0) {

                    Composant composant = null;
                    for (int i = 0; i < listComposant.size(); i++) {
                        if (listComposant.get(i).getIdComposant() == this.composantPackFragment.getIdSelectionnnerComposant()) {
                            composant = listComposant.get(i);
                        }
                    }

                    if (composant != null) {
                        composant.setNomComposant(nomComposant);
                        composant.setUniteComposant(uniteComposant);
                        composant.setPrixComposant(prixComposant);
                        Controleur.getInstance(this.composantPackFragment.getContext()).modifierComposant(composant);
                        this.composantPackFragment.actualiserListeComposants();
                    }
                }
            }
        }
    }
}
