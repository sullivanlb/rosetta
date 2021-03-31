package com.example.rosetta;

import android.os.Bundle;

import com.example.rosetta.model.Client;
import com.example.rosetta.ui.main.DevisFragment;
import com.google.android.material.tabs.TabLayout;

import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import com.example.rosetta.ui.main.SectionsPagerAdapter;

import java.util.ArrayList;

/**
 * Cette classe permet d'afficher l'application. Il n'y a qu'une seule activité, et plusieurs
 * fragments : Client, Scenario, Devis.
 *
 * @author Alice, Christophe
 * @version 2.0
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this,
                getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

    }
}