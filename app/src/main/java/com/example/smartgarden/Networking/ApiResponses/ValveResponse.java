package com.example.smartgarden.Networking.ApiResponses;

import com.example.smartgarden.Models.Valve;

import java.util.ArrayList;

public class ValveResponse {
    private Integer id;
    private String name;
    private String description;
    private int icon;
    private boolean state;

    public Valve getValve() {
        return new Valve(id, name, description,icon,state);
    }
    public String getValveNames() {
        return name;
    }
}
