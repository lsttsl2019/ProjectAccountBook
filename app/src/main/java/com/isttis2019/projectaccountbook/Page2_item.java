package com.isttis2019.projectaccountbook;

public class Page2_item {

        String data;
        String time;
        String item;
        String money;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
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

    public Page2_item(String data, String time, String item, String money) {
        this.data = data;
        this.time = time;
        this.item = item;
        this.money = money;
    }
}
