package com.example.kaaninan.sifrele.pager;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.example.kaaninan.sifrele.Mesaj;
import com.example.kaaninan.sifrele.R;
import com.example.kaaninan.sifrele.adapter.MesajlarAdapter;
import com.example.kaaninan.sifrele.constructor.MesajConstructor;
import com.fortysevendeg.swipelistview.SwipeListView;

import java.util.ArrayList;

public class Mesajlar extends Fragment {

    private SwipeListView listMesajlar;

    public Drawable renk;

    public Mesajlar() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.mesajlar, container, false);

        listMesajlar = (SwipeListView) rootView.findViewById(R.id.listViewMesaj);

        final ArrayList<MesajConstructor> mesajlar = new ArrayList<MesajConstructor>();

        MesajConstructor mesajConstructor = new MesajConstructor();
        mesajConstructor.setIsim("Ceyhun ÜNAL");
        mesajConstructor.setNumara("05052748075");
        mesajlar.add(mesajConstructor);
        MesajConstructor mesajConstructor2 = new MesajConstructor();
        mesajConstructor2.setIsim("Osman AZ");
        mesajConstructor2.setNumara("05052748075");
        mesajlar.add(mesajConstructor2);
        MesajConstructor mesajConstructor3 = new MesajConstructor();
        mesajConstructor3.setIsim("Leyla AKPINAR");
        mesajConstructor3.setNumara("05052748075");
        mesajlar.add(mesajConstructor3);
        MesajConstructor mesajConstructor4 = new MesajConstructor();
        mesajConstructor4.setIsim("Orhan DEDE");
        mesajConstructor4.setNumara("05052748075");
        mesajlar.add(mesajConstructor4);
        MesajConstructor mesajConstructor5 = new MesajConstructor();
        mesajConstructor5.setIsim("Rıza TEPELİ");
        mesajConstructor5.setNumara("05052748075");
        mesajlar.add(mesajConstructor5);

        MesajlarAdapter adapter = new MesajlarAdapter(getActivity(), mesajlar);

        listMesajlar.setAdapter(adapter);



        listMesajlar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), Mesaj.class);

                MesajConstructor mesaj = (MesajConstructor) view.getTag();

                intent.putExtra("renk_id",mesaj.getRenk());
                intent.putExtra("isim",mesaj.getIsim());

                startActivity(intent);
            }
        });

        return rootView;
    }
}// END Tek MainFragment