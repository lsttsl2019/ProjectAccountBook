package com.isttis2019.projectaccountbook;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Page3Fragment extends Fragment {

    CalendarView calendarView;
    ArrayList<EventDay>  days=new ArrayList<>();
    ArrayList<Calendar> fg1=new ArrayList<Calendar>();
    ArrayList<Calendar> fg2=new ArrayList<>();
    ArrayList<String> fg1ToDay=new ArrayList<>();
    ArrayList<String> fg2ToDay=new ArrayList<>();

    MainActivity mainActivity;
    ArrayList<Page1Item> page1Item;
    ArrayList<Page2_item> page2Item;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view=inflater.inflate(R.layout.fragment_page3, container,false);
        calendarView=view.findViewById(R.id.fg3_meter_calendar);
        mainActivity= (MainActivity) getActivity();

        page1Item =mainActivity.getItemsPage1();
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        page2Item=mainActivity.getItemsPage2();
///////////////////////////////////////////////////////// fg1 아이템이 찍이는것
        for (Page1Item t: page1Item){
            fg1.add(t.getCalendar());
            fg1ToDay.add(t.getToDay());
        }
        for (int i=0; i<fg1ToDay.size(); i++){

            String toDay=fg1ToDay.get(i);
            SimpleDateFormat sdf= new SimpleDateFormat("yyyyMMdd");
            try {
                Date date=sdf.parse(toDay);
                fg1.get(i).setTime(date);
                days.add(new EventDay(fg1.get(i),R.drawable.ic_dot));
            } catch (ParseException e) {
                e.printStackTrace();
                Toast.makeText(getContext(), ""+days.size(), Toast.LENGTH_SHORT).show();
            }
        }
        if (page2Item!=null){
            for (int i=0; i< page2Item.size(); i++){
                getItemfg2(page2Item.get(i));
            }
        }





        calendarView.setEvents(days);




    }




    public void getItemfg2(Page2_item item){
        page2Item.add(item);

        for (Page2_item t: page2Item){
            fg2.add(t.getCalendar());
            fg1ToDay.add(t.getToDay());
        }
        for (int i=0; i<fg2ToDay.size(); i++){

            String toDay=fg2ToDay.get(i);
            SimpleDateFormat sdf= new SimpleDateFormat("yyyyMMdd");
            try {
                Date date=sdf.parse(toDay);
                fg2.get(i).setTime(date);
                days.add(new EventDay(fg2.get(i),R.drawable.ic_dot2));
            } catch (ParseException e) {
                e.printStackTrace();
                Toast.makeText(getContext(), ""+days.size(), Toast.LENGTH_SHORT).show();
            }
        }
    }


}



























































