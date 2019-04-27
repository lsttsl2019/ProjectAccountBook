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
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Page4Fragment extends Fragment {

   BarChart barChart;


    ArrayList<Page1Item> page1Items;
    ArrayList<Page2_item> page2Items;

    Button btn1;
    Button btn2;

    Calendar calendar;
    ArrayList<BarEntry> entries=new ArrayList<>();

    BarDataSet dataSet;
    BarData data;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_page4, container,false);
        MainActivity mainActivity= (MainActivity) getActivity();

        barChart=view.findViewById(R.id.BarChrt);
        btn1=view.findViewById(R.id.fg4_Btn1);
        btn2=view.findViewById(R.id.fg4_Btn2);
        calendar=Calendar.getInstance();
//        year=calendar.get(Calendar.YEAR);
//        calendar.set(year,month,date);




        if (mainActivity.getItemsPage1()!=null || mainActivity.getItemsPage2() !=null){
            page1Items=mainActivity.getItems();
            page2Items=mainActivity.getItem2s();

        }


        if (page1Items!=null && page2Items!=null){
            for (int i=0; i<12; i++){
                entries.add(new BarEntry(i+1, 0));
            }

        }


          dataSet=new BarDataSet( entries,"지출");
            dataSet.setColors(ColorTemplate.COLORFUL_COLORS);


            data=new BarData(dataSet);


            data.setDrawValues(true);
            data.setValueTextSize(0);


            barChart.setData(data);
            barChart.setDrawValueAboveBar(true);
            barChart.setHighlightFullBarEnabled(true);

            barChart.getDescription().setEnabled(false);
            barChart.getLegend().setEnabled(false);
            barChart.getAxisRight().setEnabled(false);
            barChart.getAxisLeft().setMinWidth(0);

            XAxis xAxis=barChart.getXAxis();
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            xAxis.setAvoidFirstLastClipping(false);

            YAxis yAxis=barChart.getAxisLeft();
            yAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
            yAxis.setMinWidth(0);
            xAxis.setGranularity(1);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (page1Items.size() != 0){

                    addchart();
                    btn1.setBackgroundColor(Color.YELLOW);
                    btn2.setBackgroundColor(Color.WHITE);

                }else {
                    Toast.makeText(getContext(), "아이템이없습니다.", Toast.LENGTH_SHORT).show();
                    btn1.setBackgroundColor(Color.WHITE);
                }

            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (page2Items.size()!=0){
                    add2Chart();
                    btn2.setBackgroundColor(Color.YELLOW);
                    btn1.setBackgroundColor(Color.WHITE);
                }else {
                    Toast.makeText(getContext(), "아이템이없습니다", Toast.LENGTH_SHORT).show();
                    btn2.setBackgroundColor(Color.WHITE);
                }
            }
        });


            return view;
        }
        //////////////////////////////oncreat
        void addchart(){
 for ( int k=0; k<page1Items.size(); k++){
        String s=page1Items.get(k).getToDay();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
        try {
            Date date=sdf.parse(s);
            calendar.setTime(date);
            String num=page1Items.get(k).moneyData;
            int num2=Integer.parseInt(num);
            int nums=0;
            nums+=num2;
            for (int i=0; i< 12; i++){
                if (i== calendar.get(Calendar.MONDAY)){

                    entries.add(new BarEntry(i+1, nums));

                }else{
                    entries.add(new BarEntry(i+1, 0));
                }

            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }///////////////////추출

        }

        void add2Chart() {
        for (int i=0; i<page2Items.size(); i++){
            String str=page2Items.get(i).getToDay();
            SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
            Date date= null;
            try {
                date = sdf.parse(str);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            calendar.setTime(date);
            String num=page2Items.get(i).money;
            int num2=Integer.parseInt(num);
            int nums=0;
            nums+=num2;
            for (int k=0; k<12; k++){
                if (k== calendar.get(Calendar.MONDAY)){
                    entries.add(new BarEntry(i+1, nums));
                }else {
                    entries.add(new BarEntry(i+1, 0));
                }
            }

            }
        }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



    }




}













