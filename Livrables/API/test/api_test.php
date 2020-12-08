<?php
	include("SqliteConnection.php");
	
	include("Question.php");
	include("QuestionDAO.php");
	include("Connexion.php");
	include("ConnexionDAO.php");
	include("Devis.php");
	include("DevisDAO.php");
	include("Pack.php");
	include("PackDAO.php");
	include("Composant.php");
	include("ComposantDAO.php");
	include("Scenario.php");
	include("ScenarioDAO.php");
	include("Client.php");
	include("ClientDAO.php");
	
	$connexion = new SqliteConnection();
	echo "Connexion réussie<br/>";

	// Question
	$question1 = new Question();
	$question1->init(0, "quest1");
	
	#TEST 1 : insert de question
	echo 'insertion question';

	$questionDAO = new QuestionDAO();
	$questionDAO->insert($question1);
	
	#TEST2 : update de question
	echo $question1;
	
	$question1->_set($nomQuestion, "question updated");
	$questionDAO->update($question1);

	foreach ($questionDAO->findAll() as $str){
		echo $str;
	}

	#TEST3 : suppression de question
	$questionDAO->delete($question1);
	
	foreach ($questionDAO->findAll() as $str){
		echo $str;
	}

	// Connexion
	$connexion1 = new Connexion();
	$connexion1->init(0, "mdp1");
	
	#TEST 1 : insert de connexion
	echo 'insertion connexion';

	$connexionDAO = new ConnexionDAO();
	$connexionDAO->insert($connexion1);
	
	#TEST2 : update de connexion
	echo $connexion1;
	
	$connexion1->_set($mdp, "connexionUpdated");
	$connexionDAO->update($connexion1);

	foreach ($connexionDAO->findAll() as $str){
		echo $str;
	}

	#TEST3 : suppression de connexion
	$connexionDAO->delete($connexion1);
	
	foreach ($connexionDAO->findAll() as $str){
		echo $str;
	}

	// Devis
	$devis1 = new Devis();
	$devis1->init(0, "devis1");
	
	#TEST 1 : insert de devis
	echo 'insertion devis';

	$devisDAO = new DevisDAO();
	$devisDAO->insert($devis1);
	
	#TEST2 : update de devis
	echo $devis1;
	
	$devis1->_set($nomDevis, "devis updated");
	$devisDAO->update($devis1);

	foreach ($devisDAO->findAll() as $str){
		echo $str;
	}

	#TEST3 : suppression de devis
	$devisDAO->delete($devis1);
	
	foreach ($devisDAO->findAll() as $str){
		echo $str;
	}

	// Pack
	$pack1 = new Pack();
	$pack1->init(0, "pack1");
	
	#TEST 1 : insert de pack
	echo 'insertion pack';

	$packDAO = new PackDAO();
	$packDAO->insert($pack1);
	
	#TEST2 : update de pack
	echo $pack1;
	
	$pack1->_set($nomPack, "pack updated");
	$packDAO->update($pack1);

	foreach ($packDAO->findAll() as $str){
		echo $str;
	}

	#TEST3 : suppression de pack
	$packDAO->delete($pack1);
	
	foreach ($packDAO->findAll() as $str){
		echo $str;
	}

	// Composant
	$composant1 = new Composant();
	$composant1->init(0, "composant1", "metre1", 10);
	
	#TEST 1 : insert de composant
	echo 'insertion composant';

	$composantDAO = new ComposantDAO();
	$composantDAO->insert($composant1);
	
	#TEST2 : update de composant
	echo $composant1;
	
	$composant1->_set($prixComposant, 100);
	$composantDAO->update($composant1);

	foreach ($composantDAO->findAll() as $str){
		echo $str;
	}

	#TEST3 : suppression de composant
	$composantDAO->delete($composant1);
	
	foreach ($composantDAO->findAll() as $str){
		echo $str;
	}

	// Scenario
	$scenario1 = new Scenario();
	$scenario1->init(0, "scenario1");
	
	#TEST 1 : insert de scenario
	echo 'insertion scenario';

	$scenarioDAO = new ScenarioDAO();
	$scenarioDAO->insert($scenario1);
	
	#TEST2 : update de scenario
	echo $scenario1;
	
	$scenario1->_set($nomScenario, "scenario updated");
	$scenarioDAO->update($scenario1);

	foreach ($scenarioDAO->findAll() as $str){
		echo $str;
	}

	#TEST3 : suppression de scenario
	$scenarioDAO->delete($scenario1);
	
	foreach ($scenarioDAO->findAll() as $str){
		echo $str;
	}

	// Client
	$client1 = new Client();
	$client1->init(0, "nom1", "prenom1", "adresse1", "email1", NULL);
	
	#TEST 1 : insert de client
	echo 'insertion client';

	$clientDAO = new ClientDAO();
	$clientDAO->insert($client1);
	
	#TEST2 : update de client
	echo $client1;
	
	$client1->_set($prenomClient, "prenom updated");
	$clientDAO->update($client1);

	foreach ($clientDAO->findAll() as $str){
		echo $str;
	}

	#TEST3 : suppression de client
	$clientDAO->delete($client1);
	
	foreach ($clientDAO->findAll() as $str){
		echo $str;
	}
	
?>
