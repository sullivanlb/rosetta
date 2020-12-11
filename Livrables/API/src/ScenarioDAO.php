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
     * Méthode d'insertion insertion()
     * 
     * Insère un nouveau scenario dans la base de données
     * 
     * @param $request
     */
    public final function insertion($request) {
        if ($request instanceof Scenario) {
            $dbc = BDD_Externe_Connexion::getInstance()->getConnexion();

            // Prépare la déclaration SQL
            $query = "INSERT INTO Scenario(idScenario, nomScenario) VALUES (:id, :nom)";
            $stmt = $dbc->prepare($query);

            // Lie les paramètres
            $stmt->bindValue(':id', $request['idScenario'], PDO::PARAM_STR);
            $stmt->bindValue(':nom', $request['nomScenario'], PDO::PARAM_STR);
            
            // Exécute la déclaration SQL
            $stmt->execute();
        }
    }

    /**
     * Méthode de suppression suppression()
     * 
     * Supprime un scenario de la base de données
     * 
     * @param $request
     */
    public function suppression($request) { 
        if ($request instanceof Scenario) {
            $dbc = BDD_Externe_Connexion::getInstance()->getConnexion();
            $idScenario = $request['idScenario'];

            // Prépare la déclaration SQL
            $query = "DELETE FROM Scenario WHERE idScenario = '" . $idScenario . "'";
            $stmt = $dbc->prepare($query);

            // Exécute la déclaration SQL
            $stmt->execute();
        }
    }

     /**
     * Méthode de modification suppression()
     * 
     * Modifie les données d'un scenario
     * 
     * @param $request
     */
    public function modification($request) {
        if ($request instanceof Scenario) {
            $dbc = BDD_Externe_Connexion::getInstance()->getConnexion();
            $idScenario = $request['idScenario'];

            // Prépare la déclaration SQL
            $query = "UPDATE Scenario SET idScenario=:id, nomScenario=:nom WHERE idScenario = '" . $idScenario . "'";
            $stmt = $dbc->prepare($query);
        
            // Lie les paramètres
            $stmt->bindValue(':id', $request['idScenario'], PDO::PARAM_STR);
            $stmt->bindValue(':nom', $request['nomScenario'], PDO::PARAM_STR);
          
            // Exécute la déclaration SQL
            $stmt->execute();
        } 
    }
}

?>
