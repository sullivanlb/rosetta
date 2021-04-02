package com.example.rosetta.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.rosetta.R;
import com.example.rosetta.controller.Controleur;
import com.example.rosetta.controller.ControleurListeScenario;
import com.example.rosetta.controller.ScenarioAdapter;
import com.example.rosetta.model.Scenario;

import java.util.ArrayList;

/**
 * Cette classe permet de mettre en place l'ensemble des controleurs correspondants à la vue
 * associée : l'interface de Scenario.
 *
 * @author Alice, Lucy
 * @version 2.0
 */
public class ScenarioFragment extends Fragment {

    private static ScenarioFragment scenarioFragment;

    private View rootView;
    private Controleur controleur;

    private ArrayList<Scenario> listeScenarios;
    private ListView listeViewScenarios;
    private ScenarioAdapter adapteurScenarios;
    private int indiceSelectionnerScenario;
    private int idSelectionnnerScenario;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.rootView = inflater.inflate(R.layout.fragment_scenario_layout, container, false);

        scenarioFragment = this;

        // Le controleur principal (accès à la base de données interne)
        this.controleur = Controleur.getInstance(this.getContext());

        // La liste des scénarios récupérée depuis la base de données interne
        this.listeScenarios = new ArrayList<Scenario>(this.controleur.getListeScenarios());

        // Initialisation de l'adapteur pour les composants
        this.listeViewScenarios = (ListView) rootView.findViewById(R.id.scenarioListe);
        ControleurListeScenario controleurListeScenario = new ControleurListeScenario(this);
        this.listeViewScenarios.setOnItemClickListener(controleurListeScenario);

        this.adapteurScenarios = new ScenarioAdapter(this.getActivity(),listeScenarios, this);
        this.listeViewScenarios.setAdapter(this.adapteurScenarios);


        //======================= Boutons ==========================================================


        //Bouton pour ajouter des composants ou des packs
        Button composantPackButton = (Button) rootView.findViewById(R.id.composantsPacksButton);
        composantPackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SectionsPagerAdapter.setScenarioFragment("ComposantPackFragment");
                FragmentManager frman = getFragmentManager();
                FragmentTransaction ftran = frman.beginTransaction();
                Fragment leFrag = new ComposantPackFragment();
                ftran.replace(R.id.view_pager, leFrag);
                ftran.commit();
            }
        });

        //Bouton pour ajouter un scénario
        Button nouveauScenario = (Button) rootView.findViewById(R.id.nouveauScenarioButton);
        nouveauScenario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SectionsPagerAdapter.setScenarioFragment("NouveauModifierScenarioFragment");
                FragmentManager frman = getFragmentManager();
                FragmentTransaction ftran = frman.beginTransaction();
                Fragment leFrag = new NouveauScenarioFragment();
                ftran.replace(R.id.view_pager, leFrag);
                ftran.commit();
            }
        });


        return this.rootView;
    }

    /**
     * @return la liste des Scénarios
     */

    public ArrayList<Scenario> getListeScenarios() {
        return listeScenarios;
    }

    /**
     * @return l'indice selectionné
     */

    public int getIndiceSelectionnerScenario() {
        return indiceSelectionnerScenario;
    }

    /**
     * Setter
     * @param indiceSelectionnerScenario à modifier
     */

    public void setIndiceSelectionnerScenario(int indiceSelectionnerScenario) {
        this.indiceSelectionnerScenario = indiceSelectionnerScenario;
    }

    /**
     * @return l'id selectionné
     */

    public int getIdSelectionnnerScenario() {
        return idSelectionnnerScenario;
    }

    /**
     * Setter
     * @param idSelectionnnerScenario à modifier
     */

    public void setIdSelectionnnerScenario(int idSelectionnnerScenario) {
        this.idSelectionnnerScenario = idSelectionnnerScenario;
    }

    /**
     * Permet d'actualiser la liste affichée des composants lorsqu'un scénario est ajouté, ou supprimé
     */
    public void actualiserListeScenarios() {
        listeScenarios = new ArrayList<Scenario>(controleur.getListeScenarios());
        adapteurScenarios = new ScenarioAdapter(getActivity(), listeScenarios, this);
        listeViewScenarios.setAdapter(adapteurScenarios);
    }

    /**
     * Cette méthode permet d'accéder au fragment InfoScenarioFragment à partir du FloatingActionButton
     * "voirScenarioButton" de la listeView de ScénarioFragement.
     */

    public void actionVoir(){
        SectionsPagerAdapter.setScenarioFragment("InfoScenarioFragment");
        FragmentManager frman = getFragmentManager();
        FragmentTransaction ftran = frman.beginTransaction();
        Fragment leFrag = InfoScenarioFragment.getInstance();
        ((InfoScenarioFragment) leFrag).setScenarioAffiche(listeScenarios.get(indiceSelectionnerScenario));
        ((InfoScenarioFragment) leFrag).setScenarioFragment(this);
        ftran.replace(R.id.view_pager, leFrag);
        ftran.commit();


    }

    /**
     * Cette méthode permet de donner l'accès à une instance de ScenarioFragement.
     * @return une instance de ScenarioFragment
     */
    public static ScenarioFragment getInstance(){
        if (scenarioFragment == null){
            scenarioFragment = new ScenarioFragment();
        }
        return scenarioFragment;
    }
}
