package com.example.rosetta.controller;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.rosetta.R;
import com.example.rosetta.model.Client;
import com.example.rosetta.model.Sexe;
import com.example.rosetta.ui.main.ClientFragment;
import com.example.rosetta.utils.MySingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.example.rosetta.model.Sexe.AUTRE;
import static com.example.rosetta.model.Sexe.FEMME;
import static com.example.rosetta.model.Sexe.HOMME;

/**
 * Cette classe permet d'ajouter un client et d'enregistrer les informations d'un client, ainsi que de les modifier, lorsque ce bouton Enregistrer est appuyé.
 * Elle permet également de vider les champs avec le bouton nouveau Client.
 *
 * @author Christophe, Lucy
 * @version 2.0
 */
public class ControleurEnregistrerNouveauClient implements View.OnClickListener {

    private ClientFragment clientFragment;


    /**
     * Le constructeur crée une nouvelle forme de ControleurEnregistrerNouveauClient.
     *
     * @param cf le contexte {@link ClientFragment}
     */
    public ControleurEnregistrerNouveauClient(ClientFragment cf) {
        this.clientFragment = cf;
    }

    @Override
    public void onClick(View v) {

        //On récupère le bouton sur lequel on clique (Enregistrer ou Nouveau Client)
        Button boutonSelectionne = (Button) v;

        //Récupération des informations du formulaire du client
        EditText editNom = (EditText) this.clientFragment.getView().findViewById(R.id.NomEditText);
        String nom = editNom.getText().toString();
        EditText editPrenom = (EditText) this.clientFragment.getView().findViewById(R.id.PrenomEditText);
        String prenom = editPrenom.getText().toString();
        EditText editAdresse = (EditText) this.clientFragment.getView().findViewById(R.id.AdresseEditText);
        String adresse = editAdresse.getText().toString();
        EditText editEmail = (EditText) this.clientFragment.getView().findViewById(R.id.EmailEditText);
        String email = editEmail.getText().toString();
        EditText editTelephone = (EditText) this.clientFragment.getView().findViewById(R.id.TelephoneEditText);
        String tel = editTelephone.getText().toString();
        CheckBox femmeButton = (CheckBox) this.clientFragment.getView().findViewById(R.id.femmeCheckbox);
        CheckBox hommeButton = (CheckBox) this.clientFragment.getView().findViewById(R.id.hommeCheckbox);
        CheckBox autreButton = (CheckBox) this.clientFragment.getView().findViewById(R.id.autreCheckbox);

        //Bouton Nouveau Client
        if (boutonSelectionne.getText().equals("Nouveau client")) {

            this.clientFragment.setIndiceSelectionner(-1);

            // N'affiche plus aucune donnée dans le formulaire
            editPrenom.setText("");
            editNom.setText("");
            editAdresse.setText("");
            editEmail.setText("");
            editTelephone.setText("");
            femmeButton.setChecked(false);
            hommeButton.setChecked(false);
            autreButton.setChecked(false);
        }
        else {

            //Bouton Enregistrer
            this.clientFragment.actualiserListeClients();
            ArrayList<Client> list = Controleur.getInstance(this.clientFragment.getContext()).getListeClients();

            //Modifier un Client
            if (this.clientFragment.getIndiceSelectionner() >= 0) {

                Client client = null;

                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getIdClient() == this.clientFragment.getIdClient()) {
                        client = list.get(i);
                    }
                }
                if (client != null) {
                    client.setNomClient(nom);
                    client.setPrenomClient(prenom);
                    client.setAdresseClient(adresse);
                    client.setEmailClient(email);
                    client.setTelClient(tel);

                    if (hommeButton.isChecked()) {
                        client.setSexeClient(Sexe.HOMME);
                    } else if (femmeButton.isChecked()) {
                        client.setSexeClient(Sexe.FEMME);
                    } else {
                        client.setSexeClient(Sexe.AUTRE);
                    }
                }

                Controleur.getInstance(this.clientFragment.getContext()).modifierClient(client);
                this.clientFragment.actualiserListeClients();

            } else {

                //Ajouter un Client

                // Si le nom, le prénom, l'adresse, le sexe sont bien renseignés, ainsi que l'email ou le
                // ou le numéro de téléphone, alors le client est ajouté/enregistré
                if (nom != null && nom.length() > 0 && prenom != null && prenom.length() > 0 && adresse != null && adresse.length() > 0 &&
                        ((email != null && email.length() > 0) || (tel != null && tel.length() > 0))
                        && (femmeButton.isChecked() || hommeButton.isChecked() || autreButton.isChecked())) {
                    if (checkNetworkConnection()) {
                        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://192.168.1.49/api/client/ajoutClient",
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        try {
                                            JSONObject jsonObject = new JSONObject(response);
                                            String theResponse = jsonObject.getString("response");

                                            // Si le client a bien été enregistré dans la base de données externe
                                            if (theResponse.equalsIgnoreCase("OK")) {
                                                Client client = new Client(0, 0, nom, prenom, adresse, ((email != null) ? email : ""), ((tel != null) ? tel : ""), ((femmeButton.isChecked()) ? FEMME :
                                                        ((hommeButton.isChecked()) ? HOMME : AUTRE)));
                                                Controleur.getInstance(ControleurEnregistrerNouveauClient.this.clientFragment.getContext()).creerClient(client);
                                                ControleurEnregistrerNouveauClient.this.clientFragment.actualiserListeClients();
                                            } else {
                                                Client client = new Client(0, 1, nom, prenom, adresse, ((email != null) ? email : ""), ((tel != null) ? tel : ""), ((femmeButton.isChecked()) ? FEMME :
                                                        ((hommeButton.isChecked()) ? HOMME : AUTRE)));
                                                Controleur.getInstance(ControleurEnregistrerNouveauClient.this.clientFragment.getContext()).creerClient(client);
                                                ControleurEnregistrerNouveauClient.this.clientFragment.actualiserListeClients();
                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Client client = new Client(0, 1, nom, prenom, adresse, ((email != null) ? email : ""), ((tel != null) ? tel : ""), ((femmeButton.isChecked()) ? FEMME :
                                        ((hommeButton.isChecked()) ? HOMME : AUTRE)));
                                Controleur.getInstance(ControleurEnregistrerNouveauClient.this.clientFragment.getContext()).creerClient(client);
                                ControleurEnregistrerNouveauClient.this.clientFragment.actualiserListeClients();
                            }
                        }) {
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String, String> params = new HashMap<>();
                                params.put("nom", nom);
                                params.put("prenom", prenom);
                                params.put("adresse", adresse);
                                params.put("email", ((email != null) ? email : ""));
                                params.put("tel", ((tel != null) ? tel : ""));
                                params.put("sexe", ((femmeButton.isChecked()) ? "femme" :
                                        ((hommeButton.isChecked()) ? "homme" : "autre")));

                                return params;
                            }
                        };

                        MySingleton.getInstance(this.clientFragment.getContext()).addToRequestQueue(stringRequest);
                    } else {
                        Client client = new Client(0, 1, nom, prenom, adresse, email != null ? email : "", tel != null ? tel : "", (femmeButton.isChecked()) ? FEMME :
                                (hommeButton.isChecked()) ? HOMME : AUTRE);
                        Controleur.getInstance(this.clientFragment.getContext()).creerClient(client);
                        this.clientFragment.actualiserListeClients();
                    }

                    // Vidage des champs
                    editPrenom.setText("");
                    editNom.setText("");
                    editAdresse.setText("");
                    editEmail.setText("");
                    editTelephone.setText("");
                    femmeButton.setChecked(false);
                    hommeButton.setChecked(false);
                    autreButton.setChecked(false);

                } else {

                    // Une information est envoyée à l'utilisateur lorsqu'il n'a pas saisi correctement
                    // les informations du formulaire
                    Toast.makeText(this.clientFragment.getView().getContext(), "Veuillez remplir le formulaire.", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    /**
     * Check whether the connection with the distant database can be established.
     *
     * @return true if we can connect us to the distant database, otherwise false
     */
    public boolean checkNetworkConnection() {
        ConnectivityManager connectivityManager = (ConnectivityManager) this.clientFragment.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    /**
     * @return le client fragment
     */
    public ClientFragment getClientFragment() {
        return clientFragment;
    }
}
