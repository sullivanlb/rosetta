<?php 
	header('Access-Control-Allow-Origin: *');
	header('Content-Type: application/json');

	include_once("../BDD_Externe_Connexion.php");

	include("../objets/Pack.php");
	include("../dao/PackDAO.php");
	
	$connexion = new BDD_Externe_Connexion();
	
	// Ajout d'un nouveau pack dans la base de données depuis le formulaire du site
	if (isset($_POST["nom"])) {
		$nomNouveauPack = $_POST["nom"];
	
		$daoPack = new PackDAO();
		$pack = new Pack();
		$pack->init(null, $nomNouveauPack);
		$daoPack->insertion($pack);
	}

?>