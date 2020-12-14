<?php

/** 
 * AppartientDC est une classe de représentant la table AppartientDC de la base de données.
 * 
 * AppartientDC est une classe permettant de renseigner et obtenir les informations
 * d'une liaison.
 * 
 * @author Leboeuf Sullivan
 * @version $Revision: 1.0 $
 * @access public
 */
class AppartientDC {
    
    /**
     * Identifiant de la liaison
     * 
     * @var Devis
     * @access private
     */
    private $unDevis;

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
     * @param Devis $unDevis
     * @param Composant $unComposant
     * @param int $quantite
     */
	public function init ($unDevis, $unComposant, $quantite) {
        $this->__set('unDevis', $unDevis);
        $this->__set('unComposant', $unComposant);
        $this->__set('quantite', $quantite);
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
        } else if ($propriete === 'unComposant') {
            return $this->unComposant;
        } else if ($propriete === 'quantite') {
            return $this->quantite;
        } else {
            throw new Exception('AppartientDC: __get(mixed): propriété invalide.');
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
        } else if ($propriete === 'unComposant' && !is_null($valeur)) {
            $this->unComposant = $valeur;
        } else if ($propriete === 'quantite' && is_int($valeur)) {
            $this->quantite = $valeur;
        } else {
            throw new Exception('AppartientDC: __set(string, mixed): propriété ou valeur invalide.');
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
        return "Devis : " . $this->unDevis . "\r\n" .
            "Composant : " . $this->unComposant . "\r\n" .
            "Quantite : " . $this->quantite . "\r\n";
	}

}

?>
