package com.example.rosetta.controller;

import android.content.Context;

import com.example.rosetta.model.AccesLocal;
import com.example.rosetta.model.Client;

import java.util.ArrayList;

public class Controleur {

    private static Controleur instance;
    private static AccesLocal accesLocal;
    private static ArrayList<Client> listeClients;

    public Controleur() {
        super();
        this.listeClients = Controleur.accesLocal.tousLesClients();
    }

    public static final Controleur getInstance(Context context) {
        if (Controleur.instance == null) {
            Controleur.accesLocal = new AccesLocal(context);
            Controleur.instance = new Controleur();
        }

        return Controleur.instance;
    }

    public void creerClient(Client client) {
        if (client != null) {
            Controleur.accesLocal.ajoutClient(client);
            this.listeClients.add(Controleur.accesLocal.dernierClient());
        }
    }

    public static ArrayList<Client> getListeClients() {
        return listeClients;
    }
}
