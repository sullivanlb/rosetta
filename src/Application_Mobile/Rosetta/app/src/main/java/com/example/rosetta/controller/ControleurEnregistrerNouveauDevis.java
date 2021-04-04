package com.example.rosetta.controller;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.rosetta.R;
import com.example.rosetta.ui.main.AjoutComposantFragment;
import com.example.rosetta.ui.main.DevisFragment;
import com.example.rosetta.ui.main.NouveauDevisFragment;
import com.example.rosetta.ui.main.SectionsPagerAdapter;

/**
 * Cette classe permet d'ajouter un devis et d'enregistrer les informations de celui-ci.
 *
 * @version 2.0
 */

public class ControleurEnregistrerNouveauDevis implements View.OnClickListener {

    private NouveauDevisFragment nouveauDevisFragment;

    /**
     * Le constructeur crée une nouvelle forme de ControleurEnregistrerNouveauComposant.
     *
     * @param nDF le contexte {@link NouveauDevisFragment}
     */
    public ControleurEnregistrerNouveauDevis(NouveauDevisFragment nDF) {
        this.nouveauDevisFragment = nDF;
    }


    @Override
    public void onClick(View v) {

        //Récupération des informations du formulaire du Composant
        EditText editNom = (EditText) this.nouveauDevisFragment.getView().findViewById(R.id.ajout_nomDevisEditText);
        String nomDevis = editNom.getText().toString();

        if(nomDevis != null && nomDevis.length() > 0){

            if(this.nouveauDevisFragment.getListObject().size() > 0){

                //Récupèrer le Client

                //Ajouter dans la BDD : AppartientCD, AppartientDC, AppartientDP, Devis




                //Permet de changer de framgment
                SectionsPagerAdapter.setDevisFragment("DevisFragment");
                FragmentManager frman = this.nouveauDevisFragment.getFragmentManager();
                FragmentTransaction ftran = frman.beginTransaction();
                Fragment leFrag = new DevisFragment();
                ftran.replace(R.id.view_pager, leFrag);
                ftran.commit();
            }
            else{
                Toast.makeText(this.nouveauDevisFragment.getView().getContext(), "Veuillez saisir au moins un composant/pack ", Toast.LENGTH_LONG).show();
            }
        }
        else{
            Toast.makeText(this.nouveauDevisFragment.getView().getContext(), "Veuillez saisir le nom du devis ", Toast.LENGTH_LONG).show();
        }

    }
}
