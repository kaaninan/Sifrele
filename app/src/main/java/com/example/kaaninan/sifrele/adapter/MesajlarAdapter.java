package com.example.kaaninan.sifrele.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
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

    private Drawable[] test;

    private int a = 0; //Yuvarlağın rengi için

    public MesajlarAdapter(Context context, int layoutResourceId, List<String> list) {
        super();
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.list = list;

        test[0] = context.getResources().getDrawable(R.drawable.yuvarlak_blue);
        test[1] = context.getResources().getDrawable(R.drawable.yuvarlak_gray);
        test[2] = context.getResources().getDrawable(R.drawable.yuvarlak_green);
        test[3] = context.getResources().getDrawable(R.drawable.yuvarlak_orange);
        test[4] = context.getResources().getDrawable(R.drawable.yuvarlak_red);
        test[5] = context.getResources().getDrawable(R.drawable.yuvarlak_yellow);
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

        relativeLayout.setBackgroundDrawable(test[a]);
        a++;

        if (a == 5){
            a = 0;
        }

        mesaj.setText(list.get(position));

        return view;

    }

}
