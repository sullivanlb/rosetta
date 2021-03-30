package com.example.rosetta.controller;

import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.example.rosetta.R;
import com.example.rosetta.model.Client;
import com.example.rosetta.model.Composant;
import com.example.rosetta.ui.main.AjoutPackFragment;
import com.example.rosetta.ui.main.ClientFragment;
import com.example.rosetta.ui.main.ComposantPackFragment;
import com.example.rosetta.ui.main.NouveauModifierScenarioFragment;
import com.example.rosetta.ui.main.ScenarioFragment;

import java.util.ArrayList;

/**
 * Cette classe permet d'afficher les informations du composant sélectionné dans le formulaire.
 *
 * @author Lucy
 * @version 2.0
 */

public class ControleurListeComposants implements AdapterView.OnItemClickListener  {

    private ComposantPackFragment composantPackFragment;

    /**
     * Le constructeur crée une nouvelle forme de ControleurListeComposants
     *
     * @param sf le contexte {@link ComposantPackFragment}
     */
    public ControleurListeComposants(ComposantPackFragment sf) {
        if (sf != null) {
            this.composantPackFragment = sf;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


        // Récupération des informations du composant sélectionné
        Composant composant = this.composantPackFragment.getListeComposants().get(position);
        this.composantPackFragment.setIdComposant(composant.getIdComposant());
        this.composantPackFragment.setIndiceSelectionnerComposant(position);

        //Récupération du champs du formulaire
        EditText editNom = (EditText) this.composantPackFragment.getView().findViewById(R.id.modif_nomComposantEditText);
        EditText editUnite = (EditText) this.composantPackFragment.getView().findViewById(R.id.modif_nomUniteEditText);
        EditText editPrix = (EditText) this.composantPackFragment.getView().findViewById(R.id.modif_nomPrixEditText);

        //Remplit les champs avec l'information correspondante
        editNom.setText(composant.getNomComposant());
        editUnite.setText(composant.getUniteComposant());
        editPrix.setText("" + composant.getPrixComposant());

    }
}
