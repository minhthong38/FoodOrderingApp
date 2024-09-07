package com.mobile.foodorderingproject.Controller;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.mobile.foodorderingproject.Model.HoaDon;

import java.util.ArrayList;

public class HoaDonHandler extends SQLiteOpenHelper {
    public static final String DB_NAME = "qlch";
    @SuppressLint("SdCardPath")
    private static final String PATH = "/data/data/com.mobile.foodorderingproject/database/FoodOrdering.db";
    private static final String TABLE_NAME = "HoaDon";
    private static final String MAHOADON_COL = "MaHoaDon";
    private static final String MANHANVIEN_COL = "MaNhanVien";
    private static final String MABAN_COL = "MaBan";
    private static final String TONGTIEN_COL = "TongTien";
    private static final String TIME_COL = "ThoiGian";
    static final String TABLE_DATA_NHANVIEN = "NhanVien";
    static final String TABLE_DATA_BAN = "Ban";
    public HoaDonHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db = SQLiteDatabase.openDatabase(PATH,null,SQLiteDatabase.CREATE_IF_NECESSARY);
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + MAHOADON_COL +" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, " + MANHANVIEN_COL + " INTEGER NOT NULL REFERENCES "+TABLE_DATA_NHANVIEN+"("+MANHANVIEN_COL+")," + MABAN_COL + " INTEGER NOT NULL REFERENCES "+TABLE_DATA_BAN+"("+MABAN_COL+"),"+ TONGTIEN_COL+" INTEGER NOT NULL, "+TIME_COL+" TEXT NOT NULL)";
        db.execSQL(sql);
        db.close();
    }
    public void insertData(int maStaff, int maTable, int tongTien, String time){
        SQLiteDatabase db = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.OPEN_READWRITE);
        ContentValues values = new ContentValues();
        values.put(MANHANVIEN_COL, maStaff);
        values.put(MABAN_COL, maTable);
        values.put(TONGTIEN_COL, tongTien);
        values.put(TIME_COL, time);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }
    public HoaDon hoaDonSearch(int maHoaDon, ArrayList<HoaDon> lsHoaDon){
        for(HoaDon a: lsHoaDon)
            if(a.getMaHoaDon() == maHoaDon)
                return a;
        return null;
    }
    public void updateData(HoaDon oldHD, HoaDon newHD)
    {
        SQLiteDatabase db=SQLiteDatabase.openDatabase(PATH,null,SQLiteDatabase.OPEN_READWRITE);
        ContentValues values=new ContentValues();
        values.put(MAHOADON_COL, newHD.getMaHoaDon());
        values.put(MANHANVIEN_COL, newHD.getMaNhanVien());
        values.put(MABAN_COL, newHD.getMaBan());
        values.put(TONGTIEN_COL, newHD.getTongTien());
        values.put(TIME_COL, newHD.getThoiGian());
        db.update(TABLE_NAME,values, MAHOADON_COL+"=?",
                new String[]{String.valueOf(oldHD.getMaHoaDon())});
        db.close();
    }
    public void deleteData(int maHoaDon) {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(PATH,null,SQLiteDatabase.OPEN_READWRITE);
        db.delete(TABLE_NAME, MAHOADON_COL + " =?",
                new String[]{String.valueOf(maHoaDon)});
        db.close();
    }
    public ArrayList<HoaDon> loadData(){
        ArrayList<HoaDon> kq = new ArrayList<>();
        SQLiteDatabase db = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME,null);
        cursor.moveToFirst();
        do {
            HoaDon hd = new HoaDon();
            hd.setMaHoaDon(cursor.getInt(0));
            hd.setMaNhanVien(cursor.getInt(1));
            hd.setMaBan(cursor.getInt(2));
            hd.setTongTien(cursor.getInt(3));
            hd.setThoiGian(cursor.getString(4));
            kq.add(hd);
        }while (cursor.moveToNext());
        return kq;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
