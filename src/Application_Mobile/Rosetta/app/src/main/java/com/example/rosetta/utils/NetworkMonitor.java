package com.example.rosetta.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import com.example.rosetta.controller.Controleur;
import com.example.rosetta.controller.ControleurEnregistrerNouveauClient;

/**
 * Cette classe gère la connexion du client informatique contenant cette application. Elle permet
 * d'essayer de synchroniser les données lors de la connexion à un réseau.
 *
 * @author Sullivan, Christophe
 */
public class NetworkMonitor extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Connection changed", Toast.LENGTH_LONG).show();

        if (checkNetworkConnection(context)) {
            Controleur.getInstance(context).updateLesClients();
        }
    }

    /**
     * Check whether the connection with the distant database can be established.
     *
     * @return true if we can connect us to the distant database, otherwise false
     */
    public boolean checkNetworkConnection (Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }
}