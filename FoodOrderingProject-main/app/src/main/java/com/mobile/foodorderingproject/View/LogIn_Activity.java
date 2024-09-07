package com.mobile.foodorderingproject.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mobile.foodorderingproject.Adapter.CustomDessertAdapter;
import com.mobile.foodorderingproject.Controller.BanHandler;
import com.mobile.foodorderingproject.Controller.ComboHandler;
import com.mobile.foodorderingproject.Controller.DessertHandler;
import com.mobile.foodorderingproject.Controller.DrinkHandler;
import com.mobile.foodorderingproject.Controller.FoodHandler;
import com.mobile.foodorderingproject.Controller.HoaDonHandler;
import com.mobile.foodorderingproject.Controller.StaffHandler;
import com.mobile.foodorderingproject.Model.Staff;
import com.mobile.foodorderingproject.R;

import java.util.ArrayList;

public class LogIn_Activity extends AppCompatActivity {
    SQLiteDatabase db;
    EditText edtUserName, edtPassword;
    Button btnLogin;
    ArrayList<Staff> arrayListStaff = new ArrayList<>();
    StaffHandler staffHandler = new StaffHandler(this,StaffHandler.DB_NAME,null,1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        DBActive();
        arrayListStaff= staffHandler.loadData();
        addControls();
        addEvent();
    }
    public void DBActive(){
        BanHandler banHandler = new BanHandler(this, BanHandler.DB_NAME,null,1);
        banHandler.onCreate(db);
        banHandler.initData();
        staffHandler.onCreate(db);
        staffHandler.initData();
        DrinkHandler drinkHandler= new DrinkHandler(this,DrinkHandler.DB_NAME,null,1);
        drinkHandler.onCreate(db);
        drinkHandler.initData();
        FoodHandler foodHandler= new FoodHandler(this,FoodHandler.DB_NAME,null,1);
        foodHandler.onCreate(db);
        foodHandler.initData();
        DessertHandler dessertHandler= new DessertHandler(this,DessertHandler.DB_NAME,null,1);
        dessertHandler.onCreate(db);
        dessertHandler.initData();
        ComboHandler comboHandler= new ComboHandler(this,ComboHandler.DB_NAME,null,1);
        comboHandler.onCreate(db);
        comboHandler.initData();
    }
    public void addControls(){
        edtUserName = (EditText)findViewById(R.id.edtUserName);
        edtPassword = (EditText)findViewById(R.id.edtPassWord);
        btnLogin = (Button)findViewById(R.id.btnLogIn);
    }
    public void addEvent(){
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taikhoan = String.valueOf(edtUserName.getText());
                String matkhau = String.valueOf(edtPassword.getText());
                if(staffHandler.confirmLogIn(taikhoan,matkhau,arrayListStaff)){
                    Staff staff = staffHandler.staffSearch(taikhoan, arrayListStaff);
                    Intent intent = new Intent(LogIn_Activity.this, ListTable.class);
                    intent.putExtra("manv",String.valueOf(staff.getMaNv()));
                    startActivity(intent);
                }
                else
                    Toast.makeText(LogIn_Activity.this,"Vui lòng nhập lại tài khoản và mật khẩu",Toast.LENGTH_LONG).show();
            }
        });
    }
}