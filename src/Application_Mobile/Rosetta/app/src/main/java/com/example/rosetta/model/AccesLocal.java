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
 * @author Christophe, Lucy
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
//=================================== Client ===============================================================================
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
    // ============================================ Scénario ==================================================================

    /**
     * Ajouter un nouveau Scénario
     *
     * @param scenario, le nouveau scenario
     */

    public void ajoutScenario(Scenario scenario) {
        this.bd = this.accesBD.getWritableDatabase();
        String requete = "INSERT INTO Scenario (nomScenario) values";
        requete += "(\"" + scenario.getNomScenario() + "\")";
        this.bd.execSQL(requete);
    }

    /**
     * Modifie les données d'un scenario existant.
     *
     * @param scenario le scenario comportant les nouvelles données
     */
    public void modifierScenario(Scenario scenario) {
        this.bd = this.accesBD.getWritableDatabase();
        String requete = "UPDATE Scenario SET nomScenario = \"" + scenario.getNomScenario() + "\"" +
                "WHERE idClient = \"" + scenario.getIdScenario()+ "\"";
        this.bd.execSQL(requete);
    }

    /**
     * Supprimer un scenario.
     *
     * @param id l'identifiant du scenario à supprimer
     */
    public void supprimerScenario(int id) {
        this.bd = this.accesBD.getWritableDatabase();
        String requete = "DELETE FROM Scenario WHERE idScenario = \"" + id + "\"";
        this.bd.execSQL(requete);
    }

    /**
     * Récupère le dernier scenario ajouté.
     *
     * @return le dernier scenario ajouté
     */
    public Scenario dernierScenario() {
        this.bd = this.accesBD.getReadableDatabase();
        Scenario scenario = null;
        String requete = "SELECT * FROM Scenario";
        Cursor curseur = this.bd.rawQuery(requete, null);
        curseur.moveToLast();

        if (!curseur.isAfterLast()) {
            int id = curseur.getInt(0);
            String nom = curseur.getString(1);

            scenario = new Scenario(id, nom);
        }

        curseur.close();
        return scenario;
    }


    /**
     * Récupère tous les scenarios.
     *
     * @return la liste de tous les scenarios.
     */
    public ArrayList<Scenario> tousLesScenarios() {
        this.bd = this.accesBD.getReadableDatabase();
        ArrayList<Scenario> listeScenarios = new ArrayList<Scenario>();
        Scenario scenario = null;
        String requete = "SELECT * FROM Scenario";
        Cursor curseur = this.bd.rawQuery(requete, null);
        curseur.moveToFirst();

        while (!curseur.isAfterLast()) {
            int id = curseur.getInt(0);
            String nom = curseur.getString(1);

            scenario = new Scenario(id, nom);
            listeScenarios.add(scenario);
            curseur.moveToNext();
        }

        curseur.close();
        return listeScenarios;
    }

    //============================ Composant ===================================================================================

    /**
     * Ajouter un nouveau Composant
     *
     * @param composant, le nouveau composant
     */

    public void ajoutComposant(Composant composant) {
        this.bd = this.accesBD.getWritableDatabase();
        String requete = "INSERT INTO Composant (nomComposant, uniteComposant, prixComposant) values";
        requete += "(\"" + composant.getNomComposant()+ "\", \"" + composant.getUniteComposant()+ "\", \"" + composant.getPrixComposant() + "\")";
        this.bd.execSQL(requete);
    }

    /**
     * Modifie les données d'un composant existant.
     *
     * @param composant le composant comportant les nouvelles données
     */
    public void modifierComposant(Composant composant) {
        this.bd = this.accesBD.getWritableDatabase();
        String requete = "UPDATE Composant SET nomComposant = \"" + composant.getNomComposant() + "\", " +
                "uniteComposant = \"" + composant.getUniteComposant() + "\", " +
                "prixComposant = \"" + composant.getPrixComposant() + "\"" +
                "WHERE idComposant = \"" + composant.getIdComposant() + "\"";
        this.bd.execSQL(requete);
    }

    /**
     * Récupère le dernier composant ajouté.
     *
     * @return le dernier composant ajouté
     */
    public Composant dernierComposant() {
        this.bd = this.accesBD.getReadableDatabase();
        Composant composant = null;
        String requete = "SELECT * FROM Composant";
        Cursor curseur = this.bd.rawQuery(requete, null);
        curseur.moveToLast();

        if (!curseur.isAfterLast()) {
            int id = curseur.getInt(0);
            String nom = curseur.getString(1);
            String unite = curseur.getString(2);
            double prix  = curseur.getDouble(3);

            composant = new Composant(id, nom, unite, prix);
        }

        curseur.close();
        return composant;
    }

    /**
     * Récupère tous les composants.
     *
     * @return la liste de tous les composants.
     */
    public ArrayList<Composant> tousLesComposants() {
        this.bd = this.accesBD.getReadableDatabase();
        ArrayList<Composant> listeComposants = new ArrayList<Composant>();
        Composant composant = null;
        String requete = "SELECT * FROM Composant";
        Cursor curseur = this.bd.rawQuery(requete, null);
        curseur.moveToFirst();

        while (!curseur.isAfterLast()) {
            int id = curseur.getInt(0);
            String nom = curseur.getString(1);
            String unite = curseur.getString(2);
            double prix = curseur.getDouble(3);

            composant = new Composant(id, nom, unite, prix);
            listeComposants.add(composant);
            curseur.moveToNext();
        }

        curseur.close();
        return listeComposants;
    }


    //============================== Pack =================================================================================

    /**
     * Ajouter un nouveau Pack
     *
     * @param pack, le nouveau pack
     */

    public void ajoutPack(Pack pack) {
        this.bd = this.accesBD.getWritableDatabase();
        String requete = "INSERT INTO Pack (nomPack ) values";
        requete += "(\"" + pack.getNomPack()+ "\")";
        this.bd.execSQL(requete);
    }

    /**
     * Modifie les données d'un pack existant.
     *
     * @param pack le pack comportant les nouvelles données
     */
    public void modifierPack(Pack pack) {
        this.bd = this.accesBD.getWritableDatabase();
        String requete = "UPDATE Pack SET nomPack = \"" + pack.getNomPack() + "\"" +
                "WHERE idPack = \"" + pack.getIdPack() + "\"";
        this.bd.execSQL(requete);
    }

    /**
     * Récupère le dernier pack ajouté.
     *
     * @return le dernier pack ajouté
     */
    public Pack dernierPack() {
        this.bd = this.accesBD.getReadableDatabase();
        Pack pack = null;
        String requete = "SELECT * FROM Pack";
        Cursor curseur = this.bd.rawQuery(requete, null);
        curseur.moveToLast();

        if (!curseur.isAfterLast()) {
            int id = curseur.getInt(0);
            String nom = curseur.getString(1);

            pack = new Pack(id, nom);
        }

        curseur.close();
        return pack;
    }

    /**
     * Récupère tous les packs.
     *
     * @return la liste de tous les packs.
     */
    public ArrayList<Pack> tousLesPacks() {
        this.bd = this.accesBD.getReadableDatabase();
        ArrayList<Pack> listePacks = new ArrayList<Pack>();
        Pack pack = null;
        String requete = "SELECT * FROM Pack";
        Cursor curseur = this.bd.rawQuery(requete, null);
        curseur.moveToFirst();

        while (!curseur.isAfterLast()) {
            int id = curseur.getInt(0);
            String nom = curseur.getString(1);

            pack = new Pack(id, nom);
            listePacks.add(pack);

            curseur.moveToNext();
        }

        curseur.close();
        return listePacks;
    }

    //============================== Devis =============================================================

    /**
     * Ajoute un nouveau devis.
     *
     * @param devis le nouveau devis.
     */
    public void ajoutDevis(Devis devis) {
        this.bd = this.accesBD.getWritableDatabase();
        String requete = "INSERT INTO Devis (nomDevis, descriptionDevis, dureeDevis, dateEditionDevis, dateTravauxDevis) values";
        requete += "(\"" + devis.getNomDevis() + "\", \"" + devis.getDescriptionDevis() + "\", \"" + devis.getDureeDevis() + "\", \"" +
                devis.getDateEditionDevis() + "\", \"" + devis.getDateTravauxDevis() + "\")";
        this.bd.execSQL(requete);
    }

    /**
     * Modifie les données d'un devis existant.
     *
     * @param devis le devis comportant les nouvelles données.
     */
    public void modifierDevis(Devis devis) {
        this.bd = this.accesBD.getWritableDatabase();
        String requete = "UPDATE Devis SET nomDevis = \"" + devis.getNomDevis() + "\", " +
                "descriptionDevis = \"" + devis.getDescriptionDevis() + "\", " +
                "dureeDevis = \"" + devis.getDureeDevis() + "\", " +
                "dateEditionDevis = \"" + devis.getDateEditionDevis() + "\"," +
                "dateTravauxDevis = \"" + devis.getDateTravauxDevis() + "\"";
        this.bd.execSQL(requete);
    }

    /**
     * Supprimer un devis.
     *
     * @param id le numéro d'identification du devis à supprimer
     */
    public void supprimerDevis(int id) {
        this.bd = this.accesBD.getWritableDatabase();
        String requete = "DELETE FROM Devis WHERE idDevis = \"" + id + "\"";
        this.bd.execSQL(requete);
    }

    /**
     * Récupère le dernier devis ajouté.
     *
     * @return le dernier  devis ajouté
     */
    public Devis dernierDevis() {
        this.bd = this.accesBD.getReadableDatabase();
        Devis devis = null;
        String requete = "SELECT * FROM Devis";
        Cursor curseur = this.bd.rawQuery(requete, null);
        curseur.moveToLast();

        if (!curseur.isAfterLast()) {
            int id = curseur.getInt(0);
            String nom = curseur.getString(1);
            String description = curseur.getString(2);
            String duree = curseur.getString(3);
            String dateEdition = curseur.getString(4);
            String dateTravaux = curseur.getString(5);

            devis = new Devis(id, nom, description, duree, dateEdition, dateTravaux);
        }

        curseur.close();
        return devis;
    }

    /**
     * Récupère tous les devis.
     *
     * @return la liste de tous les devis.
     */
    public ArrayList<Devis> tousLesDevis() {
        this.bd = this.accesBD.getReadableDatabase();
        ArrayList<Devis> listeDevis = new ArrayList<Devis>();
        Devis devis = null;
        String requete = "SELECT * FROM Devis";
        Cursor curseur = this.bd.rawQuery(requete, null);
        curseur.moveToFirst();

        while (!curseur.isAfterLast()) {
            int id = curseur.getInt(0);
            String nom = curseur.getString(1);
            String description = curseur.getString(2);
            String duree = curseur.getString(3);
            String dateEdition = curseur.getString(4);
            String dateTravaux = curseur.getString(5);

            devis = new Devis(id, nom, description, duree, dateEdition, dateTravaux);
            listeDevis.add(devis);
            curseur.moveToNext();
        }

        curseur.close();
        return listeDevis;
    }

    //=========================== Appartient Pack Composant =========================================

    /**
     * Ajoute un pack et un composant dans AppartientPC.
     *
     * @param pack l'id du pack.
     * @param  composant l'id composant
     */
    public void ajoutAppartientPC(Pack pack, Composant composant, int quantite) {
        this.bd = this.accesBD.getWritableDatabase();
        String requete = "INSERT INTO AppartientPC (unPack, unComposant, quantite) values";
        requete += "(\"" + pack.getIdPack() + "\", \"" + composant.getIdComposant() + "\", \""+ quantite +"\")";
        this.bd.execSQL(requete);
    }
}
