<?php
	include_once("../src/BDD_Externe_Connexion.php");
	
	include("../src/QuestionDAO.php");
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

	// Question
	$varDAO = new AppartientDAO();
	
	/*
	$client1 = new Client();
	$client1->init(NULL, "nom1", "prenom1", "adresse1", "email1", NULL, "H");
	$devis1 = new Devis();
	$devis1->init(NULL, "devis1");
	$appartient1 = new Appartient();
	$appartient1->init($client1, $devis1, null);

	echo $appartient1;
	*/

	foreach ($varDAO->tousLesElements("DP") as $str){
		//$varDAO->suppression($str, "DP");
		echo $str;
	}


?>