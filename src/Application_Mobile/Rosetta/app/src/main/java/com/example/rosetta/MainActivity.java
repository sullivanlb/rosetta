package com.example.rosetta;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import com.example.rosetta.ui.main.SectionsPagerAdapter;


/**
 * Cette classe permet d'afficher l'application. Il n'y a qu'une seule activité, et plusieurs
 * fragments : Client, Scenario, Devis.
 *
 * @author Alice, Christophe
 * @version 2.0
 */
public class MainActivity extends AppCompatActivity {

    private static MainActivity instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.instance = this;
        setContentView(R.layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this,
                getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

    }

    /**
     * Cette méthode permet de rafraîchir les fragments en tournant les interfaces automatiquement
     */
    public static void refreshFrag(){
        instance.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        instance.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }
}