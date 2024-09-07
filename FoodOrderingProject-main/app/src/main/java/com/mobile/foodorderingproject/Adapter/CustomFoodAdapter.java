package com.mobile.foodorderingproject.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobile.foodorderingproject.Model.Food;
import com.mobile.foodorderingproject.Model.ShoppingCart;
import com.mobile.foodorderingproject.R;
import com.mobile.foodorderingproject.View.ShoppingCartFrag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

public class CustomFoodAdapter extends BaseAdapter {
    Context context;
    List<Food> lsFood;
    ShoppingCart shoppingCart;
    ArrayList<ShoppingCart> arrayListShoppingCart = new ArrayList<>();

    private final LayoutInflater layoutInflater;

    @Override
    public int getCount() {
        return lsFood.size();
    }

    @Override
    public Object getItem(int position) {
        return lsFood.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        //Tạo view Grid
        if (convertView == null){
            convertView = layoutInflater.inflate(R.layout.menu_food_drink_dessert_layout, null);
            holder = new ViewHolder();
            holder.menuView = (ImageView) convertView.findViewById(R.id.imgMenu);
            holder.nameView = (TextView) convertView.findViewById(R.id.tvName);
            holder.priceView = (TextView) convertView.findViewById(R.id.tvPrice);
            holder.numsView = (TextView) convertView.findViewById(R.id.tvNums);
            holder.btnImgPlus = (ImageButton) convertView.findViewById(R.id.btnImgPlus);
            holder.btnImgMinus = (ImageButton) convertView.findViewById(R.id.btnImgMinus);
            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
        }
        //Truyền data lên view
        Food food = this.lsFood.get(position);
        holder.nameView.setText(food.getTenFood());
        holder.priceView.setText(String.valueOf(food.getGiaFood()));
        int imageId = this.getMipMapResIdByName(food.getImgFood());
        holder.menuView.setImageResource(imageId);
        holder.btnImgPlus.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v) {
                ((GridView)parent).performItemClick(v, position, 0);
                holder.numsView.setText(String.valueOf(Integer.parseInt((String) holder.numsView.getText())+1));
                String name = food.getTenFood();
                int soluongtruoc = Integer.parseInt((String) holder.numsView.getText())-1;
                int soluong = Integer.parseInt((String) holder.numsView.getText());
                int price = food.getGiaFood()*soluong;
                shoppingCart = new ShoppingCart(name, price, soluong);
                arrayListShoppingCart.add(shoppingCart);
                if(soluongtruoc > 0)
                    arrayListShoppingCart.removeIf(shoppingCart -> shoppingCart.getTen().equals(name) && shoppingCart.getSoluong() == soluongtruoc);
            }
        });
        holder.btnImgMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((GridView)parent).performItemClick(v, position, 0);
                if(Integer.parseInt((String) holder.numsView.getText()) <= 0)
                    holder.numsView.setText("0");
                else{
                    holder.numsView.setText(String.valueOf(Integer.parseInt((String) holder.numsView.getText()) - 1));
                    String name = food.getTenFood();
                    int soluongtruoc = Integer.parseInt((String) holder.numsView.getText())+1;
                    int soluong = Integer.parseInt((String) holder.numsView.getText());
                    int price = food.getGiaFood()*soluong;
                    shoppingCart = new ShoppingCart(name, price, soluong);
                    arrayListShoppingCart.add(shoppingCart);
                    if (soluong == 0)
                        arrayListShoppingCart.removeIf(shoppingCart -> shoppingCart.getTen().equals(name));
                    else if (soluongtruoc >= 0)
                        arrayListShoppingCart.removeIf(shoppingCart -> shoppingCart.getTen().equals(name) && shoppingCart.getSoluong() == soluongtruoc);
                }
            }
        });
        ShoppingCartFrag.arrayListShoppingCartFood = arrayListShoppingCart;
        return convertView;
    }

    public CustomFoodAdapter(Context aContext, ArrayList<Food> lsFood) {
        this.lsFood = lsFood;
        this.layoutInflater = LayoutInflater.from(aContext);
        this.context = aContext;
    }

    public int getMipMapResIdByName(String resName){
        String pkgName = context.getPackageName();
        int ResId = context.getResources().getIdentifier(resName, "mipmap", pkgName);
        return ResId;
    }

    static class ViewHolder {
        ImageButton btnImgPlus, btnImgMinus;
        ImageView menuView;
        TextView nameView, priceView, numsView;
    }

}
