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

public class ScenarioAdapter extends BaseAdapter {

    private ArrayList<Scenario> listScenario;
    private Context context;
    private LayoutInflater inflater;

    public ScenarioAdapter(Context context, ArrayList<Scenario> list){
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

        if (convertView == null){
            //initialisation de la vue film_list_item
            view = (View) inflater.inflate(R.layout.scenario_item, parent, false);
        }
        else {
            view = (View) convertView;
        }

        //Initialisation des vues du layout
        TextView nomScenario = (TextView) view.findViewById(R.id.scenarioNomTextView);

        //modification des vues
        nomScenario.setText(listScenario.get(position).getNomScenario());

        //on retourne la vue créée
        return view;
    }
}
