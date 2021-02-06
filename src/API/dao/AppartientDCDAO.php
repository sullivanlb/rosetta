<?php

require_once('BDD_Externe_Connexion.php');

/** 
 * AppartientDCDAO est une classe de gestion de liaison.
 * 
 * AppartientDCDAO est une classe permettant de :
 * - Retourner toutes les liaisons Devis--Composant,
 * - De retourner une liaison suivant un Devis ou un Composant,
 * - D'insérer une nouvelle liaison,
 * - De supprimer une liaison,
 * - De modifier les données d'une liaison.
 * Une seule instance de cette classe est possible.
 * 
 * @author Leboeuf Sullivan
 * @version $Revision: 1.0 $
 * @access public
 */
class AppartientDCDAO {

    /**
     * Objet d'accès aux données
     * 
     * @access private
     */
    private static $dao;

    /**
     * Méthode magique __construct()
     * 
     * Permet de créer le AppartientDCDAO
     */
    public function __construct() {
    }

    /**
     * Méthode d'accès à l'unique instance getInstance()
     * 
     * Crée une instance si elle n'existe déjà pas, et retourne celle-ci
     */
    public final static function getInstance() {
        if (!isset(self::$dao)) {
            self::$dao = new AppartientDCDAO();
        }
        return self::$dao;
    }

    /**
     * Méthode d'accès à l'ensemble des liaisons tousLesElements()
     * 
     * Retourne tous les liaisons de la base de données
     */
    public final function tousLesElements() {
        $dbc = BDD_Externe_Connexion::getInstance()->getConnexion();
        $query = "SELECT * FROM AppartientDC";
        $stmt = $dbc->query($query);
        $results = $stmt->fetchALL(PDO::FETCH_CLASS, 'AppartientDC');
        return $results;
    }

    /**
     * Méthode d'accès à une unique liaison unElement()
     * 
     * Retourne la liaison dont la clé est passé en paramètre
     * 
     * @param mixed $cle
     * @throws Exception
     */
    public final function unElement($cle) {
        $dbc = BDD_Externe_Connexion::getInstance()->getConnexion();

        if ($cle instanceof Devis) {
            $query = "SELECT * FROM AppartientDC WHERE unDevis = '" . $cle . "'";
        } else if ($cle instanceof Composant) {
            $query = "SELECT * FROM AppartientDC WHERE unComposant = '" . $cle . "'";
        } else {
            throw new Exception('AppartientDCDAO: unElement(): parametre invalide.');
        }

        $stmt = $dbc->query($query);
        $results = $stmt->fetchALL(PDO::FETCH_CLASS, 'AppartientDC');
        return $results;
    }

    /**
     * Méthode d'insertion insertion()
     * 
     * Insère une nouvelle liaison dans la base de données
     * 
     * @param AppartientDC $liaison
     */
    public final function insertion($liaison) {
        if ($liaison instanceof AppartientDC) {
            $dbc = BDD_Externe_Connexion::getInstance()->getConnexion();

            // Prépare la déclaration SQL
            $query = "INSERT INTO AppartientDC(unDevis, unComposant, quantite) VALUES (:unDevis, :unComposant, :quantite)";
            $stmt = $dbc->prepare($query);

            // Lie les paramètres
            $stmt->bindValue(':unDevis', $liaison->__get("unDevis"), PDO::PARAM_STR);
            $stmt->bindValue(':unComposant', $liaison->__get("unComposant"), PDO::PARAM_STR);
            $stmt->bindValue(':quantite', $liaison->__get("quantite"), PDO::PARAM_STR);
            
            // Exécute la déclaration SQL
            $stmt->execute();
        } else {
            throw new Exception("AppartientDCDAO: insertion(): n'est pas une instance de AppartientDC.");
        }
    }

    /**
     * Méthode de suppression suppression()
     * 
     * Supprime une liaison de la base de données
     * 
     * @param AppartientDC $liaison
     */
    public function suppression($liaison) { 
        if ($liaison instanceof AppartientDC) {
            $dbc = BDD_Externe_Connexion::getInstance()->getConnexion();
            $unDevis = $liaison->__get("unDevis");
            $unComposant = $liaison->__get("unComposant");
            $quantite = $liaison->__get("quantite");

            // Prépare la déclaration SQL
            $query = "DELETE FROM AppartientDC WHERE unDevis = '" . $unDevis . "' AND unComposant = '" . $unComposant . "' AND quantite = '" . $quantite . "'";
            $stmt = $dbc->prepare($query);

            // Exécute la déclaration SQL
            $stmt->execute();
        } else {
            throw new Exception('AppartientDCDAO: suppression(): n est pas une instance de AppartientDC.');
        }
    }

    /**
     * Méthode de modification suppression()
     * 
     * Modifie les données d'une liaison
     * 
     * @param $liaison
     */
    public function modification($liaison) {
        if ($liaison instanceof AppartientDC) {
            $dbc = BDD_Externe_Connexion::getInstance()->getConnexion();
            $unDevis = $liaison->__get("unDevis");
            $unComposant = $liaison->__get("unComposant");

            // Prépare la déclaration SQL
            $query = "UPDATE AppartientDC SET quantite=:quantite WHERE unDevis = '" . $unDevis . "' AND unComposant = '" . $unComposant . "'";
            $stmt = $dbc->prepare($query);
        
            // Lie les paramètres
            $stmt->bindValue(':quantite', $liaison->__get("quantite"), PDO::PARAM_STR);
          
            // Exécute la déclaration SQL
            $stmt->execute();
        } else {
            throw new Exception('AppartientDCDAO: modification(): n est pas une instance de AppartientDC.');
        } 
    }
}

?>