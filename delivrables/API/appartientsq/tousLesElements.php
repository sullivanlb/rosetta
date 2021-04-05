<?php 
	header('Access-Control-Allow-Origin: *');
	header('Content-Type: application/json');

	include_once("../BDD_Externe_Connexion.php");

	include("../objets/AppartientSQ.php");
	include("../dao/AppartientSQDAO.php");
	
	$connexion = new BDD_Externe_Connexion();
	
	$daoAppartientSQ = new AppartientSQDAO();
	$result = $daoAppartientSQ->tousLesElements();

	echo json_encode($result);
?>