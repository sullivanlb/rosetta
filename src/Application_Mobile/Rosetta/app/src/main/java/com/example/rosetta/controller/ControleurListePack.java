package com.example.rosetta.controller;

import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;

import com.example.rosetta.R;
import com.example.rosetta.model.Composant;
import com.example.rosetta.ui.main.ComposantPackFragment;
import com.example.rosetta.ui.main.NouveauDevisFragment;
import com.example.rosetta.ui.main.NouveauModifierScenarioFragment;

/**
 * Cette classe permet d'afficher les informations du pack sélectionné dans le formulaire pour le modifie.
 *
 * @author Lucy
 * @version 2.0
 */

public class ControleurListePack implements AdapterView.OnItemClickListener{

    private ComposantPackFragment composantPackFragment;
    private  NouveauModifierScenarioFragment nouveauModifierScenarioFragment;
    private NouveauDevisFragment nouveauDevisFragment;

    /**
     * Le constructeur crée une nouvelle forme de ControleurListePack
     *
     * @param sf le contexte {@link ComposantPackFragment}
     */
    public ControleurListePack(ComposantPackFragment sf) {
        if (sf != null) {
            this.composantPackFragment = sf;
        }
    }

    /**
     * Le constructeur crée une nouvelle forme de ControleurListeComposants
     *
     * @param nmSf le contexte {@link NouveauModifierScenarioFragment}
     */

    public ControleurListePack(NouveauModifierScenarioFragment nmSf) {
        if(nmSf != null){
            this.nouveauModifierScenarioFragment = nmSf;
        }
    }

    /**
     * Le constructeur crée une nouvelle forme de ControleurListeComposants
     *
     * @param nDf le contexte {@link NouveauDevisFragment}
     */

    public ControleurListePack(NouveauDevisFragment nDf) {
        if(nDf != null){
            this.nouveauDevisFragment= nDf;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        // Récupération des informations du composant sélectionné





    }
}
