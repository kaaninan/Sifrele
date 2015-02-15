package com.example.kaaninan.sifrele;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class Mesaj extends Activity {

    ListView smsListView;
    ArrayAdapter<String> arrayAdapter;


    private Integer renk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);

        getActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        int renk_id = intent.getIntExtra("renk_id",0);
        String isim = intent.getStringExtra("isim");
        long mesaj_id = intent.getLongExtra("mesaj_id", 0);
        String body = intent.getStringExtra("body");

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


        SmsYonet smsYonet = new SmsYonet(getApplicationContext());
        smsYonet.okunduIsaretle(mesaj_id);


        setContentView(R.layout.activity_conversation);
        //smsListView = (ListView) findViewById(R.id.SMSList);
        SmsYonet smsManager = new SmsYonet(getApplicationContext());
        //arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, smsManager.getGelenKutusuString(false));
        //smsListView.setAdapter(arrayAdapter);

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
