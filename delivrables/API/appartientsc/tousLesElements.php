<?php 
	header('Access-Control-Allow-Origin: *');
	header('Content-Type: application/json');

	include_once("../BDD_Externe_Connexion.php");

	include("../objets/AppartientSC.php");
	include("../dao/AppartientSCDAO.php");
	
	$connexion = new BDD_Externe_Connexion();
	
	$daoAppartientSC = new AppartientSCDAO();
	$result = $daoAppartientSC->tousLesElements();

	echo json_encode($result);
?>