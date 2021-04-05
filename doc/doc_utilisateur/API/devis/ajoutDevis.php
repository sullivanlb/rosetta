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

	if (isset($_POST["nom"]) && isset($_POST["description"]) && isset($_POST["duree"]) && isset($_POST["dateEdition"]) && isset($_POST["dateTravaux"]) && isset($_POST["leClient"])) {
		
		// création du devis
		$nomDevis = $_POST["nom"];
		$descriptionDevis = $_POST["description"];
		$dureeDevis = $_POST["duree"];
		$dateEditionDevis = $_POST["dateEdition"];
		$dateTravauxDevis = $_POST["dateTravaux"];

		// insertion du devis
		$daoDevis = new DevisDAO();
		$devis = new Devis();
		$devis->init(NULL, $nomDevis, $descriptionDevis, $dureeDevis, $dateEditionDevis, $dateTravauxDevis);
		$daoDevis->insertion($devis);

		// création du client
		$clientDAO = new ClientDAO();
		$clientArr = $clientDAO->unElement($_POST["leClient"]);

		// insertion de la liaison client-devis
		$appartientCD = new AppartientCD();
		$appartientCD->init($clientArr[0], $devis);
		$appartientCDDAO = new AppartientCDDAO();
		$appartientCDDAO->insertion($appartientCD);

		// création du scénario
		$scenarioDAO = new ScenarioDAO();
		$scenario = new Scenario();
		$scenario = $scenarioDAO->unElement($_POST["leScenario"]);

		// récupération des composants du scénario
		$appartientSCDAO = new AppartientSCDAO();
		$composants = $appartientSCDAO->unElement($scenario[0]);

		// récupération des packs du scénario
		$appartientSPDAO = new AppartientSPDAO();
		$packs = $appartientSPDAO->unElement($scenario[0]);

		// création et insertion de la liaison devis-composant
		$appartientDCDAO = new AppartientDCDAO();
		$appartientDC = new AppartientDC();
		$composantDAO = new ComposantDAO();
		for ($i = 0; $i < count($composants); $i++) {
			$idComposant = $composants[$i]->__get("unComposant");
			$composant = $composantDAO->unElement($idComposant);
			$appartientDC->init($devis, $composant[0], intval($composants[$i]->__get("quantite")));
			$appartientDCDAO->insertion($appartientDC);
		}

		// création et insertion de la liaison devis-packs
		$appartientDPDAO = new AppartientDPDAO();
		$appartientDP = new AppartientDP();
		$packDAO = new PackDAO();
		for ($i = 0; $i < count($packs); $i++) {
			$idPack = $packs[$i]->__get("unPack");
			$pack = $packDAO->unElement($idPack);
			$appartientDP->init($devis, $pack[0], intval($packs[$i]->__get("quantite")));
			$appartientDPDAO->insertion($appartientDP);
		}
	}
