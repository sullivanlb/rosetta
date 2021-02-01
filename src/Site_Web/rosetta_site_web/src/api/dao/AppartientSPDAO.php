<?php

require_once('../BDD_Externe_Connexion.php');

/** 
 * AppartientSPDAO est une classe de gestion de liaison.
 * 
 * AppartientSPDAO est une classe permettant de :
 * - Retourner toutes les liaisons Scenario--Pack,
 * - De retourner une liaison suivant un Scenario ou un Pack,
 * - D'insérer une nouvelle liaison,
 * - De supprimer une liaison,
 * - De modifier les données d'une liaison.
 * Une seule instance de cette classe est possible.
 * 
 * @author Leboeuf Sullivan
 * @version $Revision: 1.0 $
 * @access public
 */
class AppartientSPDAO {

    /**
     * Objet d'accès aux données
     * 
     * @access private
     */
    private static $dao;

    /**
     * Méthode magique __construct()
     * 
     * Permet de créer le AppartientSPDAO
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
            self::$dao = new AppartientSPDAO();
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
        $query = "SELECT * FROM AppartientSP";
        $stmt = $dbc->query($query);
        $results = $stmt->fetchALL(PDO::FETCH_CLASS, 'AppartientSP');
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

        if ($cle instanceof Scenario) {
            $query = "SELECT * FROM AppartientSP WHERE unScenario = '" . $cle . "'";
        } else if ($cle instanceof Pack) {
            $query = "SELECT * FROM AppartientSP WHERE unPack = '" . $cle . "'";
        } else {
            throw new Exception('AppartientSPDAO: unElement(): parametre invalide.');
        }

        $stmt = $dbc->query($query);
        $results = $stmt->fetchALL(PDO::FETCH_CLASS, 'AppartientSP');
        return $results;
    }

    /**
     * Méthode d'insertion insertion()
     * 
     * Insère une nouvelle liaison dans la base de données
     * 
     * @param AppartientSP $liaison
     */
    public final function insertion($liaison) {
        if ($liaison instanceof AppartientSP) {
            $dbc = BDD_Externe_Connexion::getInstance()->getConnexion();

            // Prépare la déclaration SQL
            $query = "INSERT INTO AppartientSP(unScenario, unPack, quantite) VALUES (:unScenario, :unPack, :quantite)";
            $stmt = $dbc->prepare($query);

            // Lie les paramètres
            $stmt->bindValue(':unScenario', $liaison->__get("unScenario"), PDO::PARAM_STR);
            $stmt->bindValue(':unPack', $liaison->__get("unPack"), PDO::PARAM_STR);
            $stmt->bindValue(':quantite', $liaison->__get("quantite"), PDO::PARAM_STR);
            
            // Exécute la déclaration SQL
            $stmt->execute();
        } else {
            throw new Exception("AppartientSPDAO: insertion(): n'est pas une instance de AppartientSP.");
        }
    }

    /**
     * Méthode de suppression suppression()
     * 
     * Supprime une liaison de la base de données
     * 
     * @param AppartientSP $liaison
     */
    public function suppression($liaison) { 
        if ($liaison instanceof AppartientSP) {
            $dbc = BDD_Externe_Connexion::getInstance()->getConnexion();
            $unScenario = $liaison->__get("unScenario");
            $unPack = $liaison->__get("unPack");
            $quantite = $liaison->__get("quantite");

            // Prépare la déclaration SQL
            $query = "DELETE FROM AppartientSP WHERE unScenario = '" . $unScenario . "' AND unPack = '" . $unPack . "' AND quantite = '" . $quantite . "'";
            $stmt = $dbc->prepare($query);

            // Exécute la déclaration SQL
            $stmt->execute();
        } else {
            throw new Exception('AppartientSPDAO: suppression(): n est pas une instance de AppartientSP.');
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
        if ($liaison instanceof AppartientSP) {
            $dbc = BDD_Externe_Connexion::getInstance()->getConnexion();
            $unScenario = $liaison->__get("unScenario");
            $unPack = $liaison->__get("unPack");

            // Prépare la déclaration SQL
            $query = "UPDATE AppartientSP SET quantite=:quantite WHERE unScenario = '" . $unScenario . "' AND unPack = '" . $unPack . "'";
            $stmt = $dbc->prepare($query);
        
            // Lie les paramètres
            $stmt->bindValue(':quantite', $liaison->__get("quantite"), PDO::PARAM_STR);
          
            // Exécute la déclaration SQL
            $stmt->execute();
        } else {
            throw new Exception("AppartientSPDAO: modification(): n'est pas une instance de AppartientSP.");
        } 
    }
}

?>