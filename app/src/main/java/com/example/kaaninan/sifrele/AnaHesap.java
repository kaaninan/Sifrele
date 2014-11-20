package com.example.kaaninan.sifrele;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class AnaHesap extends Fragment {

    private ListView listMesajlar;

    public AnaHesap() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.hesap_ana, container, false);

        listMesajlar = (ListView) rootView.findViewById(R.id.listViewMesaj);

        ArrayList<String> mesajlar = new ArrayList<String>();
        mesajlar.add("Oo Bu Yeni Mesaj");
        mesajlar.add("Bu Daha Yeni Mesaj");
        mesajlar.add("Bu En Yenisi");
        mesajlar.add("Bundan Yenisi Yok");
        mesajlar.add("Bu En Eski");
        mesajlar.add("Bu Dışlanmış");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, mesajlar);
        listMesajlar.setAdapter(adapter);

        return rootView;
    }
}// END Tek Hesap