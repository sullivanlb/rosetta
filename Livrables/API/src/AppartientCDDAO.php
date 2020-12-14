<?php

require_once('BDD_Externe_Connexion.php');

/** 
 * AppartientCDDAO est une classe de gestion de liaison.
 * 
 * AppartientCDDAO est une classe permettant de :
 * - Retourner toutes les liaisons Client--Devis,
 * - De retourner une liaison suivant un Client ou un Devis,
 * - D'insérer une nouvelle liaison,
 * - De supprimer une liaison,
 * - De modifier les données d'une liaison.
 * Une seule instance de cette classe est possible.
 * 
 * @author Leboeuf Sullivan
 * @version $Revision: 1.0 $
 * @access public
 */
class AppartientCDDAO {

    /**
     * Objet d'accès aux données
     * 
     * @access private
     */
    private static $dao;

    /**
     * Méthode magique __construct()
     * 
     * Permet de créer le AppartientCDDAO
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
            self::$dao = new AppartientCDDAO();
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
        $query = "SELECT * FROM AppartientCD";
        $stmt = $dbc->query($query);
        $results = $stmt->fetchALL(PDO::FETCH_CLASS, 'AppartientCD');
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

        if ($cle instanceof Client) {
            $query = "SELECT * FROM AppartientCD WHERE unClient = '" . $cle . "'";
        } else if ($cle instanceof Devis) {
            $query = "SELECT * FROM AppartientCD WHERE unDevis = '" . $cle . "'";
        } else {
            throw new Exception('AppartientCDDAO: unElement(): parametre invalide.');
        }

        $stmt = $dbc->query($query);
        $results = $stmt->fetchALL(PDO::FETCH_CLASS, 'AppartientCD');
        return $results;
    }

    /**
     * Méthode d'insertion insertion()
     * 
     * Insère une nouvelle liaison dans la base de données
     * 
     * @param AppartientCD $liaison
     */
    public final function insertion($liaison) {
        if ($liaison instanceof AppartientCD) {
            $dbc = BDD_Externe_Connexion::getInstance()->getConnexion();

            // Prépare la déclaration SQL
            $query = "INSERT INTO AppartientCD(unClient, unDevis) VALUES (:unClient, :unDevis)";
            $stmt = $dbc->prepare($query);

            // Lie les paramètres
            $stmt->bindValue(':unClient', $liaison->__get("unClient"), PDO::PARAM_STR);
            $stmt->bindValue(':unDevis', $liaison->__get("unDevis"), PDO::PARAM_STR);
            
            // Exécute la déclaration SQL
            $stmt->execute();
        } else {
            throw new Exception("AppartientCDDAO: insertion(): n'est pas une instance de AppartientCD.");
        }
    }

    /**
     * Méthode de suppression suppression()
     * 
     * Supprime une liaison de la base de données
     * 
     * @param AppartientCD $liaison
     */
    public function suppression($liaison) { 
        if ($liaison instanceof AppartientCD) {
            $dbc = BDD_Externe_Connexion::getInstance()->getConnexion();
            $unClient = $liaison->__get("unClient");
            $unDevis = $liaison->__get("unDevis");

            // Prépare la déclaration SQL
            $query = "DELETE FROM AppartientCD WHERE unClient = '" . $unClient . "' AND unDevis = '" . $unDevis . "'";
            $stmt = $dbc->prepare($query);

            // Exécute la déclaration SQL
            $stmt->execute();
        } else {
            throw new Exception('AppartientCDDAO: suppression(): n est pas une instance de AppartientCD.');
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
        if ($liaison instanceof AppartientCD) {
            $dbc = BDD_Externe_Connexion::getInstance()->getConnexion();
            $unClient = $liaison->__get("unClient");
            $unDevis = $liaison->__get("unDevis");

            // Prépare la déclaration SQL
            $query = "UPDATE AppartientCD SET unClient=:unClient, unDevis=:unDevis WHERE unClient = '" . $unClient . "' AND unDevis = '" . $unDevis . "'";
            $stmt = $dbc->prepare($query);
        
            // Lie les paramètres
            $stmt->bindValue(':unClient', $liaison->__get("unClient"), PDO::PARAM_STR);
            $stmt->bindValue(':unDevis', $liaison->__get("unDevis"), PDO::PARAM_STR);
          
            // Exécute la déclaration SQL
            $stmt->execute();
        } else {
            throw new Exception('AppartientCDDAO: modification(): n est pas une instance de AppartientCD.');
        } 
    }
}

?>