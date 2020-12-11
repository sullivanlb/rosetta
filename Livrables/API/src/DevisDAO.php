<?php

require_once('BDD_Externe_Connexion.php');

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
        $query = "SELECT * FROM Devis";
        $stmt = $dbc->query($query);
        $results = $stmt->fetchALL(PDO::FETCH_CLASS, 'Devis');
        return $results;
    }

    /**
     * Méthode d'insertion insertion()
     * 
     * Insère un nouveau devis dans la base de données
     * 
     * @param $request
     */
    public final function insertion($request) {
        if ($request instanceof Devis) {
            $dbc = BDD_Externe_Connexion::getInstance()->getConnexion();

            // Prépare la déclaration SQL
            $query = "INSERT INTO Devis(idDevis, nomDevis) VALUES (:id, :nom)";
            $stmt = $dbc->prepare($query);

            // Lie les paramètres
            $stmt->bindValue(':id', $request['idDevis'], PDO::PARAM_STR);
            $stmt->bindValue(':nom', $request['nomDevis'], PDO::PARAM_STR);
            
            // Exécute la déclaration SQL
            $stmt->execute();
        }
    }

    /**
     * Méthode de suppression suppression()
     * 
     * Supprime un devis de la base de données
     * 
     * @param $request
     */
    public function suppression($request) { 
        if ($request instanceof Devis) {
            $dbc = BDD_Externe_Connexion::getInstance()->getConnexion();
            $idDevis = $request['idDevis'];

            // Prépare la déclaration SQL
            $query = "DELETE FROM Devis WHERE idDevis = '" . $idDevis . "'";
            $stmt = $dbc->prepare($query);

            // Exécute la déclaration SQL
            $stmt->execute();
        }
    }

     /**
     * Méthode de modification suppression()
     * 
     * Modifie les données d'un devis
     * 
     * @param $request
     */
    public function modification($request) {
        if ($request instanceof Devis) {
            $dbc = BDD_Externe_Connexion::getInstance()->getConnexion();
            $idDevis = $request['idDevis'];

            // Prépare la déclaration SQL
            $query = "UPDATE Devis SET idDevis=:id, nomDevis=:nom WHERE idDevis = '" . $idDevis . "'";
            $stmt = $dbc->prepare($query);
        
            // Lie les paramètres
            $stmt->bindValue(':id', $request['idDevis'], PDO::PARAM_STR);
            $stmt->bindValue(':nom', $request['nomDevis'], PDO::PARAM_STR);
          
            // Exécute la déclaration SQL
            $stmt->execute();
        } 
    }
}

?>
