package com.example.kaaninan.sifrele.arkadaslar;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.example.kaaninan.sifrele.Adapter;
import com.example.kaaninan.sifrele.R;
import com.example.kaaninan.sifrele.RehberConstructor;

import java.util.ArrayList;
import java.util.List;

public class Arkadaslar extends Fragment {

    String LOG = "Arkadaslar.java";
    Boolean listBos;

    public static final String ARG_SECTION_NUMBER = "section_number";

    public Arkadaslar() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.arkadaslar, container, false);

        ListView list = (ListView) rootView.findViewById(R.id.listRehber);
        FrameLayout layoutBos = (FrameLayout) rootView.findViewById(R.id.layoutBos);

        ArrayList<RehberConstructor> rehber = rehberiGetir();

        Adapter adapter = new Adapter(getActivity(), R.layout.rehber_item, rehber);

        List<String> testler= new ArrayList<String>();

        testler.add("merhaba");
        testler.add("dünya");

        ArrayAdapter<String> test = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, testler);

        list.setAdapter(test);

        if (listBos){
            list.setVisibility(View.GONE);
            layoutBos.setVisibility(View.VISIBLE);
        }

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i(LOG,"tıklandı");
            }
        });

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i(LOG,"uzun tıklandı");
                return false;
            }
        });

        return rootView;
    }

    private ArrayList<RehberConstructor> rehberiGetir(){

        RehberConstructor rehberConstructor = null;

        ArrayList<RehberConstructor> arrayList = new ArrayList<RehberConstructor>();

        Uri CONTENT_URI = ContactsContract.Contacts.CONTENT_URI;
        String _ID = ContactsContract.Contacts._ID;
        String DISPLAY_NAME = ContactsContract.Contacts.DISPLAY_NAME;
        String HAS_PHONE_NUMBER = ContactsContract.Contacts.HAS_PHONE_NUMBER;

        Uri PhoneCONTENT_URI = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String Phone_CONTACT_ID = ContactsContract.CommonDataKinds.Phone.CONTACT_ID;
        String NUMBER = ContactsContract.CommonDataKinds.Phone.NUMBER;

        ContentResolver contentResolver = getActivity().getContentResolver();

        Cursor cursor = contentResolver.query(CONTENT_URI, null,null, null, null);

        if (cursor.getCount() > 0) {

            while (cursor.moveToNext()) {

                String contact_id = cursor.getString(cursor.getColumnIndex( _ID ));
                String name = cursor.getString(cursor.getColumnIndex( DISPLAY_NAME ));

                int hasPhoneNumber = Integer.parseInt(cursor.getString(cursor.getColumnIndex( HAS_PHONE_NUMBER )));

                if (hasPhoneNumber > 0) {
                    listBos = false;

                    rehberConstructor = new RehberConstructor();
                    rehberConstructor.setIsim(name);
                    Log.i(LOG, name);
                    Log.i(LOG, "geld,");

                    Cursor phoneCursor = contentResolver.query(PhoneCONTENT_URI, null, Phone_CONTACT_ID + " = ?", new String[] { contact_id }, null);

                    while (phoneCursor.moveToNext()) {
                        String phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(NUMBER));
                        rehberConstructor.setNumara(phoneNumber);
                        break;
                    }

                    phoneCursor.close();

                    arrayList.add(rehberConstructor);

                }else {
                    listBos = true;
                }
            }
        }else{
            listBos = true;
        }

        return arrayList;
    }
}