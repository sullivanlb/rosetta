<?php

require_once('../BDD_Externe_Connexion.php');

/** 
 * AppartientDPDAO est une classe de gestion de liaison.
 * 
 * AppartientDPDAO est une classe permettant de :
 * - Retourner toutes les liaisons Devis--Pack,
 * - De retourner une liaison suivant un Devis ou un Pack,
 * - D'insérer une nouvelle liaison,
 * - De supprimer une liaison,
 * - De modifier les données d'une liaison.
 * Une seule instance de cette classe est possible.
 * 
 * @author Leboeuf Sullivan
 * @version $Revision: 1.0 $
 * @access public
 */
class AppartientDPDAO {

    /**
     * Objet d'accès aux données
     * 
     * @access private
     */
    private static $dao;

    /**
     * Méthode magique __construct()
     * 
     * Permet de créer le AppartientDPDAO
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
            self::$dao = new AppartientDPDAO();
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
        $query = "SELECT * FROM AppartientDP";
        $stmt = $dbc->query($query);
        $results = $stmt->fetchALL(PDO::FETCH_CLASS, 'AppartientDP');
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
            $query = "SELECT * FROM AppartientDP WHERE unDevis = '" . $cle . "'";
        } else if ($cle instanceof Pack) {
            $query = "SELECT * FROM AppartientDP WHERE unPack = '" . $cle . "'";
        } else {
            throw new Exception('AppartientDPDAO: unElement(): parametre invalide.');
        }

        $stmt = $dbc->query($query);
        $results = $stmt->fetchALL(PDO::FETCH_CLASS, 'AppartientDP');
        return $results;
    }

    /**
     * Méthode d'insertion insertion()
     * 
     * Insère une nouvelle liaison dans la base de données
     * 
     * @param AppartientDP $liaison
     */
    public final function insertion($liaison) {
        if ($liaison instanceof AppartientDP) {
            $dbc = BDD_Externe_Connexion::getInstance()->getConnexion();

            // Prépare la déclaration SQL
            $query = "INSERT INTO AppartientDP(unDevis, unPack, quantite) VALUES (:unDevis, :unPack, :quantite)";
            $stmt = $dbc->prepare($query);

            // Lie les paramètres
            $stmt->bindValue(':unDevis', $liaison->__get("unDevis"), PDO::PARAM_STR);
            $stmt->bindValue(':unPack', $liaison->__get("unPack"), PDO::PARAM_STR);
            $stmt->bindValue(':quantite', $liaison->__get("quantite"), PDO::PARAM_STR);
            
            // Exécute la déclaration SQL
            $stmt->execute();
        } else {
            throw new Exception("AppartientDPDAO: insertion(): n'est pas une instance de AppartientDP.");
        }
    }

    /**
     * Méthode de suppression suppression()
     * 
     * Supprime une liaison de la base de données
     * 
     * @param AppartientDP $liaison
     */
    public function suppression($liaison) { 
        if ($liaison instanceof AppartientDP) {
            $dbc = BDD_Externe_Connexion::getInstance()->getConnexion();
            $unDevis = $liaison->__get("unDevis");
            $unPack = $liaison->__get("unPack");
            $quantite = $liaison->__get("quantite");

            // Prépare la déclaration SQL
            $query = "DELETE FROM AppartientDP WHERE unDevis = '" . $unDevis . "' AND unPack = '" . $unPack . "' AND quantite = '" . $quantite . "'";
            $stmt = $dbc->prepare($query);

            // Exécute la déclaration SQL
            $stmt->execute();
        } else {
            throw new Exception('AppartientDPDAO: suppression(): n est pas une instance de AppartientDP.');
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
        if ($liaison instanceof AppartientDP) {
            $dbc = BDD_Externe_Connexion::getInstance()->getConnexion();
            $unDevis = $liaison->__get("unDevis");
            $unPack = $liaison->__get("unPack");

            // Prépare la déclaration SQL
            $query = "UPDATE AppartientDP SET quantite=:quantite WHERE unDevis = '" . $unDevis . "' AND unPack = '" . $unPack . "'";
            $stmt = $dbc->prepare($query);
        
            // Lie les paramètres
            $stmt->bindValue(':quantite', $liaison->__get("quantite"), PDO::PARAM_STR);
          
            // Exécute la déclaration SQL
            $stmt->execute();
        } else {
            throw new Exception('AppartientDPDAO: modification(): n est pas une instance de AppartientDP.');
        } 
    }
}

?>