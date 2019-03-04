package com.isttis2019.projectaccountbook;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Calendar;

public class Page1_ListView_Adapter extends BaseAdapter {




    ArrayList<Page1Item> page1Items;
    LayoutInflater inflater;
    Context context;
    Uri uri;


    public Page1_ListView_Adapter(ArrayList<Page1Item> page1Items, Context context) {
        this.page1Items = page1Items;
        this.inflater = LayoutInflater.from(context);
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
            convertView=inflater.inflate(R.layout.page1_listview_item,null);
        }

        ImageView imgbill=convertView.findViewById(R.id.listview_img);
        TextView tvday= convertView.findViewById(R.id.listview_day);
        TextView tvtime= convertView.findViewById(R.id.listview_time);
        TextView tvpalce= convertView.findViewById(R.id.listview_place);
        TextView tvmoney= convertView.findViewById(R.id.listview_money);


       Page1Item page1Item=page1Items.get(position);

        if (page1Item.path==null){

                Picasso.with(context).load(R.drawable.img_back01).into(imgbill);
        }else {
            uri=Uri.parse(page1Item.path);
            Glide.with(context).load(uri).into(imgbill);
        }






       tvday.setText(page1Item.toDay);
       tvtime.setText(page1Item.timeData);
       tvpalce.setText(page1Item.placeData);
       tvmoney.setText(page1Item.moneyData);



        return convertView;
    }
}

































