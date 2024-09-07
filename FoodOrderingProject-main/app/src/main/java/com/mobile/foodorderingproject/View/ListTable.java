package com.mobile.foodorderingproject.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.mobile.foodorderingproject.Adapter.CustomComboAdapter;
import com.mobile.foodorderingproject.Adapter.CustomTableAdapter;
import com.mobile.foodorderingproject.Controller.BanHandler;
import com.mobile.foodorderingproject.Controller.ComboHandler;
import com.mobile.foodorderingproject.Controller.DrinkHandler;
import com.mobile.foodorderingproject.Model.Table;
import com.mobile.foodorderingproject.R;

import java.util.ArrayList;

public class ListTable extends AppCompatActivity {

    GridView gridTable;
    ArrayList<Table> arrayListTable = new ArrayList<>();
    CustomTableAdapter adapter;

    String manv;
    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_table);
        gridTable = (GridView) findViewById(R.id.GridTable);
        DBActive();
        Intent intent = getIntent();
        manv = intent.getStringExtra("manv");
        addEvent();
    }
    
    private void addEvent(){
        gridTable.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Table table = arrayListTable.get(position);
                if (table.getTrangThai().equals("Available")) {
                    Intent intent = new Intent(ListTable.this, Menu_Activity.class);
                    intent.putExtra("IDTable", String.valueOf(table.getMaBan()));
                    intent.putExtra("MaNV",manv);
                    startActivity(intent);
                } else if (table.getTrangThai().equals("Unavailable")) {
                    Toast.makeText(ListTable.this, "Bàn đang được sử dụng", Toast.LENGTH_LONG).show();
                } else if (table.getTrangThai().equals("Reserved")) {
                    Toast.makeText(ListTable.this, "Bàn đã được đặt", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void load(){
        arrayListTable = BanHandler.loadData();
        adapter = new CustomTableAdapter(this,arrayListTable);
        gridTable.setAdapter(adapter);
    }

    public void DBActive() {
        BanHandler tableHandler = new BanHandler(this, BanHandler.DB_NAME, null, 1);
        tableHandler.onCreate(db);
        tableHandler.initData();
        load();
    }
}