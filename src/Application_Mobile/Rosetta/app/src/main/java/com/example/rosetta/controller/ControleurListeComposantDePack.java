package com.example.rosetta.controller;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.rosetta.model.Composant;
import com.example.rosetta.ui.main.AjoutPackFragment;

public class ControleurListeComposantDePack implements AdapterView.OnItemClickListener {

    private AjoutPackFragment ajoutPackFragment;

    public ControleurListeComposantDePack(AjoutPackFragment apf) {
        if (apf != null) {
            this.ajoutPackFragment = apf;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        String nom = this.ajoutPackFragment.getListeComposants().get(position).getNomComposant();
        int identifiant = this.ajoutPackFragment.getListeComposants().get(position).getIdComposant();
        String unite = this.ajoutPackFragment.getListeComposants().get(position).getUniteComposant();
        double prix = this.ajoutPackFragment.getListeComposants().get(position).getPrixComposant();

        Composant composant = new Composant(identifiant, nom, unite, prix);

        this.ajoutPackFragment.getListeComposantDePack().add(composant);

        this.ajoutPackFragment.actualiserListeComposantDePack();

    }
}


