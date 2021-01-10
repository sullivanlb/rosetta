package model;

/**
 * Cette classe permet de représenter une Question à ne pas oublier.
 * 
 * @author Christophe
 * @version 1.0
 */
public class Question {

	/*
	 * Le numéro d'identification de la question.
	 */
	private int idQuestion;

	/**
	 * Le nom de la question.
	 */
	private String nomQuestion;

	/**
	 * Initialise les données de la nouvelle question créée.
	 * 
	 * @param id  le numéro d'identification de la question
	 * @param nom le nom de la question
	 */
	public Question(int id, String nom) {
		this.setIdQuestion(id);
		this.setNomQuestion(nom);
	}

	/**
	 * Permet d'accéder au numéro d'identification de la question.
	 * 
	 * @return le numéro d'identification de la question
	 */
	public int getIdQuestion() {
		return this.idQuestion;
	}

	/**
	 * Renseigne le numéro d'identification de la question.
	 * 
	 * @param id le numéro d'identification de la question
	 */
	public void setIdQuestion(int id) {
		this.idQuestion = id;
	}

	/**
	 * Permet d'accéder au nom de la questionk.
	 * 
	 * @return le nom de la question
	 */
	public String getNomQuestion() {
		return this.nomQuestion;
	}

	/**
	 * Renseigne le nom de la question.
	 * 
	 * @param nom le nom de la question
	 */
	public void setNomQuestion(String nom) {
		this.nomQuestion = nom;
	}

	/**
	 * Représente textuellement les données de la question.
	 */
	public String toString() {
		String question = "QUESTION : " + this.idQuestion;
		String nom = "\n\tNom : " + this.nomQuestion;

		return question + nom;
	}

}