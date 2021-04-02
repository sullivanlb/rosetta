package com.example.rosetta.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.rosetta.R;
import com.example.rosetta.model.Composant;

import java.util.ArrayList;

/**
 * Cette classe permet de contrôler l'affichage de la liste des Composants.
 *
 * @author Alice
 * @version 2.0
 */

public class ComposantAdapter extends BaseAdapter {

    private ArrayList<Composant> listComposant;
    private Context context;
    private LayoutInflater inflater;

    /**
     * Le constructeur crée une nouvelle forme de composantAdapter.
     *
     * @param context le contexte
     * @param list la liste des composants
     */
    public ComposantAdapter(Context context, ArrayList<Composant> list){
        this.context = context;
        this.listComposant = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return this.listComposant.size();
    }

    @Override
    public Object getItem(int position) {
        return this.listComposant.get(position);
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
            view = (View) inflater.inflate(R.layout.composant_item, parent, false);
        } else {
            view = (View) convertView;
        }

        // Initialisation des vues du layout
        TextView nomQuestion = (TextView) view.findViewById(R.id.nomComposantTextView);

        // Modification des vues
        nomQuestion.setText(listComposant.get(position).getNomComposant());

        // On retourne la vue créée
        return view;
    }
}
