<?php

require_once('../BDD_Externe_Connexion.php');

/** 
 * DevisDAO est une classe de gestion de Devis.
 * 
 * DevisDAO est une classe permettant de :
 * - Retourner tous les devis,
 * - De retourner un devis suivant son identifiant,
 * - D'insérer un nouveau devis,
 * - De supprimer un devis,
 * - De modifier les données d'un devis.
 * Une seule instance de cette classe est possible.
 * 
 * @author Garcia Christophe
 * @version $Revision: 1.2 $
 * @access public
 */
class DevisDAO {

    /**
     * Objet d'accès aux données
     * 
     * @access private
     */
    private static $dao;

    /**
     * Méthode magique __construct()
     * 
     * Permet de créer le DevisDAO
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
            self::$dao = new DevisDAO();
        }
        return self::$dao;
    }

    /**
     * Méthode d'accès à l'ensemble des devis tousLesElements()
     * 
     * Retourne tous les devis de la base de données
     */
    public final function tousLesElements() {
        $dbc = BDD_Externe_Connexion::getInstance()->getConnexion();
        $query = "SELECT * FROM devis";
        $stmt = $dbc->query($query);
        $results = $stmt->fetchALL(PDO::FETCH_CLASS, 'Devis');
        return $results;
    }

    /**
     * Méthode d'accès à un unique devis unElement()
     * 
     * Retourne le devis dont l'identifiant est passé en paramètre
     * 
     * @param $id
     */
    public final function unElement($id) {
        $dbc = BDD_Externe_Connexion::getInstance()->getConnexion();
        $query = "SELECT * FROM devis WHERE idDevis = '" . $id . "'";
        $stmt = $dbc->query($query);
        $results = $stmt->fetchALL(PDO::FETCH_CLASS, 'Devis');
        return $results;
    }

    /**
     * Méthode d'insertion insertion()
     * 
     * Insère un nouveau devis dans la base de données
     * 
     * @param $devis
     */
    public final function insertion($devis) {
        if ($devis instanceof Devis) {
            $dbc = BDD_Externe_Connexion::getInstance()->getConnexion();

            // Prépare la déclaration SQL
            $query = "INSERT INTO devis(idDevis, nomDevis, descriptionDevis, dureeDevis, dateEditionDevis, dateTravauxDevis) VALUES (:id, :nom, :description, :duree, :dateEdition, :dateTravaux)";
            $stmt = $dbc->prepare($query);

            // Lie les paramètres
            $stmt->bindValue(':id', $devis->__get("id"), PDO::PARAM_STR);
            $stmt->bindValue(':nom', $devis->__get("nom"), PDO::PARAM_STR);
            $stmt->bindValue(':description', $devis->__get("description"), PDO::PARAM_STR);
            $stmt->bindValue(':duree', $devis->__get("duree"), PDO::PARAM_STR);
            $stmt->bindValue(':dateEdition', $devis->__get("dateEdition"), PDO::PARAM_STR);
            $stmt->bindValue(':dateTravaux', $devis->__get("dateTravaux"), PDO::PARAM_STR);
            
            // Exécute la déclaration SQL
            $stmt->execute();

            // Fixe l'id attribué à l'objet
            $lastid = $dbc->lastInsertId();
            $devis->__set("id", $lastid);
        } else {
            throw new Exception('DevisDAO: insertion(): n est pas une instance de Devis.');
        }
    }

    /**
     * Méthode de suppression suppression()
     * 
     * Supprime un devis de la base de données
     * 
     * @param $devis
     */
    public function suppression($devis) { 
        if ($devis instanceof Devis) {
            $dbc = BDD_Externe_Connexion::getInstance()->getConnexion();
            $idDevis = $devis->__get("id");

            // Prépare la déclaration SQL
            $query = "DELETE FROM devis WHERE idDevis = '" . $idDevis . "'";
            $stmt = $dbc->prepare($query);

            // Exécute la déclaration SQL
            $stmt->execute();
        } else {
            throw new Exception('DevisDAO: suppression(): n est pas une instance de Devis.');
        }
    }

     /**
     * Méthode de modification suppression()
     * 
     * Modifie les données d'un devis
     * 
     * @param $devis
     */
    public function modification($devis) {
        if ($devis instanceof Devis) {
            $dbc = BDD_Externe_Connexion::getInstance()->getConnexion();
            $idDevis = $devis->__get("id");

            // Prépare la déclaration SQL
            $query = "UPDATE devis SET nomDevis=:nom, descriptionDevis=:description, dureeDevis=:duree, dateEditionDevis=:dateEdition, dateTravauxDevis=:dateTravaux WHERE idDevis = '" . $idDevis . "'";
            $stmt = $dbc->prepare($query);
        
            // Lie les paramètres
            $stmt->bindValue(':nom', $devis->__get("nom"), PDO::PARAM_STR);
            $stmt->bindValue(':description', $devis->__get("description"), PDO::PARAM_STR);
            $stmt->bindValue(':duree', $devis->__get("duree"), PDO::PARAM_STR);
            $stmt->bindValue(':dateEdition', $devis->__get("dateEdition"), PDO::PARAM_STR);
            $stmt->bindValue(':dateTravaux', $devis->__get("dateTravaux"), PDO::PARAM_STR);
          
            // Exécute la déclaration SQL
            $stmt->execute();
        } else {
            throw new Exception('DevisDAO: modification(): n est pas une instance de Devis.');
        } 
    }
}

?>
