package com.example.rosetta.controller;

import android.view.View;
import com.example.rosetta.ui.main.DevisFragment;

/**
 * Cette classe permet de supprimer un devis lorsque celui-ci est sélectionné.
 *
 * @author Alice
 * @version 2.0
 */
public class ControleurDevisSupprimer implements View.OnClickListener {

    private DevisFragment devisFragment;

    /**
     * Le constructeur.
     *
     * @param df le contexte {@link DevisFragment}
     */
    public ControleurDevisSupprimer(DevisFragment df) {
        this.devisFragment = df;
    }

    @Override
    public void onClick(View v) {

        //Demande la suppression
        openDialog();

    }

    /**
     * Cette méthode appelle la boite de dialogue pour supprimer un devis
     */
    public void  openDialog(){

        DialogSuppressionDevis dialogSuppressionDevis = new DialogSuppressionDevis(this.devisFragment);
        dialogSuppressionDevis.show(this.devisFragment.getActivity().getSupportFragmentManager(),"Supprimer Devis" );
    }
}
