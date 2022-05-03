package com.example.smartgarden.Models;

import java.sql.Timestamp;
import java.util.Calendar;

public class Command {
    private Integer valve_id;
    private Calendar date_time;
    private boolean state;

    public Command(int valveId, boolean state){
        this.valve_id = valveId;
        this.state = state;
    }


    public void setState(boolean state) {
        this.state = state;
    }

    public void setDate_time(Calendar date_time) {
        this.date_time = date_time;
    }

    public void setValveId(Integer valveId) {
        this.valve_id = valveId;
    }

    public Calendar getDate_time() {
        return date_time;
    }

    public Integer getValveId() {
        return valve_id;
    }

    public boolean getState() {
        return state;
    }
}
