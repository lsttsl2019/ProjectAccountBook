package com.isttis2019.projectaccountbook;

import android.graphics.Color;
import android.media.audiofx.AudioEffect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.widget.Toast;

import com.applandeo.materialcalendarview.CalendarView;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Page4Fragment extends Fragment {

    PieChart pieChart1;
    PieChart pieChart2;

    Button wbBtn;
    Button dnjfBtn;
    Button susBtn;

    ArrayList<Page1Item> page1Items;
    ArrayList<Page2_item> page2Items;

//    CalendarView calendarView;
    Calendar calendar;
    String[] str;

    String[] s;

    ArrayList<PieEntry>  entries =new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        MainActivity mainActivity= (MainActivity) getActivity();
        if (mainActivity.getItemsPage1()==null &&mainActivity.getItemsPage2()==null){
            Toast.makeText(getContext(), "데이터를 추가해주세요", Toast.LENGTH_SHORT).show();
            View view=inflater.inflate(R.layout.fragment_page4, container,false);
            return view;
        }else {
            View view=inflater.inflate(R.layout.fragment_page4, container,false);


            pieChart1 =view.findViewById(R.id.bar_chart1);
            pieChart2=view.findViewById(R.id.bar_chart2);

            wbBtn=view.findViewById(R.id.wnBtn);
            dnjfBtn=view.findViewById(R.id.dnjfBtn);
            susBtn=view.findViewById(R.id.susBtn);

            calendar=Calendar.getInstance();

//            calendarView=mainActivity.calendarViews;

            pieChart1.setUsePercentValues(true);
            pieChart2.setUsePercentValues(true);
            pieChart1.getDescription().setEnabled(false);
            pieChart2.getDescription().setEnabled(false);
            pieChart1.setExtraOffsets(5,10,5,5);
            pieChart2.setExtraOffsets(5,10,5,5);
            pieChart1.setDrawHoleEnabled(true);
            pieChart2.setDrawHoleEnabled(true);
            pieChart1.setHoleColor(Color.WHITE);
            pieChart2.setHoleColor(Color.WHITE);
            pieChart1.setTransparentCircleRadius(61f);
            pieChart2.setTransparentCircleRadius(61f);



            if (mainActivity.getItemsPage1()!=null || mainActivity.getItemsPage2()!=null){
                page1Items=mainActivity.getItemsPage1();
                page2Items=mainActivity.getItemsPage2();
                pieChart1.setVisibility(View.VISIBLE);
                pieChart2.setVisibility(View.VISIBLE);

                 str=getResources().getStringArray(R.array.incomesle);





                for (int i=0; i<s.length; i++){
                    entries.add(new PieEntry(30+i, str[i]));
                }

                Description description=new Description();
                description.setText("Test");
                pieChart1.setDescription(description);
                pieChart1.getDescription().setEnabled(true);

                pieChart1.animateY(1000, Easing.EaseOutCubic);
                PieDataSet pieDataSet=new PieDataSet(entries,"지출");
                pieDataSet.setSliceSpace(3f);
                pieDataSet.setSelectionShift(5f);
                pieDataSet.setColors(ColorTemplate.JOYFUL_COLORS);

                PieData pieData=new PieData(pieDataSet);
                pieData.setValueTextSize(10f);
                pieData.setValueTextColor(Color.YELLOW);

                pieChart1.setData(pieData);




            }else if (mainActivity.getItemsPage1()==null){
                pieChart1.setVisibility(View.INVISIBLE);
            }else if (mainActivity.getItemsPage2()==null){
                pieChart2.setVisibility(View.INVISIBLE);
            }





            return view;
        }

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }




}






















