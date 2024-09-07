package com.mobile.foodorderingproject.Model;

public class Combo {
    int maCombo, maDrink;
    int maFood, maDessert;

    int giaCombo;
    String tenCombo;

    String imgCombo;

    public Combo() {
    }

    public Combo(int macombo, int mafood, int madrink, int madessert, int giacombo, String tencombo, String imgcombo) {
        this.maCombo = macombo;
        this.maFood = mafood;
        this.maDrink = madrink;
        this.maDessert = madessert;
        this.giaCombo = giacombo;
        this.tenCombo = tencombo;
        this.imgCombo = imgcombo;
    }

    public String getImgCombo() {
        return imgCombo;
    }

    public void setImgCombo(String imgCombo) {
        this.imgCombo = imgCombo;
    }

    public int getMaCombo() {
        return maCombo;
    }

    public void setMaCombo(int maCombo) {
        this.maCombo = maCombo;
    }

    public int getMaFood() {
        return maFood;
    }

    public void setMaFood(int maFood) {
        this.maFood = maFood;
    }

    public int getMaDrink() {
        return maDrink;
    }

    public void setMaDrink(int maDrink) {
        this.maDrink = maDrink;
    }

    public int getMaDessert() {
        return maDessert;
    }

    public void setMaDessert(int maDessert) {
        this.maDessert = maDessert;
    }

    public int getGiaCombo() {
        return giaCombo;
    }

    public void setGiaCombo(int giaCombo) {
        this.giaCombo = giaCombo;
    }

    public String getTenCombo() {
        return tenCombo;
    }

    public void setTenCombo(String tenCombo) {
        this.tenCombo = tenCombo;
    }
}
