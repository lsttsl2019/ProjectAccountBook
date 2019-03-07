package com.isttis2019.projectaccountbook;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
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

        drawChart();


    }


    public void drawChart(){
        if (parcelables!=null){
            for (int i=0; i< parcelables.size(); i++){
                String s= parcelables.get(i).toDayDate;
                Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
                Toast.makeText(this, month+"", Toast.LENGTH_SHORT).show();
                SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
                String moneydate= parcelables.get(i).moneyDate;
                int money1=Integer.parseInt(moneydate);
                int moneys=0;
                moneys+=money1;
                try {
                    Date date=sdf.parse(s);
                    calendar.setTime(date);
                    Toast.makeText(this, "!!,  "+calendar.get(Calendar.DAY_OF_MONTH), Toast.LENGTH_SHORT).show();
                    if (calendar.get(Calendar.MONTH)==month){
                        for (int k=1; k<32; k++){
                            if (calendar.get(Calendar.DAY_OF_MONTH)==k){
                                Toast.makeText(this, ""+k, Toast.LENGTH_SHORT).show();
                                entries.add(new BarEntry(k,moneys));
                            }else {
                                Toast.makeText(this, "false", Toast.LENGTH_SHORT).show();
                                entries.add(new BarEntry(k,null));
                            }
                        }
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

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

           xAxis.setAxisMinimum(0);
           xAxis.setGranularity(1);


        }
    }

    public void clcikBefore(View view) {
        if (month>0){
            month--;
            tvMothy.setText(month+"");
        }else if (month==0){
            month=12;
            year--;
            tvMothy.setText(month+"");
            tvYyaer.setText(year+"");
        }


    }

    public void clickNext(View view) {
        if(month<13){
            month++;
            tvMothy.setText(month+"");
        }else {
            month=1;
            year++;
            tvMothy.setText(month+"");
            tvYyaer.setText(year+"'");
        }
    }
}



















































