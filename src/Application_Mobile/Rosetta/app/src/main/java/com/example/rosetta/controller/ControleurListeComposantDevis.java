package com.example.rosetta.controller;

import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rosetta.R;
import com.example.rosetta.model.Composant;
import com.example.rosetta.ui.main.NouveauDevisFragment;

/**
 * Cette classe permet de gérer La liste des composants dans Devis
 *
 * @version 2.0
 */

public class ControleurListeComposantDevis implements AdapterView.OnItemClickListener{

    private NouveauDevisFragment nouveauDevisFragment;

    /**
     * Le constructeur crée une nouvelle forme de ControleurListeClients
     *
     * @param nD contexte {@link NouveauDevisFragment }
     */
    public ControleurListeComposantDevis(NouveauDevisFragment nD) {
        if (nD  != null) {
            this.nouveauDevisFragment = nD ;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        // Récupération des informations du pack sélectionné
        Composant composant = this.nouveauDevisFragment.getListeComposants().get(position);
        this.nouveauDevisFragment.setIdSelectionnnerComposant(composant.getIdComposant());
        this.nouveauDevisFragment.setIndiceSelectionnerComposant(position);

        // Vérification de la saisie de la quantité
        EditText editQuantite = (EditText) this.nouveauDevisFragment.getView().findViewById(R.id.quantiteEditTextDevis);
        int quantite = editQuantite.getText().toString().length() > 0 ? Integer.parseInt(editQuantite.getText().toString()) : -1;

        boolean estDejaAjoute = false;
        int idComposant = composant.getIdComposant();

        int i = 0;
        for (Object object : this.nouveauDevisFragment.getListObject()) {

            if(object instanceof Composant){
                Composant comp = (Composant) this.nouveauDevisFragment.getListObject().get(i);
                if(comp.getIdComposant() == idComposant){
                    estDejaAjoute = true;
                }
            }
            i++;
        }

        if(!estDejaAjoute){
            if(quantite > 0){

                this.nouveauDevisFragment.getListObject().add(composant);
                this.nouveauDevisFragment.getHashmapIdComposantQuantite().put(idComposant, quantite);

                DevisAdapterObject adapter = new DevisAdapterObject(this.nouveauDevisFragment.getActivity(), nouveauDevisFragment.getListObject());
                this.nouveauDevisFragment.getListeViewObject().setAdapter(adapter);

                // Vider le champ de la quantité
                editQuantite.setText("");

            }
            else{
                Toast.makeText(this.nouveauDevisFragment.getContext(), "Veuillez saisir la quantité avant.", Toast.LENGTH_LONG).show();
            }
        }
        else{

            int j = 0;
            for (Object object : this.nouveauDevisFragment.getListObject()) {

                if(object instanceof Composant){
                    Composant comp = (Composant) this.nouveauDevisFragment.getListObject().get(j);
                    if(comp.getIdComposant() == idComposant){
                        this.nouveauDevisFragment.getListObject().remove(j);
                        this.nouveauDevisFragment.getHashmapIdComposantQuantite().remove(idComposant);
                    }
                }
                j++;
            }

            DevisAdapterObject adapter = new DevisAdapterObject(this.nouveauDevisFragment.getActivity(), nouveauDevisFragment.getListObject());
            this.nouveauDevisFragment.getListeViewObject().setAdapter(adapter);

            // Vider le champ de la quantité
            editQuantite.setText("");

        }
    }
}
