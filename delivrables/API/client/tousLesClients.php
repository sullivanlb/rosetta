<?php 
	header('Access-Control-Allow-Origin: *');
	header('Content-Type: application/json');

	include_once("../BDD_Externe_Connexion.php");

	include("../objets/Client.php");
	include("../dao/ClientDAO.php");
	
	$connexion = new BDD_Externe_Connexion();
	
	$daoClient = new ClientDAO();
	$result = $daoClient->tousLesElements();

	echo json_encode($result);
?>