package com.example.kaaninan.sifrele.database;

public class DatabaseContract {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME= "db";

    public static class Hesap {

        private Hesap() {}

        public static final String TABLE_NAME = "hesap";
        public static final String ID = "id";
        public static final String COLUMN_ISIM= "isim";
        public static final String COLUMN_EPOSTA = "eposta";
        public static final String COLUMN_SIFRE = "sifre";
        public static final String COLUMN_RESIM = "resim";
        public static final String COLUMN_NUMARA = "numara";
    }

    public static class Mesajlar {

        private Mesajlar() {}

        public static final String TABLE_NAME = "mesajlar";
        public static final String ID = "id";
        public static final String COLUMN_MESAJ= "mesaj";
        public static final String COLUMN_VERICI = "verici";
        public static final String COLUMN_ALICI = "alici";
        public static final String COLUMN_TARIH = "tarih";

    }

}