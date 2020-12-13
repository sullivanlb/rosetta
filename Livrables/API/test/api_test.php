<?php
	include_once("../src/BDD_Externe_Connexion.php");
	
	include("../src/Question.php");
	include("../src/QuestionDAO.php");
	include("../src/Connexion.php");
	include("../src/Devis.php");
	include("../src/DevisDAO.php");
	include("../src/Pack.php");
	include("../src/PackDAO.php");
	include("../src/Composant.php");
	include("../src/ComposantDAO.php");
	include("../src/Scenario.php");
	include("../src/ScenarioDAO.php");
	include("../src/Client.php");
	include("../src/ClientDAO.php");
	include("../src/Appartient.php");
	include("../src/AppartientDAO.php");
	
	$connexion = new BDD_Externe_Connexion();
	echo "Connexion réussie\r\n";
/*
	// Question
	echo "\r\n======TEST QUESTION======";

	$question1 = new Question();
	$question2 = new Question();
	$questionDAO = new QuestionDAO();

	$question1->init(NULL, "quest1");
	$question2->init(NULL, "quest2");
	
	#TEST 1 : insertion de question
	echo "\r\nInsertion de 2 questions\r\n";

	$questionDAO->insertion($question1);
	$questionDAO->insertion($question2);

	echo "\r\nAffichage des questions avant modif\r\n";
	foreach ($questionDAO->tousLesElements() as $str){
		echo $str;
	}

	#TEST2 : modification de question
	echo "\r\nModification question 1\r\n";
	$question1->__set("nom", "question modifié");
	$questionDAO->modification($question1);

	echo "\r\nAffichage des questions après modif\r\n";
	foreach ($questionDAO->tousLesElements() as $str){
		echo $str;
	}

	#TEST3 : suppression de question
	$questionDAO->suppression($question1);
	$questionDAO->suppression($question2);

	echo "\r\nContenu après suppression\r\n";
	foreach ($questionDAO->tousLesElements() as $str){
		echo $str;
	}

	echo "\r\n======FIN TEST QUESTION======\r\n";

	// Devis
	echo "\r\n======TEST DEVIS======";

	$devis1 = new Devis();
	$devis2 = new Devis();
	$devisDAO = new DevisDAO();

	$devis1->init(NULL, "devis1");
	$devis2->init(NULL, "devis2");
	
	#TEST 1 : insertion de devis
	echo "\r\nInsertion de 2 devis\r\n";

	$devisDAO->insertion($devis1);
	$devisDAO->insertion($devis2);

	echo "\r\nAffichage des devis avant modif\r\n";
	foreach ($devisDAO->tousLesElements() as $str){
		echo $str;
	}
	
	#TEST2 : modification de devis
	echo "\r\nModification devis 1\r\n";
	$devis1->__set("nom", "devis modifié");
	$devisDAO->modification($devis1);

	echo "\r\nAffichage des devis après modif\r\n";
	foreach ($devisDAO->tousLesElements() as $str){
		echo $str;
	}

	#TEST3 : suppression de devis
	$devisDAO->suppression($devis1);
	$devisDAO->suppression($devis2);

	echo "\r\nContenu après suppression\r\n";
	foreach ($devisDAO->tousLesElements() as $str){
		echo $str;
	}

	echo "\r\n======FIN TEST DEVIS======\r\n";

	// Pack
	echo "\r\n======TEST PACK======";

	$pack1 = new Pack();
	$pack2 = new Pack();
	$packDAO = new PackDAO();

	$pack1->init(NULL, "pack1");
	$pack2->init(NULL, "pack2");
	
	#TEST 1 : insertion de 2 packs
	echo "\r\nInsertion de 2 packs\r\n";

	$packDAO->insertion($pack1);
	$packDAO->insertion($pack2);

	echo "\r\nAffichage des packs avant modif\r\n";
	foreach ($packDAO->tousLesElements() as $str){
		echo $str;
	}

	#TEST2 : modification de pack
	echo "\r\nModification pack 1\r\n";
	$pack1->__set("nom", "pack modifié");
	$packDAO->modification($pack1);

	echo "\r\nAffichage des packs après modif\r\n";
	foreach ($packDAO->tousLesElements() as $str){
		echo $str;
	}

	#TEST3 : suppression de pack
	$packDAO->suppression($pack1);
	$packDAO->suppression($pack2);

	echo "\r\nContenu après suppression\r\n";
	foreach ($packDAO->tousLesElements() as $str){
		echo $str;
	}

	echo "\r\n======FIN TEST PACK======\r\n";


	// Composant
	echo "\r\n======TEST COMPOSANT======";

	$composant1 = new Composant();
	$composant2 = new Composant();
	$composantDAO = new ComposantDAO();

	$composant1->init(NULL, "composant1", "metre1", 9.99);
	$composant2->init(NULL, "composant2", "kilo2", 45.99);
	
	#TEST 1 : insertion de composant
	echo "\r\nInsertion de 2 composants\r\n";

	$composantDAO->insertion($composant1);
	$composantDAO->insertion($composant2);

	echo "\r\nAffichage des composants avant modif\r\n";
	foreach ($composantDAO->tousLesElements() as $str){
		echo $str;
	}
	
	#TEST2 : modification de composant
	echo "\r\nModification composants 1\r\n";
	$composant1->__set("nom", "composant modifié");
	$composantDAO->modification($composant1);

	echo "\r\nAffichage des composants après modif\r\n";
	foreach ($composantDAO->tousLesElements() as $str){
		echo $str;
	}

	#TEST3 : suppression de composant
	$composantDAO->suppression($composant1);
	$composantDAO->suppression($composant2);

	echo "\r\nContenu après suppression\r\n";
	foreach ($composantDAO->tousLesElements() as $str){
		echo $str;
	}

	echo "\r\n======FIN TEST COMPOSANT======\r\n";

	// Scenario
	echo "\r\n======TEST SCENARIO======";

	$scenario1 = new Scenario();
	$scenario2 = new Scenario();
	$scenarioDAO = new ScenarioDAO();

	$scenario1->init(NULL, "scenario 1");
	$scenario2->init(NULL, "scenario 2");
	
	#TEST 1 : insertion de scenario
	echo "\r\nInsertion de 2 scenarios\r\n";

	$scenarioDAO->insertion($scenario1);
	$scenarioDAO->insertion($scenario2);

	echo "\r\nAffichage des scenarios avant modif\r\n";
	foreach ($scenarioDAO->tousLesElements() as $str){
		echo $str;
	}
	
	#TEST2 : modification de scenario
	echo "\r\nModification scenario 1\r\n";
	$scenario1->__set("nom", "scenario modifié");
	$scenarioDAO->modification($scenario1);

	echo "\r\nAffichage des scenarios après modif\r\n";
	foreach ($scenarioDAO->tousLesElements() as $str){
		echo $str;
	}

	#TEST3 : suppression de scenario
	$scenarioDAO->suppression($scenario1);
	$scenarioDAO->suppression($scenario2);

	echo "\r\nContenu après suppression\r\n";
	foreach ($scenarioDAO->tousLesElements() as $str){
		echo $str;
	}

	echo "\r\n======FIN TEST SCENARIO======\r\n";

	// Client
	echo "\r\n======TEST CLIENT======";

	$client1 = new Client();
	$client2 = new Client();
	$clientDAO = new ClientDAO();

	$client1->init(NULL, "nom1", "prenom1", "adresse1", "email1", NULL, "H");
	$client2->init(NULL, "nom2", "prenom2", "adresse2", "email2", NULL, "F");
	
	#TEST 1 : insertion de client
	echo "\r\nInsertion de 2 clients\r\n";

	$clientDAO->insertion($client1);
	$clientDAO->insertion($client2);

	echo "\r\nAffichage des clients avant modif\r\n";
	foreach ($clientDAO->tousLesElements() as $str){
		echo $str;
	}
	
	#TEST2 : modification de client
	echo "\r\nModification client 1\r\n";
	$client1->__set("nom", "nom modifié");
	$clientDAO->modification($client1);

	echo "\r\nAffichage des clients après modif\r\n";
	foreach ($clientDAO->tousLesElements() as $str){
		echo $str;
	}

	#TEST3 : suppression de client
	$clientDAO->suppression($client1);
	$clientDAO->suppression($client2);

	echo "\r\nContenu après suppression\r\n";
	foreach ($clientDAO->tousLesElements() as $str){
		echo $str;
	}

	echo "\r\n======FIN TEST CLIENT======\r\n";
*/

	// Appartient
	echo "\r\n======TEST APPARTIENT======\r\n";
	$client1 = new Client();
	$clientDAO = new ClientDAO();
	$client1->init(NULL, "nom1", "prenom1", "adresse1", "email1", NULL, "H");

	$devis1 = new Devis();
	$devisDAO = new DevisDAO();
	$devis1->init(NULL, "devis1");

	$pack1 = new Pack();
	$packDAO = new PackDAO();
	$pack1->init(NULL, "pack1");

	$appartientDAO = new AppartientDAO();
	$appartient1 = new Appartient();
	$appartient1->init($client1, $devis1, null);
	$appartient2 = new Appartient();
	$appartient2->init($devis1, $pack1, 12);
	
	#TEST 1 : insertion des 2 liaisons Appartient
	echo "\r\nInsertion des 2 liaisons ainsi que leur contenant et contenu\r\n";

	$clientDAO->insertion($client1);
	$devisDAO->insertion($devis1);
	$packDAO->insertion($pack1);
	$appartientDAO->insertion($appartient1, "CD");
	$appartientDAO->insertion($appartient2, "DP");

	echo "\r\nAffichage des liaisons avant modif\r\n";
	foreach ($appartientDAO->tousLesElements("CD") as $str){
		echo $str;
	}
	foreach ($appartientDAO->tousLesElements("DP") as $str){
		echo $str;
	}

	/*
	#TEST2 : modification des liaisons
	echo "\r\nModification de la quantite de appartient2\r\n";
	$appartient2->setQuantite(200);
	$appartientDAO->modification($appartient2, "DP");

	echo "\r\nAffichage des questions après modif\r\n";	
	foreach ($appartientDAO->tousLesElements("CD") as $str){
		echo $str;
	}
	foreach ($appartientDAO->tousLesElements("DP") as $str){
		echo $str;
	}
    */
	#TEST3 : suppression des liaisons
	$appartientDAO->suppression($appartient1, "CD");
	$appartientDAO->suppression($appartient2, "DP");
	$clientDAO->suppression($client1);
	$devisDAO->suppression($devis1);
	$packDAO->suppression($pack1);

	echo "\r\nContenu après suppression\r\n";	
	foreach ($appartientDAO->tousLesElements("CD") as $str){
		echo $str;
	}
	foreach ($appartientDAO->tousLesElements("DP") as $str){
		echo $str;
	}
	
	echo "\r\n======FIN TEST APPARTIENT======\r\n";
	
?>
