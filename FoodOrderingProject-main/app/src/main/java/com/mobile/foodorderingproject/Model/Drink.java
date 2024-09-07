package com.mobile.foodorderingproject.Model;

public class Drink {

    int maDrink, giaDrink;
    String tenDrink, imgDrink;

    public Drink() {
    }

    public Drink(int madrink, int giadrink, String tendrink, String imgdrink) {
        this.maDrink = madrink;
        this.giaDrink = giadrink;
        this.tenDrink = tendrink;
        this.imgDrink = imgdrink;
    }

    public String getImgDrink() {
        return imgDrink;
    }

    public void setImgDrink(String imgDrink) {
        this.imgDrink = imgDrink;
    }

    public int getMaDrink() {
        return maDrink;
    }

    public void setMaDrink(int maDrink) {
        this.maDrink = maDrink;
    }

    public int getGiaDrink() {
        return giaDrink;
    }

    public void setGiaDrink(int giaDrink) {
        this.giaDrink = giaDrink;
    }

    public String getTenDrink() {
        return tenDrink;
    }

    public void setTenDrink(String tenDrink) {
        this.tenDrink = tenDrink;
    }



}
