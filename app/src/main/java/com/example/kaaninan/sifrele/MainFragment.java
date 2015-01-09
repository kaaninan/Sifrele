package com.example.kaaninan.sifrele;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kaaninan.sifrele.animation.ZoomOutPageTransformer;
import com.example.kaaninan.sifrele.pager.Rehber;
import com.example.kaaninan.sifrele.pager.Mesajlar;

public class MainFragment extends Fragment{

    private Fragment fragment;

    public static Fragment newInstance(Context context) {
        MainFragment f = new MainFragment();
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.hesap_main, null);

        ViewPager pager = (ViewPager)root.findViewById(R.id.pager);
        pager.setPageTransformer(true, new ZoomOutPageTransformer());

        FragmentManager fm = getActivity().getSupportFragmentManager();


        pager.setAdapter(new FragmentStatePagerAdapter(fm) {

            public int getCount() {
                return 2;
            }

            public Fragment getItem(int position) {
                switch(position){
                    case 0:
                        return fragment = new Mesajlar();
                    case 1:
                        Fragment fragment = new Rehber();
                        return fragment;
                }
                return null;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                CharSequence isim;
                if(position == 0){
                    isim = getResources().getString(R.string.viewPager_mesaj);
                }else
                    isim = getResources().getString(R.string.viewPager_arkadas);
                return isim;
            }
        });

        return root;
    }

}