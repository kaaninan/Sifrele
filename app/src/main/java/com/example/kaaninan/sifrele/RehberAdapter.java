package com.example.kaaninan.sifrele;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

public class RehberAdapter extends BaseAdapter implements StickyListHeadersAdapter {

    public ArrayList<String> ulkeler;
    private LayoutInflater inflater;

    private Context context;

    public RehberAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);

        ulkeler = new ArrayList<String>();

        String[] ulke = context.getResources().getStringArray(R.array.countries);
        for(String text : ulke){
            ulkeler.add(text);
        }
    }

    @Override
    public int getCount() {
        return ulkeler.size();
    }

    @Override
    public Object getItem(int position) {
        return ulkeler.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.test_list_item_layout, parent, false);
            holder.text = (TextView) convertView.findViewById(R.id.text1);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.text.setText(ulkeler.get(position));

        return convertView;
    }

    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        HeaderViewHolder holder;
        if (convertView == null) {
            holder = new HeaderViewHolder();
            convertView = inflater.inflate(R.layout.header, parent, false);
            holder.text = (TextView) convertView.findViewById(R.id.text2);
            convertView.setTag(holder);
        } else {
            holder = (HeaderViewHolder) convertView.getTag();
        }
        //set header text as first char in name
        String headerText = "" + ulkeler.get(position).subSequence(0, 1).charAt(0);
        holder.text.setText(headerText);
        holder.text.setTag("test");
        return convertView;
    }

    @Override
    public long getHeaderId(int position) {
        //return the first character of the country as ID because this is what headers are based upon
        return ulkeler.get(position).subSequence(0, 1).charAt(0);
    }

    class HeaderViewHolder {
        TextView text;
    }

    class ViewHolder {
        TextView text;
    }


    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        ulkeler.clear();
        if (charText.length() == 0) {

            String[] ulke = context.getResources().getStringArray(R.array.countries);
            for(String text : ulke){
                ulkeler.add(text);
            }
        }
        else
        {
            String[] ulke = context.getResources().getStringArray(R.array.countries);
            for(String foo : ulke){
                if (foo.toLowerCase(Locale.getDefault()).contains(charText))
                {
                    ulkeler.add(foo);
                }
            }
        }
        notifyDataSetChanged();
    }

}