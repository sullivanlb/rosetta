<?php 
	header('Access-Control-Allow-Origin: *');
	header('Content-Type: application/json');

	include_once("../BDD_Externe_Connexion.php");

	include("../objets/AppartientPC.php");
	include("../dao/AppartientPCDAO.php");
	
	$connexion = new BDD_Externe_Connexion();
	
	$daoAppartientPC = new AppartientPCDAO();
	$result = $daoAppartientPC->tousLesElements();

	echo json_encode($result);
?>