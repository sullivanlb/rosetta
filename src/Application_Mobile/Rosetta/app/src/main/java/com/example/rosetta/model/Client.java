package com.example.rosetta.model;

import java.util.ArrayList;

/**
 * Cette classe permet de représenter un Client.
 *
 * @author Christophe
 * @version 1.0
 */
public class Client {

    /**
     * Le numéro d'identification du client.
     */
    private int idClient;

    /**
     * Le nom du client.
     */
    private String nomClient;

    /**
     * Le prénom du client.
     */
    private String prenomClient;

    /**
     * L'adresse postale du client.
     */
    private String adresseClient;

    /**
     * L'adresse email du client.
     */
    private String emailClient;

    /**
     * Le numéro de téléphone du client.
     */
    private String telClient;

    /**
     * Le sexe du client.
     */
    private Sexe sexeClient;

    /**
     * Liste de tous les devis du client.
     */
    private ArrayList<Devis> lesDevis;

    /**
     * Initialise les données du nouveau client créé.
     *
     * @param id      le numéro d'identification du client
     * @param nom     le nom du client
     * @param prenom  le prénom du client
     * @param adresse l'adresse postale du client
     * @param email   l'adresse email du client
     * @param tel     le numéro de téléphone du client
     * @param sexe    le sexe du client
     */
    public Client(int id, String nom, String prenom, String adresse, String email, String tel, Sexe sexe) {
        this.setIdClient(id);
        this.setNomClient(nom);
        this.setPrenomClient(prenom);
        this.setAdresseClient(adresse);
        this.setEmailClient(email);
        this.setTelClient(tel);
        this.setSexeClient(sexe);
        this.lesDevis = new ArrayList<Devis>();
    }

    /**
     * Permet d'accéder au numéro d'identification du client.
     *
     * @return le numéro d'identification du client
     */
    public int getIdClient() {
        return this.idClient;
    }

    /**
     * Renseigne le numéro d'identification du client.
     *
     * @param id le numéro d'identification du client
     */
    public void setIdClient(int id) {
        this.idClient = id;
    }

    /**
     * Permet d'accéder au nom du client.
     *
     * @return le nom du client
     */
    public String getNomClient() {
        return this.nomClient;
    }

    /**
     * Renseigne le nom du client.
     *
     * @param nom le nom du client
     */
    public void setNomClient(String nom) {
        this.nomClient = nom;
    }

    /**
     * Permet d'accéder au prénom du client.
     *
     * @return le prénom du client
     */
    public String getPrenomClient() {
        return this.prenomClient;
    }

    /**
     * Renseigne le prénom du client.
     *
     * @param prenom le prénom du client
     */
    public void setPrenomClient(String prenom) {
        this.prenomClient = prenom;
    }

    /**
     * Permet d'accéder à l'adresse postale du client.
     *
     * @return l'adresse postale du client
     */
    public String getAdresseClient() {
        return this.adresseClient;
    }

    /**
     * Renseigne l'adresse postale du client.
     *
     * @param adresse l'adresse postale du client
     */
    public void setAdresseClient(String adresse) {
        this.adresseClient = adresse;
    }

    /**
     * Permet d'accéder à l'adresse email du client.
     *
     * @return l'adresse email du client
     */
    public String getEmailClient() {
        return this.emailClient;
    }

    /**
     * Renseigne l'adresse email du client.
     *
     * @param email l'adresse email du client
     */
    public void setEmailClient(String email) {
        this.emailClient = email;
    }

    /**
     * Permet d'accéder au numéro de téléphone du client.
     *
     * @return le numéro de téléphone du client
     */
    public String getTelClient() {
        return this.telClient;
    }

    /**
     * Renseigne le numéro de téléphone du client.
     *
     * @param tel le numéro de téléphone du client
     */
    public void setTelClient(String tel) {
        this.telClient = tel;
    }

    /**
     * Permet d'accéder au sexe du client.
     *
     * @return le sexe du client
     */
    public Sexe getSexeClient() {
        return this.sexeClient;
    }

    /**
     * Renseigne le sexe du client.
     *
     * @param sexe le sexe du client
     */
    public void setSexeClient(Sexe sexe) {
        this.sexeClient = sexe;
    }

    /**
     * Permet d'accéder à la liste des devis du client.
     *
     * @return la liste des devis du client
     */
    public ArrayList<Devis> getDevis() {
        return this.lesDevis;
    }

    /**
     * Ajoute un devis à la liste des devis du client.
     *
     * @param nouveauDevis le nouveau devis du client
     */
    public void ajouterDevis(Devis nouveauDevis) {
        this.lesDevis.add(nouveauDevis);
    }

    /**
     * Représente textuellement les données du client.
     */
    public String toString() {
//        String client = "CLIENT : " + this.idClient;
//        String nom = "\n\tNom : " + this.nomClient;
//        String prenom = "\n\tPrenom : " + this.prenomClient;
//        String adresse = "\n\tAdresse : " + this.adresseClient;
//        String email = "\n\tEmail : " + this.emailClient;
//        String tel = "\n\tTel : " + this.telClient;
//        String sexe = "\n\tSexe : " + this.sexeClient;
//        String devis = "\n\tLes devis : \n---------------";
//        for (Devis d : this.lesDevis)
//            devis += d.toString() + "\n\n";
//        devis += "\n---------------";

//        return client + nom + prenom + adresse + email + tel + sexe + devis;

        return this.getNomClient() + " " + this.getPrenomClient();
    }

}