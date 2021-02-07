<<<<<<< HEAD
<?php header('Access-Control-Allow-Origin: *');
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
	}

=======
<?php
	include_once("BDD_Externe_Connexion.php");
	
	include("Question.php");
	include("QuestionDAO.php");
	include("Connexion.php");
	include("Devis.php");
	include("DevisDAO.php");
	include("Pack.php");
	include("PackDAO.php");
	include("Composant.php");
	include("ComposantDAO.php");
	include("Scenario.php");
	include("ScenarioDAO.php");
	include("Client.php");
	include("ClientDAO.php");
	include("AppartientCD.php");
	include("AppartientCDDAO.php");
	include("AppartientDC.php");
	include("AppartientDCDAO.php");
	include("AppartientDP.php");
	include("AppartientDPDAO.php");
	include("AppartientPC.php");
	include("AppartientPCDAO.php");
	include("AppartientSC.php");
	include("AppartientSCDAO.php");
	include("AppartientSP.php");
	include("AppartientSPDAO.php");
	include("AppartientSQ.php");
	include("AppartientSQDAO.php");
	
	$connexion = BDD_Externe_Connexion.getInstance();
    echo "Connexion réussie\r\n";
    
    $daoClient = ClientDAO.getInstance();
    $daoComposant = ComposantDAO.getInstance();
    $daoDevis = DevisDAO.getInstance();
    $daoPack = PackDAO.getInstance();
    $daoScenario = ScenarioDAO.getInstance();
    $daoQuestion = QuestionDAO.getInstance();
    $daoAppartientCD = AppartientCDDAO.getInstance();
    $daoAppartientDC = AppartientDCDAO.getInstance();
    $daoAppartientDP = AppartientDPDAO.getInstance();
    $daoAppartientPC = AppartientPCDAO.getInstance();
    $daoAppartientSC = AppartientSCDAO.getInstance();
    $daoAppartientSP = AppartientSPDAO.getInstance();
    $daoAppartientSQ = AppartientSQDAO.getInstance();

    $nomNouveauClient = $_POST['nom'];

    $client = new Client();
    $client->init(0, $nomNouveauClient, "bob", "1, boulevard des airs", "client@gmail.com", "01", "homme");
    $daoClient->insertion($client);

	// Question
	$varDAO = new AppartientCDDAO();

	foreach ($varDAO->tousLesElements() as $str){
		$varDAO->suppression($str);
		//echo $str;
	}


>>>>>>> Site_Web_React
?>