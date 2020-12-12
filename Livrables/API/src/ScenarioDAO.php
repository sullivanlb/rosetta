<?php

require_once('BDD_Externe_Connexion.php');

/** 
 * ScenarioDAO est une classe de gestion de Scenario.
 * 
 * ScenarioDAO est une classe permettant de :
 * - Retourner tous les scenarios,
 * - De retourner un scenarios suivant son identifiant,
 * - D'insérer un nouveau scenario,
 * - De supprimer un scenario,
 * - De modifier les données d'un scenario.
 * Une seule instance de cette classe est possible.
 * 
 * @author Garcia Christophe
 * @version $Revision: 1.2 $
 * @access public
 */
class ScenarioDAO {

    /**
     * Objet d'accès aux données
     * 
     * @access private
     */
    private static $dao;

    /**
     * Méthode magique __construct()
     * 
     * Permet de créer le ScenarioDAO
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
            self::$dao = new ScenarioDAO();
        }
        return self::$dao;
    }

    /**
     * Méthode d'accès à l'ensemble des scenarios tousLesElements()
     * 
     * Retourne tous les scenarios de la base de données
     */
    public final function tousLesElements() {
        $dbc = BDD_Externe_Connexion::getInstance()->getConnexion();
        $query = "SELECT * FROM Scenario";
        $stmt = $dbc->query($query);
        $results = $stmt->fetchALL(PDO::FETCH_CLASS, 'Scenario');
        return $results;
    }

    /**
     * Méthode d'accès à un unique scenario unElement()
     * 
     * Retourne le scenario dont l'identifiant est passé en paramètre
     * 
     * @param $id
     */
    public final function unElement($id) {
        $dbc = BDD_Externe_Connexion::getInstance()->getConnexion();
        $query = "SELECT * FROM Scenario WHERE idScenario = '" . $id . "'";
        $stmt = $dbc->query($query);
        $results = $stmt->fetchALL(PDO::FETCH_CLASS, 'Scenario');
        return $results;
    }

    /**
     * Méthode d'insertion insertion()
     * 
     * Insère un nouveau scenario dans la base de données
     * 
     * @param $scenario
     */
    public final function insertion($scenario) {
        if ($scenario instanceof Scenario) {
            $dbc = BDD_Externe_Connexion::getInstance()->getConnexion();

            // Prépare la déclaration SQL
            $query = "INSERT INTO Scenario(idScenario, nomScenario) VALUES (:id, :nom)";
            $stmt = $dbc->prepare($query);

            // Lie les paramètres
            $stmt->bindValue(':id', $scenario->__get("id"), PDO::PARAM_STR);
            $stmt->bindValue(':nom', $scenario->__get("nom"), PDO::PARAM_STR);
            
            // Exécute la déclaration SQL
            $stmt->execute();

            // Fixe l'id attribué à l'objet
            $lastid = $dbc->lastInsertId();
            $scenario->__set("id", $lastid);
        } else {
            throw new Exception('ScenarioDAO: insertion(): n est pas une instance de Scenario.');
        }
    }

    /**
     * Méthode de suppression suppression()
     * 
     * Supprime un scenario de la base de données
     * 
     * @param $scenario
     */
    public function suppression($scenario) { 
        if ($scenario instanceof Scenario) {
            $dbc = BDD_Externe_Connexion::getInstance()->getConnexion();
            $idScenario = $scenario->__get("id");

            // Prépare la déclaration SQL
            $query = "DELETE FROM Scenario WHERE idScenario = '" . $idScenario . "'";
            $stmt = $dbc->prepare($query);

            // Exécute la déclaration SQL
            $stmt->execute();
        } else {
            throw new Exception('ScenarioDAO: suppression(): n est pas une instance de Scenario.');
        }
    }

     /**
     * Méthode de modification suppression()
     * 
     * Modifie les données d'un scenario
     * 
     * @param $scenario
     */
    public function modification($scenario) {
        if ($scenario instanceof Scenario) {
            $dbc = BDD_Externe_Connexion::getInstance()->getConnexion();
            $idScenario = $scenario->__get("id");

            // Prépare la déclaration SQL
            $query = "UPDATE Scenario SET nomScenario=:nom WHERE idScenario = '" . $idScenario . "'";
            $stmt = $dbc->prepare($query);
        
            // Lie les paramètres
            $stmt->bindValue(':nom', $scenario->__get("nom"), PDO::PARAM_STR);
          
            // Exécute la déclaration SQL
            $stmt->execute();
        } else {
            throw new Exception('ScenarioDAO: modification(): n est pas une instance de Scenario.');
        } 
    }
}

?>
