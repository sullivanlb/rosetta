<?php

require_once('../BDD_Externe_Connexion.php');

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
        $query = "SELECT * FROM client";
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
        $query = "SELECT * FROM client WHERE idClient = '" . $id . "'";
        $stmt = $dbc->query($query);
        $results = $stmt->fetchALL(PDO::FETCH_CLASS, 'Client');
        return $results;
    }

    /**
     * Méthode d'insertion insertion()
     * 
     * Insère un nouveau client dans la base de données
     * 
     * @param $client
     */
    public final function insertion($client) {
        if ($client instanceof Client) {
            $dbc = BDD_Externe_Connexion::getInstance()->getConnexion();

            // Prépare la déclaration SQL
            $query = "INSERT INTO client(idClient, nomClient, prenomClient, adresseClient, emailClient, telClient, sexeClient) VALUES (:id, :nom, :prenom, :adresse, :email, :tel, :sexe)";
            $stmt = $dbc->prepare($query);

            // Lie les paramètres
            $stmt->bindValue(':id', $client->__get("id"), PDO::PARAM_STR);
            $stmt->bindValue(':nom', $client->__get("nom"), PDO::PARAM_STR);
            $stmt->bindValue(':prenom', $client->__get("prenom"), PDO::PARAM_STR);
            $stmt->bindValue(':adresse', $client->__get("adresse"), PDO::PARAM_STR);
            $stmt->bindValue(':email', $client->__get("email"), PDO::PARAM_STR);
            $stmt->bindValue(':tel', $client->__get("tel"), PDO::PARAM_STR);
            $stmt->bindValue(':sexe', $client->__get("sexe"), PDO::PARAM_STR);
            
            // Exécute la déclaration SQL
            $stmt->execute();

            // Fixe l'id attribué à l'objet
            $lastid = $dbc->lastInsertId();
            $client->__set("id", $lastid);
        } else {
            throw new Exception('ClientDAO: insertion(): n est pas une instance de Client.');
        }
    }

    /**
     * Méthode de suppression suppression()
     * 
     * Supprime un client de la base de données
     * 
     * @param $client
     */
    public function suppression($client) { 
        if ($client instanceof Client) {
            $dbc = BDD_Externe_Connexion::getInstance()->getConnexion();
            $idClient = $client->__get("id");

            // Prépare la déclaration SQL
            $query = "DELETE FROM client WHERE idClient = '" . $idClient . "'";
            $stmt = $dbc->prepare($query);

            // Exécute la déclaration SQL
            $stmt->execute();
        } else {
            throw new Exception('ClientDAO: suppression(): n est pas une instance de Client.');
        }
    }

    /**
     * Méthode de modification suppression()
     * 
     * Modifie les données d'un client
     * 
     * @param $client
     */
    public function modification($client) {
        if ($client instanceof Client) {
            $dbc = BDD_Externe_Connexion::getInstance()->getConnexion();
            $idClient = $client->__get("id");

            // Prépare la déclaration SQL
            $query = "UPDATE client SET nomClient=:nom, prenomClient=:prenom, adresseClient=:adresse, emailClient=:email, telClient=:tel, sexeClient=:sexe WHERE idClient = '" . $idClient . "'";
            $stmt = $dbc->prepare($query);
        
            // Lie les paramètres
            $stmt->bindValue(':nom', $client->__get("nom"), PDO::PARAM_STR);
            $stmt->bindValue(':prenom', $client->__get("prenom"), PDO::PARAM_STR);
            $stmt->bindValue(':adresse', $client->__get("adresse"), PDO::PARAM_STR);
            $stmt->bindValue(':email', $client->__get("email"), PDO::PARAM_STR);
            $stmt->bindValue(':tel', $client->__get("tel"), PDO::PARAM_STR);
            $stmt->bindValue(':sexe', $client->__get("sexe"), PDO::PARAM_STR);
          
            // Exécute la déclaration SQL
            $stmt->execute();
        } else {
            throw new Exception('ClientDAO: modification(): n est pas une instance de Client.');
        } 
    }
}

?>
