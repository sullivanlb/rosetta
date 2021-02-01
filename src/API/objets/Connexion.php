<?php

/** 
 * Connexion est une classe de représentant la table Connexion de la base de données.
 * 
 * Connexion est une classe permettant de renseigner et obtenir les informations
 * de la connexion (l'unique élément de la Table).
 * 
 * @author Garcia Christophe
 * @version $Revision: 1.2 $
 * @access public
 */
class Connexion {
    
    /**
     * Login de l'utilisateur
     * 
     * @var string
     * @access private
     */
    private $login;

    /**
     * Mot de passe de l'utilisateur
     * 
     * @var string
     * @access private
     */
    private $mdp;

    /**
     * Méthode magique __construct()
     * 
     * Permet de créer la connexion
     */
	public function __construct () {
    }

    /**
     * Méthode d'initialisation init()
     * 
     * Iniatilise les données de la connexion
     * 
     * @param string $login
     * @param string $mdp
     */
	public function init ($login, $mdp) {
        $this->__set('login', $login);
        $this->__set('mdp', $mdp);
    }

    /**
     * Méthode magique __get()
     * 
     * Retourne la valeur de la propriété de la connexion
     * 
     * @param string $propriete
     * @return string
     * @throws Exception
     */
    public function __get($propriete) {
        if ($propriete === 'login') {
            return $this->login;
        } else if ($propriete === 'mdp') {
            return $this->mdp;
        } else {
            throw new Exception('Connexion: __get(string): propriété invalide.');
        }
    }

    /**
     * Méthode magique __set()
     * 
     * Fixe la valeur de la propriété appelée à la connexion
     * 
     * @param string $propriete
     * @param mixed $valeur
     * @throws Exception
     */
    public function __set($propriete, $valeur) {
        if ($propriete === 'login' && is_string($valeur)) {
            $this->login = $valeur;
        } else if ($propriete === 'mdp' && is_string($valeur)) {
            $this->mdp = $valeur;
        } else {
            throw new Exception('Connexion: __set(string, mixed): propriété ou valeur invalide.');
        }
    }

}

?>
