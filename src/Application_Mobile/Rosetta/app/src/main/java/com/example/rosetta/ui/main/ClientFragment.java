package com.example.rosetta.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.rosetta.MainActivity;
import com.example.rosetta.R;
import com.example.rosetta.controller.ClientAdapter;
import com.example.rosetta.controller.ControleurCheckSexeClient;
import com.example.rosetta.controller.ControleurEnregistrerClient;
import com.example.rosetta.model.Client;
import com.example.rosetta.model.Scenario;
import com.example.rosetta.model.Sexe;

import java.util.ArrayList;

import static com.example.rosetta.model.Sexe.AUTRE;
import static com.example.rosetta.model.Sexe.FEMME;
import static com.example.rosetta.model.Sexe.HOMME;

public class ClientFragment extends Fragment {

    private ArrayAdapter<CharSequence> adapterNomClients;
    private ListView listeViewClients;
    private Button enregistrerButton;
    private CheckBox femmeButton;
    private CheckBox hommeButton;
    private CheckBox autreButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_client_layout, container, false);

        // Le bouton pour enregistrer les données d'un client (ajouter/sauvegarder)
        this.enregistrerButton = (Button) rootView.findViewById(R.id.EnregistrerButton);
        ControleurEnregistrerClient controleurEnregistrerClient =
                new ControleurEnregistrerClient(this);
        this.enregistrerButton.setOnClickListener(controleurEnregistrerClient);

        // Les cases à cocher pour spécifier le sexe du client
        this.femmeButton = (CheckBox) rootView.findViewById(R.id.femmeCheckbox);
        this.hommeButton = (CheckBox) rootView.findViewById(R.id.hommeCheckbox);
        this.autreButton = (CheckBox) rootView.findViewById(R.id.autreCheckbox);
        ArrayList<CheckBox> listeCases = new ArrayList<CheckBox>();
        listeCases.add(this.femmeButton);
        listeCases.add(this.hommeButton);
        listeCases.add(this.autreButton);
        ControleurCheckSexeClient controleurCheckSexeClient = new ControleurCheckSexeClient(listeCases);
        this.femmeButton.setOnClickListener(controleurCheckSexeClient);
        this.hommeButton.setOnClickListener(controleurCheckSexeClient);
        this.autreButton.setOnClickListener(controleurCheckSexeClient);

        // Temporaire : tant que nous n'avons pas connecté la BDD (liste des clients)
        ArrayList<Client> listeClients = new ArrayList<Client>();
        Client client1 = new Client(1, "Grint","Rupert","110 rue des allouettes","nom1@gmail.com","0777777777", AUTRE);
        Client client2 = new Client(2, "Watson","Emma","89 rue des fleurs","nom2@gmail.com","0666666666", FEMME);
        Client client3 = new Client(3, "Radcliffe","Daniel","48 rue des allouettes","nom3@gmail.com","0777445566", HOMME);
        Client client4 = new Client(4, "Felton","Tom","78 rue des oiseaux","nom4@gmail.com","0777445567", HOMME);
        Client client5 = new Client(5, "Smith","Maggie","45 rue des corbeaux","nom5@gmail.com","0777445567", AUTRE);
        Client client6 = new Client(6, "Yates","David","32 rue des pies","nom6@gmail.com","0777444444", HOMME);
        Client client7 = new Client(7, "Zimmer","Hanz","12 rue des jaguars","nom7@gmail.com","0777443333", HOMME);
        Client client8 = new Client(8, "Depp","Johnny","2 rue des arcs","nom8@gmail.com","0777445127", HOMME);
        Client client9 = new Client(9, "Hanks","Tom","14 rue des bois","nom9@gmail.com","0777445514", HOMME);
        Client client10 = new Client(10, "DiCaprio","Leonardo","17 boulevard des oiseaux","nom10@gmail.com","0787121314", HOMME);
        Client client11 = new Client(11, "De Funès","Louis","3 rue des guarages","nom11@gmail.com","0777441720", HOMME);
        Client client12 = new Client(12, "Eastwood","Clint","14 rue des cars","nom12@gmail.com","0666445567", HOMME);
        Client client13 = new Client(13, "Smith","Will","15 rue des trais","nom13@gmail.com","0657449567", HOMME);
        Client client14 = new Client(14, "Cruise","Tom","78 rue des oiseaux","nom14@gmail.com","0777333333", HOMME);
        Client client15 = new Client(15, "Nolan","Christopher","17 rue des fires","nom15@gmail.com","0777442222", HOMME);
        Client client16 = new Client(16, "Willis","Bruce","49 rue des combattants","nom15@gmail.com","0636476234", HOMME);
        Client client17 = new Client(17, "Johansson","Scarlett","20 rue des combattantes","nom15@gmail.com","0765656565", FEMME);
        Client client18 = new Client(18, "Holland","Tom","3 rue des araignees","nom15@gmail.com","0754166362", HOMME);
        Client client19 = new Client(19, "Downey Jr.","Robert","18 rue des métaux","nom15@gmail.com","0654545434", HOMME);
        Client client20 = new Client(20, "Freeman","Morgan","44 rue des dieux","nom15@gmail.com","0762954352", HOMME);

        listeClients.add(client1);
        listeClients.add(client2);
        listeClients.add(client3);
        listeClients.add(client4);
        listeClients.add(client5);
        listeClients.add(client6);
        listeClients.add(client7);
        listeClients.add(client8);
        listeClients.add(client9);
        listeClients.add(client10);
        listeClients.add(client11);
        listeClients.add(client12);
        listeClients.add(client13);
        listeClients.add(client14);
        listeClients.add(client15);
        listeClients.add(client16);
        listeClients.add(client17);
        listeClients.add(client18);
        listeClients.add(client19);
        listeClients.add(client20);

        // Initialisation de l'adapter pour scenario
        ClientAdapter adapter = new ClientAdapter(this.getActivity(), listeClients);
        ListView list = (ListView) rootView.findViewById(R.id.listView_clients);
        list.setAdapter(adapter);

        return rootView;
    }

    public void ajouterClient(View view) {
        EditText editPrenom = (EditText) getView().findViewById(R.id.PrenomEditText);
        String prenom = editPrenom.getText().toString();
        EditText editNom = (EditText) getView().findViewById(R.id.NomEditText);
        String nom = editNom.getText().toString();
        EditText editAdresse = (EditText) getView().findViewById(R.id.AdresseEditText);
        String adresse = editAdresse.getText().toString();
        EditText editEmail = (EditText) getView().findViewById(R.id.EmailEditText);
        String email = editEmail.getText().toString();
        EditText editTelephone = (EditText) getView().findViewById(R.id.TelephoneEditText);
        String telephone = editTelephone.getText().toString();

        if (prenom != null && prenom.length() > 0 && nom != null && nom.length() > 0 && adresse != null && adresse.length() > 0 &&
                ((email != null && email.length() > 0) || (telephone != null && telephone.length() > 0))
                && (this.femmeButton.isChecked() || this.hommeButton.isChecked() || this.autreButton.isChecked())) {
            this.adapterNomClients.add(prenom + " " + nom);

            editPrenom.setText("");
            editNom.setText("");
            editAdresse.setText("");
            editEmail.setText("");
            editTelephone.setText("");
            this.femmeButton.setChecked(false);
            this.hommeButton.setChecked(false);
            this.autreButton.setChecked(false);
        } else {
            Toast.makeText(getActivity(), "Veuillez remplir le formulaire", Toast.LENGTH_LONG)
                    .show();
        }
    }
}
