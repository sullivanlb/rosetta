<?php

require_once('BDD_Externe_Connexion.php');

class PackDAO {

    private static $dao;

    public function __construct() {
    }

    public final static function getInstance() {
        if (!isset(self::$dao)) {
            self::$dao = new PackDAO();
        }
        return self::$dao;
    }

    public final function findAll() {
        $dbc = BDD_Externe_Connexion::getInstance()->getConnexion();
        $query = "SELECT * FROM Pack";
        $stmt = $dbc->query($query);
        $results = $stmt->fetchALL(PDO::FETCH_CLASS, 'Pack');
        return $results;
    }

    public final function insert($request) {
        if ($request instanceof Pack) {
            $dbc = BDD_Externe_Connexion::getInstance()->getConnexion();

            // Prépare la déclaration SQL
            $query = "INSERT INTO Pack(idPack, nomPack) VALUES (:id, :nom)";
            $stmt = $dbc->prepare($query);

            // lie les paramètres
            $stmt->bindValue(':id', $request['idPack'], PDO::PARAM_STR);
            $stmt->bindValue(':nom', $request['nomPack'], PDO::PARAM_STR);
            
            // Exécute la déclaration SQL
            $stmt->execute();
        }
    }

    public function delete($request) { 
        if ($request instanceof Pack) {
            $dbc = BDD_Externe_Connexion::getInstance()->getConnexion();
            $idPack = $request['idPack'];

            // Prépare la déclaration SQL
            $query = "DELETE FROM Pack WHERE idPack = '" . $idPack . "'";
            $stmt = $dbc->prepare($query);

            // Exécute la déclaration SQL
            $stmt->execute();
        }
    }

    public function update($request) {
        if ($request instanceof Pack) {
            $dbc = BDD_Externe_Connexion::getInstance()->getConnexion();
            $idPack = $request['idPack'];

            // Prépare la déclaration SQL
            $query = "UPDATE Pack SET idPack=:id, nomPack=:nom WHERE idPack = '" . $idPack . "'";
            $stmt = $dbc->prepare($query);
        
            // lie les paramètres
            $stmt->bindValue(':id', $request['idPack'], PDO::PARAM_STR);
            $stmt->bindValue(':nom', $request['nomPack'], PDO::PARAM_STR);
          
            // Exécute la déclaration SQL
            $stmt->execute();
        } 
    }
}

?>
