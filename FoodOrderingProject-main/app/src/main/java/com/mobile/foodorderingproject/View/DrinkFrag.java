package com.mobile.foodorderingproject.View;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.mobile.foodorderingproject.Adapter.CustomDrinkAdapter;
import com.mobile.foodorderingproject.Controller.DrinkHandler;
import com.mobile.foodorderingproject.Model.Drink;
import com.mobile.foodorderingproject.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FoodFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DrinkFrag extends Fragment {
    ImageButton btnImgPlus, btnImgMinus;
    TextView tvNums, tvPrice, tvName;
    SQLiteDatabase db;
    GridView GridDrink;
    CustomDrinkAdapter adapter;
    ArrayList<Drink> lsDrink=new ArrayList<>();
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DrinkFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FoodFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static DrinkFrag newInstance(String param1, String param2) {
        DrinkFrag fragment = new DrinkFrag();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_drink, container, false);

// Inflate the layout for this fragment
        addControls(view);
        load();
        GridDrink.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            }
        });
        return view;

    }

    public void addControls(View view){
        GridDrink = (GridView) view.findViewById(R.id.GridDrink);
    }
    public void load(){
        lsDrink = DrinkHandler.loadData();
        adapter = new CustomDrinkAdapter(requireActivity(),lsDrink);
        GridDrink.setAdapter(adapter);
    }
}
