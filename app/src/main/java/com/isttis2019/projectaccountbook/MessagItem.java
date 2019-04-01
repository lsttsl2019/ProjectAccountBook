package com.isttis2019.projectaccountbook;

public class MessagItem {

    String name;
    String msg;
    String time;
    String profileUrl;

    public MessagItem(String name, String msg, String time, String profileUrl) {
        this.name = name;
        this.msg = msg;
        this.time = time;
        this.profileUrl = profileUrl;
    }

    //getValue 파이어베이스 사용할때 데이터를 얻어올때 빈생성자가 없으면 에러
    public MessagItem(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }
}
