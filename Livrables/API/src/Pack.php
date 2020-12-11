<?php

/** 
 * Pack est une classe de représentant la table Pack de la base de données.
 * 
 * Pack est une classe permettant de renseigner et obtenir les informations
 * d'un pack (un élément de la Table).
 * 
 * @author Garcia Christophe
 * @version $Revision: 1.2 $
 * @access public
 */
class Pack {
    
    /**
     * Identifiant du pack
     * 
     * @var int
     * @access private
     */
    private $idPack;

    /**
     * Nom du pack
     * 
     * @var string
     * @access private
     */
    private $nomPack;

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
        if ($propriete === 'id' && is_int($valeur)) {
            $this->idPack = $valeur;
        } else if ($propriete === 'nom' && is_string($valeur)) {
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
