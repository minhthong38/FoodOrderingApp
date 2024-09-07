package com.mobile.foodorderingproject.Model;

public class Dessert {
    int maDessert, giaDessert;
    String tenDessert, imgDessert;

    public int getMaDessert() {
        return maDessert;
    }

    public Dessert(int madessert, int giadessert, String tendessert, String imgdessert) {
        this.maDessert = madessert;
        this.giaDessert = giadessert;
        this.tenDessert = tendessert;
        this.imgDessert = imgdessert;
    }

    public String getImgDessert() {
        return imgDessert;
    }

    public void setImgDessert(String imgDessert) {
        this.imgDessert = imgDessert;
    }

    public Dessert() {
    }

    public void setMaDessert(int maDessert) {
        this.maDessert = maDessert;
    }

    public int getGiaDessert() {
        return giaDessert;
    }

    public void setGiaDessert(int giaDessert) {
        this.giaDessert = giaDessert;
    }

    public String getTenDessert() {
        return tenDessert;
    }

    public void setTenDessert(String tenDessert) {
        this.tenDessert = tenDessert;
    }
}
