package com.example.rosetta.model;

/**
 * Cette classe permet de représenter un Composant.
 *
 * @author Christophe
 * @version 1.0
 */
public class Composant {

    /**
     * Le numéro d'identification du composant.
     */
    private int idComposant;

    /**
     * Le nom du composant.
     */
    private String nomComposant;

    /**
     * L'unité du composant.
     */
    private String uniteComposant;

    /**
     * Le prix du composant.
     */
    private double prixComposant;

    /**
     * Initialise les données du nouveau composant créé.
     *
     * @param id    le numéro d'identification du composant
     * @param nom   le nom du composant
     * @param unite l'unité du composant
     * @param prix  le prix du composant
     */
    public Composant(int id, String nom, String unite, double prix) {
        this.setIdComposant(id);
        this.setNomComposant(nom);
        this.setUniteComposant(unite);
        this.setPrixComposant(prix);
    }

    /**
     * Permet d'accéder au numéro d'identification du composant.
     *
     * @return le numéro d'identification du composant
     */
    public int getIdComposant() {
        return this.idComposant;
    }

    /**
     * Renseigne le numéro d'identification du composant.
     *
     * @param id le numéro d'identification du composant
     */
    public void setIdComposant(int id) {
        this.idComposant = id;
    }

    /**
     * Permet d'accéder au nom du composant.
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
     * Permet d'accéder à l'unité du composant.
     *
     * @return l'unité du composant
     */
    public String getUniteComposant() {
        return this.uniteComposant;
    }

    /**
     * Renseigne l'unité du composant.
     *
     * @param unite l'unité du composant
     */
    public void setUniteComposant(String unite) {
        this.uniteComposant = unite;
    }

    /**
     * Permet d'accéder au prix du composant.
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
     * Représente textuellement les données du composant.
     * @return une chaine de caractères
     */
    public String toString() {
        String composant = "COMPOSANT : " + this.idComposant;
        String nom = "\n\tNom : " + this.nomComposant;
        String unite = "\n\tUnite : " + this.uniteComposant;
        String prix = "\n\tPrix : " + this.prixComposant;

        return composant + nom + unite + prix;
    }

}