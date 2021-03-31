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
import com.example.rosetta.controller.QuestionAdapter;
import com.example.rosetta.model.Question;
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_devis_question_layout, container, false);

        // Temporaire : les données sont brutes. Elles seront ensuite récupérées depuis la base de
        // données
        ArrayList<Question> listeQuestion = new ArrayList<Question>();
        Question question1 = new Question(1,"Question 11111111111111111");
        Question question2 = new Question(2,"Question testtt  jdkvcdn fej ? ");
        Question question3 = new Question(3,"Question blablabla ? ");
        Question question4 = new Question(4,"Question très longue longue blablablbalabalalaaklablablahdvndh ? ");
        Question question5 = new Question(5,"Question testtt  jdkvcdn fej ? ");
        Question question6 = new Question(6,"Question blablabla ? ");
        Question question7 = new Question(7,"Question très longue longue blablablbalabalalaaklablablahdvndh ? ");

        //On ajoute la liste des questions
        listeQuestion.add(question1);
        listeQuestion.add(question2);
        listeQuestion.add(question3);
        listeQuestion.add(question4);
        listeQuestion.add(question5);
        listeQuestion.add(question6);
        listeQuestion.add(question7);

        // Initialisation de l'adapter pour devis
        QuestionAdapter adapter = new QuestionAdapter(this.getActivity(), listeQuestion);
        ListView list = (ListView) rootView.findViewById(R.id.listView_devis_question);
        list.setAdapter(adapter);

        //Bouton check et croix
        FloatingActionButton fabCheck = (FloatingActionButton) rootView.findViewById(R.id.checkDevisButton);
        FloatingActionButton fabCroix = (FloatingActionButton) rootView.findViewById(R.id.crossDevisButton);

        Button retourQuestionButton = (Button) rootView.findViewById(R.id.retourQuestionButton);
        Button suivantQuestionButton = (Button) rootView.findViewById(R.id.suivantQuestionButton);

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

        suivantQuestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SectionsPagerAdapter.setDevisFragment("NouveauDevisFragment");
                FragmentManager frman = getFragmentManager();
                FragmentTransaction ftran = frman.beginTransaction();
                Fragment leFrag = new NouveauDevisFragment();
                ftran.replace(R.id.view_pager, leFrag);
                ftran.commit();
            }
        });

        return rootView;
    }
}