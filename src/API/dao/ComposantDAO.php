<?php

require_once('../BDD_Externe_Connexion.php');

/** 
 * ComposantDAO est une classe de gestion de Composant.
 * 
 * ComposantDAO est une classe permettant de :
 * - Retourner tous les composants,
 * - De retourner un composant suivant son identifiant,
 * - D'insérer un nouveau composant,
 * - De supprimer un composant,
 * - De modifier les données d'un composant.
 * Une seule instance de cette classe est possible.
 * 
 * @author Garcia Christophe
 * @version $Revision: 1.2 $
 * @access public
 */
class ComposantDAO {

    /**
     * Objet d'accès aux données
     * 
     * @access private
     */
    private static $dao;

    /**
     * Méthode magique __construct()
     * 
     * Permet de créer le ComposantDAO
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
            self::$dao = new ComposantDAO();
        }
        return self::$dao;
    }

    /**
     * Méthode d'accès à l'ensemble des composants tousLesElements()
     * 
     * Retourne tous les composants de la base de données
     */
    public final function tousLesElements() {
        $dbc = BDD_Externe_Connexion::getInstance()->getConnexion();
        $query = "SELECT * FROM Composant";
        $stmt = $dbc->query($query);
        $results = $stmt->fetchALL(PDO::FETCH_CLASS, 'Composant');
        return $results;
    }

    /**
     * Méthode d'accès à un unique composant unElement()
     * 
     * Retourne le composant dont l'identifiant est passé en paramètre
     * 
     * @param $id
     */
    public final function unElement($id) {
        $dbc = BDD_Externe_Connexion::getInstance()->getConnexion();
        $query = "SELECT * FROM Composant WHERE idComposant = '" . $id . "'";
        $stmt = $dbc->query($query);
        $results = $stmt->fetchALL(PDO::FETCH_CLASS, 'Composant');
        return $results;
    }

    /**
     * Méthode d'insertion insertion()
     * 
     * Insère un nouveau composant dans la base de données
     * 
     * @param $composant
     */
    public final function insertion($composant) {
        if ($composant instanceof Composant) {
            $dbc = BDD_Externe_Connexion::getInstance()->getConnexion();

            // Prépare la déclaration SQL
            $query = "INSERT INTO Composant(idComposant, nomComposant, uniteComposant, prixComposant) VALUES (:id, :nom, :unite, :prix)";
            $stmt = $dbc->prepare($query);

            // Lie les paramètres
            $stmt->bindValue(':id', $composant->__get("id"), PDO::PARAM_STR);
            $stmt->bindValue(':nom', $composant->__get("nom"), PDO::PARAM_STR);
            $stmt->bindValue(':unite', $composant->__get("unite"), PDO::PARAM_STR);
            $stmt->bindValue(':prix', $composant->__get("prix"), PDO::PARAM_STR);
            
            // Exécute la déclaration SQL
            $stmt->execute();

            // Fixe l'id attribué à l'objet
            $lastid = $dbc->lastInsertId();
            $composant->__set("id", $lastid);
        } else {
            throw new Exception('ComposantDAO: insertion(): n est pas une instance de Composant.');
        }
    }

    /**
     * Méthode de suppression suppression()
     * 
     * Supprime un composant de la base de données
     * 
     * @param $composant
     */
    public function suppression($composant) { 
        if ($composant instanceof Composant) {
            $dbc = BDD_Externe_Connexion::getInstance()->getConnexion();
            $idComposant = $composant->__get("id");

            // Prépare la déclaration SQL
            $query = "DELETE FROM Composant WHERE idComposant = '" . $idComposant . "'";
            $stmt = $dbc->prepare($query);

            // Exécute la déclaration SQL
            $stmt->execute();
        } else {
            throw new Exception('ComposantDAO: suppression(): n est pas une instance de Composant.');
        }
    }

    /**
     * Méthode de modification suppression()
     * 
     * Modifie les données d'un composant
     * 
     * @param $composant
     */
    public function modification($composant) {
        if ($composant instanceof Composant) {
            $dbc = BDD_Externe_Connexion::getInstance()->getConnexion();
            $idComposant = $composant->__get("id");

            // Prépare la déclaration SQL
            $query = "UPDATE Composant SET nomComposant=:nom, uniteComposant=:unite, prixComposant=:prix WHERE idComposant = '" . $idComposant . "'";
            $stmt = $dbc->prepare($query);
        
            // Lie les paramètres
            $stmt->bindValue(':nom', $composant->__get("nom"), PDO::PARAM_STR);
            $stmt->bindValue(':unite', $composant->__get("unite"), PDO::PARAM_STR);
            $stmt->bindValue(':prix', $composant->__get("prix"), PDO::PARAM_STR);
          
            // Exécute la déclaration SQL
            $stmt->execute();
        } else {
            throw new Exception('ComposantDAO: modification(): n est pas une instance de Composant.');
        } 
    }
}

?>
