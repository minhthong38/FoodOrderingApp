package com.mobile.foodorderingproject.Model;

public class Table {
    String trangThai;
    int maBan;

    public Table(String trangthai, int maban) {
        this.trangThai = trangthai;
        this.maBan = maban;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public Table() {
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public int getMaBan() {
        return maBan;
    }

    public void setMaBan(int maBan) {
        this.maBan = maBan;
    }
}
