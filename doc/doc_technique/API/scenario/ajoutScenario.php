<?php 
	header('Access-Control-Allow-Origin: *');
	header('Content-Type: application/json');

	include_once("../BDD_Externe_Connexion.php");

	include("../objets/AppartientSQ.php");
	include("../dao/AppartientSQDAO.php");
	include("../objets/AppartientSC.php");
	include("../dao/AppartientSCDAO.php");
	include("../objets/AppartientSP.php");
	include("../dao/AppartientSPDAO.php");
	include("../objets/Scenario.php");
	include("../dao/ScenarioDAO.php");
	include("../objets/Question.php");
	include("../dao/QuestionDAO.php");
	include("../objets/Composant.php");
	include("../dao/ComposantDAO.php");
	include("../objets/Pack.php");
	include("../dao/PackDAO.php");
	
	$connexion = new BDD_Externe_Connexion();
	
	// Ajout d'un nouveau scenario dans la base de données depuis le formulaire du site
	if (isset($_POST["nom"]) && isset($_POST["questionsTaille"]) && isset($_POST["composantsTaille"]) && isset($_POST["packsTaille"])) {
		$nomNouveauScenario = $_POST["nom"];
		$questionsTaille = $_POST["questionsTaille"];
		$composantsTaille = $_POST["composantsTaille"];
		$packsTaille = $_POST["packsTaille"];
	
		// Insertion du scenario dans la bdd
		$daoScenario = new ScenarioDAO();
		$scenario = new Scenario();
		$scenario->init(NULL, $nomNouveauScenario);
		$daoScenario->insertion($scenario);

		// Liaison Scenario-Question
		for($i = 0; $i < $questionsTaille; $i++) {
			if (isset($_POST["question{$i}_nom"])) {

				// Création de la question + insertion dans la bdd
				$question = new Question();
				$question->init(NULL, $_POST["question{$i}_nom"]);
				$daoQuestion = new QuestionDAO();
				$daoQuestion->insertion($question);

				// Création de la laison scenario-question + insertion dans la bdd
				$liaisonScenarioQuestion = new AppartientSQ();
				$liaisonScenarioQuestion->init($scenario, $question, NULL);
				$daoAppartientSQ = new AppartientSQDAO();
				$daoAppartientSQ->insertion($liaisonScenarioQuestion);
			}
		}

		// Liaison Scenario-Composant
		for($i = 0; $i < $composantsTaille; $i++) {
			if (isset($_POST["composant{$i}_id"]) && isset($_POST["composant{$i}_quantite"])) {

				// Récupération du composant
				$daoComposant = new ComposantDAO();
				$composants = $daoComposant->unElement($_POST["composant{$i}_id"]);
				
				// Création de la laison scenario-composant + insertion dans la bdd
				foreach ($composants as $composant){
					$liaisonScenarioComposant = new AppartientSC();
					$liaisonScenarioComposant->init($scenario, $composant, intval($_POST["composant{$i}_quantite"]));
					$daoAppartientSC = new AppartientSCDAO();
					$daoAppartientSC->insertion($liaisonScenarioComposant);
				}
			}
		}

		// Liaison Scenario-Pack
		for($i = 0; $i < $packsTaille; $i++) {
			if (isset($_POST["pack{$i}_id"]) && isset($_POST["pack{$i}_quantite"])) {

				// Récupération du pack
				$daoPack = new PackDAO();
				$packs = $daoPack->unElement($_POST["pack{$i}_id"]);
				
				// Création de la laison scenario-pack + insertion dans la bdd
				foreach ($packs as $pack){
					$liaisonScenarioPack = new AppartientSP();
					$liaisonScenarioPack->init($scenario, $pack, intval($_POST["pack{$i}_quantite"]));
					$daoAppartientSP = new AppartientSPDAO();
					$daoAppartientSP->insertion($liaisonScenarioPack);
				}
			}
		}
	}

?>