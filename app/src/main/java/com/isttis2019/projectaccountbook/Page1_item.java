package com.isttis2019.projectaccountbook;

import android.graphics.Bitmap;
import android.widget.ImageView;

import java.util.Calendar;

public class Page1_item {

    Calendar calendar;
    String placeData;
    String timeData;
    String moneyData;
    String path;


    public Page1_item(Calendar calendar, String placeData, String timeData, String moneyData, String path) {
        this.calendar= calendar;
        this.placeData = placeData;
        this.moneyData = moneyData;
        this.path= path;
        this.timeData=timeData;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }



    public String getPlaceData() {
        return placeData;
    }

    public void setPlaceData(String placeData) {
        this.placeData = placeData;
    }

    public String getMoneyData() {
        return moneyData;
    }

    public void setMoneyData(String moneyData) {
        this.moneyData = moneyData;
    }




    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTimeData() {
        return timeData;
    }

    public void setTimeData(String timeData) {
        this.timeData = timeData;
    }


}
