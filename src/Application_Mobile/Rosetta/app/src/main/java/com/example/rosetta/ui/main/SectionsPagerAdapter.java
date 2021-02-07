package com.example.rosetta.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.rosetta.R;

/**
 * Cette classe est un adapteur de pages, elle renvoie un fragment correspondant à une des
 * sections/onglets/pages : Client, Scenario, Devis.
 *
 * @author Alice, Christophe
 * @version 2.0
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2, R.string.tab_text_3};
    private final Context mContext;

    /**
     * Le constructeur crée une nouvelle forme de SectionPagerAdapter.
     *
     * @param context le contexte
     * @param fm le manager de fragments
     */
    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {

        // Retourne le fragment correspondant au lieu où se trouve l'utilisateur
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new ClientFragment();
                break;
            case 1:
                fragment = new ScenarioFragment();
                break;
            case 2:
                fragment = new DevisFragment();
                break;
        }

        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        // Retourne le titre de la page où se trouve l'utilisateur
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Montre au total 3 pages
        return 3;
    }
}