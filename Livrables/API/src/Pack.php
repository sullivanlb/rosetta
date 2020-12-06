<?php

class Pack {
    
    /**
     * Identifiant du pack
     * 
     * @var int
     * @access private
     */
    private int $idPack;

    /**
     * Nom du pack
     * 
     * @var string
     * @access private
     */
    private string $nomPack;

    /**
     * Méthode magique __construct()
     * 
     * Permet de créer un nouveau pack
     */
	public function __construct () {
    }

    /**
     * Méthode d'initialisation init()
     * 
     * Iniatilise les données du pack
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
     * Retourne la valeur de la propriété du pack
     * 
     * @param string $propriete
     * @return int|string
     * @throws Exception
     */
    public function __get($propriete) {
        if ($propriete === 'id') {
            return $this->idPack;
        } else if ($propriete === 'nom') {
            return $this->nomPack;
        } else {
            throw new Exception('Pack: __get(int|string): propriété invalide.');
        }
    }

    /**
     * Méthode magique __set()
     * 
     * Fixe la valeur de la propriété appelée au pack
     * 
     * @param string $propriete
     * @param mixed $valeur
     * @throws Exception
     */
    public function __set($propriete, $valeur) {
        if ($propriete === 'id') {
            $this->idPack = $valeur;
        } else if ($propriete === 'nom') {
            $this->nomPack = $valeur;
        } else {
            throw new Exception('Pack: __set(string, mixed): propriété ou valeur invalide.');
        }
    }
    
    /**
     * Méthode magique __toString()
     * 
     * Retourne le nom du pack
     * 
     * @return string
     */
	public function __toString() {
        return "Nom : " . $this->nomPack . "<br/>";
	}

}

?>
