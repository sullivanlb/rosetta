<?php

/** 
 * Devis est une classe de représentant la table Devis de la base de données.
 * 
 * Devis est une classe permettant de renseigner et obtenir les informations
 * d'un devis (un élément de la Table).
 * 
 * @author Garcia Christophe
 * @version $Revision: 1.2 $
 * @access public
 */
class Devis implements JsonSerializable {
    
    /**
     * Identifiant du devis
     * 
     * @var int
     * @access private
     */
    private $idDevis;

    /**
     * Nom du devis
     * 
     * @var string
     * @access private
     */
    private $nomDevis;

    /**
     * Description du devis
     * 
     * @var string
     * @access private
     */
    private $descriptionDevis;

    /**
     * Durée du devis
     * 
     * @var string
     * @access private
     */
    private $dureeDevis;

    /**
     * Date d'édition du devis
     * 
     * @var string
     * @access private
     */
    private $dateEditionDevis;

    /**
     * Date des travaux du devis
     * 
     * @var string
     * @access private
     */
    private $dateTravauxDevis;

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
     * @param string $description
     * @param string $duree
     * @param string $dateEdition
     * @param string $dateTravaux
     */
	public function init ($id, $nom, $description, $duree, $dateEdition, $dateTravaux) {
        $this->__set('id', $id);
        $this->__set('nom', $nom);
        $this->__set('description', $description);
        $this->__set('duree', $duree);
        $this->__set('dateEdition', $dateEdition);
        $this->__set('dateTravaux', $dateTravaux);
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
        } else if ($propriete === 'description') {
            return $this->descriptionDevis;
        } else if ($propriete === 'duree') {
            return $this->dureeDevis;
        } else if ($propriete === 'dateEdition') {
            return $this->dateEditionDevis;
        } else if ($propriete === 'dateTravaux') {
            return $this->dateTravauxDevis;
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
        } else if ($propriete === 'nom' && is_string($valeur)) {
            $this->nomDevis = $valeur;
        } else if ($propriete === 'description' && is_string($valeur)) {
            $this->descriptionDevis = $valeur;
        } else if ($propriete === 'duree' && is_string($valeur)) {
            $this->dureeDevis = $valeur;
        } else if ($propriete === 'dateEdition' && is_string($valeur)) {
            $this->dateEditionDevis = $valeur;
        } else if ($propriete === 'dateTravaux' && is_string($valeur)) {
            $this->dateTravauxDevis = $valeur;
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
        return "Nom : " . $this->nomDevis . "\r\n"
        . "Description : " . $this->descriptionDevis . "\r\n"
        . "Duree : " . $this->dureeDevis . "\r\n"
        . "Date d'edition : " . $this->dateEditionDevis . "\r\n"
        . "Date des travaux : " . $this->DateTravauxDevis . "\r\n";
	}

    public function jsonSerialize() {
        $vars = get_object_vars($this);
        return $vars;
    }

}

?>
