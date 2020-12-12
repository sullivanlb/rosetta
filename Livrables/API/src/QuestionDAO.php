<?php

require_once('BDD_Externe_Connexion.php');

/** 
 * QuestionDAO est une classe de gestion de Question.
 * 
 * ComposantDAO est une classe permettant de :
 * - Retourner toutes les questions,
 * - De retourner une question suivant son identifiant,
 * - D'insérer une nouvelle question,
 * - De supprimer une question,
 * - De modifier les données d'une question.
 * Une seule instance de cette classe est possible.
 * 
 * @author Garcia Christophe
 * @version $Revision: 1.2 $
 * @access public
 */
class QuestionDAO {

    /**
     * Objet d'accès aux données
     * 
     * @access private
     */
    private static $dao;

    /**
     * Méthode magique __construct()
     * 
     * Permet de créer le QuestionDAO
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
            self::$dao = new QuestionDAO();
        }
        return self::$dao;
    }

    /**
     * Méthode d'accès à l'ensemble des questions tousLesElements()
     * 
     * Retourne toutes les questions de la base de données
     */
    public final function tousLesElements() {
        $dbc = BDD_Externe_Connexion::getInstance()->getConnexion();
        $query = "SELECT * FROM Question";
        $stmt = $dbc->query($query);
        $results = $stmt->fetchALL(PDO::FETCH_CLASS, 'Question');
        return $results;
    }

    /**
     * Méthode d'accès à une unique question unElement()
     * 
     * Retourne la question dont l'identifiant est passé en paramètre
     * 
     * @param $id
     */
    public final function unElement($id) {
        $dbc = BDD_Externe_Connexion::getInstance()->getConnexion();
        $query = "SELECT * FROM Question WHERE idQuestion = '" . $id . "'";
        $stmt = $dbc->query($query);
        $results = $stmt->fetchALL(PDO::FETCH_CLASS, 'Question');
        return $results;
    }

    /**
     * Méthode d'insertion insertion()
     * 
     * Insère une nouvelle question dans la base de données
     * 
     * @param $question
     */
    public final function insertion($question) {
        if ($question instanceof Question) {
            $dbc = BDD_Externe_Connexion::getInstance()->getConnexion();

            // Prépare la déclaration SQL
            $query = "INSERT INTO Question(idQuestion, nomQuestion) VALUES (:id, :nom)";
            $stmt = $dbc->prepare($query);

            // Lie les paramètres
            $stmt->bindValue(':id', $question->__get("id"), PDO::PARAM_STR);
            $stmt->bindValue(':nom', $question->__get("nom"), PDO::PARAM_STR);
            
            // Exécute la déclaration SQL
            $stmt->execute();

            // Fixe l'id attribué à l'objet
            $lastid = $dbc->lastInsertId();
            $question->__set("id", $lastid);
        } else {
            throw new Exception('QuestionDAO: insertion(): n est pas une instance de Question.');
        }
    }

    /**
     * Méthode de suppression suppression()
     * 
     * Supprime une question de la base de données
     * 
     * @param $question
     */
    public function suppression($question) { 
        if ($question instanceof Question) {
            $dbc = BDD_Externe_Connexion::getInstance()->getConnexion();
            $idQuestion = $question->__get("id");

            // Prépare la déclaration SQL
            $query = "DELETE FROM Question WHERE idQuestion = '" . $idQuestion . "'";
            $stmt = $dbc->prepare($query);

            // Exécute la déclaration SQL
            $stmt->execute();
        } else {
            throw new Exception('QuestionDAO: suppression(): n est pas une instance de Question.');
        }
    }

     /**
     * Méthode de modification suppression()
     * 
     * Modifie les données d'une question
     * 
     * @param $question
     */
    public function modification($question) {
        if ($question instanceof Question) {
            $dbc = BDD_Externe_Connexion::getInstance()->getConnexion();
            $idQuestion = $question->__get("id");

            // Prépare la déclaration SQL
            $query = "UPDATE Question SET nomQuestion=:nom WHERE idQuestion = '" . $idQuestion . "'";
            $stmt = $dbc->prepare($query);
        
            // Lie les paramètres
            $stmt->bindValue(':nom', $question->__get("nom"), PDO::PARAM_STR);
          
            // Exécute la déclaration SQL
            $stmt->execute();
        } else {
            throw new Exception('QuestionDAO: modification(): n est pas une instance de Question.');
        } 
    }
}

?>
