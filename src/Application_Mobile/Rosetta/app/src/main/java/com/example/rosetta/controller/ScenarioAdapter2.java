package com.example.rosetta.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.rosetta.R;
import com.example.rosetta.model.Scenario;

import java.util.ArrayList;

/**
 * Cette classe permet de contrôler l'affichage de la liste des Scénarios.
 *
 * @author Lucy
 * @version 2.0
 */

public class ScenarioAdapter2  extends BaseAdapter {

    private ArrayList<Scenario> listScenario;
    private Context context;
    private LayoutInflater inflater;

    /**
     * Le constructeur crée une nouvelle forme de ScenarioAdapter.
     *
     * @param context la contexte
     * @param list la liste des scénarios
     */
    public ScenarioAdapter2(Context context, ArrayList<Scenario> list){
        this.context = context;
        this.listScenario = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount(){
        return listScenario.size();
    }

    @Override
    public Object getItem(int position){
        return listScenario.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View view;

        // Initilisation de la vue
        if (convertView == null) {
            view = (View) inflater.inflate(R.layout.scenario2_item, parent, false);
        } else {
            view = (View) convertView;
        }

        // Initialisation des vues du layout
        TextView nomScenario = (TextView) view.findViewById(R.id.titreScenarioTextView);

        // Modification des vues
        nomScenario.setText(listScenario.get(position).getNomScenario());

        // On retourne la vue créée
        return view;
    }
}
