package com.example.rosetta.ui.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.rosetta.MainActivity;
import com.example.rosetta.R;
import com.example.rosetta.model.Client;
import com.example.rosetta.model.Sexe;

import java.util.ArrayList;

public class ListeClients extends ArrayAdapter<Client> {

    private ArrayList<Client> listeClients;
    private Context context;
    private LayoutInflater inflater;

    public ListeClients(@NonNull Context context, int resource) {
        super(context, resource);
        this.listeClients = new ArrayList<Client>();
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    public ArrayList<Client> getListeClients() {
        return this.listeClients;
    }

    @Override
    public int getCount() { return this.listeClients.size(); }

    @Override
    public Client getItem(int position) { return this.listeClients.get(position); }

    @Override
    public long getItemId(int position) { return position; }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;

        EditText editPrenom = (EditText) parent.getRootView().findViewById(R.id.PrenomEditText);
        String prenom = editPrenom.getText().toString();
        EditText editNom = (EditText) parent.getRootView().findViewById(R.id.NomEditText);
        String nom = editNom.getText().toString();
        EditText editAdresse = (EditText) parent.getRootView().findViewById(R.id.AdresseEditText);
        String adresse = editAdresse.getText().toString();
        EditText editEmail = (EditText) parent.getRootView().findViewById(R.id.EmailEditText);
        String email = editEmail.getText().toString();
        EditText editTelephone = (EditText) parent.getRootView().findViewById(R.id.TelephoneEditText);
        String telephone = editTelephone.getText().toString();

        if (convertView == null) {
            view = (View) inflater.inflate(R.layout.client_item, parent, false);
        } else {
            view = (View) convertView;
        }

        TextView nomView = (TextView) view.findViewById(R.id.itemClientNom);
        TextView prenomView = (TextView) view.findViewById(R.id.itemClientPrenom);

        return view;
    }
}
