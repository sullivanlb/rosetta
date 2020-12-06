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
     * Méthode magique __construct()
     * 
     * Permet de créer une nouvelle association
     */
	public function __construct () {
    }

    /**
     * Méthode d'initialisation init()
     * 
     * Iniatilise l'association du conteneur avec le contenu
     * 
     * @param $conteneur
     * @param $contenu
     */
	public function init ($conteneur, $contenu) {
        $this->__set($conteneur, $contenu);
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
        if ($conteneur instanceof Client && $contenu instanceof Devis) {
            $this->conteneur = $conteneur;
            $this->contenu = $contenu;
        } else if ($propriete instanceof Devis && $contenu instanceof Pack) {
            $this->conteneur = $conteneur;
            $this->contenu = $contenu;
        } else if ($propriete instanceof Devis && $contenu instanceof Composant) {
            $this->conteneur = $conteneur;
            $this->contenu = $contenu;
        } else if ($propriete instanceof Pack && $contenu instanceof Composant) {
            $this->conteneur = $conteneur;
            $this->contenu = $contenu;
        } else if ($propriete instanceof Scenario && $contenu instanceof Pack) {
            $this->conteneur = $conteneur;
            $this->contenu = $contenu;
        } else if ($propriete instanceof Scenario && $contenu instanceof Composant) {
            $this->conteneur = $conteneur;
            $this->contenu = $contenu;
        } else if ($propriete instanceof Scenario && $contenu instanceof Question) {
            $this->conteneur = $conteneur;
            $this->contenu = $contenu;
        } else {
            throw new Exception('Appartient: __set(string, string): propriété ou valeur invalide.');
        }
    }

}

?>
