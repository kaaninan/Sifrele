package com.example.kaaninan.sifrele.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.kaaninan.sifrele.R;

import java.util.List;

/**
 * Created by Kaaninan on 09/01/15.
 */

public class MesajlarAdapter extends BaseAdapter {

    private Context context;
    private List<String> list;
    private int layoutResourceId;

    private int[] renkler = new int[6];

    private int a = 0; //Yuvarlağın rengi için

    public MesajlarAdapter(Context context, int layoutResourceId, List<String> list) {
        super();
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.list = list;

        renkler[0] = R.drawable.yuvarlak_blue;
        renkler[1] = R.drawable.yuvarlak_gray;
        renkler[2] = R.drawable.yuvarlak_green;
        renkler[3] = R.drawable.yuvarlak_orange;
        renkler[4] = R.drawable.yuvarlak_red;
        renkler[5] = R.drawable.yuvarlak_yellow;
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

    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if(view==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layoutResourceId, null);
        }

        RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.layoutMesajlarYuvarlak);
        TextView mesaj = (TextView) view.findViewById(R.id.textMesaj);
        TextView bas_harf = (TextView) view.findViewById(R.id.bas_harf);

        String bas_harfi = "" + list.get(position).subSequence(0, 1).charAt(0);

        relativeLayout.setBackgroundResource(renkler[a]);
        a++;

        if (a == 5){
            a = 0;
        }

        mesaj.setText(list.get(position));
        bas_harf.setText(bas_harfi);

        return view;

    }

}
