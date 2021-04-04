package com.example.rosetta.ui.main;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.rosetta.R;
import com.example.rosetta.controller.ClientAdapter;
import com.example.rosetta.controller.Controleur;
import com.example.rosetta.controller.ControleurListeScenarioDevis;
import com.example.rosetta.controller.QuestionAdapter;
import com.example.rosetta.controller.QuestionAdapter2;
import com.example.rosetta.controller.ScenarioAdapter2;
import com.example.rosetta.model.Client;
import com.example.rosetta.model.Question;
import com.example.rosetta.model.Scenario;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * Cette classe permet de mettre en place l'ensemble des controleurs correspondants à la vue
 * associée : l'interface de DevisQuestions.
 * Elle est accessible depuis le bouton continuer de l'interface Devis.
 *
 * @author Lucy
 * @version 2.0
 */

public class DevisQuestionFragment extends Fragment {

    private View rootView;
    private Controleur controleur;

    private NouveauDevisClientScenarioFragment nouveauDevisClientScenarioFragment;
    private static DevisQuestionFragment instance;

    private ArrayList<Question> listeQuestionTemporaire;
    private QuestionAdapter adapteurQuestion;
    private ListView listeViewQuestion;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.rootView = inflater.inflate(R.layout.fragment_devis_question_layout, container, false);

        // Le controleur principal (accès à la base de données interne)
        this.controleur = Controleur.getInstance(this.getContext());

        //========================= Questions =====================================================

        //Affichage des questions
        ArrayList<Scenario> ScenarioChoisi =  this.nouveauDevisClientScenarioFragment.getListScenarioTemporaire();
        ArrayList<Question> toutesLesQuestions = Controleur.getInstance(this.nouveauDevisClientScenarioFragment.getContext()).getListeQuestions();
        ArrayList<Integer> tousIdQuestionsSQ = Controleur.getInstance(this.nouveauDevisClientScenarioFragment.getContext()).getTousLesElementsSQ_question();
        ArrayList<Integer> tousIdScenariosSQ = Controleur.getInstance(this.nouveauDevisClientScenarioFragment.getContext()).getTousLesElementsSQ_scenario();
        listeQuestionTemporaire = new ArrayList<Question>();


        for (Question question : toutesLesQuestions) {
            for (int i = 0; i < tousIdQuestionsSQ.size() ; i++){
                if (question.getIdQuestion() == tousIdQuestionsSQ.get(i)){

                    for(int j = 0; j < ScenarioChoisi.size(); j++){
                        if(tousIdScenariosSQ.get(i) == ScenarioChoisi.get(j).getIdScenario()){
                            listeQuestionTemporaire.add(question);
                        }
                    }
                }
            }
        }

        // Initialisation de l'adapteur pour les questions récupèrées depuis les scénarios choisis
        this.listeViewQuestion = (ListView) rootView.findViewById(R.id.listView_devis_question);

        this.adapteurQuestion= new QuestionAdapter(this.getActivity(), listeQuestionTemporaire);
        this.listeViewQuestion.setAdapter(this.adapteurQuestion);


        //=========================== Boutons ======================================================

        //Bouton qui permet de passer à l'interface suivant en enregistrant le client t les scénarios choisis
        Button suivantQuestionButton = (Button) rootView.findViewById(R.id.suivantQuestionButton);
        suivantQuestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Envoyer Client + les scénarios à la prochaine interface
                //Permet de changer de fragment
                SectionsPagerAdapter.setDevisFragment("NouveauDevisFragment");
                FragmentManager frman = getFragmentManager();
                FragmentTransaction ftran = frman.beginTransaction();
                Fragment leFrag = NouveauDevisFragment.getInstance();
                ((NouveauDevisFragment) leFrag).setNouveauDevisClientScenarioFragment(DevisQuestionFragment.this.nouveauDevisClientScenarioFragment);
                ftran.replace(R.id.view_pager, leFrag);
                ftran.commit();
            }
        });

        //Bouton pour revenir en arrière.
        Button retourQuestionButton = (Button) rootView.findViewById(R.id.retourQuestionButton);
        retourQuestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SectionsPagerAdapter.setDevisFragment("NouveauDevisClientScenarioFragment");
                FragmentManager frman = getFragmentManager();
                FragmentTransaction ftran = frman.beginTransaction();
                Fragment leFrag = new NouveauDevisClientScenarioFragment();
                ftran.replace(R.id.view_pager, leFrag);
                ftran.commit();
            }
        });

        // ============================== Check Question ===========================================
        FloatingActionButton fabCheck = (FloatingActionButton) rootView.findViewById(R.id.checkDevisButton);
        FloatingActionButton fabCroix = (FloatingActionButton) rootView.findViewById(R.id.crossDevisButton);

        return this.rootView;
    }

    /**
     * Récupère l'Instance et les données (client + scénarios)
     * @param nDCSF la nouvelle instance
     */
    public void setNouveauDevisClientScenarioFragment(NouveauDevisClientScenarioFragment nDCSF) {
        this.nouveauDevisClientScenarioFragment = nDCSF;
    }

    /**
     * @return donne l'accès à une instance de DevisQuestionFragment
     */
    public static DevisQuestionFragment getInstance(){
        if (instance == null){
            instance = new DevisQuestionFragment();
        }
        return instance;
    }
}

