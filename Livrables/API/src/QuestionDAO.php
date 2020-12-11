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
     * Méthode d'insertion insertion()
     * 
     * Insère une nouvelle question dans la base de données
     * 
     * @param $request
     */
    public final function insertion($request) {
        if ($request instanceof Question) {
            $dbc = BDD_Externe_Connexion::getInstance()->getConnexion();

            // Prépare la déclaration SQL
            $query = "INSERT INTO Question(idQuestion, nomQuestion) VALUES (:id, :nom)";
            $stmt = $dbc->prepare($query);

            // Lie les paramètres
            $stmt->bindValue(':id', $request['idQuestion'], PDO::PARAM_STR);
            $stmt->bindValue(':nom', $request['nomQuestion'], PDO::PARAM_STR);
            
            // Exécute la déclaration SQL
            $stmt->execute();
        }
    }

    /**
     * Méthode de suppression suppression()
     * 
     * Supprime une question de la base de données
     * 
     * @param $request
     */
    public function suppression($request) { 
        if ($request instanceof Question) {
            $dbc = BDD_Externe_Connexion::getInstance()->getConnexion();
            $idQuestion = $request['idQuestion'];

            // Prépare la déclaration SQL
            $query = "DELETE FROM Question WHERE idQuestion = '" . $idQuestion . "'";
            $stmt = $dbc->prepare($query);

            // Exécute la déclaration SQL
            $stmt->execute();
        }
    }

     /**
     * Méthode de modification suppression()
     * 
     * Modifie les données d'une question
     * 
     * @param $request
     */
    public function modification($request) {
        if ($request instanceof Question) {
            $dbc = BDD_Externe_Connexion::getInstance()->getConnexion();
            $idQuestion = $request['idQuestion'];

            // Prépare la déclaration SQL
            $query = "UPDATE Question SET idQuestion=:id, nomQuestion=:nom WHERE idQuestion = '" . $idQuestion . "'";
            $stmt = $dbc->prepare($query);
        
            // Lie les paramètres
            $stmt->bindValue(':id', $request['idQuestion'], PDO::PARAM_STR);
            $stmt->bindValue(':nom', $request['nomQuestion'], PDO::PARAM_STR);
          
            // Exécute la déclaration SQL
            $stmt->execute();
        } 
    }
}

?>
