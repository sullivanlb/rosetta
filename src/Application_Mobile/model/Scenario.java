package model;

import java.util.ArrayList;

/**
 * Cette classe permet de représenter un Scénario.
 * 
 * @author Christophe
 * @version 1.0
 */
public class Scenario {

	/*
	 * Le numéro d'identification du scénario.
	 */
	private int idScenario;

	/*
	 * Le nom du scénario.
	 */
	private String nomScenario;

	/**
	 * Liste de tous les packs du scénario.
	 */
	private ArrayList<Pack> lesPacks;

	/**
	 * Liste de tous les composants du pack.
	 */
	private ArrayList<Composant> lesComposants;

	/**
	 * Liste de toutes les questions du scénario.
	 */
	private ArrayList<Question> lesQuestions;

	/**
	 * Initialise les données du nouveau scénario créé.
	 * 
	 * @param id
	 * @param nom
	 */
	public Scenario(int id, String nom) {
		this.setIdScenario(id);
		this.setNomScenario(nom);
		this.lesPacks = new ArrayList<Pack>();
		this.lesComposants = new ArrayList<Composant>();
		this.lesQuestions = new ArrayList<Question>();
	}

	/**
	 * Permet d'accéder au numéro d'identification du scénario.
	 * 
	 * @return le numéro d'identification du scénario
	 */
	public int getIdScenario() {
		return this.idScenario;
	}

	/**
	 * Renseigne le numéro d'identification du scénario.
	 * 
	 * @param id le numéro d'identification du scénario
	 */
	public void setIdScenario(int id) {
		this.idScenario = id;
	}

	/**
	 * Permet d'accéder au nom du scénario.
	 * 
	 * @return le nom du scénario
	 */
	public String getNomScenario() {
		return this.nomScenario;
	}

	/**
	 * Renseigne le nom du scénario.
	 * 
	 * @param nom le nom du scénario
	 */
	public void setNomScenario(String nom) {
		this.nomScenario = nom;
	}

	/**
	 * Permet d'accéder à la liste des packs du scénario.
	 * 
	 * @return la liste des packs du scénario
	 */
	public ArrayList<Pack> getPacks() {
		return this.lesPacks;
	}

	/**
	 * Ajoute un pack à la liste des packs du scénario.
	 * 
	 * @param nouveauPack le nouveau pack du scénario
	 */
	public void ajouterPack(Pack nouveauPack) {
		this.lesPacks.add(nouveauPack);
	}

	/**
	 * Permet d'accéder à la liste des composants du scénario.
	 * 
	 * @return la liste des composants du scénario
	 */
	public ArrayList<Composant> getComposants() {
		return this.lesComposants;
	}

	/**
	 * Ajoute un composant à la liste des composants du scénario.
	 * 
	 * @param nouveauComposant le nouveau composant du scénario
	 */
	public void ajouterComposant(Composant nouveauComposant) {
		this.lesComposants.add(nouveauComposant);
	}

	/**
	 * Permet d'accéder à la liste des questions du scénario.
	 * 
	 * @return la liste des questions du scénario
	 */
	public ArrayList<Question> getQuestions() {
		return this.lesQuestions;
	}

	/**
	 * Ajoute une question à la liste des questions du scénario.
	 * 
	 * @param nouvelleQuestion la nouvelle question du scénario
	 */
	public void ajouterQuestion(Question nouvelleQuestion) {
		this.lesQuestions.add(nouvelleQuestion);
	}

	/**
	 * Représente textuellement les données du scénario.
	 */
	public String toString() {
		String scenario = "SCENARIO : " + this.idScenario;
		String nom = "\n\tNom : " + this.nomScenario;
		String packs = "\n\tLes packs : \n---------------";
		for (Pack p : this.lesPacks)
			packs += p.toString() + "\n\n";
		packs += "\n---------------";
		String composants = "\n\tLes composants : \n---------------";
		for (Composant c : this.lesComposants)
			composants += c.toString() + "\n\n";
		composants += "\n---------------";
		String questions = "\n\tLes questions : \n---------------";
		for (Question q : this.lesQuestions)
			composants += q.toString() + "\n\n";
		composants += "\n---------------";

		return scenario + nom + packs + composants + questions;
	}

}