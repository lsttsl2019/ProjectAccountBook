package com.isttis2019.projectaccountbook;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.CharacterPickerDialog;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.ImageView;
import android.widget.QuickContactBadge;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ExpenditureActivity extends AppCompatActivity {


    ArrayList<Parcelable> parcelables=new ArrayList<>();
    BarChart barChart;
    Calendar calendar;
    ArrayList<BarEntry> entries=new ArrayList<>();

    BarDataSet dataSet;
    BarData data;

    TextView tvYyaer;
    TextView tvMothy;

    int year;
    int month;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenditure);


        barChart=findViewById(R.id.BarChrt);
        tvMothy=findViewById(R.id.na_mothy);
        tvYyaer=findViewById(R.id.na_year);
        calendar=Calendar.getInstance();
        year  =calendar.get(Calendar.YEAR);
        month = (calendar.get(Calendar.MONTH))+1;

        Intent intent=getIntent();
        parcelables=intent.getParcelableArrayListExtra("Item");

        redrawChart();


    }
 public void redrawChart(){
     for (int k=1; k<32; k++){
         entries.add(new BarEntry((float) k,0));
     }

     drawChart();

     dataSet =new BarDataSet(entries, "지출");
     dataSet.setColors(ColorTemplate.COLORFUL_COLORS);

     data=new BarData(dataSet);
     data.setDrawValues(true);
     data.setValueTextSize(12);
     data.setValueTextColor(Color.BLACK);


     barChart.setData(data);
     barChart.setDrawValueAboveBar(true);
     barChart.setHighlightFullBarEnabled(true);

     barChart.getDescription().setEnabled(false);
     barChart.getLegend().setEnabled(false);
     barChart.getAxisRight().setEnabled(false);
     

     XAxis xAxis=barChart.getXAxis();
     xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
     xAxis.setAvoidFirstLastClipping(false);
     xAxis.setGranularityEnabled(true);
     xAxis.setGranularity(1);
     xAxis.setAxisMinimum(0);

     YAxis yAxis=barChart.getAxisLeft();
     yAxis.setAxisMinimum(0);


 }

    public void drawChart(){
        if (parcelables!=null){
            for (int i=0; i< parcelables.size(); i++){
                String s= parcelables.get(i).toDayDate;
               SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
                String moneydate= parcelables.get(i).moneyDate;
                int money1=Integer.parseInt(moneydate);
                int moneys=0;
                moneys+=money1;
                try {
                    Date date=sdf.parse(s);
                    calendar.setTime(date);
                    if( (calendar.get(Calendar.MONTH)+1)==month){

                        for (int k=1; k<32; k++){
                            if (calendar.get(Calendar.DAY_OF_MONTH)==k){
//                                entries.add(new BarEntry(k,moneys));
                                entries.get(k).setY(moneys);
                            }else {
//                                entries.add(new BarEntry(k,0f));
                            }
                        }

                    }else {

                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }




        }
    }

    public void clcikBefore(View view) {
        entries.clear();
        barChart.clear();

        month--;
        if (month>0){
            tvMothy.setText(month+"");
        }else if (month==0){
            year--;
            month=12;
            tvMothy.setText(month+"");
            tvYyaer.setText(year+"");
        }

        redrawChart();



    }

    public void clickNext(View view) {
        entries.clear();
        barChart.clear();

        month++;
        if(month<13){
            tvMothy.setText(month+"");
        }else {
            year++;
            month=1;
            tvMothy.setText(month+"");
            tvYyaer.setText(year+"");
        }

        redrawChart();

    }

}


















































