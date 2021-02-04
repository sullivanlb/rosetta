package com.example.rosetta.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.rosetta.utils.MySQLiteOpenHelper;
import com.google.android.material.badge.BadgeDrawable;

import java.util.ArrayList;

/**
 * Cette classe permet la liaison propre entre le {@link com.example.rosetta.controller.Controleur}
 * et la base de données interne. Elle ajoute/modifie/supprime directement dans les tables.
 *
 * @author Christophe
 * @version 2.0
 */
public class AccesLocal {

    private String name;
    private int version;
    private MySQLiteOpenHelper accesBD;
    private SQLiteDatabase bd;

    /**
     * Le constructeur crée une nouvelle forme de AccesLocal.
     *
     * @param context le contexte
     */
    public AccesLocal(Context context) {
        this.name = "RosettaInterne.sqlite";
        this.version = 1;
        this.accesBD = new MySQLiteOpenHelper(context, this.name, null, this.version);
    }

    /**
     * Ajoute un nouveau client.
     *
     * @param client le nouveau client
     */
    public void ajoutClient(Client client) {
        this.bd = this.accesBD.getWritableDatabase();
        String requete = "INSERT INTO Client (nomClient, prenomClient, adresseClient, emailClient, telClient, sexeClient) values";
        requete += "(\"" + client.getNomClient() + "\", \"" + client.getPrenomClient() + "\", \"" + client.getAdresseClient() + "\", \"" +
                client.getEmailClient() + "\", \"" + client.getTelClient() + "\", \"" + client.getSexeClient() + "\")";
        this.bd.execSQL(requete);
    }

    /**
     * Modifie les données d'un client existant.
     *
     * @param client le client comportant les nouvelles données
     */
    public void modifierClient(Client client) {
        this.bd = this.accesBD.getWritableDatabase();
        String requete = "UPDATE Client SET nomClient = \"" + client.getNomClient() + "\", " +
                "prenomClient = \"" + client.getPrenomClient() + "\", " +
                "adresseClient = \"" + client.getAdresseClient() + "\", " +
                "emailClient = \"" + client.getEmailClient() + "\"," +
                "telClient = \"" + client.getTelClient() + "\"," +
                "sexeClient = \"" + client.getSexeClient() + "\"" +
                "WHERE idClient = \"" + client.getIdClient() + "\"";
        this.bd.execSQL(requete);
    }

    /**
     * Supprimer un client.
     *
     * @param id le numéro d'identification du client à supprimer
     */
    public void supprimerClient(int id) {
        this.bd = this.accesBD.getWritableDatabase();
        String requete = "DELETE FROM Client WHERE idClient = \"" + id + "\"";
        this.bd.execSQL(requete);
    }

    /**
     * Récupère le dernier client ajouté.
     *
     * @return le dernier client ajouté
     */
    public Client dernierClient() {
        this.bd = this.accesBD.getReadableDatabase();
        Client client = null;
        String requete = "SELECT * FROM Client";
        Cursor curseur = this.bd.rawQuery(requete, null);
        curseur.moveToLast();

        if (!curseur.isAfterLast()) {
            int id = curseur.getInt(0);
            String nom = curseur.getString(1);
            String prenom = curseur.getString(2);
            String adresse = curseur.getString(3);
            String email = curseur.getString(4);
            String tel = curseur.getString(5);
            Sexe sexe = Sexe.valueOf(curseur.getString(6));

            client = new Client(id, nom, prenom, adresse, email, tel, sexe);
        }

        curseur.close();
        return client;
    }

    /**
     * Récupère tous les clients.
     *
     * @return la liste de tous les clients
     */
    public ArrayList<Client> tousLesClients() {
        this.bd = this.accesBD.getReadableDatabase();
        ArrayList<Client> listeClients = new ArrayList<Client>();
        Client client = null;
        String requete = "SELECT * FROM Client";
        Cursor curseur = this.bd.rawQuery(requete, null);
        curseur.moveToFirst();

        while (!curseur.isAfterLast()) {
            int id = curseur.getInt(0);
            String nom = curseur.getString(1);
            String prenom = curseur.getString(2);
            String adresse = curseur.getString(3);
            String email = curseur.getString(4);
            String tel = curseur.getString(5);
            Sexe sexe = Sexe.valueOf(curseur.getString(6));

            client = new Client(id, nom, prenom, adresse, email, tel, sexe);
            listeClients.add(client);

            curseur.moveToNext();
        }

        curseur.close();
        return listeClients;
    }

}
