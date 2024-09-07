package com.mobile.foodorderingproject.Controller;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.mobile.foodorderingproject.Model.Dessert;

import java.util.ArrayList;

public class DessertHandler extends SQLiteOpenHelper {
    public static final String DB_NAME = "qlch";
    @SuppressLint("SdCardPath")
    private static final String PATH = "/data/data/com.mobile.foodorderingproject/database/FoodOrdering.db";
    private static final String TABLE_NAME = "Dessert";
    private static final String MATRANGMIENG_COL = "MaDessert";
    private static final String TENTRANGMIENG_COL = "TenDessert";
    private static final String GIATRANGMIENG_COL = "GiaDessert";
    private static final String IMAGETRANGMIENG_COL = "ImageDessert";
    public DessertHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_NAME, factory, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db = SQLiteDatabase.openDatabase(PATH, null,SQLiteDatabase.CREATE_IF_NECESSARY);
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + MATRANGMIENG_COL +" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, " + TENTRANGMIENG_COL + " TEXT NOT NULL UNIQUE, " + GIATRANGMIENG_COL + " INTEGER NOT NULL, "+IMAGETRANGMIENG_COL+" TEXT NOT NULL)";
        db.execSQL(sql);
        db.close();
    }
    public void initData(){
        SQLiteDatabase db = SQLiteDatabase.openDatabase(PATH,null,SQLiteDatabase.CREATE_IF_NECESSARY);
        String sql1 = "INSERT OR IGNORE INTO " + TABLE_NAME + "("+TENTRANGMIENG_COL+","+GIATRANGMIENG_COL+","+IMAGETRANGMIENG_COL+") VALUES ('Súp Tổ Yến',3000000,'toyen')";
        db.execSQL(sql1);
        String sql2 = "INSERT OR IGNORE INTO " + TABLE_NAME + "("+TENTRANGMIENG_COL+","+GIATRANGMIENG_COL+","+IMAGETRANGMIENG_COL+") VALUES ('Tiramisu',125000,'tiramisu')";
        db.execSQL(sql2);
        String sql3 = "INSERT OR IGNORE INTO " + TABLE_NAME + "("+TENTRANGMIENG_COL+","+GIATRANGMIENG_COL+","+IMAGETRANGMIENG_COL+") VALUES ('Bông Lan Trứng Muối',150000,'bonglan')";
        db.execSQL(sql3);
        String sql4 = "INSERT OR IGNORE INTO " + TABLE_NAME + "("+TENTRANGMIENG_COL+","+GIATRANGMIENG_COL+","+IMAGETRANGMIENG_COL+") VALUES ('Mochi',120000,'mochi')";
        db.execSQL(sql4);
        String sql5 = "INSERT OR IGNORE INTO " + TABLE_NAME + "("+TENTRANGMIENG_COL+","+GIATRANGMIENG_COL+","+IMAGETRANGMIENG_COL+") VALUES ('Pudding',150000,'pudding')";
        db.execSQL(sql5);
        String sql6 = "INSERT OR IGNORE INTO " + TABLE_NAME + "("+TENTRANGMIENG_COL+","+GIATRANGMIENG_COL+","+IMAGETRANGMIENG_COL+") VALUES ('Kem dâu',50000,'kemdau')";
        db.execSQL(sql6);
        String sql7 = "INSERT OR IGNORE INTO " + TABLE_NAME + "("+TENTRANGMIENG_COL+","+GIATRANGMIENG_COL+","+IMAGETRANGMIENG_COL+") VALUES ('Bánh tai yến',20000,'taiyen')";
        db.execSQL(sql7);
    }
    public Dessert dessertSearch(int maTrangMieng, ArrayList<Dessert> lsDessert){
        for(Dessert a: lsDessert)
            if(a.getMaDessert() == maTrangMieng)
                return a;
        return null;
    }
    public void insertData(String tenTrangMieng, int price, String img){
        SQLiteDatabase db = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.OPEN_READWRITE);
        ContentValues values = new ContentValues();
        values.put(TENTRANGMIENG_COL, tenTrangMieng);
        values.put(GIATRANGMIENG_COL, price);
        values.put(IMAGETRANGMIENG_COL, img);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }
    public void updateData(Dessert oldD, Dessert newD)
    {
        SQLiteDatabase db=SQLiteDatabase.openDatabase(PATH,null,SQLiteDatabase.OPEN_READWRITE);
        ContentValues values=new ContentValues();
        values.put(MATRANGMIENG_COL, newD.getMaDessert());
        values.put(TENTRANGMIENG_COL, newD.getTenDessert());
        values.put(GIATRANGMIENG_COL, newD.getGiaDessert());
        values.put(IMAGETRANGMIENG_COL, newD.getImgDessert());
        db.update(TABLE_NAME,values, MATRANGMIENG_COL+"=?",
                new String[]{String.valueOf(oldD.getMaDessert())});
        db.close();
    }
    public void deleteData(int maTrangMieng) {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(PATH,null,SQLiteDatabase.OPEN_READWRITE);
        db.delete(TABLE_NAME, MATRANGMIENG_COL + " =?",
                new String[]{String.valueOf(maTrangMieng)});
        db.close();
    }
    public static ArrayList<Dessert> loadData(){
        ArrayList<Dessert> kq = new ArrayList<>();
        SQLiteDatabase db = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME,null);
        cursor.moveToFirst();
        do {
            Dessert tb = new Dessert();
            tb.setMaDessert(cursor.getInt(0));
            tb.setTenDessert(cursor.getString(1));
            tb.setGiaDessert(cursor.getInt(2));
            tb.setImgDessert(cursor.getString(3));
            kq.add(tb);
        }while (cursor.moveToNext());
        return kq;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}