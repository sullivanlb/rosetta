<?php

/** 
 * AppartientSQ est une classe de représentant la table AppartientSQ de la base de données.
 * 
 * AppartientSQ est une classe permettant de renseigner et obtenir les informations
 * d'une liaison.
 * 
 * @author Leboeuf Sullivan
 * @version $Revision: 1.0 $
 * @access public
 */
class AppartientSQ {
    
    /**
     * Identifiant de la liaison
     * 
     * @var Scenario
     * @access private
     */
    private $unScenario;

    /**
     * Nom de la liaison
     * 
     * @var Question
     * @access private
     */
    private $uneQuestion;

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
     * @param Question $uneQuestion
     */
	public function init ($unScenario, $uneQuestion) {
        $this->__set('unScenario', $unScenario);
        $this->__set('uneQuestion', $uneQuestion);
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
        } else if ($propriete === 'uneQuestion') {
            return $this->uneQuestion;
        } else {
            throw new Exception('AppartientSQ: __get(mixed): propriété invalide.');
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
        } else if ($propriete === 'uneQuestion' && !is_null($valeur)) {
            $this->uneQuestion = $valeur;
        } else {
            throw new Exception('AppartientSQ: __set(string, mixed): propriété ou valeur invalide.');
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
        return "Scenario : " . $this->unScenario . "\r\n" .
            "Question : " . $this->uneQuestion . "\r\n";
	}

}

?>