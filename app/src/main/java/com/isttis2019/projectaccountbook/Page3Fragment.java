package com.isttis2019.projectaccountbook;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;

import java.util.ArrayList;
import java.util.Calendar;

public class Page3Fragment extends Fragment {

    CalendarView calendarView;
    ArrayList<EventDay>  days=new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view=inflater.inflate(R.layout.fragment_page3, container,false);


        ArrayList<Page1_item> items = ((MainActivity)getActivity()).getItems();
        ArrayList<Calendar> tmp = null;
        for (Page1_item t : items){
             tmp.add(t.getCalendar());
        }

    calendarView=view.findViewById(R.id.fg3_meter_calendar);
    //days.add(new EventDay(Calendar.getInstance(),R.drawable.ic_dot));
    calendarView.setEvents(days);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


}
