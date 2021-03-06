<?php

/** 
 * Client est une classe de représentant la table Client de la base de données.
 * 
 * Client est une classe permettant de renseigner et obtenir les informations
 * d'un client (un élément de la Table).
 * 
 * @author Garcia Christophe
 * @version $Revision: 1.2 $
 * @access public
 */
class Client implements JsonSerializable {
    
    /**
     * Identifiant du client
     * 
     * @var int
     * @access private
     */
    private $idClient;

    /**
     * Nom du client
     * 
     * @var string
     * @access private
     */
    private $nomClient;
    
    /**
     * Prenom du client
     * 
     * @var string
     * @access private
     */
    private $prenomClient;
    
    /**
     * Adresse du client
     * 
     * @var string
     * @access private
     */
    private $adresseClient;
    
    /**
     * Email du client
     * 
     * @var string
     * @access private
     */
    private $emailClient;
    
    /**
     * Numéro de téléphone du client
     * 
     * @var string
     * @access private
     */
    private $telClient;
    
    /**
     * Sexe du client (homme ou femme)
     * 
     * @var string
     * @access private
     */
    private $sexeClient;

    /**
     * Méthode magique __construct()
     * 
     * Permet de créer un nouveau client
     */
	public function __construct () {
    }

    /**
     * Méthode d'initialisation init()
     * 
     * Iniatilise les données du client
     * 
     * @param int $id
     * @param string $nom
     * @param string $prenom
     * @param string $adresse
     * @param string $email
     * @param int $tel
     * @param string $sexe 
     */
	public function init ($id, $nom, $prenom, $adresse, $email, $tel, $sexe) {
        $this->__set('id', $id);
        $this->__set('nom', $nom);
        $this->__set('prenom', $prenom);
        $this->__set('adresse', $adresse);
        $this->__set('email', $email);
        $this->__set('tel', $tel);
        $this->__set('sexe', $sexe);
    }

    /**
     * Méthode magique __get()
     * 
     * Retourne la valeur de la propriété du client
     * 
     * @param string $propriete
     * @return int|string
     * @throws Exception
     */
    public function __get($propriete) {
        if ($propriete === 'id') {
            return $this->idClient;
        } else if ($propriete === 'nom') {
            return $this->nomClient;
        } else if ($propriete === 'prenom') {
            return $this->prenomClient;
        } else if ($propriete === 'adresse') {
            return $this->adresseClient;
        } else if ($propriete === 'email') {
            return $this->emailClient;
        } else if ($propriete === 'tel') {
            return $this->telClient;
        } else if ($propriete === 'sexe') {
            return $this->sexeClient;
        } else {
            throw new Exception('Client: __get(int|string): propriété invalide.');
        }
    }

    /**
     * Méthode magique __set()
     * 
     * Fixe la valeur de la propriété appelée au client
     * 
     * @param string $propriete
     * @param mixed $valeur
     * @throws Exception
     */
    public function __set($propriete, $valeur) {
        if ($propriete === 'id') {
            $this->idClient = $valeur;
        } else if ($propriete === 'nom' && is_string($valeur)) {
            $this->nomClient = $valeur;
        } else if ($propriete === 'prenom' && is_string($valeur)) {
            $this->prenomClient = $valeur;
        } else if ($propriete === 'adresse' && (is_string($valeur) || is_null($valeur))) {
            $this->adresseClient = $valeur;
        } else if ($propriete === 'email' && is_string($valeur)) {
            $this->emailClient = $valeur;
        } else if ($propriete === 'tel' && (is_string($valeur) || is_null($valeur))) {
            $this->telClient = $valeur;
        } else if ($propriete === 'sexe' && is_string($valeur)) {
            $this->sexeClient = $valeur;
        } else {
            throw new Exception('Client: __set(string, mixed): propriété ou valeur invalide.');
        }
    }
    
    /**
     * Méthode magique __toString()
     * 
     * Retourne un affichage des données du client
     * 
     * @return string
     */
	public function __toString() {
        $str = "Id : " . $this->idClient . "\r\nNom : " . $this->nomClient . "\r\n" .
                "Prenom : " . $this->prenomClient . "\r\nEmail : " . $this->emailClient . "\r\n" .
                "Sexe : " . $this->sexeClient . "\r\n";
        
        if(!is_null($this->telClient)) $str = $str . "Tel : " . $this->telClient . "\r\n";
        if(!is_null($this->adresseClient)) $str = $str . "Adresse : " . $this->adresseClient . "\r\n";
        return $str;
	}

    public function jsonSerialize() {
        $vars = get_object_vars($this);
        return $vars;
    }

}

?>
