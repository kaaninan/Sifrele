package com.example.kaaninan.sifrele.pager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.kaaninan.sifrele.Mesaj;
import com.example.kaaninan.sifrele.R;
import com.example.kaaninan.sifrele.adapter.MesajlarAdapter;

import java.util.ArrayList;

public class Mesajlar extends Fragment {

    private ListView listMesajlar;

    public Mesajlar() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.mesajlar, container, false);

        listMesajlar = (ListView) rootView.findViewById(R.id.listViewMesaj);

        ArrayList<String> mesajlar = new ArrayList<String>();
        mesajlar.add("Oo Bu Yeni Mesaj");
        mesajlar.add("Bu Daha Yeni Mesaj");
        mesajlar.add("Bu En Yenisi");
        mesajlar.add("Bundan Yenisi Yok");
        mesajlar.add("Bu En Eski");
        mesajlar.add("Bu Dışlanmış");

        MesajlarAdapter adapter = new MesajlarAdapter(getActivity(), R.layout.list_mesajlar, mesajlar);

        listMesajlar.setAdapter(adapter);

        listMesajlar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), Mesaj.class);
                startActivity(intent);
            }
        });

        return rootView;
    }
}// END Tek MainFragment