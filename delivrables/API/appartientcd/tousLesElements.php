<?php 
	header('Access-Control-Allow-Origin: *');
	header('Content-Type: application/json');

	include_once("../BDD_Externe_Connexion.php");

	include("../objets/AppartientCD.php");
	include("../dao/AppartientCDDAO.php");
	
	$connexion = new BDD_Externe_Connexion();
	
	$daoAppartientCD = new AppartientCDDAO();
	$result = $daoAppartientCD->tousLesElements();

	echo json_encode($result);
?>