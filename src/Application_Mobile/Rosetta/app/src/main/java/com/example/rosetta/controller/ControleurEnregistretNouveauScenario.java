package com.example.rosetta.controller;


import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.rosetta.R;
import com.example.rosetta.model.Question;
import com.example.rosetta.model.Scenario;
import com.example.rosetta.ui.main.NouveauScenarioFragment;
import com.example.rosetta.ui.main.ScenarioFragment;
import com.example.rosetta.ui.main.SectionsPagerAdapter;

/**
 * Cette classe permet d'ajouter un scenario et d'enregistrer les informations de celui-ci
 *
 * @author Lucy
 * @version 2.0
 */

public class ControleurEnregistretNouveauScenario implements View.OnClickListener {

    private NouveauScenarioFragment nouveauScenarioFragment;

    /**
     * Le constructeur crée une nouvelle forme de ControleurEnregistrerNouveauPack
     *
     * @param nSf le contexte {@link NouveauScenarioFragment}
     */
    public ControleurEnregistretNouveauScenario (NouveauScenarioFragment nSf) {
        this.nouveauScenarioFragment = nSf;
    }


    @Override
    public void onClick(View v) {

        // Récupération du champs du formulaire
        EditText editNom = (EditText) this.nouveauScenarioFragment.getView().findViewById(R.id.ajout_nomScenarioEditText);

        String nomScenario = editNom.getText().toString();

        if(nomScenario != null & nomScenario.length() > 0){

            // Vérification de l'Arrayliste Question != vide
            if(this.nouveauScenarioFragment.getListeQuestions().size() > 0) {

                //Vérification de l'Arrayliste pack et composant != vide
                if(this.nouveauScenarioFragment.getListeObjectPackComposant().size() > 0){

                    //Ajout d'un Scenario
                    Scenario scenario = new Scenario(0, nomScenario);
                    Controleur.getInstance(this.nouveauScenarioFragment.getContext()).creerScenario(scenario);

                    //Ajout des questions dans la table Questions
                    for(int i = 0; i < this.nouveauScenarioFragment.getListeQuestions().size(); i++){
                        Question question = this.nouveauScenarioFragment.getListeQuestions().get(i);
                        Controleur.getInstance(this.nouveauScenarioFragment.getContext()).creerQuestion(question);
                    }

                    // Ajout aux tables AppartientSP(scenario, pack, quantite), AppartientSC(scenario, composant, quantite) et AppartientSQ(scenario, question)



                    //Permet de changer de framgment
                    SectionsPagerAdapter.setScenarioFragment("ScenarioFragment");
                    FragmentManager frman = this.nouveauScenarioFragment.getFragmentManager();
                    FragmentTransaction ftran = frman.beginTransaction();
                    Fragment leFrag = new ScenarioFragment();
                    ftran.replace(R.id.view_pager, leFrag);
                    ftran.commit();
                }
                else{
                    Toast.makeText(this.nouveauScenarioFragment.getView().getContext(), "Veuillez ajouter des packs ou/et des composants.", Toast.LENGTH_LONG).show();
                }
            }
            else{
                Toast.makeText(this.nouveauScenarioFragment.getView().getContext(), "Veuillez ajouter des questions.", Toast.LENGTH_LONG).show();
            }
        }
        else{
            Toast.makeText(this.nouveauScenarioFragment.getView().getContext(), "Veuillez remplir le formulaire.", Toast.LENGTH_LONG).show();
        }
    }
}
