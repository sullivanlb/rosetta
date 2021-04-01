package com.example.rosetta.controller;

import android.view.View;
import android.widget.AdapterView;

import com.example.rosetta.ui.main.NouveauScenarioFragment;

/**
 * Cette classe permet d'afficher les informations des objects sélectionnés dans le formulaire.
 *
 * @author Lucy
 * @version 2.0
 */

public class ControleurListeObject implements AdapterView.OnItemClickListener {

    private NouveauScenarioFragment nouveauScenarioFragment;

    /**
     * Le constructeur crée une nouvelle forme de ControleurListeComposants
     *
     * @param nSf le contexte {@link NouveauScenarioFragment}
     */

    public  ControleurListeObject(NouveauScenarioFragment nSf) {
        if(nSf != null){
            this.nouveauScenarioFragment = nSf;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



    }
}
