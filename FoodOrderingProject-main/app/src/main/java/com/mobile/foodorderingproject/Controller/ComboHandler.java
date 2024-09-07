package com.mobile.foodorderingproject.Controller;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.mobile.foodorderingproject.Model.Combo;
import com.mobile.foodorderingproject.Model.Dessert;

import java.util.ArrayList;
import java.util.Objects;

public class ComboHandler extends SQLiteOpenHelper {
    public static final String DB_NAME = "qlch";
    @SuppressLint("SdCardPath")
    private static final String PATH = "/data/data/com.mobile.foodorderingproject/database/FoodOrdering.db";
    private static final String TABLE_NAME = "Combo";
    private static final String MACOMBO_COL = "MaCombo";
    private static final String TENCOMBO_COL = "TenCombo";
    private static final String GIACOMBO_COL = "GiaCombo";
    private static final String MADOAN_COL = "MaFood";
    private static final String MADOUONG_COL = "MaDrink";
    private static final String MATRANGMIENG_COL = "MaDessert";
    private static final String IMAGECOMBO_COL = "ImgCombo";
    static final String TABLE_DATA_FOOD = "Food";
    static final String TABLE_DATA_DRINK = "Drink";
    static final String TABLE_DATA_DESSERT = "Dessert";
    public ComboHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_NAME, factory, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db = SQLiteDatabase.openDatabase(PATH, null,SQLiteDatabase.CREATE_IF_NECESSARY);
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ( ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " + MACOMBO_COL +" INTEGER NOT NULL UNIQUE, " + TENCOMBO_COL + " TEXT NOT NULL UNIQUE, " + GIACOMBO_COL + " INTEGER NOT NULL, " + MADOAN_COL + " INTERGER NOT NULL REFERENCES Food("+MADOAN_COL+"), " + MADOUONG_COL + " INTERGER NOT NULL REFERENCES Drink("+MADOUONG_COL+"), "+ MATRANGMIENG_COL + " INTERGER  NOT NULL REFERENCES Dessert("+MATRANGMIENG_COL+"), "+IMAGECOMBO_COL+" TEXT NOT NULL)";
        db.execSQL(sql);
        db.close();
    }
    public void insertData(int maStaff, String tenCombo, int giaCombo, int maFood, int maDrink, int maDessert){
        SQLiteDatabase db = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.OPEN_READWRITE);
        ContentValues values = new ContentValues();
        values.put(MACOMBO_COL, maStaff);
        values.put(TENCOMBO_COL, tenCombo);
        values.put(GIACOMBO_COL, giaCombo);
        values.put(MADOAN_COL, maFood);
        values.put(MADOUONG_COL, maDrink);
        values.put(MATRANGMIENG_COL, maDessert);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }
    public void initData(){
        SQLiteDatabase db = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        String sql1 = "INSERT OR IGNORE INTO " + TABLE_NAME + "("+MACOMBO_COL+","+TENCOMBO_COL+
                ","+ GIACOMBO_COL+","+ MADOAN_COL+","+ MADOUONG_COL+
                ","+ MATRANGMIENG_COL+","+ IMAGECOMBO_COL+") VALUES (1,'Combo hủy diệt',1700000,6,'null',3,'pizzacombo')";
        db.execSQL(sql1);
        String sql2 = "INSERT OR IGNORE INTO " + TABLE_NAME + "("+MACOMBO_COL+","+TENCOMBO_COL+
                ","+ GIACOMBO_COL+","+ MADOAN_COL+","+ MADOUONG_COL+
                ","+ MATRANGMIENG_COL+","+ IMAGECOMBO_COL+") VALUES (2,'Combo thông tài',4800000, 1, 4,'null','ratatouille')";
        db.execSQL(sql2);
        String sql3 = "INSERT OR IGNORE INTO " + TABLE_NAME + "("+MACOMBO_COL+","+TENCOMBO_COL+
                ","+ GIACOMBO_COL+","+ MADOAN_COL+","+ MADOUONG_COL+
                ","+ MATRANGMIENG_COL+","+ IMAGECOMBO_COL+") VALUES (3,'Combo khổng lồ',2200000, 7,5,3,'beefwellcombo')";
        db.execSQL(sql3);
        String sql4 = "INSERT OR IGNORE INTO " + TABLE_NAME + "("+MACOMBO_COL+","+TENCOMBO_COL+
                ","+ GIACOMBO_COL+","+ MADOAN_COL+","+ MADOUONG_COL+
                ","+ MATRANGMIENG_COL+","+ IMAGECOMBO_COL+") VALUES (4,'Combo double cheese',2200000, 8,3,7,'burgercombo')";
        db.execSQL(sql4);
//        String sql3 = "INSERT OR IGNORE INTO " + TABLE_NAME + "("+MACOMBO_COL+ "," +TENCOMBO_COL+
//                ","+ GIACOMBO_COL+","+ MADOAN_COL+","+ MADOUONG_COL+
//                ","+ MATRANGMIENG_COL+","+ IMAGECOMBO_COL+") VALUES (3,'Combo vạn thọ',110000,1,3,null,null)";
//        db.execSQL(sql3);
        db.close();
    }
    public void updateData(Combo oldCB, Combo newCB)
    {
        SQLiteDatabase db=SQLiteDatabase.openDatabase(PATH,null,SQLiteDatabase.OPEN_READWRITE);
        ContentValues values=new ContentValues();
        values.put(MACOMBO_COL, newCB.getMaCombo());
        values.put(TENCOMBO_COL, newCB.getTenCombo());
        values.put(GIACOMBO_COL, newCB.getGiaCombo());
        values.put(MADOAN_COL, newCB.getMaFood());
        values.put(MADOUONG_COL, newCB.getMaDrink());
        values.put(MATRANGMIENG_COL, newCB.getMaDessert());
        values.put(IMAGECOMBO_COL, newCB.getImgCombo());
        db.update(TABLE_NAME,values, MATRANGMIENG_COL+"=?",
                new String[]{String.valueOf(oldCB.getMaDessert())});
        db.close();
    }
    public void deleteData(int maCombo) {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(PATH,null,SQLiteDatabase.OPEN_READWRITE);
        db.delete(TABLE_NAME, MACOMBO_COL + " =?",
                new String[]{String.valueOf(maCombo)});
        db.close();
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public static ArrayList<Combo> loadData(){
        ArrayList<Combo> kq = new ArrayList<>();
        SQLiteDatabase db = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME,null);
        cursor.moveToFirst();
        do {
            Combo cb = new Combo();
            cb.setMaCombo(cursor.getInt(1));
            cb.setTenCombo(cursor.getString(2));
            cb.setGiaCombo(cursor.getInt(3));
            cb.setMaFood(cursor.getInt(4));
            cb.setMaDrink(cursor.getInt(5));
            cb.setMaDessert(cursor.getInt(6));
            cb.setImgCombo(cursor.getString(7));
            kq.add(cb);
        }while (cursor.moveToNext());
        return kq;
    }
    public String descFoodCombo(int maFood, int maCombo){
        String result = "";
        String temp2 = "";
        int i = 0;
        SQLiteDatabase db = SQLiteDatabase.openDatabase(PATH, null,SQLiteDatabase.CREATE_IF_NECESSARY);
        Cursor cursor = db.rawQuery("SELECT TenFood FROM " + TABLE_DATA_FOOD + ','+ TABLE_NAME +  " WHERE "+TABLE_DATA_FOOD+"."+MADOAN_COL+" = ? and "+TABLE_NAME+"."+MACOMBO_COL+" = ?", new String[]{String.valueOf(maFood), String.valueOf(maCombo)});
        cursor.moveToFirst();
        do{
            String temp1 = cursor.getString(0);
            if (temp1 != null){
                i = 1;
            }
            if(Objects.equals(temp1, temp2))
                i += 1;
            result = i + " " + temp1;
            temp2 = temp1;
        }while(cursor.moveToNext());
        return result;
    }
    public String descDrinkCombo(int maDrink, int maCombo){
        String result = "";
        String temp2 = "";
        int i = 0;
        SQLiteDatabase db = SQLiteDatabase.openDatabase(PATH, null,SQLiteDatabase.CREATE_IF_NECESSARY);
        Cursor cursor = db.rawQuery("SELECT TenDrink FROM " + TABLE_DATA_DRINK + ','+ TABLE_NAME +  " WHERE "+TABLE_DATA_DRINK+"."+MADOUONG_COL+" = ? and "+TABLE_NAME+"."+MACOMBO_COL+" = ?", new String[]{String.valueOf(maDrink), String.valueOf(maCombo)});
        cursor.moveToFirst();
        do{
            String temp1 = cursor.getString(0);
            if (temp1 != null){
                i = 1;
            }
            if(Objects.equals(temp1, temp2))
                i += 1;
            result = i + " " + temp1;
            temp2 = temp1;
        }while(cursor.moveToNext());
        return result;
    }
    public String descDessertCombo(int maDessert, int maCombo){
        String result = "";
        String temp2 = "";
        int i = 0;
        SQLiteDatabase db = SQLiteDatabase.openDatabase(PATH, null,SQLiteDatabase.CREATE_IF_NECESSARY);
        Cursor cursor = db.rawQuery("SELECT TenDessert FROM " + TABLE_DATA_DESSERT + ','+ TABLE_NAME +  " WHERE "+TABLE_DATA_DESSERT+"."+MATRANGMIENG_COL+" = ? and "+TABLE_NAME+"."+MACOMBO_COL+" = ?", new String[]{String.valueOf(maDessert), String.valueOf(maCombo)});
        cursor.moveToFirst();
        do{
            String temp1 = cursor.getString(0);
            if (temp1 != null){
                i = 1;
            }
            if(Objects.equals(temp1, temp2))
                i += 1;
            result = i + " " + temp1;
            temp2 = temp1;
        }while(cursor.moveToNext());
        return result;
    }
}
