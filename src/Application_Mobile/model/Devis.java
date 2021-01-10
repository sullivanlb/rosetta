package model;

import java.util.ArrayList;

/**
 * Cette classe permet de repr�senter un Devis.
 * 
 * @author Christophe
 * @version 1.0
 */
public class Devis {

	/*
	 * Le num�ro d'identification du devis.
	 */
	private int idDevis;

	/**
	 * Le nom du devis.
	 */
	private String nomDevis;

	/**
	 * Liste de tous les packs du devis.
	 */
	private ArrayList<Pack> lesPacks;

	/**
	 * Liste de tous les composants du devis.
	 */
	private ArrayList<Composant> lesComposants;

	/**
	 * Initialise les donn�es du nouveau devis cr��.
	 * 
	 * @param id  le num�ro d'identification du cdevis
	 * @param nom le nom du devis
	 */
	public Devis(int id, String nom) {
		this.setIdDevis(id);
		this.setNomDevis(nom);
		this.lesPacks = new ArrayList<Pack>();
		this.lesComposants = new ArrayList<Composant>();
	}

	/**
	 * Permet d'acc�der au num�ro d'identification du devis.
	 * 
	 * @return le num�ro d'identification du devis
	 */
	public int getIdDevis() {
		return this.idDevis;
	}

	/**
	 * Renseigne le num�ro d'identification du devis.
	 * 
	 * @param id le num�ro d'identification du devis
	 */
	public void setIdDevis(int id) {
		this.idDevis = id;
	}

	/**
	 * Permet d'acc�der au nom du devis.
	 * 
	 * @return le nom du devis
	 */
	public String getNomDevis() {
		return this.nomDevis;
	}

	/**
	 * Renseigne le nom du devis.
	 * 
	 * @param nom le nom du devis
	 */
	public void setNomDevis(String nom) {
		this.nomDevis = nom;
	}

	/**
	 * Permet d'acc�der � la liste des packs du devis.
	 * 
	 * @return la liste des packs du devis
	 */
	public ArrayList<Pack> getPacks() {
		return this.lesPacks;
	}

	/**
	 * Ajoute un pack � la liste des pack du devis.
	 * 
	 * @param nouveauPack le nouveau pack du devis
	 */
	public void ajouterPack(Pack nouveauPack) {
		this.lesPacks.add(nouveauPack);
	}

	/**
	 * Permet d'acc�der � la liste des composants du devis.
	 * 
	 * @return la liste des composants du devis
	 */
	public ArrayList<Composant> getComposants() {
		return this.lesComposants;
	}

	/**
	 * Ajoute un composant � la liste des composants du devis.
	 * 
	 * @param nouveauComposant le nouveau composant du devis
	 */
	public void ajouterComposant(Composant nouveauComposant) {
		this.lesComposants.add(nouveauComposant);
	}

	/**
	 * Repr�sente textuellement les donn�es du devis.
	 */
	public String toString() {
		String devis = "DEVIS : " + this.idDevis;
		String nom = "\n\tNom : " + this.nomDevis;
		String packs = "\n\tLes packs : \n---------------";
		for (Pack p : this.lesPacks)
			packs += p.toString() + "\n\n";
		packs += "\n---------------";
		String composants = "\n\tLes composants : \n---------------";
		for (Composant c : this.lesComposants)
			composants += c.toString() + "\n\n";
		composants += "\n---------------";

		return devis + nom + packs + composants;
	}

}