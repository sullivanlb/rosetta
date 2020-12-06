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
    idClient NUMBER(10)
    CONSTRAINT pk_Client PRIMARY KEY,

    nomClient VARCHAR(100)
    CONSTRAINT nn_nomClient NOT NULL,

    prenomClient VARCHAR(100)
    CONSTRAINT nn_prenomClient NOT NULL,

    adresseClient VARCHAR(100),

    emailClient VARCHAR(100)
    CONSTRAINT nn_emailClient NOT NULL,

    telClient NUMBER(10),
    
    sexeClient VARCHAR(100)
    CONSTRAINT nn_sexeClient NOT NULL
  );

--------------------
-- Table Scenario --
--------------------

  CREATE TABLE Scenario
    (
      idScenario NUMBER(10)
      CONSTRAINT pk_Scenario PRIMARY KEY,

      nomScenario VARCHAR(100)
      CONSTRAINT nn_Scenario NOT NULL
    );

---------------------
-- Table Composant --
---------------------

  CREATE TABLE Composant
    (
      idComposant NUMBER(10)
      CONSTRAINT pk_Composant PRIMARY KEY,

      nomComposant VARCHAR(100)
      CONSTRAINT nn_nomComp NOT NULL,

      uniteComposant VARCHAR(100)
      CONSTRAINT nn_uniteComp NOT NULL,

      prixComposant NUMBER(10)
      CONSTRAINT nn_prixComp NOT NULL
    );

----------------
-- Table Pack --
----------------

  CREATE TABLE Pack
    (
      idPack NUMBER(10)
      CONSTRAINT pk_Pack PRIMARY KEY,

      nomPack VARCHAR(100)
      CONSTRAINT nn_Pack NOT NULL
    );

-----------------
-- Table Devis --
-----------------

  CREATE TABLE Devis
    (
      idDevis NUMBER(10)
      CONSTRAINT pk_Devis PRIMARY KEY,

      nomDevis VARCHAR(100)
      CONSTRAINT nn_Devis NOT NULL
    );

---------------------
-- Table Connexion --
---------------------

  CREATE TABLE Connexion
    (
      login VARCHAR(100)
      CONSTRAINT pk_Connexion PRIMARY KEY,

      mdp VARCHAR(100)
      CONSTRAINT nn_Connexion NOT NULL
    );

--------------------
-- Table Question --
--------------------

  CREATE TABLE Question
    (
      idQuestion NUMBER(10)
      CONSTRAINT pk_Question PRIMARY KEY,

      nomQuestion VARCHAR(100)
      CONSTRAINT nn_Question NOT NULL
    );

------------------------
-- Table AppartientCD --
------------------------

  CREATE TABLE AppartientCD
    (
      unClient NUMBER(10)
      CONSTRAINT fk_unClient_AppartientCD REFERENCES Client(idClient),

      unDevis NUMBER(10)
      CONSTRAINT fk_unDevis_AppartientCD REFERENCES Devis(idDevis),

      CONSTRAINT pk_AppartientCD PRIMARY KEY (unClient, unDevis)
    );


------------------------
-- Table AppartientDP --
------------------------

  CREATE TABLE AppartientDP
    (
      unDevis NUMBER(10)
      CONSTRAINT fk_unDevis_AppartientDP REFERENCES Devis(idDevis),

      unPack NUMBER(10)
      CONSTRAINT fk_unPack_AppartientDP REFERENCES Pack(idPack),

      CONSTRAINT pk_AppartientDP PRIMARY KEY (unDevis,unPack)
    );

------------------------
-- Table AppartientDC --
------------------------

  CREATE TABLE AppartientDC
    (
      unDevis NUMBER(10)
      CONSTRAINT fk_unDevis_AppartientDC REFERENCES Devis(idDevis),

      unComposant NUMBER(10)
      CONSTRAINT fk_unComposant_AppartientDC REFERENCES Composant(idComposant),

      CONSTRAINT pk_AppartientDC PRIMARY KEY (unDevis,unComposant)
    );

------------------------
-- Table AppartientPC --
------------------------

  CREATE TABLE AppartientPC
    (
      unPack NUMBER(10)
      CONSTRAINT fk_unPack_AppartientPC REFERENCES Pack(idPack),

      unComposant NUMBER(10)
      CONSTRAINT fk_unComposant_AppartientPC REFERENCES Composant(idComposant),

      CONSTRAINT pk_AppartientPC PRIMARY KEY (unPack,unComposant)
    );

------------------------
-- Table AppartientSP --
------------------------

  CREATE TABLE AppartientSP
    (
      unScenario NUMBER(10)
      CONSTRAINT fk_unScenario_AppartientSP REFERENCES Scenario(idScenario),

      unPack NUMBER(10)
      CONSTRAINT fk_unPack_AppartientSP REFERENCES Pack(idPack),

      CONSTRAINT pk_AppartientSP PRIMARY KEY (unScenario,unPack)
    );

------------------------
-- Table AppartientSC --
------------------------

  CREATE TABLE AppartientSC
    (
      unScenario NUMBER(10)
      CONSTRAINT fk_unScenario_AppartientSC REFERENCES Scenario(idScenario),

      unComposant NUMBER(10)
      CONSTRAINT fk_unComposant_AppartientSC REFERENCES Composant(idComposant),

      CONSTRAINT pk_AppartientSC PRIMARY KEY (unScenario,unComposant)
    );

------------------------
-- Table AppartientSQ --
------------------------

  CREATE TABLE AppartientSQ
    (
      unScenario NUMBER(10)
      CONSTRAINT fk_unScenario_AppartientSQ REFERENCES Scenario(idScenario),

      uneQuestion NUMBER(10)
      CONSTRAINT fk_uneQuestion_AppartientSQ REFERENCES Question(idQuestion),

      CONSTRAINT pk_AppartientSQ PRIMARY KEY (unScenario,uneQuestion)
    );
