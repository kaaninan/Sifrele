package com.example.kaaninan.sifrele.adapter;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.kaaninan.sifrele.R;
import com.example.kaaninan.sifrele.constructor.RehberConstructor;

import java.util.ArrayList;
import java.util.Locale;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

public class RehberAdapter extends BaseAdapter implements StickyListHeadersAdapter {

    private LayoutInflater inflater;

    private ArrayList<RehberConstructor> rehber;

    private Context context;

    public RehberAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        rehber = rehberiGetir();
    }

    @Override
    public int getCount() {
        return rehber.size();
    }

    @Override
    public Object getItem(int position) {
        return rehber.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_rehber_item, parent, false);
        }

        TextView isim = (TextView) convertView.findViewById(R.id.textRehberIsim);
        TextView numara = (TextView) convertView.findViewById(R.id.textRehberNumara);

        RehberConstructor rehberConstructor = rehber.get(position);

        isim.setText(rehberConstructor.getIsim());
        numara.setText(rehberConstructor.getNumara());

        return convertView;
    }

    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        HeaderViewHolder holder;
        if (convertView == null) {
            holder = new HeaderViewHolder();
            convertView = inflater.inflate(R.layout.list_rehber_header, parent, false);
            holder.text = (TextView) convertView.findViewById(R.id.text2);
            convertView.setTag(holder);
        } else {
            holder = (HeaderViewHolder) convertView.getTag();
        }
        RehberConstructor rehberConstructor = rehber.get(position);
        String headerText = "" + rehberConstructor.getIsim().subSequence(0, 1).charAt(0);
        holder.text.setText(headerText);
        return convertView;
    }

    @Override
    public long getHeaderId(int position) {
        RehberConstructor rehberConstructor = rehber.get(position);
        return rehberConstructor.getIsim().subSequence(0, 1).charAt(0);
    }

    class HeaderViewHolder {
        TextView text;
    }

    class ViewHolder {
        TextView text;
    }


    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        rehber.clear();
        if (charText.length() == 0) {

            rehber = rehberiGetir();
        }
        else {
            ArrayList<RehberConstructor> rehber2 = rehberiGetir();

            for(RehberConstructor foo : rehber2){
                if (foo.getIsim().toLowerCase(Locale.getDefault()).contains(charText)) {
                    rehber.add(foo);
                }
            }
        }
        notifyDataSetChanged();
    }

    public Boolean listBos;

    public ArrayList<RehberConstructor> rehberiGetir(){

        RehberConstructor rehberConstructor = null;

        ArrayList<RehberConstructor> arrayList = new ArrayList<RehberConstructor>();

        Uri CONTENT_URI = ContactsContract.Contacts.CONTENT_URI;
        String _ID = ContactsContract.Contacts._ID;
        String DISPLAY_NAME = ContactsContract.Contacts.DISPLAY_NAME;
        String HAS_PHONE_NUMBER = ContactsContract.Contacts.HAS_PHONE_NUMBER;

        Uri PhoneCONTENT_URI = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String Phone_CONTACT_ID = ContactsContract.CommonDataKinds.Phone.CONTACT_ID;
        String NUMBER = ContactsContract.CommonDataKinds.Phone.NUMBER;

        ContentResolver contentResolver = context.getContentResolver();

        Cursor cursor = contentResolver.query(CONTENT_URI, null, null,  null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC");

        if (cursor.getCount() > 0) {

            while (cursor.moveToNext()) {

                String contact_id = cursor.getString(cursor.getColumnIndex( _ID ));
                String name = cursor.getString(cursor.getColumnIndex( DISPLAY_NAME ));

                int hasPhoneNumber = Integer.parseInt(cursor.getString(cursor.getColumnIndex( HAS_PHONE_NUMBER )));

                if (hasPhoneNumber > 0) {
                    listBos = false;

                    rehberConstructor = new RehberConstructor();
                    rehberConstructor.setIsim(name);
                    rehberConstructor.setId(contact_id);

                    Cursor phoneCursor = contentResolver.query(PhoneCONTENT_URI, null, null, null, null);

                    while (phoneCursor.moveToNext()) {
                        String phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(NUMBER));
                        rehberConstructor.setNumara(phoneNumber);
                        break;
                    }
                    phoneCursor.close();
                    arrayList.add(rehberConstructor);
                }
            }
        }else{
            listBos = true;
        }

        return arrayList;
    }

}