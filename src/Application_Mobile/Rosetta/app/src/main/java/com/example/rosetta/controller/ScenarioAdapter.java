package com.example.rosetta.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.rosetta.R;
import com.example.rosetta.model.Scenario;
import com.example.rosetta.ui.main.ScenarioFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * Cette classe permet de contrôler l'affichage de la liste des Scénarios.
 *
 * @author Alice
 * @version 2.0
 */
public class ScenarioAdapter extends BaseAdapter {

    private ArrayList<Scenario> listScenario;
    private Context context;
    private LayoutInflater inflater;
    private ScenarioFragment scenarioFragment;

    /**
     * Le constructeur crée une nouvelle forme de ScenarioAdapter.
     *
     * @param context la contexte
     * @param list la liste des scénarios
     * @param sf instance de ScenarioFragment
     */
    public ScenarioAdapter(Context context, ArrayList<Scenario> list, ScenarioFragment sf){
        this.context = context;
        this.listScenario = list;
        inflater = LayoutInflater.from(context);
        this.scenarioFragment = sf;
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
            view = (View) inflater.inflate(R.layout.scenario_item, parent, false);
        } else {
            view = (View) convertView;
        }

        // Initialisation des vues du layout
        TextView nomScenario = (TextView) view.findViewById(R.id.scenarioNomTextView);

        // Modification des vues
        nomScenario.setText(listScenario.get(position).getNomScenario());

        //Bouton pour afficher les informations d'un scénario
        FloatingActionButton voirScenarioButton = (FloatingActionButton) view.findViewById(R.id.voirScenarioButton);

        voirScenarioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScenarioFragment scenarioFragment = ScenarioFragment.getInstance();
                scenarioFragment.setIdSelectionnnerScenario(listScenario.get(position).getIdScenario());
                scenarioFragment.setIndiceSelectionnerScenario(position);
                scenarioFragment.actionVoir();
            }
        });

        //Bouton pour supprimer un scénario
        FloatingActionButton supprimerScenario = (FloatingActionButton) view.findViewById(R.id.supprimerScenarioButton);
        ControleurScenarioSupprimer controleurScenarioSupprimer = new ControleurScenarioSupprimer(ScenarioFragment.getInstance(), listScenario.get(position).getIdScenario());
        supprimerScenario.setOnClickListener(controleurScenarioSupprimer);

        // On retourne la vue créée
        return view;
    }
}
