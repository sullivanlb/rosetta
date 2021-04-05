<?php

/** 
 * AppartientDP est une classe de représentant la table AppartientDP de la base de données.
 * 
 * AppartientDP est une classe permettant de renseigner et obtenir les informations
 * d'une liaison.
 * 
 * @author Leboeuf Sullivan
 * @version $Revision: 1.0 $
 * @access public
 */
class AppartientDP implements JsonSerializable {
    
    /**
     * Premiere cle
     * 
     * @var Devis
     * @access private
     */
    private $unDevis;

    /**
     * Seconde cle
     * 
     * @var Pack
     * @access private
     */
    private $unPack;

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
     * @param Devis $unDevis
     * @param Pack $unPack
     * @param int $quantite
     */
	public function init ($unDevis, $unPack, $quantite) {
        $this->__set('unDevis', $unDevis);
        $this->__set('unPack', $unPack);
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
        if ($propriete === 'unDevis') {
            return $this->unDevis;
        } else if ($propriete === 'unPack') {
            return $this->unPack;
        } else if ($propriete === 'quantite') {
            return $this->quantite;
        }  else {
            throw new Exception('AppartientDP: __get(mixed): propriété invalide.');
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
        if ($propriete === 'unDevis' && !is_null($valeur)) {
            $this->unDevis = $valeur;
        } else if ($propriete === 'unPack' && !is_null($valeur)) {
            $this->unPack = $valeur;
        } else {
            throw new Exception('AppartientDP: __set(string, mixed): propriété ou valeur invalide.');
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
            throw new Exception('AppartientDP: setQuantite(int): paramètre invalide.');
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
        return "Devis : " . $this->unDevis .
            "Pack : " . $this->unPack .
            "Quantite : " . $this->quantite . "\r\n";
	}

    public function jsonSerialize() {
        $vars = get_object_vars($this);
        return $vars;
    }

}

?>
