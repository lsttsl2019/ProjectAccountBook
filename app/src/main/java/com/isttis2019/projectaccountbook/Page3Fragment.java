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

    ListView fg2ListView;
   Fg3_ListView_Adapter2 fg3Adpter2;
    ArrayList<Fg3Page2Item> fg3Page2Item=new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view=inflater.inflate(R.layout.fragment_page3, container,false);
        calendarView=view.findViewById(R.id.fg3_meter_calendar);
        mainActivity= (MainActivity) getActivity();
        tvsleDay=view.findViewById(R.id.fg3_listView_fgtv_tv);
        tvSleDay2=view.findViewById(R.id.fg3_listView_fg1_tv2);

        page1Item =mainActivity.getItemsPage1();

        fg1ListView=view.findViewById(R.id.fg3_listView_fg1);
        fg3Adapter=new Fg3_ListView_Adapter(fg3Page1Item, getContext());
        fg1ListView.setAdapter(fg3Adapter);

        fg2ListView=view.findViewById(R.id.fg3_listView_fg2);
        fg3Adpter2=new Fg3_ListView_Adapter2(fg3Page2Item,getContext());
        fg2ListView.setAdapter(fg3Adpter2);

        mainActivity.addCalendarView(calendarView);

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



        calendarView.setEvents(days);




        return view;
    }

    TextView tvsleDay;
    TextView tvSleDay2;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        if (fg1 !=null || fg2 !=null){
            calendarView.setOnDayClickListener(new OnDayClickListener() {
                @Override
                public void onDayClick(EventDay eventDay) {
                    Calendar calendar=eventDay.getCalendar();
                    fg3Page1Item.clear();
                    fg3Page2Item.clear();

                    Date date=calendar.getTime();
                    SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
                    String str=sdf.format(date);

                    for (int i=0; i<page1Item.size(); i++){
                        if (page1Item.get(i).toDay.equals(str)){
                            tvsleDay.setText(page1Item.get(i).getToDay());
                            fg3Page1Item.add(new Fg3Page1Item(page1Item.get(i).getPlaceData(),page1Item.get(i).getMoneyData()));
                            fg3Adapter.notifyDataSetChanged();

                        }
                    }
                    for (int i=0; i<page2Item.size(); i++){
                        if (page2Item.get(i).toDay.equals(str)){
                            tvSleDay2.setText(page2Item.get(i).getToDay());
                            fg3Page2Item.add(new Fg3Page2Item(page2Item.get(i).item,page2Item.get(i).money));
                            fg3Adpter2.notifyDataSetChanged();
                        }
                    }


                }
            });
        }


    }


    @Override
    public void onResume() {
        super.onResume();

        if (fg3Page1Item!=null || fg3Page2Item!=null){
            fg3Page1Item.clear();
            fg3Adapter.notifyDataSetChanged();
            fg3Page2Item.clear();
            fg3Adpter2.notifyDataSetChanged();
        }

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




















