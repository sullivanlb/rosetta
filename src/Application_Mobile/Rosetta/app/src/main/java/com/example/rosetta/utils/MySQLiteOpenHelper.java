package com.example.rosetta.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Cette classe permet de créer la base de données interne.
 *
 * @author Christophe
 * @version 2.0
 */
public class MySQLiteOpenHelper extends SQLiteOpenHelper {

    /**
     * Script de création de tables de la base de données.
     */

    private final String TABLE1 = "Client";
    private final String TABLE2 = "Scenario";
    private final String TABLE3 = "Composant";
    private final String TABLE4 = "Pack";
    private final String TABLE5 = "Devis";
    private final String TABLE6 = "Connexion";
    private final String TABLE7 = "Question";
    private final String TABLE8 = "AppartientCD";
    private final String TABLE9 = "AppartientDP";
    private final String TABLE10 = "AppartientDC";
    private final String TABLE11 = "AppartientPC";
    private final String TABLE12 = "AppartientSP";
    private final String TABLE13 = "AppartientSC";
    private final String TABLE14 = "AppartientSQ";


    /**
     * Crée une nouvelle forme de SQLiteOpenHelper
     *
     * @param context le contexte de la BDD
     * @param name le nom de la BDD
     * @param factory la sous-classe quand on fait des requetes
     * @param version le numero de version de la BDD
     */
    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    /**
     * Si changement de la base de données
     *
     * @param db la base de données
     */
    @Override
    public void onCreate(SQLiteDatabase db) {

        //Script de création des tables

        String tableClient = "CREATE TABLE " + TABLE1 + "(idClient INTEGER PRIMARY KEY autoincrement, nomClient TEXT(100) CONSTRAINT nn_nomClient NOT NULL, " +
              "prenomClient TEXT(100) CONSTRAINT nn_prenomClient NOT NULL, adresseClient TEXT(100), emailClient TEXT(100), telClient TEXT(20), sexeClient TEXT(100) CONSTRAINT nn_sexeClient NOT NULL )" ;

        String tableScenario = "CREATE TABLE " + TABLE2 + "(idScenario INTEGER PRIMARY KEY autoincrement, nomScenario TEXT(100) CONSTRAINT nn_Scenario NOT NULL )";

        String tableComposant = "CREATE TABLE " + TABLE3 + "(idComposant INTEGER PRIMARY KEY autoincrement, nomComposant TEXT(100) CONSTRAINT nn_nomComp NOT NULL, " +
              "uniteComposant TEXT(100) CONSTRAINT nn_uniteComp NOT NULL, prixComposant REAL(10) CONSTRAINT nn_prixComp NOT NULL )";

        String tablePack = "CREATE TABLE " + TABLE4 + "(idPack INTEGER PRIMARY KEY autoincrement, nomPack TEXT(100) CONSTRAINT nn_Pack NOT NULL )";

        String tableDevis = "CREATE TABLE " + TABLE5 + "(idDevis INTEGER PRIMARY KEY autoincrement, nomDevis TEXT(100) CONSTRAINT nn_Devis NOT NULL," +
            "descriptionDevis TEXT(500), dureeDevis TEXT(100), dateEditionDevis TEXT(100), dateTravauxDevis TEXT(100) )";

        String tableConnexion = "CREATE TABLE " + TABLE6 + "(login TEXT(100) CONSTRAINT pk_Connexion PRIMARY KEY, mdp TEXT(100) CONSTRAINT nn_Connexion NOT NULL )";

        String tableQuestion = "CREATE TABLE " + TABLE7 + "(idQuestion INTEGER PRIMARY KEY autoincrement, nomQuestion TEXT(100) CONSTRAINT nn_Question NOT NULL )";

        String tableAppartientCD = "CREATE TABLE " + TABLE8 + "(unClient INTEGER(10) CONSTRAINT fk_unClient_AppartientCD REFERENCES Client(idClient)," +
            "unDevis INTEGER(10) CONSTRAINT fk_unDevis_AppartientCD REFERENCES Devis(idDevis), CONSTRAINT pk_AppartientCD PRIMARY KEY (unClient, unDevis) )";

        String tableAppartientDP = "CREATE TABLE " + TABLE9 + "(unDevis INTEGER(10) CONSTRAINT fk_unDevis_AppartientDP REFERENCES Devis(idDevis)," +
            "unPack INTEGER(10) CONSTRAINT fk_unPack_AppartientDP REFERENCES Pack(idPack), quantite INTEGER(10) CONSTRAINT nn_DP_quantite NOT NULL," +
            "CONSTRAINT pk_AppartientDP PRIMARY KEY (unDevis,unPack) )";

        String tableAppartientDC = "CREATE TABLE " + TABLE10 + "(unDevis INTEGER(10) CONSTRAINT fk_unDevis_AppartientDC REFERENCES Devis(idDevis)," +
            "unComposant INTEGER(10) CONSTRAINT fk_unComposant_AppartientDC REFERENCES Composant(idComposant), quantite INTEGER(10) CONSTRAINT nn_DC_quantite NOT NULL," +
            "CONSTRAINT pk_AppartientDC PRIMARY KEY (unDevis,unComposant) )";

        String tableAppartientPC = "CREATE TABLE " + TABLE11 + "(unPack INTEGER(10) CONSTRAINT fk_unPack_AppartientPC REFERENCES Pack(idPack)," +
            "unComposant INTEGER(10) CONSTRAINT fk_unComposant_AppartientPC REFERENCES Composant(idComposant), quantite INTEGER(10) CONSTRAINT nn_PC_quantite NOT NULL," +
            "CONSTRAINT pk_AppartientPC PRIMARY KEY (unPack,unComposant) )";

        String tableAppartientSP = "CREATE TABLE " + TABLE12 + "(unScenario INTEGER(10) CONSTRAINT fk_unScenario_AppartientSP REFERENCES Scenario(idScenario)," +
            "unPack INTEGER(10) CONSTRAINT fk_unPack_AppartientSP REFERENCES Pack(idPack), quantite INTEGER(10) CONSTRAINT nn_SP_quantite NOT NULL," +
            "CONSTRAINT pk_AppartientSP PRIMARY KEY (unScenario,unPack) )";

        String tableAppartientSC = "CREATE TABLE " + TABLE13 + "(unScenario INTEGER(10) CONSTRAINT fk_unScenario_AppartientSC REFERENCES Scenario(idScenario)," +
            "unComposant INTEGER(10) CONSTRAINT fk_unComposant_AppartientSC REFERENCES Composant(idComposant), quantite INTEGER(10) CONSTRAINT nn_SC_quantite NOT NULL," +
            "CONSTRAINT pk_AppartientSC PRIMARY KEY (unScenario,unComposant) )";

        String tableAppartientSQ = "CREATE TABLE " + TABLE14 + "(unScenario INTEGER(10) CONSTRAINT fk_unScenario_AppartientSQ REFERENCES Scenario(idScenario)," +
            "uneQuestion INTEGER(10) CONSTRAINT fk_uneQuestion_AppartientSQ REFERENCES Question(idQuestion), CONSTRAINT pk_AppartientSQ PRIMARY KEY (unScenario,uneQuestion) )";

        db.execSQL(tableClient);
        db.execSQL(tableScenario);
        db.execSQL(tableComposant);
        db.execSQL(tablePack);
        db.execSQL(tableDevis);
        db.execSQL(tableConnexion);
        db.execSQL(tableQuestion);
        db.execSQL(tableAppartientCD);
        db.execSQL(tableAppartientDP);
        db.execSQL(tableAppartientDC);
        db.execSQL(tableAppartientPC);
        db.execSQL(tableAppartientSP);
        db.execSQL(tableAppartientSC);
        db.execSQL(tableAppartientSQ);
    }

    /**
     * Si changement de version
     *
     * @param db la BDD
     * @param oldVersion le numero de l'ancienne version
     * @param newVersion le numero de la nouvelle version
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Pas de redéfinition de la méthode.
    }
}
