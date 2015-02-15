package com.example.kaaninan.sifrele.constructor;


public class MesajConstructor {

    long id;
    String isim;
    String numara;
    String tarih;
    int renk;
    boolean okundu;
    boolean fav;
    String son_mesaj;

    public MesajConstructor(long id, String isim, String numara, String tarih, int renk, boolean okundu, boolean fav, String son_mesaj) {
        this.id = id;
        this.isim = isim;
        this.numara = numara;
        this.tarih = tarih;
        this.renk = renk;
        this.okundu = okundu;
        this.fav = fav;
        this.son_mesaj = son_mesaj;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

    public String getNumara() {
        return numara;
    }

    public void setNumara(String numara) {
        this.numara = numara;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }

    public int getRenk() {
        return renk;
    }

    public void setRenk(int renk) {
        this.renk = renk;
    }

    public boolean isOkundu() {
        return okundu;
    }

    public void setOkundu(boolean okundu) {
        this.okundu = okundu;
    }

    public boolean isFav() {
        return fav;
    }

    public void setFav(boolean fav) {
        this.fav = fav;
    }

    public String getSon_mesaj() {
        return son_mesaj;
    }

    public void setSon_mesaj(String son_mesaj) {
        this.son_mesaj = son_mesaj;
    }
}