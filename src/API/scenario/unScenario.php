<?php 
	header('Access-Control-Allow-Origin: *');
	header('Content-Type: application/json');

	include_once("../BDD_Externe_Connexion.php");

	include("../objets/Scenario.php");
	include("../dao/ScenarioDAO.php");
	
	$connexion = new BDD_Externe_Connexion();

	echo $_POST['id'];
	
	// $daoClient = new ClientDAO();
	// $result = $daoClient->tousLesElements();

	// echo json_encode($result);
?>