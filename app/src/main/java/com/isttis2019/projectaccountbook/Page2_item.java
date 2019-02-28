package com.isttis2019.projectaccountbook;

import java.util.Calendar;

public class Page2_item {

       Calendar calendar;
       String toDay;
        String time;
        String item;
        String money;

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public Page2_item(Calendar calendar, String toDay,String time, String item, String money) {
        this.calendar=calendar;
        this.toDay=toDay;
        this.time = time;
        this.item = item;
        this.money = money;
    }

    public String getToDay() {
        return toDay;
    }

    public void setToDay(String toDay) {
        this.toDay = toDay;
    }
}
