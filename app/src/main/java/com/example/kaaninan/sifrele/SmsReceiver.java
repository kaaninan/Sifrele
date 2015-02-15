/*
 * Copyright 2014 Jacob Klinker
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.kaaninan.sifrele;

import android.annotation.TargetApi;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

/**
 * Needed to make default sms app for testing
 */
public class SmsReceiver extends BroadcastReceiver {

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onReceive(Context context, Intent intent) {


        NotificationCompat.Builder  mBuilder = new NotificationCompat.Builder(context);
        mBuilder.setContentTitle("Mesaj Geldi HA");
        mBuilder.setContentText("Mesaja bağacaksan uygulaayı açacaksın evlat");
        mBuilder.setTicker("Burası nirde çıkay?");
        mBuilder.setSmallIcon(R.drawable.ic_launcher);

        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();

        String[] events = new String[3];

        events[0] = new String("1) Nenen sağa mesaj bıraktı");
        events[1] = new String("2) Markete git");
        events[2] = new String("3) Neneğe alışveriş yap tağammı");

        // Sets a title for the Inbox style big view

        inboxStyle.setBigContentTitle("Fazla merak ...");

        // Moves events into the big view

        for (int i=0; i < events.length; i++) {
            inboxStyle.addLine(events[i]);
        }

        mBuilder.setStyle(inboxStyle);

        mBuilder.setNumber(0);

        mBuilder.setAutoCancel(true);

        Intent resultIntent = new Intent("com.example.javacodegeeks.TEL_INTENT",
                Uri.parse("tel:123456789"));
        resultIntent.putExtra("from", "javacodegeeks");

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);

        stackBuilder.addParentStack(MainActivity.class);


        stackBuilder.addNextIntent(resultIntent);

        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_ONE_SHOT);

        mBuilder.setContentIntent(resultPendingIntent);

        NotificationManager myNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        myNotificationManager.notify(0, mBuilder.build());


    }

}
