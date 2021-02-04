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
    private final String CREATION = "CREATE TABLE Client\n" +
            "  (\n" +
            "    idClient INTEGER PRIMARY KEY autoincrement,\n" +
            "\n" +
            "    nomClient TEXT(100)\n" +
            "    CONSTRAINT nn_nomClient NOT NULL,\n" +
            "\n" +
            "    prenomClient TEXT(100)\n" +
            "    CONSTRAINT nn_prenomClient NOT NULL,\n" +
            "\n" +
            "    adresseClient TEXT(100),\n" +
            "\n" +
            "    emailClient TEXT(100),\n" +
            "\n" +
            "    telClient TEXT(20),\n" +
            "\n" +
            "    sexeClient TEXT(100)\n" +
            "    CONSTRAINT nn_sexeClient NOT NULL\n" +
            "    );\n" +
            "\n" +
            "  CREATE TABLE Scenario\n" +
            "    (\n" +
            "      idScenario INTEGER PRIMARY KEY autoincrement,\n" +
            "\n" +
            "      nomScenario TEXT(100)\n" +
            "      CONSTRAINT nn_Scenario NOT NULL\n" +
            "    );\n" +
            "\n" +
            "  CREATE TABLE Composant\n" +
            "    (\n" +
            "      idComposant INTEGER PRIMARY KEY autoincrement,\n" +
            "\n" +
            "      nomComposant TEXT(100)\n" +
            "      CONSTRAINT nn_nomComp NOT NULL,\n" +
            "\n" +
            "      uniteComposant TEXT(100)\n" +
            "      CONSTRAINT nn_uniteComp NOT NULL,\n" +
            "\n" +
            "      prixComposant REAL(10)\n" +
            "      CONSTRAINT nn_prixComp NOT NULL\n" +
            "    );\n" +
            "\n" +
            "  CREATE TABLE Pack\n" +
            "    (\n" +
            "      idPack INTEGER PRIMARY KEY autoincrement,\n" +
            "\n" +
            "      nomPack TEXT(100)\n" +
            "      CONSTRAINT nn_Pack NOT NULL\n" +
            "    );\n" +
            "\n" +
            "  CREATE TABLE Devis\n" +
            "    (\n" +
            "      idDevis INTEGER PRIMARY KEY autoincrement,\n" +
            "\n" +
            "      nomDevis TEXT(100)\n" +
            "      CONSTRAINT nn_Devis NOT NULL\n" +
            "    );\n" +
            "\n" +
            "  CREATE TABLE Connexion\n" +
            "    (\n" +
            "      login TEXT(100)\n" +
            "      CONSTRAINT pk_Connexion PRIMARY KEY,\n" +
            "\n" +
            "      mdp TEXT(100)\n" +
            "      CONSTRAINT nn_Connexion NOT NULL\n" +
            "    );\n" +
            "\n" +
            "  CREATE TABLE Question\n" +
            "    (\n" +
            "      idQuestion INTEGER PRIMARY KEY autoincrement,\n" +
            "\n" +
            "      nomQuestion TEXT(100)\n" +
            "      CONSTRAINT nn_Question NOT NULL\n" +
            "    );\n" +
            "\n" +
            "  CREATE TABLE AppartientCD\n" +
            "    (\n" +
            "      unClient INTEGER(10)\n" +
            "      CONSTRAINT fk_unClient_AppartientCD REFERENCES Client(idClient),\n" +
            "\n" +
            "      unDevis INTEGER(10)\n" +
            "      CONSTRAINT fk_unDevis_AppartientCD REFERENCES Devis(idDevis),\n" +
            "\n" +
            "      CONSTRAINT pk_AppartientCD PRIMARY KEY (unClient, unDevis)\n" +
            "    );\n" +
            "\n" +
            "  CREATE TABLE AppartientDP\n" +
            "    (\n" +
            "      unDevis INTEGER(10)\n" +
            "      CONSTRAINT fk_unDevis_AppartientDP REFERENCES Devis(idDevis),\n" +
            "\n" +
            "      unPack INTEGER(10)\n" +
            "      CONSTRAINT fk_unPack_AppartientDP REFERENCES Pack(idPack),\n" +
            "\n" +
            "      quantite INTEGER(10)\n" +
            "      CONSTRAINT nn_DP_quantite NOT NULL,\n" +
            "\n" +
            "      CONSTRAINT pk_AppartientDP PRIMARY KEY (unDevis,unPack)\n" +
            "    );\n" +
            "\n" +
            "  CREATE TABLE AppartientDC\n" +
            "    (\n" +
            "      unDevis INTEGER(10)\n" +
            "      CONSTRAINT fk_unDevis_AppartientDC REFERENCES Devis(idDevis),\n" +
            "\n" +
            "      unComposant INTEGER(10)\n" +
            "      CONSTRAINT fk_unComposant_AppartientDC REFERENCES Composant(idComposant),\n" +
            "\n" +
            "      quantite INTEGER(10)\n" +
            "      CONSTRAINT nn_DC_quantite NOT NULL,\n" +
            "\n" +
            "      CONSTRAINT pk_AppartientDC PRIMARY KEY (unDevis,unComposant)\n" +
            "    );\n" +
            "\n" +
            "  CREATE TABLE AppartientPC\n" +
            "    (\n" +
            "      unPack INTEGER(10)\n" +
            "      CONSTRAINT fk_unPack_AppartientPC REFERENCES Pack(idPack),\n" +
            "\n" +
            "      unComposant INTEGER(10)\n" +
            "      CONSTRAINT fk_unComposant_AppartientPC REFERENCES Composant(idComposant),\n" +
            "\n" +
            "      quantite INTEGER(10)\n" +
            "      CONSTRAINT nn_PC_quantite NOT NULL,\n" +
            "\n" +
            "      CONSTRAINT pk_AppartientPC PRIMARY KEY (unPack,unComposant)\n" +
            "    );\n" +
            "\n" +
            "  CREATE TABLE AppartientSP\n" +
            "    (\n" +
            "      unScenario INTEGER(10)\n" +
            "      CONSTRAINT fk_unScenario_AppartientSP REFERENCES Scenario(idScenario),\n" +
            "\n" +
            "      unPack INTEGER(10)\n" +
            "      CONSTRAINT fk_unPack_AppartientSP REFERENCES Pack(idPack),\n" +
            "\n" +
            "      quantite INTEGER(10)\n" +
            "      CONSTRAINT nn_SP_quantite NOT NULL,\n" +
            "\n" +
            "      CONSTRAINT pk_AppartientSP PRIMARY KEY (unScenario,unPack)\n" +
            "    );\n" +
            "\n" +
            "  CREATE TABLE AppartientSC\n" +
            "    (\n" +
            "      unScenario INTEGER(10)\n" +
            "      CONSTRAINT fk_unScenario_AppartientSC REFERENCES Scenario(idScenario),\n" +
            "\n" +
            "      unComposant INTEGER(10)\n" +
            "      CONSTRAINT fk_unComposant_AppartientSC REFERENCES Composant(idComposant),\n" +
            "\n" +
            "      quantite INTEGER(10)\n" +
            "      CONSTRAINT nn_SC_quantite NOT NULL,\n" +
            "\n" +
            "      CONSTRAINT pk_AppartientSC PRIMARY KEY (unScenario,unComposant)\n" +
            "    );\n" +
            "\n" +
            "  CREATE TABLE AppartientSQ\n" +
            "    (\n" +
            "      unScenario INTEGER(10)\n" +
            "      CONSTRAINT fk_unScenario_AppartientSQ REFERENCES Scenario(idScenario),\n" +
            "\n" +
            "      uneQuestion INTEGER(10)\n" +
            "      CONSTRAINT fk_uneQuestion_AppartientSQ REFERENCES Question(idQuestion),\n" +
            "\n" +
            "      CONSTRAINT pk_AppartientSQ PRIMARY KEY (unScenario,uneQuestion)\n" +
            "    );\n";

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
        db.execSQL(this.CREATION);
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
