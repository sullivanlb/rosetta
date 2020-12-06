<?php

require_once('BDD_Externe_Connexion.php');

class ClientDAO {

    private static $dao;

    public function __construct() {
    }

    public final static function getInstance() {
        if (!isset(self::$dao)) {
            self::$dao = new ClientDAO();
        }
        return self::$dao;
    }

    public final function findAll() {
        $dbc = BDD_Externe_Connexion::getInstance()->getConnexion();
        $query = "SELECT * FROM Client";
        $stmt = $dbc->query($query);
        $results = $stmt->fetchALL(PDO::FETCH_CLASS, 'Client');
        return $results;
    }

    public final function insert($request) {
        if ($request instanceof Client) {
            $dbc = BDD_Externe_Connexion::getInstance()->getConnexion();

            // Prépare la déclaration SQL
            $query = "INSERT INTO Client(idClient, nomClient, prenomClient, adresseClient, emailClient, telClient, sexeClient) VALUES (:id, :nom, :prenom, :adresse, :email, :tel, :sexe)";
            $stmt = $dbc->prepare($query);

            // lie les paramètres
            $stmt->bindValue(':id', $request['idClient'], PDO::PARAM_STR);
            $stmt->bindValue(':nom', $request['nomClient'], PDO::PARAM_STR);
            $stmt->bindValue(':prenom', $request['prenomClient'], PDO::PARAM_STR);
            $stmt->bindValue(':adresse', $request['adresseClient'], PDO::PARAM_STR);
            $stmt->bindValue(':email', $request['emailClient'], PDO::PARAM_STR);
            $stmt->bindValue(':tel', $request['telClient'], PDO::PARAM_STR);
            $stmt->bindValue(':sexe', $request['sexeClient'], PDO::PARAM_STR);
            
            // Exécute la déclaration SQL
            $stmt->execute();
        }
    }

    public function delete($request) { 
        if ($request instanceof Client) {
            $dbc = BDD_Externe_Connexion::getInstance()->getConnexion();
            $idClient = $request['idClient'];

            // Prépare la déclaration SQL
            $query = "DELETE FROM Client WHERE idClient = '" . $idClient . "'";
            $stmt = $dbc->prepare($query);

            // Exécute la déclaration SQL
            $stmt->execute();
        }
    }

    public function update($request) {
        if ($request instanceof Client) {
            $dbc = BDD_Externe_Connexion::getInstance()->getConnexion();
            $idClient = $request['idClient'];

            // Prépare la déclaration SQL
            $query = "UPDATE Client SET idClient=:id, nomClient=:nom, prenomClient=:prenom, adresseClient=:adresse, emailClient=:email, telClient=:tel, sexeClient=:sexe WHERE idClient = '" . $idClient . "'";
            $stmt = $dbc->prepare($query);
        
            // lie les paramètres
            $stmt->bindValue(':id', $request['idClient'], PDO::PARAM_STR);
            $stmt->bindValue(':nom', $request['nomClient'], PDO::PARAM_STR);
            $stmt->bindValue(':prenom', $request['prenomClient'], PDO::PARAM_STR);
            $stmt->bindValue(':adresse', $request['adresseClient'], PDO::PARAM_STR);
            $stmt->bindValue(':email', $request['emailClient'], PDO::PARAM_STR);
            $stmt->bindValue(':tel', $request['telClient'], PDO::PARAM_STR);
            $stmt->bindValue(':sexe', $request['sexeClient'], PDO::PARAM_STR);
          
            // Exécute la déclaration SQL
            $stmt->execute();
        } 
    }
}

?>
