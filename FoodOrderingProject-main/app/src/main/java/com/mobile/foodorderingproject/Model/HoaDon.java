package com.mobile.foodorderingproject.Model;

public class HoaDon {
    int maHoaDon, maNhanVien, maBan, tongTien;
    String thoiGian;

    public HoaDon() {
    }

    public int getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(int maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public int getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(int maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public int getMaBan() {
        return maBan;
    }

    public void setMaBan(int maBan) {
        this.maBan = maBan;
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }

    public String getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(String thoiGian) {
        this.thoiGian = thoiGian;
    }

    public HoaDon(int mahoadon, int manhanvien, int maban, int tongtien, String thoigian) {
        this.maHoaDon = mahoadon;
        this.maNhanVien = manhanvien;
        this.maBan = maban;
        this.tongTien = tongtien;
        this.thoiGian = thoigian;
    }
}
