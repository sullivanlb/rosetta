<?php 
  	header("Access-Control-Allow-Methods: GET, POST, OPTIONS, DELETE");
  	header('Access-Control-Allow-Origin: *');
	header('Content-Type: application/json');

	include_once("../BDD_Externe_Connexion.php");

	include("../objets/Composant.php");
	include("../dao/ComposantDAO.php");
	
	$connexion = new BDD_Externe_Connexion();

	if ($_SERVER['REQUEST_METHOD'] === 'DELETE') {
		$index = basename($_SERVER['REQUEST_URI']);
		
		$daoComposant = new ComposantDAO();
		$composant = new Composant();
		$composant = $daoComposant->unElement($index);

		$daoComposant->suppression($composant[0]);
	}
?>