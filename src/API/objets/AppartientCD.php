<?php

/** 
 * AppartientCD est une classe de représentant la table AppartientCD de la base de données.
 * 
 * AppartientCD est une classe permettant de renseigner et obtenir les informations
 * d'une liaison.
 * 
 * @author Leboeuf Sullivan
 * @version $Revision: 1.0 $
 * @access public
 */
class AppartientCD {
    
    /**
     * Premiere cle
     * 
     * @var Client
     * @access private
     */
    private $unClient;

    /**
     * Seconde cle
     * 
     * @var Devis
     * @access private
     */
    private $unDevis;

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
     * @param Client $unClient
     * @param Devis $unDevis
     */
	public function init ($unClient, $unDevis) {
        $this->__set('unClient', $unClient);
        $this->__set('unDevis', $unDevis);
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
        if ($propriete === 'unClient') {
            return $this->unClient;
        } else if ($propriete === 'unDevis') {
            return $this->unDevis;
        } else {
            throw new Exception('AppartientCD: __get(mixed): propriété invalide.');
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
        if ($propriete === 'unClient' && !is_null($valeur)) {
            $this->unClient = $valeur;
        } else if ($propriete === 'unDevis' && !is_null($valeur)) {
            $this->unDevis = $valeur;
        } else {
            throw new Exception('AppartientCD: __set(string, mixed): propriété ou valeur invalide.');
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
        return "Client : " . $this->unClient . "\r\n" .
            "Devis : " . $this->unDevis . "\r\n";
	}

}

?>
