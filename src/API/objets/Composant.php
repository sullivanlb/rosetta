<?php

/** 
 * Composant est une classe de représentant la table Composant de la base de données.
 * 
 * Composant est une classe permettant de renseigner et obtenir les informations
 * d'un composant (un élément de la Table).
 * 
 * @author Garcia Christophe
 * @version $Revision: 1.2 $
 * @access public
 */
class Composant {
    
    /**
     * Identifiant du composant
     * 
     * @var int
     * @access private
     */
    private $idComposant;

    /**
     * Nom du composant
     * 
     * @var string
     * @access private
     */
    private $nomComposant;
    
    /**
     * Unité du composant
     * 
     * @var string
     * @access private
     */
    private $uniteComposant;
    
    /**
     * Prix du composant
     * 
     * @var float
     * @access private
     */
    private $prixComposant;

    /**
     * Méthode magique __construct()
     * 
     * Permet de créer un nouveau composant
     */
	public function __construct () {
    }

    /**
     * Méthode d'initialisation init()
     * 
     * Iniatilise les données du composant
     * 
     * @param int $id
     * @param string $nom
     * @param string $unite
     * @param float $prix
     */
	public function init ($id, $nom, $unite, $prix) {
        $this->__set('id', $id);
        $this->__set('nom', $nom);
        $this->__set('unite', $unite);
        $this->__set('prix', $prix);
    }

    /**
     * Méthode magique __get()
     * 
     * Retourne la valeur de la propriété du composant
     * 
     * @param string $propriete
     * @return int|string|float
     * @throws Exception
     */
    public function __get($propriete) {
        if ($propriete === 'id') {
            return $this->idComposant;
        } else if ($propriete === 'nom') {
            return $this->nomComposant;
        } else if ($propriete === 'unite') {
            return $this->uniteComposant;
        } else if ($propriete === 'prix') {
            return $this->prixComposant;
        } else {
            throw new Exception('Composant: __get(int|string|float): propriété invalide.');
        }
    }

    /**
     * Méthode magique __set()
     * 
     * Fixe la valeur de la propriété appelée au composant
     * 
     * @param string $propriete
     * @param mixed $valeur
     * @throws Exception
     */
    public function __set($propriete, $valeur) {
        if ($propriete === 'id') {
            $this->idComposant = $valeur;
        } else if ($propriete === 'nom' && is_string($valeur)) {
            $this->nomComposant = $valeur;
        } else if ($propriete === 'unite' && is_string($valeur)) {
            $this->uniteComposant = $valeur;
        } else if ($propriete === 'prix' && is_float($valeur)) {
            $this->prixComposant = $valeur;
        } else {
            throw new Exception('Composant: __set(string, mixed): propriété ou valeur invalide.');
        }
    }
    
    /**
     * Méthode magique __toString()
     * 
     * Retourne un affichage des données du composant
     * 
     * @return string
     */
	public function __toString() {
        return "Nom : " . $this->nomComposant . "\r\n" .
            "Unite : " . $this->uniteComposant . "\r\n" .
            "Prix : " . $this->prixComposant . "\r\n";
	}

}

?>
