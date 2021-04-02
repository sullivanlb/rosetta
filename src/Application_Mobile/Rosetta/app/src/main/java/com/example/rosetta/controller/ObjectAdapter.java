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
 * Cette classe permet de contrôler l'affichage de la liste des Objects.
 *
 * @author Lucy, Christophe, Alice
 * @version 2.0
 */

public class ObjectAdapter extends BaseAdapter {

    private ArrayList<Object> listObject;
    private ArrayList<Integer> quantites;
    private Context context;
    private LayoutInflater inflater;

    /**
     * Le constructeur crée une nouvelle forme de ObjectAdapter
     *
     * @param context le contexte
     * @param list la liste des objects (composant + pasck)
     */
    public ObjectAdapter(Context context, ArrayList<Object> list, ArrayList<Integer> quantites){
        this.context = context;
        this.listObject = list;
        this.quantites = quantites;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listObject.size();
    }

    @Override
    public Object getItem(int position) {
        return listObject.get(position);
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
        TextView nom = (TextView) view.findViewById(R.id.nomPackTextView);

        // Modification des vues
        if(listObject.get(position) instanceof Composant){
            Composant composant = (Composant) listObject.get(position);
            nom.setText("Composant : " + composant.getNomComposant());
        }
        else if(listObject.get(position) instanceof Pack){
            Pack pack = (Pack) listObject.get(position);
            nom.setText("Pack : " + pack.getNomPack());
        }


        // On retourne la vue créée
        return view;
    }
}
