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


?>