/*
Client (idClient(1), (nomClient, prenomClient) NN, adresseClient, emailClient NN, telClient) ;
Scenario (idScenario(1), nomScenario NN) ;
Composant (idComposant(1), (nomComposant, uniteComosant, prixComposant) NN) ;
Pack (idPack(1), nomPack NN) ;
Devis (idDevis(1), nomDevis NN) ;
Connexion (login(1), mdp NN) ;
Question (idQuestion(1), nomQuestion NN) ;
AppartientCD ((unClient=@Client[idClient], unDevis=@Devis[idDevis])(1)) ;
AppartientDP ((unDevis=@Devis[idDevis], unPack=@Pack[idPack])(1)) ;
AppartientDC ((unDevis=@Devis[idDevis], unComposant=@Composant[idComposant])(1)) ;
AppartientPC ((unPack=@Pack[idPack], unComposant=@Composant[idComposant])(1)) ;
AppartientSP ((unScenario=@Scenario[idScenario], unPack=@Pack[idPack])(1)) ;
AppartientSC ((unScenario=@Scenario[idScenario], unComposant=@Composant[idComposant])(1)) ;
AppartientSQ ((unScenario=@Scenario[idScenario], uneQuestion=@Question[idQuestion])(1)).
*/


/*
Vidage des tables
*/

DELETE FROM AppartientSQ;
DELETE FROM AppartientSC;
DELETE FROM AppartientSP;
DELETE FROM AppartientPC;
DELETE FROM AppartientDC;
DELETE FROM AppartientDP;
DELETE FROM AppartientCD;
DELETE FROM Question;
DELETE FROM Connexion;
DELETE FROM Devis;
DELETE FROM Pack;
DELETE FROM Composant;
DELETE FROM Scenario;
DELETE FROM Client;


/*
Insertion de Client
*/

INSERT INTO Client VALUES (NULL, 'nomCli1', 'prenomCli1', 'adresse1', 'email1', 0102030405, 'H');
INSERT INTO Client VALUES (NULL, 'nomCli2', 'prenomCli2', NULL, 'email2', 0102030405, 'F');
INSERT INTO Client VALUES (NULL, 'nomCli3', 'prenomCli3', 'addresse3', 'email3', 0102030405, 'F');
INSERT INTO Client VALUES (NULL, 'nomCli4', 'prenomCli4', NULL, 'email4', NULL, 'H');
INSERT INTO Client VALUES (NULL, 'nomCli7', 'prenomCli7', 'adresse7', NULL, 0102030405, 'F');

/*
Test contrainte null
*/

INSERT INTO Client VALUES (NULL, NULL, 'prenomCli5', 'adresse5', 'email5', 0102030405, 'H');
INSERT INTO Client VALUES (NULL, 'nomCli6', NULL, 'adresse6', 'email6', 0102030405, 'F');
INSERT INTO Client VALUES (NULL, 'nomCli7', 'prenomCli7', 'adresse7', NULL, NULL, 'F');
INSERT INTO Client VALUES (NULL, 'nomCli7', 'prenomCli7', 'adresse7', 'email6', 0102030405, NULL);

/*
Insertion de Scenario
*/

INSERT INTO Scenario VALUES (NULL, 'nomSce1');
INSERT INTO Scenario VALUES (NULL, 'nomSce2');

/*
Test contrainte null
*/

INSERT INTO Scenario VALUES (NULL, NULL);

/*
Insertion de Composant
*/

INSERT INTO Composant VALUES (NULL, 'nomComp1', 'metre', 20);
INSERT INTO Composant VALUES (NULL, 'nomComp2', 'gramme', 20);

/*
Test contrainte null
*/

INSERT INTO Composant VALUES (NULL, 'nomComp3', 'metre', NULL);
INSERT INTO Composant VALUES (NULL, NULL, 'metre', 20);
INSERT INTO Composant VALUES (NULL, 'nomComp5', NULL, 20);

/*
Insertion de Pack
*/

INSERT INTO Pack VALUES (NULL, 'nomPack1');
INSERT INTO Pack VALUES (NULL, 'nomPack2');

/*
Test contrainte null
*/

INSERT INTO Pack VALUES (NULL, NULL);

/*
Insertion de Devis
*/

INSERT INTO Devis VALUES (NULL, 'nomDevis1');
INSERT INTO Devis VALUES (NULL, 'nomDevis2');

/*
Test contrainte null
*/

INSERT INTO Devis VALUES (NULL, NULL);

/*
Insertion de Connexion
*/

INSERT INTO Connexion VALUES (1, 'nomConnexion1');
INSERT INTO Connexion VALUES (2, 'nomConnexion2');

/*
Test contrainte null
*/

INSERT INTO Connexion VALUES (NULL, NULL);

/*
 Insertion de Question
*/

INSERT INTO Question VALUES (NULL, 'nomQuestion1');
INSERT INTO Question VALUES (NULL, 'nomQuestion2');

/*
Test contrainte null
*/

INSERT INTO Question VALUES (NULL, NULL);

/*
Insertion de AppartientXX
*/

INSERT INTO AppartientCD VALUES (1, 1);
INSERT INTO AppartientCD VALUES (2, 2);
INSERT INTO AppartientDP VALUES (1, 1, 20);
INSERT INTO AppartientDP VALUES (2, 2, 30);
INSERT INTO AppartientDC VALUES (1, 1, 2);
INSERT INTO AppartientDC VALUES (2, 2, 5);
INSERT INTO AppartientPC VALUES (1, 1, 0);
INSERT INTO AppartientPC VALUES (2, 2, 100);
INSERT INTO AppartientSP VALUES (1, 1, 15);
INSERT INTO AppartientSP VALUES (2, 2, 70);
INSERT INTO AppartientSC VALUES (1, 1, 56);
INSERT INTO AppartientSC VALUES (2, 2, 41);
INSERT INTO AppartientSQ VALUES (1, 1);
INSERT INTO AppartientSQ VALUES (2, 2);

/*
Test d'erreur du trigger avec UPDATE
*/
UPDATE Client SET telClient = NULL WHERE idClient = 3;
UPDATE Client SET emailClient = NULL WHERE idClient = 4;