/*
Schéma relationnel :
Client (idClient(1), (nomClient, prenomClient) NN, adresseClient, emailClient NN, telClient, sexeClient)
Scenario (idScenario(1), nomScenario NN)
Composant (idComposant(1), (nomComposant, uniteComosant, prixComposant) NN)
Pack (idPack(1), nomPack NN)
Devis (idDevis(1), nomDevis NN)
Connexion (login(1), mdp NN)
Question (idQuestion(1), nomQuestion NN)
AppartientCD ((unClient, unDevis)(1))
AppartientDP ((unDevis, unPack)(1), quantite NN)
AppartientDC ((unDevis, unComposant)(1), quantite NN)
AppartientPC ((unPack, unComposant)(1), quantite NN)
AppartientSP ((unScenario, unPack)(1), quantite NN)
AppartientSC ((unScenario, unComposant)(1), quantite NN)
AppartientSQ ((unScenario, uneQuestion)(1))
*/

/*
Destruction des tables
*/

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


/*
Table Client
*/

CREATE TABLE Client
  (
    idClient INT PRIMARY KEY AUTO_INCREMENT,

    nomClient TEXT(100) NOT NULL,

    prenomClient TEXT(100) NOT NULL,

    adresseClient TEXT(100),

    emailClient TEXT(100),

    telClient INT(10),

    sexeClient TEXT(100) NOT NULL
  );

/*
Table Scenario
*/

  CREATE TABLE Scenario
    (
      idScenario INT PRIMARY KEY AUTO_INCREMENT,

      nomScenario TEXT(100) NOT NULL
    );


/*
Table Composant
*/

  CREATE TABLE Composant
    (
      idComposant INT PRIMARY KEY AUTO_INCREMENT,

      nomComposant TEXT(100) NOT NULL,

      uniteComposant TEXT(100) NOT NULL,

      prixComposant DECIMAL(10,2) NOT NULL
    );


/*
Table Pack
*/

  CREATE TABLE Pack
    (
      idPack INT PRIMARY KEY AUTO_INCREMENT,

      nomPack TEXT(100) NOT NULL
    );


/*
Table Devis
*/

  CREATE TABLE Devis
    (
      idDevis INT PRIMARY KEY AUTO_INCREMENT,

      nomDevis TEXT(100) NOT NULL
    );


/*
Table Connexion
*/

  CREATE TABLE Connexion
    (
      login VARCHAR(100) PRIMARY KEY,

      mdp TEXT(100) NOT NULL
    );


/*
Table Question
*/

  CREATE TABLE Question
    (
      idQuestion INT PRIMARY KEY AUTO_INCREMENT,

      nomQuestion TEXT(100) NOT NULL
    );


/*
Table AppartientCD
*/

  CREATE TABLE AppartientCD
    (
      unClient INT(10) REFERENCES Client(idClient),

      unDevis INT(10) REFERENCES Devis(idDevis),

      CONSTRAINT pk_AppartientCD PRIMARY KEY (unClient, unDevis)
    );


/*
AppartientDP
*/

  CREATE TABLE AppartientDP
    (
      unDevis INT(10) REFERENCES Devis(idDevis),

      unPack INT(10) REFERENCES Pack(idPack),

      quantite INT(10) NOT NULL,

      CONSTRAINT pk_AppartientDP PRIMARY KEY (unDevis,unPack)
    );


/*
AppartientDC
*/

  CREATE TABLE AppartientDC
    (
      unDevis INT(10) REFERENCES Devis(idDevis),

      unComposant INT(10) REFERENCES Composant(idComposant),

      quantite INT(10) NOT NULL,

      CONSTRAINT pk_AppartientDC PRIMARY KEY (unDevis,unComposant)
    );


/*
AppartientPC
*/

  CREATE TABLE AppartientPC
    (
      unPack INT(10) REFERENCES Pack(idPack),

      unComposant INT(10) REFERENCES Composant(idComposant),

      quantite INT(10) NOT NULL,

      CONSTRAINT pk_AppartientPC PRIMARY KEY (unPack,unComposant)
    );


/*
AppartientSP
*/

  CREATE TABLE AppartientSP
    (
      unScenario INT(10) REFERENCES Scenario(idScenario),

      unPack INT(10) REFERENCES Pack(idPack),

      quantite INT(10) NOT NULL,

      CONSTRAINT pk_AppartientSP PRIMARY KEY (unScenario,unPack)
    );


/*
AppartientSC
*/

  CREATE TABLE AppartientSC
    (
      unScenario INT(10) REFERENCES Scenario(idScenario),

      unComposant INT(10) REFERENCES Composant(idComposant),

      quantite INT(10) NOT NULL,

      CONSTRAINT pk_AppartientSC PRIMARY KEY (unScenario,unComposant)
    );


/*
AppartientSQ
*/

  CREATE TABLE AppartientSQ
    (
      unScenario INT(10) REFERENCES Scenario(idScenario),

      uneQuestion INT(10) REFERENCES Question(idQuestion),

      CONSTRAINT pk_AppartientSQ PRIMARY KEY (unScenario,uneQuestion)
    );
