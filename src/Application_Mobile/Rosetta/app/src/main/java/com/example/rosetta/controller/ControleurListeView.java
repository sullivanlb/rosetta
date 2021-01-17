package com.example.rosetta.controller;

import android.view.View;
import android.widget.AdapterView;

import com.example.rosetta.R;

public class ControleurListeView implements AdapterView.OnItemClickListener {

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        view.setBackgroundColor(0xFFF8CBAD);
    }
}
