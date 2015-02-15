package com.example.kaaninan.sifrele.constructor;

import android.content.Context;

/**
 * Created by Kaaninan on 14/02/15.
 */
public class SmsConstructor {

    Context context;
    long messageId;
    long threadId;
    String address;
    long contactId;
    String contactId_string;
    long timestamp;
    int count;
    String body;

    public SmsConstructor(Context context, long messageId, long threadId, String address, long contactId, String contactId_string, long timestamp, int count, String body) {
        this.context = context;
        this.messageId = messageId;
        this.threadId = threadId;
        this.address = address;
        this.contactId = contactId;
        this.contactId_string = contactId_string;
        this.timestamp = timestamp;
        this.count = count;
        this.body = body;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public long getMessageId() {
        return messageId;
    }

    public void setMessageId(long messageId) {
        this.messageId = messageId;
    }

    public long getThreadId() {
        return threadId;
    }

    public void setThreadId(long threadId) {
        this.threadId = threadId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getContactId() {
        return contactId;
    }

    public void setContactId(long contactId) {
        this.contactId = contactId;
    }

    public String getContactId_string() {
        return contactId_string;
    }

    public void setContactId_string(String contactId_string) {
        this.contactId_string = contactId_string;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
