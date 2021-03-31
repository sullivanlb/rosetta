package com.example.rosetta.controller;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.rosetta.R;
import com.example.rosetta.model.Composant;
import com.example.rosetta.ui.main.AjoutComposantFragment;
import com.example.rosetta.ui.main.ComposantPackFragment;
import com.example.rosetta.ui.main.SectionsPagerAdapter;


public class ControleurEnregistrerNouveauComposant implements View.OnClickListener {

    private AjoutComposantFragment ajoutComposantFragment;

    /**
     * Le constructeur crée une nouvelle forme de ControleurEnregistrerNouveauComposant.
     *
     * @param cf le contexte {@link AjoutComposantFragment}
     */
    public ControleurEnregistrerNouveauComposant(AjoutComposantFragment cf) {
        this.ajoutComposantFragment = cf;
    }

    @Override
    public void onClick(View v) {

        //Récupération des informations du formulaire du Composant
        EditText editNom = (EditText) this.ajoutComposantFragment.getView().findViewById(R.id.NomAjoutComposantEditText);
        EditText editUnite = (EditText) this.ajoutComposantFragment.getView().findViewById(R.id.ajoutUniteEditText);
        EditText editPrix = (EditText) this.ajoutComposantFragment.getView().findViewById(R.id.ajoutPrixEditText);

        String nom = editNom.getText().toString();
        String unite = editUnite.getText().toString();
        double prix = Double.parseDouble(editPrix.getText().toString());

        //Ajout d'un composant
        // Si le nom, le unite, le prix est bien renseigné, alors le composant est ajouté
        if (nom != null && nom.length() > 0 && unite != null && unite.length() > 0 && prix > 0) {
            Composant composant =  new Composant(0,nom, unite, prix);
            Controleur.getInstance(this.ajoutComposantFragment.getContext()).creerComposant(composant);

            //Permet de changer de framgment
            SectionsPagerAdapter.setScenarioFragment("ComposantPackFragment");
            FragmentManager frman = this.ajoutComposantFragment.getFragmentManager();
            FragmentTransaction ftran = frman.beginTransaction();
            Fragment leFrag = new ComposantPackFragment();
            ftran.replace(R.id.view_pager, leFrag);
            ftran.commit();
        }
        else {
            //Si les formulaires ne sont pas complètés
            Toast.makeText(this.ajoutComposantFragment.getView().getContext(), "Veuillez remplir le formulaire.", Toast.LENGTH_LONG).show();
        }
    }
}
