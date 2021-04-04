package com.example.rosetta.controller;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.rosetta.R;
import com.example.rosetta.model.Question;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


import java.util.ArrayList;

/**
 * Cette classe permet de contrôler l'affichage de la liste des questions sur l'onglet Devis.
 *
 * @author Lucy
 * @version 2.0
 */

public class QuestionAdapter extends BaseAdapter {

    private ArrayList<Question> listQuestion;
    private Context context;
    private LayoutInflater inflater;

    /**
     * Le constructeur crée une nouvelle forme de DevisQuestionAdapter.
     *
     * @param context le contexte
     * @param list la liste des questions
     */
    public QuestionAdapter(Context context, ArrayList<Question> list){
        this.context = context;
        this.listQuestion = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listQuestion.size();
    }

    @Override
    public Object getItem(int position) {
        return listQuestion.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;

        // Initialisation de la vue
        if (convertView == null) {
            view = (View) inflater.inflate(R.layout.question_item_devis, parent, false);
        } else {
            view = (View) convertView;
        }

        // Initialisation des vues du layout
        TextView nomQuestion = (TextView) view.findViewById(R.id.questionScenarioTextView);

        // Modification des vues
        nomQuestion.setText(listQuestion.get(position).getNomQuestion());

        //Bouton pour valider et change en vert
        FloatingActionButton okButton = (FloatingActionButton) view.findViewById(R.id.checkDevisButton);
        //Bouton pour Refuser et change en rouge
        FloatingActionButton nonOKButton = (FloatingActionButton) view.findViewById(R.id.crossDevisButton);

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                okButton.setBackgroundTintList(ColorStateList.valueOf(context.getResources().getColor(R.color.vert)));
            }
        });


        nonOKButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nonOKButton.setBackgroundTintList(ColorStateList.valueOf(context.getResources().getColor(R.color.rouge)));
            }
        });

        // On retourne la vue créée
        return view;
    }
}
