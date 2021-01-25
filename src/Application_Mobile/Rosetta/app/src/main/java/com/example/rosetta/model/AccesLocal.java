package com.example.rosetta.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.rosetta.utils.MySQLiteOpenHelper;
import com.google.android.material.badge.BadgeDrawable;

public class AccesLocal {

    private String name;
    private int version;
    private MySQLiteOpenHelper accesBD;
    private SQLiteDatabase bd;

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
        requete += "(" + client.getNomClient() + ", " + client.getPrenomClient() + ", " + client.getAdresseClient() + ", " +
                client.getEmailClient() + ", " + client.getTelClient() + ", \"" + client.getSexeClient() + "\")";
        this.bd.execSQL(requete);
    }

    /**
     * Récupère le dernier client ajouté.
     *
     * @return le dernier client ajouté
     */
    public Client recupereClient() {
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
     * Ajoute un nouveau composant.
     *
     * @param composant le nouveau composant
     */
    public void ajoutComposant(Composant composant) {
        this.bd = this.accesBD.getWritableDatabase();
        String requete = "INSERT INTO Composant (nomComposant, uniteComposant, prixComposant) values";
        requete += "(" + composant.getNomComposant() + ", " + composant.getUniteComposant() + ", " + composant.getPrixComposant() + ")";
        this.bd.execSQL(requete);
    }

    /**
     * Ajoute un nouveau devis.
     *
     * @param devis le nouveau devis
     */
    public void ajoutClient(Devis devis) {
        this.bd = this.accesBD.getWritableDatabase();
        String requete = "INSERT INTO Composant (nomDevis) values";
        requete += "(" + devis.getNomDevis() + ")";
        this.bd.execSQL(requete);
    }

    /**
     * Ajoute un nouveau client.
     *
     * @param client
     */
    public void ajoutClient(Client client) {
        this.bd = this.accesBD.getWritableDatabase();
        String requete = "INSERT INTO Client (nomClient, prenomClient, adresseClient, emailClient, telClient, sexeClient) values";
        requete += "(" + client.getNomClient() + ", " + client.getPrenomClient() + ", " + client.getAdresseClient() + ", " +
                client.getEmailClient() + ", " + client.getTelClient() + ", \"" + client.getSexeClient() + "\")";
        this.bd.execSQL(requete);
    }

    /**
     * Ajoute un nouveau client.
     *
     * @param client
     */
    public void ajoutClient(Client client) {
        this.bd = this.accesBD.getWritableDatabase();
        String requete = "INSERT INTO Client (nomClient, prenomClient, adresseClient, emailClient, telClient, sexeClient) values";
        requete += "(" + client.getNomClient() + ", " + client.getPrenomClient() + ", " + client.getAdresseClient() + ", " +
                client.getEmailClient() + ", " + client.getTelClient() + ", \"" + client.getSexeClient() + "\")";
        this.bd.execSQL(requete);
    }

    /**
     * Ajoute un nouveau client.
     *
     * @param client
     */
    public void ajoutClient(Client client) {
        this.bd = this.accesBD.getWritableDatabase();
        String requete = "INSERT INTO Client (nomClient, prenomClient, adresseClient, emailClient, telClient, sexeClient) values";
        requete += "(" + client.getNomClient() + ", " + client.getPrenomClient() + ", " + client.getAdresseClient() + ", " +
                client.getEmailClient() + ", " + client.getTelClient() + ", \"" + client.getSexeClient() + "\")";
        this.bd.execSQL(requete);
    }

}
