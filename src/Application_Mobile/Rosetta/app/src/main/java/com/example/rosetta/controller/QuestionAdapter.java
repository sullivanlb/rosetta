package com.example.rosetta.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.rosetta.R;
import com.example.rosetta.model.Question;


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
        TextView nomQuestion = (TextView) view.findViewById(R.id.questionTextView);

        // Modification des vues
        nomQuestion.setText(listQuestion.get(position).getNomQuestion());

        // On retourne la vue créée
        return view;
    }
}
