package com.example.rosetta.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.rosetta.MainActivity;
import com.example.rosetta.R;
import com.example.rosetta.controller.Controleur;
import com.example.rosetta.controller.ObjectAdapter;
import com.example.rosetta.controller.QuestionAdapter2;
import com.example.rosetta.model.Composant;
import com.example.rosetta.model.Pack;
import com.example.rosetta.model.Question;
import com.example.rosetta.model.Scenario;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * Cette classe permet d'afficher toutes les informations concernant un scénario.
 *
 * @author Alice, Christophe
 * @version 2.0
 */

public class InfoScenarioFragment extends Fragment {
    private Scenario scenarioAffiche;
    private ScenarioFragment scenarioFragment;
    private static InfoScenarioFragment instance;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_affichage_scenario_layout, container, false);

        TextView nomScenario = (TextView) rootView.findViewById(R.id.nomscenario_affichage_textView);
        nomScenario.setText("");

        QuestionAdapter2 questionAdapter = new QuestionAdapter2(this.getContext(), new ArrayList<Question>());
        ObjectAdapter objectAdapter = new ObjectAdapter(this.getContext(), new ArrayList<Object>(), new ArrayList<Integer>());

        ListView questionsScenario = (ListView) rootView.findViewById(R.id.listView_questions_affichage);

        ListView compPackScenario = (ListView) rootView.findViewById(R.id.listView_composants_packs_affichage);


        questionsScenario.setAdapter(questionAdapter);
        compPackScenario.setAdapter(objectAdapter);


        if(scenarioAffiche != null){
            //Affichage du nom

            nomScenario.setText(scenarioAffiche.getNomScenario());

            //Affichage des questions
            ArrayList<Question> toutesLesQuestions = Controleur.getInstance(this.scenarioFragment.getContext()).getListeQuestions();
            ArrayList<Integer> tousIdQuestionsScenario = Controleur.getInstance(this.scenarioFragment.getContext()).getTousLesElementsSQ_question();
            ArrayList<Integer> tousIdScenariosScenario = Controleur.getInstance(this.scenarioFragment.getContext()).getTousLesElementsSQ_scenario();
            ArrayList<Question> tousNomQuestionsScenario = new ArrayList<>();

            for (Question question : toutesLesQuestions) {
                for (int i = 0; i < tousIdQuestionsScenario.size() ; i++){
                    if (question.getIdQuestion() == tousIdQuestionsScenario.get(i) && tousIdScenariosScenario.get(i) == this.scenarioAffiche.getIdScenario()){
                        tousNomQuestionsScenario.add(question);
                    }
                }
            }

            QuestionAdapter2 questionAdapter2 = new QuestionAdapter2(this.getContext(), tousNomQuestionsScenario);
            questionsScenario.setAdapter(questionAdapter2);



            //Affichage des composants / packs
            ArrayList<Composant> tousLesComposants = Controleur.getInstance(this.scenarioFragment.getContext()).getListeComposants();
            ArrayList<Pack> tousLesPacks = Controleur.getInstance(this.scenarioFragment.getContext()).getListePacks();
            ArrayList<Integer> tousIdComposantScenario = Controleur.getInstance(this.scenarioFragment.getContext()).getTousLesElementsSC_composant();
            ArrayList<Integer> tousIdPackScenario = Controleur.getInstance(this.scenarioFragment.getContext()).getTousLesElementsSP_pack();
            ArrayList<Integer> tousQuantiteSPScenario = Controleur.getInstance(this.scenarioFragment.getContext()).getTousLesElementsSP_quantite();
            ArrayList<Integer> tousQuantiteSCScenario = Controleur.getInstance(this.scenarioFragment.getContext()).getTousLesElementsSC_quantite();
            ArrayList<Integer> tousIdSCScenario = Controleur.getInstance(this.scenarioFragment.getContext()).getTousLesElementsSC_scenarios();
            ArrayList<Integer> tousIdSPScenario = Controleur.getInstance(this.scenarioFragment.getContext()).getTousLesElementsSP_scenarios();
            ArrayList<Integer> toutesLesQuantites = new ArrayList<>();
            ArrayList<Object> tousComposantPackScenario = new ArrayList<>();

            for (Composant composant : tousLesComposants) {
                for (int i = 0; i < tousIdComposantScenario.size(); i++){
                    if (composant.getIdComposant() == tousIdComposantScenario.get(i) && tousIdSCScenario.get(i) == this.scenarioAffiche.getIdScenario()){
                        tousComposantPackScenario.add(composant);
                        toutesLesQuantites.add(tousQuantiteSCScenario.get(i));
                    }
                }
            }
            for (Pack pack : tousLesPacks) {
                for (int i = 0; i < tousIdPackScenario.size(); i++){
                    if (pack.getIdPack() == tousIdPackScenario.get(i) && tousIdSPScenario.get(i) == this.scenarioAffiche.getIdScenario()){
                        tousComposantPackScenario.add(pack);
                        toutesLesQuantites.add(tousQuantiteSPScenario.get(i));
                    }
                }
            }
            ObjectAdapter objectAdapter2 = new ObjectAdapter(this.getContext(), tousComposantPackScenario, toutesLesQuantites);
            compPackScenario.setAdapter(objectAdapter2);
        }




        //Pour sortir du fragment d'affichage
        FloatingActionButton fermerAffichageButton = (FloatingActionButton) rootView.findViewById(R.id.fermer_affichage_scenarioButton);

        fermerAffichageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SectionsPagerAdapter.setScenarioFragment("ScenarioFragment");
                FragmentManager frman = getFragmentManager();
                FragmentTransaction ftran = frman.beginTransaction();
                Fragment leFrag = new ScenarioFragment();
                ftran.replace(R.id.view_pager, leFrag);
                ftran.commit();
                MainActivity.refreshFrag();
            }
        });

        return rootView;
    }

    /**
     * change le scénario dont on doit afficher les informations
     * @param scenarioAffiche le scenario à afficher
     */
    public void setScenarioAffiche(Scenario scenarioAffiche) {
        this.scenarioAffiche = scenarioAffiche;
    }

    /**
     * change d'instance
     * @param scenarioFragment la nouvelle instance
     */
    public void setScenarioFragment(ScenarioFragment scenarioFragment) {
        this.scenarioFragment = scenarioFragment;
    }

    /**
     * @return donne l'accès à une instance d' InforScenarioFragment
     */
    public static InfoScenarioFragment getInstance(){
        if (instance == null){
            instance = new InfoScenarioFragment();
        }
        return instance;
    }
}
