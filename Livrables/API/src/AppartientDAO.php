<?php

require_once('BDD_Externe_Connexion.php');

/** 
 * AppartientDAO est une classe de gestion de Composant.
 * 
 * AppartientDAO est une classe permettant de :
 * - Retourner toutes les liaisons correspondantes à une table,
 * - De retourner une liaison suivant un de ses éléments,
 * - D'insérer une nouvelle liaison,
 * - De supprimer une liaison,
 * - De modifier les données d'une liaison.
 * Une seule instance de cette classe est possible.
 * 
 * @author Garcia Christophe
 * @version $Revision: 1.2 $
 * @access public
 */
class AppartientDAO {

    /**
     * Objet d'accès aux données
     * 
     * @access private
     */
    private static $dao;

    /**
     * Méthode magique __construct()
     * 
     * Permet de créer le AppartientDAO
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
            self::$dao = new AppartientDAO();
        }
        return self::$dao;
    }

    /**
     * Méthode d'accès à l'ensemble des composants tousLesElements()
     * 
     * Retourne toutes les liaisons de la table correspondante
     * 
     * @param $initiales
     */
    public final function tousLesElements($initiales) {
        $dbc = BDD_Externe_Connexion::getInstance()->getConnexion();
        if (strtoupper($initiales) === 'CD') {
            $query = "SELECT * FROM AppartientCD";
        } else if (strtoupper($initiales) === 'DP') {
            $query = "SELECT * FROM AppartientDP";
        } else if (strtoupper($initiales) === 'DC') {
            $query = "SELECT * FROM AppartientDC";
        } else if (strtoupper($initiales) === 'PC') {
            $query = "SELECT * FROM AppartientPC";
        } else if (strtoupper($initiales) === 'SP') {
            $query = "SELECT * FROM AppartientSP";
        } else if (strtoupper($initiales) === 'SC') {
            $query = "SELECT * FROM AppartientSC";
        } else if (strtoupper($initiales) === 'SQ') {
            $query = "SELECT * FROM AppartientSQ";
        } else {
            throw new Exception('AppartientDAO: tousLesElements(): initiales inconnues.');
        }
        $stmt = $dbc->query($query);
        $results = $stmt->fetchALL(PDO::FETCH_CLASS, 'Appartient');
        return $results;
    }

    /**
     * Méthode d'accès à des liaisons précises unElement()
     * 
     * Retourne les liaisons dont un élément clé est passé en paramètre
     * 
     * @param $cle
     * @param $initiales
     */
    public final function unElement($cle, $initiales) {
        $dbc = BDD_Externe_Connexion::getInstance()->getConnexion();
        if (strtoupper($initiales) === 'CD') {
            if ($cle instanceof Client) {
                $query = "SELECT * FROM AppartientCD WHERE unClient = '" . $cle . "'";
            } else if ($cle instanceof Devis) {
                $query = "SELECT * FROM AppartientCD WHERE unDevis = '" . $cle . "'";
            } else {
                throw new Exception('AppartientDAO: unElement(): parametre != Client et != Devis.');
            }
        } else if (strtoupper($initiales) === 'DP') {
            if ($cle instanceof Devis) {
                $query = "SELECT * FROM AppartientDP WHERE unDevis = '" . $cle . "'";
            } else if ($cle instanceof Pack) {
                $query = "SELECT * FROM AppartientDP WHERE unPack = '" . $cle . "'";
            } else {
                throw new Exception('AppartientDAO: unElement(): parametre != Devis et != Pack.');
            }
        } else if (strtoupper($initiales) === 'DC') {
            if ($cle instanceof Devis) {
                $query = "SELECT * FROM AppartientDC WHERE unDevis = '" . $cle . "'";
            } else if ($cle instanceof Composant) {
                $query = "SELECT * FROM AppartientDC WHERE unComposant = '" . $cle . "'";
            } else {
                throw new Exception('AppartientDAO: unElement(): parametre != Devis et != Composant.');
            }
        } else if (strtoupper($initiales) === 'PC') {
            if ($cle instanceof Pack) {
                $query = "SELECT * FROM AppartientPC WHERE unPack = '" . $cle . "'";
            } else if ($cle instanceof Composant) {
                $query = "SELECT * FROM AppartientPC WHERE unComposant = '" . $cle . "'";
            } else {
                throw new Exception('AppartientDAO: unElement(): parametre != Pack et != Composant.');
            }
        } else if (strtoupper($initiales) === 'SP') {
            if ($cle instanceof Scenario) {
                $query = "SELECT * FROM AppartientSP WHERE unScenario = '" . $cle . "'";
            } else if ($cle instanceof Pack) {
                $query = "SELECT * FROM AppartientSP WHERE unPack = '" . $cle . "'";
            } else {
                throw new Exception('AppartientDAO: unElement(): parametre != Scenario et != Pack.');
            }
        } else if (strtoupper($initiales) === 'SC') {
            if ($cle instanceof Scenario) {
                $query = "SELECT * FROM AppartientSC WHERE unScenario = '" . $cle . "'";
            } else if ($cle instanceof Composant) {
                $query = "SELECT * FROM AppartientSC WHERE unComposant = '" . $cle . "'";
            } else {
                throw new Exception('AppartientDAO: unElement(): parametre != Scenario et != Composant.');
            }
        } else if (strtoupper($initiales) === 'SQ') {
            if ($cle instanceof Scenario) {
                $query = "SELECT * FROM AppartientSQ WHERE unScenario = '" . $cle . "'";
            } else if ($cle instanceof Question) {
                $query = "SELECT * FROM AppartientSQ WHERE uneQuestion = '" . $cle . "'";
            } else {
                throw new Exception('AppartientDAO: unElement(): parametre != Scenario et != Question.');
            }
        } else {
            throw new Exception('AppartientDAO: tousLesElements(): initiales inconnues.');
        }
        $stmt = $dbc->query($query);
        $results = $stmt->fetchALL(PDO::FETCH_CLASS, 'Client'); // fetchAll ? fetch ? .toArray() ? jspppp : tests.
        return $results;
    }

    /**
     * Méthode d'insertion insertion()
     * 
     * Insère une nouvelle liaison dans la base de données
     * 
     * @param $appartient
     * @param $initiales
     */
    public final function insertion($appartient, $initiales) {
        if ($appartient instanceof Appartient) {
            $dbc = BDD_Externe_Connexion::getInstance()->getConnexion();

            // Prépare la déclaration SQL
            if (strtoupper($initiales) === 'CD') {
                $query = "INSERT INTO AppartientCD(unClient, unDevis) VALUES (:unClient, :unDevis)";

                // Lie les paramètres
                $stmt->bindValue(':unClient', $appartient->__get("contenant"), PDO::PARAM_STR);
                $stmt->bindValue(':unDevis', $appartient->__get("contenu"), PDO::PARAM_STR);
            } else if (strtoupper($initiales) === 'DP') {
                $query = "INSERT INTO AppartientDP(unDevis, unPack, quantite) VALUES (:unDevis, :unPack, :quantite)";

                // Lie les paramètres
                $stmt->bindValue(':unDevis', $appartient->__get("contenant"), PDO::PARAM_STR);
                $stmt->bindValue(':unPack', $appartient->__get("contenu"), PDO::PARAM_STR);
                $stmt->bindValue(':quantite', $appartient->__get("quantite"), PDO::PARAM_STR);
            } else if (strtoupper($initiales) === 'DC') {
                $query = "INSERT INTO AppartientDC(unDevis, unComposant, quantite) VALUES (:unDevis, :unComposant, :quantite)";

                // Lie les paramètres
                $stmt->bindValue(':unDevis', $appartient->__get("contenant"), PDO::PARAM_STR);
                $stmt->bindValue(':unComposant', $appartient->__get("contenu"), PDO::PARAM_STR);
                $stmt->bindValue(':quantite', $appartient->__get("quantite"), PDO::PARAM_STR);
            } else if (strtoupper($initiales) === 'PC') {
                $query = "INSERT INTO AppartientDP(unPack, unComposant, quantite) VALUES (:unPack, :unComposant, :quantite)";

                // Lie les paramètres
                $stmt->bindValue(':unPack', $appartient->__get("contenant"), PDO::PARAM_STR);
                $stmt->bindValue(':unComposant', $appartient->__get("contenu"), PDO::PARAM_STR);
                $stmt->bindValue(':quantite', $appartient->__get("quantite"), PDO::PARAM_STR);
            } else if (strtoupper($initiales) === 'SP') {
                $query = "INSERT INTO AppartientSP(unScenario, unPack, quantite) VALUES (:unScenario, :unPack, :quantite)";

                // Lie les paramètres
                $stmt->bindValue(':unScenario', $appartient->__get("contenant"), PDO::PARAM_STR);
                $stmt->bindValue(':unPack', $appartient->__get("contenu"), PDO::PARAM_STR);
                $stmt->bindValue(':quantite', $appartient->__get("quantite"), PDO::PARAM_STR);
            } else if (strtoupper($initiales) === 'SC') {
                $query = "INSERT INTO AppartientDP(unScenario, unComposant, quantite) VALUES (:unScenario, :unComposant, :quantite)";

                // Lie les paramètres
                $stmt->bindValue(':unScenario', $appartient->__get("contenant"), PDO::PARAM_STR);
                $stmt->bindValue(':unComposant', $appartient->__get("contenu"), PDO::PARAM_STR);
                $stmt->bindValue(':quantite', $appartient->__get("quantite"), PDO::PARAM_STR);
            } else if (strtoupper($initiales) === 'SQ') {
                $query = "INSERT INTO AppartientSQ(unScenario, uneQuestion) VALUES (:unScenario, :uneQuestion)";

                // Lie les paramètres
                $stmt->bindValue(':unScenario', $appartient->__get("contenant"), PDO::PARAM_STR);
                $stmt->bindValue(':uneQuestion', $appartient->__get("contenu"), PDO::PARAM_STR);
            } else {
                throw new Exception('AppartientDAO: insertion(): initiales inconnues.');
            }
            $stmt = $dbc->prepare($query);
            
            // Exécute la déclaration SQL
            $stmt->execute();
        } else {
            throw new Exception('AppartientDAO: insertion(): n est pas une instance de Appartient.');
        }
    }

    /**
     * Méthode de suppression suppression()
     * 
     * Supprime une liaison de la table correspondante de la base de données
     * 
     * @param $appartient
     * @param $initiales
     */
    public function suppression($appartient, $initiles) { 
        if ($appartient instanceof Appartient) {
            $dbc = BDD_Externe_Connexion::getInstance()->getConnexion();
            $contenant = $appartient->__get("contenant");
            $contenu = $appartient->__get("contenu");
            $quantite = $appartient->__get("quantite");

            // Prépare la déclaration SQL
            if (strtoupper($initiales) === 'CD') {
                $query = "DELETE FROM AppartientCD WHERE unClient = '" . $contenant . "' AND unDevis = '" . $contenu . "'";
            } else if (strtoupper($initiales) === 'DP') {
                $query = "DELETE FROM AppartientDP WHERE unDevis = '" . $contenant . "' AND unPack = '" . $contenu . "' AND quantite = '" . $quantite . "'";
            } else if (strtoupper($initiales) === 'DC') {
                $query = "DELETE FROM AppartientDC WHERE unDevis = '" . $contenant . "' AND unComposant = '" . $contenu . "' AND quantite = '" . $quantite . "'";
            } else if (strtoupper($initiales) === 'PC') {
                $query = "DELETE FROM AppartientPC WHERE unPack = '" . $contenant . "' AND unComposant = '" . $contenu . "' AND quantite = '" . $quantite . "'";
            } else if (strtoupper($initiales) === 'SP') {
                $query = "DELETE FROM AppartientSP WHERE unScenario = '" . $contenant . "' AND unPack = '" . $contenu . "' AND quantite = '" . $quantite . "'";
            } else if (strtoupper($initiales) === 'SC') {
                $query = "DELETE FROM AppartientSC WHERE unScenario = '" . $contenant . "' AND unComposant = '" . $contenu . "' AND quantite = '" . $quantite . "'";
            } else if (strtoupper($initiales) === 'SQ') {
                $query = "DELETE FROM AppartientSQ WHERE unScenario = '" . $contenant . "' AND uneQuestion = '" . $contenu . "'";
            } else {
                throw new Exception('AppartientDAO: suppression(): initiales inconnues.');
            }
            $stmt = $dbc->prepare($query);

            // Exécute la déclaration SQL
            $stmt->execute();
        } else {
            throw new Exception('AppartientDAO: suppression(): n est pas une instance de Appartient.');
        }
    }

    /**
     * Méthode de modification suppression()
     * 
     * Modifie les données d'une liaison
     * 
     * @param $appartient
     * @param $initiales
     */
    public function modification($appartient, $initiales) {
        if ($appartient instanceof Appartient) {
            $dbc = BDD_Externe_Connexion::getInstance()->getConnexion();
            $contenant = $appartient->__get("contenant");
            $contenu = $appartient->__get("contenu");
            $quantite = $appartient->__get("quantite");

            // Prépare la déclaration SQL
            if (strtoupper($initiales) === 'DP') {
                $query = "UPDATE AppartientDP SET quantite = '" . $quantite . "' WHERE unClient = '" . $contenant . "' AND unDevis = '" . $contenu . "'";
            } else if (strtoupper($initiales) === 'DC') {
                $query = "UPDATE AppartientDC SET quantite = '" . $quantite . "' WHERE unClient = '" . $contenant . "' AND unDevis = '" . $contenu . "'";
            } else if (strtoupper($initiales) === 'PC') {
                $query = "UPDATE AppartientPC SET quantite = '" . $quantite . "' WHERE unClient = '" . $contenant . "' AND unDevis = '" . $contenu . "'";
            } else if (strtoupper($initiales) === 'SP') {
                $query = "UPDATE AppartientSP SET quantite = '" . $quantite . "' WHERE unClient = '" . $contenant . "' AND unDevis = '" . $contenu . "'";
            } else if (strtoupper($initiales) === 'SC') {
                $query = "UPDATE AppartientSC SET quantite = '" . $quantite . "' WHERE unClient = '" . $contenant . "' AND unDevis = '" . $contenu . "'";
            } else {
                throw new Exception('AppartientDAO: modification(): initiales inconnues.');
            }
            $stmt = $dbc->prepare($query);
          
            // Exécute la déclaration SQL
            $stmt->execute();
        } else {
            throw new Exception('AppartientDAO: modification(): n est pas une instance de Appartient.');
        }
    }
}

?>
