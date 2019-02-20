package com.isttis2019.projectaccountbook;

import android.graphics.Bitmap;
import android.widget.ImageView;

public class Page1_item {

    String dayData;
    String placeData;
    String timeData;
    String moneyData;
    String imgBill;




    public Page1_item(String dayData, String placeData, String timeData, String moneyData, String imgBill) {
        this.dayData = dayData;
        this.placeData = placeData;
        this.moneyData = moneyData;
        this.imgBill= imgBill;
        this.timeData=timeData;
    }

    public String getDatData() {
        return dayData;
    }

    public void setDatData(String datData) {
        this.dayData = datData;
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

    public String getImgBill() {
        return imgBill;
    }

    public void setImgBill(String imgBill) {
        this.imgBill = imgBill;
    }

    public String getTimeData() {
        return timeData;
    }

    public void setTimeData(String timeData) {
        this.timeData = timeData;
    }


}
