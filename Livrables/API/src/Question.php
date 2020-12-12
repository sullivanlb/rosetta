<?php

/** 
 * Question est une classe de représentant la table Question de la base de données.
 * 
 * Question est une classe permettant de renseigner et obtenir les informations
 * d'une question (un élément de la Table).
 * 
 * @author Garcia Christophe
 * @version $Revision: 1.2 $
 * @access public
 */
class Question {
    
    /**
     * Identifiant de la question
     * 
     * @var int
     * @access private
     */
    private $idQuestion;

    /**
     * Nom de la question
     * 
     * @var string
     * @access private
     */
    private $nomQuestion;

    /**
     * Méthode magique __construct()
     * 
     * Permet de créer une nouvelle question
     */
	public function __construct () {
    }

    /**
     * Méthode d'initialisation init()
     * 
     * Iniatilise les données de la question
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
     * Retourne la valeur de la propriété de la question
     * 
     * @param string $propriete
     * @return int|string
     * @throws Exception
     */
    public function __get($propriete) {
        if ($propriete === 'id') {
            return $this->idQuestion;
        } else if ($propriete === 'nom') {
            return $this->nomQuestion;
        } else {
            throw new Exception('Question: __get(int|string): propriété invalide.');
        }
    }

    /**
     * Méthode magique __set()
     * 
     * Fixe la valeur de la propriété appelée à la question
     * 
     * @param string $propriete
     * @param mixed $valeur
     * @throws Exception
     */
    public function __set($propriete, $valeur) {
        if ($propriete === 'id') {
            $this->idQuestion = $valeur;
        } else if ($propriete === 'nom' && is_string($valeur)) {
            $this->nomQuestion = $valeur;
        } else {
            throw new Exception('Question: __set(string, mixed): propriété ou valeur invalide.');
        }
    }
    
    /**
     * Méthode magique __toString()
     * 
     * Retourne le nom de la question
     * 
     * @return string
     */
	public function __toString() {
        return "Nom : " . $this->nomQuestion . "\r\n";
	}

}

?>
