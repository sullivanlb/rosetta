<?php

require_once('../BDD_Externe_Connexion.php');

/** 
 * AppartientSQDAO est une classe de gestion de liaison.
 * 
 * AppartientSQDAO est une classe permettant de :
 * - Retourner toutes les liaisons Scenario--Question,
 * - De retourner une liaison suivant un Scenario ou un Question,
 * - D'insérer une nouvelle liaison,
 * - De supprimer une liaison,
 * - De modifier les données d'une liaison.
 * Une seule instance de cette classe est possible.
 * 
 * @author Leboeuf Sullivan
 * @version $Revision: 1.0 $
 * @access public
 */
class AppartientSQDAO {

    /**
     * Objet d'accès aux données
     * 
     * @access private
     */
    private static $dao;

    /**
     * Méthode magique __construct()
     * 
     * Permet de créer le AppartientSQDAO
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
            self::$dao = new AppartientSQDAO();
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
        $query = "SELECT * FROM appartientsq";
        $stmt = $dbc->query($query);
        $results = $stmt->fetchALL(PDO::FETCH_CLASS, 'AppartientSQ');
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
        $id = $cle->__get("id");

        if ($cle instanceof Scenario) {
            $query = "SELECT * FROM appartientsq WHERE unScenario = '" . $id . "'";
        } else if ($cle instanceof Question) {
            $query = "SELECT * FROM appartientsq WHERE uneQuestion = '" . $id . "'";
        } else {
            throw new Exception('AppartientSQDAO: unElement(): parametre invalide.');
        }

        $stmt = $dbc->query($query);
        $results = $stmt->fetchALL(PDO::FETCH_CLASS, 'AppartientSQ');
        return $results;
    }

    /**
     * Méthode d'insertion insertion()
     * 
     * Insère une nouvelle liaison dans la base de données
     * 
     * @param AppartientSQ $liaison
     */
    public final function insertion($liaison) {
        if ($liaison instanceof AppartientSQ) {
            $dbc = BDD_Externe_Connexion::getInstance()->getConnexion();

            // Prépare la déclaration SQL
            $query = "INSERT INTO appartientsq(unScenario, uneQuestion) VALUES (:unScenario, :uneQuestion)";
            $stmt = $dbc->prepare($query);

            // Lie les paramètres
            $stmt->bindValue(':unScenario', $liaison->__get("unScenario")->__get("id"), PDO::PARAM_STR);
            $stmt->bindValue(':uneQuestion', $liaison->__get("uneQuestion")->__get("id"), PDO::PARAM_STR);
            
            // Exécute la déclaration SQL
            $stmt->execute();
        } else {
            throw new Exception("AppartientSQDAO: insertion(): n'est pas une instance de AppartientSQ.");
        }
    }

    /**
     * Méthode de suppression suppression()
     * 
     * Supprime une liaison de la base de données
     * 
     * @param AppartientSQ $liaison
     */
    public function suppression($liaison) { 
        if ($liaison instanceof AppartientSQ) {
            $dbc = BDD_Externe_Connexion::getInstance()->getConnexion();
            $unScenario = $liaison->__get("unScenario");
            $uneQuestion = $liaison->__get("uneQuestion");

            // Prépare la déclaration SQL
            $query = "DELETE FROM appartientsq WHERE unScenario = '" . $unScenario . "' AND uneQuestion = '" . $uneQuestion . "'";
            $stmt = $dbc->prepare($query);

            // Exécute la déclaration SQL
            $stmt->execute();
        } else {
            throw new Exception('AppartientSQDAO: suppression(): n est pas une instance de AppartientSQ.');
        }
    }
}

?>