package com.example.rosetta.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.rosetta.R;
import com.example.rosetta.model.Client;
import com.example.rosetta.ui.main.ClientFragment;

import java.util.ArrayList;

public class ClientAdapter extends BaseAdapter {

    private ArrayList<Client> listClient;
    private Context context;
    private LayoutInflater inflater;

    public ClientAdapter(Context context, ArrayList<Client> list) {
        this.context = context;
        this.listClient = list;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount(){
        return listClient.size();
    }

    @Override
    public Object getItem(int position){
        return listClient.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;

        if (convertView == null){
            // Initialisation de la vue film_list_item
            view = (View) inflater.inflate(R.layout.client_item, parent, false);
        }
        else {
            view = (View) convertView;
        }

        // Initialisation des composants du layout
        TextView nomClient = (TextView) view.findViewById(R.id.itemClientNom);
        TextView prenomClient = (TextView) view.findViewById(R.id.itemClientPrenom);

        // Modification des composants
        nomClient.setText(listClient.get(position).getNomClient());
        prenomClient.setText(listClient.get(position).getPrenomClient());

        // On retourne la vue créée
        return view;
    }
}
