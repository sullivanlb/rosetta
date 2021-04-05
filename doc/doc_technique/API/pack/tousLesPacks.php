<?php 
	header('Access-Control-Allow-Origin: *');
	header('Content-Type: application/json');

	include_once("../BDD_Externe_Connexion.php");

	include("../objets/Pack.php");
	include("../dao/PackDAO.php");
	
	$connexion = new BDD_Externe_Connexion();
	
	$daoPack = new PackDAO();
	$result = $daoPack->tousLesElements();

	echo json_encode($result);
?>