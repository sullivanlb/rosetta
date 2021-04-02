package com.example.rosetta.model;

import java.util.ArrayList;

/**
 * Cette classe permet de représenter un Devis.
 *
 * @author Christophe
 * @version 1.0
 */
public class Devis {

    /**
     * Le numéro d'identification du devis.
     */
    private int idDevis;

    /**
     * Le nom du devis.
     */
    private String nomDevis;

    /**
     * La description d'un devis.
     */

    private String descriptionDevis;

    /**
     * Le duree des travaux.
     */

    private String dureeDevis;

    /**
     * La date de la création du devis.
     */

    private String dateEditionDevis;

    /**
     * La date de commencement des travaux.
     */

    private String dateTravauxDevis;

    /**
     * Liste de tous les packs du devis.
     */
    private ArrayList<Pack> lesPacks;

    /**
     * Liste de tous les composants du devis.
     */
    private ArrayList<Composant> lesComposants;

    /**
     * Initialise les données du nouveau devis créé.
     *
     * @param id  le numéro d'identification du cdevis
     * @param nom le nom du devis
     */
    public Devis(int id, String nom, String description, String duree, String dateEdition, String dateTravaux) {
        this.setIdDevis(id);
        this.setNomDevis(nom);
        this.setDescriptionDevis(description);
        this.setDureeDevis(duree);
        this.setDateEditionDevis(dateEdition);
        this.setDateTravauxDevis(dateTravaux);
        this.lesPacks = new ArrayList<Pack>();
        this.lesComposants = new ArrayList<Composant>();
    }

    /**
     * Permet d'accéder au numéro d'identification du devis.
     *
     * @return le numéro d'identification du devis
     */
    public int getIdDevis() {
        return this.idDevis;
    }

    /**
     * Renseigne le numéro d'identification du devis.
     *
     * @param id le numéro d'identification du devis
     */
    public void setIdDevis(int id) {
        this.idDevis = id;
    }

    /**
     * Permet d'accéder au nom du devis.
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
     * Permet d'accèsder à la description du devis.
     *
     * @return la description du Devis.
     */

    public String getDescriptionDevis() {
        return descriptionDevis;
    }

    /**
     * Renseigne la description du devis.
     *
     * @param descriptionDevis la description du devis.
     */

    public void setDescriptionDevis(String descriptionDevis) {
        this.descriptionDevis = descriptionDevis;
    }

    /**
     * Permet d'accèder à la duree du devis.
     *
     * @return la duree du devis.
     */

    public String getDureeDevis() {
        return dureeDevis;
    }

    /**
     * Renseigne la duree du devis.
     *
     * @param dureeDevis la duree du devis.
     */

    public void setDureeDevis(String dureeDevis) {
        this.dureeDevis = dureeDevis;
    }

    /**
     * Permet d'accèder à la date d'édition du devis.
     *
     * @return la date d'édition du devis.
     */

    public String getDateEditionDevis() {
        return dateEditionDevis;
    }

    /**
     * Renseigne la date d'édition du devis.
     *
     * @param dateEditionDevis la date d'édition du devis.
     */

    public void setDateEditionDevis(String dateEditionDevis) {
        this.dateEditionDevis = dateEditionDevis;
    }

    /**
     * Permet d'accèder à la date de commencement des travaux.
     *
     * @return la date de commencement des travaux.
     */

    public String getDateTravauxDevis() {
        return dateTravauxDevis;
    }

    /**
     * Renseigne la date de commencement des travaux.
     *
     * @param dateTravauxDevis la date de commencement des travaux.
     */

    public void setDateTravauxDevis(String dateTravauxDevis) {
        this.dateTravauxDevis = dateTravauxDevis;
    }

    /**
     * Permet d'accéder à la liste des packs du devis.
     *
     * @return la liste des packs du devis
     */
    public ArrayList<Pack> getPacks() {
        return this.lesPacks;
    }

    /**
     * Ajoute un pack à la liste des pack du devis.
     *
     * @param nouveauPack le nouveau pack du devis
     */
    public void ajouterPack(Pack nouveauPack) {
        this.lesPacks.add(nouveauPack);
    }

    /**
     * Permet d'accéder à la liste des composants du devis.
     *
     * @return la liste des composants du devis
     */
    public ArrayList<Composant> getComposants() {
        return this.lesComposants;
    }

    /**
     * Ajoute un composant à la liste des composants du devis.
     *
     * @param nouveauComposant le nouveau composant du devis
     */
    public void ajouterComposant(Composant nouveauComposant) {
        this.lesComposants.add(nouveauComposant);
    }

    /**
     * Représente textuellement les données du devis.
     */
    public String toString() {
        String devis = "DEVIS : " + this.idDevis;
        String nom = "\n\tNom : " + this.nomDevis;
        String description = "\n\tDescription : " + this.descriptionDevis;
        String duree = "\n\tDuree : " + this.dureeDevis;
        String dateEdition = "\n\tDate de creation du devis : " + this.dateEditionDevis;
        String dateTravaux = "\n\tDate de commencement des travaux : " + this.dateTravauxDevis;
        String packs = "\n\tLes packs : \n---------------";
        for (Pack p : this.lesPacks)
            packs += p.toString() + "\n\n";
        packs += "\n---------------";
        String composants = "\n\tLes composants : \n---------------";
        for (Composant c : this.lesComposants)
            composants += c.toString() + "\n\n";
        composants += "\n---------------";

        return devis + nom + description + duree + dateEdition + dateTravaux + packs + composants;
    }
}