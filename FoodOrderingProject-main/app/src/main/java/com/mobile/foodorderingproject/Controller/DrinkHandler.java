package com.mobile.foodorderingproject.Controller;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.mobile.foodorderingproject.Model.Drink;

import java.util.ArrayList;

public class DrinkHandler extends SQLiteOpenHelper {
    public static final String DB_NAME = "qlch";
    @SuppressLint("SdCardPath")
    private static final String PATH = "/data/data/com.mobile.foodorderingproject/database/FoodOrdering.db";
    private static final String TABLE_NAME = "Drink";
    private static final String MADRINK_COL = "MaDrink";
    private static final String TENDRINK_COL = "TenDrink";
    private static final String GIADRINK_COL = "GiaDrink";
    private static final String IMAGEDRINK_COL = "ImageDrink";
    public DrinkHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_NAME, factory, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db = SQLiteDatabase.openDatabase(PATH, null,SQLiteDatabase.CREATE_IF_NECESSARY);
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + MADRINK_COL +" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, " + TENDRINK_COL + " TEXT NOT NULL UNIQUE, " + GIADRINK_COL + " INTEGER NOT NULL, "+ IMAGEDRINK_COL+" TEXT NOT NULL)";
        db.execSQL(sql);
        db.close();
    }
    public void initData(){
        SQLiteDatabase db = SQLiteDatabase.openDatabase(PATH,null,SQLiteDatabase.CREATE_IF_NECESSARY);
        String sql1 = "INSERT OR IGNORE INTO " + TABLE_NAME + "("+TENDRINK_COL+","+GIADRINK_COL+","+IMAGEDRINK_COL+") VALUES ('Soju',120000,'soju')";
        db.execSQL(sql1);
        String sql2 = "INSERT OR IGNORE INTO " + TABLE_NAME + "("+TENDRINK_COL+","+GIADRINK_COL+","+IMAGEDRINK_COL+") VALUES ('Vodka',500000,'vodka')";
        db.execSQL(sql2);
        String sql3 = "INSERT OR IGNORE INTO " + TABLE_NAME + "("+TENDRINK_COL+","+GIADRINK_COL+","+IMAGEDRINK_COL+") VALUES ('Pepsi Caramel',50000,'pepsicaramel')";
        db.execSQL(sql3);
        String sql4 = "INSERT OR IGNORE INTO " + TABLE_NAME + "("+TENDRINK_COL+","+GIADRINK_COL+","+IMAGEDRINK_COL+") VALUES ('Cocktail',150000,'cocktail')";
        db.execSQL(sql4);
        String sql5 = "INSERT OR IGNORE INTO " + TABLE_NAME + "("+TENDRINK_COL+","+GIADRINK_COL+","+IMAGEDRINK_COL+") VALUES ('Champagne',1500000,'champagne')";
        db.execSQL(sql5);
        String sql6 = "INSERT OR IGNORE INTO " + TABLE_NAME + "("+TENDRINK_COL+","+GIADRINK_COL+","+IMAGEDRINK_COL+") VALUES ('Red Dragon',25000,'rongdo')";
        db.execSQL(sql6);
        String sql7 = "INSERT OR IGNORE INTO " + TABLE_NAME + "("+TENDRINK_COL+","+GIADRINK_COL+","+IMAGEDRINK_COL+") VALUES ('Trà tắc',36000,'tratac')";
        db.execSQL(sql7);
    }
    public Drink drinkSearch(int maDrink, ArrayList<Drink> lsDrink){
        for(Drink a: lsDrink)
            if(a.getMaDrink() == maDrink)
                return a;
        return null;
    }
    public void insertData(String tenDrink, int price, String img){
        SQLiteDatabase db = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.OPEN_READWRITE);
        ContentValues values = new ContentValues();
        values.put(TENDRINK_COL, tenDrink);
        values.put(GIADRINK_COL, price);
        values.put(IMAGEDRINK_COL, img);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }
    public void updateData(Drink oldD, Drink newD)
    {
        SQLiteDatabase db=SQLiteDatabase.openDatabase(PATH,null,SQLiteDatabase.OPEN_READWRITE);
        ContentValues values=new ContentValues();
        values.put(MADRINK_COL, newD.getMaDrink());
        values.put(TENDRINK_COL, newD.getTenDrink());
        values.put(GIADRINK_COL, newD.getGiaDrink());
        values.put(IMAGEDRINK_COL, newD.getImgDrink());
        db.update(TABLE_NAME,values, MADRINK_COL+"=?",
                new String[]{String.valueOf(oldD.getMaDrink())});
        db.close();
    }
    public void deleteData(int maDrink) {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(PATH,null,SQLiteDatabase.OPEN_READWRITE);
        db.delete(TABLE_NAME, MADRINK_COL + " =?",
                new String[]{String.valueOf(maDrink)});
        db.close();
    }
    public static ArrayList<Drink> loadData(){
        ArrayList<Drink> kq = new ArrayList<>();
        SQLiteDatabase db = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME,null);
        cursor.moveToFirst();
        do {
            Drink d = new Drink();
            d.setMaDrink(cursor.getInt(0));
            d.setTenDrink(cursor.getString(1));
            d.setGiaDrink(cursor.getInt(2));
            d.setImgDrink(cursor.getString(3));
            kq.add(d);
        }while (cursor.moveToNext());
        return kq;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}