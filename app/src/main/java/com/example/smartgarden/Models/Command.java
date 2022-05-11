package com.example.smartgarden.Models;

import java.sql.Timestamp;
import java.util.Calendar;

public class Command {
    private Integer valve_id;
    private String date_time;
    private boolean state;
    private Integer  command_id;

    public Command(int valveId){
        this.valve_id = valveId;
    }

    public void setCommand_id(Integer command_id) {
        this.command_id = command_id;
    }

    public Integer getCommand_id() {
        return command_id;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public void setDate_time(String date_time) {
        this.date_time = date_time;
    }

    public void setValveId(Integer valveId) {
        this.valve_id = valveId;
    }

    public String getDate_time() {
        return date_time;
    }

    public Integer getValveId() {
        return valve_id;
    }

    public boolean getState() {
        return state;
    }
}
