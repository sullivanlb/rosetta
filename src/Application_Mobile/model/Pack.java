package model;

import java.util.ArrayList;

/**
 * Cette classe permet de repr�senter un Pack de composants.
 * 
 * @author Christophe
 * @version 1.0
 */
public class Pack {

	/*
	 * Le num�ro d'identification du devis.
	 */
	private int idPack;

	/**
	 * Le nom du pack.
	 */
	private String nomPack;

	/**
	 * Liste de tous les composants du pack.
	 */
	private ArrayList<Composant> lesComposants;

	/**
	 * Initialise les donn�es du nouveau pack cr��.
	 * 
	 * @param id  le num�ro d'identification du pack
	 * @param nom le nom du pack
	 */
	public Pack(int id, String nom) {
		this.setIdPack(id);
		this.setNomPack(nom);
		this.lesComposants = new ArrayList<Composant>();
	}

	/**
	 * Permet d'acc�der au num�ro d'identification du pack.
	 * 
	 * @return le num�ro d'identification du pack
	 */
	public int getIdPack() {
		return this.idPack;
	}

	/**
	 * Renseigne le num�ro d'identification du pack.
	 * 
	 * @param id le num�ro d'identification du pack
	 */
	public void setIdPack(int id) {
		this.idPack = id;
	}

	/**
	 * Permet d'acc�der au nom du pack.
	 * 
	 * @return le nom du pack
	 */
	public String getNomPack() {
		return this.nomPack;
	}

	/**
	 * Renseigne le nom du pack.
	 * 
	 * @param nom le nom du pack
	 */
	public void setNomPack(String nom) {
		this.nomPack = nom;
	}

	/**
	 * Permet d'acc�der � la liste des composants du pack.
	 * 
	 * @return la liste des composants du pack
	 */
	public ArrayList<Composant> getComposants() {
		return this.lesComposants;
	}

	/**
	 * Ajoute un composant � la liste des composants du pack.
	 * 
	 * @param nouveauComposant le nouveau composant du pack
	 */
	public void ajouterComposant(Composant nouveauComposant) {
		this.lesComposants.add(nouveauComposant);
	}

	/**
	 * Repr�sente textuellement les donn�es du pack.
	 */
	public String toString() {
		String pack = "PACK : " + this.idPack;
		String nom = "\n\tNom : " + this.nomPack;
		String composants = "\n\tLes composants : \n---------------";
		for (Composant c : this.lesComposants)
			composants += c.toString() + "\n\n";
		composants += "\n---------------";

		return pack + nom + composants;
	}

}