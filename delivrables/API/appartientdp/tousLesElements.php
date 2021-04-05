<?php 
	header('Access-Control-Allow-Origin: *');
	header('Content-Type: application/json');

	include_once("../BDD_Externe_Connexion.php");

	include("../objets/AppartientDP.php");
	include("../dao/AppartientDPDAO.php");
	
	$connexion = new BDD_Externe_Connexion();
	
	$daoAppartientDP = new AppartientDPDAO();
	$result = $daoAppartientDP->tousLesElements();

	echo json_encode($result);
?>