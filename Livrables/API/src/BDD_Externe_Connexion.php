<?php
ini_set('display_errors', 'On');
error_reporting(E_ALL);

	class BDD_Externe_Connexion {
		private static $instance;
		private $connexion;

		public function __construct() {
			try {
				$this->connexion = new PDO('sqlite:'.dirname(__FILE__)."/bdd_externe.db");
				$this->connexion->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
			}catch (PDOException $e) {
				print "Erreur !: " . $e->getMessage() . "<br/>";
				die();
 			}
		}

		public static function getInstance() {
			if (self::$instance === null) {
				self::$instance = new BDD_Externe_Connexion();
			}
			return self::$instance;
		}

		public function getConnexion() {
			return $this->connexion;
		}
	}
	
	BDD_Externe_Connexion::getInstance()->getConnexion();
?>
