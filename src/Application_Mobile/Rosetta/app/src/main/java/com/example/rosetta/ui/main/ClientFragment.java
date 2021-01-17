package com.example.rosetta.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.rosetta.R;
import com.example.rosetta.controller.ControleurEnregistrerClient;
import com.example.rosetta.controller.ControleurListeView;

import java.util.ArrayList;

public class ClientFragment extends Fragment {

    private ArrayList<String> listeNomClients;
    private ArrayAdapter<String> adapterNomClients;
    private ListView listeViewClients;
    private Button enregistrerButton;
    private int positionItemSelectionne;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_client_layout, container, false);

        this.listeViewClients = (ListView) rootView.findViewById(R.id.listView_clients);
        //ControleurListeView controleurListeView = new ControleurListeView();
        //this.listeViewClients.setOnItemClickListener(controleurListeView);

        this.enregistrerButton = (Button) rootView.findViewById(R.id.EnregistrerButton);
        ControleurEnregistrerClient controleurEnregistrerClient =
                new ControleurEnregistrerClient(this);
        this.enregistrerButton.setOnClickListener(controleurEnregistrerClient);

        this.listeNomClients = new ArrayList<String>();
        this.adapterNomClients = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1, this.listeNomClients);
        this.listeViewClients.setAdapter(this.adapterNomClients);

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

        if (prenom != null && prenom.length() > 0 && nom != null && nom.length() > 0 && adresse !=
                null && adresse.length() > 0 && ((email != null && email.length() > 0) || (telephone
                != null && telephone.length() > 0))) {
            this.adapterNomClients.add(prenom + " " + nom);
            editPrenom.setText("");
            editNom.setText("");
            editAdresse.setText("");
            editEmail.setText("");
            editTelephone.setText("");
        } else {
            Toast.makeText(getActivity(), "Veuillez remplir le formulaire", Toast.LENGTH_LONG)
                    .show();
        }
    }
}
