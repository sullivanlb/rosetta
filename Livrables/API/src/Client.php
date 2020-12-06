<?php

class Client {
    
    /**
     * Identifiant du client
     * 
     * @var int
     * @access private
     */
    private int $idClient;

    /**
     * Nom du client
     * 
     * @var string
     * @access private
     */
    private string $nomClient;
    
    /**
     * Prenom du client
     * 
     * @var string
     * @access private
     */
    private string $prenomClient;
    
    /**
     * Adresse du client
     * 
     * @var string
     * @access private
     */
    private string $adresseClient;
    
    /**
     * Email du client
     * 
     * @var string
     * @access private
     */
    private string $emailClient;
    
    /**
     * Numéro de téléphone du client
     * 
     * @var int
     * @access private
     */
    private int $telClient;
    
    /**
     * Sexe du client (homme ou femme)
     * 
     * @var string
     * @access private
     */
    private string $sexeClient;

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
        } else if ($propriete === 'nom') {
            $this->nomClient = $valeur;
        } else if ($propriete === 'prenom') {
            $this->prenomClient = $valeur;
        } else if ($propriete === 'adresse') {
            $this->adresseClient = $valeur;
        } else if ($propriete === 'email') {
            $this->emailClient = $valeur;
        } else if ($propriete === 'tel') {
            $this->telClient = $valeur;
        } else if ($propriete === 'sexe') {
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
        return "Nom : " . $this->nomClient . "<br/>" .
            "Prenom : " . $this->prenomClient . "<br/>" .
            "Adresse : " . $this->adresseClient . "<br/>" .
            "Email : " . $this->emailClient . "<br/>" . 
            "Tel : " . $this->telClient . "<br/>" .
            "Sexe : " . $this->sexeClient . "<br/>";
	}

}

?>
