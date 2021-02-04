package com.example.rosetta.controller;

import android.view.View;
import android.widget.CheckBox;

import java.util.ArrayList;

/**
 * Cette classe permet de cocher ou décocher les cases, représentant le sexe du nouveau client,
 * selon les actions réalisées par l'utilisateur.
 *
 * @author Christophe
 * @version 2.0
 */
public class ControleurClientCheckSexe implements View.OnClickListener {

    private ArrayList<CheckBox> listeCases;

    /**
     * Le constructeur crée une nouvelle forme de ControleurClientCheckSexe.
     *
     * @param liste la liste des cases présentes
     */
    public ControleurClientCheckSexe(ArrayList<CheckBox> liste) {
        if (liste != null)
            this.listeCases = liste;
        else
            this.listeCases = new ArrayList<CheckBox>();
    }

    @Override
    public void onClick(View v) {
        // Si la case est cochée, les autres sont décochées
        if (((CheckBox) v).isChecked()) {
            for (CheckBox box : this.listeCases) {
                if (box != ((CheckBox) v)) {
                    box.setChecked(false);
                }
            }
        }
    }
}
