<?php 
  	header("Access-Control-Allow-Methods: GET, POST, OPTIONS, DELETE");
  	header('Access-Control-Allow-Origin: *');
	header('Content-Type: application/json');

	include_once("../BDD_Externe_Connexion.php");

	include("../objets/Question.php");
	include("../dao/QuestionDAO.php");
	
	$connexion = new BDD_Externe_Connexion();

	if ($_SERVER['REQUEST_METHOD'] === 'DELETE') {
		$index = basename($_SERVER['REQUEST_URI']);
		
		$daoQuestion = new QuestionDAO();
		$question = new Question();
		$question = $daoQuestion->unElement($index);

		$daoQuestion->suppression($question[0]);
	}
?>