package com.example.rosetta.controller;

import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rosetta.R;
import com.example.rosetta.model.Pack;
import com.example.rosetta.ui.main.NouveauScenarioFragment;

/**
 * Cette classe permet d'afficher les informations du pack sélectionné dans le formulaire.
 *
 * @author Lucy
 * @version 2.0
 */

public class ControleurListePackScenario implements AdapterView.OnItemClickListener {

    private NouveauScenarioFragment nouveauScenarioFragment;

    /**
     * Le constructeur crée une nouvelle forme de ControleurListeComposants
     *
     * @param nSf le contexte {@link NouveauScenarioFragment}
     */

    public  ControleurListePackScenario(NouveauScenarioFragment nSf) {
        if(nSf != null){
            this.nouveauScenarioFragment = nSf;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        // Récupération des informations du pack sélectionné
        Pack pack = this.nouveauScenarioFragment.getListePacks().get(position);
        this.nouveauScenarioFragment.setIdSelectionnnerPack(pack.getIdPack());
        this.nouveauScenarioFragment.setIndiceSelectionnerPack(position);

        // Vérification de la saisie de la quantité
        EditText editQuantite = (EditText) this.nouveauScenarioFragment.getView().findViewById(R.id.quantiteEditTextScenario);
        int quantite = editQuantite.getText().toString().length() > 0 ? Integer.parseInt(editQuantite.getText().toString()) : -1;

        boolean estDejaAjoute = false;
        int idPack = pack.getIdPack();

        int i = 0;
        for (Object object : this.nouveauScenarioFragment.getListeObjectPackComposant()) {

            if(object instanceof Pack){
                Pack pa = (Pack) this.nouveauScenarioFragment.getListeObjectPackComposant().get(i);
                if(pa.getIdPack() == idPack){
                    estDejaAjoute = true;
                }
            }
            i++;
        }

        if(!estDejaAjoute){

            if( quantite > 0){

                this.nouveauScenarioFragment.getListeObjectPackComposant().add(pack);
                this.nouveauScenarioFragment.getHashmapIdPackQuantite().put(idPack, quantite);

                ObjectAdapter adapter = new ObjectAdapter(this.nouveauScenarioFragment.getActivity(), nouveauScenarioFragment.getListeObjectPackComposant(),null);
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

                if(object instanceof Pack){
                    Pack pa = (Pack) this.nouveauScenarioFragment.getListeObjectPackComposant().get(j);
                    if(pa.getIdPack() == idPack){
                        this.nouveauScenarioFragment.getListeObjectPackComposant().remove(j);
                        this.nouveauScenarioFragment.getHashmapIdPackQuantite().remove(idPack);
                    }
                }
                j++;
            }

            ObjectAdapter adapter = new ObjectAdapter(this.nouveauScenarioFragment.getActivity(), nouveauScenarioFragment.getListeObjectPackComposant(),null);
            this.nouveauScenarioFragment.getListViewObject().setAdapter(adapter);

            // Vider le champ de la quantité
            editQuantite.setText("");
        }
    }
}
