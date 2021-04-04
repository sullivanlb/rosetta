package com.example.rosetta.model;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.rosetta.controller.Controleur;
import com.example.rosetta.controller.ControleurEnregistrerNouveauClient;
import com.example.rosetta.utils.MySQLiteOpenHelper;
import com.example.rosetta.utils.MySingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Cette classe permet la liaison propre entre le {@link com.example.rosetta.controller.Controleur}
 * et la base de données interne. Elle ajoute/modifie/supprime directement dans les tables.
 *
 * @author Christophe, Lucy, Alice
 * @version 2.0
 */
public class AccesLocal {

    private String name;
    private int version;
    private MySQLiteOpenHelper accesBD;
    private SQLiteDatabase bd;
    private Context context;

    /**
     * Le constructeur crée une nouvelle forme de AccesLocal.
     *
     * @param context le contexte
     */
    public AccesLocal(Context context) {
        this.name = "RosettaInterne.sqlite";
        this.version = 1;
        this.accesBD = new MySQLiteOpenHelper(context, this.name, null, this.version);
        this.context = context;
    }
//=================================== Client ===============================================================================
    /**
     * Ajoute un nouveau client.
     *
     * @param client le nouveau client
     */
    public void ajoutClient(Client client) {
        this.bd = this.accesBD.getWritableDatabase();
        String requete = "INSERT INTO Client (syncStatus, nomClient, prenomClient, adresseClient, emailClient, telClient, sexeClient) values";
        requete += "(\"" + client.getSyncStatus() + "\", \"" + client.getNomClient() + "\", \"" + client.getPrenomClient() + "\", \"" +
                client.getAdresseClient() + "\", \"" + client.getEmailClient() + "\", \"" + client.getTelClient() + "\", \"" + client.getSexeClient() + "\")";
        this.bd.execSQL(requete);
    }


    /**
     * Modifie les données d'un client existant.
     *
     * @param client le client comportant les nouvelles données
     */
    public void modifierClient(Client client) {
        this.bd = this.accesBD.getWritableDatabase();
        String requete = "UPDATE Client SET syncStatus = \"" + client.getSyncStatus() + "\", " +
                "nomClient = \"" + client.getNomClient() + "\", " +
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
            int syncStatus = curseur.getInt(1);
            String nom = curseur.getString(2);
            String prenom = curseur.getString(3);
            String adresse = curseur.getString(4);
            String email = curseur.getString(5);
            String tel = curseur.getString(6);
            Sexe sexe = Sexe.valueOf(curseur.getString(7));

            client = new Client(id, syncStatus, nom, prenom, adresse, email, tel, sexe);
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
            int syncStatus = curseur.getInt(1);
            String nom = curseur.getString(2);
            String prenom = curseur.getString(3);
            String adresse = curseur.getString(4);
            String email = curseur.getString(5);
            String tel = curseur.getString(6);
            Sexe sexe = Sexe.valueOf(curseur.getString(7));

            client = new Client(id, syncStatus, nom, prenom, adresse, email, tel, sexe);
            listeClients.add(client);

            curseur.moveToNext();
        }

        curseur.close();
        return listeClients;
    }

    /**
     * Essaye de synchroniser le client non synchronisé lorsque la tablette est connectée à un réseau
     * @param cenc
     */
    public void synchroniserClients(ControleurEnregistrerNouveauClient cenc) {
        this.bd = this.accesBD.getReadableDatabase();
        String requete = "SELECT * FROM Client";
        Cursor curseur = this.bd.rawQuery(requete, null);
        curseur.moveToFirst();

        while(curseur.moveToNext()) {
            int syncStatus = curseur.getInt(curseur.getColumnIndex("syncStatus"));
            if (syncStatus == 1) {
                int id = curseur.getInt(0);
                String nom = curseur.getString(2);
                String prenom = curseur.getString(3);
                String adresse = curseur.getString(4);
                String email = curseur.getString(5);
                String tel = curseur.getString(6);
                Sexe sexe = Sexe.valueOf(curseur.getString(7));

                StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://192.168.1.49/api/client/ajoutClient",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    String theResponse = jsonObject.getString("response");

                                    // Si le client a bien été enregistré dans la base de données externe
                                    if (theResponse.equalsIgnoreCase("OK")) {
                                        Client client = new Client(id, 0, nom, prenom, adresse, ((email != null) ? email : ""), ((tel != null) ? tel : ""), sexe);
                                        Controleur.getInstance(context).modifierClient(client);
                                        cenc.getClientFragment().actualiserListeClients();
                                        context.sendBroadcast(new Intent(".utils.NetworkMonitor"));
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("nom", nom);
                        params.put("prenom", prenom);
                        params.put("adresse", adresse);
                        params.put("email", ((email != null) ? email : ""));
                        params.put("tel", ((tel != null) ? tel : ""));
                        params.put("sexe", String.valueOf(sexe));

                        return params;
                    }
                };

                MySingleton.getInstance(context).addToRequestQueue(stringRequest);
            }
        }
        curseur.close();
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

    //============================ Question ========================================================

    /**
     * Ajouter un nouvelle question
     *
     * @param question, le nouvelle pquestion
     */

    public void ajoutQuestion(Question question) {
        this.bd = this.accesBD.getWritableDatabase();
        String requete = "INSERT INTO Question (nomQuestion) values";
        requete += "(\"" + question.getNomQuestion()+ "\")";
        this.bd.execSQL(requete);
    }

    /**
     * Supprimer une question.
     *
     * @param id le numéro d'identification de la question à supprimer
     */
    public void supprimerQuetion(int id) {
        this.bd = this.accesBD.getWritableDatabase();
        String requete = "DELETE FROM Question WHERE idQuestion = \"" + id + "\"";
        this.bd.execSQL(requete);
    }

    /**
     * Récupère la dernière question ajoutée.
     *
     * @return la dernière question ajoutée
     */
    public Question derniereQuestion() {
        this.bd = this.accesBD.getReadableDatabase();
        Question question = null;
        String requete = "SELECT * FROM Question";
        Cursor curseur = this.bd.rawQuery(requete, null);
        curseur.moveToLast();

        if (!curseur.isAfterLast()) {
            int id = curseur.getInt(0);
            String nom = curseur.getString(1);

            question = new Question(id, nom);
        }

        curseur.close();
        return question;
    }

    /**
     * Récupère tous les questions.
     *
     * @return la liste de tous les questions.
     */
    public ArrayList<Question> tousLesQuestions() {
        this.bd = this.accesBD.getReadableDatabase();
        ArrayList<Question> listeQuestions = new ArrayList<Question>();
        Question question = null;
        String requete = "SELECT * FROM Question";
        Cursor curseur = this.bd.rawQuery(requete, null);
        curseur.moveToFirst();

        while (!curseur.isAfterLast()) {
            int id = curseur.getInt(0);
            String nom = curseur.getString(1);

            question = new Question(id, nom);
            listeQuestions.add(question);

            curseur.moveToNext();
        }

        curseur.close();
        return listeQuestions;
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

    /**
     * Récupère tous les composants de la table appartient PC.
     *
     * @return la liste de toutes les liaisons
     */
    public ArrayList<Integer> tousLesElementsPC_Composants() {
        this.bd = this.accesBD.getReadableDatabase();
        ArrayList<Integer> tousLesElements = new ArrayList<Integer>();
        String requete = "SELECT * FROM AppartientPC";
        Cursor curseur = this.bd.rawQuery(requete, null);
        curseur.moveToFirst();

        while (!curseur.isAfterLast()) {
            int idComposant = curseur.getInt(1);

            tousLesElements.add(idComposant);
            curseur.moveToNext();
        }

        curseur.close();
        return tousLesElements;
    }

    /**
     * Récupère tous les packs de la table appartient PC.
     *
     * @return la liste de toutes les liaisons
     */
    public ArrayList<Integer> tousLesElementsPC_Packs() {
        this.bd = this.accesBD.getReadableDatabase();
        ArrayList<Integer> tousLesElements = new ArrayList<Integer>();
        String requete = "SELECT * FROM AppartientPC";
        Cursor curseur = this.bd.rawQuery(requete, null);
        curseur.moveToFirst();

        while (!curseur.isAfterLast()) {
            int idPack = curseur.getInt(0);

            tousLesElements.add(idPack);
            curseur.moveToNext();
        }

        curseur.close();
        return tousLesElements;
    }

    /**
     * Récupère tous les composants de la table appartient PC.
     *
     * @return la liste de toutes les liaisons
     */
    public ArrayList<Integer> tousLesElementsPC_Quantite() {
        this.bd = this.accesBD.getReadableDatabase();
        ArrayList<Integer> tousLesElements = new ArrayList<Integer>();
        String requete = "SELECT * FROM AppartientPC";
        Cursor curseur = this.bd.rawQuery(requete, null);
        curseur.moveToFirst();

        while (!curseur.isAfterLast()) {
            int quantite = curseur.getInt(2);

            tousLesElements.add(quantite);
            curseur.moveToNext();
        }

        curseur.close();
        return tousLesElements;
    }


    /**
     * Supprime toutes les liaisons dans la table AppartientPC correspondant à l'id du pack
     * @param idPack
     */
    public void supprimerPackPC(int idPack){
        this.bd = this.accesBD.getWritableDatabase();
        String requete = "DELETE FROM AppartientPC WHERE unPack = \"" + idPack + "\"";
        this.bd.execSQL(requete);
    }

    //=========================== Appartient Scenario Pack =========================================

    /**
     * Ajoute un pack et un composant dans AppartientSP.
     *
     * @param scenario le scenario.
     * @param  pack le pack
     * @param  quantite la quantité
     */
    public void ajoutAppartientSP(Scenario scenario, Pack pack, int quantite) {
        this.bd = this.accesBD.getWritableDatabase();
        String requete = "INSERT INTO AppartientSP (unScenario, unPack, quantite) values";
        requete += "(\"" + scenario.getIdScenario() + "\", \"" + pack.getIdPack() + "\", \""+ quantite +"\")";
        this.bd.execSQL(requete);
    }


    /**
     * @return tous les scénarios de la table AppartientSP
     */
    public ArrayList<Integer> tousLesElementsSP_Scenarios() {
        this.bd = this.accesBD.getReadableDatabase();
        ArrayList<Integer> tousLesElements = new ArrayList<Integer>();
        String requete = "SELECT * FROM AppartientSP";
        Cursor curseur = this.bd.rawQuery(requete, null);
        curseur.moveToFirst();

        while (!curseur.isAfterLast()) {
            int idScenario = curseur.getInt(0);

            tousLesElements.add(idScenario);
            curseur.moveToNext();
        }

        curseur.close();
        return tousLesElements;
    }


    /**
     * @return tous les packs de la table AppartientSP
     */
    public ArrayList<Integer> tousLesElementsSP_Pack() {
        this.bd = this.accesBD.getReadableDatabase();
        ArrayList<Integer> tousLesElements = new ArrayList<Integer>();
        String requete = "SELECT * FROM AppartientSP";
        Cursor curseur = this.bd.rawQuery(requete, null);
        curseur.moveToFirst();

        while (!curseur.isAfterLast()) {
            int idPack = curseur.getInt(1);

            tousLesElements.add(idPack);
            curseur.moveToNext();
        }

        curseur.close();
        return tousLesElements;
    }


    /**
     * @return toutes les quantites de la table AppartientSP
     */
    public ArrayList<Integer> tousLesElementsSP_Quantite() {
        this.bd = this.accesBD.getReadableDatabase();
        ArrayList<Integer> tousLesElements = new ArrayList<Integer>();
        String requete = "SELECT * FROM AppartientSP";
        Cursor curseur = this.bd.rawQuery(requete, null);
        curseur.moveToFirst();

        while (!curseur.isAfterLast()) {
            int quantite = curseur.getInt(2);

            tousLesElements.add(quantite);
            curseur.moveToNext();
        }

        curseur.close();
        return tousLesElements;
    }

    /**
     * Détruit toutes les liaisons correspondant à l'id du scénario passé en paramètre dans la table AppartientSP
     * @param idScenario
     */
    public void supprimerScenarioSP(int idScenario){
        this.bd = this.accesBD.getWritableDatabase();
        String requete = "DELETE FROM AppartientSP WHERE unScenario = \"" + idScenario + "\"";
        this.bd.execSQL(requete);
    }

    //================================ Appartient Scenario Composant =============================

    /**
     * Ajoute un pack et un composant dans AppartientSC.
     *
     * @param scenario le scenario.
     * @param  composant le composant.
     * @param  quantite la quantité.
     */
    public void ajoutAppartientSC(Scenario scenario, Composant composant, int quantite) {
        this.bd = this.accesBD.getWritableDatabase();
        String requete = "INSERT INTO AppartientSC (unScenario, unComposant, quantite) values";
        requete += "(\"" + scenario.getIdScenario() + "\", \"" + composant.getIdComposant() + "\", \""+ quantite +"\")";
        this.bd.execSQL(requete);
    }

    /**
     * Détruit toutes les liaisons correspondant à l'id du scénario passé en paramètre dans la table AppartientSC
     * @param idScenario
     */
    public void supprimerScenarioSC(int idScenario){
        this.bd = this.accesBD.getWritableDatabase();
        String requete = "DELETE FROM AppartientSC WHERE unScenario = \"" + idScenario + "\"";
        this.bd.execSQL(requete);
    }

    /**
     * @return tous les composants de la table AppartientSC
     */
    public ArrayList<Integer> tousLesElementsSC_Composant() {
        this.bd = this.accesBD.getReadableDatabase();
        ArrayList<Integer> tousLesElements = new ArrayList<Integer>();
        String requete = "SELECT * FROM AppartientSC";
        Cursor curseur = this.bd.rawQuery(requete, null);
        curseur.moveToFirst();

        while (!curseur.isAfterLast()) {
            int idComposant = curseur.getInt(1);

            tousLesElements.add(idComposant);
            curseur.moveToNext();
        }

        curseur.close();
        return tousLesElements;
    }

    /**
     * @return tous les scenarios de la table AppartientSC
     */
    public ArrayList<Integer> tousLesElementsSC_Scenarios() {
        this.bd = this.accesBD.getReadableDatabase();
        ArrayList<Integer> tousLesElements = new ArrayList<Integer>();
        String requete = "SELECT * FROM AppartientSC";
        Cursor curseur = this.bd.rawQuery(requete, null);
        curseur.moveToFirst();

        while (!curseur.isAfterLast()) {
            int idScenario = curseur.getInt(0);

            tousLesElements.add(idScenario);
            curseur.moveToNext();
        }

        curseur.close();
        return tousLesElements;
    }

    /**
     * @return toutes les quantites de la table AppartientSC
     */
    public ArrayList<Integer> tousLesElementsSC_Quantite() {
        this.bd = this.accesBD.getReadableDatabase();
        ArrayList<Integer> tousLesElements = new ArrayList<Integer>();
        String requete = "SELECT * FROM AppartientSC";
        Cursor curseur = this.bd.rawQuery(requete, null);
        curseur.moveToFirst();

        while (!curseur.isAfterLast()) {
            int quantite = curseur.getInt(2);

            tousLesElements.add(quantite);
            curseur.moveToNext();
        }

        curseur.close();
        return tousLesElements;
    }

    //========================== Appartient Scenario Question ====================================
    /**
     * Ajoute un scenario et une question dans AppartientSQ.
     *
     * @param scenario le scenario.
     * @param  question  la question.
     */
    public void ajoutAppartientSQ(Scenario scenario, Question question) {
        this.bd = this.accesBD.getWritableDatabase();
        String requete = "INSERT INTO AppartientSQ (unScenario, uneQuestion) values";
        requete += "(\"" + scenario.getIdScenario() + "\", \"" + question.getIdQuestion() + "\")";
        this.bd.execSQL(requete);
    }

    /**
     * Détruit toutes les liaisons correspondant à l'id du scénario passé en paramètre dans la table AppartientSQ
     * @param idScenario
     */
    public void supprimerScenarioSQ(int idScenario){
        this.bd = this.accesBD.getWritableDatabase();
        String requete = "DELETE FROM AppartientSQ WHERE unScenario = \"" + idScenario + "\"";
        this.bd.execSQL(requete);
    }

    /**
     * @return toutes les questions de la table AppartientSQ
     */
    public ArrayList<Integer> tousLesElementsSQ_Question() {
        this.bd = this.accesBD.getReadableDatabase();
        ArrayList<Integer> tousLesElements = new ArrayList<Integer>();
        String requete = "SELECT * FROM AppartientSQ";
        Cursor curseur = this.bd.rawQuery(requete, null);
        curseur.moveToFirst();

        while (!curseur.isAfterLast()) {
            int idQuestion = curseur.getInt(1);

            tousLesElements.add(idQuestion);
            curseur.moveToNext();
        }

        curseur.close();
        return tousLesElements;
    }

    /**
     * @return tous les scenarios de la table AppartientSQ
     */
    public ArrayList<Integer> tousLesElementsSQ_Scenario() {
        this.bd = this.accesBD.getReadableDatabase();
        ArrayList<Integer> tousLesElements = new ArrayList<Integer>();
        String requete = "SELECT * FROM AppartientSQ";
        Cursor curseur = this.bd.rawQuery(requete, null);
        curseur.moveToFirst();

        while (!curseur.isAfterLast()) {
            int idScenario = curseur.getInt(0);

            tousLesElements.add(idScenario);
            curseur.moveToNext();
        }

        curseur.close();
        return tousLesElements;
    }


    //=========================== Appartient Devis Pack =========================================

    /**
     * Ajoute un devis et un pack dans AppartientDP.
     *
     * @param devis le devis.
     * @param  pack le pack
     * @param quantite la quantite
     */
    public void ajoutAppartientDP(Devis devis, Pack pack, int quantite) {
        this.bd = this.accesBD.getWritableDatabase();
        String requete = "INSERT INTO AppartientDP (unDevis, unPack, quantite) values";
        requete += "(\"" + devis.getIdDevis() + "\", \"" + pack.getIdPack() + "\", \""+ quantite +"\")";
        this.bd.execSQL(requete);
    }

    /**
     * @return tous les devis de la table AppartientDP
     */
    public ArrayList<Integer> tousLesElementsDP_Devis() {
        this.bd = this.accesBD.getReadableDatabase();
        ArrayList<Integer> tousLesElements = new ArrayList<Integer>();
        String requete = "SELECT * FROM AppartientDP";
        Cursor curseur = this.bd.rawQuery(requete, null);
        curseur.moveToFirst();

        while (!curseur.isAfterLast()) {
            int idDevis = curseur.getInt(0);

            tousLesElements.add(idDevis);
            curseur.moveToNext();
        }

        curseur.close();
        return tousLesElements;
    }

    /**
     * @return tous les packs de la table AppartientDP
     */
    public ArrayList<Integer> tousLesElementsDP_Pack() {
        this.bd = this.accesBD.getReadableDatabase();
        ArrayList<Integer> tousLesElements = new ArrayList<Integer>();
        String requete = "SELECT * FROM AppartientDP";
        Cursor curseur = this.bd.rawQuery(requete, null);
        curseur.moveToFirst();

        while (!curseur.isAfterLast()) {
            int idPack = curseur.getInt(1);

            tousLesElements.add(idPack);
            curseur.moveToNext();
        }

        curseur.close();
        return tousLesElements;
    }

    /**
     * @return toutes les quantites de la table AppartientDP
     */
    public ArrayList<Integer> tousLesElementsDP_Quantite() {
        this.bd = this.accesBD.getReadableDatabase();
        ArrayList<Integer> tousLesElements = new ArrayList<Integer>();
        String requete = "SELECT * FROM AppartientDP";
        Cursor curseur = this.bd.rawQuery(requete, null);
        curseur.moveToFirst();

        while (!curseur.isAfterLast()) {
            int quantite = curseur.getInt(2);

            tousLesElements.add(quantite);
            curseur.moveToNext();
        }

        curseur.close();
        return tousLesElements;
    }

    /**
     * Supprime toutes les liaisons dans la table AppartientDP correspondant à l'id du pack passé en paramètre
     * @param idPack
     */
    public void supprimerPackDP(int idPack){
        this.bd = this.accesBD.getWritableDatabase();
        String requete = "DELETE FROM AppartientDP WHERE unPack = \"" + idPack + "\"";
        this.bd.execSQL(requete);
    }

    // =========================== Appartient Devis Composant ====================================

    /**
     * Ajoute un devis et un composant dans AppartientDC.
     *
     * @param devis le devis.
     * @param  composant le composant
     * @param quantite la quantite
     */
    public void ajoutAppartientDC(Devis devis, Composant composant, int quantite) {
        this.bd = this.accesBD.getWritableDatabase();
        String requete = "INSERT INTO AppartientDC (unDevis, unComposant, quantite) values";
        requete += "(\"" + devis.getIdDevis() + "\", \"" + composant.getIdComposant() + "\", \""+ quantite +"\")";
        this.bd.execSQL(requete);
    }

    //=========================== Appartient Client Devis =========================================

    /**
     * Ajoute un client et un devis dans AppartientCD.
     *
     * @param client le client.
     * @param  devis le composant
     */
    public void ajoutAppartientCD(Client client, Devis devis) {
        this.bd = this.accesBD.getWritableDatabase();
        String requete = "INSERT INTO AppartientCD (unClient, unDevis) values";
        requete += "(\"" + client.getIdClient() + "\", \"" + devis.getIdDevis() +"\")";
        this.bd.execSQL(requete);
    }


    /**
     * Supprime toutes les liaisons dans la table AppartientCD correspondant à l'id du client passé en paramètre
     * @param idClient
     */
    public void supprimerClientCD(int idClient){
        this.bd = this.accesBD.getWritableDatabase();
        String requete = "DELETE FROM AppartientCD WHERE unClient = \"" + idClient + "\"";
        this.bd.execSQL(requete);
    }

}
