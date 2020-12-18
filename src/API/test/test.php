<?php
	include_once("../BDD_Externe_Connexion.php");
	
	include("../QuestionDAO.php");
	include("../Question.php");
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
	
	$connexion = new BDD_Externe_Connexion();
	echo "Connexion réussie\r\n";

	// Question
	$varDAO = new AppartientCDDAO();

	foreach ($varDAO->tousLesElements() as $str){
		$varDAO->suppression($str);
		//echo $str;
	}


?>