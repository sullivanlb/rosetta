/*
Schéma relationnel :
--------------------
Client (idClient(1), (nomClient, prenomClient) NN, adresseClient, emailClient NN, telClient)
Scenario (idScenario(1), nomScenario NN)
Composant (idComposant(1), (nomComposant, uniteComosant, prixComposant) NN)
Pack (idPack(1), nomPack NN)
Devis (idDevis(1), nomDevis NN)
Connexion (login(1), mdp NN)
Question (idQuestion(1), nomQuestion NN)
AppartientCD ((unClient, unDevis)(1))
AppartientDP ((unDevis, unPack)(1))
AppartientDC ((unDevis, unComposant)(1))
AppartientPC ((unPack, unComposant)(1))
AppartientSP ((unScenario, unPack)(1))
AppartientSC ((unScenario, unComposant)(1))
AppartientSQ ((unScenario, uneQuestion)(1))
*/

----------------------------
-- Destruction des tables --
----------------------------

DROP TABLE AppartientSQ;
DROP TABLE AppartientSC;
DROP TABLE AppartientSP;
DROP TABLE AppartientPC;
DROP TABLE AppartientDC;
DROP TABLE AppartientDP;
DROP TABLE AppartientCD;
DROP TABLE Question;
DROP TABLE Connexion;
DROP TABLE Devis;
DROP TABLE Pack;
DROP TABLE Composant;
DROP TABLE Scenario;
DROP TABLE Client;


------------------
-- Table Client --
------------------

CREATE TABLE Client
  (
    idClient INTEGER PRIMARY KEY autoincrement,

    nomClient TEXT(100)
    CONSTRAINT nn_nomClient NOT NULL,

    prenomClient TEXT(100)
    CONSTRAINT nn_prenomClient NOT NULL,

    adresseClient TEXT(100),

    emailClient TEXT(100)
    CONSTRAINT nn_emailClient NOT NULL,

    telClient INTEGER(10),

    sexeClient TEXT(100)
    CONSTRAINT nn_sexeClient NOT NULL
  );

--------------------
-- Table Scenario --
--------------------

  CREATE TABLE Scenario
    (
      idScenario INTEGER PRIMARY KEY autoincrement,

      nomScenario TEXT(100)
      CONSTRAINT nn_Scenario NOT NULL
    );

---------------------
-- Table Composant --
---------------------

  CREATE TABLE Composant
    (
      idComposant INTEGER PRIMARY KEY autoincrement,

      nomComposant TEXT(100)
      CONSTRAINT nn_nomComp NOT NULL,

      uniteComposant TEXT(100)
      CONSTRAINT nn_uniteComp NOT NULL,

      prixComposant REAL(10)
      CONSTRAINT nn_prixComp NOT NULL
    );

----------------
-- Table Pack --
----------------

  CREATE TABLE Pack
    (
      idPack INTEGER PRIMARY KEY autoincrement,

      nomPack TEXT(100)
      CONSTRAINT nn_Pack NOT NULL
    );

-----------------
-- Table Devis --
-----------------

  CREATE TABLE Devis
    (
      idDevis INTEGER PRIMARY KEY autoincrement,

      nomDevis TEXT(100)
      CONSTRAINT nn_Devis NOT NULL
    );

---------------------
-- Table Connexion --
---------------------

  CREATE TABLE Connexion
    (
      login TEXT(100)
      CONSTRAINT pk_Connexion PRIMARY KEY,

      mdp TEXT(100)
      CONSTRAINT nn_Connexion NOT NULL
    );

--------------------
-- Table Question --
--------------------

  CREATE TABLE Question
    (
      idQuestion INTEGER PRIMARY KEY autoincrement,

      nomQuestion TEXT(100)
      CONSTRAINT nn_Question NOT NULL
    );

------------------------
-- Table AppartientCD --
------------------------

  CREATE TABLE AppartientCD
    (
      unClient INTEGER(10)
      CONSTRAINT fk_unClient_AppartientCD REFERENCES Client(idClient),

      unDevis INTEGER(10)
      CONSTRAINT fk_unDevis_AppartientCD REFERENCES Devis(idDevis),

      quantite INTEGER(10)
      CONSTRAINT nn_CD_quantite NOT NULL,

      CONSTRAINT pk_AppartientCD PRIMARY KEY (unClient, unDevis)
    );


------------------------
-- Table AppartientDP --
------------------------

  CREATE TABLE AppartientDP
    (
      unDevis INTEGER(10)
      CONSTRAINT fk_unDevis_AppartientDP REFERENCES Devis(idDevis),

      unPack INTEGER(10)
      CONSTRAINT fk_unPack_AppartientDP REFERENCES Pack(idPack),

      quantite INTEGER(10)
      CONSTRAINT nn_DP_quantite NOT NULL,

      CONSTRAINT pk_AppartientDP PRIMARY KEY (unDevis,unPack)
    );

------------------------
-- Table AppartientDC --
------------------------

  CREATE TABLE AppartientDC
    (
      unDevis INTEGER(10)
      CONSTRAINT fk_unDevis_AppartientDC REFERENCES Devis(idDevis),

      unComposant INTEGER(10)
      CONSTRAINT fk_unComposant_AppartientDC REFERENCES Composant(idComposant),

      quantite INTEGER(10)
      CONSTRAINT nn_DC_quantite NOT NULL,

      CONSTRAINT pk_AppartientDC PRIMARY KEY (unDevis,unComposant)
    );

------------------------
-- Table AppartientPC --
------------------------

  CREATE TABLE AppartientPC
    (
      unPack INTEGER(10)
      CONSTRAINT fk_unPack_AppartientPC REFERENCES Pack(idPack),

      unComposant INTEGER(10)
      CONSTRAINT fk_unComposant_AppartientPC REFERENCES Composant(idComposant),

      quantite INTEGER(10)
      CONSTRAINT nn_PC_quantite NOT NULL,

      CONSTRAINT pk_AppartientPC PRIMARY KEY (unPack,unComposant)
    );

------------------------
-- Table AppartientSP --
------------------------

  CREATE TABLE AppartientSP
    (
      unScenario INTEGER(10)
      CONSTRAINT fk_unScenario_AppartientSP REFERENCES Scenario(idScenario),

      unPack INTEGER(10)
      CONSTRAINT fk_unPack_AppartientSP REFERENCES Pack(idPack),

      quantite INTEGER(10)
      CONSTRAINT nn_SP_quantite NOT NULL,

      CONSTRAINT pk_AppartientSP PRIMARY KEY (unScenario,unPack)
    );

------------------------
-- Table AppartientSC --
------------------------

  CREATE TABLE AppartientSC
    (
      unScenario INTEGER(10)
      CONSTRAINT fk_unScenario_AppartientSC REFERENCES Scenario(idScenario),

      unComposant INTEGER(10)
      CONSTRAINT fk_unComposant_AppartientSC REFERENCES Composant(idComposant),

      quantite INTEGER(10)
      CONSTRAINT nn_SC_quantite NOT NULL,

      CONSTRAINT pk_AppartientSC PRIMARY KEY (unScenario,unComposant)
    );

------------------------
-- Table AppartientSQ --
------------------------

  CREATE TABLE AppartientSQ
    (
      unScenario INTEGER(10)
      CONSTRAINT fk_unScenario_AppartientSQ REFERENCES Scenario(idScenario),

      uneQuestion INTEGER(10)
      CONSTRAINT fk_uneQuestion_AppartientSQ REFERENCES Question(idQuestion),

      quantite INTEGER(10)
      CONSTRAINT nn_SQ_quantite NOT NULL,

      CONSTRAINT pk_AppartientSQ PRIMARY KEY (unScenario,uneQuestion)
    );
