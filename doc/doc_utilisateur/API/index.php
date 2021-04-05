<?php 
	header('Access-Control-Allow-Origin: *');
	header('Content-Type: application/json');

	include_once("BDD_Externe_Connexion.php");
	
	include("objets/Question.php");
	include("dao/QuestionDAO.php");
	include("objets/Connexion.php");
	include("objets/Devis.php");
	include("dao/DevisDAO.php");
	include("objets/Pack.php");
	include("dao/PackDAO.php");
	include("objets/Composant.php");
	include("dao/ComposantDAO.php");
	include("objets/Scenario.php");
	include("dao/ScenarioDAO.php");
	include("objets/Client.php");
	include("dao/ClientDAO.php");
	include("objets/AppartientCD.php");
	include("dao/AppartientCDDAO.php");
	include("objets/AppartientDC.php");
	include("dao/AppartientDCDAO.php");
	include("objets/AppartientDP.php");
	include("dao/AppartientDPDAO.php");
	include("objets/AppartientPC.php");
	include("dao/AppartientPCDAO.php");
	include("objets/AppartientSC.php");
	include("dao/AppartientSCDAO.php");
	include("objets/AppartientSP.php");
	include("dao/AppartientSPDAO.php");
	include("objets/AppartientSQ.php");
	include("dao/AppartientSQDAO.php");
	
	$connexion = new BDD_Externe_Connexion();
	
	$daoClient = new ClientDAO();
	$result = $daoClient->tousLesElements();

	echo "insert";
	
	// Ajout d'un nouveau client dans la base de données depuis le formulaire du site
	if (isset($_POST["nom"]) && isset($_POST["prenom"]) && isset($_POST["adresse"]) && isset($_POST["email"]) && isset($_POST["tel"]) && isset($_POST["sexe"])) {
		$nomNouveauClient = $_POST["nom"];
		$prenomNouveauClient = $_POST["prenom"];
		$adresseNouveauClient = $_POST["adresse"];
		$emailNouveauClient = $_POST["email"];
		$telNouveauClient = $_POST["tel"];
		$sexeNouveauClient = $_POST["sexe"];
	
		$daoClient = new ClientDAO();
		$client = new Client();
		$client->init(null, $nomNouveauClient, $prenomNouveauClient, $adresseNouveauClient, $emailNouveauClient, $telNouveauClient, $sexeNouveauClient);
		$daoClient->insertion($client);

		foreach($daoClient->tousLesElements() as $client) {
			echo $client;
		}
	}

?>