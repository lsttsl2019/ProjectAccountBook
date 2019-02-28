package com.isttis2019.projectaccountbook;

import android.graphics.Bitmap;
import android.widget.ImageView;

import java.util.Calendar;

public class Page1Item {

    Calendar calendar;
    String toDay;
    String placeData;
    String timeData;
    String moneyData;
    String path;



    public Page1Item(Calendar calendar, String toDay,String placeData, String timeData, String moneyData, String path) {
        this.calendar = calendar;
        this.toDay= toDay;
        this.placeData = placeData;
        this.timeData = timeData;
        this.moneyData = moneyData;
        this.path = path;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public String getToDay() {
        return toDay;
    }

    public void setToDay(String toDay) {
        this.toDay = toDay;
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

    public String getTimeData() {
        return timeData;
    }

    public void setTimeData(String timeData) {
        this.timeData = timeData;
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
}
