<?php

/** 
 * AppartientSP est une classe de représentant la table AppartientSP de la base de données.
 * 
 * AppartientSP est une classe permettant de renseigner et obtenir les informations
 * d'une liaison.
 * 
 * @author Leboeuf Sullivan
 * @version $Revision: 1.0 $
 * @access public
 */
class AppartientSP {
    
    /**
     * Premiere cle
     * 
     * @var Scenario
     * @access private
     */
    private $unScenario;

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
     * @param Scenario $unScenario
     * @param Pack $unPack
     * @param int $quantite
     */
	public function init ($unScenario, $unPack, $quantite) {
        $this->__set('unScenario', $unScenario);
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
        if ($propriete === 'unScenario') {
            return $this->unScenario;
        } else if ($propriete === 'unPack') {
            return $this->unPack;
        } else if ($propriete === 'quantite') {
            return $this->quantite;
        } else {
            throw new Exception('AppartientSP: __get(mixed): propriété invalide.');
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
        if ($propriete === 'unScenario' && !is_null($valeur)) {
            $this->unScenario = $valeur;
        } else if ($propriete === 'unPack' && !is_null($valeur)) {
            $this->unPack = $valeur;
        } else {
            throw new Exception('AppartientSP: __set(string, mixed): propriété ou valeur invalide.');
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
            throw new Exception('AppartientSP: setQuantite(int): paramètre invalide.');
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
        return "Scenario : " . $this->unScenario .
            "Pack : " . $this->unPack .
            "Quantite : " . $this->quantite . "\r\n";
	}

}

?>