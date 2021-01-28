<?php

class Utilisateur {

	private $email;
	private $nom;
	private $prenom;
	private $sexe;
	private $taille;
	private $poids;
	private $motDePasse;
	private $dateNaissance;
	private $uneActivite;

	public function __construct (){	}

	public function init ($e, $n, $pre, $sexe, $poi, $motDePasse, $date, $tai){
		$this->email = $e;
		$this->nom = $n;
		$this->prenom = $pre;
		$this->sexe = $sexe;
		$this->taille = $tai;
		$this->poids = $poi;
		$this->motDePasse = $motDePasse;
		$this->dateNaissance = $date;
	}

	public function getNom(){ return $this->nom; }
	public function getEmail(){ return $this->email; }
	public function getPrenom(){ return $this->prenom; }
	public function getSexe(){ return $this->sexe; }
	public function getTaille(){ return $this->taille; }
	public function getPoids(){ return $this->poids; }
	public function getMotDePasse(){ return $this->motDePasse; }
	public function getDateNaissance(){ return $this->dateNaissance; }
	public function getUneActivite(){ return $this->uneActivite; }

	public function setNom($nom){ $this->nom = $nom; }
	public function setEmail($e){ $this->email = $e; }
	public function setPrenom($p){ $this->prenom = $p; }
	public function setSexe($sexe){ $this->sexe = $sexe; }
	public function setTaille($taille){ $this->taille = $taille; }
	public function setPoids($poids){ $this->poids = $poids; }
	public function setMotDePasse($mdp){ $this->motDePasse = $mdp; }
	public function setDateNaissance($dateN){ $this->dateNaissance = $dateN; }
	public function setUneActivite($uneA){ $this->uneActivite = $uneA; }
	
	public function __toString() {
		return "<br/>Nom : ". $this->nom. " | Prenom : ". $this->prenom. "<br/>".
		       "Sexe : ". $this->sexe. " | Taille : ". $this->taille. " | Poids : ". $this->poids. "<br/>".
		       "Date de naissance ". $this->dateNaissance. "<br/>".
		       "Email : ". $this->email. " | Mot de passe : ". $this->motDePasse;
	}

}

?>
