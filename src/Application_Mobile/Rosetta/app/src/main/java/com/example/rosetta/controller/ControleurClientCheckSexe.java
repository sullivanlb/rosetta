package com.example.rosetta.controller;

import android.view.View;
import android.widget.CheckBox;

import java.util.ArrayList;

public class ControleurClientCheckSexe implements View.OnClickListener {

    private ArrayList<CheckBox> listeCases;

    public ControleurClientCheckSexe(ArrayList<CheckBox> liste) {
        if (liste != null)
            this.listeCases = liste;
        else
            this.listeCases = new ArrayList<CheckBox>();
    }

    @Override
    public void onClick(View v) {
        if (((CheckBox) v).isChecked()) {
            for (CheckBox box : this.listeCases) {
                if (box != ((CheckBox) v)) {
                    box.setChecked(false);
                }
            }
        }
    }
}
