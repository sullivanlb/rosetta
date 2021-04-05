<?php 
	header('Access-Control-Allow-Origin: *');
	header('Content-Type: application/json');

	include_once("../BDD_Externe_Connexion.php");

	include("../objets/Composant.php");
	include("../dao/ComposantDAO.php");
	
	$connexion = new BDD_Externe_Connexion();


	if (isset($_POST["id"]) && isset($_POST["nom"]) && isset($_POST["unite"]) && isset($_POST["prix"])) {
		$idComposant = $_POST["id"];
		$nomComposant = $_POST["nom"];
		$uniteComposant = $_POST["unite"];
		$prixComposant = $_POST["prix"];

		$daoComposant = new ComposantDAO();
		$composant = new Composant();
		$composant->init($idComposant, $nomComposant, $uniteComposant, $prixComposant);

		$daoComposant->modification($composant);
	}
?>