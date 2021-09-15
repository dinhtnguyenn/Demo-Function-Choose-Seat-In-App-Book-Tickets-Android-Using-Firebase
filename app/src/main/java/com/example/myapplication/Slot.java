package com.example.myapplication;

public class Slot {
    private String seat;
    private String flag;

    public Slot(){}

    public Slot(String seat, String flag) {
        this.seat = seat;
        this.flag = flag;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
