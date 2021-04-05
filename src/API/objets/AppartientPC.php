<?php

/** 
 * AppartientPC est une classe de représentant la table AppartientPC de la base de données.
 * 
 * AppartientPC est une classe permettant de renseigner et obtenir les informations
 * d'une liaison.
 * 
 * @author Leboeuf Sullivan
 * @version $Revision: 1.0 $
 * @access public
 */
class AppartientPC implements JsonSerializable {
    
    /**
     * Identifiant de la liaison
     * 
     * @var Pack
     * @access private
     */
    private $unPack;

    /**
     * Nom de la liaison
     * 
     * @var Composant
     * @access private
     */
    private $unComposant;

    /**
     * La quantite
     * 
     * @var int
     * @access private
     */
    private $quantite;

    /**
     * Méthode magique __construct()
     * 
     * Permet de créer une nouvelle liaison
     */
	public function __construct () {
    }

    /**
     * Méthode d'initialisation init()
     * 
     * Initialise les données de la liaison
     * 
     * @param Pack $unPack
     * @param Composant $unComposant
     * @param int $quantite
     */
	public function init ($unPack, $unComposant, $quantite) {
        $this->__set('unPack', $unPack);
        $this->__set('unComposant', $unComposant);
        $this->setQuantite($quantite);
    }

    /**
     * Méthode magique __get()
     * 
     * Retourne la valeur de la propriété de la liaison
     * 
     * @param string $propriete
     * @return mixed
     * @throws Exception
     */
    public function __get($propriete) {
        if ($propriete === 'unPack') {
            return $this->unPack;
        } else if ($propriete === 'unComposant') {
            return $this->unComposant;
        } else if ($propriete === 'quantite') {
            return $this->quantite;
        } else {
            throw new Exception('AppartientPC: __get(mixed): propriété invalide.');
        }
    }

    /**
     * Méthode magique __set()
     * 
     * Fixe la valeur de la propriété de la liaison
     * 
     * @param string $propriete
     * @param mixed $valeur
     * @throws Exception
     */
    public function __set($propriete, $valeur) {
        if ($propriete === 'unPack' && !is_null($valeur)) {
            $this->unPack = $valeur;
        } else if ($propriete === 'unComposant' && !is_null($valeur)) {
            $this->unComposant = $valeur;
        } else {
            throw new Exception('AppartientPC: __set(string, mixed): propriété ou valeur invalide.');
        }
    }

    /**
     * Définis une nouvelle quantite
     * 
     * @param int $valeur
     * @throws Exception
     */
    public function setQuantite($valeur){
        if (is_int($valeur) && $valeur >= 0){
            $this->quantite = $valeur;
        }else{
            throw new Exception('AppartientPC: setQuantite(int): paramètre invalide.');
        }
    }
    
    /**
     * Méthode magique __toString()
     * 
     * Retourne un affichage des données de la liaison
     * 
     * @return string
     */
	public function __toString() {
        return "Pack : " . $this->unPack .
            "Composant : " . $this->unComposant .
            "Quantite : " . $this->quantite . "\r\n";
	}

    public function jsonSerialize() {
        $vars = get_object_vars($this);
        return $vars;
    }

}

?>