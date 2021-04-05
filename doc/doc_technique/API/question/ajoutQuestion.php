<?php 
	header('Access-Control-Allow-Origin: *');
	header('Content-Type: application/json');

	include_once("../BDD_Externe_Connexion.php");

	include("../objets/Question.php");
	include("../dao/QuestionDAO.php");
	
	$connexion = new BDD_Externe_Connexion();
	
	// Ajout d'une nouvelle question dans la base de données depuis le formulaire du site
	if (isset($_POST["nomQuestion"])) {
		$nomNouveauQuestion = $_POST["nomQuestion"];
	
		$daoQuestion = new QuestionDAO();
		$question = new Question();
		$question->init(null, $nomNouveauQuestion);
		$daoQuestion->insertion($question);
	}

?>