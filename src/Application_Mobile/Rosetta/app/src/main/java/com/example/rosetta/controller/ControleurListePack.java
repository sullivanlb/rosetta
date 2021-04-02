package com.example.rosetta.controller;

import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;

import com.example.rosetta.R;
import com.example.rosetta.model.Composant;
import com.example.rosetta.model.Pack;
import com.example.rosetta.ui.main.ComposantPackFragment;
import com.example.rosetta.ui.main.NouveauDevisFragment;

import java.util.ArrayList;

/**
 * Cette classe permet d'afficher les informations du pack sélectionné dans le formulaire pour le modifie.
 *
 * @author Lucy, Alice 
 * @version 2.0
 */

public class ControleurListePack implements AdapterView.OnItemClickListener{

    private ComposantPackFragment composantPackFragment;
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
     * @param nDf le contexte {@link NouveauDevisFragment}
     */

    public ControleurListePack(NouveauDevisFragment nDf) {
        if(nDf != null){
            this.nouveauDevisFragment= nDf;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // === Affichage du nom du pack sélectionné

        // Récupération des informations du pack sélectionné
        Pack pack = this.composantPackFragment.getListePacks().get(position);
        this.composantPackFragment.setIdSelectionnnerPack(pack.getIdPack());
        this.composantPackFragment.setIndiceSelectionnerPack(position);

        // Récupération du champs du formulaire
        EditText editNom = (EditText) this.composantPackFragment.getView().findViewById(R.id.modif_nomPackEditText);

        // Remplit le champs avec l'information correspondante
        editNom.setText(pack.getNomPack());

        // === Affichage de la liste de ses composants

        // Nettoyage des listes du pack précédent
        this.composantPackFragment.getHashmapIdComposantQuantite().clear();
        this.composantPackFragment.getListeComposantDePack().clear();

        // Ajout des composants de ce pack dans les listes
        ArrayList<Integer> composantsPC = Controleur.getInstance(this.composantPackFragment.getContext()).getTousLesElementsPC_Composants();
        ArrayList<Integer> packsPC = Controleur.getInstance(this.composantPackFragment.getContext()).getTousLesElementsPC_Packs();

        for(int i = 0; i < packsPC.size(); i++) {
            Integer idPackPC = packsPC.get(i);

            if (idPackPC.intValue() == pack.getIdPack()) {
                this.composantPackFragment.getHashmapIdComposantQuantite().put(idPackPC, composantsPC.get(i));

                ArrayList<Composant> tousLesComposants = Controleur.getInstance(this.composantPackFragment.getContext()).getListeComposants();
                for (Composant composant : tousLesComposants) {
                    if (composant.getIdComposant() == composantsPC.get(i).intValue()) {
                        this.composantPackFragment.getListeComposantDePack().add(composant);
                    }
                }
            }
        }

        this.composantPackFragment.actualiserListeComposantDePack();
    }
}
