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

import java.util.ArrayList;
import java.util.Calendar;

public class Page3Fragment extends Fragment {

    CalendarView calendarView;
    ArrayList<EventDay>  days=new ArrayList<>();
    ArrayList<Calendar> tmp =new ArrayList<>();

    MainActivity mainActivity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view=inflater.inflate(R.layout.fragment_page3, container,false);
        calendarView=view.findViewById(R.id.fg3_meter_calendar);
        mainActivity = (MainActivity) getActivity();
        ArrayList<Page1Item>  items = mainActivity.getItemsPage1();

        if (tmp!=null){
            for (Page1Item t : items){
                tmp.add(t.getCalendar());
                for (int i=0; i<=items.size(); i++){
                    days.add(new EventDay(tmp.get(i),R.drawable.ic_dot));
                }

            }

            calendarView.setEvents(days);

        }
        Toast.makeText(mainActivity, tmp.size()+" : "+ items.size(), Toast.LENGTH_SHORT).show();



        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }


}
