package com.example.kaaninan.sifrele;

import android.content.Context;

/**
 * Created by Kaaninan on 14/02/15.
 */
public class SMSManager {

    Context context;
    String address; // gönderen adresi
    String contactId_string; // gönderen Id
    String body; // mesaj içeriği
    long timestamp; // gönderilme tarihi
    long threadId; // eğer aynı kişiden birden fazla mesaj alınmışsa android bu mesajlar bir dizi halinde tutar ve id olarak bu değeri verir
    int count; // kaçıncı mesaj
    long messageId; // mesaj Id – android her mesaja özel bir Id atar

    public SMSManager(Context context, String address, String contactId_string, String body, long timestamp, long threadId, int count, long messageId) {
        this.context = context;
        this.address = address;
        this.contactId_string = contactId_string;
        this.body = body;
        this.timestamp = timestamp;
        this.threadId = threadId;
        this.count = count;
        this.messageId = messageId;
    }
}
