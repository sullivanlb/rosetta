<?php 
require('Controller.php');
require('../model/Utilisateur.php');
require('../model/UtilisateurDAO.php');

class ConnectUserController implements Controller {

	private $id;
	
	function __construct (){
	}

	public function handle($request) {


		if ($this->checkConnection($request['email'], $request['password'])) {
			$userDAO = new Utilisateur();
			$_SESSION['email'] = $request['email'];
			$_SESSION['password'] = $request['password'];

		} else {
			include "views/ErrorView.php";
		}
	}
	
	private function checkConnection($mail, $mdp) {;
		$correctId = false;
		$userDAO = new UtilisateurDAO();
		
		foreach ($userDAO->findAll() as $user) {
			if ($user->getEmail() == $mail)
				if ($user->getMotDePasse() == $mdp) {
					$correctId = true;
					// this->id = $user->getId();
				}
		}
		
		return $correctId;
	}

	public function getId() { 
		return $this->id;
	}
}
?>
