package com.example.rosetta.controller;

import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rosetta.R;
import com.example.rosetta.model.Composant;
import com.example.rosetta.model.Pack;
import com.example.rosetta.ui.main.NouveauDevisClientScenarioFragment;
import com.example.rosetta.ui.main.NouveauDevisFragment;

public class ControleurListePackDevis implements AdapterView.OnItemClickListener{

    private NouveauDevisFragment nouveauDevisFragment;

    /**
     * Le constructeur crée une nouvelle forme de ControleurListeClients
     *
     * @param nD contexte {@link NouveauDevisFragment }
     */
    public ControleurListePackDevis(NouveauDevisFragment nD) {
        if (nD  != null) {
            this.nouveauDevisFragment = nD ;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        // Récupération des informations du pack sélectionné
        Pack pack = this.nouveauDevisFragment.getListePacks().get(position);
        this.nouveauDevisFragment.setIdSelectionnnerPack(pack.getIdPack());
        this.nouveauDevisFragment.setIndiceSelectionnerPack(position);

        // Vérification de la saisie de la quantité
        EditText editQuantite = (EditText) this.nouveauDevisFragment.getView().findViewById(R.id.quantiteEditTextDevis);
        int quantite = editQuantite.getText().toString().length() > 0 ? Integer.parseInt(editQuantite.getText().toString()) : -1;

        boolean estDejaAjoute = false;
        int idPack = pack.getIdPack();

        int i = 0;
        for (Object object : this.nouveauDevisFragment.getListObject()) {

            if(object instanceof Pack){
                Pack p = (Pack)this.nouveauDevisFragment.getListObject().get(i);
                if(p.getIdPack() == idPack){
                    estDejaAjoute = true;
                }
            }
            i++;
        }

        if(!estDejaAjoute){
            if(quantite > 0){

                this.nouveauDevisFragment.getListObject().add(pack);
                this.nouveauDevisFragment.getHashmapIdPackQuantite().put(idPack, quantite);

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

                if(object instanceof Pack){
                    Pack p = (Pack)this.nouveauDevisFragment.getListObject().get(j);
                    if(p.getIdPack()== idPack){
                        this.nouveauDevisFragment.getListObject().remove(j);
                        this.nouveauDevisFragment.getHashmapIdPackQuantite().remove(idPack);
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
