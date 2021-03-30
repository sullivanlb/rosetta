package com.example.rosetta.controller;

import android.view.View;
import android.widget.EditText;

import com.example.rosetta.R;
import com.example.rosetta.model.Client;
import com.example.rosetta.model.Composant;
import com.example.rosetta.ui.main.ComposantPackFragment;

import java.util.ArrayList;

/**
 * Cette classe permet d'enregistrer les modifications apportées sur un composant ou un pack.
 *
 * @author Lucy
 * @version 2.0
 */

public class ControleurEnregistrerModificationComposantPack implements View.OnClickListener {

    private ComposantPackFragment composantPackFragment;

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

        //Récupération du champs du formulaire
        EditText editNom = (EditText) this.composantPackFragment.getView().findViewById(R.id.modif_nomComposantEditText);
        EditText editUnite = (EditText) this.composantPackFragment.getView().findViewById(R.id.modif_nomUniteEditText);
        EditText editPrix = (EditText) this.composantPackFragment.getView().findViewById(R.id.modif_nomPrixEditText);

        String nomComposant = editNom.getText().toString();
        String uniteComposant = editUnite.getText().toString();
        double prixComposant = Double.parseDouble(editPrix.getText().toString());

        ArrayList<Composant> listComposant = Controleur.getInstance(this.composantPackFragment.getContext()).getListeComposants();

        if(this.composantPackFragment.getIndiceSelectionnerComposant() >=0){

            Composant comp = listComposant.get(this.composantPackFragment.getIndiceSelectionnerComposant());
            comp.setNomComposant(nomComposant);
            comp.setUniteComposant(uniteComposant);
            comp.setPrixComposant(prixComposant);

            Controleur.getInstance(this.composantPackFragment.getContext()).modifierComposant(comp);
            this.composantPackFragment.actualiserListeComposants();

        }

    }
}
