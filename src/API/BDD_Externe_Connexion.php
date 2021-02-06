<?php

ini_set('display_errors', 'On');
error_reporting(E_ALL);

/** 
 * BDD_Externe_Connexion est une classe d'accès à la base de données.
 * 
 * BDD_Externe_Connexion est une classe permettant de se connecter à
 * la base de données externe, afin de réaliser par la suite autant de
 * méthodes qu'on veut (Ex : insertion, modification, suppression, ...).
 * 
 * @author Garcia Christophe
 * @version $Revision: 1.2 $
 * @access public
 */
class BDD_Externe_Connexion {

	/**
     * L'unique instance de cette classe
     * 
     * @access private
     */
	private static $instance;

	/**
     * La connexion à la base de données
     * 
     * @access private
     */
	private $connexion;

	/**
     * Méthode magique __construct()
     * 
     * Permet de créer la BDD_Externe_Connexion
     */
	public function __construct() {
		try {
			$this->connexion = new PDO("sqlite:./Rosetta.db");
			$this->connexion->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
		}catch (PDOException $e) {
			print "Erreur : " . $e->getMessage() . "\r\n";
			die();
		}
	}

	/**
     * Méthode d'accès à l'unique instance getInstance()
     * 
     * Crée une instance si elle n'existe déjà pas, et retourne celle-ci
     */
	public static function getInstance() {
		if (self::$instance === null) {
			self::$instance = new BDD_Externe_Connexion();
		}
		return self::$instance;
	}

	/**
     * Méthode d'accès à la connexion getConnexion()
     * 
     * Retourne la connexion établie à la base de données
     */
	public function getConnexion() {
		return $this->connexion;
	}
}

BDD_Externe_Connexion::getInstance()->getConnexion();

?>
