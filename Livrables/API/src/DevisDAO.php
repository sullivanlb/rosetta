<?php

require_once('BDD_Externe_Connexion.php');

class DevisDAO {

    private static $dao;

    public function __construct() {
    }

    public final static function getInstance() {
        if (!isset(self::$dao)) {
            self::$dao = new DevisDAO();
        }
        return self::$dao;
    }

    public final function findAll() {
        $dbc = BDD_Externe_Connexion::getInstance()->getConnexion();
        $query = "SELECT * FROM Devis";
        $stmt = $dbc->query($query);
        $results = $stmt->fetchALL(PDO::FETCH_CLASS, 'Devis');
        return $results;
    }

    public final function insert($request) {
        if ($request instanceof Devis) {
            $dbc = BDD_Externe_Connexion::getInstance()->getConnexion();

            // Prépare la déclaration SQL
            $query = "INSERT INTO Devis(idDevis, nomDevis) VALUES (:id, :nom)";
            $stmt = $dbc->prepare($query);

            // lie les paramètres
            $stmt->bindValue(':id', $request['idDevis'], PDO::PARAM_STR);
            $stmt->bindValue(':nom', $request['nomDevis'], PDO::PARAM_STR);
            
            // Exécute la déclaration SQL
            $stmt->execute();
        }
    }

    public function delete($request) { 
        if ($request instanceof Devis) {
            $dbc = BDD_Externe_Connexion::getInstance()->getConnexion();
            $idDevis = $request['idDevis'];

            // Prépare la déclaration SQL
            $query = "DELETE FROM Devis WHERE idDevis = '" . $idDevis . "'";
            $stmt = $dbc->prepare($query);

            // Exécute la déclaration SQL
            $stmt->execute();
        }
    }

    public function update($request) {
        if ($request instanceof Devis) {
            $dbc = BDD_Externe_Connexion::getInstance()->getConnexion();
            $idDevis = $request['idDevis'];

            // Prépare la déclaration SQL
            $query = "UPDATE Devis SET idDevis=:id, nomDevis=:nom WHERE idDevis = '" . $idDevis . "'";
            $stmt = $dbc->prepare($query);
        
            // lie les paramètres
            $stmt->bindValue(':id', $request['idDevis'], PDO::PARAM_STR);
            $stmt->bindValue(':nom', $request['nomDevis'], PDO::PARAM_STR);
          
            // Exécute la déclaration SQL
            $stmt->execute();
        } 
    }
}

?>
