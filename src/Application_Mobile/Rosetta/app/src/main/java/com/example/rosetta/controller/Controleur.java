package com.example.rosetta.controller;

import android.content.Context;

import com.example.rosetta.model.AccesLocal;
import com.example.rosetta.model.Client;
import com.example.rosetta.model.Composant;
import com.example.rosetta.model.Devis;
import com.example.rosetta.model.Pack;
import com.example.rosetta.model.Question;
import com.example.rosetta.model.Scenario;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Cette classe permet de faire la différence proprement entre l'ensemble des contrôleurs et l'accès
 * local à la base de données interne.
 *
 * @author Christophe, Lucy, Alice
 * @version 2.0
 */
public class Controleur {

    private static Controleur instance;
    private static AccesLocal accesLocal;
    private static ArrayList<Client> listeClients;
    private static ArrayList<Scenario> listeScenarios;
    private static ArrayList<Composant> listeComposants;
    private static ArrayList<Pack> listePacks;
    private static  ArrayList<Question> listeQuestions;
    private static ArrayList<Devis> listeDevis;

    /**
     * Le constructeur crée une nouvelle forme de Controleur.
     */
    public Controleur() {
        super();
        this.listeClients = Controleur.accesLocal.tousLesClients();
        this.listeComposants = Controleur.accesLocal.tousLesComposants();
        this.listePacks = Controleur.accesLocal.tousLesPacks();
        this.listeQuestions = Controleur.accesLocal.tousLesQuestions();
        this.listeScenarios = Controleur.accesLocal.tousLesScenarios();
    }

    /**
     * Méthode statique qui permet de récupérer la seule et unique instance de cette classe.
     *
     * @param context le contexte
     * @return l'unique instance de Controleur
     */
    public static final Controleur getInstance(Context context) {
        if (Controleur.instance == null) {
            Controleur.accesLocal = new AccesLocal(context);
            Controleur.instance = new Controleur();
        }

        return Controleur.instance;
    }

    // ================================= Client ======================================================
    /**
     * Demande à la classe accesLocal d'ajouter un client.
     *
     * @param client le client à ajouter
     */
    public void creerClient(Client client) {
        if (client != null) {
            Controleur.accesLocal.ajoutClient(client);
            this.listeClients.add(Controleur.accesLocal.dernierClient());
        }
    }

    /**
     * Demande à la classe accesLocal de modifier un client.
     *
     * @param client le client à modifier
     */

    public void modifierClient(Client client){
        if(client != null){
            Controleur.accesLocal.modifierClient(client);

            int position = 0;
            boolean trouve = false;

            //Permet de retrouver la position dans l'ArrayList
            while (position < this.listeClients.size() && !trouve) {
                if (this.listeClients.get(position).getIdClient() == client.getIdClient()) {
                    trouve = true;
                }
                else position++;
            }
            if (trouve) {
                this.listeClients.set(position,client);
            }
        }
    }

    /**
     * Demande à la classe accesLocal de supprimer un client.
     *
     * @param id l'identifiant du client à supprimer
     */
    public void supprimerClient(int id) {
        Controleur.accesLocal.supprimerClient(id);

        int position = 0;
        boolean trouve = false;

        //En fonction de id, on retrouve la position du client dans l'ArrayList
        while (position < this.listeClients.size() && !trouve) {
            if (this.listeClients.get(position).getIdClient() == id) {
                trouve = true;
            }
            else position++;
        }
        if (trouve) {
            this.listeClients.remove(position);
        }
    }

    /**
     * Retourne la liste de tous les clients.
     *
     * @return le liste de tous les clients.
     */
    public static ArrayList<Client> getListeClients() {
        return listeClients;
    }

    //====================================== Scénario ===================================================

    /**
     * Demande à la classe accesLocal d'ajouter un scenario.
     *
     * @param scenario le scenario à ajouter
     */
    public void creerScenario(Scenario scenario) {
        if (scenario != null) {
            Controleur.accesLocal.ajoutScenario(scenario);
            this.listeScenarios.add(Controleur.accesLocal.dernierScenario());
            System.out.println("Scénario créer ");
        }
    }

    /**
     * Demande à la classe accesLocal de modifier un scenario.
     *
     * @param scenario le scenario à modifier
     */

    public void modifierScenario(Scenario scenario){
        if(scenario != null){
            Controleur.accesLocal.modifierScenario(scenario);

            int position = 0;
            boolean trouve = false;

            //Permet de retrouver la position dans l'ArrayList
            while (position < this.listeScenarios.size() && !trouve) {
                if (this.listeScenarios.get(position).getIdScenario() == scenario.getIdScenario()) {
                    trouve = true;
                }
                else position++;
            }
            if (trouve) {
                this.listeScenarios.set(position,scenario);
            }
        }
    }

    /**
     * Demande à la classe accesLocal de supprimer un scenario.
     *
     * @param id l'identifiant du scenario  à supprimer
     */
    public void supprimerScenario(int id) {
        Controleur.accesLocal.supprimerScenario(id);

        int position = 0;
        boolean trouve = false;

        //En fonction de id, on retrouve la position du scenario dans l'ArrayList
        while (position < this.listeScenarios.size() && !trouve) {
            if (this.listeScenarios.get(position).getIdScenario() == id) {
                trouve = true;
            }
            else position++;
        }
        if (trouve) {
            this.listeScenarios.remove(position);
        }
    }

    /**
     * Retourne la liste de tous les scénarios.
     *
     * @return le liste de tous les scénarios.
     */
    public static ArrayList<Scenario> getListeScenarios() {
        return listeScenarios;
    }

    // ================================== Composant =======================================================

    /**
     * Demande à la classe accesLocal d'ajouter un composant.
     *
     * @param composant le composant à ajouter
     */
    public void creerComposant(Composant composant) {
        if (composant != null) {
            Controleur.accesLocal.ajoutComposant(composant);
            this.listeComposants.add(Controleur.accesLocal.dernierComposant());
        }
    }

    /**
     * Demande à la classe accesLocal de modifier un composant.
     *
     * @param composant le composant à modifier
     */

    public void modifierComposant(Composant composant){
        if(composant != null){
            Controleur.accesLocal.modifierComposant(composant);

            int position = 0;
            boolean trouve = false;

            //Permet de retrouver la position dans l'ArrayList
            while (position < this.listeComposants.size() && !trouve) {
                if (this.listeComposants.get(position).getIdComposant() == composant.getIdComposant()) {
                    trouve = true;
                }
                else position++;
            }
            if (trouve) {
                this.listeComposants.set(position,composant);
            }
        }
    }

    /**
     * Retourne la liste de tous les composants.
     *
     * @return le liste de tous les composants.
     */
    public static ArrayList<Composant> getListeComposants() {
        return listeComposants;
    }

    // ================================= Pack =============================================================

    /**
     * Demande à la classe accesLocal d'ajouter un pack.
     *
     * @param pack le pack à ajouter
     */
    public void creerPack(Pack pack) {
        if (pack != null) {
            Controleur.accesLocal.ajoutPack(pack);
            this.listePacks.add(Controleur.accesLocal.dernierPack());
        }
    }

    /**
     * Demande à la classe accesLocal de modifier un pack.
     *
     * @param pack le pack à modifier
     */

    public void modifierPack(Pack pack){
        if(pack != null){
            Controleur.accesLocal.modifierPack(pack);

            int position = 0;
            boolean trouve = false;

            //Permet de retrouver la position dans l'ArrayList
            while (position < this.listePacks.size() && !trouve) {
                if (this.listePacks.get(position).getIdPack() == pack.getIdPack()) {
                    trouve = true;
                }
                else position++;
            }
            if (trouve) {
                this.listePacks.set(position,pack);
            }
        }
    }

    /**
     * Retourne la liste de tous les packs.
     *
     * @return le liste de tous les packs.
     */
    public static ArrayList<Pack> getListePacks() {
        return listePacks;
    }

    /**
     *
     * @param
     */

    public void supprimerPack(int idPack){
        Controleur.accesLocal.supprimerPack(idPack);
    }

    // ===================================Devis ===========================================================

    /**
     * Demande à la classe accesLocal d'ajouter un devis.
     *
     * @param devis le devis à ajouter
     */
    public void creerDevis(Devis devis) {
        if (devis != null) {
            Controleur.accesLocal.ajoutDevis(devis);
            this.listeDevis.add(Controleur.accesLocal.dernierDevis());
        }
    }

    /**
     * Demande à la classe accesLocal de modifier un devis.
     *
     * @param devis le devis à modifier
     */

    public void modifierDevis(Devis devis){
        if(devis != null){
            Controleur.accesLocal.modifierDevis(devis);

            int position = 0;
            boolean trouve = false;

            //Permet de retrouver la position dans l'ArrayList
            while (position < this.listeDevis.size() && !trouve) {
                if (this.listeDevis.get(position).getIdDevis() == devis.getIdDevis()) {
                    trouve = true;
                }
                else position++;
            }
            if (trouve) {
                this.listeDevis.set(position,devis);
            }
        }
    }

    /**
     * Demande à la classe accesLocal de supprimer un devis.
     *
     * @param id l'identifiant du devis à supprimer
     */
    public void supprimerDevis(int id) {
        Controleur.accesLocal.supprimerDevis(id);

        int position = 0;
        boolean trouve = false;

        //En fonction de id, on retrouve la position du client dans l'ArrayList
        while (position < this.listeDevis.size() && !trouve) {
            if (this.listeDevis.get(position).getIdDevis() == id) {
                trouve = true;
            }
            else position++;
        }
        if (trouve) {
            this.listeDevis.remove(position);
        }
    }

    /**
     * Retourne la liste de tous les devis.
     *
     * @return le liste de tous les devis.
     */
    public static ArrayList<Devis> getListeDevis() {
        return listeDevis;
    }

    // =================================== Question ================================================

    /**
     * Demande à la classe accesLocal d'ajouter une question.
     *
     * @param question la question à ajouter
     */
    public void creerQuestion(Question question) {
        if (question != null) {
            Controleur.accesLocal.ajoutQuestion(question);
            this.listeQuestions.add(Controleur.accesLocal.derniereQuestion());
        }
    }

    /**
     * Demande à la classe accesLocal de supprimer une question.
     *
     * @param id l'identifiant de la question à supprimer
     */
    public void supprimerQuestion(int id) {
        Controleur.accesLocal.supprimerQuetion(id);

        int position = 0;
        boolean trouve = false;

        //En fonction de id, on retrouve la position du client dans l'ArrayList
        while (position < this.listeQuestions.size() && !trouve) {
            if (this.listeQuestions.get(position).getIdQuestion() == id) {
                trouve = true;
            }
            else position++;
        }
        if (trouve) {
            this.listeQuestions.remove(position);
        }
    }

    /**
     * Retourne la liste de tous les questions.
     *
     * @return le liste de tous les questions.
     */
    public static ArrayList<Question> getListeQuestions() {
        return listeQuestions;
    }


    //================================== Appartient Pack Composant ==================================

    /**
     * Demande à la classe accesLocal d'ajouter un élèment dans la table AppartientPC.
     *
     * @param pack le pack à ajouter
     * @param composant le composant à ajouter
     */
    public void creerAppartientPC(Pack pack, Composant composant, int quantite) {
        if (pack != null && composant != null) {
            Controleur.accesLocal.ajoutAppartientPC(pack, composant, quantite);
        }
    }

    /**
     * @return tous les composants de la table appartient PC
     */
    public ArrayList<Integer> getTousLesElementsPC_Composants() {
        return Controleur.accesLocal.tousLesElementsPC_Composants();
    }

    /**
     * @return tous les packs de la table appartient PC
     */
    public ArrayList<Integer> getTousLesElementsPC_Packs() {
        return Controleur.accesLocal.tousLesElementsPC_Packs();
    }

    /**
     * @return tous les packs de la table appartient PC
     */
    public ArrayList<Integer> getTousLesElementsPC_Quantite() {
        return Controleur.accesLocal.tousLesElementsPC_Quantite();
    }

    /**
     *
     * @param
     */

    public void supprimerPackPC(int idPack){
        Controleur.accesLocal.supprimerPackPC(idPack);
    }

    //========================== Appartient Scénario Composant =======================================

    /**
     * Demande à la classe accesLocal d'ajouter un élèment dans la table AppartientSC.
     *
     * @param scenario le scénario à ajouter
     * @param composant le composant à ajouter
     */
    public void creerAppartientSC(Scenario scenario, Composant composant, int quantite) {
        if (scenario!= null && composant != null) {
            Controleur.accesLocal.ajoutAppartientSC(scenario, composant, quantite);
        }
    }

    /**
     *
     * @param
     */

    public void supprimerScenarioSC(int idScenario){
        Controleur.accesLocal.supprimerScenarioSC(idScenario);
    }

    //=============================== Appartient Scénario Pack ======================================

    /**
     * Demande à la classe accesLocal d'ajouter un élèment dans la table AppartientSP.
     *
     * @param scenario le scénario à ajouter
     * @param pack le pack à ajouter
     * @param quantite la quantité à ajouter
     */
    public void creerAppartientSP(Scenario scenario, Pack pack, int quantite) {
        if (scenario!= null && pack != null) {
            Controleur.accesLocal.ajoutAppartientSP(scenario, pack, quantite);
        }
    }

    /**
     * Getter
     * @return
     */
    public ArrayList<Integer> getTousLesElementsSP_scenarios(){
        return Controleur.accesLocal.tousLesElementsSP_Scenarios();
    }

    /**
     * Getter
     * @return
     */
    public ArrayList<Integer> getTousLesElementsSP_pack(){
        return Controleur.accesLocal.tousLesElementsSP_Pack();
    }

    /**
     * Getter
     * @return
     */
    public ArrayList<Integer> getTousLesElementsSP_quantite(){
        return Controleur.accesLocal.tousLesElementsSP_Quantite();
    }

    /**
     *
     * @param
     */

    public void supprimerScenarioSP(int idScenario){
        Controleur.accesLocal.supprimerScenarioSP(idScenario);
    }


    //========================= Appartient Scénario Question ========================================

    /**
     * Demande à la classe accesLocal d'ajouter un élèment dans la table AppartientSQ.
     *
     * @param scenario le scénario à ajouter
     * @param question la question à ajouter
     */
    public void creerAppartientSQ(Scenario scenario, Question question) {
        if (scenario!= null && question != null) {
            Controleur.accesLocal.ajoutAppartientSQ(scenario, question);
        }
    }

    /**
     *
     * @param
     */

    public void supprimerScenarioSQ(int idScenario){
        Controleur.accesLocal.supprimerScenarioSQ(idScenario);
    }

    //================================== Appartient Devis Pack ==================================

    /**
     * Demande à la classe accesLocal d'ajouter un élèment dans la table AppartientPC.
     *
     * @param idDevis le pack à ajouter
     * @param idPack le composant à ajouter
     */
    public void creerAppartientDP(int idDevis, int idPack, int quantite) {
        Controleur.accesLocal.ajoutAppartientDP(idDevis, idPack, quantite);
    }

    /**
     *
     * @param
     */

    public void supprimerPackDP(int idPack){
        Controleur.accesLocal.supprimerPackDP(idPack);
    }

    /**
     * Getter
     * @return
     */
    public ArrayList<Integer> getTousLesElementsDP_devis(){
        return Controleur.accesLocal.tousLesElementsDP_Devis();
    }


    /**
     * Getter
     * @return
     */
    public ArrayList<Integer> getTousLesElementsDP_pack(){
        return Controleur.accesLocal.tousLesElementsDP_Pack();
    }

    /**
     * Getter
     * @return
     */
    public ArrayList<Integer> getTousLesElementsDP_quantite(){
        return Controleur.accesLocal.tousLesElementsDP_Quantite();
    }
}
