/*
Création de séquences pour toutes les tables qui ont des ids.
Soit :
- Client
- Scenario
- Composant
- Pack
- Devis
- Connexion
- Question
*/

----------------------------------
-- Initialisation des séquences --
----------------------------------

---------------------
-- Séquence Client --
---------------------

DROP SEQUENCE seq_Client;
CREATE SEQUENCE seq_Client
	MINVALUE 1
	START WITH 1
	INCREMENT BY 1 ;

-----------------------
-- Séquence Scenario --
-----------------------

DROP SEQUENCE seq_Scenario;
CREATE SEQUENCE seq_Scenario
	MINVALUE 1
	START WITH 1
	INCREMENT BY 1 ;

------------------------
-- Séquence Composant --
------------------------

DROP SEQUENCE seq_Composant;
CREATE SEQUENCE seq_Composant
	MINVALUE 1
	START WITH 1
	INCREMENT BY 1 ;

-------------------
-- Séquence Pack --
-------------------

DROP SEQUENCE seq_Pack;
CREATE SEQUENCE seq_Pack
	MINVALUE 1
	START WITH 1
	INCREMENT BY 1 ;

--------------------
-- Séquence Devis --
--------------------

DROP SEQUENCE seq_Devis;
CREATE SEQUENCE seq_Devis
	MINVALUE 1
	START WITH 1
	INCREMENT BY 1 ;

-----------------------
-- Séquence Question --
-----------------------

DROP SEQUENCE seq_Question;
CREATE SEQUENCE seq_Question
	MINVALUE 1
	START WITH 1
	INCREMENT BY 1 ;


---------------------------------
-- Les triggers correspondants --
---------------------------------

---------------------
-- Trigger Client --
---------------------

CREATE OR REPLACE TRIGGER trig_seq_Client
BEFORE INSERT ON Client
FOR EACH ROW
WHEN(NEW.idClient IS NULL)
BEGIN
	:NEW.idClient := seq_Client.nextVal;
END;
/

----------------------
-- Trigger Scenario --
----------------------

CREATE OR REPLACE TRIGGER trig_seq_Scenario
BEFORE INSERT ON Scenario
FOR EACH ROW
WHEN(NEW.idScenario IS NULL)
BEGIN
	:NEW.idScenario := seq_Scenario.nextVal;
END;
/

-----------------------
-- Trigger Composant --
-----------------------

CREATE OR REPLACE TRIGGER trig_seq_Composant
BEFORE INSERT ON Composant
FOR EACH ROW
WHEN(NEW.idComposant IS NULL)
BEGIN
	:NEW.idComposant := seq_Composant.nextVal;
END;
/

------------------
-- Trigger Pack --
------------------

CREATE OR REPLACE TRIGGER trig_seq_Pack
BEFORE INSERT ON Pack
FOR EACH ROW
WHEN(NEW.idPack IS NULL)
BEGIN
	:NEW.idPack := seq_Pack.nextVal;
END;
/

-------------------
-- Trigger Devis --
-------------------

CREATE OR REPLACE TRIGGER trig_seq_Devis
BEFORE INSERT ON Devis
FOR EACH ROW
WHEN(NEW.idDevis IS NULL)
BEGIN
	:NEW.idDevis := seq_Devis.nextVal;
END;
/

----------------------
-- Trigger Question --
----------------------

CREATE OR REPLACE TRIGGER trig_seq_Question
BEFORE INSERT ON Question
FOR EACH ROW
WHEN(NEW.idQuestion IS NULL)
BEGIN
	:NEW.idQuestion := seq_Question.nextVal;
END;
/
