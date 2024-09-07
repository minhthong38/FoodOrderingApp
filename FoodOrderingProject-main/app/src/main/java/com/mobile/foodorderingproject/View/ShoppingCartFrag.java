package com.mobile.foodorderingproject.View;

import android.annotation.SuppressLint;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.mobile.foodorderingproject.Adapter.CustomShoppingCartAdapter;
import com.mobile.foodorderingproject.Controller.HoaDonHandler;
import com.mobile.foodorderingproject.Model.HoaDon;
import com.mobile.foodorderingproject.Model.ShoppingCart;
import com.mobile.foodorderingproject.R;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ShoppingCartFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShoppingCartFrag extends Fragment {
    ListView lvShopping;
    TextView tvTongMon, tvTongTien;
    SQLiteDatabase db;
    Button btnConfirm;
    CustomShoppingCartAdapter adapter;
    int tongtien;
    public static String idTable;
    public static String maNV;
    public static ArrayList<ShoppingCart> arrayListShoppingCartFood;
    public static ArrayList<ShoppingCart> arrayListShoppingCartDrink;
    public static ArrayList<ShoppingCart> arrayListShoppingCartDessert;
    public static ArrayList<ShoppingCart> arrayListShoppingCartCombo;
    ArrayList<ShoppingCart> arrayListShoppingCart;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ShoppingCartFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ShoppingCartFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static ShoppingCartFrag newInstance(String param1, String param2) {
        ShoppingCartFrag fragment = new ShoppingCartFrag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shopping_cart, container, false);
        addControls(view);
        initData();
        String string = (String) tvTongMon.getText();
        String tonggia = (String) tvTongTien.getText();
        tongtien = 0;
        for(ShoppingCart s: arrayListShoppingCart){
            tongtien += s.getGia();
        }
        tvTongMon.setText(string+ " "+arrayListShoppingCart.size());
        tvTongTien.setText(tonggia+" "+tongtien);
        adapter = new CustomShoppingCartAdapter(requireActivity(),R.layout.item_shopping_cart_layout,arrayListShoppingCart);
        lvShopping.setAdapter(adapter);
        HoaDonHandler hoaDonHandler = new HoaDonHandler(requireActivity(),HoaDonHandler.DB_NAME,null,1);
        hoaDonHandler.onCreate(db);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @SuppressLint({"NewApi", "LocalSuppress"})
            @Override
            public void onClick(View v) {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                hoaDonHandler.insertData(Integer.parseInt(maNV),Integer.parseInt(idTable),tongtien,dtf.format(now));
                Toast.makeText(requireActivity(),"Bạn đã đặt món thành công", Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }
    public void addControls(View view){
        btnConfirm=(Button)view.findViewById(R.id.btnConfirm);
        lvShopping=(ListView)view.findViewById(R.id.lvShopping);
        tvTongMon=(TextView)view.findViewById(R.id.tvTongMon);
        tvTongTien=(TextView) view.findViewById(R.id.tvTongTien);
    }
    public void initData(){
        arrayListShoppingCart = new ArrayList<>();
        if(arrayListShoppingCartFood != null)
            arrayListShoppingCart.addAll(arrayListShoppingCartFood);
        if(arrayListShoppingCartDrink != null)
            arrayListShoppingCart.addAll(arrayListShoppingCartDrink);
        if(arrayListShoppingCartDessert != null)
            arrayListShoppingCart.addAll(arrayListShoppingCartDessert);
        if(arrayListShoppingCartCombo != null)
            arrayListShoppingCart.addAll(arrayListShoppingCartCombo);
    }
}