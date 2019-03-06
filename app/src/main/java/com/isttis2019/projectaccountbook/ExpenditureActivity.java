package com.isttis2019.projectaccountbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.Calendar;

public class ExpenditureActivity extends AppCompatActivity {


    ArrayList<Parcelable> parcelables=new ArrayList<>();
    BarChart barChart;
    Calendar calendar;
    ArrayList<BarEntry> entries=new ArrayList<>();

    BarDataSet dataSet;
    BarData data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenditure);
        barChart=findViewById(R.id.BarChrt);

        Intent intent=getIntent();
        parcelables=intent.getParcelableArrayListExtra("Item");

        if (parcelables!=null){
        for (int i=0; i< parcelables.size(); i++){

            }

        }






    }
}
