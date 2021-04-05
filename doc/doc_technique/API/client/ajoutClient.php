<?php 
    header('Access-Control-Allow-Origin: *');
    header('Content-Type: application/json');

    include_once("../BDD_Externe_Connexion.php");

    include("../objets/Client.php");
    include("../dao/ClientDAO.php");
    
    $connexion = new BDD_Externe_Connexion();
    $status = 'FAILED';
    
    // Ajout d'un nouveau client dans la base de données depuis le formulaire du site
    if (isset($_POST["nom"]) && isset($_POST["prenom"]) && isset($_POST["adresse"]) && isset($_POST["email"]) && isset($_POST["tel"]) && isset($_POST["sexe"])) {
        $nomNouveauClient = $_POST["nom"];
        $prenomNouveauClient = $_POST["prenom"];
        $adresseNouveauClient = $_POST["adresse"];
        $emailNouveauClient = $_POST["email"];
        $telNouveauClient = $_POST["tel"];
        $sexeNouveauClient = $_POST["sexe"];
    
        $daoClient = new ClientDAO();
        $client = new Client();
        $client->init(null, $nomNouveauClient, $prenomNouveauClient, $adresseNouveauClient, $emailNouveauClient, $telNouveauClient, $sexeNouveauClient);
        $daoClient->insertion($client);

        $status = 'OK';
    }

    echo json_encode(array("response"=>$status));
?>