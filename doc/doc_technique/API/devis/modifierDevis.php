<?php 
	header('Access-Control-Allow-Origin: *');
	header('Content-Type: application/json');

	include_once("../BDD_Externe_Connexion.php");

	include("../objets/Devis.php");
	include("../dao/DevisDAO.php");
	include("../objets/Client.php");
	include("../dao/ClientDAO.php");
	include("../objets/Scenario.php");
	include("../dao/ScenarioDAO.php");
	include("../objets/Composant.php");
	include("../dao/ComposantDAO.php");
	include("../objets/Pack.php");
	include("../dao/PackDAO.php");
	include("../objets/AppartientCD.php");
	include("../dao/AppartientCDDAO.php");
	include("../objets/AppartientSC.php");
	include("../dao/AppartientSCDAO.php");
	include("../objets/AppartientSP.php");
	include("../dao/AppartientSPDAO.php");
	include("../objets/AppartientDC.php");
	include("../dao/AppartientDCDAO.php");
	include("../objets/AppartientDP.php");
	include("../dao/AppartientDPDAO.php");
	
	$connexion = new BDD_Externe_Connexion();


	if (isset($_POST["id"]) && isset($_POST["nom"]) && isset($_POST["description"]) && isset($_POST["duree"]) && isset($_POST["dateEdition"]) 
	&& isset($_POST["dateTravaux"]) && isset($_POST["leClient"]) && isset($_POST["leScenario"])) {
		$idDevis = $_POST["id"];
		$nomDevis = $_POST["nom"];
		$descriptionDevis = $_POST["description"];
		$dureeDevis = $_POST["duree"];
		$dateEditionDevis = $_POST["dateEdition"];
		$dateTravauxDevis = $_POST["dateTravaux"];
		$leClient = $_POST["leClient"];
		$leScenario = $_POST["leScenario"];

		$daoDevis = new DevisDAO();
		$devis = new Devis();
		$devis->init($idDevis, $nomDevis, $descriptionDevis, $dateEditionDevis, $dateTravauxDevis, $leClient, $leScenario);

		$daoDevis->modification($devis);
	}
?>