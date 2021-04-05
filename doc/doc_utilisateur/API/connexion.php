<?php 
    header('Access-Control-Allow-Origin: *');
    header('Content-Type: application/json');

    include_once("BDD_Externe_Connexion.php");
    include("objets/Connexion.php");

    $status = 'ECHOUEE';
    
    if (isset($_POST["nomUtilisateur"]) && isset($_POST["mdp"])) {

        // On établit un prefixe pour le salage
        $prefixe = $_POST["nomUtilisateur"];

        // On établit un suffixe pour le salage
        $suffixe = "";
        for ($i = strlen($_POST["nomUtilisateur"]) - 1; $i >= 0; $i--) {
            $suffixe = $suffixe . $_POST["nomUtilisateur"][$i];
        }

        // hachage du nom d'utilisateur
        $nomUtilisateur = hash("sha256", $_POST["nomUtilisateur"]);

        // Hachage du mot de passe déjà salé
        $mdp = hash("sha256", $prefixe . $_POST["mdp"] . $suffixe);

        // On récupère le mdp de la bdd dont le nom d'utilisateur est le hash du nom d'utilisateur saisi
        // (le mot de passe n'existe pas si la saisie n'est pas le bon nom d'utilisateur)
        $dbc = BDD_Externe_Connexion::getInstance()->getConnexion();
        $query = "SELECT * FROM connexion WHERE login = '" . $nomUtilisateur . "'";
        $stmt = $dbc->query($query);
        $results = $stmt->fetchALL(PDO::FETCH_CLASS, 'Connexion');
        if (count($results) > 0) {
            $mdpBDD = $results[0]->__get("mdp");
        } else {
            $mdpBDD = "";
        }

        // Vérification de la validité du nom d'utilisateur et du mot de passe
        if ($mdp == $mdpBDD) {
            $status = 'REUSSIE';
        }
    }

    echo json_encode(array("connexion"=>$status));
?>