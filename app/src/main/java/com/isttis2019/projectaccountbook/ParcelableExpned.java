package com.isttis2019.projectaccountbook;

import android.os.Parcel;

public class ParcelableExpned implements android.os.Parcelable {

    String today;
    String place;
    String time;
    String money;
    String path;

    protected ParcelableExpned(Parcel in) {
        today = in.readString();
        place = in.readString();
        time = in.readString();
        money = in.readString();
        path = in.readString();
    }

    public ParcelableExpned(String today, String place, String time, String money, String path) {
        this.today = today;
        this.place = place;
        this.time = time;
        this.money = money;
        this.path = path;
    }

    public String getToday() {
        return today;
    }

    public void setToday(String today) {
        this.today = today;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(today);
        dest.writeString(place);
        dest.writeString(time);
        dest.writeString(money);
        dest.writeString(path);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ParcelableExpned> CREATOR = new Creator<ParcelableExpned>() {
        @Override
        public ParcelableExpned createFromParcel(Parcel in) {
            return new ParcelableExpned(in);
        }

        @Override
        public ParcelableExpned[] newArray(int size) {
            return new ParcelableExpned[size];
        }
    };
}
