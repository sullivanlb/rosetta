<?php

/** 
 * Scenario est une classe de représentant la table Scenario de la base de données.
 * 
 * Scenario est une classe permettant de renseigner et obtenir les informations
 * d'un scenario (un élément de la Table).
 * 
 * @author Garcia Christophe
 * @version $Revision: 1.2 $
 * @access public
 */
class Scenario {
    
    /**
     * Identifiant du scenario
     * 
     * @var int
     * @access private
     */
    private $idScenario;

    /**
     * Nom du scenario
     * 
     * @var string
     * @access private
     */
    private $nomScenario;

    /**
     * Méthode magique __construct()
     * 
     * Permet de créer un nouveau scenario
     */
	public function __construct () {
    }

    /**
     * Méthode d'initialisation init()
     * 
     * Iniatilise les données du scenario
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
     * Retourne la valeur de la propriété du scenario
     * 
     * @param string $propriete
     * @return int|string
     * @throws Exception
     */
    public function __get($propriete) {
        if ($propriete === 'id') {
            return $this->idScenario;
        } else if ($propriete === 'nom') {
            return $this->nomScenario;
        } else {
            throw new Exception('Scenario: __get(int|string): propriété invalide.');
        }
    }

    /**
     * Méthode magique __set()
     * 
     * Fixe la valeur de la propriété appelée au scenario
     * 
     * @param string $propriete
     * @param mixed $valeur
     * @throws Exception
     */
    public function __set($propriete, $valeur) {
        if ($propriete === 'id' && is_int($valeur)) {
            $this->idScenario = $valeur;
        } else if ($propriete === 'nom' && is_string($valeur)) {
            $this->nomScenario = $valeur;
        } else {
            throw new Exception('Scenario: __set(string, mixed): propriété ou valeur invalide.');
        }
    }
    
    /**
     * Méthode magique __toString()
     * 
     * Retourne le nom du scenario
     * 
     * @return string
     */
	public function __toString() {
        return "Nom : " . $this->nomScenario . "<br/>";
	}

}

?>
