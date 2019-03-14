package com.isttis2019.projectaccountbook;

import android.os.Parcel;

public class ParcelableIncome implements android.os.Parcelable {

    String today;
    String income;
    String time;
    String money;

    public ParcelableIncome(String today, String income, String time, String money) {
        this.today = today;
        this.income = income;
        this.time = time;
        this.money = money;
    }

    protected ParcelableIncome(Parcel in) {
        today = in.readString();
        income = in.readString();
        time = in.readString();
        money = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(today);
        dest.writeString(income);
        dest.writeString(time);
        dest.writeString(money);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ParcelableIncome> CREATOR = new Creator<ParcelableIncome>() {
        @Override
        public ParcelableIncome createFromParcel(Parcel in) {
            return new ParcelableIncome(in);
        }

        @Override
        public ParcelableIncome[] newArray(int size) {
            return new ParcelableIncome[size];
        }
    };

    public String getToday() {
        return today;
    }

    public void setToday(String today) {
        this.today = today;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }
}
