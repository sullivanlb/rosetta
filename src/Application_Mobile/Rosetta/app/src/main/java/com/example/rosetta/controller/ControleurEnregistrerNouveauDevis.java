package com.example.rosetta.controller;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.rosetta.R;
import com.example.rosetta.model.Client;
import com.example.rosetta.model.Composant;
import com.example.rosetta.model.Devis;
import com.example.rosetta.model.Pack;
import com.example.rosetta.ui.main.AjoutComposantFragment;
import com.example.rosetta.ui.main.DevisFragment;
import com.example.rosetta.ui.main.NouveauDevisFragment;
import com.example.rosetta.ui.main.SectionsPagerAdapter;

import java.util.ArrayList;

/**
 * Cette classe permet d'ajouter un devis et d'enregistrer les informations de celui-ci.
 *
 * @version 2.0
 */

public class ControleurEnregistrerNouveauDevis implements View.OnClickListener {

    private NouveauDevisFragment nouveauDevisFragment;

    /**
     * Le constructeur crée une nouvelle forme de ControleurEnregistrerNouveauComposant.
     *
     * @param nDF le contexte {@link NouveauDevisFragment}
     */
    public ControleurEnregistrerNouveauDevis(NouveauDevisFragment nDF) {
        this.nouveauDevisFragment = nDF;
    }


    @Override
    public void onClick(View v) {

        //Récupération des informations du formulaire du Composant
        EditText editNom = (EditText) this.nouveauDevisFragment.getView().findViewById(R.id.ajout_nomDevisEditText);
        String nomDevis = editNom.getText().toString();

        if(nomDevis != null && nomDevis.length() > 0){

            if(this.nouveauDevisFragment.getListObject().size() > 0){

                //Récupèrer le Client
                Client c = this.nouveauDevisFragment.getClientChoisi();

                //Ajouter dans la BDD : Devis, AppartientCD, AppartientDC, AppartientDP
                //Ajout d'un Devis
                Devis devis = new Devis(0, nomDevis, null, null, null, null);
                Controleur.getInstance(this.nouveauDevisFragment.getContext()).creerDevis(devis);

                //Ajout AppartientCD
                Controleur.getInstance(this.nouveauDevisFragment.getContext())
                        .creerAppartientCD(c, Controleur.getInstance(this.nouveauDevisFragment.getContext()).getListeDevis()
                                .get(Controleur.getInstance(this.nouveauDevisFragment.getContext()).getListeDevis().size() -1));

                // Ajout aux tables AppartientDC(devis, composant, quantite) et  AppartientDP(devis, pack, quantite) = Ok

                //!\\ Ajout des nouveaux composants et des nouveaux pack + ceux des scénarios choisis = A faire
                ArrayList<Object> listObject = this.nouveauDevisFragment.getListObject();

                //Composant
                for (int i = 0; i < listObject.size(); i++){
                    if(listObject.get(i).getClass().getName().equalsIgnoreCase("com.example.rosetta.model.Composant")){
                        Composant composant = (Composant) listObject.get(i);
                        int quantite = this.nouveauDevisFragment.getHashmapIdComposantQuantite().get(composant.getIdComposant());

                        Controleur.getInstance(this.nouveauDevisFragment.getContext())
                                .creerAppartientDC(Controleur.getInstance(this.nouveauDevisFragment.getContext()).getListeDevis()
                                        .get(Controleur.getInstance(this.nouveauDevisFragment.getContext()).getListeDevis().size() -1), composant, quantite);

                    }
                }

                //Pack
                for(int i = 0; i < listObject.size(); i++){
                    if(listObject.get(i).getClass().getName().equalsIgnoreCase("com.example.rosetta.model.Pack")){
                        Pack pack = (Pack) listObject.get(i);
                        int quantite = this.nouveauDevisFragment.getHashmapIdPackQuantite().get(pack.getIdPack());

                        Controleur.getInstance(this.nouveauDevisFragment.getContext())
                                .creerAppartientDP(Controleur.getInstance(this.nouveauDevisFragment.getContext()).getListeDevis()
                                        .get(Controleur.getInstance(this.nouveauDevisFragment.getContext()).getListeDevis().size() -1), pack, quantite);

                    }
                }


                //Permet de changer de framgment
                SectionsPagerAdapter.setDevisFragment("DevisFragment");
                FragmentManager frman = this.nouveauDevisFragment.getFragmentManager();
                FragmentTransaction ftran = frman.beginTransaction();
                Fragment leFrag = new DevisFragment();
                ftran.replace(R.id.view_pager, leFrag);
                ftran.commit();
            }
            else{
                Toast.makeText(this.nouveauDevisFragment.getView().getContext(), "Veuillez saisir au moins un composant/pack ", Toast.LENGTH_LONG).show();
            }
        }
        else{
            Toast.makeText(this.nouveauDevisFragment.getView().getContext(), "Veuillez saisir le nom du devis ", Toast.LENGTH_LONG).show();
        }

    }
}
