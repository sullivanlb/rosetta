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

import com.example.rosetta.MainActivity;
import com.example.rosetta.R;
import com.example.rosetta.controller.ComposantAdapter;
import com.example.rosetta.controller.Controleur;
import com.example.rosetta.controller.ControleurAjoutQuestionScenario;
import com.example.rosetta.controller.ControleurEnregistretNouveauScenario;
import com.example.rosetta.controller.ControleurListeComposantScenario;
import com.example.rosetta.controller.ControleurListePackScenario;
import com.example.rosetta.controller.ControleurListeQuestionScenario;
import com.example.rosetta.controller.ObjectAdapter;
import com.example.rosetta.controller.PackAdapter;
import com.example.rosetta.controller.QuestionAdapter2;
import com.example.rosetta.model.Composant;
import com.example.rosetta.model.Pack;
import com.example.rosetta.model.Question;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Cette classe permet de mettre en place l'ensemble des controleurs correspondants à la vue
 * associée : l'interface de fragment_nouveaumodifier_scenario_layout.
 * Elle est accessible depuis le bouton nouveau Scénario de l'interface Scénario .
 *
 * @author Alice, Lucy, Christophe
 * @version 2.0
 */

public class NouveauScenarioFragment extends Fragment {

    private View rootView;
    private Controleur controleur;

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

    private ArrayList<Question> listeQuestions;
    private  ArrayList<Question> listeQuestionTemporaire;
    private ListView listeViewQuestion;
    private QuestionAdapter2 adapteurQuestion;
    private int indiceSelectionnerQuestion;

    private ArrayList<Object> listeObjectPackComposant = new ArrayList<Object>();
    private ListView listViewObject;
    private ObjectAdapter adapteurObject;

    private HashMap<Integer, Integer> hashmapIdComposantQuantite;
    private HashMap<Integer, Integer> hashmapIdPackQuantite;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.rootView = inflater.inflate(R.layout.fragment_nouveau_scenario_layout, container, false);


        // Le controleur principal (accès à la base de données interne)
        this.controleur = Controleur.getInstance(this.getContext());

        // =============================== COMPOSANT ================================================
        // La liste des composants récupérée depuis la base de données interne
        this.listeComposants = new ArrayList<Composant>(this.controleur.getListeComposants());

        // Initialisation de l'adapteur pour les composants
        this.listeViewComposant = (ListView) rootView.findViewById(R.id.listView_composants_nouveauscenario);
        ControleurListeComposantScenario controleurListeComposantScenario = new ControleurListeComposantScenario(this);
        this.listeViewComposant.setOnItemClickListener(controleurListeComposantScenario);

        this.adapteurComposant = new ComposantAdapter(this.getActivity(), listeComposants);
        this.listeViewComposant.setAdapter(this.adapteurComposant);

        //Initialisation de la hashmap
        this.hashmapIdComposantQuantite = new HashMap<Integer, Integer>();

        //================================= PACK ====================================================

        // La liste des composants récupérée depuis la base de données interne
        this.listePacks = new ArrayList<Pack>(this.controleur.getListePacks());

        // Initialisation de l'adapter pour les packs
        this.listeViewPack = (ListView) rootView.findViewById(R.id.listView_packs_nouveauscenario);
        ControleurListePackScenario controleurListePackScenario = new ControleurListePackScenario(this);
        this.listeViewPack.setOnItemClickListener(controleurListePackScenario);

        this.adapteurPack = new PackAdapter(this.getActivity(), listePacks);
        this.listeViewPack.setAdapter(this.adapteurPack);

        //Initisalisation de la hashmap
        this.hashmapIdPackQuantite = new HashMap<Integer, Integer>();

        //============================== Question ===================================================

        // La liste des composants récupérée depuis la base de données interne
        this.listeQuestions = new ArrayList<Question>(this.controleur.getListeQuestions());
        this.listeQuestionTemporaire = new ArrayList<Question>();

        // Initialisation de l'adapter pour les packs
        this.listeViewQuestion = (ListView) rootView.findViewById(R.id.listView_question);
        ControleurListeQuestionScenario controleurListeQuestionScenario = new ControleurListeQuestionScenario(this);
        this.listeViewQuestion.setOnItemClickListener(controleurListeQuestionScenario);

        this.adapteurQuestion = new QuestionAdapter2(this.getActivity(), listeQuestionTemporaire);
        this.listeViewQuestion.setAdapter(this.adapteurQuestion);

        //================== Object = Arrayliste de Composant + de Pack ============================

        this.listeObjectPackComposant = new ArrayList<Object>();

        // Initialisation de l'adapter pour les objects
        this.listViewObject = (ListView) rootView.findViewById(R.id.listView_pack_avec_composants_packs);

        this.adapteurObject = new ObjectAdapter(this.getActivity(), listeObjectPackComposant, null);
        this.listViewObject.setAdapter(this.adapteurObject);

        // =============================== Boutons =================================================

        //Bouton pour enregistrer les informations renseignées
        Button enregistrerButton = (Button) rootView.findViewById(R.id.nouveauscenarioEnregistrerButton);
        ControleurEnregistretNouveauScenario controleurEnregistretNouveauScenario = new ControleurEnregistretNouveauScenario(this);
        enregistrerButton.setOnClickListener(controleurEnregistretNouveauScenario);

        //Bouton pour ajouter une question à l'ArrayList
        Button ajoutQuestion = (Button) rootView.findViewById(R.id.ajout_questionButton);
        ControleurAjoutQuestionScenario controleurAjoutQuestionScenario = new ControleurAjoutQuestionScenario(this);
        ajoutQuestion.setOnClickListener(controleurAjoutQuestionScenario);

        // Bouton pour annuler
        Button annulerButton = (Button) rootView.findViewById(R.id.nouveauscenarioAnnulerButton);
        annulerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SectionsPagerAdapter.setScenarioFragment("ScenarioFragment");
                FragmentManager frman = getFragmentManager();
                FragmentTransaction ftran = frman.beginTransaction();
                Fragment leFrag = new ScenarioFragment();
                ftran.replace(R.id.view_pager, leFrag);
                ftran.commit();
                MainActivity.refreshFrag();
            }
        });

        // Effectuer une recherche sur la liste de composante et sur la liste de packs
        EditText recherche = (EditText) rootView.findViewById(R.id.rechercher_composants_packs_nouveauscenario);
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
     * @return la liste des composants
     */
    public ArrayList<Composant> getListeComposants() {
        return listeComposants;
    }

    /**
     * @return la liste des packs
     */
    public ArrayList<Pack> getListePacks() {
        return listePacks;
    }

    /**
     * @return la liste des questions
     */
    public ArrayList<Question> getListeQuestions() {
        return listeQuestions;
    }

    /**
     * @return la liste des temporaire
     */
    public ArrayList<Question> getListeQuestionTemporaire() {
        return listeQuestionTemporaire;
    }

    /**
     * @return la liste des objects (composant et pack)
     */
    public ArrayList<Object> getListeObjectPackComposant() {
        return listeObjectPackComposant;
    }

    /**
     * @return la hashMap (idComposant et quantité)
     */
    public HashMap<Integer, Integer> getHashmapIdComposantQuantite() {
        return hashmapIdComposantQuantite;
    }

    /**
     * @return la hashMap (idPack et quantité)
     */
    public HashMap<Integer, Integer> getHashmapIdPackQuantite() {
        return hashmapIdPackQuantite;
    }


    /**
     * Setter
     * @param indiceSelectionnerComposant à modifier
     */
    public void setIndiceSelectionnerComposant(int indiceSelectionnerComposant) {
        this.indiceSelectionnerComposant = indiceSelectionnerComposant;
    }

    /**
     * Setter
     * @param idSelectionnnerComposant à modifier
     */
    public void setIdSelectionnnerComposant(int idSelectionnnerComposant) {
        this.idSelectionnnerComposant = idSelectionnnerComposant;
    }

    /**
     * Setter
     * @param indiceSelectionnerPack à modifier
     */
    public void setIndiceSelectionnerPack(int indiceSelectionnerPack) {
        this.indiceSelectionnerPack = indiceSelectionnerPack;
    }

    /**
     * Setter
     * @param idSelectionnnerPack à modifier
     */
    public void setIdSelectionnnerPack(int idSelectionnnerPack) {
        this.idSelectionnnerPack = idSelectionnnerPack;
    }

    /**
     * @return l'indice selectionné d'une question
     */

    public int getIndiceSelectionnerQuestion() {
        return indiceSelectionnerQuestion;
    }

    /**
     * Setter
     * @param indiceSelectionnerQuestion à modifier
     */

    public void setIndiceSelectionnerQuestion(int indiceSelectionnerQuestion) {
        this.indiceSelectionnerQuestion = indiceSelectionnerQuestion;
    }


    /**
     * @return la listeView de Question
     */

    public ListView getListeViewQuestion() {
        return listeViewQuestion;
    }

    /**
     * @return la listeView d'Object
     */
    public ListView getListViewObject() {
        return listViewObject;
    }

}
