package com.example.kaaninan.sifrele;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class Mesaj extends Activity {

    ListView smsListView;
    ArrayAdapter<String> arrayAdapter;


    public ArrayList<String> getAllSmsInbox(boolean unreadOnly) {

        ArrayList<String> results = new ArrayList<String>();
        String SMS_READ_COLUMN = "read";
        String WHERE_CONDITION = unreadOnly ? SMS_READ_COLUMN + " = 0" : null;
        String SORT_ORDER = "date DESC";
        int count = 0;

        ContentResolver contentResolver = getContentResolver();
        final String[] projection = new String[]{"*"};
        Uri uri = Uri.parse("content://mms-sms/conversations/");
        Cursor cursor = contentResolver.query(uri, projection, null, null, null);


        //Cursor cursor = getContentResolver().query(Uri.parse("content://sms/inbox"),
        //new String[] { "_id", "thread_id", "address", "person", "date", "body" }, WHERE_CONDITION, null, SORT_ORDER);

        if (cursor != null) {
            try {
                while (cursor.moveToNext()) {

                    Toast.makeText(getApplicationContext(), "Merhaba bu bir", Toast.LENGTH_SHORT).show();

                    long messageId = cursor.getLong(0); // id sütunu saklanır
                    long threadId = cursor.getLong(1); // thread id saklanır
                    String address = cursor.getString(2); // gönderen adresi saklanır
                    long contactId = cursor.getLong(3); // kişi id’si saklanır (adres defteriyle ilişkili)
                    String contactId_string = String.valueOf(contactId);
                    long timestamp = cursor.getLong(4); // gönderilen zaman saklanır

                    String body = cursor.getString(5); // mesaj içeriği saklanır

                    if (!unreadOnly) {
                        count = 0;
                    }

                    //SMSManager smsMessage = new SMSManager(getApplicationContext(), address,contactId_string, body, timestamp, threadId, count,messageId);
                    results.add(body);

                }
            } finally {
                cursor.close(); // işimiz bitince cursor kapanmalı yoksa memory leak hatası alırız
            }
        }
        return results; // cevabı döndürüyoruz
    }



    public void refreshSmsInbox() {
        ContentResolver contentResolver = getContentResolver();
        Uri uri = Uri.parse("content://sms/inbox");
        String[] reqCols = new String[] { "_id", "address", "body" };
        Cursor smsInboxCursor = contentResolver.query(uri, reqCols, null, null, null);

        int indexBody = smsInboxCursor.getColumnIndex("body");
        int indexAddress = smsInboxCursor.getColumnIndex("address");
        if (indexBody < 0 || !smsInboxCursor.moveToFirst()) return;
        arrayAdapter.clear();
        do {
            String str = "SMS From: " + smsInboxCursor.getString(indexAddress) + "\n" + smsInboxCursor.getString(indexBody) + "\n";
            arrayAdapter.add(str);
            Log.i("Mesaj","Deneme");
        } while (smsInboxCursor.moveToNext());
        //smsInboxCursor.close();
    }

    public void updateList(final String smsMessage) {
        arrayAdapter.insert(smsMessage, 0);
        arrayAdapter.notifyDataSetChanged();
    }
















    private Integer renk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mesaj);

        getActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        Integer renk_id = intent.getIntExtra("renk_id",0);
        String isim = intent.getStringExtra("isim");

        Log.i("renk_id",String.valueOf(renk_id));
        Log.i("renk blue",String.valueOf(R.drawable.yuvarlak_blue));

        switch (renk_id){
            case R.drawable.yuvarlak_blue:
                renk = R.color.blue;
                break;
            case R.drawable.yuvarlak_gray:
                renk = R.color.gray;
                break;
            case R.drawable.yuvarlak_green:
                renk = R.color.green;
                break;
            case R.drawable.yuvarlak_orange:
                renk = R.color.orange;
                break;
            case R.drawable.yuvarlak_red:
                renk = R.color.red;
                break;
            case R.drawable.yuvarlak_yellow:
                renk = R.color.yellow;
                break;
        }

        getActionBar().setBackgroundDrawable(getResources().getDrawable(renk));
        getActionBar().setTitle((Html.fromHtml("<font color=\"#ffffff\">" + isim + "</font>")));

        setContentView(R.layout.activity_sms);
        smsListView = (ListView) findViewById(R.id.SMSList);
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getAllSmsInbox(false));
        smsListView.setAdapter(arrayAdapter);

        //refreshSmsInbox();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_mesaj, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
