<?php

class Devis {
    
    /**
     * Identifiant du devis
     * 
     * @var int
     * @access private
     */
    private int $idDevis;

    /**
     * Nom du devis
     * 
     * @var string
     * @access private
     */
    private string $nomDevis;

    /**
     * Méthode magique __construct()
     * 
     * Permet de créer un nouveau devis
     */
	public function __construct () {
    }

    /**
     * Méthode d'initialisation init()
     * 
     * Iniatilise les données du devis
     * 
     * @param int $id
     * @param string $nom
     */
	public function init ($id, $nom) {
        $this->__set('id', $id);
        $this->__set('nom', $nom);
    }

    /**
     * Méthode magique __get()
     * 
     * Retourne la valeur de la propriété du devis
     * 
     * @param string $propriete
     * @return int|string
     * @throws Exception
     */
    public function __get($propriete) {
        if ($propriete === 'id') {
            return $this->idDevis;
        } else if ($propriete === 'nom') {
            return $this->nomDevis;
        } else {
            throw new Exception('Devis: __get(int|string): propriété invalide.');
        }
    }

    /**
     * Méthode magique __set()
     * 
     * Fixe la valeur de la propriété appelée au devis
     * 
     * @param string $propriete
     * @param mixed $valeur
     * @throws Exception
     */
    public function __set($propriete, $valeur) {
        if ($propriete === 'id') {
            $this->idDevis = $valeur;
        } else if ($propriete === 'nom') {
            $this->nomDevis = $valeur;
        } else {
            throw new Exception('Devis: __set(string, mixed): propriété ou valeur invalide.');
        }
    }
    
    /**
     * Méthode magique __toString()
     * 
     * Retourne le nom du devis
     * 
     * @return string
     */
	public function __toString() {
        return "Nom : " . $this->nomDevis . "<br/>";
	}

}

?>
