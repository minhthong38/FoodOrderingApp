package com.mobile.foodorderingproject.Controller;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.mobile.foodorderingproject.Model.Food;

import java.util.ArrayList;

public class FoodHandler extends SQLiteOpenHelper {
    public static final String DB_NAME = "qlch";
    @SuppressLint("SdCardPath")
    private static final String PATH = "/data/data/com.mobile.foodorderingproject/database/FoodOrdering.db";
    private static final String TABLE_NAME = "Food";
    private static final String MAFOOD_COL = "MaFood";
    private static final String TENFOOD_COL = "TenFood";
    private static final String GIAFOOD_COL = "GiaFood";
    private static final String IMAGEFOOD_COL = "ImgFood";
    public FoodHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_NAME, factory, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db = SQLiteDatabase.openDatabase(PATH, null,SQLiteDatabase.CREATE_IF_NECESSARY);
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + MAFOOD_COL +" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, " + TENFOOD_COL + " TEXT NOT NULL UNIQUE, " + GIAFOOD_COL + " INTEGER NOT NULL, "+IMAGEFOOD_COL+" TEXT NOT NULL)";
        db.execSQL(sql);
        db.close();
    }

    public void initData(){
        SQLiteDatabase db = SQLiteDatabase.openDatabase(PATH,null,SQLiteDatabase.CREATE_IF_NECESSARY);
        String sql1 = "INSERT OR IGNORE INTO " + TABLE_NAME + "("+TENFOOD_COL+","+GIAFOOD_COL+","+IMAGEFOOD_COL+") VALUES ('Foie gras',1200000,'foiegras')";
        db.execSQL(sql1);
        String sql2 = "INSERT OR IGNORE INTO " + TABLE_NAME + "("+TENFOOD_COL+","+GIAFOOD_COL+","+IMAGEFOOD_COL+") VALUES ('Ratatouille',300000,'ratatouille')";
        db.execSQL(sql2);
        String sql3 = "INSERT OR IGNORE INTO " + TABLE_NAME + "("+TENFOOD_COL+","+GIAFOOD_COL+","+IMAGEFOOD_COL+") VALUES ('Sashimi',600000,'sashimi')";
        db.execSQL(sql3);
        String sql4 = "INSERT OR IGNORE INTO " + TABLE_NAME + "("+TENFOOD_COL+","+GIAFOOD_COL+","+IMAGEFOOD_COL+") VALUES ('Iberico Ham',13800000,'iberico')";
        db.execSQL(sql4);
        String sql5 = "INSERT OR IGNORE INTO " + TABLE_NAME + "("+TENFOOD_COL+","+GIAFOOD_COL+","+IMAGEFOOD_COL+") VALUES ('Kobe A5',10000000,'kobea5')";
        db.execSQL(sql5);
        String sql6 = "INSERT OR IGNORE INTO " + TABLE_NAME + "("+TENFOOD_COL+","+GIAFOOD_COL+","+IMAGEFOOD_COL+") VALUES ('Pizza Truffle',10000000,'pizzatruf')";
        db.execSQL(sql6);
        String sql7 = "INSERT OR IGNORE INTO " + TABLE_NAME + "("+TENFOOD_COL+","+GIAFOOD_COL+","+IMAGEFOOD_COL+") VALUES ('Beef Wellington',3450000,'wellington')";
        db.execSQL(sql7);
        String sql8 = "INSERT OR IGNORE INTO " + TABLE_NAME + "("+TENFOOD_COL+","+GIAFOOD_COL+","+IMAGEFOOD_COL+") VALUES ('Luxury Burger',3850000,'burger')";
        db.execSQL(sql8);
    }
    public Food foodSearch(int maFood, ArrayList<Food> lsFood){
        for(Food a: lsFood)
            if(a.getMaFood() == maFood)
                return a;
        return null;
    }
    public void insertData(String tenFood, int price, String img){
        SQLiteDatabase db = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.OPEN_READWRITE);
        ContentValues values = new ContentValues();
        values.put(TENFOOD_COL, tenFood);
        values.put(GIAFOOD_COL, price);
        values.put(IMAGEFOOD_COL, img);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }
    public void updateData(Food oldF, Food newF)
    {
        SQLiteDatabase db=SQLiteDatabase.openDatabase(PATH,null,SQLiteDatabase.OPEN_READWRITE);
        ContentValues values=new ContentValues();
        values.put(MAFOOD_COL, newF.getMaFood());
        values.put(TENFOOD_COL, newF.getTenFood());
        values.put(GIAFOOD_COL, newF.getGiaFood());
        values.put(IMAGEFOOD_COL, newF.getImgFood());
        db.update(TABLE_NAME,values, MAFOOD_COL+"=?",
                new String[]{String.valueOf(oldF.getMaFood())});
        db.close();
    }
    public void deleteData(int maFood) {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(PATH,null,SQLiteDatabase.OPEN_READWRITE);
        db.delete(TABLE_NAME, MAFOOD_COL + " =?",
                new String[]{String.valueOf(maFood)});
        db.close();
    }
    public  static ArrayList<Food> loadData(){
        ArrayList<Food> re = new ArrayList<>();
        SQLiteDatabase db = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME,null);
        cursor.moveToFirst();
        do {
            Food f = new Food();
            f.setMaFood(cursor.getInt(0));
            f.setTenFood(cursor.getString(1));
            f.setGiaFood(cursor.getInt(2));
            f.setImgFood(cursor.getString(3));
            re.add(f);
        }while (cursor.moveToNext());
        return re;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
