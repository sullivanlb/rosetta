package com.example.rosetta.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.rosetta.R;
import com.example.rosetta.controller.ControleurEnregistrerNouveauComposant;

/**
 * Cette classe permet de mettre en place l'ensemble des controleurs correspondants à la vue
 * associée : l'interface de ajout_composant.
 * Elle est accessible depuis le bouton ajouter composant .
 *
 * @author Alice, Lucy
 * @version 2.0
 */

public class AjoutComposantFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_ajout_composant, container, false);

        // bouton pour enregistrer les données d'un composant (ajouter)
        Button enregistrerBouton = (Button) rootView.findViewById(R.id.ajoutComposantEnregistrerButton);
        ControleurEnregistrerNouveauComposant controleurEnregistrerNouveauComposant = new ControleurEnregistrerNouveauComposant(this);
        enregistrerBouton.setOnClickListener(controleurEnregistrerNouveauComposant);

        //Bouton pour annuler et revenir à l'interface d'avant
        Button annulerBouton = (Button) rootView.findViewById(R.id.ajoutComposantAnnulerButton);
        annulerBouton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SectionsPagerAdapter.setScenarioFragment("ComposantPackFragment");
                FragmentManager frman = getFragmentManager();
                FragmentTransaction ftran = frman.beginTransaction();
                Fragment leFrag = new ComposantPackFragment();
                ftran.replace(R.id.view_pager, leFrag);
                ftran.commit();
            }
        });
        return rootView;
    }
}
