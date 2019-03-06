package com.isttis2019.projectaccountbook;

import android.os.Parcel;

public class Parcelable implements android.os.Parcelable {

    String toDayDate;
    String moneyDate;

    public Parcelable(String toDay, String money){
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

    protected Parcelable(Parcel in) {
        toDayDate = in.readString();
        moneyDate = in.readString();
    }

    public static final Creator<Parcelable> CREATOR = new Creator<Parcelable>() {
        @Override
        public Parcelable createFromParcel(Parcel in) {
            return new Parcelable(in);
        }

        @Override
        public Parcelable[] newArray(int size) {
            return new Parcelable[size];
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
