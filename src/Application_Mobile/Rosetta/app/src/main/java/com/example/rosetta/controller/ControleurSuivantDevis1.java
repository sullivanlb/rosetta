package com.example.rosetta.controller;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.rosetta.R;
import com.example.rosetta.ui.main.DevisQuestionFragment;
import com.example.rosetta.ui.main.NouveauDevisClientScenarioFragment;
import com.example.rosetta.ui.main.SectionsPagerAdapter;

public class ControleurSuivantDevis1 implements View.OnClickListener {

    private NouveauDevisClientScenarioFragment nouveauDevisClientScenarioFragment;

    /**
     * Le constructeur crée une nouvelle forme de ControleurListeClients
     *
     * @param nDcS le contexte {@link NouveauDevisClientScenarioFragment }
     */
    public ControleurSuivantDevis1(NouveauDevisClientScenarioFragment nDcS ) {
        if (nDcS  != null) {
            this.nouveauDevisClientScenarioFragment = nDcS ;
        }
    }

    @Override
    public void onClick(View v) {

        //Récupération des informations du formulaire du devis
        EditText editNom = (EditText) this.nouveauDevisClientScenarioFragment.getView().findViewById(R.id.choix_clientEditText);
        String nomClient = editNom.getText().toString();

        //Vérification du client
        if(nomClient != null && nomClient.length() > 0){

            //Vérification d'au moins un scénario
            if(this.nouveauDevisClientScenarioFragment.getListScenarioTemporaire().size() > 0){

                //Comment récupérer dans la base de donnée ? 


                //Permet de changer de fragment
                SectionsPagerAdapter.setDevisFragment("DevisQuestionFragment");
                FragmentManager frman = this.nouveauDevisClientScenarioFragment.getFragmentManager();
                FragmentTransaction ftran = frman.beginTransaction();
                Fragment leFrag = new DevisQuestionFragment();
                ftran.replace(R.id.view_pager, leFrag);
                ftran.commit();

            }
            else{
                Toast.makeText(this.nouveauDevisClientScenarioFragment.getView().getContext(), "Veuillez choisir au moins un Scénario.", Toast.LENGTH_LONG).show();
            }
        }
        else{
            Toast.makeText(this.nouveauDevisClientScenarioFragment.getView().getContext(), "Veuillez choisir un Client.", Toast.LENGTH_LONG).show();
        }
    }
}
