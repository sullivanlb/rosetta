<?php

require_once('BDD_Externe_Connexion.php');

class ComposantDAO {

    private static $dao;

    public function __construct() {
    }

    public final static function getInstance() {
        if (!isset(self::$dao)) {
            self::$dao = new ComposantDAO();
        }
        return self::$dao;
    }

    public final function findAll() {
        $dbc = BDD_Externe_Connexion::getInstance()->getConnexion();
        $query = "SELECT * FROM Composant";
        $stmt = $dbc->query($query);
        $results = $stmt->fetchALL(PDO::FETCH_CLASS, 'Composant');
        return $results;
    }

    public final function insert($request) {
        if ($request instanceof Composant) {
            $dbc = BDD_Externe_Connexion::getInstance()->getConnexion();

            // Prépare la déclaration SQL
            $query = "INSERT INTO Composant(idComposant, nomComposant, uniteComposant, prixComposant) VALUES (:id, :nom, :unite, :prix)";
            $stmt = $dbc->prepare($query);

            // lie les paramètres
            $stmt->bindValue(':id', $request['idComposant'], PDO::PARAM_STR);
            $stmt->bindValue(':nom', $request['nomComposant'], PDO::PARAM_STR);
            $stmt->bindValue(':unite', $request['uniteComposant'], PDO::PARAM_STR);
            $stmt->bindValue(':prix', $request['prixComposant'], PDO::PARAM_STR);
            
            // Exécute la déclaration SQL
            $stmt->execute();
        }
    }

    public function delete($request) { 
        if ($request instanceof Composant) {
            $dbc = BDD_Externe_Connexion::getInstance()->getConnexion();
            $idComposant = $request['idComposant'];

            // Prépare la déclaration SQL
            $query = "DELETE FROM Composant WHERE idComposant = '" . $idComposant . "'";
            $stmt = $dbc->prepare($query);

            // Exécute la déclaration SQL
            $stmt->execute();
        }
    }

    public function update($request) {
        if ($request instanceof Composant) {
            $dbc = BDD_Externe_Connexion::getInstance()->getConnexion();
            $idComposant = $request['idComposant'];

            // Prépare la déclaration SQL
            $query = "UPDATE Composant SET idComposant=:id, nomComposant=:nom, uniteComposant=:unite, prixComposant=:prix WHERE idComposant = '" . $idComposant . "'";
            $stmt = $dbc->prepare($query);
        
            // lie les paramètres
            $stmt->bindValue(':id', $request['idComposant'], PDO::PARAM_STR);
            $stmt->bindValue(':nom', $request['nomComposant'], PDO::PARAM_STR);
            $stmt->bindValue(':unite', $request['uniteComposant'], PDO::PARAM_STR);
            $stmt->bindValue(':prix', $request['prixComposant'], PDO::PARAM_STR);
          
            // Exécute la déclaration SQL
            $stmt->execute();
        } 
    }
}

?>
