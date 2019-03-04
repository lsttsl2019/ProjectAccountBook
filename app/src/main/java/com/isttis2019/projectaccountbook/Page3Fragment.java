package com.isttis2019.projectaccountbook;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;

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
    ArrayList<String>  fg1Today=new ArrayList<>();
    ArrayList<String>  fg2Today=new ArrayList<>();

    MainActivity mainActivity;
    ArrayList<Page1Item> page1Item;
    ArrayList<Page2_item> page2Item=new ArrayList<>();

    String toDay;


    ListView fg1ListView;
    Fg3_ListView_Adapter fg3Adapter;
    ArrayList<Fg3Page1Item> fg3Page1Item=new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view=inflater.inflate(R.layout.fragment_page3, container,false);
        calendarView=view.findViewById(R.id.fg3_meter_calendar);
        mainActivity= (MainActivity) getActivity();
        tvsleDay=view.findViewById(R.id.fg3_listView_fgtv_tv);

        page1Item =mainActivity.getItemsPage1();

        fg1ListView=view.findViewById(R.id.fg3_listView_fg1);
        fg3Adapter=new Fg3_ListView_Adapter(fg3Page1Item, getContext());
        fg1ListView.setAdapter(fg3Adapter);


        return view;
    }

    TextView tvsleDay;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        for (Page1Item t: page1Item){
            fg1.add(t.getCalendar());
            fg1Today.add(t.getToDay());
        }

        for (int i=0; i<fg1Today.size(); i++){
                toDay=fg1Today.get(i);
                SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
            try {
                Date date=sdf.parse(toDay);
                fg1.get(i).setTime(date);
                days.add(new EventDay(fg1.get(i),R.drawable.ic_dot));
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }

//        if (page2Item != null){
//            for (Page2_item t: page2Item){
//                fg2.add(t.getCalendar());
//                fg2Today.add(t.getToDay());
//            }
//
////            for (int i=0; i<fg2Today.size(); i++){
////                String stoDay=fg2Today.get(i);
////                SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
////                try {
////                    Date date=sdf.parse(stoDay);
////                    fg2.get(i).setTime(date);
////                    days.add(new EventDay(fg2.get(i),R.drawable.ic_dot2));
////                } catch (ParseException e) {
////                    e.printStackTrace();
////                }
////            }
//        }



        calendarView.setEvents(days);
        if (fg1 !=null || fg2 !=null){
            calendarView.setOnDayClickListener(new OnDayClickListener() {
                @Override
                public void onDayClick(EventDay eventDay) {
                    Calendar calendar=eventDay.getCalendar();
                    fg3Page1Item.clear();

                    for (int i=0; i<page1Item.size(); i++){
                        if (fg1.get(i)==calendar){
                            tvsleDay.setText(page1Item.get(i).getToDay());
                          fg3Page1Item.add(new Fg3Page1Item(page1Item.get(i).getPlaceData(),page1Item.get(i).getMoneyData()));
                            fg3Adapter.notifyDataSetChanged();

                        }
                    }
                }
            });
        }



    }


    @Override
    public void onResume() {
        super.onResume();
        fg3Page1Item.clear();
        fg3Adapter.notifyDataSetChanged();
    }

    public void getItemfg2(Page2_item item){
        page2Item.add(item);
        if (page2Item != null){
            for (Page2_item t: page2Item){
                fg2.add(t.getCalendar());
                fg2Today.add(t.getToDay());
            }

            for (int i=0; i<fg2Today.size(); i++){
                String stoDay=fg2Today.get(i);
                SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
                try {
                    Date date=sdf.parse(stoDay);
                    fg2.get(i).setTime(date);
                    days.add(new EventDay(fg2.get(i),R.drawable.ic_dot2));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }


        }
//        MainActivity mainActivity = (MainActivity) getActivity();
//        mainActivity.fragmentsAdapter.notifyFrag3();

        FragmentTransaction ft=getFragmentManager().beginTransaction();
        ft.detach(this).attach(this).commit();
    }


}




















