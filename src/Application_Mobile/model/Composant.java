package model;

/**
 * Cette classe permet de repr�senter un Composant.
 * 
 * @author Christophe
 * @version 1.0
 */
public class Composant {

	/**
	 * Le num�ro d'identification du composant.
	 */
	private int idComposant;

	/**
	 * Le nom du composant.
	 */
	private String nomComposant;

	/**
	 * L'unit� du composant.
	 */
	private String uniteComposant;

	/**
	 * Le prix du composant.
	 */
	private double prixComposant;

	/**
	 * Initialise les donn�es du nouveau composant cr��.
	 * 
	 * @param id    le num�ro d'identification du composant
	 * @param nom   le nom du composant
	 * @param unite l'unit� du composant
	 * @param prix  le prix du composant
	 */
	public Composant(int id, String nom, String unite, double prix) {
		this.setIdComposant(id);
		this.setNomComposant(nom);
		this.setUniteComposant(unite);
		this.setPrixComposant(prix);
	}

	/**
	 * Permet d'acc�der au num�ro d'identification du composant.
	 * 
	 * @return le num�ro d'identification du composant
	 */
	public int getIdComposant() {
		return this.idComposant;
	}

	/**
	 * Renseigne le num�ro d'identification du composant.
	 * 
	 * @param id le num�ro d'identification du composant
	 */
	public void setIdComposant(int id) {
		this.idComposant = id;
	}

	/**
	 * Permet d'acc�der au nom du composant.
	 * 
	 * @return le nom du composant
	 */
	public String getNomComposant() {
		return this.nomComposant;
	}

	/**
	 * Renseigne le nom du composant.
	 * 
	 * @param nom le nom du composant
	 */
	public void setNomComposant(String nom) {
		this.nomComposant = nom;
	}

	/**
	 * Permet d'acc�der � l'unit� du composant.
	 * 
	 * @return l'unit� du composant
	 */
	public String getUniteComposant() {
		return this.uniteComposant;
	}

	/**
	 * Renseigne l'unit� du composant.
	 * 
	 * @param unite l'unit� du composant
	 */
	public void setUniteComposant(String unite) {
		this.uniteComposant = unite;
	}

	/**
	 * Permet d'acc�der au prix du composant.
	 * 
	 * @return le prix du composant
	 */
	public double getPrixComposant() {
		return this.prixComposant;
	}

	/**
	 * Renseigne le prix du composant.
	 * 
	 * @param prix le prix du composant
	 */
	public void setPrixComposant(double prix) {
		this.prixComposant = prix;
	}

	/**
	 * Repr�sente textuellement les donn�es du composant.
	 */
	public String toString() {
		String composant = "COMPOSANT : " + this.idComposant;
		String nom = "\n\tNom : " + this.nomComposant;
		String unite = "\n\tUnite : " + this.uniteComposant;
		String prix = "\n\tPrix : " + this.prixComposant;

		return composant + nom + unite + prix;
	}

}