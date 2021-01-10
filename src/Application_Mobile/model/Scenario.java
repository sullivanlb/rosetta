package model;

import java.util.ArrayList;

/**
 * Cette classe permet de repr�senter un Sc�nario.
 * 
 * @author Christophe
 * @version 1.0
 */
public class Scenario {

	/*
	 * Le num�ro d'identification du sc�nario.
	 */
	private int idScenario;

	/*
	 * Le nom du sc�nario.
	 */
	private String nomScenario;

	/**
	 * Liste de tous les packs du sc�nario.
	 */
	private ArrayList<Pack> lesPacks;

	/**
	 * Liste de tous les composants du pack.
	 */
	private ArrayList<Composant> lesComposants;

	/**
	 * Liste de toutes les questions du sc�nario.
	 */
	private ArrayList<Question> lesQuestions;

	/**
	 * Initialise les donn�es du nouveau sc�nario cr��.
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
	 * Permet d'acc�der au num�ro d'identification du sc�nario.
	 * 
	 * @return le num�ro d'identification du sc�nario
	 */
	public int getIdScenario() {
		return this.idScenario;
	}

	/**
	 * Renseigne le num�ro d'identification du sc�nario.
	 * 
	 * @param id le num�ro d'identification du sc�nario
	 */
	public void setIdScenario(int id) {
		this.idScenario = id;
	}

	/**
	 * Permet d'acc�der au nom du sc�nario.
	 * 
	 * @return le nom du sc�nario
	 */
	public String getNomScenario() {
		return this.nomScenario;
	}

	/**
	 * Renseigne le nom du sc�nario.
	 * 
	 * @param nom le nom du sc�nario
	 */
	public void setNomScenario(String nom) {
		this.nomScenario = nom;
	}

	/**
	 * Permet d'acc�der � la liste des packs du sc�nario.
	 * 
	 * @return la liste des packs du sc�nario
	 */
	public ArrayList<Pack> getPacks() {
		return this.lesPacks;
	}

	/**
	 * Ajoute un pack � la liste des packs du sc�nario.
	 * 
	 * @param nouveauPack le nouveau pack du sc�nario
	 */
	public void ajouterPack(Pack nouveauPack) {
		this.lesPacks.add(nouveauPack);
	}

	/**
	 * Permet d'acc�der � la liste des composants du sc�nario.
	 * 
	 * @return la liste des composants du sc�nario
	 */
	public ArrayList<Composant> getComposants() {
		return this.lesComposants;
	}

	/**
	 * Ajoute un composant � la liste des composants du sc�nario.
	 * 
	 * @param nouveauComposant le nouveau composant du sc�nario
	 */
	public void ajouterComposant(Composant nouveauComposant) {
		this.lesComposants.add(nouveauComposant);
	}

	/**
	 * Permet d'acc�der � la liste des questions du sc�nario.
	 * 
	 * @return la liste des questions du sc�nario
	 */
	public ArrayList<Question> getQuestions() {
		return this.lesQuestions;
	}

	/**
	 * Ajoute une question � la liste des questions du sc�nario.
	 * 
	 * @param nouvelleQuestion la nouvelle question du sc�nario
	 */
	public void ajouterQuestion(Question nouvelleQuestion) {
		this.lesQuestions.add(nouvelleQuestion);
	}

	/**
	 * Repr�sente textuellement les donn�es du sc�nario.
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