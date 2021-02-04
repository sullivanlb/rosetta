package com.example.rosetta.controller;

import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.rosetta.R;
import com.example.rosetta.ui.main.ClientFragment;

/**
 * Cette classe permet de supprimer un client lorsque celui est sélectionné (après confirmation).
 *
 * @author Christophe
 * @version 2.0
 */
public class ControleurClientSupprimer implements View.OnClickListener {

    private ClientFragment clientFragment;

    /**
     * Le constructeur crée une nouvelle forme de ControleurClientSupprimer.
     *
     * @param cf le contexte {@link ClientFragment}
     */
    public ControleurClientSupprimer(ClientFragment cf) {
        this.clientFragment = cf;
    }

    @Override
    public void onClick(View v) {
    }
}
