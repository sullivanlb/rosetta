package com.example.rosetta.controller;

import android.content.Context;

import com.example.rosetta.model.AccesLocal;
import com.example.rosetta.model.Client;

import java.util.ArrayList;

/**
 * Cette classe permet de faire la différence proprement entre l'ensemble des contrôleurs et l'accès
 * local à la base de données interne.
 *
 * @author Christophe
 * @version 2.0
 */
public class Controleur {

    private static Controleur instance;
    private static AccesLocal accesLocal;
    private static ArrayList<Client> listeClients;

    /**
     * Le constructeur crée une nouvelle forme de Controleur.
     */
    public Controleur() {
        super();
        this.listeClients = Controleur.accesLocal.tousLesClients();
    }

    /**
     * Méthode statique qui permet de récupérer la seule et unique instance de cette classe.
     *
     * @param context le contexte
     * @return l'unique instance de Controleur
     */
    public static final Controleur getInstance(Context context) {
        if (Controleur.instance == null) {
            Controleur.accesLocal = new AccesLocal(context);
            Controleur.instance = new Controleur();
        }

        return Controleur.instance;
    }

    /**
     * Demande à l'accès local d'ajouter un client.
     *
     * @param client le client à ajouter
     */
    public void creerClient(Client client) {
        if (client != null) {
            Controleur.accesLocal.ajoutClient(client);
            this.listeClients.add(Controleur.accesLocal.dernierClient());
        }
    }

    /**
     * Demande à l'accesLocal de supprimer un client.
     *
     * @param position la position dans la liste du client à supprimer
     */
    public void supprimerClient(int position) {
        /*
        if (client != null) {
            Controleur.accesLocal.supprimerClient();
            this.listeClients.remove(position);
        }*/
    }

    /**
     * Retourne la liste de tous les clients.
     *
     * @return le liste de tous les clients.
     */
    public static ArrayList<Client> getListeClients() {
        return listeClients;
    }
}
