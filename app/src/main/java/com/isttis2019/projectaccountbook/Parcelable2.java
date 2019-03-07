package com.isttis2019.projectaccountbook;

import android.os.Parcel;

public class Parcelable2 implements android.os.Parcelable {

    String toDayDate;
    String moneyDate;

    public Parcelable2(String toDay, String money){
        this.toDayDate=toDay;
        this.moneyDate=money;

    }

    public String getToDayDate() {
        return toDayDate;
    }

    public void setToDayDate(String toDayDate) {
        this.toDayDate = toDayDate;
    }

    public String getMoneyDate() {
        return moneyDate;
    }

    public void setMoneyDate(String moneyDate) {
        this.moneyDate = moneyDate;
    }

    protected Parcelable2(Parcel in) {
        toDayDate = in.readString();
        moneyDate = in.readString();
    }

    public static final Creator<Parcelable2> CREATOR = new Creator<Parcelable2>() {
        @Override
        public Parcelable2 createFromParcel(Parcel in) {
            return new Parcelable2(in);
        }

        @Override
        public Parcelable2[] newArray(int size) {
            return new Parcelable2[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(toDayDate);
        dest.writeString(moneyDate);
    }
}
