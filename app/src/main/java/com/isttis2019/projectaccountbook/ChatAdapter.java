package com.isttis2019.projectaccountbook;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatAdapter extends BaseAdapter {

    ArrayList<MessagItem> messagItems;
    LayoutInflater layoutInflater;

    public ChatAdapter(ArrayList<MessagItem> messagItems, LayoutInflater layoutInflater) {
        this.messagItems = messagItems;
        this.layoutInflater = layoutInflater;
    }

    @Override
    public int getCount() {
        return messagItems.size();
    }

    @Override
    public Object getItem(int position) {
        return messagItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        MessagItem item=messagItems.get(position);

        View itemView =null;

        if (item.getName().equals(G.nickName)){
            itemView=layoutInflater.inflate(R.layout.my_msgbox, parent, false);

        }else {
            itemView=layoutInflater.inflate(R.layout.other_msgbox,parent, false);
        }

        CircleImageView circleImageView=itemView.findViewById(R.id.iv);
        TextView tvName= itemView.findViewById(R.id.tv_name);
        TextView tvmsg=itemView.findViewById(R.id.tv_msg);
        TextView tvTime=itemView.findViewById(R.id.tv_time);

        tvName.setText(item.getName());
        tvmsg.setText(item.getMsg());
        tvTime.setText(item.getTime());

        Picasso.with(layoutInflater.getContext()).load(item.getProfileUrl()).into(circleImageView);



        return itemView;
    }
}











































