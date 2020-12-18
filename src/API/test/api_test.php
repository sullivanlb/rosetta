<?php
	include_once("../BDD_Externe_Connexion.php");
	
	include("../Question.php");
	include("../QuestionDAO.php");
	include("../Connexion.php");
	include("../Devis.php");
	include("../DevisDAO.php");
	include("../Pack.php");
	include("../PackDAO.php");
	include("../Composant.php");
	include("../ComposantDAO.php");
	include("../Scenario.php");
	include("../ScenarioDAO.php");
	include("../Client.php");
	include("../ClientDAO.php");
	include("../AppartientCD.php");
	include("../AppartientCDDAO.php");
	include("../AppartientDC.php");
	include("../AppartientDCDAO.php");
	include("../AppartientDP.php");
	include("../AppartientDPDAO.php");
	include("../AppartientPC.php");
	include("../AppartientPCDAO.php");
	include("../AppartientSC.php");
	include("../AppartientSCDAO.php");
	include("../AppartientSP.php");
	include("../AppartientSPDAO.php");
	include("../AppartientSQ.php");
	include("../AppartientSQDAO.php");
	
	$connexion = new BDD_Externe_Connexion();
	echo "Connexion réussie\r\n";

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

	// AppartientCD
	echo "\r\n======TEST APPARTIENTCD======\r\n";
	$client1 = new Client();
	$client1->init(NULL, "nom1", "prenom1", "adresse1", "email1", NULL, "H");
	$clientDAO = new ClientDAO();

	$devis1 = new Devis();
	$devis1->init(NULL, "devis1");
	$devisDAO = new DevisDAO();

	$appartient1 = new AppartientCD();
	$appartient1->init($client1, $devis1, null);
	$appartientCDDAO = new AppartientCDDAO();
	
	#TEST 1 : insertion des 2 liaisons AppartientCD
	echo "\r\nInsertion des 2 liaisons ainsi que le client et son devis\r\n";

	$clientDAO->insertion($client1);
	$devisDAO->insertion($devis1);
	$appartientCDDAO->insertion($appartient1);

	echo "\r\nAffichage des liaisons insérées\r\n";
	foreach ($appartientCDDAO->tousLesElements() as $str){
		echo $str;
	}
    
	#TEST2 : suppression des liaisons
	$appartientCDDAO->suppression($appartient1);
	$clientDAO->suppression($client1);
	$devisDAO->suppression($devis1);

	echo "\r\nContenu après suppression\r\n";	
	foreach ($appartientCDDAO->tousLesElements() as $str){
		echo $str;
	}
	
	echo "\r\n======FIN TEST APPARTIENTCD======\r\n";

	// AppartientDC
	echo "\r\n======TEST APPARTIENTDC======\r\n";
	$devis1 = new Devis();
	$devis1->init(NULL, "devis1");
	$devisDAO = new DevisDAO();

	$composant1 = new Composant();
	$composant1->init(NULL, "composant1", "metre1", 19.99);
	$composantDAO = new ComposantDAO();

	$appartient1 = new AppartientDC();
	$appartient1->init($devis1, $composant1, 5);
	$appartientDCDAO = new AppartientDCDAO();
	
	#TEST 1 : insertion des 2 liaisons AppartientDC
	echo "\r\nInsertion des 2 liaisons ainsi que le devis et ses composants\r\n";

	$devisDAO->insertion($devis1);
	$composantDAO->insertion($composant1);
	$appartientDCDAO->insertion($appartient1);

	echo "\r\nAffichage des liaisons avant modif\r\n";
	foreach ($appartientDCDAO->tousLesElements() as $str){
		echo $str;
	}

	
	#TEST2 : modification des liaisons
	echo "\r\nModification de la quantite de appartient1\r\n";
	$appartient1->setQuantite(200);
	$appartientDCDAO->modification($appartient1);

	echo "\r\nAffichage des liaisons après modif\r\n";	
	foreach ($appartientDCDAO->tousLesElements() as $str){
		echo $str;
	}
    
	#TEST3 : suppression des liaisons
	$appartientDCDAO->suppression($appartient1);
	$composantDAO->suppression($composant1);
	$devisDAO->suppression($devis1);

	echo "\r\nContenu après suppression\r\n";	
	foreach ($appartientDCDAO->tousLesElements() as $str){
		echo $str;
	}
	
	echo "\r\n======FIN TEST APPARTIENTDC======\r\n";

	// AppartientDP
	echo "\r\n======TEST APPARTIENTDP======\r\n";
	$devis1 = new Devis();
	$devis1->init(NULL, "devis1");
	$devisDAO = new DevisDAO();

	$pack1 = new Pack();
	$pack1->init(NULL, "pack1");
	$packDAO = new PackDAO();

	$appartient1 = new AppartientDP();
	$appartient1->init($devis1, $pack1, 15);
	$appartientDPDAO = new AppartientDPDAO();
	
	#TEST 1 : insertion des 2 liaisons AppartientDP
	echo "\r\nInsertion des 2 liaisons ainsi que le devis et ses packs\r\n";

	$devisDAO->insertion($devis1);
	$packDAO->insertion($pack1);
	$appartientDPDAO->insertion($appartient1);

	echo "\r\nAffichage des liaisons avant modif\r\n";
	foreach ($appartientDPDAO->tousLesElements() as $str){
		echo $str;
	}

	#TEST2 : modification des liaisons
	echo "\r\nModification de la quantite de appartient1\r\n";
	$appartient1->setQuantite(45);
	$appartientDPDAO->modification($appartient1);

	echo "\r\nAffichage des liaisons après modif\r\n";	
	foreach ($appartientDPDAO->tousLesElements() as $str){
		echo $str;
	}
    
	#TEST3 : suppression des liaisons
	$appartientDPDAO->suppression($appartient1);
	$packDAO->suppression($pack1);
	$devisDAO->suppression($devis1);

	echo "\r\nContenu après suppression\r\n";	
	foreach ($appartientDPDAO->tousLesElements() as $str){
		echo $str;
	}
	
	echo "\r\n======FIN TEST APPARTIENTDC======\r\n";
	
	// AppartientPC
	echo "\r\n======TEST APPARTIENTPC======\r\n";
	$pack1 = new Pack();
	$pack1->init(NULL, "pack1");
	$packDAO = new PackDAO();

	$composant1 = new Composant();
	$composant1->init(NULL, "composant1", "metre1", 19.99);
	$composantDAO = new ComposantDAO();

	$appartient1 = new AppartientPC();
	$appartient1->init($pack1, $composant1, 3);
	$appartientPCDAO = new AppartientPCDAO();
	
	#TEST 1 : insertion des 2 liaisons appartientPC
	echo "\r\nInsertion des 2 liaisons ainsi que le pack et ses composants\r\n";

	$packDAO->insertion($pack1);
	$composantDAO->insertion($composant1);
	$appartientPCDAO->insertion($appartient1);

	echo "\r\nAffichage des liaisons avant modif\r\n";
	foreach ($appartientPCDAO->tousLesElements() as $str){
		echo $str;
	}

	#TEST2 : modification des liaisons
	echo "\r\nModification de la quantite de appartient1\r\n";
	$appartient1->setQuantite(30);
	$appartientPCDAO->modification($appartient1);

	echo "\r\nAffichage des liaisons après modif\r\n";	
	foreach ($appartientPCDAO->tousLesElements() as $str){
		echo $str;
	}
    
	#TEST3 : suppression des liaisons
	$appartientPCDAO->suppression($appartient1);
	$packDAO->suppression($pack1);
	$composantDAO->suppression($composant1);

	echo "\r\nContenu après suppression\r\n";	
	foreach ($appartientPCDAO->tousLesElements() as $str){
		echo $str;
	}
	
	echo "\r\n======FIN TEST APPARTIENTPC======\r\n";

	// AppartientSC
	echo "\r\n======TEST APPARTIENTSC======\r\n";
	$scenario1 = new Scenario();
	$scenario1->init(NULL, "scenario1");
	$scenarioDAO = new ScenarioDAO();

	$composant1 = new Composant();
	$composant1->init(NULL, "composant1", "metre1", 0.99);
	$composantDAO = new ComposantDAO();

	$appartient1 = new AppartientSC();
	$appartient1->init($scenario1, $composant1, 3);
	$appartientSCDAO = new AppartientSCDAO();
	
	#TEST 1 : insertion des 2 liaisons appartientSC
	echo "\r\nInsertion des 2 liaisons ainsi que le scénario et ses composants\r\n";

	$scenarioDAO->insertion($scenario1);
	$composantDAO->insertion($composant1);
	$appartientSCDAO->insertion($appartient1);

	echo "\r\nAffichage des liaisons avant modif\r\n";
	foreach ($appartientSCDAO->tousLesElements() as $str){
		echo $str;
	}

	#TEST2 : modification des liaisons
	echo "\r\nModification de la quantite de appartient1\r\n";
	$appartient1->setQuantite(14);
	$appartientSCDAO->modification($appartient1);

	echo "\r\nAffichage des liaisons après modif\r\n";	
	foreach ($appartientSCDAO->tousLesElements() as $str){
		echo $str;
	}
    
	#TEST3 : suppression des liaisons
	$appartientSCDAO->suppression($appartient1);
	$scenarioDAO->suppression($scenario1);
	$composantDAO->suppression($composant1);

	echo "\r\nContenu après suppression\r\n";	
	foreach ($appartientSCDAO->tousLesElements() as $str){
		echo $str;
	}
	
	echo "\r\n======FIN TEST APPARTIENTSC======\r\n";

	// AppartientSP
	echo "\r\n======TEST APPARTIENTSP======\r\n";
	$scenario1 = new Scenario();
	$scenario1->init(NULL, "scenario1");
	$scenarioDAO = new ScenarioDAO();

	$pack1 = new Pack();
	$pack1->init(NULL, "pack1");
	$packDAO = new PackDAO();

	$appartient1 = new AppartientSP();
	$appartient1->init($scenario1, $pack1, 100);
	$appartientSPDAO = new AppartientSPDAO();
	
	#TEST 1 : insertion des 2 liaisons appartientSP
	echo "\r\nInsertion des 2 liaisons ainsi que le scénario et ses packs\r\n";

	$scenarioDAO->insertion($scenario1);
	$packDAO->insertion($pack1);
	$appartientSPDAO->insertion($appartient1);

	echo "\r\nAffichage des liaisons avant modif\r\n";
	foreach ($appartientSPDAO->tousLesElements() as $str){
		echo $str;
	}

	#TEST2 : modification des liaisons
	echo "\r\nModification de la quantite de appartient1\r\n";
	$appartient1->setQuantite(7);
	$appartientSPDAO->modification($appartient1);

	echo "\r\nAffichage des liaisons après modif\r\n";	
	foreach ($appartientSPDAO->tousLesElements() as $str){
		echo $str;
	}
    
	#TEST3 : suppression des liaisons
	$appartientSPDAO->suppression($appartient1);
	$scenarioDAO->suppression($scenario1);
	$packDAO->suppression($pack1);

	echo "\r\nContenu après suppression\r\n";	
	foreach ($appartientSPDAO->tousLesElements() as $str){
		echo $str;
	}
	
	echo "\r\n======FIN TEST APPARTIENTPC======\r\n";

	// AppartientSQ
	echo "\r\n======TEST APPARTIENTSQ======\r\n";
	$scenario1 = new Scenario();
	$scenario1->init(NULL, "scenario1");
	$scenarioDAO = new ScenarioDAO();

	$question1 = new Question();
	$question1->init(NULL, "question1");
	$questionDAO = new QuestionDAO();

	$appartient1 = new AppartientSQ();
	$appartient1->init($scenario1, $question1, null);
	$appartientSQDAO = new AppartientSQDAO();
	
	#TEST 1 : insertion des 2 liaisons AppartientSQ
	echo "\r\nInsertion des 2 liaisons ainsi que le scénario et ses questions\r\n";

	$scenarioDAO->insertion($scenario1);
	$questionDAO->insertion($question1);
	$appartientSQDAO->insertion($appartient1);

	echo "\r\nAffichage des liaisons insérées\r\n";
	foreach ($appartientSQDAO->tousLesElements() as $str){
		echo $str;
	}
    
	#TEST2 : suppression des liaisons
	$appartientSQDAO->suppression($appartient1);
	$questionDAO->suppression($question1);
	$scenarioDAO->suppression($scenario1);

	echo "Contenu après suppression\r\n";	
	foreach ($appartientSQDAO->tousLesElements() as $str){
		echo $str;
	}
	
	echo "\r\n======FIN TEST APPARTIENTSQ======\r\n";
?>
