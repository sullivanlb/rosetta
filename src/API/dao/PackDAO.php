<?php

require_once('../BDD_Externe_Connexion.php');

/** 
 * PackDAO est une classe de gestion de Pack.
 * 
 * PackDAO est une classe permettant de :
 * - Retourner tous les packs,
 * - De retourner un packs suivant son identifiant,
 * - D'insérer un nouveau packs,
 * - De supprimer un packs,
 * - De modifier les données d'un packs.
 * Une seule instance de cette classe est possible.
 * 
 * @author Garcia Christophe
 * @version $Revision: 1.2 $
 * @access public
 */
class PackDAO {

    /**
     * Objet d'accès aux données
     * 
     * @access private
     */
    private static $dao;

    /**
     * Méthode magique __construct()
     * 
     * Permet de créer le PackDAO
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
            self::$dao = new PackDAO();
        }
        return self::$dao;
    }

    /**
     * Méthode d'accès à l'ensemble des packs tousLesElements()
     * 
     * Retourne tous les packs de la base de données
     */
    public final function tousLesElements() {
        $dbc = BDD_Externe_Connexion::getInstance()->getConnexion();
        $query = "SELECT * FROM pack";
        $stmt = $dbc->query($query);
        $results = $stmt->fetchALL(PDO::FETCH_CLASS, 'Pack');
        return $results;
    }

    /**
     * Méthode d'accès à un unique pack unElement()
     * 
     * Retourne le pack dont l'identifiant est passé en paramètre
     * 
     * @param $id
     */
    public final function unElement($id) {
        $dbc = BDD_Externe_Connexion::getInstance()->getConnexion();
        $query = "SELECT * FROM pack WHERE idPack = '" . $id . "'";
        $stmt = $dbc->query($query);
        $results = $stmt->fetchALL(PDO::FETCH_CLASS, 'Pack');
        return $results;
    }

    /**
     * Méthode d'insertion insertion()
     * 
     * Insère un nouveau pack dans la base de données
     * 
     * @param $pack
     */
    public final function insertion($pack) {
        if ($pack instanceof Pack) {
            $dbc = BDD_Externe_Connexion::getInstance()->getConnexion();

            // Prépare la déclaration SQL
            $query = "INSERT INTO pack(idPack, nomPack) VALUES (:id, :nom)";
            $stmt = $dbc->prepare($query);

            // Lie les paramètres
            $stmt->bindValue(':id', $pack->__get("id"), PDO::PARAM_STR);
            $stmt->bindValue(':nom', $pack->__get("nom"), PDO::PARAM_STR);
            
            // Exécute la déclaration SQL
            $stmt->execute();

            // Fixe l'id attribué à l'objet
            $lastid = $dbc->lastInsertId();
            $pack->__set("id", $lastid);
        } else {
            throw new Exception('PackDAO: insertion(): n est pas une instance de Pack.');
        }
    }

    /**
     * Méthode de suppression suppression()
     * 
     * Supprime un pack de la base de données
     * 
     * @param $pack
     */
    public function suppression($pack) { 
        if ($pack instanceof Pack) {
            $dbc = BDD_Externe_Connexion::getInstance()->getConnexion();
            $idPack = $pack->__get("id");

            // Prépare la déclaration SQL
            $query = "DELETE FROM pack WHERE idPack = '" . $idPack . "'";
            $stmt = $dbc->prepare($query);

            // Exécute la déclaration SQL
            $stmt->execute();
        } else {
            throw new Exception('PackDAO: suppression(): n est pas une instance de Pack.');
        }
    }

    /**
     * Méthode de modification suppression()
     * 
     * Modifie les données d'un pack
     * 
     * @param $pack
     */
    public function modification($pack) {
        if ($pack instanceof Pack) {
            $dbc = BDD_Externe_Connexion::getInstance()->getConnexion();
            $idPack = $pack->__get("id");

            // Prépare la déclaration SQL
            $query = "UPDATE pack SET nomPack=:nom WHERE idPack = '" . $idPack . "'";
            $stmt = $dbc->prepare($query);
        
            // Lie les paramètres
            $stmt->bindValue(':nom', $pack->__get("nom"), PDO::PARAM_STR);
          
            // Exécute la déclaration SQL
            $stmt->execute();
        } else {
            throw new Exception('PackDAO: modification(): n est pas une instance de Pack.');
        } 
    }
}

?>
