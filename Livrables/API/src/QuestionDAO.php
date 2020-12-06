<?php

require_once('BDD_Externe_Connexion.php');

class QuestionDAO {

    private static $dao;

    public function __construct() {
    }

    public final static function getInstance() {
        if (!isset(self::$dao)) {
            self::$dao = new QuestionDAO();
        }
        return self::$dao;
    }

    public final function findAll() {
        $dbc = BDD_Externe_Connexion::getInstance()->getConnexion();
        $query = "SELECT * FROM Question";
        $stmt = $dbc->query($query);
        $results = $stmt->fetchALL(PDO::FETCH_CLASS, 'Question');
        return $results;
    }

    public final function insert($request) {
        if ($request instanceof Question) {
            $dbc = BDD_Externe_Connexion::getInstance()->getConnexion();

            // Prépare la déclaration SQL
            $query = "INSERT INTO Question(idQuestion, nomQuestion) VALUES (:id, :nom)";
            $stmt = $dbc->prepare($query);

            // lie les paramètres
            $stmt->bindValue(':id', $request['idQuestion'], PDO::PARAM_STR);
            $stmt->bindValue(':nom', $request['nomQuestion'], PDO::PARAM_STR);
            
            // Exécute la déclaration SQL
            $stmt->execute();
        }
    }

    public function delete($request) { 
        if ($request instanceof Question) {
            $dbc = BDD_Externe_Connexion::getInstance()->getConnexion();
            $idQuestion = $request['idQuestion'];

            // Prépare la déclaration SQL
            $query = "DELETE FROM Question WHERE idQuestion = '" . $idQuestion . "'";
            $stmt = $dbc->prepare($query);

            // Exécute la déclaration SQL
            $stmt->execute();
        }
    }

    public function update($request) {
        if ($request instanceof Question) {
            $dbc = BDD_Externe_Connexion::getInstance()->getConnexion();
            $idQuestion = $request['idQuestion'];

            // Prépare la déclaration SQL
            $query = "UPDATE Question SET idQuestion=:id, nomQuestion=:nom WHERE idQuestion = '" . $idQuestion . "'";
            $stmt = $dbc->prepare($query);
        
            // lie les paramètres
            $stmt->bindValue(':id', $request['idQuestion'], PDO::PARAM_STR);
            $stmt->bindValue(':nom', $request['nomQuestion'], PDO::PARAM_STR);
          
            // Exécute la déclaration SQL
            $stmt->execute();
        } 
    }
}

?>
