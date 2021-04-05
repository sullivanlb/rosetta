<?php 
	header('Access-Control-Allow-Origin: *');
	header('Content-Type: application/json');

	include_once("../BDD_Externe_Connexion.php");

	include("../objets/AppartientSP.php");
	include("../dao/AppartientSPDAO.php");
	
	$connexion = new BDD_Externe_Connexion();
	
	$daoAppartientSP = new AppartientSPDAO();
	$result = $daoAppartientSP->tousLesElements();

	echo json_encode($result);
?>