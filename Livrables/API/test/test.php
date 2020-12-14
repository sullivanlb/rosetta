<?php
	include_once("../src/BDD_Externe_Connexion.php");
	
	include("../src/QuestionDAO.php");
	include("../src/Question.php");
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
	include("../src/AppartientCD.php");
	include("../src/AppartientCDDAO.php");
	
	$connexion = new BDD_Externe_Connexion();
	echo "Connexion réussie\r\n";

	// Question
	$varDAO = new AppartientCDDAO();

	foreach ($varDAO->tousLesElements() as $str){
		$varDAO->suppression($str);
		//echo $str;
	}


?>