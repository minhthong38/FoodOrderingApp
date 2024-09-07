package com.mobile.foodorderingproject.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobile.foodorderingproject.Model.Drink;
import com.mobile.foodorderingproject.Model.ShoppingCart;
import com.mobile.foodorderingproject.R;
import com.mobile.foodorderingproject.View.ShoppingCartFrag;

import java.util.ArrayList;
import java.util.List;

public class CustomDrinkAdapter extends BaseAdapter {
    Context context;
    List<Drink> lsDrink;
    ShoppingCart shoppingCart;
    ArrayList<ShoppingCart> arrayListShoppingCart = new ArrayList<>();
    private final LayoutInflater layoutInflater;

    @Override
    public int getCount() {
        return lsDrink.size();
    }

    @Override
    public Object getItem(int position) {
        return lsDrink.get(position);
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
        Drink drink = this.lsDrink.get(position);
        holder.nameView.setText(drink.getTenDrink());
        holder.priceView.setText(String.valueOf(drink.getGiaDrink()));
        int imageId = this.getMipMapResIdByName(drink.getImgDrink());
        holder.menuView.setImageResource(imageId);

        holder.btnImgPlus.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v) {
                ((GridView)parent).performItemClick(v, position, 0);
                holder.numsView.setText(String.valueOf(Integer.parseInt((String) holder.numsView.getText())+1));
                String name = drink.getTenDrink();
                int soluongtruoc = Integer.parseInt((String) holder.numsView.getText())-1;
                int soluong = Integer.parseInt((String) holder.numsView.getText());
                int price = drink.getGiaDrink()*soluong;
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
                    String name = drink.getTenDrink();
                    int soluongtruoc = Integer.parseInt((String) holder.numsView.getText())+1;
                    int soluong = Integer.parseInt((String) holder.numsView.getText());
                    int price = drink.getGiaDrink()*soluong;
                    shoppingCart = new ShoppingCart(name, price, soluong);
                    arrayListShoppingCart.add(shoppingCart);
                    if (soluong == 0)
                        arrayListShoppingCart.removeIf(shoppingCart -> shoppingCart.getTen().equals(name));
                    else if (soluongtruoc >= 0)
                        arrayListShoppingCart.removeIf(shoppingCart -> shoppingCart.getTen().equals(name) && shoppingCart.getSoluong() == soluongtruoc);
                }
            }
        });
        ShoppingCartFrag.arrayListShoppingCartDrink = arrayListShoppingCart;
        return convertView;
    }

    public CustomDrinkAdapter(Context aContext, ArrayList<Drink> lsDrink) {
        this.lsDrink = lsDrink;
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
