package com.example.rosetta.controller;

import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rosetta.R;
import com.example.rosetta.model.Composant;
import com.example.rosetta.model.Pack;
import com.example.rosetta.ui.main.NouveauScenarioFragment;

/**
 * Cette classe permet d'afficher les informations du composant sélectionné dans le formulaire.
 *
 * @author Lucy
 * @version 2.0
 */

public class ControleurListeComposantScenario  implements AdapterView.OnItemClickListener {

    private NouveauScenarioFragment nouveauScenarioFragment;

    /**
     * Le constructeur crée une nouvelle forme de ControleurListeComposants
     *
     * @param nSf le contexte {@link NouveauScenarioFragment}
     */

    public  ControleurListeComposantScenario(NouveauScenarioFragment nSf) {
        if(nSf != null){
            this.nouveauScenarioFragment = nSf;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        // Récupération des informations du pack sélectionné
        Composant composant = this.nouveauScenarioFragment.getListeComposants().get(position);
        this.nouveauScenarioFragment.setIdSelectionnnerComposant(composant.getIdComposant());
        this.nouveauScenarioFragment.setIndiceSelectionnerComposant(position);

        // Vérification de la saisie de la quantité
        EditText editQuantite = (EditText) this.nouveauScenarioFragment.getView().findViewById(R.id.quantiteEditTextScenario);
        int quantite = editQuantite.getText().toString().length() > 0 ? Integer.parseInt(editQuantite.getText().toString()) : -1;

        boolean estDejaAjoute = false;
        int idComposant = composant.getIdComposant();

        int i = 0;
        for (Object object : this.nouveauScenarioFragment.getListeObjectPackComposant()) {

            if(object instanceof Composant){
                Composant comp = (Composant) this.nouveauScenarioFragment.getListeObjectPackComposant().get(i);
                if(comp.getIdComposant() == idComposant){
                    estDejaAjoute = true;
                }
            }
            i++;
        }

        if(!estDejaAjoute){
            if( quantite > 0){

                this.nouveauScenarioFragment.getListeObjectPackComposant().add(composant);
                this.nouveauScenarioFragment.getHashmapIdComposantQuantite().put(idComposant, quantite);

                ObjectAdapter adapter = new ObjectAdapter(this.nouveauScenarioFragment.getActivity(), nouveauScenarioFragment.getListeObjectPackComposant());
                this.nouveauScenarioFragment.getListViewObject().setAdapter(adapter);

                // Vider le champ de la quantité
                editQuantite.setText("");
            }
            else{
                Toast.makeText(this.nouveauScenarioFragment.getContext(), "Veuillez saisir la quantité avant.", Toast.LENGTH_LONG).show();
            }
        }
        else{

            int j = 0;
            for (Object object : this.nouveauScenarioFragment.getListeObjectPackComposant()) {

                if(object instanceof Composant){
                    Composant comp = (Composant) this.nouveauScenarioFragment.getListeObjectPackComposant().get(j);
                    if(comp.getIdComposant() == idComposant){
                       this.nouveauScenarioFragment.getListeObjectPackComposant().remove(j);
                        this.nouveauScenarioFragment.getHashmapIdComposantQuantite().remove(idComposant);
                    }
                }
                j++;
            }

            ObjectAdapter adapter = new ObjectAdapter(this.nouveauScenarioFragment.getActivity(), nouveauScenarioFragment.getListeObjectPackComposant());
            this.nouveauScenarioFragment.getListViewObject().setAdapter(adapter);

            // Vider le champ de la quantité
            editQuantite.setText("");
        }
    }
}
