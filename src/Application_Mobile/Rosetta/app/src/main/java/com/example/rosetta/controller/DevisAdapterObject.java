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
import com.example.rosetta.ui.main.NouveauDevisClientScenarioFragment;

import java.util.ArrayList;

/**
 * Cette classe permet de contrôler l'affichage de la liste des Objects.
 *
 * @author Lucy
 * @version 2.0
 */

public class DevisAdapterObject extends BaseAdapter {

    private ArrayList<Object> listObject;
    private Context context;
    private LayoutInflater inflater;

    /**
     * Le constructeur crée une nouvelle forme de ObjectAdapter
     *
     * @param context le contexte
     * @param list la liste des objects (composant + pasck)
     */
    public DevisAdapterObject(Context context, ArrayList<Object> list){
        this.context = context;
        this.listObject = list;
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

            view = (View) inflater.inflate(R.layout.composantspacks_nouveaudevis_item, parent, false);
        } else {
            view = (View) convertView;
        }

        // Initialisation des vues du layout
        TextView nom = (TextView) view.findViewById(R.id.nomComposantPack_nouveau_devisTextView);
        TextView unite = (TextView) view.findViewById(R.id.uniteComposantPack_nouveau_devisTextView);
        TextView prix = (TextView) view.findViewById(R.id.prixComposantPack_nouveau_devisTextView);

        // Modification des vues
        if(listObject.get(position) instanceof  Composant){
            Composant composant = (Composant) listObject.get(position);
            nom.setText("Composant : " + composant.getNomComposant());
            unite.setText(composant.getUniteComposant());
            prix.setText(""+ composant.getPrixComposant() + " €");
        }
        else if(listObject.get(position) instanceof Pack){
            Pack pack = (Pack) listObject.get(position);
            nom.setText("Pack : " + pack.getNomPack());
            unite.setText(" / ");
            prix.setText(" / ");
        }

        // On retourne la vue créée
        return view;
    }
}
