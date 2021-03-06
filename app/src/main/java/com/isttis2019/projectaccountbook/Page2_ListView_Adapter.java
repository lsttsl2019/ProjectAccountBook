package com.isttis2019.projectaccountbook;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Calendar;

public class Page2_ListView_Adapter extends BaseAdapter {




    ArrayList<Page2_item> page2Items;
    LayoutInflater inflater;
    Context context;

    public Page2_ListView_Adapter(ArrayList<Page2_item> page2Items, Context context) {
        this.page2Items = page2Items;
        this.inflater = LayoutInflater.from(context);
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
            convertView=inflater.inflate(R.layout.page2_listview_item,null);
        }

        TextView tvdata=convertView.findViewById(R.id.item_day_listview);
        TextView tvTime=convertView.findViewById(R.id.item_time_listview);
        TextView tvItem=convertView.findViewById(R.id.item_income_listview);
        TextView tvMoney=convertView.findViewById(R.id.item_money_listview);

        Page2_item page2Item=page2Items.get(position);





        tvdata.setText(page2Item.getToDay());
        tvItem.setText(page2Item.item);
        tvTime.setText(page2Item.time);
        tvMoney.setText(page2Item.money);


        return convertView;
    }
}

































