package com.example.rosetta.controller;

import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.rosetta.R;
import com.example.rosetta.model.Client;
import com.example.rosetta.model.Composant;
import com.example.rosetta.ui.main.AjoutPackFragment;
import com.example.rosetta.ui.main.ClientFragment;
import com.example.rosetta.ui.main.ComposantPackFragment;

import com.example.rosetta.ui.main.ScenarioFragment;

import java.util.ArrayList;

/**
 * Cette classe permet d'afficher les informations du composant sélectionné dans le formulaire.
 *
 * @author Lucy, Alice 
 * @version 2.0
 */

public class ControleurListeComposantsComposantPack implements AdapterView.OnItemClickListener  {

    private ComposantPackFragment composantPackFragment;

    /**
     * Le constructeur crée une nouvelle forme de ControleurListeComposantsComposantPack
     *
     * @param sf le contexte {@link ComposantPackFragment}
     */
    public ControleurListeComposantsComposantPack(ComposantPackFragment sf) {
        if (sf != null) {
            this.composantPackFragment = sf;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        CheckBox checkPack = (CheckBox) this.composantPackFragment.getView().findViewById(R.id.checkBoxPack);
        EditText editNomPack = (EditText) this.composantPackFragment.getView().findViewById(R.id.modif_nomPackEditText);
        boolean unPackEstSelectionne = editNomPack.getText().toString().length() > 0;

        if (checkPack.isChecked()) {
            // Modification du pack

            // Si un pack est sélectionné
            if (unPackEstSelectionne) {
                // Vider les informations du composant en-haut
                EditText editNom = (EditText) this.composantPackFragment.getView().findViewById(R.id.modif_nomComposantEditText);
                EditText editUnite = (EditText) this.composantPackFragment.getView().findViewById(R.id.modif_nomUniteEditText);
                EditText editPrix = (EditText) this.composantPackFragment.getView().findViewById(R.id.modif_nomPrixEditText);
                editNom.setText("");
                editUnite.setText("");
                editPrix.setText("");

                // Vérification de la saisie de la quantité
                EditText editQuantite = (EditText) this.composantPackFragment.getView().findViewById(R.id.modif_quantitePackEditText);
                int quantite = editQuantite.getText().toString().length() > 0 ? Integer.parseInt(editQuantite.getText().toString()) : -1;

                boolean estDejaAjoute = false;
                int idComposant = this.composantPackFragment.getListeComposants().get(position).getIdComposant();

                for (Composant composant : this.composantPackFragment.getListeComposantDePack()) {
                    if (composant.getIdComposant() == idComposant) {
                        estDejaAjoute = true;
                    }
                }

                // Vérifie si on ajoute le composant au pack, ou si on l'enlève
                if (!estDejaAjoute) {
                    // Ajoute le composant dans la liste

                    // Vérification de la quantité
                    if (quantite > 0) {
                        String nom = this.composantPackFragment.getListeComposants().get(position).getNomComposant();
                        int identifiant = this.composantPackFragment.getListeComposants().get(position).getIdComposant();
                        String unite = this.composantPackFragment.getListeComposants().get(position).getUniteComposant();
                        double prix = this.composantPackFragment.getListeComposants().get(position).getPrixComposant();

                        Composant composant = new Composant(identifiant, nom, unite, prix);

                        this.composantPackFragment.getListeComposantDePack().add(composant);
                        this.composantPackFragment.getHashmapIdComposantQuantite().put(identifiant, quantite);
                        this.composantPackFragment.actualiserListeComposantDePack();
                    } else {
                        Toast.makeText(this.composantPackFragment.getContext(), "Veuillez saisir la quantité avant.", Toast.LENGTH_LONG).show();
                    }
                } else {
                    // Enlever le composant de la liste

                    int idDansLaListe = 0;
                    for (int i = 0; i < this.composantPackFragment.getListeComposantDePack().size(); i++) {
                        if (idComposant == this.composantPackFragment.getListeComposantDePack().get(i).getIdComposant()) {
                            idDansLaListe = i;
                        }
                    }

                    this.composantPackFragment.getListeComposantDePack().remove(idDansLaListe);
                    this.composantPackFragment.getHashmapIdComposantQuantite().remove(idComposant);
                    this.composantPackFragment.actualiserListeComposantDePack();
                }
            } else {
                Toast.makeText(this.composantPackFragment.getContext(), "Veuillez sélectionner un pack avant.", Toast.LENGTH_LONG).show();
            }
        } else {
            // Affichage des informations du composant sélectionné

            // Récupération des informations du composant sélectionné
            Composant composant = this.composantPackFragment.getListeComposants().get(position);
            this.composantPackFragment.setIdSelectionnnerComposant(composant.getIdComposant());
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
}
