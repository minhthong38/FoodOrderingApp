package com.mobile.foodorderingproject.Controller;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.Editable;

import androidx.annotation.Nullable;

import com.mobile.foodorderingproject.Model.Staff;

import java.util.ArrayList;

public class StaffHandler extends SQLiteOpenHelper {
    public static final String DB_NAME = "qlch";
    @SuppressLint("SdCardPath")
    private static final String PATH = "/data/data/com.mobile.foodorderingproject/database/FoodOrdering.db";
    private static final String TABLE_NAME = "NhanVien";
    private static final String MANHANVIEN_COL = "MaNhanVien";
    private static final String TENNHANVIEN_COL = "TenNhanVien";
    private static final String TAIKHOAN_COL = "TaiKhoan";
    private static final String MATKHAU_COL = "MatKhau";
    public StaffHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_NAME, factory, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db = SQLiteDatabase.openDatabase(PATH, null,SQLiteDatabase.CREATE_IF_NECESSARY);
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + MANHANVIEN_COL +" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, " + TENNHANVIEN_COL + " TEXT NOT NULL UNIQUE, " + TAIKHOAN_COL + " INTEGER NOT NULL," + MATKHAU_COL +" INTEGER NOT NULL)";
        db.execSQL(sql);
        db.close();
    }
    public void initData(){
        SQLiteDatabase db = SQLiteDatabase.openDatabase(PATH,null,SQLiteDatabase.OPEN_READWRITE);
        String sql1 = "INSERT OR IGNORE INTO " + TABLE_NAME + " ("+TENNHANVIEN_COL+","+TAIKHOAN_COL+","+MATKHAU_COL+") VALUES ('Lưu Tiến Tài','tientai','123456')";
        db.execSQL(sql1);
        String sql2 = "INSERT OR IGNORE INTO " + TABLE_NAME + " ("+TENNHANVIEN_COL+","+TAIKHOAN_COL+","+MATKHAU_COL+") VALUES ('Admin','admin','justadmin')";
        db.execSQL(sql2);
    }
    public void insertData(String tenStaff, String taikhoan, String matkhau){
        SQLiteDatabase db = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.OPEN_READWRITE);
        ContentValues values = new ContentValues();
        values.put(TENNHANVIEN_COL, tenStaff);
        values.put(TAIKHOAN_COL, taikhoan);
        values.put(MATKHAU_COL, matkhau);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }
    public Staff staffSearch(String taikhoan, ArrayList<Staff> lsStaff){
        for (Staff a: lsStaff)
            if(a.getTaiKhoan().equals(taikhoan))
                return a;
        return null;
    }
    public boolean confirmLogIn(String taikhoan, String matkhau, ArrayList<Staff> lsStaff){
        for(Staff a: lsStaff)
            if(a.getTaiKhoan().equals(taikhoan) && a.getMatKhau().equals(matkhau))
                return true;
        return false;
    }
    public void updateData(Staff oldS, Staff newS)
    {
        SQLiteDatabase db=SQLiteDatabase.openDatabase(PATH,null,SQLiteDatabase.OPEN_READWRITE);
        ContentValues values=new ContentValues();
        values.put(MANHANVIEN_COL, newS.getMaNv());
        values.put(TENNHANVIEN_COL, newS.getTenNv());
        values.put(TAIKHOAN_COL, newS.getTaiKhoan());
        values.put(MATKHAU_COL, newS.getMatKhau());
        db.update(TABLE_NAME,values, MANHANVIEN_COL+"=?",
                new String[]{String.valueOf(oldS.getMaNv())});
        db.close();
    }
    public void deleteData(int maStaff) {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(PATH,null,SQLiteDatabase.OPEN_READWRITE);
        db.delete(TABLE_NAME, MANHANVIEN_COL + " =?",
                new String[]{String.valueOf(maStaff)});
        db.close();
    }
    public ArrayList<Staff> loadData(){
        ArrayList<Staff> kq = new ArrayList<>();
        SQLiteDatabase db = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME,null);
        cursor.moveToFirst();
        do {
            Staff tb = new Staff();
            tb.setMaNv(cursor.getInt(0));
            tb.setTenNv(cursor.getString(1));
            tb.setTaiKhoan(cursor.getString(2));
            tb.setMatKhau(cursor.getString(3));
            kq.add(tb);
        }while (cursor.moveToNext());
        return kq;
    }
    public void findStaff(){

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
