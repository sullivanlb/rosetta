package com.example.rosetta.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.rosetta.R;
import com.example.rosetta.model.Composant;
import com.example.rosetta.model.Pack;

import java.util.ArrayList;

/**
 * Cette classe permet de contrôler l'affichage de la liste des Packs.
 *
 * @author Alice
 * @version 2.0
 */

public class PackAdapter extends BaseAdapter {

    private ArrayList<Pack> listPack;
    private Context context;
    private LayoutInflater inflater;

    /**
     * Le constructeur crée une nouvelle forme de PackAdapter.
     *
     * @param context le contexte
     * @param list la liste des packs
     */
    public PackAdapter(Context context, ArrayList<Pack> list){
        this.context = context;
        this.listPack= list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return this.listPack.size();
    }

    @Override
    public Object getItem(int position) {
        return this.listPack.get(position);
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
            view = (View) inflater.inflate(R.layout.pack_item, parent, false);
        } else {
            view = (View) convertView;
        }

        // Initialisation des vues du layout
        TextView nomQuestion = (TextView) view.findViewById(R.id.nomPackTextView);

        // Modification des vues
        nomQuestion.setText(listPack.get(position).getNomPack());

        // On retourne la vue créée
        return view;
    }
}

