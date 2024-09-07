package com.mobile.foodorderingproject.Model;

public class ShoppingCart {
    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    String ten;
    int gia;

    public ShoppingCart(String ten, int gia, int soluong) {
        this.ten = ten;
        this.gia = gia;
        this.soluong = soluong;
    }

    public ShoppingCart() {
    }

    int soluong;
}
