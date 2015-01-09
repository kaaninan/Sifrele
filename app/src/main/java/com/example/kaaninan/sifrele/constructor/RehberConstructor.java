package com.example.kaaninan.sifrele.constructor;


public class RehberConstructor {

    public int id;
    public String isim;
    public String numara;
    public boolean fav;

    public void setId(int id) {
        this.id = id;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

    public void setNumara(String numara) {
        this.numara = numara;
    }

    public void setFav(boolean fav) {
        this.fav = fav;
    }

    public int getId() {
        return id;
    }

    public String getIsim() {
        return isim;
    }

    public String getNumara() {
        return numara;
    }

    public boolean isFav() {
        return fav;
    }
}