package com.example.kaaninan.sifrele.database;

public class DatabaseContract {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME= "db";

    public static class Kayit {

        private Kayit() {}

        public static final String TABLE_NAME = "kayit";
        public static final String ID = "id";
        public static final String COLUMN_TEXT= "text";
        public static final String COLUMN_TARIH = "tarih";
        public static final String COLUMN_CHANGE = "change";
        public static final String COLUMN_SON_DUZEN = "son_duzen";
        public static final String COLUMN_RESIM = "resim";
        public static final String COLUMN_RESIM2 = "resim2";
    }
}