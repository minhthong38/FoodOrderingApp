package com.mobile.foodorderingproject.Model;

public class Food {
    int maFood, giaFood;
    String tenFood, imgFood;

    public Food(int mafood, int giafood, String tenfood, String imgfood) {
        this.maFood = mafood;
        this.giaFood = giafood;
        this.tenFood = tenfood;
        this.imgFood = imgfood;
    }

    public String getImgFood() {
        return imgFood;
    }

    public void setImgFood(String imgFood) {
        this.imgFood = imgFood;
    }

    public int getMaFood() {
        return maFood;
    }

    public void setMaFood(int maFood) {
        this.maFood = maFood;
    }

    public int getGiaFood() {
        return giaFood;
    }

    public void setGiaFood(int giaFood) {
        this.giaFood = giaFood;
    }

    public String getTenFood() {
        return tenFood;
    }

    public void setTenFood(String tenFood) {
        this.tenFood = tenFood;
    }

    public Food() {
    }

}
