package com.isttis2019.projectaccountbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.isttis2019.projectaccountbook.Fg3Page2Item;
import com.isttis2019.projectaccountbook.Fg3_ListView_Adapter;
import com.isttis2019.projectaccountbook.R;

import java.util.ArrayList;

public class Fg3_ListView_Adapter2 extends BaseAdapter {

    ArrayList<Fg3Page2Item> page2Items;
    LayoutInflater layoutInflater;
    Context context;

    public Fg3_ListView_Adapter2(ArrayList<Fg3Page2Item> page2Items, Context context) {
        this.page2Items = page2Items;
        layoutInflater=LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public int getCount() {
        return page2Items.size();
    }

    @Override
    public Object getItem(int position) {
        return page2Items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            convertView= layoutInflater.inflate(R.layout.fg3_listview_item_day2,null);
        }

        TextView tvincome=convertView.findViewById(R.id.fg3_listItem2_tv);
        TextView tvMoney=convertView.findViewById(R.id.fg3_listItem2_tvMoney);

        Fg3Page2Item page2Item=page2Items.get(position);
        tvincome.setText(page2Item.getPlaceData());
        tvMoney.setText(page2Item.getMoneyData());


        return convertView;
    }
}
