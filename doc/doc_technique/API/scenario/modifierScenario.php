<?php 
	header('Access-Control-Allow-Origin: *');
	header('Content-Type: application/json');

	include_once("../BDD_Externe_Connexion.php");

	include("../objets/Scenario.php");
	include("../dao/ScenarioDAO.php");
	
	$connexion = new BDD_Externe_Connexion();


	if (isset($_POST["id"]) && isset($_POST["nom"])) {
		$idScenario = $_POST["id"];
		$nomScenario = $_POST["nom"];

		$daoScenario = new ScenarioDAO();
		$scenario = new Scenario();
		$scenario->init($idScenario, $nomScenario);

		$daoScenario->modification($scenario);
	}
?>