package com.example.kaaninan.sifrele.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.kaaninan.sifrele.R;
import com.example.kaaninan.sifrele.constructor.MesajConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kaaninan on 09/01/15.
 */

public class MesajlarAdapter extends BaseAdapter {

    private Context context;
    private List<MesajConstructor> list;
    private LayoutInflater inflater;

    private int[] renkler = new int[6];

    private int lastPosition = 1;
    private int a; //Yuvarlağın rengi için

    public MesajlarAdapter(Context context, ArrayList<MesajConstructor> list) {
        super();
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.list = list;

        renkler[0] = R.drawable.yuvarlak_blue;
        renkler[1] = R.drawable.yuvarlak_gray;
        renkler[2] = R.drawable.yuvarlak_green;
        renkler[3] = R.drawable.yuvarlak_orange;
        renkler[4] = R.drawable.yuvarlak_red;
        renkler[5] = R.drawable.yuvarlak_yellow;

        a = 0;
    }

    public int getCount() {
        if(list.size() == 0)
            return 1;
        return list.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View view, ViewGroup parent) {

        if(position == 0)
            a = 0;

        if(view==null){
            view = inflater.inflate(R.layout.list_mesajlar, parent, false);
        }

        MesajConstructor mesajConstructor = list.get(position);


        RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.layoutMesajlarYuvarlak);
        TextView mesaj = (TextView) view.findViewById(R.id.textMesaj);
        TextView bas_harf = (TextView) view.findViewById(R.id.bas_harf);

        String bas_harfi = "" + mesajConstructor.getIsim().subSequence(0, 1).charAt(0);

        relativeLayout.setBackgroundResource(renkler[a]);

        mesajConstructor.setRenk(renkler[a]);

        mesaj.setText(mesajConstructor.getIsim());
        bas_harf.setText(bas_harfi);
        view.setTag(mesajConstructor);
        parent.setTag(mesajConstructor.getIsim());

        a++;
        if (a == 5){ a = 0; }

        Animation animation = AnimationUtils.loadAnimation(context, R.anim.fade_in);
        animation.setDuration(300);
        view.startAnimation(animation);
        lastPosition = position;

        return view;

    }

    private void sifirla(){
        a = 0;
    }

}
