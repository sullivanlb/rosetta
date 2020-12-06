<?php

class Composant {
    
    /**
     * Identifiant du composant
     * 
     * @var int
     * @access private
     */
    private int $idComposant;

    /**
     * Nom du composant
     * 
     * @var string
     * @access private
     */
    private string $nomComposant;
    
    /**
     * Unité du composant
     * 
     * @var string
     * @access private
     */
    private string $uniteComposant;
    
    /**
     * Prix du composant
     * 
     * @var float
     * @access private
     */
    private float $prixComposant;

    /**
     * Méthode magique __construct()
     * 
     * Permet de créer un nouveau composant
     */
	public function __construct () {
    }

    /**
     * Méthode d'initialisation init()
     * 
     * Iniatilise les données du composant
     * 
     * @param int $id
     * @param string $nom
     * @param string $unite
     * @param float $prix
     */
	public function init ($id, $nom, $unite, $prix) {
        $this->__set('id', $id);
        $this->__set('nom', $nom);
        $this->__set('unite', $unite);
        $this->__set('prix', $prix);
    }

    /**
     * Méthode magique __get()
     * 
     * Retourne la valeur de la propriété du composant
     * 
     * @param string $propriete
     * @return int|string|float
     * @throws Exception
     */
    public function __get($propriete) {
        if ($propriete === 'id') {
            return $this->idComposant;
        } else if ($propriete === 'nom') {
            return $this->nomComposant;
        } else if ($propriete === 'unite') {
            return $this->uniteComposant;
        } else if ($propriete === 'prix') {
            return $this->prixClient;
        } else {
            throw new Exception('Composant: __get(int|string|float): propriété invalide.');
        }
    }

    /**
     * Méthode magique __set()
     * 
     * Fixe la valeur de la propriété appelée au composant
     * 
     * @param string $propriete
     * @param mixed $valeur
     * @throws Exception
     */
    public function __set($propriete, $valeur) {
        if ($propriete === 'id') {
            $this->idComposant = $valeur;
        } else if ($propriete === 'nom') {
            $this->nomComposant = $valeur;
        } else if ($propriete === 'unite') {
            $this->uniteComposant = $valeur;
        } else if ($propriete === 'prix') {
            $this->prixComposant = $valeur;
        } else {
            throw new Exception('Composant: __set(string, mixed): propriété ou valeur invalide.');
        }
    }
    
    /**
     * Méthode magique __toString()
     * 
     * Retourne un affichage des données du composant
     * 
     * @return string
     */
	public function __toString() {
        return "Nom : " . $this->nomComposant . "<br/>" .
            "Unite : " . $this->uniteComposant . "<br/>" .
            "Prix : " . $this->prixComposant . "<br/>";
	}

}

?>
