<?php

class Appartient {
    
    /**
     * Le contenant du contenu
     * 
     * @access private
     */
    private $contenant;

    /**
     * Le contenu dans le contenant
     * 
     * @access private
     */
    private $contenu;

    /**
     * La quantité du composant ou du pack si nécessaire
     * 
     * @access private
     */
    private $quantite;

    /**
     * Méthode magique __construct()
     * 
     * Permet de créer une nouvelle association
     */
	public function __construct () {
    }

    /**
     * Méthode d'initialisation init()
     * 
     * Iniatilise l'association du conteneur avec le contenu et sa quantite si nécessaire
     * 
     * @param $conteneur
     * @param $contenu
     * @param $quantite
     */
	public function init ($conteneur, $contenu, $quantite) {
        if ($conteneur instanceof Client && $contenu instanceof Devis) {
            $this->__set($conteneur, $contenu);
        } else if ($propriete instanceof Devis && $contenu instanceof Pack) {
            $this->__set($conteneur, $conteneur);
            $this->setQuantite($quantite);
        } else if ($propriete instanceof Devis && $contenu instanceof Composant) {
            $this->__set($conteneur, $contenu);
            $this->setQuantite($quantite);
        } else if ($propriete instanceof Pack && $contenu instanceof Composant) {
            $this->__set($conteneur, $contenu);
            $this->setQuantite($quantite);
        } else if ($propriete instanceof Scenario && $contenu instanceof Pack) {
            $this->__set($conteneur, $contenu);
            $this->setQuantite($quantite);
        } else if ($propriete instanceof Scenario && $contenu instanceof Composant) {
            $this->__set($conteneur, $contenu);
            $this->setQuantite($quantite);
        } else if ($propriete instanceof Scenario && $contenu instanceof Question) {
            $this->__set($conteneur, $contenu);
        } else {
            throw new Exception('Appartient: init(): propriété ou valeur invalide.');
        }
    }

    /**
     * Méthode magique __set()
     * 
     * Associe le conteneur avec le contenu
     * 
     * @param $conteneur
     * @param $contenu
     * @throws Exception
     */
    public function __set($conteneur, $contenu) {
        $this->conteneur = $conteneur;
        $this->contenu = $contenu;
    }

     /**
     * Méthode de mise à jour de quantité setQuantite()
     * 
     * Fixe la valeur de la quantité du composant ou du pack si nécessaire
     * 
     * @param $quantite
     */
    public function setQuantite($quantite) {
        $this->quantite = $quantite;
    }

}

?>
