<?php 
  	header("Access-Control-Allow-Methods: GET, POST, OPTIONS, DELETE");
  	header('Access-Control-Allow-Origin: *');
	header('Content-Type: application/json');

	include_once("../BDD_Externe_Connexion.php");

	include("../objets/Client.php");
	include("../dao/ClientDAO.php");
	include("../objets/AppartientCD.php");
	include("../dao/AppartientCDDAO.php");
	include("../objets/Devis.php");
	include("../dao/DevisDAO.php");
	
	$connexion = new BDD_Externe_Connexion();
	$status = 'FAILED';

	if ($_SERVER['REQUEST_METHOD'] === 'DELETE') {
		$index = basename($_SERVER['REQUEST_URI']);
		
		echo json_encode(array("response"=>$index));

		$daoClient = new ClientDAO();
		$client = new Client();
		$client = $daoClient->unElement($index);

		// Suppression des liaisons avec ses devis et ses devis
		$daoAppartientCD = new AppartientCDDAO();
		$appartientCD = new AppartientCD();
		$appartientCD = $daoAppartientCD->unElement($client[0]);
		$daoDevis = new DevisDAO();
		$devis = $daoDevis->tousLesElements();
		foreach ($appartientCD as $liaison) {
			foreach ($devis as $leD) {
				if ($leD->__get("id") == $liaison->__get("unDevis")) {
					$daoDevis->suppression($leD);
				}
			}
			$daoAppartientCD->suppression($liaison);
		}

		$daoClient->suppression($client[0]);
		$status = 'OK';
	}

	// echo json_encode(array("response"=>$status));
?>