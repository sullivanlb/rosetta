<?php 
  	header("Access-Control-Allow-Methods: GET, POST, OPTIONS, DELETE");
  	header('Access-Control-Allow-Origin: *');
	header('Content-Type: application/json');

	include_once("../BDD_Externe_Connexion.php");

	include("../objets/Scenario.php");
	include("../dao/ScenarioDAO.php");
	include("../objets/AppartientSC.php");
	include("../dao/AppartientSCDAO.php");
	include("../objets/AppartientSP.php");
	include("../dao/AppartientSPDAO.php");
	include("../objets/AppartientSQ.php");
	include("../dao/AppartientSQDAO.php");
	include("../objets/Question.php");
	include("../dao/QuestionDAO.php");
	
	$connexion = new BDD_Externe_Connexion();

	if ($_SERVER['REQUEST_METHOD'] === 'DELETE') {
		$index = basename($_SERVER['REQUEST_URI']);
		
		// Suppression du scenario
		$daoScenario = new ScenarioDAO();
		$scenario = new Scenario();
		$scenario = $daoScenario->unElement($index);
		$daoScenario->suppression($scenario[0]);

		// Suppression des liaisons avec ses composants
		$daoAppartientSC = new AppartientSCDAO();
		$appartientSC = new AppartientSC();
		$appartientSC = $daoAppartientSC->unElement($scenario[0]);
		foreach ($appartientSC as $liaison) {
			$daoAppartientSC->suppression($liaison);
		}

		// Suppression des liaisons avec ses packs
		$daoAppartientSP = new AppartientSPDAO();
		$appartientSP = new AppartientSP();
		$appartientSP = $daoAppartientSP->unElement($scenario[0]);
		foreach ($appartientSP as $liaison) {
			$daoAppartientSP->suppression($liaison);
		}

		// Suppression des liaisons avec ses questions et ses questions
		$daoAppartientSQ = new AppartientSQDAO();
		$appartientSQ = new AppartientSQ();
		$appartientSQ = $daoAppartientSQ->unElement($scenario[0]);
		$daoQuestion = new QuestionDAO();
		$questions = $daoQuestion->tousLesElements();
		foreach ($appartientSQ as $liaison) {
			foreach ($questions as $laQ) {
				if ($laQ->__get("id") == $liaison->__get("uneQuestion")) {
					$daoQuestion->suppression($laQ);
				}
			}
			$daoAppartientSQ->suppression($liaison);
		}

	}
?>