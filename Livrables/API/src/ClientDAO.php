<?php

require_once('BDD_Externe_Connexion.php');

/** 
 * ClientDAO est une classe de gestion de Client.
 * 
 * ClientDAO est une classe permettant de :
 * - Retourner tous les clients,
 * - De retourner un client suivant son identifiant,
 * - D'insérer un nouveau client,
 * - De supprimer un client,
 * - De modifier les données d'un client.
 * Une seule instance de cette classe est possible.
 * 
 * @author Garcia Christophe
 * @version $Revision: 1.2 $
 * @access public
 */
class ClientDAO {

    /**
     * Objet d'accès aux données
     * 
     * @access private
     */
    private static $dao;

    /**
     * Méthode magique __construct()
     * 
     * Permet de créer le ClientDAO
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
            self::$dao = new ClientDAO();
        }
        return self::$dao;
    }

    /**
     * Méthode d'accès à l'ensemble des clients tousLesElements()
     * 
     * Retourne tous les clients de la base de données
     */
    public final function tousLesElements() {
        $dbc = BDD_Externe_Connexion::getInstance()->getConnexion();
        $query = "SELECT * FROM Client";
        $stmt = $dbc->query($query);
        $results = $stmt->fetchALL(PDO::FETCH_CLASS, 'Client');
        return $results;
    }

    /**
     * Méthode d'accès à un unique client unElement()
     * 
     * Retourne le client dont l'identifiant est passé en paramètre
     * 
     * @param $id
     */
    public final function unElement($id) {
        $dbc = BDD_Externe_Connexion::getInstance()->getConnexion();
        $query = "SELECT * FROM Client WHERE idClient = '" . $id . "'";
        $stmt = $dbc->query($query);
        $results = $stmt->fetchALL(PDO::FETCH_CLASS, 'Client'); // fetchAll ? fetch ? .toArray() ? jspppp : tests.
        return $results;
    }

    /**
     * Méthode d'insertion insertion()
     * 
     * Insère un nouveau client dans la base de données
     * 
     * @param $request
     */
    public final function insertion($request) {
        if ($request instanceof Client) {
            $dbc = BDD_Externe_Connexion::getInstance()->getConnexion();

            // Prépare la déclaration SQL
            $query = "INSERT INTO Client(idClient, nomClient, prenomClient, adresseClient, emailClient, telClient, sexeClient) VALUES (:id, :nom, :prenom, :adresse, :email, :tel, :sexe)";
            $stmt = $dbc->prepare($query);

            // Lie les paramètres
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

    /**
     * Méthode de suppression suppression()
     * 
     * Supprime un client de la base de données
     * 
     * @param $request
     */
    public function suppression($request) { 
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

    /**
     * Méthode de modification suppression()
     * 
     * Modifie les données d'un client
     * 
     * @param $request
     */
    public function modification($request) {
        if ($request instanceof Client) {
            $dbc = BDD_Externe_Connexion::getInstance()->getConnexion();
            $idClient = $request['idClient'];

            // Prépare la déclaration SQL
            $query = "UPDATE Client SET idClient=:id, nomClient=:nom, prenomClient=:prenom, adresseClient=:adresse, emailClient=:email, telClient=:tel, sexeClient=:sexe WHERE idClient = '" . $idClient . "'";
            $stmt = $dbc->prepare($query);
        
            // Lie les paramètres
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
