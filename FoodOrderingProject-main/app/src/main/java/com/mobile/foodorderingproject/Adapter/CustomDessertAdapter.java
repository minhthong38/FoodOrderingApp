package com.mobile.foodorderingproject.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobile.foodorderingproject.Model.Dessert;
import com.mobile.foodorderingproject.Model.ShoppingCart;
import com.mobile.foodorderingproject.R;
import com.mobile.foodorderingproject.View.ShoppingCartFrag;

import java.util.ArrayList;
import java.util.List;

public class CustomDessertAdapter extends BaseAdapter {
    Context context;
    ArrayList<Dessert> lsDessert;
    ShoppingCart shoppingCart;
    ArrayList<ShoppingCart> arrayListShoppingCart = new ArrayList<>();
    private final LayoutInflater layoutInflater;

    @Override
    public int getCount() {
        return lsDessert.size();
    }

    @Override
    public Object getItem(int position) {
        return lsDessert.get(position);
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
            holder = new CustomDessertAdapter.ViewHolder();
            holder.menuView = (ImageView) convertView.findViewById(R.id.imgMenu);
            holder.nameView = (TextView) convertView.findViewById(R.id.tvName);
            holder.priceView = (TextView) convertView.findViewById(R.id.tvPrice);
            holder.numsView = (TextView) convertView.findViewById(R.id.tvNums);
            holder.btnImgPlus = (ImageButton) convertView.findViewById(R.id.btnImgPlus);
            holder.btnImgMinus = (ImageButton) convertView.findViewById(R.id.btnImgMinus);
            convertView.setTag(holder);
        }
        else{
            holder = (CustomDessertAdapter.ViewHolder) convertView.getTag();
        }
        //Truyền data lên view
        Dessert dessert = this.lsDessert.get(position);
        holder.nameView.setText(dessert.getTenDessert());
        holder.priceView.setText(String.valueOf(dessert.getGiaDessert()));
        int imageId = this.getMipMapResIdByName(dessert.getImgDessert());
        holder.menuView.setImageResource(imageId);
        holder.btnImgPlus.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v) {
                ((GridView)parent).performItemClick(v, position, 0);
                holder.numsView.setText(String.valueOf(Integer.parseInt((String) holder.numsView.getText())+1));
                String name = dessert.getTenDessert();
                int soluongtruoc = Integer.parseInt((String) holder.numsView.getText())-1;
                int soluong = Integer.parseInt((String) holder.numsView.getText());
                int price = dessert.getGiaDessert()*soluong;
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
                    String name = dessert.getTenDessert();
                    int soluongtruoc = Integer.parseInt((String) holder.numsView.getText())+1;
                    int soluong = Integer.parseInt((String) holder.numsView.getText());
                    int price = dessert.getGiaDessert()*soluong;
                    shoppingCart = new ShoppingCart(name, price, soluong);
                    arrayListShoppingCart.add(shoppingCart);
                    if (soluong == 0)
                        arrayListShoppingCart.removeIf(shoppingCart -> shoppingCart.getTen().equals(name));
                    else if (soluongtruoc >= 0)
                        arrayListShoppingCart.removeIf(shoppingCart -> shoppingCart.getTen().equals(name) && shoppingCart.getSoluong() == soluongtruoc);
                }
            }
        });
        ShoppingCartFrag.arrayListShoppingCartDessert = arrayListShoppingCart;
        return convertView;
    }

    public CustomDessertAdapter(Context aContext,ArrayList<Dessert>lsDessert) {
        this.lsDessert = lsDessert;
        this.layoutInflater=LayoutInflater.from(aContext);
        this.context=aContext;
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
