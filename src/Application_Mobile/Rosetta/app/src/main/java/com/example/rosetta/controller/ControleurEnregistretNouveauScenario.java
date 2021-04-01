package com.example.rosetta.controller;


import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.rosetta.R;
import com.example.rosetta.model.Composant;
import com.example.rosetta.model.Pack;
import com.example.rosetta.model.Question;
import com.example.rosetta.model.Scenario;
import com.example.rosetta.ui.main.NouveauScenarioFragment;
import com.example.rosetta.ui.main.ScenarioFragment;
import com.example.rosetta.ui.main.SectionsPagerAdapter;

import java.util.ArrayList;
import java.util.HashMap;

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
            if(this.nouveauScenarioFragment.getListeQuestionTemporaire().size() > 0) {

                //Vérification de l'Arrayliste pack et composant != vide
                if(this.nouveauScenarioFragment.getListeObjectPackComposant().size() > 0){


                    //Ajout d'un Scenario
                    Scenario scenario = new Scenario(0, nomScenario);
                    Controleur.getInstance(this.nouveauScenarioFragment.getContext()).creerScenario(scenario);

                    // Ajout aux tables AppartientSC(scenario, composant, quantite) et  AppartientSP(scenario, pack, quantite)
                    ArrayList<Object> listObject = this.nouveauScenarioFragment.getListeObjectPackComposant();

                    for(int i = 0; i < listObject.size(); i++){

                        if(listObject.get(i).getClass().getName().equalsIgnoreCase("com.example.rosetta.model.Pack")){
                            Pack pack = (Pack) listObject.get(i);
                            int quantite = this.nouveauScenarioFragment.getHashmapIdPackQuantite().get(pack.getIdPack());

                            Controleur.getInstance(this.nouveauScenarioFragment.getContext())
                                    .creerAppartientSP(scenario, pack, quantite);

                        }
                        else if(listObject.get(i).getClass().getName().equalsIgnoreCase("com.example.rosetta.model.Composant")){
                            Composant composant = (Composant) listObject.get(i);
                            int quantite = this.nouveauScenarioFragment.getHashmapIdComposantQuantite().get(composant.getIdComposant());

                            Controleur.getInstance(this.nouveauScenarioFragment.getContext())
                                    .creerAppartientSC(scenario, composant, quantite);

                        }
                    }

                    //Ajout des questions dans la table Questions
                    // Ajout à la table AppartientSQ(scenario, question)
                    for(int i = 0; i < this.nouveauScenarioFragment.getListeQuestionTemporaire().size(); i++){
                        Question question = this.nouveauScenarioFragment.getListeQuestionTemporaire().get(i);
                        this.nouveauScenarioFragment.getListeQuestions().add(question);
                        Controleur.getInstance(this.nouveauScenarioFragment.getContext()).creerQuestion(question);
                        Controleur.getInstance(this.nouveauScenarioFragment.getContext()).creerAppartientSQ(scenario, question);
                    }


                    //Permet de changer de framgment
                    SectionsPagerAdapter.setScenarioFragment("ScenarioFragment");
                    FragmentManager frman = this.nouveauScenarioFragment.getFragmentManager();
                    FragmentTransaction ftran = frman.beginTransaction();
                    Fragment leFrag = new ScenarioFragment();
                    ftran.replace(R.id.view_pager, leFrag);
                    ftran.commit();

                    //Vider les Arrayliste et les HashMap
                    this.nouveauScenarioFragment.getListeObjectPackComposant().clear();
                    this.nouveauScenarioFragment.getListeQuestionTemporaire().clear();
                    this.nouveauScenarioFragment.getHashmapIdComposantQuantite().clear();
                    this.nouveauScenarioFragment.getHashmapIdPackQuantite().clear();

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
