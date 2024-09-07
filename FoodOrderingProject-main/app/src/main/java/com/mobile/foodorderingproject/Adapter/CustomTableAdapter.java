package com.mobile.foodorderingproject.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mobile.foodorderingproject.Controller.BanHandler;
import com.mobile.foodorderingproject.Controller.ComboHandler;
import com.mobile.foodorderingproject.Model.Combo;
import com.mobile.foodorderingproject.Model.Table;
import com.mobile.foodorderingproject.R;

import java.util.ArrayList;
import java.util.List;

public class CustomTableAdapter extends BaseAdapter {
    ArrayList<Table> arrayListTable;

    Context context;
    private final LayoutInflater layoutInflater;
    @Override
    public int getCount() {
        return arrayListTable.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayListTable.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){
            convertView = layoutInflater.inflate(R.layout.table_item_layout, null);
            holder = new CustomTableAdapter.ViewHolder();
            holder.tvTable = (TextView) convertView.findViewById(R.id.tvTable);
            convertView.setTag(holder);
        }
        else{
            holder = (CustomTableAdapter.ViewHolder) convertView.getTag();
        }
        Table table = this.arrayListTable.get(position);
        holder.tvTable.setText(String.valueOf(table.getMaBan()));
        if (table.getTrangThai().equals("Available")){
            holder.tvTable.setBackgroundResource(R.drawable.border_available);
        } else if (table.getTrangThai().equals("Unavailable")){
            holder.tvTable.setBackgroundResource(R.drawable.border_unavailable);
        } else if (table.getTrangThai().equals("Reserved")){
            holder.tvTable.setBackgroundResource(R.drawable.border_reserved);
        }
        return convertView;
    }

    public CustomTableAdapter(Context aContext, ArrayList<Table> lsTable) {
        this.context = aContext;
        this.arrayListTable = lsTable;
        this.layoutInflater = LayoutInflater.from(aContext);
    }

    static class ViewHolder {
        TextView tvTable;
    }
}
