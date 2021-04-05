<?php 
	header('Access-Control-Allow-Origin: *');
	header('Content-Type: application/json');

	include_once("../BDD_Externe_Connexion.php");

	include("../objets/Pack.php");
	include("../dao/PackDAO.php");
	
	$connexion = new BDD_Externe_Connexion();


	if (isset($_POST["id"]) && isset($_POST["nom"])) {
		$idPack = $_POST["id"];
		$nomPack = $_POST["nom"];

		$daoPack = new PackDAO();
		$pack = new Pack();
		$pack->init($idPack, $nomPack);

		$daoPack->modification($pack);
	}
?>