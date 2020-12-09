<?php

require_once('BDD_Externe_Connexion.php');

class ScenarioDAO {

    private static $dao;

    public function __construct() {
    }

    public final static function getInstance() {
        if (!isset(self::$dao)) {
            self::$dao = new ScenarioDAO();
        }
        return self::$dao;
    }

    public final function findAll() {
        $dbc = BDD_Externe_Connexion::getInstance()->getConnexion();
        $query = "SELECT * FROM Scenario";
        $stmt = $dbc->query($query);
        $results = $stmt->fetchALL(PDO::FETCH_CLASS, 'Scenario');
        return $results;
    }

    public final function insert($request) {
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

    public function delete($request) { 
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

    public function update($request) {
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
