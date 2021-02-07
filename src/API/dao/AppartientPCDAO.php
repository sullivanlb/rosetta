<?php

require_once('../BDD_Externe_Connexion.php');

/** 
 * AppartientPCDAO est une classe de gestion de liaison.
 * 
 * AppartientPCDAO est une classe permettant de :
 * - Retourner toutes les liaisons Pack--Composant,
 * - De retourner une liaison suivant un Pack ou un Composant,
 * - D'insérer une nouvelle liaison,
 * - De supprimer une liaison,
 * - De modifier les données d'une liaison.
 * Une seule instance de cette classe est possible.
 * 
 * @author Leboeuf Sullivan
 * @version $Revision: 1.0 $
 * @access public
 */
class AppartientPCDAO {

    /**
     * Objet d'accès aux données
     * 
     * @access private
     */
    private static $dao;

    /**
     * Méthode magique __construct()
     * 
     * Permet de créer le AppartientPCDAO
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
            self::$dao = new AppartientPCDAO();
        }
        return self::$dao;
    }

    /**
     * Méthode d'accès à l'ensemble des liaisons tousLesElements()
     * 
     * Retourne tous les liaisons de la base de données
     */
    public final function tousLesElements() {
        $dbc = BDD_Externe_Connexion::getInstance()->getConnexion();
        $query = "SELECT * FROM AppartientPC";
        $stmt = $dbc->query($query);
        $results = $stmt->fetchALL(PDO::FETCH_CLASS, 'AppartientPC');
        return $results;
    }

    /**
     * Méthode d'accès à une unique liaison unElement()
     * 
     * Retourne la liaison dont la clé est passé en paramètre
     * 
     * @param mixed $cle
     * @throws Exception
     */
    public final function unElement($cle) {
        $dbc = BDD_Externe_Connexion::getInstance()->getConnexion();

        if ($cle instanceof Pack) {
            $query = "SELECT * FROM AppartientPC WHERE unPack = '" . $cle . "'";
        } else if ($cle instanceof Composant) {
            $query = "SELECT * FROM AppartientPC WHERE unComposant = '" . $cle . "'";
        } else {
            throw new Exception('AppartientPCDAO: unElement(): parametre invalide.');
        }

        $stmt = $dbc->query($query);
        $results = $stmt->fetchALL(PDO::FETCH_CLASS, 'AppartientPC');
        return $results;
    }

    /**
     * Méthode d'insertion insertion()
     * 
     * Insère une nouvelle liaison dans la base de données
     * 
     * @param AppartientPC $liaison
     */
    public final function insertion($liaison) {
        if ($liaison instanceof AppartientPC) {
            $dbc = BDD_Externe_Connexion::getInstance()->getConnexion();

            // Prépare la déclaration SQL
            $query = "INSERT INTO AppartientPC(unPack, unComposant, quantite) VALUES (:unPack, :unComposant, :quantite)";
            $stmt = $dbc->prepare($query);

            // Lie les paramètres
            $stmt->bindValue(':unPack', $liaison->__get("unPack"), PDO::PARAM_STR);
            $stmt->bindValue(':unComposant', $liaison->__get("unComposant"), PDO::PARAM_STR);
            $stmt->bindValue(':quantite', $liaison->__get("quantite"), PDO::PARAM_STR);
            
            // Exécute la déclaration SQL
            $stmt->execute();
        } else {
            throw new Exception("AppartientPCDAO: insertion(): n'est pas une instance de AppartientPC.");
        }
    }

    /**
     * Méthode de suppression suppression()
     * 
     * Supprime une liaison de la base de données
     * 
     * @param AppartientPC $liaison
     */
    public function suppression($liaison) { 
        if ($liaison instanceof AppartientPC) {
            $dbc = BDD_Externe_Connexion::getInstance()->getConnexion();
            $unPack = $liaison->__get("unPack");
            $unComposant = $liaison->__get("unComposant");
            $quantite = $liaison->__get("quantite");

            // Prépare la déclaration SQL
            $query = "DELETE FROM AppartientPC WHERE unPack = '" . $unPack . "' AND unComposant = '" . $unComposant . "' AND quantite = '" . $quantite . "'";
            $stmt = $dbc->prepare($query);

            // Exécute la déclaration SQL
            $stmt->execute();
        } else {
            throw new Exception('AppartientPCDAO: suppression(): n est pas une instance de AppartientPC.');
        }
    }

    /**
     * Méthode de modification suppression()
     * 
     * Modifie les données d'une liaison
     * 
     * @param $liaison
     */
    public function modification($liaison) {
        if ($liaison instanceof AppartientPC) {
            $dbc = BDD_Externe_Connexion::getInstance()->getConnexion();
            $unPack = $liaison->__get("unPack");
            $unComposant = $liaison->__get("unComposant");

            // Prépare la déclaration SQL
            $query = "UPDATE AppartientPC SET quantite=:quantite WHERE unPack = '" . $unPack . "' AND unComposant = '" . $unComposant . "'";
            $stmt = $dbc->prepare($query);
        
            // Lie les paramètres
            $stmt->bindValue(':quantite', $liaison->__get("quantite"), PDO::PARAM_STR);
          
            // Exécute la déclaration SQL
            $stmt->execute();
        } else {
            throw new Exception("AppartientPCDAO: modification(): n'est pas une instance de AppartientPC.");
        } 
    }
}

?>