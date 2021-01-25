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

public class DevisAdapter extends BaseAdapter {

    private ArrayList<Devis> listDevis;
    private Context context;
    private LayoutInflater inflater;

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

        if (convertView == null){
            //initialisation de la vue film_list_item
            view = (View) inflater.inflate(R.layout.devis_item, parent, false);
        }
        else {
            view = (View) convertView;
        }

        //Initialisation des vues du layout
        TextView nomDevis = (TextView) view.findViewById(R.id.nomDevisTextView);

        //modification des vues
        nomDevis.setText(listDevis.get(position).getNomDevis());

        //on retourne la vue crée
        return view;
    }
}
