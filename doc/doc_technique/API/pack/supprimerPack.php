<?php 
  	header("Access-Control-Allow-Methods: GET, POST, OPTIONS, DELETE");
  	header('Access-Control-Allow-Origin: *');
	header('Content-Type: application/json');

	include_once("../BDD_Externe_Connexion.php");

	include("../objets/Pack.php");
	include("../dao/PackDAO.php");
	
	$connexion = new BDD_Externe_Connexion();

	if ($_SERVER['REQUEST_METHOD'] === 'DELETE') {
		$index = basename($_SERVER['REQUEST_URI']);
		
		$daoPack = new PackDAO();
		$pack = new Pack();
		$pack = $daoPack->unElement($index);

		$daoPack->suppression($pack[0]);
	}
?>