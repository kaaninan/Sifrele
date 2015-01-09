package com.example.kaaninan.sifrele.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.kaaninan.sifrele.R;

import java.util.List;

/**
 * Created by Kaaninan on 8.01.15.
 */

public class ExAdapter extends BaseAdapter {

    private Context context;
    private List<String> list;
    private int layoutResourceId;

    public ExAdapter(Context context, int layoutResourceId, List<String> list) {
        super();
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.list = list;
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

        TextView isim = (TextView) view.findViewById(R.id.textMesaj);

        isim.setText(list.get(position));

        return view;

    }

}
