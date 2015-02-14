package com.example.kaaninan.sifrele.pager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.telephony.SmsManager;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kaaninan.sifrele.Mesaj;
import com.example.kaaninan.sifrele.R;
import com.example.kaaninan.sifrele.adapter.MesajlarAdapter;
import com.example.kaaninan.sifrele.constructor.MesajConstructor;

import java.util.ArrayList;

public class Mesajlar extends Fragment {

    private ListView listMesajlar;
    private ImageButton ekleButton;
    private TextView textEkle;
    private RelativeLayout frameMesaj;
    private TextView textUyari;
    private Button gonder;

    private EditText editKime;
    private EditText editMesaj;

    private RelativeLayout layoutSearchEdit;
    private EditText editSearch;
    private boolean search_open = false;

    public boolean acik = false; // Mesaj Ekleme Kısmı

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.mesajlar, container, false);

        //ekleButton = (ImageButton) rootView.findViewById(R.id.imageButtonEkle);
        listMesajlar = (ListView) rootView.findViewById(R.id.listviewMesaj);
        //textEkle = (TextView) rootView.findViewById(R.id.textEkleButton);
        frameMesaj = (RelativeLayout) rootView.findViewById(R.id.layoutMesajYaz);
        textUyari = (TextView) rootView.findViewById(R.id.textMesajYazUyari);
        gonder = (Button) rootView.findViewById(R.id.buttonMesajGonder);

        editMesaj = (EditText) rootView.findViewById(R.id.editMesaj);
        editKime = (EditText) rootView.findViewById(R.id.editKime);

        layoutSearchEdit = (RelativeLayout) rootView.findViewById(R.id.layoutEditMesajArama);
        editSearch = (EditText) rootView.findViewById(R.id.editMesajArama);

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


        /*
        ekleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!acik) {
                    mesajGonderAc();
                }else {
                    mesajGonderKapat();
                }
            }
        });
        */


        gonder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String kime = editKime.getText().toString();
                String mesaj = editMesaj.getText().toString();

                if (kime.equals("")){
                    textUyari.setVisibility(View.VISIBLE);
                }else{
                    textUyari.setVisibility(View.GONE);
                    mesajGonderKapat();
                    smsGonder(kime, mesaj);
                }

            }
        });

        return rootView;
    }




    public void mesajGonderAc(){
        listMesajlar.setEnabled(false);
        SlideToDown();
        dondur();
        acik = true;
    }

    public void mesajGonderKapat(){
        listMesajlar.setEnabled(true);
        SlideToUp();
        dondur_ters();
        acik = false;
        editKime.setText("");
        editMesaj.setText("");
    }




    public void dondur_ters(){
        Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate_ters);
        animation.setFillAfter(true);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //textEkle.setRotation(45);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public void dondur(){

        Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate);
        animation.setFillAfter(true);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //textEkle.setRotation(45);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }



    public void SlideToUp() {
        Animation slide = null;
        slide = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                0.0f, Animation.RELATIVE_TO_SELF, -1);

        slide.setDuration(400);
        slide.setFillAfter(true);
        slide.setFillEnabled(true);
        frameMesaj.startAnimation(slide);

        slide.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                frameMesaj.clearAnimation();

                RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(frameMesaj.getWidth(), frameMesaj.getHeight());
                lp.setMargins(0,-frameMesaj.getHeight(),0,0);
                frameMesaj.setLayoutParams(lp);

            }

        });

    }

    public void SlideToDown() {
        Animation slide = null;
        slide = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                0.0f, Animation.RELATIVE_TO_SELF, 1);

        slide.setDuration(400);
        slide.setFillAfter(true);
        slide.setFillEnabled(true);
        frameMesaj.startAnimation(slide);

        slide.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                frameMesaj.clearAnimation();

                RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(frameMesaj.getWidth(), frameMesaj.getHeight());
                lp.setMargins(0,0,0,0);
                frameMesaj.setLayoutParams(lp);

            }

        });

    }








    public void SearchToUp() {
        search_open = false;
        Animation slide = null;
        slide = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                0.0f, Animation.RELATIVE_TO_SELF, -1);

        slide.setDuration(200);
        slide.setFillAfter(true);
        slide.setFillEnabled(true);
        layoutSearchEdit.startAnimation(slide);
        listMesajlar.startAnimation(slide);

        slide.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                editSearch.setText("");

                layoutSearchEdit.clearAnimation();
                listMesajlar.clearAnimation();

                RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(layoutSearchEdit.getWidth(), layoutSearchEdit.getHeight());
                lp.setMargins(0, -(layoutSearchEdit.getHeight()), 0, 0);
                layoutSearchEdit.setLayoutParams(lp);

            }

        });

    }

    public void SearchToDown() {
        search_open = true;
        Animation slide = null;
        slide = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                0.0f, Animation.RELATIVE_TO_SELF, 1);

        slide.setDuration(200);
        slide.setFillAfter(true);
        slide.setFillEnabled(true);
        layoutSearchEdit.startAnimation(slide);
        listMesajlar.startAnimation(slide);

        slide.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                layoutSearchEdit.clearAnimation();
                listMesajlar.clearAnimation();

                RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(layoutSearchEdit.getWidth(), layoutSearchEdit.getHeight());
                lp.setMargins(0, 0, 0, 0);
                lp.addRule(RelativeLayout.ALIGN_PARENT_TOP);
                layoutSearchEdit.setLayoutParams(lp);

            }

        });

    }

    public boolean isSearch_open() {
        return search_open;
    }


    protected void smsGonder(String phoneNo, String message) {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, message, null, null);
            Toast.makeText(getActivity(), "SMS sent.",Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(getActivity(),"SMS faild, please try again.",Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
}