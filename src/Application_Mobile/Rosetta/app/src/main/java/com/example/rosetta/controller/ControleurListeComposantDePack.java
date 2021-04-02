package com.example.rosetta.controller;

import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rosetta.R;
import com.example.rosetta.model.Composant;
import com.example.rosetta.ui.main.AjoutPackFragment;

/**
 * Cette classe permet de gérer tout ce qui se déroule dans la listView d'ajout de composant dans un pack
 *
 * @version 2.0
 */

public class ControleurListeComposantDePack implements AdapterView.OnItemClickListener {

    private AjoutPackFragment ajoutPackFragment;

    public ControleurListeComposantDePack(AjoutPackFragment apf) {
        if (apf != null) {
            this.ajoutPackFragment = apf;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        // Vérification de la saisie de la quantité
        EditText editQuantite = (EditText) this.ajoutPackFragment.getView().findViewById(R.id.NomQuantiteEditText);
        int quantite = editQuantite.getText().toString().length() > 0 ? Integer.parseInt(editQuantite.getText().toString()) : -1;

        boolean estDejaAjoute = false;
        int idComposant = this.ajoutPackFragment.getListeComposants().get(position).getIdComposant();

        for (Composant composant : this.ajoutPackFragment.getListeComposantDePack()) {
            if (composant.getIdComposant() == idComposant) {
                estDejaAjoute = true;
            }
        }

        // Vérifie si on ajoute le composant au pack, ou si on l'enlève
        if (!estDejaAjoute) {
            // Ajoute le composant dans la liste

            // Vérification de la quantité
            if (quantite > 0) {
                String nom = this.ajoutPackFragment.getListeComposants().get(position).getNomComposant();
                int identifiant = this.ajoutPackFragment.getListeComposants().get(position).getIdComposant();
                String unite = this.ajoutPackFragment.getListeComposants().get(position).getUniteComposant();
                double prix = this.ajoutPackFragment.getListeComposants().get(position).getPrixComposant();

                Composant composant = new Composant(identifiant, nom, unite, prix);

                this.ajoutPackFragment.getListeComposantDePack().add(composant);
                this.ajoutPackFragment.getHashmapIdComposantQuantite().put(identifiant, quantite);
                this.ajoutPackFragment.actualiserListeComposantDePack();

                // Vider le champ de la quantité
                editQuantite.setText("");
            } else {
                Toast.makeText(this.ajoutPackFragment.getContext(), "Veuillez saisir la quantité avant.", Toast.LENGTH_LONG).show();
            }
        } else {
            // Enlever le composant de la liste

            int idDansLaListe = 0;
            for (int i = 0; i < this.ajoutPackFragment.getListeComposantDePack().size(); i++) {
                if (idComposant == this.ajoutPackFragment.getListeComposantDePack().get(i).getIdComposant()) {
                    idDansLaListe = i;
                }
            }

            this.ajoutPackFragment.getListeComposantDePack().remove(idDansLaListe);
            this.ajoutPackFragment.getHashmapIdComposantQuantite().remove(idComposant);
            this.ajoutPackFragment.actualiserListeComposantDePack();
        }
    }
}


