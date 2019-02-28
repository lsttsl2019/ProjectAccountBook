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

//        page2Item=mainActivity.getItemsPage2();
//        Toast.makeText(mainActivity, ""+page2Item.size(), Toast.LENGTH_SHORT).show();

//            page1Item=mainActivity.getItems();
//            page2Item=mainActivity.getItem2s();



//        mainActivity = (MainActivity) getActivity();


//        if (tmp!=null){
//            for (Page1Item t : items){
//                tmp.add(t.getCalendar());
//
////                for (int i=0; i<=items.size(); i++){
////                    days.add(new EventDay(tmp.get(i),R.drawable.ic_dot));
////                }
//
//            }
//
//            days.add(new EventDay(tmp.get(0), R.drawable.ic_dot));
//            calendarView.setEvents(days);

//        }
//        Toast.makeText(mainActivity, tmp.size()+" : "+ items.size(), Toast.LENGTH_SHORT).show();




        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        fg1.add(page1Item.get(0).getCalendar());
//
//        String str= page1Item.get(0).getToDay();
//        Toast.makeText(getContext(), ""+str, Toast.LENGTH_SHORT).show();
//        SimpleDateFormat sdf= new SimpleDateFormat("yyyyMMdd");
//        try {
//            Date date=sdf.parse(str);
//           fg1.get(0).setTime(date);
//            days.add(new EventDay(fg1.get(0),R.drawable.ic_dot));
//
//        } catch (ParseException e) {
//            e.printStackTrace();
//            Toast.makeText(getContext(), "false", Toast.LENGTH_SHORT).show();
//        }

        for (Page1Item t: page1Item){
            fg1.add(t.getCalendar());
        }

        for (int i=0; i<page1Item.size(); i++){

            String toDay=page1Item.get(i).toDay;
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

        calendarView.setEvents(days);









//        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
//        String date=G.year+""+(G.month+1)+""+G.dayOfMonth;
//        Calendar calendar=Calendar.getInstance();
//        try {
//            Date date1=sdf.parse(date);
//            calendar.setTime(date1);
//            days.add(new EventDay(calendar,R.drawable.ic_dot));
//            calendarView.setEvents(days);
//
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }



//        int year=fg1.get(i).getYear();
//        int month=(fg1.get(i).getMonth()+1);
//        int dayofMonth=fg1.get(i).getDayOfMonth();
//        String dates=year+""+month+""+dayofMonth;
//        Calendar calendar=Calendar.getInstance();
//        calendar.set(year,month,dayofMonth);
//
//        days.add(new EventDay(calendar,R.drawable.ic_dot));
//        calendarView.setEvents(days);


    }




    public void getItemfg2(Page2_item item){
        page2Item.add(item);

    }


}





















