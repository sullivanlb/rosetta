<?php 
	header('Access-Control-Allow-Origin: *');
	header('Content-Type: application/json');

	include_once("../BDD_Externe_Connexion.php");

	include("../objets/Question.php");
	include("../dao/QuestionDAO.php");
	
	$connexion = new BDD_Externe_Connexion();
	
	$daoQuestion = new QuestionDAO();
	$result = $daoQuestion->tousLesElements();

	echo json_encode($result);
?>