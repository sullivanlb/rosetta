<?php 
	header('Access-Control-Allow-Origin: *');
	header('Content-Type: application/json');

	include_once("../BDD_Externe_Connexion.php");

	include("../objets/AppartientDC.php");
	include("../dao/AppartientDCDAO.php");
	
	$connexion = new BDD_Externe_Connexion();
	
	$daoAppartientDC = new AppartientDCDAO();
	$result = $daoAppartientDC->tousLesElements();

	echo json_encode($result);
?>