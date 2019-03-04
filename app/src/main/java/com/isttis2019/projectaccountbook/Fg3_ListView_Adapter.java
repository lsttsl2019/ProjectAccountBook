package com.isttis2019.projectaccountbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Fg3_ListView_Adapter extends BaseAdapter {

   ArrayList<Fg3Page1Item> page1Items;
    LayoutInflater layoutInflater;
    Context context;

    public Fg3_ListView_Adapter(ArrayList<Fg3Page1Item> page1Items, Context context) {
        this.page1Items = page1Items;
        this.layoutInflater=LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public int getCount() {
        return page1Items.size();
    }

    @Override
    public Object getItem(int position) {
        return page1Items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            convertView=layoutInflater.inflate(R.layout.fg3_listview_item_day1, null);
        }

        TextView tvincome= convertView.findViewById(R.id.fg3_listItem_tv);
        TextView tvMoney= convertView.findViewById(R.id.fg3_listItem_tvMoney);

        Fg3Page1Item fg3Page1Item=page1Items.get(position);

        tvincome.setText(fg3Page1Item.getPlaceData());
        tvMoney.setText(fg3Page1Item.getMoneyData());

        return convertView;
    }
}
