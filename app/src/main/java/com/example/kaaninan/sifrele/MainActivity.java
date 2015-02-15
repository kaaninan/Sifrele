package com.example.kaaninan.sifrele;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Telephony;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.example.kaaninan.sifrele.animation.ZoomOutPageTransformer;
import com.example.kaaninan.sifrele.pager.Mesajlar;
import com.example.kaaninan.sifrele.pager.Rehber;

import java.util.List;


public class MainActivity extends FragmentActivity {

    private Fragment fragment;
    private ViewPager pager;

    private RelativeLayout layoutSearchEdit;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        getActionBar().setBackgroundDrawable(getResources().getDrawable(R.color.colorPrimary));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }
        getActionBar().setTitle((Html.fromHtml("<font color=\"#ffffff\">"+getString(R.string.app_name)+"</font>")));

        setContentView(R.layout.activity_main);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);




        //layoutSearchEdit.setVisibility(View.VISIBLE);


        pager = (ViewPager) findViewById(R.id.pager);
        pager.setPageTransformer(true, new ZoomOutPageTransformer());

        FragmentManager fm = getSupportFragmentManager();

        pager.setAdapter(new FragmentStatePagerAdapter(fm) {

            public int getCount() {
                return 2;
            }

            public Fragment getItem(int position) {
                switch(position){
                    case 0:
                        return fragment = new Mesajlar();
                    case 1:
                        return fragment = new Rehber();
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
        /*
        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        tx.replace(R.id.container, android.support.v4.app.Fragment.instantiate(MainActivity.this, "com.example.kaaninan.sifrele.MainFragment"));
        tx.commit();
        */

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_ekle) {

            /*
            List<Fragment> mesaj = getSupportFragmentManager().getFragments();
            Mesajlar fragment = (Mesajlar) mesaj.get(0);

            if (fragment.acik) {
                fragment.mesajGonderKapat();
            } else {
                fragment.mesajGonderAc();
            }
            */
            Intent intent = new Intent(Telephony.Sms.Intents.ACTION_CHANGE_DEFAULT);
            intent.putExtra(Telephony.Sms.Intents.EXTRA_PACKAGE_NAME, getPackageName());
            startActivity(intent);

            return true;


        } else if (id == R.id.action_search) {

            if (pager.getCurrentItem() == 0) {

                List<Fragment> mesaj = getSupportFragmentManager().getFragments();
                Mesajlar fragment = (Mesajlar) mesaj.get(0);

                if (fragment.isSearch_open() == true){ fragment.SearchToUp(); }
                else{ fragment.SearchToDown(); }

            }else if (pager.getCurrentItem() == 1) {

                List<Fragment> rehber = getSupportFragmentManager().getFragments();
                Rehber fragment = (Rehber) rehber.get(1);

                if (fragment.isSearch_open() == true){ fragment.SlideToUp(); }
                else{ fragment.SlideToDown(); }

            }
            return true;

        } else if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }



        return super.onOptionsItemSelected(item);
    }



}
