<?php 
	header('Access-Control-Allow-Origin: *');
	header('Content-Type: application/json');

	include_once("../BDD_Externe_Connexion.php");

	include("../objets/Composant.php");
	include("../dao/ComposantDAO.php");
	
	$connexion = new BDD_Externe_Connexion();
	
	// Ajout d'un nouveau composant dans la base de données depuis le formulaire du site
	if (isset($_POST["nom"]) && isset($_POST["unite"]) && isset($_POST["prix"])) {
		$nomNouveauComposant = $_POST["nom"];
		$uniteNouveauComposant = $_POST["unite"];
		$prixNouveauComposant = $_POST["prix"];
	
		$daoComposant = new ComposantDAO();
		$composant = new Composant();
		$composant->init(null, $nomNouveauComposant, $uniteNouveauComposant, $prixNouveauComposant);
		$daoComposant->insertion($composant);
	}

?>