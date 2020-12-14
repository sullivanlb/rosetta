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
class AppartientDP {
    
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
        } else if ($propriete === 'quantite' && is_int($valeur)) {
            $this->quantite = $valeur;
        } else {
            throw new Exception('AppartientDP: __set(string, mixed): propriété ou valeur invalide.');
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
            "Pack : " . $this->unPack . "\r\n" .
            "Quantite : " . $this->quantite . "\r\n";
	}

}

?>
