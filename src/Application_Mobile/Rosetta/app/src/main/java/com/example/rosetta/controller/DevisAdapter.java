package com.example.rosetta.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.rosetta.R;
import com.example.rosetta.model.Devis;

import java.util.ArrayList;

/**
 * Cette classe permet de contrôler l'affichage de la liste des Devis.
 *
 * @author Alice
 * @version 2.0
 */
public class DevisAdapter extends BaseAdapter {

    private ArrayList<Devis> listDevis;
    private Context context;
    private LayoutInflater inflater;

    /**
     * Le constructeur crée une nouvelle forme de DevisAdapter.
     *
     * @param context le contexte
     * @param list la liste des devis
     */
    public DevisAdapter(Context context, ArrayList<Devis> list){
        this.context = context;
        this.listDevis = list;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount(){
        return listDevis.size();
    }

    @Override
    public Object getItem(int position){
        return listDevis.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View view;

        // Initialisation de la vue
        if (convertView == null) {
            view = (View) inflater.inflate(R.layout.devis_item, parent, false);
        } else {
            view = (View) convertView;
        }

        // Initialisation des vues du layout
        TextView nomDevis = (TextView) view.findViewById(R.id.nomDevisTextView);

        // Modification des vues
        nomDevis.setText(listDevis.get(position).getNomDevis());

        // On retourne la vue créée
        return view;
    }
}
