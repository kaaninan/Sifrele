package com.example.kaaninan.sifrele;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TekHesap extends Fragment {

    public static final String ARG_SECTION_NUMBER = "section_number";

    public TekHesap() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.hesap_tek, container, false);
        return rootView;
    }
}