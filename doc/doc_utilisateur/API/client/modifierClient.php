<?php 
	header('Access-Control-Allow-Origin: *');
	header('Content-Type: application/json');

	include_once("../BDD_Externe_Connexion.php");

	include("../objets/Client.php");
	include("../dao/ClientDAO.php");
	
	$connexion = new BDD_Externe_Connexion();


	if (isset($_POST["id"]) && isset($_POST["nom"]) && isset($_POST["prenom"]) && isset($_POST["adresse"]) && isset($_POST["email"]) && isset($_POST["tel"]) && isset($_POST["sexe"])) {
		$idClient = $_POST["id"];
		$nomClient = $_POST["nom"];
		$prenomClient = $_POST["prenom"];
		$adresseClient = $_POST["adresse"];
		$emailClient = $_POST["email"];
		$telClient = $_POST["tel"];
		$sexeClient = $_POST["sexe"];

		$daoClient = new ClientDAO();
		$client = new Client();
		$client->init($idClient, $nomClient, $prenomClient, $adresseClient, $emailClient, $telClient, $sexeClient);

		$daoClient->modification($client);
	}
?>