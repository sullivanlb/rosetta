<?php 
require('Controller.php');
require('../model/Utilisateur.php');
require('../model/UtilisateurDAO.php');

class DisconnectUserController implements Controller{
	
	function __construct (){
	}
	
	public function handle($request){
        print_r ($_SESSION);
		session_destroy();
    }
    
}
?>
