package com.example.rosetta.controller;

import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;

import com.example.rosetta.R;
import com.example.rosetta.model.Composant;
import com.example.rosetta.ui.main.ComposantPackFragment;

/**
 * Cette classe permet d'afficher les informations du pack sélectionné dans le formulaire pour le modifie.
 *
 * @author Lucy
 * @version 2.0
 */

public class ControleurListePack implements AdapterView.OnItemClickListener{

    private ComposantPackFragment composantPackFragment;

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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        // Récupération des informations du composant sélectionné





    }
}
