package com.example.rosetta.controller;

import android.view.View;

import com.example.rosetta.MainActivity;
import com.example.rosetta.ui.main.ClientFragment;

public class ControleurEnregistrerClient implements View.OnClickListener {

    private ClientFragment clientFragment;

    public ControleurEnregistrerClient(ClientFragment cf) {
        this.clientFragment = cf;
    }

    @Override
    public void onClick(View v) {
        this.clientFragment.ajouterClient(v);
    }
}
