package com.example.kaaninan.sifrele.constructor;


public class MesajConstructor {

    public int id;
    public String isim;
    public String numara;
    public int renk;
    public boolean fav;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getRenk() {
        return renk;
    }

    public void setRenk(int renk) {
        this.renk = renk;
    }

    public boolean isFav() {
        return fav;
    }

    public void setFav(boolean fav) {
        this.fav = fav;
    }
}