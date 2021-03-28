package com.example.rosetta.controller;


import android.content.Intent;
import android.view.View;
import android.widget.Button;


import com.example.rosetta.MainActivity;
import com.example.rosetta.R;
import com.example.rosetta.ui.main.DevisFragment;

/**
 * Cette classe permet d'avoir accès à la liste des questions lorsque l'utilisateur appuie sur le bouton "continuer"
 *
 * @author Lucy
 * @version 2.0
 */

public class ControleurDevisContinuer implements View.OnClickListener {

    private DevisFragment devisFragment;

    /**
     * Le constructeur crée une nouvelle forme de ControleurDevisContinuer.
     *
     * @param df le contexte {@link DevisFragment}
     */
    public ControleurDevisContinuer(DevisFragment df) {
        this.devisFragment = df;
    }


    @Override
    public void onClick(View v) {




    }

}
