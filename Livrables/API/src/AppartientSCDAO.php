<?php

require_once('BDD_Externe_Connexion.php');

/** 
 * AppartientSCDAO est une classe de gestion de liaison.
 * 
 * AppartientSCDAO est une classe permettant de :
 * - Retourner toutes les liaisons Scenario--Composant,
 * - De retourner une liaison suivant un Scenario ou un Composant,
 * - D'insérer une nouvelle liaison,
 * - De supprimer une liaison,
 * - De modifier les données d'une liaison.
 * Une seule instance de cette classe est possible.
 * 
 * @author Leboeuf Sullivan
 * @version $Revision: 1.0 $
 * @access public
 */
class AppartientSCDAO {

    /**
     * Objet d'accès aux données
     * 
     * @access private
     */
    private static $dao;

    /**
     * Méthode magique __construct()
     * 
     * Permet de créer le AppartientSCDAO
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
            self::$dao = new AppartientSCDAO();
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
        $query = "SELECT * FROM AppartientSC";
        $stmt = $dbc->query($query);
        $results = $stmt->fetchALL(PDO::FETCH_CLASS, 'AppartientSC');
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
            $query = "SELECT * FROM AppartientSC WHERE unScenario = '" . $cle . "'";
        } else if ($cle instanceof Composant) {
            $query = "SELECT * FROM AppartientSC WHERE unComposant = '" . $cle . "'";
        } else {
            throw new Exception('AppartientSCDAO: unElement(): parametre invalide.');
        }

        $stmt = $dbc->query($query);
        $results = $stmt->fetchALL(PDO::FETCH_CLASS, 'AppartientSC');
        return $results;
    }

    /**
     * Méthode d'insertion insertion()
     * 
     * Insère une nouvelle liaison dans la base de données
     * 
     * @param AppartientSC $liaison
     */
    public final function insertion($liaison) {
        if ($liaison instanceof AppartientSC) {
            $dbc = BDD_Externe_Connexion::getInstance()->getConnexion();

            // Prépare la déclaration SQL
            $query = "INSERT INTO AppartientSC(unScenario, unComposant, quantite) VALUES (:unScenario, :unComposant, :quantite)";
            $stmt = $dbc->prepare($query);

            // Lie les paramètres
            $stmt->bindValue(':unScenario', $liaison->__get("unScenario"), PDO::PARAM_STR);
            $stmt->bindValue(':unComposant', $liaison->__get("unComposant"), PDO::PARAM_STR);
            $stmt->bindValue(':quantite', $liaison->__get("quantite"), PDO::PARAM_STR);
            
            // Exécute la déclaration SQL
            $stmt->execute();
        } else {
            throw new Exception("AppartientSCDAO: insertion(): n'est pas une instance de AppartientSC.");
        }
    }

    /**
     * Méthode de suppression suppression()
     * 
     * Supprime une liaison de la base de données
     * 
     * @param AppartientSC $liaison
     */
    public function suppression($liaison) { 
        if ($liaison instanceof AppartientSC) {
            $dbc = BDD_Externe_Connexion::getInstance()->getConnexion();
            $unScenario = $liaison->__get("unScenario");
            $unComposant = $liaison->__get("unComposant");
            $quantite = $liaison->__get("quantite");

            // Prépare la déclaration SQL
            $query = "DELETE FROM AppartientSC WHERE unScenario = '" . $unScenario . "' AND unComposant = '" . $unComposant . "' AND quantite = '" . $quantite . "'";
            $stmt = $dbc->prepare($query);

            // Exécute la déclaration SQL
            $stmt->execute();
        } else {
            throw new Exception('AppartientSCDAO: suppression(): n est pas une instance de AppartientSC.');
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
        if ($liaison instanceof AppartientSC) {
            $dbc = BDD_Externe_Connexion::getInstance()->getConnexion();
            $unScenario = $liaison->__get("unScenario");
            $unComposant = $liaison->__get("unComposant");
            $quantite = $liaison->__get("quantite");

            // Prépare la déclaration SQL
            $query = "UPDATE AppartientSC SET unScenario=:unScenario, unComposant=:unComposant, quantite=:quantite WHERE unScenario = '" . $unScenario . "' AND unComposant = '" . $unComposant . "' AND quantite = '" . $quantite . "'";
            $stmt = $dbc->prepare($query);
        
            // Lie les paramètres
            $stmt->bindValue(':unScenario', $liaison->__get("unScenario"), PDO::PARAM_STR);
            $stmt->bindValue(':unComposant', $liaison->__get("unComposant"), PDO::PARAM_STR);
            $stmt->bindValue(':quantite', $liaison->__get("quantite"), PDO::PARAM_STR);
          
            // Exécute la déclaration SQL
            $stmt->execute();
        } else {
            throw new Exception("AppartientSCDAO: modification(): n'est pas une instance de AppartientSC.");
        } 
    }
}

?>