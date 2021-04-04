package com.example.rosetta.ui.main;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.rosetta.R;
import com.example.rosetta.controller.ClientAdapter;
import com.example.rosetta.controller.ComposantAdapter;
import com.example.rosetta.controller.Controleur;
import com.example.rosetta.controller.ControleurEnregistrerNouveauDevis;
import com.example.rosetta.controller.ControleurListeComposantDevis;
import com.example.rosetta.controller.ControleurListeComposants;
import com.example.rosetta.controller.ControleurListePack;
import com.example.rosetta.controller.ControleurListePackDevis;
import com.example.rosetta.controller.DevisAdapterObject;
import com.example.rosetta.controller.ObjectAdapter;
import com.example.rosetta.controller.PackAdapter;
import com.example.rosetta.controller.QuestionAdapter;
import com.example.rosetta.model.Client;
import com.example.rosetta.model.Composant;
import com.example.rosetta.model.Pack;
import com.example.rosetta.model.Question;
import com.example.rosetta.model.Scenario;
import com.example.rosetta.model.Sexe;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Cette classe permet de mettre en place l'ensemble des controleurs correspondants à la vue
 * associée : l'interface de fragment_nouveaudevis_final_layout.
 * Elle est accessible depuis le bouton nouveau Devis/suivant le  de l'interface Devis.
 *
 * @author ALice, Lucy
 * @version 2.0
 */

public class NouveauDevisFragment extends Fragment {

    private View rootView;
    private Controleur controleur;

    private NouveauDevisClientScenarioFragment nouveauDevisClientScenarioFragment;
    private static NouveauDevisFragment instance;
    private Client clientChoisi;

    private ArrayList<Composant> listeComposants;
    private ListView listeViewComposant;
    private ComposantAdapter adapteurComposant;
    private int indiceSelectionnerComposant;
    private int idSelectionnnerComposant;

    private ArrayList<Pack> listePacks;
    private ListView listeViewPack;
    private PackAdapter adapteurPack;
    private int indiceSelectionnerPack;
    private int idSelectionnnerPack;

    private ArrayList<Object> listObject;
    private ListView listeViewObject;
    private DevisAdapterObject adapteurObject;

    private HashMap<Integer, Integer> hashmapIdComposantQuantite;
    private HashMap<Integer, Integer> hashmapIdPackQuantite;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.rootView = inflater.inflate(R.layout.fragment_nouveaudevis_final_layout, container, false);

        // Le controleur principal (accès à la base de données interne)
        this.controleur = Controleur.getInstance(this.getContext());

        // =============================== COMPOSANT ================================================
        // La liste des composants récupérée depuis la base de données interne
        this.listeComposants = new ArrayList<Composant>(this.controleur.getListeComposants());

        // Initialisation de l'adapteur pour les composants
        this.listeViewComposant = (ListView) rootView.findViewById(R.id.listView_composants_nouveaudevis);
        ControleurListeComposantDevis controleurListeComposantDevis = new ControleurListeComposantDevis(this);
        this.listeViewComposant.setOnItemClickListener(controleurListeComposantDevis);

        this.adapteurComposant = new ComposantAdapter(this.getActivity(), listeComposants);
        this.listeViewComposant.setAdapter(this.adapteurComposant);

        //Initialisation de la hashmap
        this.hashmapIdComposantQuantite = new HashMap<Integer, Integer>();

        //================================= PACK ====================================================

        // La liste des composants récupérée depuis la base de données interne
        this.listePacks = new ArrayList<Pack>(this.controleur.getListePacks());

        // Initialisation de l'adapter pour les packs
        this.listeViewPack = (ListView) rootView.findViewById(R.id.listView_packs_nouveaudevis);
        ControleurListePackDevis controleurListePackDevis = new ControleurListePackDevis(this);
        this.listeViewPack.setOnItemClickListener(controleurListePackDevis);

        this.adapteurPack = new PackAdapter(this.getActivity(), listePacks);
        this.listeViewPack.setAdapter(this.adapteurPack);

        //Initisalisation de la hashmap
        this.hashmapIdPackQuantite = new HashMap<Integer, Integer>();

        //============================== Object =====================================================
        this.listObject = new ArrayList<Object>();

        //Récupèration des données (Client + Scénarios)
        this.setClientChoisi(this.nouveauDevisClientScenarioFragment.getClientChoisi());

        //Affichage Composant et Pack
        ArrayList<Scenario> ScenarioChoisi =  this.nouveauDevisClientScenarioFragment.getListScenarioTemporaire();
        ArrayList<Integer> toutesLesQuantites = new ArrayList<>();
        ArrayList<Integer> tousIdComposantSC= Controleur.getInstance(this.nouveauDevisClientScenarioFragment.getContext()).getTousLesElementsSC_composant();
        ArrayList<Integer> tousIdScenarioSC= Controleur.getInstance(this.nouveauDevisClientScenarioFragment.getContext()).getTousLesElementsSC_scenarios();
        ArrayList<Integer> tousIdQuantiteSC = Controleur.getInstance(this.nouveauDevisClientScenarioFragment.getContext()).getTousLesElementsSC_quantite();
        ArrayList<Integer> tousIdPackSP = Controleur.getInstance(this.nouveauDevisClientScenarioFragment.getContext()).getTousLesElementsSP_pack();
        ArrayList<Integer> tousIdScenarioSP = Controleur.getInstance(this.nouveauDevisClientScenarioFragment.getContext()).getTousLesElementsSP_scenarios();
        ArrayList<Integer> tousIdQuantiteSP = Controleur.getInstance(this.nouveauDevisClientScenarioFragment.getContext()).getTousLesElementsSP_quantite();
        ArrayList<Composant> touslesComposants = Controleur.getInstance(this.nouveauDevisClientScenarioFragment.getContext()).getListeComposants();
        ArrayList<Pack> touslesPacks = Controleur.getInstance(this.nouveauDevisClientScenarioFragment.getContext()).getListePacks();


        for (Composant composant : touslesComposants) {
            for (int i = 0; i < tousIdComposantSC.size(); i++){
                if (composant.getIdComposant() == tousIdComposantSC.get(i)){
                    for(int j = 0; j < ScenarioChoisi.size(); j++){
                        if(tousIdScenarioSC.get(i) == ScenarioChoisi.get(j).getIdScenario()){
                            this.listObject.add(composant);
                            //toutesLesQuantites.add(tousIdQuantiteSC.get(i));
                            hashmapIdComposantQuantite.put(composant.getIdComposant(),tousIdQuantiteSC.get(i));
                        }
                    }
                }
            }
        }
       for (Pack pack : touslesPacks) {
            for (int i = 0; i < tousIdPackSP.size(); i++){
                if (pack.getIdPack() == tousIdPackSP.get(i)){
                    for(int j = 0; j < ScenarioChoisi.size(); j++) {
                        if(tousIdScenarioSP.get(i) == ScenarioChoisi.get(j).getIdScenario()){
                            this.listObject.add(pack);
                            //toutesLesQuantites.add(tousIdQuantiteSP.get(i));
                            hashmapIdPackQuantite.put(pack.getIdPack(), tousIdQuantiteSP.get(i));
                        }
                    }
                }
            }
        }

        this.listeViewObject = (ListView) rootView.findViewById(R.id.listView_pack_avec_composants_packs);

        this.adapteurObject = new DevisAdapterObject(this.getActivity(), listObject);
        this.listeViewObject.setAdapter(this.adapteurObject);

        //============================= Boutons ======================================================

        //Bouton pour enregistrer un nouveau devis
        Button nouveaudevisEnregistrerDevisButton = (Button) rootView.findViewById(R.id.nouveaudevisEnregistrerDevisButton);
        ControleurEnregistrerNouveauDevis controleurEnregistrerNouveauDevis = new ControleurEnregistrerNouveauDevis(this);
        nouveaudevisEnregistrerDevisButton.setOnClickListener(controleurEnregistrerNouveauDevis);

        //bouton qui permet de revenir en arrière
        Button nouveaudevisRetourButton = (Button) rootView.findViewById(R.id.nouveaudevisRetourButton);
        nouveaudevisRetourButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SectionsPagerAdapter.setDevisFragment("DevisQuestionFragment");
                FragmentManager frman = getFragmentManager();
                FragmentTransaction ftran = frman.beginTransaction();
                Fragment leFrag = new DevisQuestionFragment();
                ftran.replace(R.id.view_pager, leFrag);
                ftran.commit();
            }
        });

        // Effectuer une recherche sur la liste de composante et sur la liste de packs
        EditText recherche = (EditText) rootView.findViewById(R.id.rechercher_composants_packs_nouveaudevis);
        recherche.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {

                String input = s.toString();

                if (input != null && !input.equals("") && !input.isEmpty()) {

                    ArrayList<Composant> rechercheListComposant= new ArrayList<Composant>();
                    ArrayList<Pack> rechercheListPack = new ArrayList<Pack>();
                    for (Composant c : listeComposants) {
                        if (c.getNomComposant().contains(input)) {
                            rechercheListComposant.add(c);
                        }
                    }
                    listeComposants = rechercheListComposant;
                    adapteurComposant = new ComposantAdapter(getActivity(), listeComposants);
                    listeViewComposant.setAdapter(adapteurComposant);

                    for(Pack p : listePacks){
                        if(p.getNomPack().contains(input)){
                            rechercheListPack.add(p);
                        }
                    }
                    listePacks = rechercheListPack;
                    adapteurPack = new PackAdapter(getActivity(), listePacks);
                    listeViewPack.setAdapter(adapteurPack);

                }
                else {
                    listeComposants = new ArrayList<Composant>(controleur.getListeComposants());
                    adapteurComposant = new ComposantAdapter(getActivity(), listeComposants);
                    listeViewComposant.setAdapter(adapteurComposant);

                    listePacks = new ArrayList<Pack>(controleur.getListePacks());
                    adapteurPack = new PackAdapter(getActivity(), listePacks);
                    listeViewPack.setAdapter(adapteurPack);
                }
            }
        });

        return this.rootView;
    }

    /**
     * Getter
     *
     * @return la liste des composants
     */
    public ArrayList<Composant> getListeComposants() {
        return listeComposants;
    }

    /**
     * Getter
     *
     * @return la liste des packs
     */
    public ArrayList<Pack> getListePacks() {
        return listePacks;
    }

    /**
     * Getter
     *
     * @return la liste des objects
     */
    public ArrayList<Object> getListObject() {
        return listObject;
    }

    /**
     * Getter
     *
     * @return le client choisi
     */
    public Client getClientChoisi() {
        return clientChoisi;
    }

    /**
     * Setter
     *
     * @param clientChoisi à modifier
     */
    public void setClientChoisi(Client clientChoisi) {
        this.clientChoisi = clientChoisi;
    }

    /**
     * Getter
     *
     * @return la hashMap (idComposant et quantité)
     */
    public HashMap<Integer, Integer> getHashmapIdComposantQuantite() {
        return hashmapIdComposantQuantite;
    }

    /**
     * Getter
     *
     * @return la hashMap (idPack et quantité)
     */
    public HashMap<Integer, Integer> getHashmapIdPackQuantite() {
        return hashmapIdPackQuantite;
    }

    /**
     * Getter
     *
     * @return l'indice selectionné
     */
    public int getIndiceSelectionnerComposant() {
        return indiceSelectionnerComposant;
    }

    /**
     * Getter
     *
     * @return l'identifiant du composant selectionne
     */
    public int getIdSelectionnnerComposant() {
        return idSelectionnnerComposant;
    }

    /**
     * Setter
     *
     * @param indiceSelectionnerComposant à modifier
     */
    public void setIndiceSelectionnerComposant(int indiceSelectionnerComposant) {
        this.indiceSelectionnerComposant = indiceSelectionnerComposant;
    }

    /**
     * Setter
     *
     * @param idSelectionnnerComposant à modifier
     */
    public void setIdSelectionnnerComposant(int idSelectionnnerComposant) {
        this.idSelectionnnerComposant = idSelectionnnerComposant;
    }

    /**
     * Setter
     *
     * @param indiceSelectionnerPack à modifier
     */
    public void setIndiceSelectionnerPack(int indiceSelectionnerPack) {
        this.indiceSelectionnerPack = indiceSelectionnerPack;
    }

    /**
     * Setter
     *
     * @param idSelectionnnerPack à modifier
     */
    public void setIdSelectionnnerPack(int idSelectionnnerPack) {
        this.idSelectionnnerPack = idSelectionnnerPack;
    }

    /**
     * Getter
     *
     * @return la listeView d'Object
     */
    public ListView getListeViewObject() {
        return listeViewObject;
    }

    /**
     * Permet d'actualiser la liste affichée des composants lorsqu'un composant est ajouté, ou
     * ses informations ont été modifiées.
     */
    public void actualiserListeComposants() {
        listeComposants = new ArrayList<Composant>(controleur.getListeComposants());
        adapteurComposant = new ComposantAdapter(getActivity(), listeComposants);
        listeViewComposant.setAdapter(adapteurComposant);
        rechercher();
    }

    /**
     * Permet d'actualiser la liste affichée des composants lorsqu'un pack est ajouté, ou
     * ses informations ont été modifiées.
     */
    public void actualiserListePacks(){
        listePacks = new ArrayList<Pack>(controleur.getListePacks());
        adapteurPack = new PackAdapter(getActivity(), listePacks);
        listeViewPack.setAdapter(adapteurPack);
        rechercher();
    }

    /**
     * Cette méthode permet de mettre à jour la recherche, et d'afficher les composant et les packs recherchés.
     */
    public void rechercher() {

        EditText recherche = (EditText) rootView.findViewById(R.id.rechercher_composants_packs);
        String input = recherche.getText().toString();

        ArrayList<Composant> rechercheListComposant= new ArrayList<Composant>();
        ArrayList<Pack> rechercheListPack = new ArrayList<Pack>();
        for (Composant c : listeComposants) {
            if (c.getNomComposant().contains(input)) {
                rechercheListComposant.add(c);
            }
        }
        listeComposants = rechercheListComposant;
        adapteurComposant = new ComposantAdapter(getActivity(), listeComposants);
        listeViewComposant.setAdapter(adapteurComposant);

        for(Pack p : listePacks){
            if(p.getNomPack().contains(input)){
                rechercheListPack.add(p);
            }
        }
        listePacks = rechercheListPack;
        adapteurPack = new PackAdapter(getActivity(), listePacks);
        listeViewPack.setAdapter(adapteurPack);
    }

    /**
     * Récupère l'Instance et les données (client + scénarios)
     * @param nDCSF la nouvelle instance
     */
    public void setNouveauDevisClientScenarioFragment(NouveauDevisClientScenarioFragment nDCSF) {
        this.nouveauDevisClientScenarioFragment = nDCSF;
    }

    /**
     * @return donne l'accès à une instance de DevisQuestionFragment
     */
    public static NouveauDevisFragment getInstance(){
        if (instance == null){
            instance = new NouveauDevisFragment();
        }
        return instance;
    }

}
