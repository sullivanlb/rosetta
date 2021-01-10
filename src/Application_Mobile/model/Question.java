package model;

/**
 * Cette classe permet de repr�senter une Question � ne pas oublier.
 * 
 * @author Christophe
 * @version 1.0
 */
public class Question {

	/*
	 * Le num�ro d'identification de la question.
	 */
	private int idQuestion;

	/**
	 * Le nom de la question.
	 */
	private String nomQuestion;

	/**
	 * Initialise les donn�es de la nouvelle question cr��e.
	 * 
	 * @param id  le num�ro d'identification de la question
	 * @param nom le nom de la question
	 */
	public Question(int id, String nom) {
		this.setIdQuestion(id);
		this.setNomQuestion(nom);
	}

	/**
	 * Permet d'acc�der au num�ro d'identification de la question.
	 * 
	 * @return le num�ro d'identification de la question
	 */
	public int getIdQuestion() {
		return this.idQuestion;
	}

	/**
	 * Renseigne le num�ro d'identification de la question.
	 * 
	 * @param id le num�ro d'identification de la question
	 */
	public void setIdQuestion(int id) {
		this.idQuestion = id;
	}

	/**
	 * Permet d'acc�der au nom de la questionk.
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
	 * Repr�sente textuellement les donn�es de la question.
	 */
	public String toString() {
		String question = "QUESTION : " + this.idQuestion;
		String nom = "\n\tNom : " + this.nomQuestion;

		return question + nom;
	}

}