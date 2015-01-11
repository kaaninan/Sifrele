package com.example.kaaninan.sifrele.pager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.kaaninan.sifrele.Mesaj;
import com.example.kaaninan.sifrele.R;
import com.example.kaaninan.sifrele.adapter.MesajlarAdapter;
import com.example.kaaninan.sifrele.constructor.MesajConstructor;

import java.util.ArrayList;

public class Mesajlar extends Fragment {

    private ListView listMesajlar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.mesajlar, container, false);

        listMesajlar = (ListView) rootView.findViewById(R.id.listviewMesaj);

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

        listMesajlar.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE_MODAL);

        listMesajlar.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
                mode.setTitle(listMesajlar.getCheckedItemCount()+" Mesaj");
            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Window window = getActivity().getWindow();
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                    window.setStatusBarColor(getResources().getColor(android.R.color.holo_red_dark));
                }
                MenuInflater inflater = mode.getMenuInflater();
                inflater.inflate(R.menu.context_menu, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return true;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.action_sil) {

                    long[] hangisi = listMesajlar.getCheckedItemIds();

                    mode.finish();
                    return true;
                }

                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Window window = getActivity().getWindow();
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                    window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
                }
            }
        });


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
}