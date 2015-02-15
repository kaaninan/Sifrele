package com.example.kaaninan.sifrele;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

import com.example.kaaninan.sifrele.constructor.MesajConstructor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Kaaninan on 15/02/15.
 */
public class SmsYonet {

    Context context;

    public SmsYonet(Context context) {
        this.context = context;
    }





    public ArrayList<MesajConstructor> getKonusmalar() {

        ArrayList<MesajConstructor> results = new ArrayList<>();
        //String WHERE_CONDITION = true ? SMS_READ_COLUMN + " = 0" : null;
        String SORT_ORDER = "date DESC";

        Cursor cursor = context.getContentResolver().query(Uri.parse("content://mms-sms/conversations"), new String[] { "_id", "thread_id", "address", "person", "date", "body", "read" }, null, null, SORT_ORDER);

        if (cursor != null) {
            try {
                while (cursor.moveToNext()) {

                    long messageId = cursor.getLong(0); // id sütunu saklanır
                    //long threadId = cursor.getLong(1); // thread id saklanır
                    String address = cursor.getString(2); // gönderen adresi saklanır
                    long contactId = cursor.getLong(3); // kişi id’si saklanır (adres defteriyle ilişkili)
                    long timestamp = cursor.getLong(4); // gönderilen zaman saklanır

                    String body = cursor.getString(5); // mesaj içeriği saklanır

                    long read = cursor.getLong(6);

                    boolean okundu = true;

                    if (read == 0){
                        okundu = false;
                    }

                    Date dateFromSms = new Date(timestamp);
                    DateFormat df = new SimpleDateFormat("HH:mm");
                    String tarih = df.format(dateFromSms);

                    MesajConstructor mesajConstructor = new MesajConstructor(messageId, getKisi(contactId, address), address, tarih, 0, okundu, false, body);
                    results.add(mesajConstructor);
                }
            } finally {
                cursor.close();
            }
        }
        return results;
    }


    public String getKisi(long id, String gelenNumara){

        String isim = "";

        Uri CONTENT_URI = ContactsContract.Contacts.CONTENT_URI;
        String _ID = ContactsContract.Contacts._ID;
        String DISPLAY_NAME = ContactsContract.Contacts.DISPLAY_NAME;
        String HAS_PHONE_NUMBER = ContactsContract.Contacts.HAS_PHONE_NUMBER;

        Uri PhoneCONTENT_URI = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String Phone_CONTACT_ID = ContactsContract.CommonDataKinds.Phone.CONTACT_ID;
        String NUMBER = ContactsContract.CommonDataKinds.Phone.NUMBER;

        ContentResolver contentResolver = context.getContentResolver();

        Cursor cursor = contentResolver.query(CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone._ID +" = ?",  new String[]{String.valueOf(id)}, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC");

        if (cursor.getCount() > 0) {

            while (cursor.moveToNext()) {

                String contact_id = cursor.getString(cursor.getColumnIndex( _ID ));
                String name = cursor.getString(cursor.getColumnIndex( DISPLAY_NAME ));

                int hasPhoneNumber = Integer.parseInt(cursor.getString(cursor.getColumnIndex( HAS_PHONE_NUMBER )));

                if (hasPhoneNumber > 0) {

                    isim = name;

                    Cursor phoneCursor = contentResolver.query(PhoneCONTENT_URI, null, Phone_CONTACT_ID + " = ?", new String[] { contact_id }, null);

                    while (phoneCursor.moveToNext()) {
                        String phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(NUMBER));

                        break;
                    }

                    phoneCursor.close();

                }
            }
        }else{
            isim = gelenNumara;
        }

        return isim;
    }


    public void okunduIsaretle(long id){
        ContentValues values = new ContentValues();
        values.put("read",true);
        context.getContentResolver().update(Uri.parse("content://sms/inbox"),values, "_id="+id, null);
    }

}
