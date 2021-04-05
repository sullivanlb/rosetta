<?php 
	header('Access-Control-Allow-Origin: *');
	header('Content-Type: application/json');

	include_once("../BDD_Externe_Connexion.php");

	include("../objets/Devis.php");
	include("../dao/DevisDAO.php");
	
	$connexion = new BDD_Externe_Connexion();
	
	$daoDevis = new DevisDAO();
	$result = $daoDevis->tousLesElements();

	echo json_encode($result);
?>