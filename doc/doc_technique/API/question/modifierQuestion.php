<?php 
	header('Access-Control-Allow-Origin: *');
	header('Content-Type: application/json');

	include_once("../BDD_Externe_Connexion.php");

	include("../objets/Question.php");
	include("../dao/QuestionDAO.php");
	
	$connexion = new BDD_Externe_Connexion();

	if (isset($_POST["idQuestion"]) && isset($_POST["nomQuestion"])) {
		$idQuestion = $_POST["idQuestion"];
		$nomQuestion = $_POST["nomQuestion"];

		$daoQuestion = new QuestionDAO();
		$question = new Question();
		$question->init($idQuestion, $nomQuestion);

		$daoQuestion->modification($question);
	}
?>