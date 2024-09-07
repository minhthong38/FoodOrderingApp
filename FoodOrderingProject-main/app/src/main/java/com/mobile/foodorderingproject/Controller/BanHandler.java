package com.mobile.foodorderingproject.Controller;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.mobile.foodorderingproject.Model.Table;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class BanHandler extends SQLiteOpenHelper {

    public static final String DB_NAME = "qlch";
    @SuppressLint("SdCardPath")
    private static final String PATH = "/data/data/com.mobile.foodorderingproject/database/FoodOrdering.db";
    private static final String TABLE_NAME = "Ban";
    private static final String MABAN_COL = "MaBan";
    private static final String TRANGTHAI_COL = "TrangThai";

    public BanHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_NAME, factory, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db = SQLiteDatabase.openDatabase(PATH, null,SQLiteDatabase.CREATE_IF_NECESSARY);
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + MABAN_COL +" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, " + TRANGTHAI_COL + " TEXT NOT NULL)";
        db.execSQL(sql);
        db.close();
    }
    public void initData(){
        SQLiteDatabase db = SQLiteDatabase.openDatabase(PATH,null,SQLiteDatabase.CREATE_IF_NECESSARY);
        String sql1 = "INSERT OR IGNORE INTO " + TABLE_NAME + "("+ MABAN_COL +","+TRANGTHAI_COL+") VALUES (1,'Available')";
        db.execSQL(sql1);
        String sql2 = "INSERT OR IGNORE INTO " + TABLE_NAME + "("+ MABAN_COL +","+TRANGTHAI_COL+") VALUES (2,'Unavailable')";
        db.execSQL(sql2);
        String sql3 = "INSERT OR IGNORE INTO " + TABLE_NAME + "("+ MABAN_COL +","+TRANGTHAI_COL+") VALUES (3,'Available')";
        db.execSQL(sql3);
        String sql4 = "INSERT OR IGNORE INTO " + TABLE_NAME + "("+ MABAN_COL +","+TRANGTHAI_COL+") VALUES (4,'Reserved')";
        db.execSQL(sql4);
        String sql5 = "INSERT OR IGNORE INTO " + TABLE_NAME + "("+ MABAN_COL +","+TRANGTHAI_COL+") VALUES (5,'Reserved')";
        db.execSQL(sql5);
    }
    public Table banSearch(int maBan, ArrayList<Table> lsTable){
        for(Table a: lsTable)
            if(a.getMaBan() == maBan)
                return a;
        return null;
    }
    public void insertData(int maBan, String trangthai){
        SQLiteDatabase db = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.OPEN_READWRITE);
        ContentValues values = new ContentValues();
        values.put(MABAN_COL, maBan);
        values.put(TRANGTHAI_COL, trangthai);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }
    public void updateData(Table oldTB, Table newTB)
    {
        SQLiteDatabase db=SQLiteDatabase.openDatabase(PATH,null,SQLiteDatabase.OPEN_READWRITE);
        ContentValues values=new ContentValues();
        values.put(MABAN_COL, newTB.getMaBan());
        values.put(TRANGTHAI_COL, newTB.getTrangThai());
        db.update(TABLE_NAME,values, MABAN_COL+"=?",
                new String[]{String.valueOf(oldTB.getMaBan())});
        db.close();
    }
    public void deleteData(int maBan) {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(PATH,null,SQLiteDatabase.OPEN_READWRITE);
        db.delete(TABLE_NAME, MABAN_COL + " =?",
                new String[]{String.valueOf(maBan)});
        db.close();
    }
    public static ArrayList<Table> loadData(){
        ArrayList<Table> kq = new ArrayList<>();
        SQLiteDatabase db = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME ,null);
        cursor.moveToFirst();
        do {
            Table tb = new Table();
            tb.setMaBan(cursor.getInt(0));
            tb.setTrangThai(cursor.getString(1));
            kq.add(tb);
        }while (cursor.moveToNext());
        return kq;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

