package com.luisa.smartnatal.Data;

public class Date {
    int id;
    String date;
    String name;
    String time;

    public  Date( String Date,String name, String time){
        this.date = Date;
        this.name = name;
        this.time = time;
    }

    public Date() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


}
